package cn.edu.hbcit.services;



import java.sql.ResultSet;
import java.util.HashMap;


import cn.edu.hbcit.dao.GetLmDAO;
import cn.edu.hbcit.dao.GetNewsDAO;

public class GetNewsService {
	GetNewsDAO getNewsDAO=new GetNewsDAO();
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
	
	 /* ��������getLm
	 * ���ܣ������ݿ��л�ȡָ�����������������Ŀ1����ĿID����Ŀ��
	 * ����ֵ��listItem;
	 * �������ͣ�List
	 * ��д�������� 2011-9-28
	 * �޸ģ�
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
	 * ��������getLm2
	 * ���ܣ������ݿ��л�ȡָ�����������������Ŀ2����ĿID����Ŀ��
	 * ������int itemId---����ִ�е�select ���
	 * ����ֵ��listSubItem;
	 * �������ͣ�List
	 * ��д�������� 2011-9-28
	 * �޸ģ�
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
