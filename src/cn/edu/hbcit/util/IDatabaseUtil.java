package cn.edu.hbcit.util;

import java.sql.ResultSet;

/*
 * �ӿڣ�IDatabaseUtil
 * ���ܣ�����DAO�ĸ��࣬ͳһDAO�Ķ���
 * ��д��ʯ��ܣ�2011-9-28
 * �޸ģ�
 */

public interface IDatabaseUtil {
	//��ȡ����
	public ResultSet getDatas(String Sql);
	
	//���insert/delete/update
	public boolean executeNonQuery(String Sql);
	
}
