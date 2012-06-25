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

		String lm = ""; 				//��Ŀ
		String lm2 = ""; 				//��Ŀ2
		String lm3 = "";  				//��Ŀ3
		String title = "";  			//����
		String content = "";  			//����
		String zz = "";    				//����(��ʵ����)
		String time = "";   			//���ʱ��
		String hit = "";   				//�����
		String URL = "";				//��תĿ��URLҳ��
		String titlecolor = "";			//������ɫ
		String pic = "";				//��ҳ������תͼƬ
		String tj = "";					//�Ƿ��Ƽ�
		String ontop = "";				//�Ƿ��ö�
		String html = "";				//ת����̬ҳ��
		String adduser = "";			//����(�ʺ�)
		String xgnews = "";				//�������
		String htitle = "";				//������
		String sh = "";					//�Ƿ��Ѿ����
		String shUsername = "";			//���Ա����
		String ztid = "";				//ר��id
		String googlemap = "";			//
		String filename = "";			//
		String updat = "";				//
		String piczz = "";				//
		String laiyuan = "";			//��Դ����
		String id = "";
		
		boolean flag = false;
		try{
			LmService ls = new LmService();
			NewsService ns = new NewsService();
			UtilTools ut = new UtilTools();
			String mylm =  ls.getSelectLmId(request.getParameter("lm"));
			log.debug("��Ŀ���:"+mylm);

			id = request.getParameter("id");
			title = request.getParameter("title");
			content = request.getParameter("content");
			zz = request.getParameter("zz");
			time = request.getParameter("time");
			hit = request.getParameter("hit");  //����ʴ��޸�
			URL = request.getParameter("URL");
			titlecolor = request.getParameter("titlecolor");
			pic = request.getParameter("pic");
			tj = request.getParameter("tj");
			ontop = request.getParameter("ontop");
			html = request.getParameter("html");
			adduser = request.getParameter("adduser"); //����
			xgnews = request.getParameter("xgnews");
			htitle = request.getParameter("htitle");
			sh = request.getParameter("sh");   //����
			shUsername = request.getParameter("shUsername");  //����
			ztid = request.getParameter("ztid");
			googlemap = request.getParameter("googlemap");//����
			filename = request.getParameter("filename");//����
			updat = request.getParameter("updat");//����
			piczz = request.getParameter("piczz");//����
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
			
			log.debug("��ʼ��ת������");
			if(flag){
				log.debug("�����޸ĳɹ�");
				log.warn("�û���"+adduser+"���ԡ�"+zz+"��Ϊ���޸��˵�"+id+"�����¡�");
				session.setAttribute("msg", "�����޸ĳɹ���");
				log.debug(request.getAttribute("msg"));
				//request.getRequestDispatcher("../servlet/sendRedirectNewsAddServlet").forward(request, response);
				//response.sendRedirect("../servlet/sendRedirectNewsEditServlet");
				// syj 2011-11-11
				response.sendRedirect("../servlet/AdminNewsListServlet?cpage=1&clearSession=yes");
			}else{
				log.debug("�����޸�ʧ��");
				log.warn("�û���"+adduser+"����ͼ�ԡ�"+zz+"��Ϊ���޸ĵ�"+id+"�����£���ʧ���ˡ�");
				session.setAttribute("msg", "�����޸�ʧ�ܣ���������״̬��");
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
