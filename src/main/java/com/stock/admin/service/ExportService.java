package com.stock.admin.service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.Shop;
import com.stock.admin.model.entity.Stock;
import com.stock.admin.repository.ShopsRepository;
import com.stock.admin.repository.StocksRepository;
import com.stock.admin.utils.HeaderFooterPageEvent;
import static com.stock.admin.utils.Helper.pageRequestFor;
import static com.stock.admin.utils.StockAdminConstants.SOMETHING_WENT_WRONG;
import static com.stock.admin.utils.StockAdminConstants.STOCK_DATE;
import static com.stock.admin.utils.StockAdminConstants.INVALID_SHOP_CODE;
import static com.stock.admin.utils.StockAdminConstants.NO_STOCKS_FOUND;
import static com.stock.admin.utils.StockAdminConstants.STOCK_DATE_LABEL;
import static com.stock.admin.utils.StockAdminConstants.SL_NO;
import static com.stock.admin.utils.StockAdminConstants.PRODUCT_NAME;
import static com.stock.admin.utils.StockAdminConstants.PRODUCT_PACKAGING;
import static com.stock.admin.utils.StockAdminConstants.STOCK_ADMIN;
import static com.stock.admin.utils.StockAdminConstants.STOCK_REPORT;
import static com.stock.admin.utils.StockAdminConstants.INCOMING_STOCK_COUNT;
import static com.stock.admin.utils.StockAdminConstants.OUTGOING_STOCK_COUNT;
import static com.stock.admin.utils.StockAdminConstants.TOTAL_STOCKS;
import static com.stock.admin.utils.StockAdminConstants.EXPORTED_DATE;
import static com.stock.admin.utils.StockAdminConstants.FROM_DATE;
import static com.stock.admin.utils.StockAdminConstants.TO_DATE;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * The type Stock service.
 */
@Service
public class ExportService {
	
	private final StocksRepository stocksRepository;
	private final ShopsRepository shopsRepository;
	private final Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
	private final Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12);
	
	/**
	 * Instantiates a new export service.
	 *
	 * @param stocksRepository the stocks repository
	 * @param shopsRepository the shops repository
	 */
	@Autowired
	public ExportService(StocksRepository stocksRepository, ShopsRepository shopsRepository) {
		this.stocksRepository = stocksRepository;
		this.shopsRepository = shopsRepository;
	}

	/**
	 * Gets the file to export.
	 *
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @param shopCode the shop code
	 * @return the file to export
	 * @throws Exception the exception
	 */
	public synchronized File getFileToExport(Date fromDate, Date toDate, String shopCode) {
		createPDF("stocks_"+shopCode+".pdf", fromDate, toDate, shopCode);		
		return new File("stocks_"+shopCode+".pdf");
	}

	/**
	 * Creates the PDF.
	 *
	 * @param pdfFilename the pdf filename
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @param shopCode the shop code
	 * @throws Exception the exception
	 */
	private void createPDF(String pdfFilename, Date fromDate, Date toDate, String shopCode) {

		Document doc = new Document();
		PdfWriter docWriter = null;

		try {

			// file path
			String path = pdfFilename;
			docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));
			Shop shop = shopsRepository.findByShopCode(shopCode);
			if(shop!=null) {
				docWriter.setPageEvent(new HeaderFooterPageEvent(shop));
				updateDocumentHeader(doc);

				// open document
				doc.open();
				doc.add(new Paragraph("\n\n\n\n\n"));
				PdfPTable stocksTable = new PdfPTable(7);
				PdfPTable infoTable = new PdfPTable(2);

				updateStocksTable(stocksTable, shop, fromDate, toDate);
				updateInfoTable(infoTable, fromDate, toDate);
				doc.add(infoTable);
				doc.add(new Paragraph("\n\n"));
				doc.add(stocksTable);
			}
			else {
				throw new StockAdminApplicationException(INVALID_SHOP_CODE,HttpStatus.BAD_REQUEST);
			}
			
		} catch (StockAdminApplicationException stockException) {			
			throw stockException;
		} catch (DocumentException | FileNotFoundException e) {		
			throw new StockAdminApplicationException(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			doc.close();
			if (docWriter != null) {
				// close the writer
				docWriter.close();
			}
		}
	}

	/**
	 * Update info table.
	 *
	 * @param infoTable the info table
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @throws DocumentException the document exception
	 */
	private void updateInfoTable(PdfPTable infoTable, Date fromDate, Date toDate) {
		
		insertCell(infoTable, EXPORTED_DATE, Element.ALIGN_RIGHT, 1, bfBold12, false,Rectangle.NO_BORDER);
		insertCell(infoTable, getDate(new Date(),"IST"), Element.ALIGN_LEFT, 1, bfBold12, false,Rectangle.NO_BORDER);		
		insertCell(infoTable, FROM_DATE, Element.ALIGN_RIGHT, 1, bfBold12, false,Rectangle.NO_BORDER);
		insertCell(infoTable, getDate(fromDate,"UTC"), Element.ALIGN_LEFT, 1, bfBold12, false,Rectangle.NO_BORDER);
		insertCell(infoTable, TO_DATE, Element.ALIGN_RIGHT, 1, bfBold12, false,Rectangle.NO_BORDER);
		insertCell(infoTable, getDate(toDate,"UTC"), Element.ALIGN_LEFT, 1, bfBold12, false,Rectangle.NO_BORDER);
	}

	/**
	 * Update document header.
	 *
	 * @param doc the doc
	 */
	private void updateDocumentHeader(Document doc) {
		// document header attributes
		doc.addAuthor(STOCK_ADMIN);
		doc.addCreationDate();
		doc.addProducer();
		doc.addCreator(STOCK_ADMIN);
		doc.addTitle(STOCK_REPORT);
		doc.setPageSize(PageSize.A4);

	}

	/**
	 * Update stocks table.
	 *
	 * @param stocksTable the stocks table
	 * @param shop the shop
	 * @param fromDate the from date
	 * @param toDate the to date
	 */
	private void updateStocksTable(PdfPTable stocksTable, Shop shop, Date fromDate, Date toDate) {
		// set table width a percentage of the page width
		stocksTable.setWidthPercentage(90f);

		// insert column headings
		insertCell(stocksTable, SL_NO, Element.ALIGN_RIGHT, 1, bfBold12, true,Rectangle.BOX);
		insertCell(stocksTable, PRODUCT_NAME, Element.ALIGN_LEFT, 1, bfBold12, true,Rectangle.BOX);
		insertCell(stocksTable, PRODUCT_PACKAGING, Element.ALIGN_RIGHT, 1, bfBold12, true,Rectangle.BOX);
		insertCell(stocksTable, STOCK_DATE_LABEL, Element.ALIGN_RIGHT, 1, bfBold12, true,Rectangle.BOX);
		insertCell(stocksTable, INCOMING_STOCK_COUNT, Element.ALIGN_RIGHT, 1, bfBold12, true,Rectangle.BOX);
		insertCell(stocksTable, OUTGOING_STOCK_COUNT, Element.ALIGN_RIGHT, 1, bfBold12, true,Rectangle.BOX);
		insertCell(stocksTable, TOTAL_STOCKS, Element.ALIGN_RIGHT, 1, bfBold12, true,Rectangle.BOX);
		stocksTable.setHeaderRows(1);

		Integer slno = 1;

		Page<Stock> stocks = stocksRepository.findByProduct_ShopCodeAndStockDateBetween(shop.getShopCode(), fromDate,
				toDate, pageRequestFor(1, Integer.MAX_VALUE, "DESC", STOCK_DATE));
		
		if (stocks.isEmpty()) {
			throw new StockAdminApplicationException(NO_STOCKS_FOUND,
					HttpStatus.EXPECTATION_FAILED);
		}

		for (Stock stock : stocks.getContent()) {
			insertCell(stocksTable, slno.toString(), Element.ALIGN_RIGHT, 1, bf12, false,Rectangle.BOX);
			insertCell(stocksTable, stock.getProduct().getName(), Element.ALIGN_LEFT, 1, bf12, false,Rectangle.BOX);
			insertCell(stocksTable, stock.getProduct().getPackaging(), Element.ALIGN_RIGHT, 1, bf12, false,Rectangle.BOX);
			insertCell(stocksTable, getDate(stock.getStockDate(),"UTC"), Element.ALIGN_RIGHT, 1, bf12, false,Rectangle.BOX);
			insertCell(stocksTable, String.valueOf(stock.getOpeningStock()), Element.ALIGN_RIGHT, 1, bf12, false,Rectangle.BOX);
			insertCell(stocksTable, String.valueOf(stock.getClosingStock()), Element.ALIGN_RIGHT, 1, bf12, false,Rectangle.BOX);
			insertCell(stocksTable, String.valueOf(stock.getTotalStock()), Element.ALIGN_RIGHT, 1, bf12, false,Rectangle.BOX);
			slno++;
		}

	}

	/**
	 * Gets the date.
	 *
	 * @param stockDate the stock date
	 * @return the date
	 */
	private String getDate(Date stockDate,String zone) {
		TimeZone timeZone = TimeZone.getTimeZone(zone);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setTimeZone(timeZone);
		return formatter.format(stockDate);
	}

	/**
	 * Insert cell.
	 *
	 * @param table the table
	 * @param text the text
	 * @param align the align
	 * @param colspan the colspan
	 * @param font the font
	 * @param isHeader the is header
	 */
	private void insertCell(PdfPTable table, String text, int align, int colspan, Font font, boolean isHeader,int border) {

		// create a new cell with the specified Text and Font
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		if (isHeader) {
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		}
		// set the cell alignment
		cell.setHorizontalAlignment(align);
		cell.setBorder(border);
		// set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		// in case there is no text and you wan to create an empty row
		if (text.trim().equalsIgnoreCase("")) {
			cell.setMinimumHeight(10f);
		}
		// add the call to the table
		table.addCell(cell);

	}

}
