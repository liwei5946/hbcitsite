package cn.edu.hbcit.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import cn.edu.hbcit.dao.LmDAO;
import cn.edu.hbcit.dao.NewsDAO;
import cn.edu.hbcit.dao.NewsMBDAO;
import cn.edu.hbcit.dao.UserDAO;
import cn.edu.hbcit.pojo.Admin;
import cn.edu.hbcit.pojo.Lm;
import cn.edu.hbcit.pojo.News;
import cn.edu.hbcit.pojo.NewsMB;
import cn.edu.hbcit.pojo.PageBean;
import cn.edu.hbcit.util.CountNews;
import cn.edu.hbcit.util.Pagination;
import cn.edu.hbcit.util.UtilTools;


public class NewsService {
	
	NewsDAO newsDAO = new NewsDAO();
	LmDAO lmDAO=new LmDAO();
	NewsMBDAO newsMBDAO=new NewsMBDAO();
	UserDAO userDAO=new UserDAO();
	CountNews countNews=null;
	Admin admin=null;
	//LmService lmService=new LmService();
	/**
	 * 统计栏目数量
	 * @param lmLevel 栏目级别1,2,3
	 * @param lmId 栏目号
	 * @return
	 */
	public int countLm(String lmLevel, String lmId)
	{ 
		return newsDAO.countLm(lmLevel, lmId);
	}	
	
	/**
	 * 新闻修改页面查询
	 * @param userName
	 * @param cPage
	 * @param perPage
	 * @return
	 */
	public ArrayList SelectAdminNewsList(String userName, String cPage, String perPage, String right,String title,String content,String contentType,String LaiYuan,String stime,String etime){
		return newsDAO.selectAdminNewsList(userName, cPage, perPage, right, title, content, contentType, LaiYuan, stime, etime);
	}
	/**
	 * 主页显示更多新闻提取
	 * @param userName 查询人的用户名
	 * @param cPage 当前页码
	 * @param perPage 每页显示记录的数量
	 * @return
	 * 石彦杰
	 */
	@SuppressWarnings("unchecked")
	public List<News> selectMoreNewsList( String cPage, String perPage, String lm){
		
		return newsDAO.selectMoreNewsList( cPage, perPage, lm);
	}
	/**
	 * 发布新闻数量统计
	 * @param bumen 部门名称
	 * @param startTime endTime 时段
	 * @return
	 *   syj
	 */
	@SuppressWarnings("unchecked")
	public int countNews(String bumen,String startTime,String endTime){
		return newsDAO.countNews(bumen, startTime, endTime);		
	}
	/**
	 * 得到部门及其时段内发布新闻的数量
	 * @param bumen 部门名称
	 * @param startTime endTime 时段
	 * @return
	 *   syj  countNews
	 */
	@SuppressWarnings("unchecked")  
	public List<CountNews> countBumenNews(String startTime,String endTime){
		List<CountNews> bumenNewList=new ArrayList<CountNews>();
		Map<String,Integer> bumenNewMap=new  HashMap<String,Integer>();
		//
		
		List<Admin> list=userDAO.selectAllUsers();
		Iterator<Admin> userIt=list.iterator();
		while(userIt.hasNext())
		{
			admin=(Admin)userIt.next();
			/*
			countNews=new CountNews();
			countNews.setUnit(admin.getBumen());
			countNews.setEndTime(endTime);
			countNews.setStartTime(startTime);
			*/
			if(!admin.getUser().trim().equals("admin") && admin.getBumen().trim().length()!=0)
			{
				//countNews.setNumbs(newsDAO.countNews(admin.getBumen(), startTime, endTime));
				bumenNewMap.put(admin.getBumen(), newsDAO.countNews(admin.getBumen(), startTime, endTime));
				//bumenNewList.add(countNews);
			}
		}
		List<Map.Entry<String, Integer>> newlist = new ArrayList<Map.Entry<String, Integer>>(bumenNewMap.entrySet());
		Collections.sort(newlist, new Comparator<Map.Entry<String, Integer>>() {     
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {     
                return (o2.getValue() - o1.getValue());  //倒序(如果要正序:把减数与被减数调换)   
            }     
        });   
	    //
		

		for(int i=0;i<newlist.size();i++)
		{
			String nn=newlist.get(i).toString();
			Map.Entry<String, Integer> bumenNewMap1=(Map.Entry)newlist.get(i);
			
				countNews=new CountNews();
				countNews.setUnit(bumenNewMap1.getKey());
			
				countNews.setNumbs(bumenNewMap1.getValue());

			bumenNewList.add(countNews);
		}
		//
		return bumenNewList;		
	}
	/**
	 * 新闻检索(用于主页显示)
	 * @param type  检索的新闻类型，共七类（校园快讯、通知公告、职教资讯、行业动态、合作交流、校报校刊、工院讲坛） 
	 * @param rows  检索出的记录数
	 *        lmId  第几个栏目
	 *        banKuai   7个板块中的哪一个板块1-7
	 * 编写：石彦杰  2011-10-20
	 * 修改：
	 */	
	public List<News> getNewsToMainIndex(String lm,String lm2,String lm3,int rows,String lmId,int banKuai)
	{
		List<News> newsList;
		if(banKuai==1)//只有第一个栏目检索置顶新闻
			newsList= newsDAO.getNewsToMainIndex(lm, lm2, lm3, rows,true);//true--检索置顶新闻
		else
			newsList= newsDAO.getNewsToMainIndex(lm, lm2, lm3, rows,false);//false--不检索置顶新闻
		//repackage
		List<News> retuNewsList= new ArrayList<News>();
		if(lmId!=null)
		{
			//get news icon
			Lm lmm=lmDAO.selectLmObject(Integer.parseInt(lmId));
			List<NewsMB> newsMBList=newsMBDAO.selectOneMB(Integer.parseInt(lmm.getMb()));
			NewsMB newsMB=null;
			Iterator<NewsMB> nmbit=newsMBList.iterator();
			if(nmbit.hasNext())
				newsMB=(NewsMB)nmbit.next();
			//Iterator and set news icon to repackage
			Iterator<News> nit=newsList.iterator();
			while(nit.hasNext())
			{
				News news=(News)nit.next();
				String time=news.getTime().trim().substring(0,10);
				news.setTime(time);
				news.setIconPath(newsMB.getIcon());
				//
				retuNewsList.add(news);
			}
		
		}
		
		return retuNewsList;
	}
	/**
	 * 模板检索(用于主页的次级页面显示选中的新闻链接)
	 * @param type  检索的新闻类型，共七类（校园快讯、通知公告、职教资讯、行业动态、合作交流、校报校刊、工院讲坛） 
	 * @param rows  检索出的记录数
	 *        lmId  第几个栏目
	 *        mbType  =1:get mbid;=2:get listmb
	 * 编写：石彦杰  2011-10-20
	 * 修改：
	 */	
	public NewsMB getMbContentToView(String lm,String lm2,String lm3)
	{
		//get newsid
		String newsId=null;
		if(!lm3.trim().equals("0"))
			newsId=lm3.trim();
		else
			if(!lm2.trim().equals("0"))
				newsId=lm2.trim();
			else
				newsId=lm.trim();
		//by lm get mbid
		NewsMB newsMB=null;
		String mbId=null;
		if(newsId!=null)
		{
			//get mbid
			mbId=lmDAO.selectLmObject(Integer.parseInt(newsId)).getMb();
			//get mbcontent
			List<NewsMB> newsMBList=newsMBDAO.selectOneMB(Integer.parseInt(mbId));
			Iterator<NewsMB> nmbit=newsMBList.iterator();
			if(nmbit.hasNext())
				newsMB=(NewsMB)nmbit.next();
		}
		//
		
			return newsMB;//return mbcontent
		
	}
	//
/**
 * 删除新闻
 * @param id 新闻id
 * @return boolean
 */
	public boolean deleteNews(int id){
		return newsDAO.deleteNews(id);
	}
	
	/**
	 * 获取新闻，准备编辑
	 * @param id 新闻id
	 * @return ArrayList
	 */
	public ArrayList newsLocate(int id){
		return newsDAO.newsLocate(id);
	}
	
	/**
	 * 得到新闻栏目字符序列,如"69,70,0"
	 * @param id
	 * @return String
	 */
	public String getLmIdString(int id){
		return newsDAO.getLmIdString(id);
	}
	
	/**
	 * 设置新闻为推荐  设置tj=1
	 * @param id
	 * @return boolean
	 */
	public boolean tjNews(int id){
		return newsDAO.tjNews(id);
	}
	/**
	 * 设置新闻为置顶  设置ontop=1
	 * @param id
	 * @return boolean
	 */
	public boolean ontopNews(int id){
		return newsDAO.ontopNews(id);
	}
	/**
	 * 审核文章并加入审核人帐号信息
	 * @param id
	 * @param shUser
	 * @return
	 */
	public boolean shNews(int id, String shUser){
		return newsDAO.shNews(id, shUser);
	}
	
	/**
	 * 修改新闻
	 * @author 李玮
	 * @param lm
	 * @param lm2
	 * @param lm3
	 * @param title
	 * @param content
	 * @param zz
	 * @param time
	 * @param hit
	 * @param url
	 * @param titlecolor
	 * @param pic
	 * @param tj
	 * @param ontop
	 * @param html
	 * @param adduser
	 * @param xgnews
	 * @param htitle
	 * @param sh
	 * @param shUsername
	 * @param ztid
	 * @param googlemap
	 * @param filename
	 * @param updat
	 * @param piczz
	 * @param laiyuan
	 * @return boolean
	 */
	public boolean editNews(
			int id,
			String lm, 
			  String lm2, 
			  String lm3, 
			  String title, 
			  String content, 
			  String zz,
			  String time,
			  String hit,
			  String url,
			  String titlecolor,
			  String pic,
			  String tj,
			  String ontop,
			  String html,
			  String adduser,
			  String xgnews,
			  String htitle,
			  String sh,
			  String shUsername,
			  String ztid,
			  String googlemap,
			  String filename,
			  String updat,
			  String piczz,
			  String laiyuan	
			
			){
		return newsDAO.editNews(id, lm, lm2, lm3, title, content, zz, time, hit, url, titlecolor, pic, tj, ontop, html, adduser, xgnews, htitle, sh, shUsername, ztid, googlemap, filename, updat, piczz, laiyuan);
	}
	
	/**
	 * 图片新闻轮转
	 * @param id
	 * @return ArrayList
	 */
	public List<News> getImageNews(){
		return newsDAO.getImageNews();
	}
	/**
	 * 交换新闻的updat，以达到上移或下移新闻的目的
	 * @param newsid  移动的新闻的id
	 * * @param moveType  移动的类型：=1上移；=2下移
	 * * @param moveRows  移动的行数
	 * @return boolean
	 * 编写：syj
	 */
	public boolean moveNewsToView(int newsid,String moveType,int moveRows){
		return newsDAO.moveNewsToView(newsid, moveType, moveRows);
	}
	
	/**
	 * 添加新闻
	 * @author 李玮
	 * @param lm
	 * @param lm2
	 * @param lm3
	 * @param title
	 * @param content
	 * @param zz
	 * @param time
	 * @param hit
	 * @param url
	 * @param titlecolor
	 * @param pic
	 * @param tj
	 * @param ontop
	 * @param html
	 * @param adduser
	 * @param xgnews
	 * @param htitle
	 * @param sh
	 * @param shUsername
	 * @param ztid
	 * @param googlemap
	 * @param filename
	 * @param updat
	 * @param piczz
	 * @param laiyuan
	 * @return boolean
	 */
	public boolean addNews(
			String lm, 
			  String lm2, 
			  String lm3, 
			  String title, 
			  String content, 
			  String zz,
			  String time,
			  String hit,
			  String url,
			  String titlecolor,
			  String pic,
			  String tj,
			  String ontop,
			  String html,
			  String adduser,
			  String xgnews,
			  String htitle,
			  String sh,
			  String shUsername,
			  String ztid,
			  String googlemap,
			  String filename,
			  String updat,
			  String piczz,
			  String laiyuan,
			  String newslmType
			){
		return newsDAO.addNews(lm, lm2, lm3, title, content, zz, time, hit, url, titlecolor, pic, tj, ontop, html, adduser, xgnews, htitle, sh, shUsername, ztid, googlemap, filename, updat, piczz, laiyuan,newslmType);
	}
}
