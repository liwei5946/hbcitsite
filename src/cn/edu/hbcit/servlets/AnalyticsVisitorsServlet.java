package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import cn.edu.hbcit.services.LogService;

/**
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: 河北工业职业技术学院</p>
 *
 * @author 作者 : liwei5946@gmail.com
 * @version 创建时间：Nov 11, 2011 11:55:56 PM
 */
public class AnalyticsVisitorsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AnalyticsVisitorsServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//HttpSession session = request.getSession();
		
		//JSONArray pageviewsJson = new JSONArray();
		//JSONArray visitsJson = new JSONArray();
		JSONArray visitorsJson = new JSONArray();
		
		String startTime=request.getParameter("st");
		String endTime=request.getParameter("et");
		if(startTime == null || startTime.trim().length()==0 )
		{
			startTime="";
		}
		if(endTime == null || endTime.trim().length()==0)
		{
			endTime="";
		}
		
		LogService ga = new LogService();
		
		//pageviewsJson = ga.selectPageviewsJSON("2011-10-01", "2011-10-05");
		//visitsJson = ga.selectVisitsJSON("2011-10-01", "2011-10-05");
		visitorsJson = ga.selectVisitorsJSON(startTime, endTime);
        
		//session.setAttribute("pageviewsJson", pageviewsJson);
		//session.setAttribute("visitsJson", visitsJson);
		//session.setAttribute("visitorsJson", visitorsJson);
		
		//response.sendRedirect("../admin/test.jsp");
		//request.getRequestDispatcher("/admin/test.jsp").forward(request, response);
		out.print(visitorsJson);
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
