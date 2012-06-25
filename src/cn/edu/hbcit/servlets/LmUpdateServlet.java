package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import cn.edu.hbcit.services.LmService;

public class LmUpdateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Logger log = Logger.getLogger(LmUpdateServlet.class.getName());

	/**
	 * Constructor of the object.
	 */
	public LmUpdateServlet() {
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
		response.setContentType("text/html");
		
		LmService lmService=new LmService();
		PrintWriter out = response.getWriter();
		//
		String selectCondition=request.getParameter("Condition").trim();
		//
		if(selectCondition.trim().equals("0"))//update lmname
		{
			int id=Integer.parseInt(request.getParameter("id").trim());
			String lmName=new String(request.getParameter("lmName").trim().getBytes("iso-8859-1"),"gbk");
			int lmLevel=Integer.parseInt(request.getParameter("lmLevel"));
			if(lmService.updateCurrentLm(id, lmName,lmLevel)){
				log.warn("成功将第"+id+"号栏目名称更改为“"+lmName+"”，栏目等级更改为“"+lmLevel+"”。");
				out.println("success");
			}				
			else
				out.println("error");
		}
		//
		if(selectCondition.trim().equals("1"))//union lm
		{
			int lmUnion1=Integer.parseInt(request.getParameter("lmUnion1").trim());
			int lmUnion2=Integer.parseInt(request.getParameter("lmUnion2").trim());
		
			if(lmService.lmUnion(lmUnion1, lmUnion2)){
				log.warn("成功将栏目“"+lmUnion1+"”与“"+lmUnion2+"”合并。");
				out.println("success");
			}
			else
				out.println("error");
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

		doGet(request,  response);
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
