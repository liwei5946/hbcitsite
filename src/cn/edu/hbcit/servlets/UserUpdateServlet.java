package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.services.UserService;

public class UserUpdateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UserUpdateServlet() {
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


		request.setCharacterEncoding("utf-8");
		
		UserService userService=new UserService();
		//Admin admin=new Admin();
	
		PrintWriter out = response.getWriter();
		
		int updateCondition=Integer.parseInt(request.getParameter("Condition").trim());
		String name=null,oldpws=null,newpws=null,Permissions=null,level=null,dept=null;
		int id=0;
		switch (updateCondition)
		{
			case 0: //：更新系统一个用户的用户名(利用id)updateUserNameById(int id,String name)
				id=Integer.parseInt(request.getParameter("id").trim());
				name=request.getParameter("name").trim();
				if(userService.updateUserNameById(id, name)) 
					out.println("success");
				else
					out.println("error");
		       break; 
			case 2: //更新系统一个用户的口令(利用用户名)
				name=request.getParameter("name").trim();
				oldpws=request.getParameter("oldpws").trim();
				if(userService.updatePwsByName(name, oldpws)) 
					out.println("success");
				else
					out.println("error");
		       break; 
			case 3: //更新系统一个用户的口令(利用id)
				id=Integer.parseInt(request.getParameter("id").trim());
				oldpws=request.getParameter("oldpws").trim();
				newpws=request.getParameter("newpws").trim();
				if(userService.updatePwsById(id, oldpws,newpws)) 
					out.println("success");
				else
					out.println("error");
		       break; 
			case 4: //更新系统一个用户的权限(byname)
				name=request.getParameter("name").trim();
				Permissions=request.getParameter("permissions").trim();
				if(userService.updatePermissionsByName(name, Permissions)) 
					out.println("success");
				else
					out.println("error");
		       break; 
			case 5: //更新系统一个用户的权限(byid)
				id=Integer.parseInt(request.getParameter("id").trim());
				Permissions=request.getParameter("permissions").trim();
				if(userService.updatePermissionsById(id, Permissions)) 
					out.println("success");
				else
					out.println("error");
		       break; 
			case 6: //更新系统一个用户的等级(byname)
				name=request.getParameter("name").trim();
				level=request.getParameter("level").trim();
				if(userService.updateLevelByName(name, level)) 
					out.println("success");
				else
					out.println("error");
		       break; 
			case 7: //更新系统一个用户的等级(byid)
				id=Integer.parseInt(request.getParameter("id").trim());
				level=request.getParameter("level").trim();
				if(userService.updateLevelById(id, level)) 
					out.println("success");
				else
					out.println("error");
		       break; 
			case 8: //更新系统一个用户的部门(byname)
				id=Integer.parseInt(request.getParameter("id").trim());
				String user=new String(request.getParameter("user").trim().getBytes("iso-8859-1"),"gbk");
				String dj=request.getParameter("dj").trim();
				String username=new String(request.getParameter("username").trim().getBytes("iso-8859-1"),"gbk");
				String bumen=new String(request.getParameter("bumen").trim().getBytes("iso-8859-1"),"gbk");
				if(dj.equals("1"))
					dj="2";
				else if(dj.equals("2"))
					dj="3";
				if(userService.updateInfoById(id, user, bumen, dj, username)) 
					out.println("success");
				else
					out.println("error");
		       break; 
			case 9: //更新系统一个用户的等级(byid)
				id=Integer.parseInt(request.getParameter("id").trim());
				dept=request.getParameter("dept").trim();
				if(userService.updateDeptById(id, dept)) 
					out.println("success");
				else
					out.println("error");
		       break; 
			
		
		
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
