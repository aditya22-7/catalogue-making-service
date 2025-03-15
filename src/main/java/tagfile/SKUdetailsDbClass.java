package tagfile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class SKUdetailsDbClass extends ConnectClass{
	
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	public XSSFWorkbook getWorkbook() {
		return workbook;
	}


	public XSSFSheet getSheet() {
		return sheet;
	}


	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}


	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}


	
	public void makeExcelSheet() {
		workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Tag file");
		setWorkbook(workbook);
		setSheet(sheet);
		
		ArrayList<String> colarr = new ArrayList<>(); 
		colarr.add("SR NO");
		colarr.add("ITEM CODE");
		colarr.add("G WT");
		colarr.add("S.");
		colarr.add("BSt.");
		colarr.add("PURITY");
		colarr.add("Design No");
		colarr.add("QR CODE");
		
		XSSFRow row_header = getSheet().createRow(0);
        
        
        for (int p=0;p<colarr.size();p++) {
       	 XSSFCell cell = row_header.createCell(p);
       	 cell.setCellValue(colarr.get(p));
        }
	}
	
	
	public void excelGenerator(ArrayList<Integer> aList) throws FileNotFoundException, MalformedURLException, IOException{
	    
		Connect();
		makeExcelSheet();
		try{
	         
	         int rownum=1;
	         for(int skunum : aList) {
	        	 
	        	 String queryString = String.format("Select \"skuNo\",\"grossWeight\",stones,\"bigStone\",\"skuPurity\",\"designID\" from public.\"SKUDetails\" where \"skuNo\" = '%s'",skunum);	    	 

		         Statement st = getCon().createStatement();
		         ResultSet rs_sku = st.executeQuery(queryString);

		         ResultSetMetaData rsMetaData = rs_sku.getMetaData();

		         
		         
		         double data;
		         String qrcodeString,itsdata;		         
		         
		         while(rs_sku.next()){
		        	 
		        	 XSSFRow row_content = getSheet().createRow(rownum);
		        	 
		        	 
		        	 XSSFCell cell_srNo = row_content.createCell(0);
		        	 cell_srNo.setCellValue(rownum);
		        	 
		        	 
		             for(int p=1;p<=rsMetaData.getColumnCount();p++){
		                 String str = rs_sku.getString(p);
		                 XSSFCell cell_skudata = row_content.createCell(p);
//		                 cell_skudata.setCellValue(str);
		                 if(str!=null) {
		                	 if(p!=6) {
		                		 data = Double.parseDouble(str);
		                		 cell_skudata.setCellValue((Double) data);
		                	 }
		                 }else {
		                	 str="0";
		                	 cell_skudata.setCellValue(Double.parseDouble(str));
		                 }
		                 if(p==6) {
		                	 str = String.format("OG50%s", rs_sku.getString(1));
		                	 cell_skudata.setCellValue(str);
		                 }
		             }
		             itsdata = rs_sku.getString(2);
		             if(rs_sku.getString(2)==null) {
		            	 itsdata = "0";
		             }
		             
		             qrcodeString = String.format("%s/%s/%s/%s",rownum,rs_sku.getString(1),itsdata,rs_sku.getString(5));
		             XSSFCell cell_qrcode = row_content.createCell(7);
		             cell_qrcode.setCellValue(qrcodeString);
		             
		         }
	        	 System.out.println("skunum ====== "+skunum);
	        	 rownum++;
	        	 
	         }
	         
	         FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Megha Mathur\\Desktop\\PdfTester\\tag_sheet.xlsx");
	         getWorkbook().write(fileOut);
	         getWorkbook().close();
	         
	     }catch(Exception e){
	         e.printStackTrace();
	     }
		CloseConnection();
	 }
	
	public void excelGenerator() throws SQLException, IOException {
		
		Connect();
		makeExcelSheet();
		String queryString = String.format("Select \"skuNo\",\"grossWeight\",stones,\"bigStone\",\"skuPurity\",\"designID\" from public.\"SKUDetails\" ORDER BY id DESC");	    	 

        Statement st = getCon().createStatement();
        ResultSet rs_sku_all = st.executeQuery(queryString);
        ResultSetMetaData rsMetaData = rs_sku_all.getMetaData();

        
        double data;
        String qrcodeString,itsdata;
        int rownum=1;
        while(rs_sku_all.next()) {
        	
        	 XSSFRow row_content = getSheet().createRow(rownum);
        	 
        	 
        	 XSSFCell cell_srNo = row_content.createCell(0);
        	 cell_srNo.setCellValue(rownum);
        	 
        	 
             for(int p=1;p<=rsMetaData.getColumnCount();p++){
                 String str = rs_sku_all.getString(p);
                 XSSFCell cell_skudata = row_content.createCell(p);
//                 cell_skudata.setCellValue(str);
                 if(str!=null) {
                	 if(p!=6) {
                		 data = Double.parseDouble(str);
                		 cell_skudata.setCellValue((Double) data);
                	 }
                 }else {
                	 str="0";
                	 cell_skudata.setCellValue(Double.parseDouble(str));
                 }
                 if(p==6) {
                	 str = String.format("OG50%s", rs_sku_all.getString(1));
                	 cell_skudata.setCellValue(str);
                 }
             }
             itsdata = rs_sku_all.getString(2);
             if(rs_sku_all.getString(2)==null) {
            	 itsdata = "0";
             }
             qrcodeString = String.format("%s/%s/%s/%s",rownum,rs_sku_all.getString(1),itsdata,rs_sku_all.getString(5));
             XSSFCell cell_qrcode = row_content.createCell(7);
             cell_qrcode.setCellValue(qrcodeString);
        	
        	rownum++;
        }
        
        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Megha Mathur\\Desktop\\PdfTester\\tag_sheet.xlsx");
        getWorkbook().write(fileOut);
        getWorkbook().close();
        
        CloseConnection();
	}
	
	
}
