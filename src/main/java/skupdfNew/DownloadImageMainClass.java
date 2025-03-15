package skupdfNew;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DownloadImageMainClass extends ConnectClass{
	public void dbOperation() throws FileNotFoundException, MalformedURLException, IOException{
	     try{
	    	 String queryString = "Select image from public.\"skuImages\" ORDER BY id DESC";
	    	 
	    	 PDFOperator pdfOperator = new PDFOperator();
	    	 pdfOperator.makePDFdoc();
	         Statement st = getCon().createStatement();
	         ResultSet rs_sku = st.executeQuery(queryString);
	         
	         int count=1;
	         String str;
	         while(rs_sku.next()){
	        	 
		        	 str = (String)rs_sku.getString(1);
		        	 System.out.println("Downloading Image "+(count++)+"....");
		        	 new AwsImageDownload(str).DownloadInstructions();
	             
	             }
	         
	         
	         
	     }catch(SQLException e){
	         e.printStackTrace();
	     }
	 }
	 
	 public void mainAssisst() throws FileNotFoundException, MalformedURLException, IOException, SQLException{
	     Connect();
	     dbOperation();
	     CloseConnection();
	 }
	 
	 public static void main(String[] args) throws FileNotFoundException, MalformedURLException, IOException, SQLException {
	     
	     new DownloadImageMainClass().mainAssisst();
	 }
}
