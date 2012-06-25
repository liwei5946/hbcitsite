package cn.edu.hbcit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.hbcit.pojo.DynamicSetLm;

public class DynamicSetLmDAO {
	BaseDAO baseDAO = new BaseDAO();
	ResultSet rs = null;
	Connection conn = null;

	/*
	 * ���ܣ���Ӱ������ 
	 * �����������ݿ����û�pojo˵�� 
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public boolean addDynamicSetLm(String indexLmNo, String lm, String lm2,
			String lm3, String indexLmPic, String indexLmTitle,
			String indexLmTitleStyle,String notes) {
		boolean returnVlue = false;
		try {
			conn = baseDAO.getConn();
			if(indexLmNo.length()==0 && lm.length()==0 && lm2.length()==0 && notes.length()==0)
				conn.createStatement()
				.executeUpdate("insert into DynamicSetLm(indexLmNo,lm,lm2,lm3,indexLmPic,indexLmTitle,indexLmTitleStyle,notes) values('','','','','','','','')");
			else
				conn.createStatement()
					.executeUpdate(
							"insert into DynamicSetLm(indexLmNo,lm,lm2,lm3,indexLmPic,indexLmTitle,indexLmTitleStyle,notes) values('"
									+ indexLmNo
									+ "','"
									+ lm
									+ "','"
									+ lm2
									+ "','"
									+ lm3
									+ "','"
									+ indexLmPic
									+ "','"
									+ indexLmTitle
									+ "','"
									+ indexLmNo
									+ "','"
									+ indexLmTitleStyle.trim() + "','"
									+ notes + "')");
			//����������Ϊ����id��ͬ
			rs=conn.createStatement().executeQuery("select max(id) from DynamicSetLm");
			int indexLmNo1=0;
			//
			if(rs.next())
				indexLmNo1=rs.getInt(1);
			//
			conn.createStatement()
			.executeUpdate("update DynamicSetLm set indexLmNo="+indexLmNo1+" where id="+indexLmNo1);
			//
			baseDAO.closeAll(conn);
			returnVlue = true;

		} catch (Exception ee) {
			returnVlue = false;
			ee.printStackTrace();
		}

		return returnVlue;
	}
	/*
	 * ���ܣ��޸İ������ 
	 * �����������ݿ����û�pojo˵�� 
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public boolean updateDynamicSetLm(int id,String indexLmNo, String lm, String lm2,
			String lm3, String indexLmPic, String indexLmTitle,
			String indexLmTitleStyle,String notes) {
		boolean returnVlue = false;
		try {
			conn = baseDAO.getConn();
			conn.createStatement()
					.executeUpdate(
							"update DynamicSetLm set indexLmNo='"+indexLmNo.trim()+"',lm='"+lm.trim()+"',lm2='"+lm2.trim()+"',lm3='"+lm3.trim()+"',indexLmTitle='"+indexLmTitle.trim()+"',indexLmTitleStyle='"+indexLmTitleStyle.trim()+"',notes='"+notes.trim()+"' where id="+String.valueOf(id));

			baseDAO.closeAll(conn);
			returnVlue = true;

		} catch (Exception ee) {
			returnVlue = false;
			ee.printStackTrace();
		}

		return returnVlue;
	}
	/*
	 * ���ܣ��������еİ������ 
	 * ������
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public List<DynamicSetLm> selectAllDynamicSetLm() {
		
		List<DynamicSetLm> dysl=new ArrayList<DynamicSetLm>();
		DynamicSetLm dynamicSetLm ;
		try {
			conn = baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from DynamicSetLm ");
			while(rs.next())
			{
				dynamicSetLm=new DynamicSetLm();
				dynamicSetLm.setId(rs.getInt("id"));
				dynamicSetLm.setIndexLmNo(rs.getString("indexLmNo"));
				dynamicSetLm.setLm(rs.getString("lm"));
				dynamicSetLm.setLm2(rs.getString("lm2"));
				dynamicSetLm.setLm3(rs.getString("lm3"));
				dynamicSetLm.setIndexLmPic(rs.getString("indexLmPic"));
				dynamicSetLm.setIndexLmTitle(rs.getString("indexLmTitle"));
				dynamicSetLm.setIndexLmTitleStyle(rs.getString("indexLmTitleStyle"));
				dynamicSetLm.setNotes(rs.getString("notes"));
				//
				dysl.add(dynamicSetLm);
			}
			baseDAO.closeAll(conn);

		} catch (Exception ee) {
			dysl=null;
			ee.printStackTrace();
		}

		return dysl;
	}
	/*
	 * ���ܣ��������еİ��˵�� 
	 * ������
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public List<String> selectAllnotes() {
		
		List<String> dysnotes=new ArrayList<String>();
		try {
			conn = baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from DynamicSetLm ");
			while(rs.next())
			{
				dysnotes.add(rs.getString("notes").trim());
			}
			baseDAO.closeAll(conn);

		} catch (Exception ee) {
			dysnotes=null;
			ee.printStackTrace();
		}

		return dysnotes;
	}
	/*
	 * ���ܣ�����ĳһ�İ������ 
	 * ������
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public DynamicSetLm selectOneDynamicSetLm(int indexLmNo) {
			
		DynamicSetLm dynamicSetLm =null;
		try {
			conn = baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from DynamicSetLm where id="+String.valueOf(indexLmNo));
			while(rs.next())
			{
				dynamicSetLm=new DynamicSetLm();
				dynamicSetLm.setIndexLmNo(rs.getString("indexLmNo"));
				dynamicSetLm.setLm(rs.getString("lm"));
				dynamicSetLm.setLm2(rs.getString("lm2"));
				dynamicSetLm.setLm3(rs.getString("lm3"));
				dynamicSetLm.setIndexLmPic(rs.getString("indexLmPic"));
				dynamicSetLm.setIndexLmTitle(rs.getString("indexLmTitle"));
				dynamicSetLm.setIndexLmTitleStyle(rs.getString("indexLmTitleStyle"));
				dynamicSetLm.setNotes(rs.getString("notes"));
				//
			}
			baseDAO.closeAll(conn);

		} catch (Exception ee) {
			dynamicSetLm =null;
			ee.printStackTrace();
		}

		return dynamicSetLm;
	}
	/*
	 * ���ܣ�����ĳһ�İ������ 
	 * ������notes---˵��
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public DynamicSetLm selectOneDynamicSetLm(String notes) {
			
		DynamicSetLm dynamicSetLm =null;
		try {
			conn = baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from DynamicSetLm where notes='"+notes+"'");
			while(rs.next())
			{
				dynamicSetLm=new DynamicSetLm();
				dynamicSetLm.setIndexLmNo(rs.getString("indexLmNo"));
				dynamicSetLm.setLm(rs.getString("lm"));
				dynamicSetLm.setLm2(rs.getString("lm2"));
				dynamicSetLm.setLm3(rs.getString("lm3"));
				dynamicSetLm.setIndexLmPic(rs.getString("indexLmPic"));
				dynamicSetLm.setIndexLmTitle(rs.getString("indexLmTitle"));
				dynamicSetLm.setIndexLmTitleStyle(rs.getString("indexLmTitleStyle"));
				dynamicSetLm.setNotes(rs.getString("notes"));
				//
			}
			baseDAO.closeAll(conn);

		} catch (Exception ee) {
			dynamicSetLm =null;
			ee.printStackTrace();
		}

		return dynamicSetLm;
	}
	/*
	 * ���ܣ�����ĳһ�İ��洢���ŵ�ǰ������Ŀ
	 * ������
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public String selectOneDynamicLmType(int type) {
		String retuValue="";	
		try {
			conn = baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from DynamicSetLm where id="+String.valueOf(type));
			while(rs.next())
			{
				retuValue=rs.getString("lm").trim()+","+rs.getString("lm2").trim()+","+rs.getString("lm3").trim();
			}
			baseDAO.closeAll(conn);

		} catch (Exception ee) {
			ee.printStackTrace();
		}

		return retuValue;
	}
	//
	/*
	 * ���ܣ�����ĳһ��鶥��ͼƬ
	 * ������indexLmPic:ͼƬ���ƣ�indexLmNo������
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public boolean updateOneDynamicLmPic(String indexLmPic,String indexLmNo) {
		boolean retuValue=false;	
		try {
			conn = baseDAO.getConn();
			conn.createStatement().executeUpdate("update DynamicSetLm  set indexLmPic='"+indexLmPic.trim()+"'where indexLmNo='"+indexLmNo+"'");
			
			baseDAO.closeAll(conn);
			retuValue=true;
		} catch (Exception ee) {
			ee.printStackTrace();
			retuValue=false;
		}

		return retuValue;
	}
	//
	/*
	 * ���ܣ�ɾ��һ�����
	 * ������indexLmPic:ͼƬ���ƣ�indexLmNo������
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public boolean deleteOneDynamicLm( int id,String indexLmNo) {
		boolean retuValue=false;	
		try {
			conn = baseDAO.getConn();
			conn.createStatement().executeUpdate("delete from  DynamicSetLm  where id="+id);
			
			baseDAO.closeAll(conn);
			retuValue=true;
		} catch (Exception ee) {
			ee.printStackTrace();
			retuValue=false;
		}

		return retuValue;
	}
	//
	/*
	 * ���ܣ�����ĳһ��Ŀ�Ƿ������ڰ����
	 * ������indexLmPic:ͼƬ���ƣ�indexLmNo������
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public boolean DynamicContinesLm( String lm, String lm2,String lm3) {
		boolean retuValue=false;	
		try {
			conn = baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from  DynamicSetLm  where lm='"+lm.trim()+"' and lm2='"+lm2.trim()+"' and lm3='"+lm3.trim()+"'");
			if(rs.next())
				retuValue=true;
			baseDAO.closeAll(conn);
		} catch (Exception ee) {
			ee.printStackTrace();
			retuValue=false;
		}

		return retuValue;
	}
}
