package cn.edu.hbcit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.*;
import cn.edu.hbcit.util.OperateProperties;

/**
 * 公共类：数据库访问类
 * @author Administrator
 *
 */
public class BaseDAO {
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	static OperateProperties op = new OperateProperties();//Properties文件操作
	protected final Logger log = Logger.getLogger(BaseDAO.class.getName());//log4j日志
	private static String mysqldriver = op.readValue("/db.properties", "driver");
	private static String mysqlURL = op.readValue("/db.properties", "dburl");
	/**
	 * 连接数据库
	 * 
	 **/
	public Connection getConn(){
		try{
			//log.debug(mysqldriver);
			//log.debug(mysqlURL);
			Class.forName(mysqldriver);
			conn=DriverManager.getConnection(mysqlURL);
		}catch(Exception e){
			log.error("数据库连接出错："+e.getMessage());
			//e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 释放资源
	 */
	
	public void closeAll(Connection con){
		try{
			con.close();
		}catch(Exception e1){
			log.error("数据库关闭出错："+e1.getMessage());
		}
		/*
		try{
			pstmt.close();
		}catch(Exception e2){
			log.error("数据库关闭出错："+e2.getMessage());
		}
		try{
			conn.close();
		}catch(Exception e3){
			log.error("数据库关闭出错："+e3.getMessage());
		}
		*/
	}
	
}
