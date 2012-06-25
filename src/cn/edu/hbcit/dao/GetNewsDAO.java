package cn.edu.hbcit.dao;



import cn.edu.hbcit.dao.BaseDAO;
import cn.edu.hbcit.util.IDatabaseUtil;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
/*
 * ���ܣ��������ж�̬����
 * ��д��ʯ���  2011-9-28
 * �޸ģ�
 * 
 */
public class GetNewsDAO implements IDatabaseUtil{
	BaseDAO baseDAO=new BaseDAO(); 
	/*
	 * ��������getDatas
	 * ���ܣ������ݿ��л�ȡָ�����������������������(non-Javadoc)
	 * ������String Sql---����ִ�е�select ���
	 * ����ֵ��ResultSet;
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
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
	 * ��������executeNonQuery
	 * ���ܣ�ִ�����ݿ�Ĳ��롢ɾ��������(non-Javadoc)
	 * ������String Sql---����ִ�е�insert/delete/update ���
	 * ����ֵ������---true:success��---false:error;
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
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
