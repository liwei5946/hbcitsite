package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.hbcit.services.UserService;

public class UserDeleteServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Logger log = Logger.getLogger(UserDeleteServlet.class.getName());

	/**
	 * Constructor of the object.
	 */
	public UserDeleteServlet() {
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
		UserService userService=new UserService();
		response.setContentType("text/html");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		PrintWriter out = response.getWriter();
		//
		String deleteCondition=request.getParameter("Condition").trim();
		//0--deleteall;
		if(deleteCondition.equals("0"))
		{
			if(userService.deleteAllUser()){
				log.warn("清空所有用户操作成功。");
				out.println("success");
			}
				
			else
				out.println("error");
		}
		//1--deletebyid
		if(deleteCondition.equals("1"))
		{
			String id=request.getParameter("id").trim();
		
			if(userService.deleteUserById(Integer.parseInt(id)))
				{
					log.warn("删除用户"+id+"操作成功。");
					out.println("success");
				}
			else
				out.println("error");
		}
		//2--deletebyname
		if(deleteCondition.equals("2"))
		{
			String name=request.getParameter("name").trim();
			if(userService.deleteUserByName(name)){
				log.warn("删除用户"+name+"操作成功。");
				out.println("success");
			}				
			else
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
