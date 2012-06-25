package cn.edu.hbcit.servlets;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.pojo.DynamicSetLm;
import cn.edu.hbcit.pojo.News;
import cn.edu.hbcit.services.DynamicSetLmService;

import cn.edu.hbcit.services.NewsService;

public class GotoMainIndexServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DynamicSetLmService dynamicSetLmService=new DynamicSetLmService();
	NewsService newsService=new NewsService();
	DynamicSetLm dynamicSetLm=null;
	//get lm new MB
	//NewsMBService newsMBService =new NewsMBService();
	/**
	 * Constructor of the object.
	 */
	public GotoMainIndexServlet() {
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

		// 设置响应内容的类型 
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		//
		String lmID="70";//调用lmid,便于获取图标
		/*
		 * 功能：返回首页校园快讯的内容
		 * 编写：shiyanjie   2011-9-28
		 * 修改：
		 */
		//one index 校园快讯
		dynamicSetLm=dynamicSetLmService.selectOneDynamicSetLm(1);
		//get lm id to get lm mb
		if(!dynamicSetLm.getLm3().trim().equals("0"))
			lmID=dynamicSetLm.getLm3().trim();
		else
			if(!dynamicSetLm.getLm2().trim().equals("0"))
				lmID=dynamicSetLm.getLm2().trim();
			else	
				lmID=dynamicSetLm.getLm().trim();
		List<News> news1List=newsService.getNewsToMainIndex(dynamicSetLm.getLm(), dynamicSetLm.getLm2(), dynamicSetLm.getLm3(), 14,lmID,1);
		request.setAttribute("news1List", news1List);
		request.setAttribute("news1TopPic", "/hbcitsite/images/"+dynamicSetLm.getIndexLmPic());
		//two index 通知公告
		dynamicSetLm=dynamicSetLmService.selectOneDynamicSetLm(2);
		//get lm id to get lm mb
		if(!dynamicSetLm.getLm3().trim().equals("0"))
			lmID=dynamicSetLm.getLm3().trim();
		else
			if(!dynamicSetLm.getLm2().trim().equals("0"))
				lmID=dynamicSetLm.getLm2().trim();
			else	
				lmID=dynamicSetLm.getLm().trim();
		List<News> news2List=newsService.getNewsToMainIndex(dynamicSetLm.getLm(), dynamicSetLm.getLm2(), dynamicSetLm.getLm3(), 6,lmID,2);
		request.setAttribute("news2List", news2List);
		request.setAttribute("news2TopPic", "/hbcitsite/images/"+dynamicSetLm.getIndexLmPic());
		
		//three index 工院讲坛
		dynamicSetLm=dynamicSetLmService.selectOneDynamicSetLm(3);
		//get lm id to get lm mb
		if(!dynamicSetLm.getLm3().trim().equals("0"))
			lmID=dynamicSetLm.getLm3().trim();
		else
			if(!dynamicSetLm.getLm2().trim().equals("0"))
				lmID=dynamicSetLm.getLm2().trim();
			else	
				lmID=dynamicSetLm.getLm().trim();
		List<News> news3List=newsService.getNewsToMainIndex(dynamicSetLm.getLm(), dynamicSetLm.getLm2(), dynamicSetLm.getLm3(), 7,lmID,3);
		request.setAttribute("news3List", news3List);
		request.setAttribute("news3TopPic", "/hbcitsite/images/"+dynamicSetLm.getIndexLmPic());

		//four index 职教资讯
		dynamicSetLm=dynamicSetLmService.selectOneDynamicSetLm(4);
		//get lm id to get lm mb
		if(!dynamicSetLm.getLm3().trim().equals("0"))
			lmID=dynamicSetLm.getLm3().trim();
		else
			if(!dynamicSetLm.getLm2().trim().equals("0"))
				lmID=dynamicSetLm.getLm2().trim();
			else	
				lmID=dynamicSetLm.getLm().trim();
		List<News> news4List=newsService.getNewsToMainIndex(dynamicSetLm.getLm(), dynamicSetLm.getLm2(), dynamicSetLm.getLm3(), 7,lmID,4);
		request.setAttribute("news4List", news4List);
		request.setAttribute("news4TopPic", "/hbcitsite/images/"+dynamicSetLm.getIndexLmPic());

		//five index 行业动态
		dynamicSetLm=dynamicSetLmService.selectOneDynamicSetLm(5);
		//get lm id to get lm mb
		if(!dynamicSetLm.getLm3().trim().equals("0"))
			lmID=dynamicSetLm.getLm3().trim();
		else
			if(!dynamicSetLm.getLm2().trim().equals("0"))
				lmID=dynamicSetLm.getLm2().trim();
			else	
				lmID=dynamicSetLm.getLm().trim();
		List<News> news5List=newsService.getNewsToMainIndex(dynamicSetLm.getLm(), dynamicSetLm.getLm2(), dynamicSetLm.getLm3(), 7,lmID,5);
		request.setAttribute("news5List", news5List);
		request.setAttribute("news5TopPic", "/hbcitsite/images/"+dynamicSetLm.getIndexLmPic());

		//six index 合作交流
		dynamicSetLm=dynamicSetLmService.selectOneDynamicSetLm(6);
		//get lm id to get lm mb
		if(!dynamicSetLm.getLm3().trim().equals("0"))
			lmID=dynamicSetLm.getLm3().trim();
		else
			if(!dynamicSetLm.getLm2().trim().equals("0"))
				lmID=dynamicSetLm.getLm2().trim();
			else	
				lmID=dynamicSetLm.getLm().trim();
		List<News> news6List=newsService.getNewsToMainIndex(dynamicSetLm.getLm(), dynamicSetLm.getLm2(), dynamicSetLm.getLm3(),7,lmID,6);
		request.setAttribute("news6List", news6List);
		request.setAttribute("news6TopPic", "/hbcitsite/images/"+dynamicSetLm.getIndexLmPic());

		//seven index 校刊校报
		dynamicSetLm=dynamicSetLmService.selectOneDynamicSetLm(7);
		//get lm id to get lm mb
		if(!dynamicSetLm.getLm3().trim().equals("0"))
			lmID=dynamicSetLm.getLm3().trim();
		else
			if(!dynamicSetLm.getLm2().trim().equals("0"))
				lmID=dynamicSetLm.getLm2().trim();
			else	
				lmID=dynamicSetLm.getLm().trim();
		List<News> news7List=newsService.getNewsToMainIndex(dynamicSetLm.getLm(), dynamicSetLm.getLm2(), dynamicSetLm.getLm3(), 7,lmID,7);
		request.setAttribute("news7List", news7List);
		request.setAttribute("news7TopPic", "/hbcitsite/images/"+dynamicSetLm.getIndexLmPic());

		/*
		 * 李玮
		 * 2011-10-18
		 * 加入新闻图片轮转
		 */
		List<News> imgList = new ArrayList<News>();
		NewsService ns = new NewsService();
		imgList = ns.getImageNews();
		request.setAttribute("imgList", imgList);
		//icon
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
		
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
