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

import cn.edu.hbcit.pojo.Admin;

import cn.edu.hbcit.services.UserService;
import cn.edu.hbcit.services.LmService;

public class updateUserRightServlet extends HttpServlet {
	UserService userService=new UserService();
	LmService lmService=new LmService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public updateUserRightServlet() {
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

		String curRights=new String(request.getParameter("nodes").getBytes("iso8859-1"),"gb2312");
		String[] rights=null;//还原编号
		if(curRights.length()!=0)
		{
			rights=curRights.split(",");
		}
		
		String lmBianHao="";
		//先取出rights各个元素的右边的第一个字符（栏目名称+级别）
		if(rights!=null)
			for(int i=0;i<rights.length;i++)
			{
				int lmLevel=Integer.parseInt(rights[i].substring(rights[i].length()-1));
				String lmName=rights[i].substring(0,rights[i].length()-1);
				if(i==0)
					lmBianHao=String.valueOf(lmService.getLmId(lmName, lmLevel, true));
				else
					lmBianHao=lmBianHao+","+String.valueOf(lmService.getLmId(lmName, lmLevel, true));
			}
		curRights=lmBianHao;
		//将其写入数据库
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		int id=Integer.parseInt((String)session.getAttribute("rightUserId"));
		String valu="";
		try
		{
			userService.updatePermissionsById(id, curRights);
			valu="success";
		}catch(Exception e)
		{
			valu="error";
		}
		
		
		
		List<Admin> adminList=new ArrayList<Admin>();
		adminList=userService.selectAllUsers();
		//HttpSession session=request.getSession();
		request.setAttribute("adminList", adminList);
		//out.println(adminList);
		request.getRequestDispatcher("/admin/admin_admin.jsp").forward(request, response);
		
		out.print(valu);
		out.flush();
		out.close();
		//request.getRequestDispatcher("${pageContext.request.contextPath }/admin/admin_admin.jsp").forward(request, response);
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

		doGet(request, response);
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
