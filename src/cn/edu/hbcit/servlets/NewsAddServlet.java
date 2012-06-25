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
 * �������. <br>
 * @author ����
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

//		String realPathImg = "";	//��תͼƬ����·��
//		String nameImg = "";		//����ͼƬ�ļ���
		
		String lm_in = ""; 				//������Ŀ
		String lm2_in = ""; 				//������Ŀ2
		String lm3_in = "";  				//������Ŀ3
		String lm_out = ""; 				//������Ŀ
		String lm2_out = ""; 				//������Ŀ2
		String lm3_out = "";  				//������Ŀ3
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
		String updat = "";				//����˳��
		String piczz = "";				//
		String laiyuan = "";			//��Դ����
		String newslmType = "";			//����������
		
		boolean flag_in = false;
		boolean flag_out = false;
		/*SmartUpload su = new SmartUpload(); //ʵ����jspSmartUpload
		su.initialize(config, request, response);// ��ʼ��SmartUpload
		su.setAllowedFilesList("gif,jpg,jpeg,png,doc,xls,ppt,pdf,txt,rar,zip,vsd"); //�����ϴ����ļ�����(�м���,����)
		su.setMaxFileSize(100 * 1024 * 1024); //�����ϴ��ļ��������
		log.debug("�����ϴ�...");*/
		
		try{
			LmService ls = new LmService();
			String outlm =  ls.getSelectLmId(request.getParameter("outlm"));
			log.debug("������Ŀ���:"+outlm);
			String inlm =  ls.getSelectLmId(request.getParameter("innerlm"));
			log.debug("������Ŀ���:"+inlm);
			//lm2 = request.getParameter("lm2");
			//lm3 = "0";
			title = request.getParameter("title");
			content = request.getParameter("content");
			zz = request.getParameter("zz");
			time = request.getParameter("time");
			hit = request.getParameter("hit");  //����ʴ��޸�
			URL = request.getParameter("URL");
			titlecolor = request.getParameter("titlecolor");
			pic = request.getParameter("pic");
			//tj = request.getParameter("tj");
			//ontop = request.getParameter("ontop");
			//html = request.getParameter("html");
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
			newslmType = request.getParameter("newstarget"); //1������2������0����
			
			//��������Ŀ����
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
			//���ֲ�����ʼ��
			tj = "0";
			ontop = "0";
			html = "0";
			
			if(newslmType.trim().equals("1")){  		//����
				flag_out = db.addNews(lm_out, lm2_out, lm3_out, title, content, zz, time, hit, URL, titlecolor, pic, tj, ontop, html, adduser, xgnews, htitle, sh, shUsername, ztid, googlemap, filename, updat, piczz, laiyuan, newslmType);
				flag_in = true;
			}else if(newslmType.trim().equals("2")){  	//����
				flag_in = db.addNews(lm_in, lm2_in, lm3_in, title, content, zz, time, hit, URL, titlecolor, pic, tj, ontop, html, adduser, xgnews, htitle, sh, shUsername, ztid, googlemap, filename, updat, piczz, laiyuan, newslmType);
				flag_out = true;
			}else if(newslmType.trim().equals("0")){	//����淢
				flag_in = db.addNews(lm_in, lm2_in, lm3_in, title, content, zz, time, hit, URL, titlecolor, pic, tj, ontop, html, adduser, xgnews, htitle, sh, shUsername, ztid, googlemap, filename, updat, piczz, laiyuan, newslmType);
				flag_out = db.addNews(lm_out, lm2_out, lm3_out, title, content, zz, time, hit, URL, titlecolor, pic, tj, ontop, html, adduser, xgnews, htitle, sh, shUsername, ztid, googlemap, filename, updat, piczz, laiyuan, newslmType);
			}
			//flag = db.addNews(lm_in, lm2_in, lm3_in, title, content, zz, time, hit, URL, titlecolor, pic, tj, ontop, html, adduser, xgnews, htitle, sh, shUsername, ztid, googlemap, filename, updat, piczz, laiyuan, newslmType);
						
			if(flag_in && flag_out){
				log.debug("������ӳɹ�");
				log.warn("�û���"+adduser+"���ԡ�"+zz+"��Ϊ��������£�"+title);
				session.setAttribute("msg", "������ӳɹ���");
				log.debug(request.getAttribute("msg"));
				//request.getRequestDispatcher("../servlet/sendRedirectNewsAddServlet").forward(request, response);
				response.sendRedirect("../servlet/AdminNewsListServlet?cpage=1&clearSession=yes");
			}else{
				log.debug("�������ʧ��");
				log.warn("�û���"+adduser+"����ͼ�ԡ�"+zz+"��Ϊ��������£���ʧ���ˡ�");
				session.setAttribute("msg", "�������ʧ�ܣ���������״̬��");
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
