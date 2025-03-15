package skupdfNew;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;


public class SkuImagesClass extends ConnectClass {
	
	PDFOperator pdfOperator;
	
//	public SkuImagesClass() {}
		
	public SkuImagesClass(PDFOperator pdfOperator) {
		this.pdfOperator=pdfOperator;
	}
	
	public PDFOperator getpdfOperator() {
		return pdfOperator;
	}
	
	public void skuImagesDb(ConnectClass connect,String skuImagesid) throws FileNotFoundException, MalformedURLException, IOException{
	     try{
	    	 String queryString = String.format("Select image from public.\"skuImages\" where \"skuID\" = '%s'",skuImagesid);
	         
	    	 Statement st = connect.getCon().createStatement();
	         ResultSet rs_skuimage = st.executeQuery(queryString);

	         String imageFile1;
	         String str;
	         System.out.println("skuImagesDb execution for current row !!!!!!!!");
	         int count=1;
	         while(rs_skuimage.next()){
	        	
	        	 if(count==1) {
	        		 System.out.println("________Printing while loop _________");
	        		 str = rs_skuimage.getString(1);
		        	 
		        	 String filename = new File(str).getName();
//		        	 System.out.println("AWS Image File ====== "+filename);
	                 String localimgpath = "C:\\Users\\Megha Mathur\\Desktop\\PdfTester\\SKUDetailsImages\\";
	                 
	                 imageFile1 = localimgpath+filename;
//		        		 new AwsImageDownload(str).DownloadInstructions();
	                 ImageData data1 = ImageDataFactory.create(imageFile1); 
	                 Image img1 = new Image(data1);  
	                 Cell cell = new Cell(0,4);
	                
	                 getpdfOperator().getTable().addCell(cell.add(img1.scaleAbsolute(80f,80f)));
	                 if(str == null) {
	                	 str = "xxxx";
	                	 getpdfOperator().addText(str);
	                 }
	                 count++;
	        	 }
	        	 
	         }
         }
	     catch(SQLException e){
	         e.printStackTrace();
	     }
	}
}
	        	 
                 
                 
                 
                 
                 
	        	 
	             
	         