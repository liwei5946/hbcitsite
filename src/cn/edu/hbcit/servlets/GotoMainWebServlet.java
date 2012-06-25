package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.edu.hbcit.pojo.Admin;
import cn.edu.hbcit.pojo.NewsMB;

import cn.edu.hbcit.services.LmService;
import cn.edu.hbcit.services.NewsMBService;
import cn.edu.hbcit.services.UserService;

public class GotoMainWebServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserService userService=null;
	/**
	 * Constructor of the object.
	 */
	public GotoMainWebServlet() {
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
		String name=request.getParameter("user");
		String pws=request.getParameter("pass");
		
		Admin admin=userService.selectUsersByNameAndPws(name, pws);
		
		if(admin!=null)
		{
			NewsMBService newsMBService=new NewsMBService();
			//
			List<NewsMB> newsMBList=newsMBService.selectAllMB(1);
			request.getSession(true).setAttribute("newsMBList", newsMBList);
			//
			List<Admin> adminlist=new ArrayList<Admin>();
			adminlist.add(admin);
			request.getSession(true).setAttribute("admin", adminlist);
			request.getSession(true).setAttribute("userName", name);
			if(name.trim().equals("admin"))  //admin 获取所有权限
			{
				LmService lmService=new LmService();
				request.getSession(true).setAttribute("currentUserright", lmService.selectAllLmIdForAdmin());
			}
			else//非admin 按实际权限
				request.getSession(true).setAttribute("currentUserright", admin.getLmid());
			
			response.sendRedirect("/hbcitsite/admin/admin_index.jsp");

		}
		else
		{
			
			PrintWriter out=response.getWriter();
			out.print("javascript:alert('无此用户(用户名或口令错误)!!');");
			out.flush();
			out.close();
			
		}

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

}
