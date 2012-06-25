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
	 * 功能：添加板块设置 
	 * 参数：看数据库设置或pojo说明 
	 * 编写：石彦杰 2011-9-28
	 * 修改：
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
			//将版块号设置为与其id相同
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
	 * 功能：修改板块设置 
	 * 参数：看数据库设置或pojo说明 
	 * 编写：石彦杰 2011-9-28
	 * 修改：
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
	 * 功能：检索所有的板块设置 
	 * 参数：
	 * 编写：石彦杰 2011-9-28
	 * 修改：
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
	 * 功能：检索所有的板块说明 
	 * 参数：
	 * 编写：石彦杰 2011-9-28
	 * 修改：
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
	 * 功能：检索某一的板块设置 
	 * 参数：
	 * 编写：石彦杰 2011-9-28
	 * 修改：
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
	 * 功能：检索某一的板块设置 
	 * 参数：notes---说明
	 * 编写：石彦杰 2011-9-28
	 * 修改：
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
	 * 功能：检索某一的板块存储新闻的前三个栏目
	 * 参数：
	 * 编写：石彦杰 2011-9-28
	 * 修改：
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
	 * 功能：更改某一板块顶部图片
	 * 参数：indexLmPic:图片名称；indexLmNo：版块号
	 * 编写：石彦杰 2011-9-28
	 * 修改：
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
	 * 功能：删除一个板块
	 * 参数：indexLmPic:图片名称；indexLmNo：版块号
	 * 编写：石彦杰 2011-9-28
	 * 修改：
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
	 * 功能：检索某一栏目是否设置在板块中
	 * 参数：indexLmPic:图片名称；indexLmNo：版块号
	 * 编写：石彦杰 2011-9-28
	 * 修改：
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
