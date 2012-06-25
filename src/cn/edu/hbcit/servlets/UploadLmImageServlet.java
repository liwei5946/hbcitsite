package cn.edu.hbcit.servlets;


import java.io.*;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.services.DynamicSetLmService;





public class UploadLmImageServlet extends HttpServlet {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	DynamicSetLmService dynamicSetLmService=new DynamicSetLmService();
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	       throws ServletException, IOException {
	   String retuValue="error";
	   //
	   String fName=new String(request.getParameter("file").getBytes("iso-8859-1"),"utf-8");//图片名
	   String id=request.getParameter("id");//版块号
	 //取出文件名
	   File tempFile =new File(fName.trim());   
       String fileName = tempFile.getName();   
       //
	   String savePath = this.getServletConfig().getServletContext().getRealPath("");
	   savePath = savePath + "/images/";
	   try
	   {
		   FileInputStream inputStream = new FileInputStream(fName);
		   FileOutputStream outputStream = new FileOutputStream(savePath + fileName);
		   byte[] buf = new byte[1024];
		   int length = 0;
		   while ((length = inputStream.read(buf)) != -1) {
		    outputStream.write(buf, 0, length);
		   }
		   inputStream.close();
		   outputStream.flush();
		   //保存到dynamicsetlm表中
		   dynamicSetLmService.updateOneDynamicLmPic(fileName, id);
		   //
		   retuValue="success";
	  } catch (Exception e) {
		  e.printStackTrace();
		  retuValue="error";
	  }
	    response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//
		out.println(retuValue);
		out.flush();
		out.close();
	 
	  
	}







	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor of the object.
	 */
	public UploadLmImageServlet() {
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
