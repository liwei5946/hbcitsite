package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.hbcit.services.NewsService;
import cn.edu.hbcit.util.UtilTools;

public class NewsOntopServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Logger log = Logger.getLogger(NewsOntopServlet.class.getName());

	/**
	 * Constructor of the object.
	 */
	public NewsOntopServlet() {
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
		NewsService ns = new NewsService();
		UtilTools ut = new UtilTools();
		boolean flag = false;
		String id = "";
		
		id = request.getParameter("id");
		log.debug("÷√∂•£∫"+id);
		if(id!=null && ut.isNumeric(id)){
			flag = ns.ontopNews(Integer.parseInt(id));
		}
		log.debug("÷√∂•≥…π¶£ø£∫"+flag);
		if(flag){
			log.warn("µ⁄"+id+"∫≈Œƒ’¬±ª÷√∂•°£");
			out.print("success");
		}else{
			log.warn("µ⁄"+id+"∫≈Œƒ’¬÷√∂• ß∞‹°£");
			out.print("fail");
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
