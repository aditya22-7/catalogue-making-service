package skupdfNew;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;


public class SkuTable extends ConnectClass {
 
	public ArrayList<Integer> convertJsonToAlist(JSONArray jsonArray) {
		ArrayList<Integer> skunumList = new ArrayList<>(); 
		int skuNo;
	
		for(Object i : jsonArray) {
			skuNo = (int)i;
			 if(skuNo!=0 && skuNo>0) {
				 skunumList.add(skuNo);
			 }
		}
		return skunumList;
	}
 
	 public void mainAssisst(JSONArray jsonArray) throws FileNotFoundException, MalformedURLException, IOException, SQLException{
		 
		 ArrayList<Integer> skunumList = convertJsonToAlist(jsonArray);
		 ConnectClass cc = new ConnectClass(); 
		 cc.Connect();
		 PDFOperator pdfOperator = new PDFOperator();
		 SkuDetailsClass sdc = new SkuDetailsClass(cc,pdfOperator);
	     
		 pdfOperator.makePDFdoc();
		 
		 if(skunumList.isEmpty())
			 sdc.dbOperation();
		 else {
			sdc.dbOperation(skunumList);
		}
	     
	     pdfOperator.getDocument().add(pdfOperator.getTable());
	     
	     pdfOperator.getDocument().close();
	     System.out.println("PDF Created !!!!");
	     
	     cc.CloseConnection();
	 }
	 
	 public void guide(){
	     
		 // SkuImagesClass -> skuImagesDb() -> localimgpath (local file image path where aws images are downloaded)
		 // AwsImageDownload -> downloadTheFile() -> imageStore (file path to store AWS images)
		 // PDFOperator -> makePDFdoc() -> path (PDF path)
		 
	 }
}