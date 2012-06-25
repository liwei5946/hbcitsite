package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import cn.edu.hbcit.services.LogService;
import cn.edu.hbcit.util.UtilTools;

public class SystemLogDeleteServlet extends HttpServlet {
	protected final Logger log = Logger.getLogger(SystemLogDeleteServlet.class.getName());

	/**
	 * Constructor of the object.
	 */
	public SystemLogDeleteServlet() {
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
		//log.debug("AAAAAAAAAAAAAA");
		PrintWriter out = response.getWriter();
		UtilTools ut = new UtilTools();
		LogService ls = new LogService();
		
		String beforeday = request.getParameter("beforeday");
		log.debug("删除天数："+beforeday);
		int delNum = 0;
		
		if(ut.isNumeric(beforeday)){	//判断beforeday是否是整型数据
			delNum = ls.delLog(Integer.parseInt(beforeday));
			log.debug("删除了"+delNum+"条日志记录");
		  }
		
		out.print(delNum);
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

		this.doGet(request, response);
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
