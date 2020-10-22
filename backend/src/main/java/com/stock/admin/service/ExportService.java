package com.stock.admin.service;

import static com.stock.admin.utils.Helper.pageRequestFor;
import static com.stock.admin.utils.StockAdminConstants.PATH_PRODUCT_NAME;
import static com.stock.admin.utils.StockAdminConstants.PATH_PRODUCT_PACKAGING;
import static com.stock.admin.utils.StockAdminConstants.PRODUCT_DOES_NOT_EXISTS;
import static com.stock.admin.utils.StockAdminConstants.STOCK_DATE;
import static com.stock.admin.utils.StockAdminConstants.TOTAL_STOCK;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.mongodb.client.result.UpdateResult;
import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.Product;
import com.stock.admin.model.entity.Shop;
import com.stock.admin.model.entity.Stock;
import com.stock.admin.model.request.StockRequest;
import com.stock.admin.repository.ShopsRepository;
import com.stock.admin.repository.StocksRepository;
import com.stock.admin.utils.HeaderFooterPageEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Stock service.
 */
@Service
public class ExportService {
	private final StocksRepository stocksRepository;
	private final ShopsRepository shopsRepository;
	private final MongoTemplate mongoTemplate;

	private final Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
	private final Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12);

	/**
	 * Instantiates a new Stock service.
	 *
	 * @param stocksRepository the stocks repository
	 * @param productService   the product service
	 * @param mongoTemplate    the mongo template
	 */
	@Autowired
	public ExportService(StocksRepository stocksRepository, ShopsRepository shopsRepository,
			MongoTemplate mongoTemplate) {
		this.stocksRepository = stocksRepository;
		this.shopsRepository = shopsRepository;
		this.mongoTemplate = mongoTemplate;
	}

	public File getFileToExport(Date fromDate, Date toDate, String shopCode) throws Exception {
		createPDF("stocks.pdf", fromDate, toDate, shopCode);		
		return new File("stocks.pdf");
	}

	private void createPDF(String pdfFilename, Date fromDate, Date toDate, String shopCode) throws Exception {

		Document doc = new Document();
		PdfWriter docWriter = null;

		try {

			// file path
			String path = pdfFilename;
			docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));
			Shop shop = shopsRepository.findByShopCode(shopCode);
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
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			doc.close();
			if (docWriter != null) {
				// close the writer
				docWriter.close();
			}
		}
	}

	private void updateInfoTable(PdfPTable infoTable, Date fromDate, Date toDate) throws DocumentException {

		PdfPCell exportedDateLabel = new PdfPCell(new Phrase("Exported Date :- ", bfBold12));
		exportedDateLabel.setBorder(Rectangle.NO_BORDER);
		exportedDateLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
		infoTable.addCell(exportedDateLabel);
		PdfPCell exportedDate = new PdfPCell(new Phrase(getDate(new Date()), bfBold12));
		exportedDate.setBorder(Rectangle.NO_BORDER);
		exportedDate.setHorizontalAlignment(Element.ALIGN_LEFT);
		infoTable.addCell(exportedDate);
		PdfPCell fromDateLabel = new PdfPCell(new Phrase("From Date :- ", bfBold12));
		fromDateLabel.setBorder(Rectangle.NO_BORDER);
		fromDateLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
		infoTable.addCell(fromDateLabel);
		PdfPCell fromDateValue = new PdfPCell(new Phrase(getDate(fromDate), bfBold12));
		fromDateValue.setBorder(Rectangle.NO_BORDER);
		fromDateValue.setHorizontalAlignment(Element.ALIGN_LEFT);
		infoTable.addCell(fromDateValue);
		PdfPCell toDateLabel = new PdfPCell(new Phrase("To Date :- ", bfBold12));
		toDateLabel.setBorder(Rectangle.NO_BORDER);
		toDateLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
		infoTable.addCell(toDateLabel);
		PdfPCell toDateValue = new PdfPCell(new Phrase(getDate(toDate), bfBold12));
		toDateValue.setBorder(Rectangle.NO_BORDER);
		toDateValue.setHorizontalAlignment(Element.ALIGN_LEFT);
		infoTable.addCell(toDateValue);

	}

	private void updateDocumentHeader(Document doc) {
		// document header attributes
		doc.addAuthor("Stock Admin");
		doc.addCreationDate();
		doc.addProducer();
		doc.addCreator("Stock Admin");
		doc.addTitle("Report with Column Headings");
		doc.setPageSize(PageSize.A4);

	}

	private void updateStocksTable(PdfPTable stocksTable, Shop shop, Date fromDate, Date toDate) {
		// set table width a percentage of the page width
		stocksTable.setWidthPercentage(90f);

		// insert column headings
		insertCell(stocksTable, "Sl.no", Element.ALIGN_RIGHT, 1, bfBold12, true);
		insertCell(stocksTable, "Product Name", Element.ALIGN_LEFT, 1, bfBold12, true);
		insertCell(stocksTable, "Product Packaging", Element.ALIGN_RIGHT, 1, bfBold12, true);
		insertCell(stocksTable, "Stock Date", Element.ALIGN_RIGHT, 1, bfBold12, true);
		insertCell(stocksTable, "Incoming Stock Count", Element.ALIGN_RIGHT, 1, bfBold12, true);
		insertCell(stocksTable, "Outgoing Stock Count", Element.ALIGN_RIGHT, 1, bfBold12, true);
		insertCell(stocksTable, "Total Stocks", Element.ALIGN_RIGHT, 1, bfBold12, true);
		stocksTable.setHeaderRows(1);

		Integer slno = 1;

		Page<Stock> stocks = stocksRepository.findByProduct_ShopCodeAndStockDateBetween(shop.getShopCode(), fromDate,
				toDate, Pageable.unpaged());

		for (Stock stock : stocks.getContent()) {
			insertCell(stocksTable, slno.toString(), Element.ALIGN_RIGHT, 1, bf12, false);
			insertCell(stocksTable, stock.getProduct().getName(), Element.ALIGN_LEFT, 1, bf12, false);
			insertCell(stocksTable, stock.getProduct().getPackaging(), Element.ALIGN_RIGHT, 1, bf12, false);
			insertCell(stocksTable, getDate(stock.getStockDate()), Element.ALIGN_RIGHT, 1, bf12, false);
			insertCell(stocksTable, String.valueOf(stock.getOpeningStock()), Element.ALIGN_RIGHT, 1, bf12, false);
			insertCell(stocksTable, String.valueOf(stock.getClosingStock()), Element.ALIGN_RIGHT, 1, bf12, false);
			insertCell(stocksTable, String.valueOf(stock.getTotalStock()), Element.ALIGN_RIGHT, 1, bf12, false);
			slno++;
		}

	}

	private String getDate(Date stockDate) {
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setTimeZone(timeZone);
		return formatter.format(stockDate);
	}

	private void insertCell(PdfPTable table, String text, int align, int colspan, Font font, boolean isHeader) {

		// create a new cell with the specified Text and Font
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		if (isHeader) {
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		}
		// set the cell alignment
		cell.setHorizontalAlignment(align);
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
