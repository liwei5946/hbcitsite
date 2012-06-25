package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.hbcit.services.UserService;
import cn.edu.hbcit.util.PasswordEncodeBean;


public class UserAddServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Logger log = Logger.getLogger(UserAddServlet.class.getName());

	/**
	 * Constructor of the object.
	 */
	public UserAddServlet() {
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
	@SuppressWarnings("static-access")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PasswordEncodeBean passwordEncodeBean=new PasswordEncodeBean();
		//
		response.setContentType("text/html");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		//
		String name=new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
		String pws=new String(request.getParameter("password").getBytes("iso-8859-1"),"utf-8");
		String bumen=new String(request.getParameter("bumen").getBytes("iso-8859-1"),"utf-8");
		String xingming=new String(request.getParameter("xingming").getBytes("iso-8859-1"),"utf-8");
		String type=request.getParameter("manager");
		
		//
		if(pws.trim().length()==0 || pws==null)
			pws="000000";
		pws=passwordEncodeBean.MD5Encode(pws);
		//
		UserService userService=new UserService();
		//
		if(userService.addUser(name, pws,bumen,xingming,type)){
			log.warn("“"+bumen+"”部门的用户“"+name+"”被添加，类型为“"+type+"”。");
			out.println("success");
		}			
		else{
			out.println("error");
		}
			
		//
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

		doGet(request,response);
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
