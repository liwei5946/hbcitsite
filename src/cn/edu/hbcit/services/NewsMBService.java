package cn.edu.hbcit.services;



import java.util.List;

import cn.edu.hbcit.pojo.NewsMB;
import cn.edu.hbcit.dao.NewsDAO;
import cn.edu.hbcit.dao.NewsMBDAO;

public class NewsMBService {
	NewsMBDAO newsMBDAO=new NewsMBDAO();
	NewsDAO newsDAO =new NewsDAO();
	/*
	 * ���ܣ�����ϵͳģ��
	 * ����: title\mid\icon:ģ�����\ģ������\ģ��ͼ��
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean addMB(String title,String mid,String icon)
	{
		return newsMBDAO.addMB(title, mid, icon);
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
		return newsMBDAO.updateMB(id, title, mid, icon);
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
		return newsMBDAO.selectAllMB();
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
		return newsMBDAO.selectAllMB(type);
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
		return newsMBDAO.selectOneMB(id);
	}
	/*
	 * ���ܣ�����ĳһϵͳģ�����
	 * ����: id\title\mid\icon:ģ��id\ģ�����\ģ������\ģ��ͼ��
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public NewsMB selectOneMBObject(int id)
	{
		return newsMBDAO.selectOneMBObject(id);
	}
}
