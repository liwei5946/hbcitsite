package cn.edu.hbcit.servlets;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.pojo.Admin;
import cn.edu.hbcit.services.DynamicSetLmService;
import cn.edu.hbcit.services.NewsService;
import cn.edu.hbcit.services.UserService;

public class AdminNewsListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Logger log = Logger.getLogger(AdminNewsListServlet.class.getName());
	/**
	 * Constructor of the object.
	 */
	public AdminNewsListServlet() {
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

/*		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();*/
		this.doPost(request, response);
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
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//NewsDAO db = new NewsDAO();
		response.setContentType("text/html");
		request.setCharacterEncoding("GB2312");
		String right = "";
		HttpSession session = request.getSession();
		@SuppressWarnings("rawtypes")
		ArrayList list = new ArrayList();
		
		List<Admin> adminlist=new ArrayList<Admin>();
		adminlist = (List<Admin>) session.getAttribute("admin");
		Admin admin = (Admin) adminlist.get(0);
		right = admin.getDj();//获取当前用户的等级（1-3）
		//获取所有查询参数  syj 2011-11-10  &title=null&content=null&contentType=null&Laiyuan=null&stime=null&etime=null
		//****************     一下   ******************************
		String cPage = request.getParameter("cpage");
		//
		String title="";
		if(request.getParameter("title")!=null)
			title = new String(request.getParameter("title").getBytes("iso-8859-1"),"utf-8");
		//
		String content = "";
		if(request.getParameter("content")!=null)
			content = new String(request.getParameter("content").getBytes("iso-8859-1"),"utf-8");
		//
		String contentType="";
		if(request.getParameter("contentType")!=null )
			if(request.getParameter("contentType")!="0")
				contentType = new String(request.getParameter("contentType").getBytes("iso-8859-1"),"utf-8");
		if(contentType.trim().equals("0") )
			contentType="";
		//&=yes
		String Laiyuan ="";
		if(request.getParameter("Laiyuan")!=null )
			if(request.getParameter("Laiyuan")!="0")
				Laiyuan = new String(request.getParameter("Laiyuan").getBytes("iso-8859-1"),"utf-8");
		if(Laiyuan.trim().equals("0") )
			Laiyuan="";
		//
		String stime ="";
		if(request.getParameter("stime")!=null)	
			stime = request.getParameter("stime");
		//
		String etime ="";
		if(request.getParameter("etime")!=null)	
			etime = request.getParameter("etime");
		//
		String clearSession ="";
		if(request.getParameter("clearSession")!=null )
			clearSession =request.getParameter("clearSession").trim();
		//
		if(title.trim().length()==0 && clearSession.equals("yes"))
		{
			// 判断session里存不存在传过来的id   
	        if (session.getAttribute("title") != null) {   
	        	session.removeAttribute("title") ; 
	        } 
		}else
		{
			if(title.trim().length()!=0)
			{
				session.setAttribute("title",title);
			}
			//
			if(session.getAttribute("title") == null)
				title="";
			
			if(session.getAttribute("title") != null)
				title=(String)session.getAttribute("title");

		}
		//
		if(content.trim().length()==0 && clearSession.equals("yes"))
		{
			// 判断session里存不存在传过来的id   
	        if (session.getAttribute("content") != null) {   
	        	session.removeAttribute("content") ; 
	        } 
		}else
		{
			if(content.trim().length()!=0)
			{
				session.setAttribute("content",content);
			}
			//
			if(session.getAttribute("content") == null)
				content="";
			
			if(session.getAttribute("content") != null)
				content=(String)session.getAttribute("content");
		}
		//
		if(contentType.trim().length()==0 && clearSession.equals("yes"))
		{
			// 判断session里存不存在传过来的id   
	        if (session.getAttribute("contentType") != null) {   
	        	session.removeAttribute("contentType") ; 
	        } 
		}else
		{
			if(contentType.trim().length()!=0)
			{
				session.setAttribute("contentType",contentType);
			}
			//
			if(session.getAttribute("contentType") == null)
				contentType="";
			
			if(session.getAttribute("contentType") != null)
				contentType=(String)session.getAttribute("contentType");
		}
		//
		if(Laiyuan.trim().length()==0 && clearSession.equals("yes"))
		{
			// 判断session里存不存在传过来的id   
	        if (session.getAttribute("Laiyuan") != null) {   
	        	session.removeAttribute("Laiyuan") ; 
	        } 
		}else
		{
			if(Laiyuan.trim().length()!=0)
			{
				session.setAttribute("Laiyuan",Laiyuan);
			}
			//
			if(session.getAttribute("Laiyuan") == null)
				Laiyuan="";
			
			if(session.getAttribute("Laiyuan") != null)
				Laiyuan=(String)session.getAttribute("Laiyuan");
		}
		//
		if(stime.trim().length()==0 && clearSession.equals("yes"))
		{
			// 判断session里存不存在传过来的id   
	        if (session.getAttribute("stime") != null) {   
	        	session.removeAttribute("stime") ; 
	        } 
		}else
		{
			if(stime.trim().length()!=0)
			{
				session.setAttribute("stime",stime);
			}
			//
			if(session.getAttribute("stime") == null)
				stime="";
			
			if(session.getAttribute("stime") != null)
				stime=(String)session.getAttribute("stime");
		}
		//
		if(etime.trim().length()==0 && clearSession.equals("yes"))
		{
			// 判断session里存不存在传过来的id   
	        if (session.getAttribute("etime") != null) {   
	        	session.removeAttribute("etime") ; 
	        } 
		}else
		{
			if(etime.trim().length()!=0)
			{
				session.setAttribute("etime",etime);
			}
			//
			if(session.getAttribute("etime") == null)
				etime="";
			
			if(session.getAttribute("etime") != null)
				etime=(String)session.getAttribute("etime");
		}
		//获取来源和栏目 syj 2011-11-10
		/*
		List<Admin> userList=userService.selectAllUsers();
		List<String> laiyuanList=new ArrayList<String>();
		for(int i=0;i<userList.size();i++)
		{
			Admin user=(Admin)userList.get(i);
			laiyuanList.add(user.getBumen().trim());
		}
		request.
		*/
		DynamicSetLmService dynamicSetLmService=new DynamicSetLmService();
		UserService userService=new UserService();

		String user=((String)request.getSession().getAttribute("userName")).trim();
		if(request.getSession().getAttribute("bumen")!=null)
			request.getSession().removeAttribute("bumen");
		request.getSession().setAttribute("bumen", userService.selectUsersBumen(user));
		//
		if(request.getSession().getAttribute("notes")!=null)
			request.getSession().removeAttribute("notes");
		request.getSession().setAttribute("notes", dynamicSetLmService.selectAllnotes());

		//*********************  以上  ********************************************************
		NewsService ns = new NewsService();
		
		String username = session.getAttribute("userName").toString();
		log.debug(username);

		if(username != null){
			list = ns.SelectAdminNewsList(username, cPage, "20", right,title,content,contentType,Laiyuan,stime,etime);//当前用户名、当前页码、每页记录数、用户等级
		}
		session.setAttribute("cpage", cPage);
		
		if(!list.isEmpty()){
			session.setAttribute("adminNewsList", list);
			response.sendRedirect("../admin/admin_news_list.jsp");
		}else//alter  syj 2011-11-17
		{
			response.sendRedirect("../admin/haveNotNews.html");
		}
		
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
