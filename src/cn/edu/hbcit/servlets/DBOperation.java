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
                	log.debug(" ok:创建文件夹成功！ ");
               } else  {
            	   log.debug(" err:创建文件夹失败！ ");
               } 
           } 
        } catch (Exception e)  {
           log.error(e);
       } 
		
		if(selectCondition.equals("0"))//备份
		{
			if(backup()){
				log.warn("数据库备份成功！");
				out.print("success");
			}
			else{
				log.warn("数据库备份失败！");
				out.print("error");
			}
		}
		if(selectCondition.equals("1"))//恢复
		{
			if(load()){
				log.warn("数据库恢复成功！");
				out.print("success");
			}
			else{
				log.warn("数据库恢复失败！");
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
     * 备份检验一个sql文件是否可以做导入文件用的一个判断方法：把该sql文件分别用记事本和ultra  
     * edit打开，如果看到的中文均正常没有乱码，则可以用来做导入的源文件（不管sql文件的编码格式如何，也不管db的编码格式如何）  
     */  
    public  boolean backup() {   
    	boolean retuvalue=false;
    	try {   
           
        	Runtime rt = Runtime.getRuntime();   
  
            // 调用 mysql 的 cmd:   
            Process child = rt.exec("mysqldump -uroot -proot --set-charset=utf8 hbcit");// 设置导出编码为utf8。这里必须是utf8   
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行   
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流   
                          
            InputStreamReader xx = new InputStreamReader(in, "utf8");// 设置输出流编码为utf8。这里必须是utf8，否则从流中读入的是乱码   
              
            String inStr;   
            StringBuffer sb = new StringBuffer("");   
            String outStr;   
            // 组合控制台输出信息字符串   
            BufferedReader br = new BufferedReader(xx);   
            while ((inStr = br.readLine()) != null) {   
                sb.append(inStr + "\r\n");   
            }   
            outStr = sb.toString();   
              
            //要用来做导入用的sql目标文件：   
            FileOutputStream fout = new FileOutputStream("d:/backwlps/hbcit.sql");   
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");   
            writer.write(outStr);   
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免   
            writer.flush();   
  
            // 别忘记关闭输入输出流   
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
     * 导入  
     *  
     */  
    public  boolean load() {  
    	boolean retuvalue=false;
        try {   
            String fPath = "d:/backwlps/hbcit.sql";   
            Runtime rt = Runtime.getRuntime();   
  
            // 调用 mysql 的 cmd:   
            Process child = rt.exec("mysql -h 222.30.188.52 -uroot -proot hbcit");   
            OutputStream out = child.getOutputStream();//控制台的输入信息作为输出流   
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
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免   
            writer.flush();   
            // 别忘记关闭输入输出流   
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
