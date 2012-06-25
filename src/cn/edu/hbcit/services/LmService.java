package cn.edu.hbcit.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.edu.hbcit.dao.LmDAO;
import cn.edu.hbcit.dao.NewsDAO;
import cn.edu.hbcit.dao.UserDAO;

import cn.edu.hbcit.pojo.Lm;
import cn.edu.hbcit.util.UserRight;

public class LmService {
	LmDAO lmDAO=new LmDAO();
	NewsDAO newsDAO=new NewsDAO();
	UserDAO userDAO=new UserDAO();
	
	/*
	 * 功能：返回栏目,
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public Lm selectLmObject(int lmid) {
		return lmDAO.selectLmObject(lmid);
	}// 传给新闻增加页面的用户的权限拉栏目，便于新闻发布者直接使用（含有栏目的梯次）
	/*parames:adminRight  用户的权限id序列
	 * 功能：成功与否,
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public List<UserRight> getUserRightLm(String adminRight) {
		//获取用户权限
		List<UserRight> rightList=new ArrayList<UserRight>();
		try
		{
			String[] dbRight=adminRight.split(",");
			for(int i=0;i<dbRight.length;i++)
			{
				UserRight userRight=new UserRight();
				String rightName=lmDAO.selectLmNameById(Integer.parseInt(dbRight[i]));
				int level=lmDAO.selectCurLmLevel(Integer.parseInt(dbRight[i]));
				int pid=lmDAO.selectCurLmPId(Integer.parseInt(dbRight[i]));
				//
				userRight.setRightName(rightName+"("+String.valueOf(level)+"级)");
				userRight.setRightID(Integer.parseInt(dbRight[i]));//本级id
				userRight.setLmLevel(level);//级别
				userRight.setpRightID(pid);//父级id
				//
				rightList.add(userRight);
			}
		}catch(Exception e)
		{
			rightList=null;
			e.printStackTrace();
		}
		//
		return rightList;
	}
	
	// 新闻增加页面的用户新闻发布后，得到选择的lm的id序列（一级栏目返回格式：1级栏目号,0,0;   二级栏目返回格式：1级栏目号,2级栏目号,0;   三级栏目返回格式：1级栏目号,2级栏目号,3级栏目号;）
	/*parames:adminRight  用户的权限id序列
	 * 功能：成功与否,
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public String getSelectLmId(String lm) {
		String retuString="";		
		try
		{
			String  lmLevel=lm.trim().substring(lm.length()-4);//主要是取出右侧的级别，如：(1级)
			//
			if(lmLevel.trim().equals("(1级)"))//得到栏目号即可
			{
				String lmName=lm.trim().substring(0,lm.length()-4);
				int id=lmDAO.getLmId(lmName, 1, true);
				retuString=String.valueOf(id)+",0,0";
			}
			//
			if(lmLevel.trim().equals("(2级)"))//得到栏目号即可
			{
				String lmName=lm.trim().substring(0,lm.length()-4);
				int id=lmDAO.getLmId(lmName, 2, true);
				int pid=lmDAO.selectCurLmPId(id);
				retuString=String.valueOf(pid)+","+String.valueOf(id)+",0";
			}
			//
			if(lmLevel.trim().equals("(3级)"))//得到栏目号即可
			{
				String lmName=lm.trim().substring(0,lm.length()-4);
				int id=lmDAO.getLmId(lmName, 1, true);
				int pid=lmDAO.selectCurLmPId(id);
				int ppid=lmDAO.selectCurLmPId(pid);
				retuString=String.valueOf(ppid)+","+String.valueOf(pid)+","+String.valueOf(id);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//
		return retuString;
	}
	// 新闻修改页面的用户新闻发布后，
	/*parames:lmIds  新闻的id串,用逗号分割，如：69,70,0
	 * 功能：返回栏目名并带有(1级)或(2级)或(3级)
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public String getLmName(String lmIds) {
		String retuString="";	
		if(lmIds.trim().length()==0)
			return null;
		String[] lmid=lmIds.split(",");
		try
		{
			//
			if(!lmid[2].trim().equals("0"))//得到栏目号即可
			{
				retuString=lmDAO.selectLmNameById(Integer.parseInt(lmid[2]));
				retuString=retuString.trim()+"(3级)";
			}else
				if(!lmid[1].trim().equals("0"))//得到栏目号即可
				{
					retuString=lmDAO.selectLmNameById(Integer.parseInt(lmid[1]));
					retuString=retuString.trim()+"(2级)";
				}
				else
				{
					retuString=lmDAO.selectLmNameById(Integer.parseInt(lmid[0]));
					retuString=retuString.trim()+"(1级)";
				}	
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//
		return retuString;
	}
	// 合并栏目
	/*parames:lmUnion1，lmUnion2：将编号为lmUnion1栏目合并到编号为lmUnion2的栏目中
	 * 功能：成功与否,
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public boolean lmUnion(int lmUnion1,int lmUnion2) {
		return lmDAO.lmUnion(lmUnion1, lmUnion2);
	}
	
	//根据父栏目名保存子栏目
	/*功能：增加栏目
	 * 参数：lmName--增加的栏目名称,pLmName---栏目的父栏目名称，如果为空，则是根栏目
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public boolean addLmByRootName(String lmName,String pLmName )
	{
		return lmDAO.addLmByRootName(lmName, pLmName);
	}
	// 根据父栏目id保存子栏目
	/* 
	 * 功能：增加栏目 参数：lmName--增加的栏目名称;pLmId---栏目的父栏目id，如果为0，则是根栏目;LmLevel--栏目级别1：root;2-child;3-leaf 
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public boolean addLmByRootId(String lmName, int pLmId,int LmLevel) {
		return lmDAO.addLmByRootId(lmName, pLmId, LmLevel);
	}
	//检索栏目
	/*功能：返回所有栏目的所有信息(含有文章数量统计)
	 *参数：
	 *编写：石彦杰 2011-9-28
	 *修改：
	 */
	public List<Lm> selectAllLm()
	{
		
		List<Lm> lmList=lmDAO.selectAllLm();
		//进行整理，并重新打包，以使pojo含有相应的数量统计
		List<Lm> reLmList=new ArrayList<Lm>();
		Iterator<Lm> lmIt=lmList.iterator();
		while(lmIt.hasNext())
		{
			Lm lm=(Lm)lmIt.next();
			String lmLevel="";
			String lmLevel1=lm.getLm();
			String lmLevel2=lm.getLm2();
			String lmLevel3=lm.getLm3();
			if(lmLevel1!="")
				lmLevel="1";
			else
				if(lmLevel2!="")
					lmLevel="2";
				else
					if(lmLevel3!="")
						lmLevel="3";
			//
			int documentsCount=newsDAO.countLm(lmLevel, String.valueOf(lm.getId()));
			lm.setDocumentsCount(documentsCount);//已经含有了数量统计
			//
			reLmList.add(lm);
		}
		//将含有数量统计的pojo传递
		return reLmList;
	}
	// 检索栏目
	/*parames:lmLevel:=0 root;lmLevel=1 lm2;lmLevel=2 lm3
	 * 功能：返回栏目id参数：lmName--栏目名称,
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public String selectLmNameById(int id,int lmLevel) {
		return lmDAO.selectLmNameById(id, lmLevel);
	}
	// 检索栏目
	/*parames:lmLevel:=0 root;lmLevel=1 lm2;lmLevel=2 lm3
	 * 功能：返回栏目id参数：lmName--栏目名称,
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public String selectLmNameById(int id) {
		return lmDAO.selectLmNameById(id);
	}
	// 以名和结构层次找栏目id
	/*
	 * 功能：返回栏目id
	 * 参数：lmName--栏目名称,optionType:true\false:connect(close)\don‘t connect(close) DB
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public int getLmId(String lmName,int lmLevel,boolean optionType) {
		return lmDAO.getLmId(lmName, lmLevel, optionType);
	}
	//检索栏目
	/*功能：返回所有父栏目名称
	 *参数：
	 *编写：石彦杰 2011-9-28
	 *修改：
	 */
	public List<String> selectAllParentLmName()
	{
		return lmDAO.selectAllParentLmName();
	}	
	//检索栏目
	/*功能：返回所有子栏目名称
	 *参数：
	 *编写：石彦杰 2011-9-28
	 *修改：
	 */
	public List<String> selectAllSubLmName()
	{
		return lmDAO.selectAllSubLmName();
	}	
	//检索栏目
	/*功能：返回所有某一父栏目所有的子栏目名称
	 *参数：pLmName---栏目的父栏目名称，
	 *编写：石彦杰 2011-9-28
	 *修改：
	 */
	public List<String> selectAllSubLmNameForPLm(String pLmName,int lmLevel)
	{ 
		return lmDAO.selectAllSubLmNameForPLm(pLmName, lmLevel);
	}	
	//检索栏目
	/*功能：返回所有的栏目名称及其id
	 *参数：
	 *编写：石彦杰 2011-9-28
	 *修改：
	 */
	public Map<Integer,String> selectAllParentLmNameAndKey()
	{ 
		return lmDAO.selectAllParentLmNameAndKey();
	}
	//检索栏目
	/*功能：返回所有子栏目名称及其id
	 *参数：
	 *编写：石彦杰 2011-9-28
	 *修改：
	 */
	public Map<Integer,String> selectAllSubLmNameAndKey()
	{ 
		return lmDAO.selectAllSubLmNameAndKey();
	}
	// 删除根栏目
	/*
	 * 功能：删除某一主栏目参数：编写：石彦杰 2011-9-28修改：
	 */
	public boolean deleteRootLmById(int id) {
		return lmDAO.deleteRootLmById(id);
	}
	//删除栏目
	/*功能：删除某一主栏目
	 *参数：
	 *编写：石彦杰 2011-9-28
	 *修改：
	 */
	public boolean deleteRootLmByName(String pName)
	{ 
		return lmDAO.deleteRootLmByName(pName);
	}	
	//删除栏目
	/*功能：删除某一子栏目
	 *参数：
	 *编写：石彦杰 2011-9-28
	 *修改：
	 */
	public boolean deleteCurrentSubLm(String subName)
	{ 
		return lmDAO.deleteCurrentSubLm(subName);
	}	
	//删除所有栏目
	/*功能：删除所有栏目
	 *参数：
	 *编写：石彦杰 2011-9-28
	 *修改：
	 */
	public boolean clear()
	{ 
		return lmDAO.clear();
	}	
	//更新栏目
	/*功能：更新某一栏目
	 *参数：
	 *编写：石彦杰 2011-9-28
	 *修改：
	 */
	public boolean updateCurrentLm(int id,String updateValue,int lmLevel)
	{ 
		return lmDAO.updateCurrentLm(id, updateValue,lmLevel);
	}	
	// 更新栏目模板
	/*
	 * 功能：更新某一栏目参数：编写：石彦杰 2011-9-28修改：
	 */
	public boolean updateLmMb(int id, String lmMb) {
		return lmDAO.updateLmMb(id, lmMb);
	}
	/*  检索栏目
	 * 功能：返回某一父栏目所有的子栏目名称
	 * 参数：id,lmLevel:栏目号和栏目级别
	 */
	public Map<Integer, String> selectAllSubLmNameAndKey(int id,int lmLevel) {
		return lmDAO.selectAllSubLmNameAndKey(id, lmLevel);
	}
	// 检索栏目
	/*
	 * 功能：返回所有栏目id：单为admin所写，以使admin获取所有栏目的id
	 */
	public String selectAllLmIdForAdmin() {
		return lmDAO.selectAllLmIdForAdmin();
	}
}
