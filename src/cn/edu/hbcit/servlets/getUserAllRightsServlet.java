package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import cn.edu.hbcit.pojo.Admin;
import cn.edu.hbcit.services.UserService;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.services.LmService;
public class getUserAllRightsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LmService  lmDAOservice=new LmService();
	UserService userService=new UserService();
	/**
	 * Constructor of the object.
	 */
	public getUserAllRightsServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts 'destroy' string in log
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
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		//get transparam usrerid for update user right
		String rightUserId=request.getParameter("userid");
		HttpSession session =request.getSession();
		if(session.getAttribute("rightUserId")!=null)
			session.removeAttribute("rightUserId");
		session.setAttribute("rightUserId", rightUserId);
		//get curuser right
		Admin admin=userService.selectUsersById(Integer.parseInt(rightUserId));
		String curUserRights=admin.getLmid();//得到当前用户的权限(全是逗号分隔的id号)
		//将id号还原成栏目名称
		
		String[] lmId=null;
		if(curUserRights==null)
			curUserRights="";
		if(curUserRights.trim().length()!=0)
		{
			lmId=curUserRights.split(",");
			curUserRights="";
		}
		//*********************************************************************************************
		//三级栏目的时候，以下代码检索有问题
		if(lmId!=null)	
		{
			
		
			curUserRights="";//将全部的id转换成栏目名称
			for(int i=0;i<lmId.length;i++)
			{
				if(i==0)
					curUserRights=lmDAOservice.selectLmNameById(Integer.parseInt(lmId[i]));
				else
					curUserRights=curUserRights+","+lmDAOservice.selectLmNameById(Integer.parseInt(lmId[i]));
			}
		}
		//三级栏目的时候，以上代码检索有问题
		//*********************************************************************************************
		
		//
		PrintWriter out = response.getWriter();
		StringBuffer s = new StringBuffer();
		//s="{\"result\":\"true\", \"msg\":\"删除成功\"}";
		Map<Integer, String> pMap=lmDAOservice.selectAllParentLmNameAndKey();
		Iterator<Integer> itM=pMap.keySet().iterator();
		if(!pMap.isEmpty())
			s.append("[");
		while(itM.hasNext())
		{
			String pName=pMap.get(itM.next());
			if(curUserRights!=null && curUserRights.contains(pName))
				s.append("{\"name\":\"").append(pName).append("\",\"ename\":\"").append(pName+"1").append("\",\"checked\":\"true\"},");//权限中有该栏目，在check
			else
				s.append("{\"name\":\"").append(pName).append("\",\"ename\":\"").append(pName+"1").append("\"},");//权限中没有该栏目，则无check
			//
			List<String> s2List=lmDAOservice.selectAllSubLmNameForPLm(pName,1);	
			Iterator<String> itL=s2List.iterator();
			if(!s2List.isEmpty())
			{
				s.deleteCharAt(s.length() - 2);
				s.append("\"open\":\"true\",\"nodes\":[");
				//s.deleteCharAt(s.length() - 2);
				//s.append("\"nodes\":[");
			}
			while(itL.hasNext())
			{
				String nextLm=(String)itL.next();
				if(curUserRights!=null && curUserRights.contains(nextLm))
					s.append("{\"name\":\"").append(nextLm).append("\",\"ename\":\"").append(nextLm+"2").append("\",\"nocheck\":\"false\",\"checked\":\"true\"},");
				else
					s.append("{\"name\":\"").append(nextLm).append("\",\"ename\":\"").append(nextLm+"2").append("\",\"nocheck\":\"false\"},");
				//
				List<String> s3List=lmDAOservice.selectAllSubLmNameForPLm(nextLm,2);	
				Iterator<String> it2L=s3List.iterator();
				if(!s3List.isEmpty())
				{
					s.deleteCharAt(s.length() - 2);
					s.append("\"open\":\"true\",\"nodes\":[");
					//s.deleteCharAt(s.length() - 2);
					//s.append("\"nodes\":[");
				}
				while(it2L.hasNext())
				{
					
					String next3Lm=(String)it2L.next();
					if(curUserRights!=null && curUserRights.contains(next3Lm))
						s.append("{\"name\":\"").append(next3Lm).append("\",\"ename\":\"").append(next3Lm+"3").append("\",\"nocheck\":\"false\",\"checked\":\"true\"},");
					else
						s.append("{\"name\":\"").append(next3Lm).append("\",\"ename\":\"").append(next3Lm+"3").append("\",\"nocheck\":\"false\"},");
				}
				//避免多加】}
				if(!s3List.isEmpty())
				{
					s.deleteCharAt(s.length() - 1);
					s.append("]},");
				}
			}
			//避免多加】}
			if(!s2List.isEmpty())
			{
				s.deleteCharAt(s.length() - 1);
				s.append("]},");
			}
			
		}
		s.deleteCharAt(s.length() - 1);
		s.append("]");
		
		out.print(s.toString());
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
