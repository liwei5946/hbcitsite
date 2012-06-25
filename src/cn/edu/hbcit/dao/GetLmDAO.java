package cn.edu.hbcit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

/*
 * ���ܣ�����������Ŀ(����Ŀ)���ơ���Ŀ������Ŀ��ID
 * ��д��ʯ���  2011-9-28
 * �޸ģ�
 * 
 */
public class GetLmDAO {
	/*
	 * ��������getLm
	 * ���ܣ������ݿ��л�ȡָ�����������������ĿID����Ŀ��
	 * �������ͣ�HashMap
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
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
	 * ��������getLm2
	 * ���ܣ������ݿ��л�ȡָ�����������������Ŀ2����ĿID����Ŀ��
	 * ������int itemId---����ִ�е�select ���
	 * ����ֵ��listSubItem;
	 * �������ͣ�List
	 * ��д�������� 2011-9-28
	 * �޸ģ�
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
