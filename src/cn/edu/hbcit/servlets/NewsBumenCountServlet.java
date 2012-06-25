package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.edu.hbcit.services.NewsService;
import cn.edu.hbcit.util.CountNews;

public class NewsBumenCountServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NewsService newsService=new NewsService();
	/**
	 * Constructor of the object.
	 */
	public NewsBumenCountServlet() {
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

		// 设置响应内容的类型 
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		//
		String startTime=request.getParameter("st");
		String endTime=request.getParameter("et");
		if(startTime.trim().length()==0 )
		{
			startTime="";
		}
		if(endTime.trim().length()==0)
		{
			endTime="";
		}
		List<CountNews> newsCountList=newsService.countBumenNews(startTime, endTime);
		Iterator<CountNews> it=newsCountList.iterator();
		StringBuffer s = new StringBuffer();
		s.append("{\"couns\":[");
		int ii=0;
		while(it.hasNext())
		{
			CountNews countNews=(CountNews)it.next();
			if(ii==0)
				s.append("{\"bm\":\"").append(countNews.getUnit().trim()).append("\",\"counts\":\"").append(countNews.getNumbs()).append("\"}");
			else
				s.append(",{\"bm\":\"").append(countNews.getUnit().trim()).append("\",\"counts\":\"").append(countNews.getNumbs()).append("\"}");
			ii++;
			
		}
		s.append("]}");
		PrintWriter out = response.getWriter();
		out.println(s);
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

		// 设置响应内容的类型 
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		//
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
