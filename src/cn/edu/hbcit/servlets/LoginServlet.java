package cn.edu.hbcit.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.pojo.Admin;
import cn.edu.hbcit.services.UserService;

public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Logger log = Logger.getLogger(LoginServlet.class.getName());
	UserService userService=null;
	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
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
		
		PrintWriter out =response.getWriter();
		String type=request.getParameter("type");
		if(type.trim().equals("1"))
		{
	        HttpSession session = request.getSession(); 
	        String valCode=(String)session.getAttribute("valCode");  
	        log.debug("验证码："+valCode);
	        out.println(valCode);
	        out.flush();
	        out.close();
		}else//load user
		{
			String name=request.getParameter("user");
			String pws=request.getParameter("pass");
			Admin admin=userService.selectUsersByNameAndPws(name, pws);
			String ip = this.getIpAddr(request);//获取用户IP
			if(admin==null)
			{
				out.print("failure");
		        out.flush();
		        out.close();
			}
			else
			{
				log.warn("用户"+name+"登录成功，登录IP地址为："+ip+"。");
				out.print("success");
		        out.flush();
		        out.close();
			}
		}
		//
		
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
		userService=new UserService();
	}
	
	/**
	 * 获得用户真实IP地址
	 * 李玮  2011-10-31
	 * @param request
	 * @return String IP
	 */
	public String getIpAddr(HttpServletRequest request) { 
		String ip = request.getHeader("x-forwarded-for"); 
	    //String ip = request.getRemoteAddr(); 
	    log.debug(ip);
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	}

}
