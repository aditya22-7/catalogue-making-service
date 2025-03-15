package tagfile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONArray;

public class PassskuNo {
	
	public void passVal(JSONArray jsonArray) throws FileNotFoundException, MalformedURLException, IOException, SQLException {
		ArrayList<Integer> aList = new ArrayList<>(); 
		int skuNo;

		for(Object i : jsonArray) {
			skuNo = (int)i;
			 if(skuNo!=0 && skuNo>0) {
				 aList.add(skuNo);
			 }
		}
		if(aList.isEmpty()) {
			System.out.println("ArrayList is Empty !!!!!!!!!!!!!!");
			new SKUdetailsDbClass().excelGenerator();
		}else {
			new SKUdetailsDbClass().excelGenerator(aList);
			System.out.println("Printing ArrayList....");
			for(int d : aList) {
				System.out.println(d);
			}
		}
		
				
	}
}
