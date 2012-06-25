package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.pojo.DynamicSetLm;
import cn.edu.hbcit.pojo.Lm;
import cn.edu.hbcit.services.DynamicSetLmService;
import cn.edu.hbcit.services.LmService;

public class IndexBanKuaiSetServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	DynamicSetLmService dynamicSetLmService=new DynamicSetLmService();
	LmService lmService=new LmService();
	/**
	 * Constructor of the object.
	 */
	public IndexBanKuaiSetServlet() {
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
		
		//
		String conditions=request.getParameter("conditions");
		if(conditions.trim().equals("1"))//检索所有版块设置
		{
			List<DynamicSetLm> dylist=dynamicSetLmService.selectAllDynamicSetLm();
			List<Lm> lmlist=lmService.selectAllLm();
			//
			
			request.setAttribute("lmlist", lmlist);
			request.setAttribute("dylist", dylist);
			request.getRequestDispatcher("/admin/admin_news_lmViewSet.jsp").forward(request, response);
		}
		//
		if(conditions.trim().equals("2"))//保存所有版块设置
		{
			int id=Integer.parseInt(request.getParameter("id"));
			String indexLmNo=request.getParameter("indexLmNo");
			String lm=request.getParameter("lm");
			String lm2=request.getParameter("lm2");
			String lm3=request.getParameter("lm3");
			String indexLmPic=request.getParameter("indexLmPic");
			String notes=new String(request.getParameter("notes").getBytes("iso-8859-1"),"utf-8");
			//
			if(dynamicSetLmService.updateDynamicSetLm(id, indexLmNo, lm, lm2, lm3, indexLmPic, "", "", notes))
			{
				PrintWriter out = response.getWriter();
				out.print("success");
				out.flush();
				out.close();
			}
			
		}
		//
		if(conditions.trim().equals("3"))//删除某一版块设置
		{
			int id=Integer.parseInt(request.getParameter("id"));
			String indexLmNo=request.getParameter("indexLmNo");
			String lm=request.getParameter("lm");
			String lm2=request.getParameter("lm2");
			String lm3=request.getParameter("lm3");
			String indexLmPic=request.getParameter("indexLmPic");
			String notes=request.getParameter("notes");
			//
			if(dynamicSetLmService.updateDynamicSetLm(id, indexLmNo, lm, lm2, lm3, indexLmPic, "", "", notes))
			{
				PrintWriter out = response.getWriter();
				out.print("success");
				out.flush();
				out.close();
			}
			
		}
		//
		if(conditions.trim().equals("4"))//检索某一栏目在版块设置中存在与否
		{
			LmService ls = new LmService();
			String mylm = ls.getSelectLmId(new String(request.getParameter("lm").getBytes("iso-8859-1"),"gbk"));//lm传过来选择的栏目名
			String[] a=mylm.split(",");
			PrintWriter out = response.getWriter();
			//
			if(dynamicSetLmService.DynamicContinesLm(a[0], a[1], a[2]))
			{
				out.print("success");
			}else
				out.print("error");
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

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		
		//
		String conditions=request.getParameter("conditions");
		if(conditions.trim().equals("1"))//检索所有版块设置
		{
			List<DynamicSetLm> dylist=dynamicSetLmService.selectAllDynamicSetLm();
			List<Lm> lmlist=lmService.selectAllLm();
			//
			
			request.setAttribute("lmlist", lmlist);
			request.setAttribute("dylist", dylist);
			request.getRequestDispatcher("/admin/admin_news_lmViewSet.jsp").forward(request, response);
		}
		if(conditions.trim().equals("2"))//保存所有版块设置
		{
			int id=Integer.parseInt(request.getParameter("id"));
			String indexLmNo=request.getParameter("indexLmNo");
			String lm=request.getParameter("lm");
			String lm2=request.getParameter("lm2");
			String lm3=request.getParameter("lm3");
			String indexLmPic=request.getParameter("indexLmPic");
			String notes=new String(request.getParameter("notes").getBytes("iso-8859-1"),"utf-8");
			//
			if(dynamicSetLmService.updateDynamicSetLm(id, indexLmNo, lm, lm2, lm3, indexLmPic, "", "", notes))
			{
				PrintWriter out = response.getWriter();
				out.print("success");
				out.flush();
				out.close();
			}
			
		}
		//
		if(conditions.trim().equals("3"))//添加一个版块
		{
			
			//
			if(dynamicSetLmService.addDynamicSetLm("", "","","", "","","",""))
			{
				PrintWriter out = response.getWriter();
				out.print("success");
				out.flush();
				out.close();
			}else
				
			{
				PrintWriter out = response.getWriter();
				out.print("error");
				out.flush();
				out.close();
			}
			
		}
		//
		if(conditions.trim().equals("4"))//删除一个版块
		{
			int id=Integer.parseInt(request.getParameter("id"));
			String indexLmNo=request.getParameter("indexLmNo");
			//
			if(dynamicSetLmService.deleteOneDynamicLm(id, indexLmNo))
			{
				PrintWriter out = response.getWriter();
				out.print("success");
				out.flush();
				out.close();
			}else
				
			{
				PrintWriter out = response.getWriter();
				out.print("error");
				out.flush();
				out.close();
			}
			
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
