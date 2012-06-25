package cn.edu.hbcit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.hbcit.pojo.NewsMB;
public class NewsMBDAO {
	BaseDAO baseDAO=new BaseDAO(); 
	ResultSet rs=null;
	Connection conn=null;
	NewsMB newsMB=null;
	/*
	 * ���ܣ�����ϵͳģ��
	 * ����: title\mid\icon:ģ�����\ģ������\ģ��ͼ��
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean addMB(String title,String mid,String icon)
	{
		boolean retuValue=false;
		//newsMB=new NewsMB();
		//newsMB.setIcon(icon);
		//newsMB.setMid(mid);
		//newsMB.setTitle(title);

		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("insert into newsmb(title,mid,icon) values('"+title.trim()+"','"+mid.trim()+"','"+icon.trim()+"')");
			retuValue=true;
		}catch(Exception ee){
			retuValue=false;
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�����ϵͳģ��
	 * ����: id\title\mid\icon:ģ��id\ģ�����\ģ������\ģ��ͼ��
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateMB(int id,String title,String mid,String icon)
	{
		boolean retuValue=false;
		
		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("update newsmb set title='"+title.trim()+"',mid='"+mid.trim()+"',icon='"+icon.trim()+"' where id="+String.valueOf(id));
			retuValue=true;
		}catch(Exception ee){
			retuValue=false;
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�����ȫ��ϵͳģ��
	 * ����: id\title\mid\icon:ģ��id\ģ�����\ģ������\ģ��ͼ��
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public List<NewsMB> selectAllMB()
	{
		List<NewsMB> mbList=new ArrayList<NewsMB>();
		


		try{
			conn=baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from  newsmb ");
			//
			while(rs.next())
			{
				newsMB=new NewsMB();
				newsMB.setId(rs.getInt("id"));
				newsMB.setIcon(rs.getString("icon"));
				newsMB.setMid(rs.getString("mid"));
				newsMB.setTitle(rs.getString("title"));
				mbList.add(newsMB);
			}
			
			
		}catch(Exception ee){
			mbList=null;
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);
		return mbList;
	}
	/*
	 * ���ܣ�����ȫ��ϵͳģ��(����һ����������ǣ�ֻ����ģ���id��title)
	 * ����: id\title\mid\icon:ģ��id\ģ�����\ģ������\ģ��ͼ��
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public List<NewsMB> selectAllMB(int type)
	{
		List<NewsMB> mbList=new ArrayList<NewsMB>();
		


		try{
			conn=baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from  newsmb ");
			//
			while(rs.next())
			{
				newsMB=new NewsMB();
				newsMB.setId(rs.getInt("id"));
				newsMB.setIcon(rs.getString("icon"));
				
				newsMB.setTitle(rs.getString("title"));
				mbList.add(newsMB);
			}
			
			
		}catch(Exception ee){
			mbList=null;
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);
		return mbList;
	}
	//
	/*
	 * ���ܣ�����ĳһϵͳģ��
	 * ����: id\title\mid\icon:ģ��id\ģ�����\ģ������\ģ��ͼ��
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public List<NewsMB> selectOneMB(int id)
	{
		List<NewsMB> mbList=new ArrayList<NewsMB>();
		try{
			conn=baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from  newsmb ");
			//
			while(rs.next())
			{
				if(rs.getInt("id")==id)
				{
					newsMB=new NewsMB();
					newsMB.setIcon(rs.getString("icon"));
					newsMB.setMid(rs.getString("mid"));
					newsMB.setTitle(rs.getString("title"));
					newsMB.setListmb(rs.getString("listmb"));
					mbList.add(newsMB);
					break;
				}
				
			}
			
			
		}catch(Exception ee){
			mbList=null;
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);
		return mbList;
	}
	//
	/*
	 * ���ܣ�����ĳһϵͳģ�����
	 * ����: id\title\mid\icon:ģ��id\ģ�����\ģ������\ģ��ͼ��
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public NewsMB selectOneMBObject(int id)
	{
		NewsMB mb=new NewsMB();
		try{
			conn=baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from  newsmb ");
			//
			while(rs.next())
			{
				if(rs.getInt("id")==id)
				{
					mb.setId(id);
					mb.setIcon(rs.getString("icon"));
					mb.setMid(rs.getString("mid"));
					mb.setTitle(rs.getString("title"));
					break;
				}
				
			}
			
			
		}catch(Exception ee){
			mb=null;
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);
		return mb;
	}
}
