package cn.edu.hbcit.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.edu.hbcit.services.NewsService;
import cn.edu.hbcit.util.CountNews;


public class ExportExcelServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NewsService newsService=new NewsService();
	/**
	 * Constructor of the object.
	 */
	public ExportExcelServlet() {
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
		//
		try
		{
			String fname = "NewsCount";
			OutputStream os = response.getOutputStream();
			response.reset();// 
			response.setHeader("Content-disposition", "attachment; filename="+ fname + ".xls");
			response.setContentType("application/msexcel");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("SysLog sheet");
			HSSFRow row = sheet.createRow((short) 0);
		    HSSFCell cell = row.createCell((short) 0);
		    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		    cell.setCellValue("部门名称");
		    HSSFCell cell1 = row.createCell((short) 1);
		    cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
		    cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
		    cell1.setCellValue("新闻数量");
		    //
			String startTime=request.getParameter("st");
			String endTime=request.getParameter("et");
			if(startTime.trim().length()==0 && endTime.trim().length()==0)
			{
				startTime="";
				endTime="";
			}
		    List<CountNews> newsCountList=newsService.countBumenNews(startTime, endTime);
			//
			Iterator<CountNews> itl=newsCountList.iterator();
			int rowNum=1;
			while(itl.hasNext())
			{
				CountNews countNews=(CountNews)itl.next();
				HSSFRow row1 = sheet.createRow((short)rowNum);// 
				HSSFCell cell11 = row1.createCell((short) 0);
			    cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
			    cell11.setEncoding(HSSFCell.ENCODING_UTF_16);// 
			    cell11.setCellValue(countNews.getUnit().trim());
			    HSSFCell cell13 = row1.createCell((short) 1);
			    cell13.setCellType(HSSFCell.CELL_TYPE_STRING);
			    cell13.setEncoding(HSSFCell.ENCODING_UTF_16);// 
			    cell13.setCellValue(countNews.getNumbs());
			    //
			    rowNum++;

			}
	        wb.write(os);
	        os.flush();
	        os.close();

	    }catch(Exception e){}
		
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

		doGet( request, response);
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
