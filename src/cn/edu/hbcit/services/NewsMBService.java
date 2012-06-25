package cn.edu.hbcit.services;



import java.util.List;

import cn.edu.hbcit.pojo.NewsMB;
import cn.edu.hbcit.dao.NewsDAO;
import cn.edu.hbcit.dao.NewsMBDAO;

public class NewsMBService {
	NewsMBDAO newsMBDAO=new NewsMBDAO();
	NewsDAO newsDAO =new NewsDAO();
	/*
	 * 功能：增加系统模板
	 * 参数: title\mid\icon:模板标题\模板内容\模板图标
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean addMB(String title,String mid,String icon)
	{
		return newsMBDAO.addMB(title, mid, icon);
	}
	/*
	 * 功能：更新系统模板
	 * 参数: id\title\mid\icon:模板id\模板标题\模板内容\模板图标
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean updateMB(int id,String title,String mid,String icon)
	{
		return newsMBDAO.updateMB(id, title, mid, icon);
	}
	/*
	 * 功能：检索全部系统模板
	 * 参数: id\title\mid\icon:模板id\模板标题\模板内容\模板图标
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public List<NewsMB> selectAllMB()
	{
		return newsMBDAO.selectAllMB();
	}
	/*
	 * 功能：检索全部系统模板(与上一个的区别就是：只保存模板的id和title)
	 * 参数: id\title\mid\icon:模板id\模板标题\模板内容\模板图标
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public List<NewsMB> selectAllMB(int type)
	{
		return newsMBDAO.selectAllMB(type);
	}
	//
	/*
	 * 功能：检索某一系统模板
	 * 参数: id\title\mid\icon:模板id\模板标题\模板内容\模板图标
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public List<NewsMB> selectOneMB(int id)
	{
		return newsMBDAO.selectOneMB(id);
	}
	/*
	 * 功能：检索某一系统模板对象
	 * 参数: id\title\mid\icon:模板id\模板标题\模板内容\模板图标
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public NewsMB selectOneMBObject(int id)
	{
		return newsMBDAO.selectOneMBObject(id);
	}
}
