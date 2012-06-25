package cn.edu.hbcit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

/*
 * 功能：返回所有栏目(子栏目)名称、栏目（子栏目）ID
 * 编写：石彦杰  2011-9-28
 * 修改：
 * 
 */
public class GetLmDAO {
	/*
	 * 方法名：getLm
	 * 功能：从数据库中获取指定表的满足条件的栏目ID和栏目名
	 * 返回类型：HashMap
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public HashMap<Integer,String> getLm(){
		HashMap<Integer,String> mapItem=new HashMap<Integer,String>();
		
		BaseDAO ba=new BaseDAO();
		ResultSet rs=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="select id,lm from lm where lm is not null ";
		
		try{
			conn=ba.getConn();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				int id=rs.getInt("id");
				String lm=rs.getString("lm");
				mapItem.put(id, lm);
			}

			ba.closeAll(conn);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapItem;
	}
	
	/*
	 * 方法名：getLm2
	 * 功能：从数据库中获取指定表的满足条件的栏目2的栏目ID和栏目名
	 * 参数：int itemId---传入执行的select 语句
	 * 返回值：listSubItem;
	 * 返回类型：List
	 * 编写：王春林 2011-9-28
	 * 修改：
	 */
	public HashMap<Integer,String> getLm2(int itemId){
	
		HashMap<Integer,String> mapSubItem=new HashMap<Integer,String>();
		BaseDAO ba=new BaseDAO();
		ResultSet rs=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="select id,lm from lm where lmid='"+String.valueOf(itemId).trim()+"' order by id";
		
		try{
			conn=ba.getConn();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				int id=rs.getInt("id");
				String subItemName=rs.getString("lm");
				mapSubItem.put(id, subItemName);
			}
			ba.closeAll(conn);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapSubItem;
	}
}
