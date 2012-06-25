package cn.edu.hbcit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.*;
import cn.edu.hbcit.util.OperateProperties;

/**
 * �����ࣺ���ݿ������
 * @author Administrator
 *
 */
public class BaseDAO {
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	static OperateProperties op = new OperateProperties();//Properties�ļ�����
	protected final Logger log = Logger.getLogger(BaseDAO.class.getName());//log4j��־
	private static String mysqldriver = op.readValue("/db.properties", "driver");
	private static String mysqlURL = op.readValue("/db.properties", "dburl");
	/**
	 * �������ݿ�
	 * 
	 **/
	public Connection getConn(){
		try{
			//log.debug(mysqldriver);
			//log.debug(mysqlURL);
			Class.forName(mysqldriver);
			conn=DriverManager.getConnection(mysqlURL);
		}catch(Exception e){
			log.error("���ݿ����ӳ���"+e.getMessage());
			//e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * �ͷ���Դ
	 */
	
	public void closeAll(Connection con){
		try{
			con.close();
		}catch(Exception e1){
			log.error("���ݿ�رճ���"+e1.getMessage());
		}
		/*
		try{
			pstmt.close();
		}catch(Exception e2){
			log.error("���ݿ�رճ���"+e2.getMessage());
		}
		try{
			conn.close();
		}catch(Exception e3){
			log.error("���ݿ�رճ���"+e3.getMessage());
		}
		*/
	}
	
}
