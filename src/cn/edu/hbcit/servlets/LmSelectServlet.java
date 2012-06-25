package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.pojo.Lm;
import cn.edu.hbcit.services.LmService;

public class LmSelectServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public LmSelectServlet() {
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
//
		LmService lmService=new LmService();
		List<Lm> lmList=null;//得到所有栏目的所有综合信息
		//
		String selectCondition=request.getParameter("Condition").trim();
		////0--select all lm ;
		if(selectCondition.equals("0"))
		{
			lmList=lmService.selectAllLm();//得到所有栏目的所有综合信息
		
			request.setAttribute("lmList", lmList);
			//
			request.getRequestDispatcher("/admin/admin_news_lm.jsp").forward(request, response);
		}
		//1--select by id
		if(selectCondition.equals("1"))//
		{
			PrintWriter out = response.getWriter();
			int id=Integer.parseInt(request.getParameter("id").trim());
			int lmLevel=Integer.parseInt(request.getParameter("lmLevel").trim());
			String lmName=lmService.selectLmNameById(id, lmLevel);
			StringBuffer s = new StringBuffer();
			s.append("{\"user\":[{\"id\":\"").append(String.valueOf(id)).append("\",\"user\":\"").append(lmName).append("\"}]}");
			out.println(s);
			out.flush();
			out.close();
			//request.getRequestDispatcher("/admin/admin_admin.jsp").forward(request, response);
		}
		//1--select by sublm for parentlmid
		if(selectCondition.equals("2"))//
		{
			StringBuffer s = new StringBuffer();
			s.append("{\"lm\":[");
			//
			PrintWriter out = response.getWriter();
			int id=Integer.parseInt(request.getParameter("id").trim());
			int lmLevel=Integer.parseInt(request.getParameter("lmLevel").trim());
			Map<Integer, String> subLm=lmService.selectAllSubLmNameAndKey(id, lmLevel);
			Set<Map.Entry<Integer, String>> set = subLm.entrySet();
			int ii=0;
			for (Iterator<Map.Entry<Integer, String>> it =set.iterator(); it.hasNext();) 
			{
				Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) it.next();
				if(ii==0)
					s.append("{\"id\":\"").append(entry.getKey()).append("\",\"lm\":\"").append(entry.getValue()).append("\"}");
				else
					s.append(",{\"id\":\"").append(entry.getKey()).append("\",\"lm\":\"").append(entry.getValue()).append("\"}");
				ii++;
				//System.out.println(entry.getKey() + "--->" + entry.getValue());
		     }

			s.append("]}");
			
			out.println(s);
			out.flush();
			out.close();
			
		}
		//out.flush();
		//out.close();
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
