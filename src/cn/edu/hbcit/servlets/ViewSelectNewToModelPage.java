package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import cn.edu.hbcit.pojo.News;
import cn.edu.hbcit.pojo.NewsMB;
import cn.edu.hbcit.services.DynamicSetLmService;
import cn.edu.hbcit.services.NewsService;

public class ViewSelectNewToModelPage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NewsService newsService=new NewsService();
	DynamicSetLmService dynamicSetLmService=new DynamicSetLmService();
	/**
	 * ���ܣ���ʾ����Ŀ�����ݼ���ʾ����Ŀ�����ࣩ
	 */
	public ViewSelectNewToModelPage() {
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
		PrintWriter out = response.getWriter();
		
		News news=new News();
		NewsMB newsMB=null;
		////get content
		String condition=request.getParameter("condition").trim();//=1��ʾѡ�е�ĳһ���ŵ���ϸ��=2��ʾ��������
		if(condition.equals("1"))//=1��ʾѡ�е�ĳһ���ŵ���ϸ��
		{
			int id=Integer.parseInt(request.getParameter("newsid"));
			@SuppressWarnings("unchecked")
			List<News> newslist=newsService.newsLocate(id);
			//
			Iterator<News> it=newslist.iterator();
			if(it.hasNext())
				news=(News)it.next();
			//String newscontent=news.getContent();//get content
			String lm=news.getLm();
			String lm2=news.getLm2();
			String lm3=news.getLm3();
			//
			newsMB=newsService.getMbContentToView(lm, lm2, lm3);
			String mbcontent=newsMB.getMid();
			String title=news.getTitle().trim();
			mbcontent=mbcontent.replace("$title$", title);
			mbcontent=mbcontent.replace("$time$", news.getTime().trim());
			mbcontent=mbcontent.replace("$laiyuan$", news.getLaiyuan().trim());
			mbcontent=mbcontent.replace("$zz$", news.getZz().trim());
			mbcontent=mbcontent.replace("$hit$", String.valueOf(news.getHit()).trim());
			mbcontent=mbcontent.replace("$content$", news.getContent().trim());
			//�����ܷ���session
			//����������
			
			out.println(mbcontent);
		}
		//
		if(condition.equals("2"))//=2��ʾ��������
		{
			
			HttpSession session = request.getSession();
			int newsType=0;
			try
			{
				newsType=Integer.parseInt(request.getParameter("type"));
			}
			catch(Exception r){
				
				newsType=(Integer)session.getAttribute("newsType");
			}
			//Ϊ�˴ﵽ��̬ȡ��Ӧ���ŵ�Ч������type�����ű����dynamicsetlm��ȡ��1-7��������type=1ȡ����1��type=2ȡ����2......
			String lms=dynamicSetLmService.selectOneDynamicLmType(newsType);
			String cPage = request.getParameter("cpage");
			@SuppressWarnings("unchecked")
			List<News> newslist=newsService.selectMoreNewsList(cPage, "65", lms);//newslist�д洢����hashmap  65--ÿҳ����
			
			session.setAttribute("cpage", cPage);
			session.setAttribute("newsType", newsType);
			//
			String[] everylm=lms.split(",");
			//
			newsMB=newsService.getMbContentToView(everylm[0], everylm[1], everylm[2]);
			String mbcontent=newsMB.getListmb();
			String icon=newsMB.getIcon();
			
			
			String totalCount="",lastPage="",nextPage="",pageSize="",totalPage="",hit="";
			String hasNextPage="", hasLastPage="";
			String viewHtml="<table width='100%' style='font-size:12px;' class='stripe_tb'>";
			Iterator it=newslist.iterator();
			while(it.hasNext())
			{
				String id="",title="",time="",title1="";
				@SuppressWarnings("unchecked")
				HashMap<String,String> newsMap=(HashMap<String, String>)it.next();
				id=String.valueOf(newsMap.get("id"));
				time=(String)newsMap.get("time");
				time=time.substring(0, 10);
				title=(String)newsMap.get("title");
				totalCount=String.valueOf(newsMap.get("totalCount"));
				lastPage=String.valueOf(newsMap.get("lastPage"));
				nextPage=String.valueOf(newsMap.get("nextPage"));
				pageSize=String.valueOf(newsMap.get("pageSize"));
				totalPage=String.valueOf(newsMap.get("totalPage"));
				hasNextPage=String.valueOf(newsMap.get("hasNextPage"));
				hasLastPage=String.valueOf(newsMap.get("hasLastPage"));
				hit=String.valueOf(newsMap.get("hit"));
				
				title1=title;
				int len=title.trim().length();
				//System.out.println(len);
				if(len>41)
					title=title.trim().substring(0, 40)+".....";
				
				viewHtml=viewHtml+"<tr><td>"+icon+"</td><td><a href='/hbcitsite/servlet/ViewSelectNewToModelPage?newsid="+id+"&condition=1' title='"+title1.trim()+"'>";
				viewHtml=viewHtml+title+"</a>    <span style='color:#C0C0C0;'> &nbsp;&nbsp;  (�Ķ�:"+hit+")</span></td><td align='right'>("+time+")</td></tr>";
				
			}
			viewHtml=viewHtml+"</table>";
			
			//************************************************************************
			viewHtml=viewHtml+"<br><br><div align='center'>��  " +totalCount+" ��&nbsp;&nbsp;ÿҳ��ʾ  "+pageSize+" ��&nbsp;&nbsp;��  "+(String)session.getAttribute("cpage")+" ҳ&nbsp;&nbsp;";
			viewHtml=viewHtml+"�� "+totalPage+" ҳ	&nbsp;&nbsp;&nbsp;&nbsp;";
			if(hasLastPage.trim().equals("true"))
				viewHtml=viewHtml+"<a href='../servlet/ViewSelectNewToModelPage?cpage=1&condition=2'>��ҳ</a>&nbsp;<a href='../servlet/ViewSelectNewToModelPage?cpage="+lastPage+"&condition=2'> ��һҳ </a>&nbsp;";
			else
				viewHtml=viewHtml+"<span style='padding:20px;overflow:hidden;word-wrap:break-word;word-break:break:all; font-size:12px; '><font disabled>��ҳ&nbsp;&nbsp;��һҳ</font> </span>"; 
			if(hasNextPage.trim().equals("true"))
			{
				viewHtml=viewHtml+"<a href='../servlet/ViewSelectNewToModelPage?cpage="+nextPage+"&condition=2'> ��һҳ </a>&nbsp;";
				viewHtml=viewHtml+"<a href='../servlet/ViewSelectNewToModelPage?cpage="+totalPage+"&condition=2'> βҳ </a></div>";
			}
			else
				viewHtml=viewHtml+" <span style='padding:20px;overflow:hidden;word-wrap:break-word;word-break:break:all; font-size:12px; line-height:18px;'><font disabled>��һҳ &nbsp;&nbsp;βҳ</font></span> ";
			
			
			
			//*********************************************************************
			mbcontent=mbcontent.replace("$$list$$", viewHtml);
			
			
			//�����ܷ���session
			
			out.println(mbcontent);
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
