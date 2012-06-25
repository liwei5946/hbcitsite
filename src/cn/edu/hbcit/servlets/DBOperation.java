package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;



import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DBOperation extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Logger log = Logger.getLogger(DBOperation.class.getName());

	/**
	 * Constructor of the object.
	 */
	public DBOperation() {
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
		//
		String selectCondition=request.getParameter("Condition").trim();
		//create folder
        boolean  result  =   false ;
        File dirFile  = null ;
        try  {
           dirFile  =   new  File("d:/backwlps");
            if ( ! (dirFile.exists())  &&   ! (dirFile.isDirectory()))  {
                boolean  creadok  =  dirFile.mkdirs();
                if (creadok)  {
                	log.debug(" ok:�����ļ��гɹ��� ");
               } else  {
            	   log.debug(" err:�����ļ���ʧ�ܣ� ");
               } 
           } 
        } catch (Exception e)  {
           log.error(e);
       } 
		
		if(selectCondition.equals("0"))//����
		{
			if(backup()){
				log.warn("���ݿⱸ�ݳɹ���");
				out.print("success");
			}
			else{
				log.warn("���ݿⱸ��ʧ�ܣ�");
				out.print("error");
			}
		}
		if(selectCondition.equals("1"))//�ָ�
		{
			if(load()){
				log.warn("���ݿ�ָ��ɹ���");
				out.print("success");
			}
			else{
				log.warn("���ݿ�ָ�ʧ�ܣ�");
				out.print("error");
			}
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**  
     * ���ݼ���һ��sql�ļ��Ƿ�����������ļ��õ�һ���жϷ������Ѹ�sql�ļ��ֱ��ü��±���ultra  
     * edit�򿪣�������������ľ�����û�����룬����������������Դ�ļ�������sql�ļ��ı����ʽ��Σ�Ҳ����db�ı����ʽ��Σ�  
     */  
    public  boolean backup() {   
    	boolean retuvalue=false;
    	try {   
           
        	Runtime rt = Runtime.getRuntime();   
  
            // ���� mysql �� cmd:   
            Process child = rt.exec("mysqldump -uroot -proot --set-charset=utf8 hbcit");// ���õ�������Ϊutf8�����������utf8   
            // �ѽ���ִ���еĿ���̨�����Ϣд��.sql�ļ����������˱����ļ���ע��������Կ���̨��Ϣ���ж�������ᵼ�½��̶����޷�����   
            InputStream in = child.getInputStream();// ����̨�������Ϣ��Ϊ������   
                          
            InputStreamReader xx = new InputStreamReader(in, "utf8");// �������������Ϊutf8�����������utf8����������ж����������   
              
            String inStr;   
            StringBuffer sb = new StringBuffer("");   
            String outStr;   
            // ��Ͽ���̨�����Ϣ�ַ���   
            BufferedReader br = new BufferedReader(xx);   
            while ((inStr = br.readLine()) != null) {   
                sb.append(inStr + "\r\n");   
            }   
            outStr = sb.toString();   
              
            //Ҫ�����������õ�sqlĿ���ļ���   
            FileOutputStream fout = new FileOutputStream("d:/backwlps/hbcit.sql");   
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");   
            writer.write(outStr);   
            // ע����������û��巽ʽд���ļ��Ļ����ᵼ���������룬��flush()��������Ա���   
            writer.flush();   
  
            // �����ǹر����������   
            in.close();   
            xx.close();   
            br.close();   
            writer.close();   
            fout.close();   
            retuvalue=true;
            System.out.println(" Output OK! "); 

        } catch (Exception e) {   
            e.printStackTrace();   
            retuvalue=false;
        }   
        return retuvalue;
    }   
  
    /**  
     * ����  
     *  
     */  
    public  boolean load() {  
    	boolean retuvalue=false;
        try {   
            String fPath = "d:/backwlps/hbcit.sql";   
            Runtime rt = Runtime.getRuntime();   
  
            // ���� mysql �� cmd:   
            Process child = rt.exec("mysql -h 222.30.188.52 -uroot -proot hbcit");   
            OutputStream out = child.getOutputStream();//����̨��������Ϣ��Ϊ�����   
            String inStr;   
            StringBuffer sb = new StringBuffer("");   
            String outStr;   
            BufferedReader br = new BufferedReader(new InputStreamReader(   
                    new FileInputStream(fPath), "utf8"));   
            while ((inStr = br.readLine()) != null) {   
                sb.append(inStr + "\r\n");   
            }   
            outStr = sb.toString();   
  
            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");   
            writer.write(outStr);   
            // ע����������û��巽ʽд���ļ��Ļ����ᵼ���������룬��flush()��������Ա���   
            writer.flush();   
            // �����ǹر����������   
            out.close();   
            br.close();   
            writer.close();   
            retuvalue=true;
            System.out.println("/* Load OK! */");   
  
        } catch (Exception e) {   
            e.printStackTrace(); 
            retuvalue=false;
        }   
        return retuvalue;
    }  

}
