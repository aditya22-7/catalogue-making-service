package tagfile;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class SkuExcelServlet
 */
public class SkuExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SkuExcelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String str = request.getParameter("sendInfoString");
		System.out.println("Printing sendInfo string ......\n"+str);
		
		JSONObject jsnobject = new JSONObject(str);  
		JSONArray jsonArray = jsnobject.getJSONArray("skuNumberArray"); 
		
		try {
			new PassskuNo().passVal(jsonArray);
			out.println("Excel Created !!!!");
		}catch (ClassCastException e) {
			System.out.println("Exception Exception Exception !!!!!!!!!!!!1");
			Thread thread = new Thread();
			thread.interrupt();	
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
