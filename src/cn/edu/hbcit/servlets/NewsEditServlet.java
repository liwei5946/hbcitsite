package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.dao.NewsDAO;
import cn.edu.hbcit.services.LmService;
import cn.edu.hbcit.services.NewsService;
import cn.edu.hbcit.util.UtilTools;

public class NewsEditServlet extends HttpServlet {
	protected final Logger log = Logger.getLogger(NewsEditServlet.class.getName());

	/**
	 * Constructor of the object.
	 */
	public NewsEditServlet() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = request.getSession();
		//PrintWriter out = response.getWriter();

		String lm = ""; 				//栏目
		String lm2 = ""; 				//栏目2
		String lm3 = "";  				//栏目3
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
		String updat = "";				//
		String piczz = "";				//
		String laiyuan = "";			//来源部门
		String id = "";
		
		boolean flag = false;
		try{
			LmService ls = new LmService();
			NewsService ns = new NewsService();
			UtilTools ut = new UtilTools();
			String mylm =  ls.getSelectLmId(request.getParameter("lm"));
			log.debug("栏目编号:"+mylm);

			id = request.getParameter("id");
			title = request.getParameter("title");
			content = request.getParameter("content");
			zz = request.getParameter("zz");
			time = request.getParameter("time");
			hit = request.getParameter("hit");  //点击率待修改
			URL = request.getParameter("URL");
			titlecolor = request.getParameter("titlecolor");
			pic = request.getParameter("pic");
			tj = request.getParameter("tj");
			ontop = request.getParameter("ontop");
			html = request.getParameter("html");
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
			
			String a[] = mylm.split(",");
			lm = a[0];
			lm2 = a[1];
			lm3 = a[2];

			
			NewsDAO db = new NewsDAO();
			
			log.debug("id-"+id);
			log.debug("lm-"+lm);
			log.debug("lm2-"+lm2);
			log.debug("lm3-"+lm3);
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
			
			if(ut.isNumeric(id)){
				flag = ns.editNews(Integer.parseInt(id), lm, lm2, lm3, title, content, zz, time, hit, URL, titlecolor, pic, tj, ontop, html, adduser, xgnews, htitle, sh, shUsername, ztid, googlemap, filename, updat, piczz, laiyuan);
				//log.debug(flag);
			}
			//flag = db.addNews(lm, lm2, lm3, title, content, zz, time, hit, URL, titlecolor, pic, tj, ontop, html, adduser, xgnews, htitle, sh, shUsername, ztid, googlemap, filename, updat, piczz, laiyuan);
			
			log.debug("开始跳转。。。");
			if(flag){
				log.debug("文章修改成功");
				log.warn("用户“"+adduser+"”以“"+zz+"”为名修改了第"+id+"号文章。");
				session.setAttribute("msg", "文章修改成功！");
				log.debug(request.getAttribute("msg"));
				//request.getRequestDispatcher("../servlet/sendRedirectNewsAddServlet").forward(request, response);
				//response.sendRedirect("../servlet/sendRedirectNewsEditServlet");
				// syj 2011-11-11
				response.sendRedirect("../servlet/AdminNewsListServlet?cpage=1&clearSession=yes");
			}else{
				log.debug("文章修改失败");
				log.warn("用户“"+adduser+"”试图以“"+zz+"”为名修改第"+id+"号文章，但失败了。");
				session.setAttribute("msg", "文章修改失败，请检查网络状态！");
				log.debug(request.getAttribute("msg"));
				//request.getRequestDispatcher("../servlet/sendRedirectNewsAddServlet").forward(request, response);
				//response.sendRedirect("../servlet/sendRedirectNewsEditServlet");
				//syj 2011-11-10
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
