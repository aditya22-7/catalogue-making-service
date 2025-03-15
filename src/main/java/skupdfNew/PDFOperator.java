package skupdfNew;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.VerticalAlignment;

public class PDFOperator {
	
	Table table;
	Document document;
	
	public Table getTable() {
		return table;
	}
	public Document getDocument() {
		return document;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	
	public void columnStyling(String str) throws IOException {
		PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
	     Paragraph para = new Paragraph(str).setFont(font);
	     
	     para.setFontSize(10);
	     if(str.equals("SKU No.")) {
//	    	 para.setFontSize(10);
	    	 para.setBold();
	     }
	     para.setFixedLeading(0);
	     para.setMultipliedLeading(1);

	     Cell cell = new Cell();
	     cell.setBackgroundColor(Color.LIGHT_GRAY);
//	     cell.setMinHeight(30);
//	     cell.setHeight(20f);
//	     cell.setWidth(25f);
	     cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	     cell.add(para);
	     getTable().addCell(cell);
	}
	
	public void imgCell(String str)throws IOException{
		PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
	     Paragraph para = new Paragraph(str).setFont(font).setBold();
	     para.setFontSize(10);
	     para.setFixedLeading(0);
	     para.setMultipliedLeading(1);

	     Cell cell = new Cell(0,4);
	     cell.setBackgroundColor(Color.LIGHT_GRAY);
//	     cell.setMinHeight(30);
//	     cell.setHeight(20f);
//	     cell.setWidth(20f);
	     cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	     cell.add(para);
	     getTable().addCell(cell);
	}
	
	public void addText(String str) throws IOException{
	     PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
	     Paragraph para = new Paragraph(str).setFont(font);
	     para.setFontSize(9);
	     para.setFixedLeading(0);
	     para.setMultipliedLeading(1);

	     Cell cell = new Cell();
	     
	     cell.setMinHeight(40);
//	     cell.setHeight(20f);
	     cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	     cell.add(para);
	     getTable().addCell(cell);
	 }
	 public void makePDFdoc() throws FileNotFoundException{
	     String path = "C:\\Users\\Megha Mathur\\Desktop\\PdfTester\\SKUDetailsTableTestNew.pdf";
	     
	     PdfWriter pdfWriter = new PdfWriter(path);

//	     Rectangle pageSize = new Rectangle(1250,1400);

	     PdfDocument pdfDocument = new PdfDocument(pdfWriter);
	     document = new Document(pdfDocument);
	     pdfDocument.setDefaultPageSize(PageSize.A4);
	     setDocument(document);

	     float columnWidth[] = {10f,5f,10f,10f,10f,10f,10f,10f,10f,10f,30f};
	     
	     table = new Table(columnWidth);
	     setTable(table);
	 }
}
