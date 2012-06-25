package cn.edu.hbcit.services;



import java.sql.ResultSet;
import java.util.HashMap;


import cn.edu.hbcit.dao.GetLmDAO;
import cn.edu.hbcit.dao.GetNewsDAO;

public class GetNewsService {
	GetNewsDAO getNewsDAO=new GetNewsDAO();
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
		try
		{
			rs=getNewsDAO.getDatas(Sql);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//
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
		try
		{
			getNewsDAO.executeNonQuery(Sql);
			resultValue=true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return resultValue;
	}
	
	 /* 方法名：getLm
	 * 功能：从数据库中获取指定表的满足条件的栏目1的栏目ID和栏目名
	 * 返回值：listItem;
	 * 返回类型：List
	 * 编写：王春林 2011-9-28
	 * 修改：
	 */
	GetLmDAO lm=new GetLmDAO(); 
	public HashMap getLm(){
		HashMap listItem=new HashMap();
		try{
			listItem=lm.getLm();
		}catch(Exception e){
			e.printStackTrace();
		}
		return listItem;
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
	public HashMap getLm2(int itemId){
		
		HashMap listSubItem=new HashMap();
		
		try{
			listSubItem=lm.getLm2(itemId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return listSubItem;
	}

}
