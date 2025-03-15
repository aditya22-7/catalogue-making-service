package skupdfNew;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class LocalImgDownloadServlet
 */
public class LocalImgDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocalImgDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			String str = request.getParameter("skuNumArrayString");
			System.out.println("Printing sendInfo string ......\n"+str);
			
			JSONObject jsnobject = new JSONObject(str);  
			JSONArray jsonArray = jsnobject.getJSONArray("skuNumberArray"); 
			
//			System.out.println("Printing JSON Array ====== "+jsonArray[]);
			new SkuTable().mainAssisst(jsonArray);
			
			
			out.println("PDF Created !!");
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		
		

}
}

