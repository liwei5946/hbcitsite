package cn.edu.hbcit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import cn.edu.hbcit.pojo.Log;
import hirondelle.date4j.DateTime;
import java.util.TimeZone;

public class LogDAO {
	BaseDAO db=new BaseDAO(); 
	private ResultSet rs=null;
	private Connection conn=null;
	private PreparedStatement pStatement = null;
	protected final Logger log = Logger.getLogger(LogDAO.class.getName());
	/**
	 * 检索日志
	 * @return
	 */
	public ArrayList<Log> selectLog(){
		ArrayList<Log> list = new ArrayList<Log>();
		conn=db.getConn();
		String sql = "SELECT id,logtime,msg,loglevel,logclass FROM log4j ORDER BY logtime DESC LIMIT 50";
		try{
			
			pStatement=conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Log log4j = new Log();
				
				log4j.setId(rs.getInt("id"));
				log4j.setLogtime(rs.getString("logtime"));
				log4j.setMsg(rs.getString("msg"));
				log4j.setLoglevel(rs.getString("loglevel"));
				log4j.setLogclass(rs.getString("logclass"));
				
				list.add(log4j);
			}
			db.closeAll(conn);
		}catch(Exception e){
			log.error(e);
		}
		return list;
	}
	
	/**
	 * 删除若干天前的日志记录
	 * @param beforeDate 删除多少天前的记录
	 * @return 删除记录数
	 */
	public int delLog(int beforeDate){
		//Boolean flag = false;
		int vol = 0;
		//使用DATE4J进行快速日期处理
		DateTime now = DateTime.now(TimeZone.getTimeZone("GMT+8:00"));
		String delDate = now.plusDays(-1 * beforeDate).format("YYYY-MM-DD hh:mm:ss").toString(); //计算beforeDate天前的日期时间
		log.debug(now.plusDays(-1 * beforeDate).format("YYYY-MM-DD hh:mm:ss"));
		String sql = "DELETE FROM log4j WHERE logtime < ?";
		try{
			conn=db.getConn();
			pStatement=conn.prepareStatement(sql);
			pStatement.setString(1, delDate);
			vol = pStatement.executeUpdate();
			db.closeAll(conn);
		}catch(Exception e){
			log.error(e);
		}
		
		return vol;
		
		/* 试验DATE4J：http://blog.csdn.net/softwave/article/details/5564724
		//Examples  
	    //Here are some quick examples of using date4j's DateTime class :  
	    DateTime dateAndTime = new DateTime("2010-01-19 23:59:59");  
	    DateTime dateAndTime = new DateTime("2010-01-19 23:59:59.123456789");  
	    DateTime dateOnly = new DateTime("2010-01-19");  
	    DateTime timeOnly = new DateTime("23:59:59");  
	    DateTime dateOnly = DateTime.forDateOnly(2010,01,19);  
	    DateTime timeOnly = DateTime.forTimeOnly(23,59,59,0);  
	    DateTime dt = new DateTime("2010-01-15 13:59:15");  
	    boolean leap = dt.isLeapYear(); //false  
	    dt.getNumDaysInMonth(); //31  
	    dt.getStartOfMonth(); //2010-01-01, 00:00:00.000000000  
	    dt.getEndOfDay(); //2010-01-15, 23:59:59.999999999  
	    dt.format("YYYY-MM-DD"); //formats as '2010-01-15'  
	    dt.plusDays(30); //30 days after Jan 15  
	    dt.numDaysFrom(someDate); //returns an int  
	    dueDate.lt(someDate); //less-than  
	    dueDate.lteq(someDate); //less-than-or-equal-to  
	    //Although DateTime carries no TimeZone information internally, there are methods that take a TimeZone as a parameter :  
	    DateTime now = DateTime.now(someTimeZone);  
	    DateTime today = DateTime.today(someTimeZone);  
	    DateTime fromMilliseconds = DateTime.forInstant(31313121L, someTimeZone);  
	    birthday.isInFuture(someTimeZone);  
	    dt.changeTimeZone(fromOneTimeZone, toAnotherTimeZone);  
			 */
	}
	
	
	


}
