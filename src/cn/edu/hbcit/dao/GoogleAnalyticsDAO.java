package cn.edu.hbcit.dao;


import com.google.gdata.client.analytics.AnalyticsService;
import com.google.gdata.client.analytics.DataQuery;
import com.google.gdata.data.analytics.AccountEntry;
import com.google.gdata.data.analytics.AccountFeed;
import com.google.gdata.data.analytics.DataEntry;
import com.google.gdata.data.analytics.DataFeed;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import hirondelle.date4j.DateTime;
import cn.edu.hbcit.util.OperateProperties;
import cn.edu.hbcit.util.UtilTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

/**
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * 
 * <p>
 * Company: 河北工业职业技术学院
 * </p>
 * 
 * @author 作者 : liwei5946@gmail.com
 * @version 创建时间：Nov 10, 2011 5:34:39 PM
 */

public class GoogleAnalyticsDAO {
	
	protected final Logger log = Logger.getLogger(GoogleAnalyticsDAO.class.getName());					//log4j日志
	static OperateProperties op = new OperateProperties();											//Properties文件操作
	private static String googleUsername = op.readValue("/analytics.properties", "googleusername");	//Google 帐号
	private static String googlePassword = op.readValue("/analytics.properties", "googlepassword");	//Google 密码
	private static String tableId = "ga:"+op.readValue("/analytics.properties", "analyticsid");		//此帐号有权访问的Google Analytics配置文件的TABLE ID
	
	BaseDAO db=new BaseDAO(); 
	private Connection conn=null;
	private PreparedStatement pStatement = null;
	private ResultSet rs=null;
	
	
	
	/**
	 * 获取Google Analytics的统计信息
	 */
	public void getAnalytics() {
		DateTime now = DateTime.now(TimeZone.getTimeZone("GMT+8:00"));
		//
//		SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss     ");  
//		Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间     
//		String   nowStr   =   formatter.format(curDate); 
		//
		String dataEveryDay = "";
		String startDay = now.plusDays(-2).format("YYYY-MM-DD").toString();
		String endDay = now.plusDays(-2).format("YYYY-MM-DD").toString();
		int flag = 0; 
		log.debug(now.format("YYYY-MM-DD hh:mm:ss").toString() +"--准备获取"+startDay+"这一天的信息");
		try {
			// Access the Data Feed if the Table Id has been set.
			if (!tableId.isEmpty() && !isAlreadyAnalysis(startDay)) {
			/*
			 * 系统创建服务对象。服务对象的参数是一个代表应用程序名称的字符串。随后，系统将采用 setUserCredentials 方法来处理
			 * Google Analytics（分析）授权。
			 */
			// Service Object to work with the Google Analytics Data Export API.
			AnalyticsService analyticsService = new AnalyticsService("gaExportAPI_acctSample_v2.0");
			// Client Login Authorization.
			analyticsService.setUserCredentials(googleUsername, googlePassword);

			// Get data from the Account Feed.
			getAccountFeed(analyticsService);  //获取帐号信息

			
				/* Get profile data from the Data Feed.
				 * 获取数据信息（包括"指标"和"维度"）
				 * 此处为防止中国与美国google服务器的时差等问题，将统计数据定为两天前
				*/
				dataEveryDay = getDataFeed(analyticsService, startDay, endDay);  
				log.debug("获得time、pageviews、visits、visitors：" + dataEveryDay);
				flag = writeAnalytics2DB(dataEveryDay);
				if(flag > 0){
					log.warn("已于" + now.format("YYYY-MM-DD hh:mm:ss").toString() + "将统计信息成功记录！");
				}
			}

		} catch (AuthenticationException e) {
			System.err.println("Authentication failed : " + e.getMessage());
			return;
		} catch (IOException e) {
			System.err.println("Network error trying to retrieve feed: "
					+ e.getMessage());
			return;
		} catch (ServiceException e) {
			System.err.println("Analytics API responded with an error message: "
							+ e.getMessage());
			return;
		}
	}

	/**
	 * 获取帐号feed
	 * @param analyticsService
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws ServiceException
	 */
	private static void getAccountFeed(AnalyticsService analyticsService)
			throws IOException, MalformedURLException, ServiceException {

		// Construct query from a string.
		URL queryUrl = new URL("https://www.google.com/analytics/feeds/accounts/default");

		// Make request to the API.
		AccountFeed accountFeed = analyticsService.getFeed(queryUrl, AccountFeed.class);

		// Output the data to the screen.
		System.out.println("-------- Account Feed Results --------");
		for (AccountEntry entry : accountFeed.getEntries()) {
			System.out.println("\nAccount Name  = "
					+ entry.getProperty("ga:accountName")
					+ "\nProfile Name  = " + entry.getTitle().getPlainText()  //配置文件名称
					+ "\nProfile Id    = " + entry.getProperty("ga:profileId")  //配置文件编号
					+ "\nTable Id      = " + entry.getTableId().getValue());   //配置文件的Table Id
		}
	}

	/**
	 * 获取指标和维度信息
	 * @param analyticsService
	 * @param startDate  统计起始日期
	 * @param endDate  统计结束日期
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws ServiceException
	 */
	private String getDataFeed(AnalyticsService analyticsService, String startDate, String endDate)
			throws IOException, MalformedURLException, ServiceException {
		
		
		//String startDate = now.plusDays(-1).format("YYYY-MM-DD").toString();
		//String endDate = now.plusDays(-1).format("YYYY-MM-DD").toString();
		String returnString = "";
		long pageviews = 0;
		long visits = 0;
		long visitors = 0;

		// Create a query using the DataQuery Object.
		DataQuery query = new DataQuery(new URL("https://www.google.com/analytics/feeds/data"));
		query.setStartDate(startDate);  //要统计的数据的起始时间
		query.setEndDate(endDate);  //要统计的数据的结束时间
		query.setDimensions("ga:pageTitle,ga:pagePath");   //要统计的维度信息
		query.setMetrics("ga:pageviews,ga:bounces,ga:visits,ga:visitors");  //要统计的指标信息
		query.setSort("-ga:pageviews");  
		query.setMaxResults(10);
		query.setIds(tableId);
		
		// Make a request to the API.
		DataFeed dataFeed = analyticsService.getFeed(query.getUrl(), DataFeed.class);

		// Output data to the screen.
		System.out.println("----------- Data Feed Results ----------");
		/*for (DataEntry entry : dataFeed.getEntries()) {
			returnString = startDate + ","
						+ entry.longValueOf("ga:pageviews") + ","
						+ entry.longValueOf("ga:visits") + ","
						+ entry.longValueOf("ga:visitors");
		}*/
		for (DataEntry entry : dataFeed.getEntries()){
			pageviews += entry.longValueOf("ga:pageviews");
		}
		for (DataEntry entry : dataFeed.getEntries()){
			visits += entry.longValueOf("ga:visits");
		}
		for (DataEntry entry : dataFeed.getEntries()){
			visitors += entry.longValueOf("ga:visitors");
		}
		returnString = startDate + ","
		+ String.valueOf(pageviews) + ","
		+ String.valueOf(visits) + ","
		+ String.valueOf(visitors);
		
		log.debug("统计结果"+returnString);
		
		for (DataEntry entry : dataFeed.getEntries()) {
			System.out.println("\nPage Title = "
					+ entry.stringValueOf("ga:pageTitle") + "\nPage Path  = "
					+ entry.stringValueOf("ga:pagePath") + "\nPageviews浏览量  = "
					+ entry.stringValueOf("ga:pageviews") + "\nga:bounces = "
					+ entry.stringValueOf("ga:bounces") + "\nga:visits访问次数 = "
					+ entry.stringValueOf("ga:visits") + "\nga:visitors访问人数 = "
					+ entry.stringValueOf("ga:visitors"));
		}
		
		return returnString;
	}
	
	/**
	 * 将每日统计结果写入数据库
	 * @param dataValue
	 * @return int
	 */
	public int writeAnalytics2DB(String dataValue){
		int state = 0;
		try{
			conn=db.getConn();
			UtilTools ut = new UtilTools();
			
			
			String time,pageviews,visits,visitors;
			String a[] = dataValue.split(",");
			time = a[0];
			pageviews = a[1];
			visits = a[2];
			visitors = a[3];
			
			
			String sql = "INSERT INTO analytics (time,pageviews,visits,visitors) VALUES (?,?,?,?)";
			
			if(ut.isNumeric(pageviews) && ut.isNumeric(visits) && ut.isNumeric(visitors)){
				pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, time);
				pStatement.setInt(2, Integer.parseInt(pageviews));
				pStatement.setInt(3, Integer.parseInt(visits));
				pStatement.setInt(4, Integer.parseInt(visitors));
				state = pStatement.executeUpdate();
			}
			db.closeAll(conn);
			
		}catch(Exception e){
			log.error(e.getMessage());
		}
		
		return state;
	}
	
	/**
	 * 检索当前日期的统计信息是否已写入库中
	 * @param day
	 * @return
	 */
	public boolean isAlreadyAnalysis(String day){
		boolean flag = false;
		int returnValue = 0;
		DateTime now = DateTime.now(TimeZone.getTimeZone("GMT+8:00"));
		try{
			conn=db.getConn();
			String sql = "SELECT count(*) FROM analytics WHERE time=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, day);
			rs = pStatement.executeQuery();
			while(rs.next()){
				returnValue = rs.getInt(1);
			}
			
			if(returnValue > 0){
				flag = true;
				log.debug(now.format("YYYY-MM-DD hh:mm:ss")+"--数据库中已存在"+day+"这一天的信息");
			}
			db.closeAll(conn);
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return flag;
	}
	
	/**
	 * 统计浏览量
	 * @param startDay
	 * @param endDay
	 * @return JSON 例：[["2011-10-01",23],["2011-10-02",29],["2011-10-03",48],["2011-10-04",88]]
	 */
	public JSONArray selectPageviewsJSON(String startDay, String endDay){
		JSONArray finalJson = new JSONArray();
		DateTime now = DateTime.now(TimeZone.getTimeZone("GMT+8:00"));
		//如果起止时间均为空，则统计10天内的信息
		if(startDay.trim().length()==0||endDay.trim().length()==0){
			endDay = now.plusDays(-2).format("YYYY-MM-DD").toString();
			startDay = now.plusDays(-11).format("YYYY-MM-DD").toString();
		}
		System.out.println("起始时间："+startDay+"\t结束时间："+endDay);
		try{
			conn=db.getConn();
			//String sql = "SELECT time,pageviews,visits,visitors FROM analytics WHERE time<=? AND time>=?";
			String sql = "SELECT time,pageviews FROM analytics WHERE time<=? AND time>=? ORDER BY time ASC";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, endDay);
			pStatement.setString(2, startDay);
			rs = pStatement.executeQuery();
			while(rs.next()){
				JSONArray json = new JSONArray();
				json.add(rs.getString("time")); 
				json.add(rs.getInt("pageviews")); 
				finalJson.add(json);
			}
			db.closeAll(conn);
		}catch(Exception e){
			System.err.println("统计浏览量 failed : " + e.getMessage());
		}
		return finalJson;
	}
	
	/**
	 * 统计访问次数
	 * @param startDay
	 * @param endDay
	 * @return JSON 例：[["2011-10-01",23],["2011-10-02",29],["2011-10-03",48],["2011-10-04",88]]
	 */
	public JSONArray selectVisitsJSON(String startDay, String endDay){
		JSONArray finalJson = new JSONArray();
		DateTime now = DateTime.now(TimeZone.getTimeZone("GMT+8:00"));
		//如果起止时间均为空，则统计10天内的信息
		if(startDay.trim().length()==0||endDay.trim().length()==0){
			endDay = now.plusDays(-2).format("YYYY-MM-DD").toString();
			startDay = now.plusDays(-11).format("YYYY-MM-DD").toString();
		}
		try{
			conn=db.getConn();
			//String sql = "SELECT time,pageviews,visits,visitors FROM analytics WHERE time<=? AND time>=?";
			String sql = "SELECT time,visits FROM analytics WHERE time<=? AND time>=? ORDER BY time ASC";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, endDay);
			pStatement.setString(2, startDay);
			rs = pStatement.executeQuery();
			while(rs.next()){
				JSONArray json = new JSONArray();
				json.add(rs.getString("time")); 
				json.add(rs.getInt("visits")); 
				finalJson.add(json);
			}
			db.closeAll(conn);
		}catch(Exception e){
			System.err.println("统计访问次数 failed : " + e.getMessage());
		}
		return finalJson;
	}
	
	/**
	 * 统计访问人数
	 * @param startDay
	 * @param endDay
	 * @return JSON 例：[["2011-10-01",23],["2011-10-02",29],["2011-10-03",48],["2011-10-04",88]]
	 */
	public JSONArray selectVisitorsJSON(String startDay, String endDay){
		JSONArray finalJson = new JSONArray();
		DateTime now = DateTime.now(TimeZone.getTimeZone("GMT+8:00"));
		//如果起止时间均为空，则统计10天内的信息
		if(startDay.trim().length()==0||endDay.trim().length()==0){
			endDay = now.plusDays(-2).format("YYYY-MM-DD").toString();
			startDay = now.plusDays(-11).format("YYYY-MM-DD").toString();
		}
		try{
			conn=db.getConn();
			//String sql = "SELECT time,pageviews,visits,visitors FROM analytics WHERE time<=? AND time>=?";
			String sql = "SELECT time,visitors FROM analytics WHERE time<=? AND time>=? ORDER BY time ASC";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, endDay);
			pStatement.setString(2, startDay);
			rs = pStatement.executeQuery();
			while(rs.next()){
				JSONArray json = new JSONArray();
				json.add(rs.getString("time")); 
				json.add(rs.getInt("visitors")); 
				finalJson.add(json);
			}
			db.closeAll(conn);
		}catch(Exception e){
			System.err.println("统计访问人数 failed : " + e.getMessage());
		}
		return finalJson;
	}

}
