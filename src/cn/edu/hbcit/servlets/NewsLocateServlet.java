package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


import cn.edu.hbcit.pojo.News;
import cn.edu.hbcit.services.NewsService;
import cn.edu.hbcit.services.LmService;
import cn.edu.hbcit.util.UtilTools;

public class NewsLocateServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Logger log = Logger.getLogger(NewsLocateServlet.class.getName());
	/**
	 * Constructor of the object.
	 */
	public NewsLocateServlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		NewsService ns = new NewsService();
		LmService ls = new LmService();
		UtilTools ut = new UtilTools();
		HttpSession session = request.getSession();
		String id = "";
		String lm = "";
		String lmName = "";
		id = request.getParameter("id");
		
		try{
			if(ut.isNumeric(id)){	//判断id是否是整型数据
				list = ns.newsLocate(Integer.parseInt(id));
				lm = ns.getLmIdString(Integer.parseInt(id));
				lmName = ls.getLmName(lm);
				
			}

				
			

		}catch(Exception e){
			log.error(e);
		}
		
		if(list!=null){
			News news=new News();
			news=(News)list.get(0);
			session.setAttribute("newslist", list);
			session.setAttribute("news", news);
			session.setAttribute("lmName", lmName);
			session.setAttribute("id", id);
			response.sendRedirect("../servlet/SendRedirectNewsEditServlet");
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
