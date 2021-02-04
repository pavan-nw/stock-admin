package com.stock.admin.utils;

import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.stock.admin.model.entity.Shop;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.*;
import java.io.IOException;
import java.net.MalformedURLException;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

    private PdfTemplate t;
    private Image total;
    private Shop shop;
    
    public HeaderFooterPageEvent(Shop shop){
    	this.shop =shop;
    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        t = writer.getDirectContent().createTemplate(30, 16);
        try {
            total = Image.getInstance(t);
            total.setRole(PdfName.ARTIFACT);
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        addHeader(writer,document);
        addFooter(writer,document);
    }

    private void addHeader(PdfWriter writer,Document document){
        PdfPTable header = new PdfPTable(2);
        try {
            // set defaults
            header.setWidths(new int[]{5, 24});
            header.setTotalWidth(527);
            header.setLockedWidth(true);            
            // add image
            Image logo = Image.getInstance(HeaderFooterPageEvent.class.getResource("/agri_logo.jpeg"));
            PdfPCell imglogo = new PdfPCell(logo);
            imglogo.setBorder(Rectangle.BOTTOM);
            imglogo.setBorderColor(BaseColor.LIGHT_GRAY);
            imglogo.setFixedHeight(70);            
            header.addCell(imglogo);
            // add text
            PdfPCell text = new PdfPCell();
            text.setBorder(Rectangle.BOTTOM);
            text.setBorderColor(BaseColor.LIGHT_GRAY);            
            text.addElement(new Phrase(shop.getName()+"\n", new Font(FontFamily.TIMES_ROMAN, 15, Font.BOLD,BaseColor.BLUE)));
            text.addElement(new Phrase(shop.getLocation(), new Font(FontFamily.TIMES_ROMAN, 9, Font.BOLD)));            
            text.setFixedHeight(30);
            header.addCell(text);            
            // write content
            header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());            
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        } catch (MalformedURLException e) {
            throw new ExceptionConverter(e);
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }

    private void addFooter(PdfWriter writer,Document document){
        PdfPTable footer = new PdfPTable(3);
        try {
            // set defaults
            footer.setWidths(new int[]{24, 2, 1});
            footer.setTotalWidth(527);
            footer.setLockedWidth(true);
            footer.getDefaultCell().setFixedHeight(40);
            footer.getDefaultCell().setBorder(Rectangle.TOP);
            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add copyright
            footer.addCell(new Phrase("\u00A9 stock-admin.com", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));

            // add current page count
            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            footer.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));

            // add placeholder for total page count
            PdfPCell totalPageCount = new PdfPCell(total);
            totalPageCount.setBorder(Rectangle.TOP);
            totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
            footer.addCell(totalPageCount);

            // write page
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer.writeSelectedRows(0, -1, 34, 50, canvas);
            canvas.endMarkedContentSequence();
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        int totalLength = String.valueOf(writer.getPageNumber()).length();
        int totalWidth = totalLength * 5;
        ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
                new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)),
                totalWidth, 6, 0);
    }
}
