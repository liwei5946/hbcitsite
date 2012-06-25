package cn.edu.hbcit.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.services.LmService;
import cn.edu.hbcit.services.NewsService;


/**
 * 新闻添加. <br>
 * @author 李玮
 * @Data 2011.9.29
 */
public class NewsAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Logger log = Logger.getLogger(NewsAddServlet.class.getName());
	/**
	 * Constructor of the object.
	 */
	public NewsAddServlet() {
		super();
	}

	
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

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = request.getSession();

//		String realPathImg = "";	//轮转图片新闻路径
//		String nameImg = "";		//生成图片文件名
		
		String lm_in = ""; 				//内网栏目
		String lm2_in = ""; 				//内网栏目2
		String lm3_in = "";  				//内网栏目3
		String lm_out = ""; 				//外网栏目
		String lm2_out = ""; 				//外网栏目2
		String lm3_out = "";  				//外网栏目3
		String title = "";  			//标题
		String content = "";  			//正文
		String zz = "";    				//作者(真实姓名)
		String time = "";   			//添加时间
		String hit = "";   				//点击量
		String URL = "";				//跳转目标URL页面
		String titlecolor = "";			//标题颜色
		String pic = "";				//首页新闻轮转图片
		String tj = "";					//是否推荐
		String ontop = "";				//是否置顶
		String html = "";				//转换静态页面
		String adduser = "";			//作者(帐号)
		String xgnews = "";				//相关新闻
		String htitle = "";				//副标题
		String sh = "";					//是否已经审核
		String shUsername = "";			//审核员姓名
		String ztid = "";				//专题id
		String googlemap = "";			//
		String filename = "";			//
		String updat = "";				//文章顺序
		String piczz = "";				//
		String laiyuan = "";			//来源部门
		String newslmType = "";			//内外网类型
		
		boolean flag_in = false;
		boolean flag_out = false;
		/*SmartUpload su = new SmartUpload(); //实例化jspSmartUpload
		su.initialize(config, request, response);// 初始化SmartUpload
		su.setAllowedFilesList("gif,jpg,jpeg,png,doc,xls,ppt,pdf,txt,rar,zip,vsd"); //允许上传的文件类型(中间用,隔开)
		su.setMaxFileSize(100 * 1024 * 1024); //声明上传文件最大容量
		log.debug("正在上传...");*/
		
		try{
			LmService ls = new LmService();
			String outlm =  ls.getSelectLmId(request.getParameter("outlm"));
			log.debug("外网栏目编号:"+outlm);
			String inlm =  ls.getSelectLmId(request.getParameter("innerlm"));
			log.debug("内网栏目编号:"+inlm);
			//lm2 = request.getParameter("lm2");
			//lm3 = "0";
			title = request.getParameter("title");
			content = request.getParameter("content");
			zz = request.getParameter("zz");
			time = request.getParameter("time");
			hit = request.getParameter("hit");  //点击率待修改
			URL = request.getParameter("URL");
			titlecolor = request.getParameter("titlecolor");
			pic = request.getParameter("pic");
			//tj = request.getParameter("tj");
			//ontop = request.getParameter("ontop");
			//html = request.getParameter("html");
			adduser = request.getParameter("adduser"); //待定
			xgnews = request.getParameter("xgnews");
			htitle = request.getParameter("htitle");
			sh = request.getParameter("sh");   //待定
			shUsername = request.getParameter("shUsername");  //待定
			ztid = request.getParameter("ztid");
			googlemap = request.getParameter("googlemap");//待定
			filename = request.getParameter("filename");//待定
			updat = request.getParameter("updat");//待定
			piczz = request.getParameter("piczz");//待定
			laiyuan = request.getParameter("laiyuan");
			newslmType = request.getParameter("newstarget"); //1外网，2内网，0内外
			
			//内外网栏目分离
			String a[] = inlm.split(",");
			lm_in = a[0];
			lm2_in = a[1];
			lm3_in = a[2];
			
			String b[] = outlm.split(",");
			lm_out = b[0];
			lm2_out = b[1];
			lm3_out = b[2];
			
			NewsService db = new NewsService();
			
			log.debug("lm-"+lm_in);
			log.debug("lm2-"+lm2_in);
			log.debug("lm3-"+lm3_in);
			log.debug("title-"+title);
			log.debug("content-"+content);
			log.debug("zz-"+zz);
			log.debug("time-"+time);
			log.debug("hit-"+hit);
			log.debug("URL-"+URL);
			log.debug("titlecolor-"+titlecolor);
			log.debug("pic-"+pic);
			log.debug("tj-"+tj);
			log.debug("ontop-"+ontop);
			log.debug("html-"+html);
			log.debug("adduser-"+adduser);
			log.debug("xgnews-"+xgnews);
			log.debug("htitle-"+htitle);
			log.debug("sh-"+sh);
			log.debug("shUsername-"+shUsername);
			log.debug("ztid-"+ztid);
			log.debug("googlemap-"+googlemap);
			log.debug("filename-"+filename);
			log.debug("updat-"+updat);
			log.debug("piczz-"+piczz);
			log.debug("laiyuan-"+laiyuan);
			log.debug("newslmType-"+newslmType);
			//部分参数初始化
			tj = "0";
			ontop = "0";
			html = "0";
			
			if(newslmType.trim().equals("1")){  		//外网
				flag_out = db.addNews(lm_out, lm2_out, lm3_out, title, content, zz, time, hit, URL, titlecolor, pic, tj, ontop, html, adduser, xgnews, htitle, sh, shUsername, ztid, googlemap, filename, updat, piczz, laiyuan, newslmType);
				flag_in = true;
			}else if(newslmType.trim().equals("2")){  	//内网
				flag_in = db.addNews(lm_in, lm2_in, lm3_in, title, content, zz, time, hit, URL, titlecolor, pic, tj, ontop, html, adduser, xgnews, htitle, sh, shUsername, ztid, googlemap, filename, updat, piczz, laiyuan, newslmType);
				flag_out = true;
			}else if(newslmType.trim().equals("0")){	//内外兼发
				flag_in = db.addNews(lm_in, lm2_in, lm3_in, title, content, zz, time, hit, URL, titlecolor, pic, tj, ontop, html, adduser, xgnews, htitle, sh, shUsername, ztid, googlemap, filename, updat, piczz, laiyuan, newslmType);
				flag_out = db.addNews(lm_out, lm2_out, lm3_out, title, content, zz, time, hit, URL, titlecolor, pic, tj, ontop, html, adduser, xgnews, htitle, sh, shUsername, ztid, googlemap, filename, updat, piczz, laiyuan, newslmType);
			}
			//flag = db.addNews(lm_in, lm2_in, lm3_in, title, content, zz, time, hit, URL, titlecolor, pic, tj, ontop, html, adduser, xgnews, htitle, sh, shUsername, ztid, googlemap, filename, updat, piczz, laiyuan, newslmType);
						
			if(flag_in && flag_out){
				log.debug("文章添加成功");
				log.warn("用户“"+adduser+"”以“"+zz+"”为名添加文章："+title);
				session.setAttribute("msg", "文章添加成功！");
				log.debug(request.getAttribute("msg"));
				//request.getRequestDispatcher("../servlet/sendRedirectNewsAddServlet").forward(request, response);
				response.sendRedirect("../servlet/AdminNewsListServlet?cpage=1&clearSession=yes");
			}else{
				log.debug("文章添加失败");
				log.warn("用户“"+adduser+"”试图以“"+zz+"”为名添加文章，但失败了。");
				session.setAttribute("msg", "文章添加失败，请检查网络状态！");
				log.debug(request.getAttribute("msg"));
				//request.getRequestDispatcher("../servlet/sendRedirectNewsAddServlet").forward(request, response);
				response.sendRedirect("../servlet/AdminNewsListServlet?cpage=1&clearSession=yes");
			}
			
			
/*			PrintWriter out = response.getWriter();
			if(flag){
				out.println("success");
			}else{
				out.println("fail");
			}
			out.flush();
			out.close();*/
			
		}catch(Exception e){
			System.out.print(e.getMessage());
			log.error(e.getMessage());
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
