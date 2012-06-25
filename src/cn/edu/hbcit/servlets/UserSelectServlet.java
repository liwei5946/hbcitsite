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
import cn.edu.hbcit.services.UserService;
import cn.edu.hbcit.services.DynamicSetLmService;

public class UserSelectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UserSelectServlet() {
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
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");


		request.setCharacterEncoding("utf-8");
		//
		
		//
		DynamicSetLmService dynamicSetLmService=new DynamicSetLmService();
		UserService userService=new UserService();
		Admin admin=new Admin();
		//
		List<Admin> adminList=new ArrayList<Admin>();
		String selectCondition=request.getParameter("Condition").trim();
		//0--selectall;
		if(selectCondition.equals("0"))
		{
			adminList=userService.selectAllUsers();
			//HttpSession session=request.getSession();
			request.setAttribute("adminList", adminList);
			//out.println(adminList);
			request.getRequestDispatcher("/admin/admin_admin.jsp").forward(request, response);
		}
		//1--selectbyid
		if(selectCondition.equals("1"))//
		{
			PrintWriter out = response.getWriter();
			String id=request.getParameter("id").trim();
			admin=userService.selectUsersById(Integer.parseInt(id));
			String username=admin.getUsername();
			if(username==null)
				username="";
			StringBuffer s = new StringBuffer();
			s.append("{\"user\":[{\"id\":\"").append(admin.getId()).append("\",\"user\":\"").append(admin.getUser()).append("\",\"bumen\":\"").append(admin.getBumen()).append("\",\"username\":\"").append(username).append("\",\"dj\":\"").append(admin.getDj()).append("\"}]}");
			out.println(s);
			out.flush();
			out.close();
			//request.getRequestDispatcher("/admin/admin_admin.jsp").forward(request, response);
		}
		//2--selectbyname
		if(selectCondition.equals("2"))
		{
			String name=request.getParameter("name").trim();
			adminList=userService.selectUsersByName(name);
			//out.println(adminList);
			request.getRequestDispatcher("/admin/admin_admin.jsp").forward(request, response);
		}
		//3--selectall bumen
		if(selectCondition.equals("3"))
		{
			
			String user=((String)request.getSession().getAttribute("userName")).trim();
			request.getSession().setAttribute("bumen", userService.selectUsersBumen(user));
			//
			request.getSession().setAttribute("notes", dynamicSetLmService.selectAllnotes());

		}
		//3--selectUserPermissions
		
		//
		//out.flush();
		//out.close();
		//request.getRequestDispatcher("/admin/admin_admin.jsp").forward(request, response);
		//reqeust.getRequestDispatcher("/hbcitsite/admin/admin_admin.jsp").forward(request, response);


		//response.sendRedirect("/hbcitsite/admin/admin_admin.jsp");
		
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
