package skupdfNew;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SkuDetailsClass extends ConnectClass{
	
	ConnectClass connect;
	PDFOperator pdfOperator;
	ResultSet rs_sku_one;
	
	
	public ResultSet getRs_sku_one() {
		return rs_sku_one;
	}
	public void setRs_sku_one(ResultSet rs_sku_one) {
		this.rs_sku_one = rs_sku_one;
	}
	public ConnectClass getConnect() {
		return connect;
	}
	public PDFOperator getpdfOperator() {
		return pdfOperator;
	}

	public SkuDetailsClass(ConnectClass connect,PDFOperator pdfOperator) {
		this.connect = connect;
		this.pdfOperator = pdfOperator;
	}
	
	public String getItemType(String item_id) throws SQLException {
		String queryString = String.format("Select \"itemType\"from public.\"ItemTypeMaster\" where id = '%s'",item_id);
		Statement st_itemtype_master = getConnect().getCon().createStatement();
        ResultSet rs_itemtype_master = st_itemtype_master.executeQuery(queryString);
        String itemtype_name="";
        while(rs_itemtype_master.next()) {
        	itemtype_name = rs_itemtype_master.getString(1);
        }
        return itemtype_name;
	}
	
	public void headers(ResultSetMetaData rsMetaData) throws SQLException, IOException {
		 String colname;
         for(int p=2;p<=rsMetaData.getColumnCount();p++){
             //System.out.println(rs_sku.getString(p));
        	 colname = rsMetaData.getColumnName(p);
        	 if(p==2) {
        		 colname = "SKU No.";
        	 }
        	 if(p==3) {
        		 colname = "skuType ID";
        	 }
        	 if(p==4) {
        		 colname = "sku Purity";
        	 }
        	 if(p==5) {
        		 colname = "Net Wt.";
             }
        	 if(p==8) {
        		 colname = "Gross Wt.";
             }
             if(p==9) {
            	 colname = "Small Stones";	
             }
             if(p==10) {
            	 colname = "Big Stones";	
             }
//             System.out.println("colname "+p+" ===== "+colname);
        	 getpdfOperator().columnStyling(colname);
         }
         getpdfOperator().imgCell("Image");
         
	}
	
	public void dbOperation(ArrayList<Integer> skunumList)throws FileNotFoundException, MalformedURLException, IOException, SQLException{

		for(int skunum : skunumList) {
			String queryString = String.format("Select id,\"skuNo\",\"skuTypeID\",\"skuPurity\",\"skuWeight\",\"entryDate\",\"skuState\",\"grossWeight\",stones,\"bigStone\",\"modelNo\" from public.\"SKUDetails\" where \"skuNo\" = %s", skunum);
			
			SkuImagesClass sic = new SkuImagesClass(pdfOperator);
	         Statement st = getConnect().getCon().createStatement();
	         ResultSet rs_sku_one = st.executeQuery(queryString);
	         
	         ResultSetMetaData rsMetaData = rs_sku_one.getMetaData();
	         if(skunum == skunumList.get(0)) {
	        	 headers(rsMetaData);
	         }
	         
	         while(rs_sku_one.next()){
	             for(int p=2;p<=rsMetaData.getColumnCount();p++){	
	                 String str = rs_sku_one.getString(p);
	                 
	                 if(p==3) {
	                	 str = getItemType(rs_sku_one.getString(p));
	                 }
	                 if(p==6) {
	                	 String strdateString = str;
	                	 str = strdateString.substring(2,10);	
	                 }
	                 
	                 if(str==null){
	                     str="xxxx";
	                 }
	                 getpdfOperator().addText(str);
	             }
	             sic.skuImagesDb(getConnect(),rs_sku_one.getString(1));
	         }
		}
	
	}
	int count=1;
	public void dbOperation() throws FileNotFoundException, MalformedURLException, IOException{
	     try{
	    	 String queryString = "Select id,\"skuNo\",\"skuTypeID\",\"skuPurity\",\"skuWeight\",\"entryDate\",\"skuState\",\"grossWeight\",stones,\"bigStone\",\"modelNo\" from public.\"SKUDetails\" ORDER BY id DESC";
	    	 
	    	 SkuImagesClass sic = new SkuImagesClass(pdfOperator);
	         Statement st = getConnect().getCon().createStatement();
	         ResultSet rs_sku = st.executeQuery(queryString);

	         ResultSetMetaData rsMetaData = rs_sku.getMetaData();
	         
	         headers(rsMetaData);
	         
	         while(rs_sku.next()){
	             for(int p=2;p<=rsMetaData.getColumnCount();p++){	
	                 String str = rs_sku.getString(p);
	                 
	                 if(p==3) {
	                	 str = getItemType(rs_sku.getString(p));
	                 }
	                 if(p==6) {
	                	 String strdateString = str;
	                	 str = strdateString.substring(2,10);	
	                 }
	                 
	                 if(str==null){
	                     str="xxxx";
	                 }
	                 getpdfOperator().addText(str);
	             }
	             sic.skuImagesDb(getConnect(),rs_sku.getString(1));
	             
	             System.out.println("Row Printed !! --- "+(count++));
	         }
	         
	     }catch(SQLException e){
	         e.printStackTrace();
	     }
	 }
	
}
