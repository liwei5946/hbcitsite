package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import cn.edu.hbcit.services.LmService;
import cn.edu.hbcit.services.NewsMBService;

public class NewsMBServletSelect extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NewsMBService newsMBService=new NewsMBService();
	LmService lmService=new LmService();
	/**
	 * Constructor of the object.
	 */
	public NewsMBServletSelect() {
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

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//
		String condition=request.getParameter("condition");
		//
		if(condition.trim().equals("1"))
		{
			//HttpSession session =request.getSession();
			
			//List<NewsMB> newsMBList=newsMBService.selectAllMB();
			//session.setAttribute("newsMBList", newsMBList);
			out.print("success");
			
		}
		if(condition.trim().equals("2"))//ÐÞ¸ÄÀ¸Ä¿Ä£°å
		{
			String selectMbId=request.getParameter("selectMbId").trim();
			int id=Integer.parseInt(request.getParameter("id").trim());
			if(lmService.updateLmMb(id, selectMbId))
				out.print("success");
			else
				out.print("error");

		}
		
		out.flush();
		out.close();
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
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
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
