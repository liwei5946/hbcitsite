package cn.edu.hbcit.dao;



import cn.edu.hbcit.dao.BaseDAO;
import cn.edu.hbcit.util.IDatabaseUtil;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
/*
 * 功能：返回所有动态新闻
 * 编写：石彦杰  2011-9-28
 * 修改：
 * 
 */
public class GetNewsDAO implements IDatabaseUtil{
	BaseDAO baseDAO=new BaseDAO(); 
	/*
	 * 方法名：getDatas
	 * 功能：从数据库中获取指定表的满足条件的所有数据(non-Javadoc)
	 * 参数：String Sql---传入执行的select 语句
	 * 返回值：ResultSet;
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public ResultSet getDatas(String Sql)
	{
		ResultSet rs=null;
		Statement st=null;
		//
		Connection con=baseDAO.getConn();
		try
		{
			st=con.createStatement();
			
			rs=st.executeQuery(Sql);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//baseDAO.closeAll(con);
		
		return rs;
	}
	/*
	 * 方法名：executeNonQuery
	 * 功能：执行数据库的插入、删除、更新(non-Javadoc)
	 * 参数：String Sql---传入执行的insert/delete/update 语句
	 * 返回值：布尔---true:success；---false:error;
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public boolean executeNonQuery(String Sql)
	{
		boolean resultValue=false;
		Connection con=baseDAO.getConn();
		try
		{
			(con.createStatement()).execute(Sql);
			resultValue=true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		baseDAO.closeAll(con);
	
		return resultValue;
	}
}
