package cn.edu.hbcit.dao;


import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;  
import java.util.Iterator;  
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import cn.edu.hbcit.pojo.DynamicSetLm;
import cn.edu.hbcit.pojo.News;
import cn.edu.hbcit.util.UtilTools;
import cn.edu.hbcit.util.Pagination;
import cn.edu.hbcit.pojo.PageBean;

/**
 * 新闻操作类
 * @author Administrator
 *
 */
public class NewsDAO extends BaseDAO {
	BaseDAO db=new BaseDAO(); 
	private ResultSet rs=null;
	private Connection conn=null;
	private PreparedStatement pStatement = null;
	protected final Logger log = Logger.getLogger(NewsDAO.class.getName());
    private DynamicSetLm dynamicSetLm=new DynamicSetLm();
    private DynamicSetLmDAO dynamicSetLmDAO=new DynamicSetLmDAO();
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
		boolean flag = false;
		UtilTools ut = new UtilTools();
		try{
			
			
			int state = 0;
			//Clob c = null;
			
			int hit_int = 0;
			int ontop_int = 0;
			int sh_int = 0;
			int ZDIT_int = 0;
			int updat_int = 0;
			int newslmType_int = 2;
			
			conn=db.getConn();
			if(content==null){ 
				  content="";
			  }

			if(hit!=null && ontop!=null && sh!=null && ztid!=null && updat!=null && newslmType!=null 
				&& ut.isNumeric(hit) && ut.isNumeric(ontop) && ut.isNumeric(sh) && ut.isNumeric(ztid) && ut.isNumeric(updat) && ut.isNumeric(newslmType)){
				hit_int = Integer.parseInt(hit);  //点击量由String转换为int
				ontop_int = Integer.parseInt(ontop);  //固顶由String转换为int
				sh_int = Integer.parseInt(sh);  //审核由String转换为int
				ZDIT_int = Integer.parseInt(ztid);  //转帖由String转换为int
				updat_int = Integer.parseInt(updat);  //文章顺序由String转换为int
				newslmType_int = Integer.parseInt(newslmType);  //转帖由String转换为int
			}
			/*
			 * 为日期加入当前时间
			 */
			SimpleDateFormat sDateFormat = new SimpleDateFormat("HH:mm:ss");
			time = time + " " + sDateFormat.format(new java.util.Date());
			log.debug(time);
			//c = new javax.sql.rowset.serial.SerialClob(content.toCharArray());
			//conn.createStatement().executeUpdate("insert into admin(user,pass) values('"+name.trim()+"','"+pws.trim()+"')");
			String sql = "INSERT INTO news (lm,lm2,lm3,title,content,zz,time,hit,URL,titlecolor,pic,tj,ontop,html,adduser,xgnews,htitle,sh,shUsername,ZTID,GoogleMap,filename,updat,piczz,LaiYuan,newslmType) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			

			
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, lm);
			pStatement.setString(2, lm2);
			pStatement.setString(3, lm3);
			pStatement.setString(4, title);
			/*java.io.Reader clobReader = new java.io.StringReader(content);
			log.debug("clobReader-"+clobReader);
			pStatement.setCharacterStream(5, clobReader, content.length());  //长文档转换为字符流
			 */
			
			pStatement.setString(5, content);
			pStatement.setString(6, zz);
			pStatement.setString(7, time);
			pStatement.setInt(8, hit_int);
			pStatement.setString(9, url);
			pStatement.setString(10, titlecolor);
			pStatement.setString(11, pic);
			pStatement.setString(12, tj);
			pStatement.setInt(13, ontop_int);
			pStatement.setString(14, html);
			pStatement.setString(15, adduser);
			pStatement.setString(16, xgnews);
			pStatement.setString(17, htitle);
			pStatement.setInt(18, sh_int);
			pStatement.setString(19, shUsername);
			pStatement.setInt(20, ZDIT_int);
			pStatement.setString(21, googlemap);
			pStatement.setString(22, filename);
			pStatement.setInt(23, updat_int);
			pStatement.setString(24, piczz);
			pStatement.setString(25, laiyuan);
			pStatement.setInt(26, newslmType_int);
			
			state = pStatement.executeUpdate();
			//设置 updat; add  syj 2011-11-10
			String sqlupdat="update news set updat=id where updat=0";
			pStatement = conn.prepareStatement(sqlupdat);
			pStatement.executeUpdate();
			//
			db.closeAll(conn);
			if(state > 0){
				  flag = true;
			  }
		}catch(Exception e){
			log.error(e.getMessage());
			}
		
		return flag;
		
	}
	
	/**
	 * 统计栏目数量
	 * @param lmLevel 栏目级别1,2,3
	 * @param lmId 栏目号
	 * @return
	 */
	public int countLm(String lmLevel, String lmId){
		int returnValue = 0;
		conn=db.getConn();
		String sql = "";
		
		if(lmLevel.equals("1")){
			sql = "SELECT count(*) FROM news WHERE lm=?";
		}else if(lmLevel.equals("2")){
			sql = "SELECT count(*) FROM news WHERE lm2=?";
		}else if(lmLevel.equals("3")){
			sql = "SELECT count(*) FROM news WHERE lm3=?";
		}
		try
		{
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, lmId);
			rs = pStatement.executeQuery();
			while(rs.next()){
				returnValue = rs.getInt(1);
			}
			//returnValue = pStatement.executeUpdate();
			log.debug(returnValue);
			db.closeAll(conn);
		}catch(Exception e)
		{
			log.error(e.getMessage());
		}
		
		return returnValue;
	}
	/**
	 * 给selectAdminNewsList生成查询语句
	 * @param userName 查询人的用户名
	 * @param right 用户权限 1-管理员,2-审核员,3-录入员
	 * @return
	 *   修改：syj 2011-11-9:修改目的：增加查询支持，增加参数String sTime,String eTime
	 */
	public String[] createSQL(String userName,String right,String title,String content,String contentType,String LaiYuan,String stime,String etime)
	{
		String[] rs=new String[2];
		String retuString="SELECT news.id,news.title,news.hit,news.ontop,news.time,news.zz,news.laiyuan,news.sh,news.tj,news.updat FROM news";
		String sql= " ";
		//
		boolean havetitle=false;
		if(title.length()!=0)
		{
			sql=sql+ " title like '%"+title.trim()+"%'";
			havetitle=true;
		}
		//
		boolean havecontent=false;
		if(content.length()!=0 && havetitle)
		{
			sql=sql+ " and content like '%"+content.trim()+"%'";
			havecontent=true;
		}
		if(content.length()!=0 && !havetitle)
		{
			sql=sql+ " content like '%"+content.trim()+"%'";
			havecontent=true;
		}
		//
		boolean havecontentType=false;
		boolean resu=(havecontent || havetitle);
		dynamicSetLm=dynamicSetLmDAO.selectOneDynamicSetLm(contentType);
		if(contentType.length()!=0 && resu)
		{
			sql=sql+ " and lm='"+dynamicSetLm.getLm()+"' and lm2='"+dynamicSetLm.getLm2()+"' and lm3='"+dynamicSetLm.getLm3()+"' ";
			havecontentType=true;
		}
		if(contentType.length()!=0 && !resu)
		{
			sql=sql+ " lm='"+dynamicSetLm.getLm()+"' and lm2='"+dynamicSetLm.getLm2()+"' and lm3='"+dynamicSetLm.getLm3()+"' ";
			havecontentType=true;
		}
		//
		boolean haveLaiYuan=false;
		resu=(havecontent || havetitle || havecontentType);
		if(LaiYuan.length()!=0 && resu)
		{
			sql=sql+ " and LaiYuan like '%"+LaiYuan+"%' ";
			haveLaiYuan=true;
		}
		if(LaiYuan.length()!=0 && !resu)
		{
			sql=sql+ " LaiYuan like '%"+LaiYuan+"%' ";
			haveLaiYuan=true;
		}
		//
		boolean havestime=false;
		resu=(havecontent || havetitle || havecontentType || haveLaiYuan);
		if(stime.length()!=0 && resu)
		{
			sql=sql+ " and time>='"+stime+"' ";
			havestime=true;
		}
		if(stime.length()!=0 && !resu)
		{
			sql=sql+ " time>='"+stime+"' ";
			havestime=true;
		}
		//
		boolean haveetime=false;
		resu=(havecontent || havetitle || havecontentType || haveLaiYuan || havestime);
		if(etime.length()!=0 && resu)
		{
			sql=sql+ " and time<='"+etime+"' ";
			haveetime=true;
		}
		if(etime.length()!=0 && !resu)
		{
			sql=sql+ " time<='"+etime+"' ";
			haveetime=true;
		}
		
		//
		resu=(havecontent || havetitle || havecontentType || haveLaiYuan || haveetime);
		if(right.equals("3") && resu){
			sql=sql+ " and adduser='"+userName+"' ";
		}
		if(right.equals("3") && !resu){
			sql=sql+ "  adduser='"+userName+"' ";
		}
		if(sql.trim().length()!=0)
			retuString=retuString+" where "+sql+ " ORDER BY news.updat DESC ";
		else
			retuString=retuString+" ORDER BY news.updat DESC ";
		//
		rs[0]=retuString;
		rs[1]=sql;
		return rs;
	}
	
	/**
	 * 新闻修改页面列表查询
	 * @param userName 查询人的用户名
	 * @param cPage 当前页码
	 * @param perPage 每页显示记录的数量
	 * @param right 用户权限 1-管理员,2-审核员,3-录入员
	 * @return
	 *   修改：syj 2011-11-9:修改目的：增加查询支持，增加参数String sTime,String eTime
	 */
	public ArrayList selectAdminNewsList(String userName, String cPage, String perPage, String right,String title,String content,String contentType,String LaiYuan,String stime,String etime){
		ArrayList list = new ArrayList();
		UtilTools ut = new UtilTools();
		PageBean page = new PageBean();  
		Pagination p = new Pagination();
		News news=new News();
		
		int curPage = 1;  //当前页码
		int pPage = 20; //每页显示条目数
		if(ut.isNumeric(cPage)){	//判断cPage是否是整型数据
			curPage = Integer.parseInt(cPage);
			log.debug("当前页码curPage: "+curPage);
		  }
		if(ut.isNumeric(perPage)){	//判断perPage是否是整型数据
			pPage = Integer.parseInt(perPage);
			log.debug("每页显示条目数量perPage: "+perPage);
		  }
		
		
		//
		try{
			page.setPage(curPage);
			page.setPageSize(pPage);
			String[] sql=createSQL(userName,right,title,content,contentType,LaiYuan,stime,etime);
			list = p.querySQL(sql[0], "news", page, userName,sql[1]);
					
/*			for(int i=0;i<list.size();i++){
	            HashMap map=(HashMap)list.get(i);  
	            Iterator it=map.keySet().iterator();  
	            while(it.hasNext()){  
	                Object id=it.next();  
	                log.debug(map.get(id));
	            }
			}*/
		}catch(Exception e){
			log.error(e);
		}
		return list;
	}
	
	/**
	 * 主页显示更多新闻提取
	 * @param userName 查询人的用户名
	 * @param cPage 当前页码
	 * @param perPage 每页显示记录的数量
	 * @return
	 *   syj
	 */
	@SuppressWarnings("unchecked")
	public List<News> selectMoreNewsList( String cPage, String perPage, String lm){
		List<News> list = new ArrayList<News>();
		UtilTools ut = new UtilTools();
		PageBean page = new PageBean();  
		Pagination p = new Pagination();
		News news=null;
		//
		String[] lms=lm.split(",");
		
		String  sql_Low = "";
		int curPage = 1;  //当前页码
		int pPage = Integer.parseInt(perPage);//20; //每页显示条目数
		if(ut.isNumeric(cPage)){	//判断cPage是否是整型数据
			curPage = Integer.parseInt(cPage);
			log.debug("当前页码curPage: "+curPage);
		  }
		if(ut.isNumeric(perPage)){	//判断perPage是否是整型数据
			pPage = Integer.parseInt(perPage);
			log.debug("每页显示条目数量perPage: "+perPage);
		  }
		sql_Low = 	"SELECT id,title,time,hit FROM news WHERE lm='"+lms[0].trim()+"' and lm2='"+lms[1].trim()+"' and lm3='"+lms[2].trim()+"' ORDER BY updat DESC";
		
		try{
			page.setPage(curPage);
			page.setPageSize(pPage);
			list = p.querySQL(sql_Low, "news", page);
		
		}catch(Exception e){
			log.error(e);
		}
		return list;
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
		int count=0;
		String sql=null;
		if(startTime.trim().length()==0 && endTime.trim().length()==0)
			sql="select count(*) from news where laiyuan='"+bumen.trim()+"'" ;
		else
		{
			if(startTime.trim().length()==0 && endTime.trim().length()!=0)
				sql="select count(*) from news where laiyuan='"+bumen.trim()+"' and time <='"+endTime.trim()+"'" ;
			else
			{
				if(startTime.trim().length()!=0 && endTime.trim().length()==0)
					sql="select count(*) from news where laiyuan='"+bumen.trim()+"' and time>='"+startTime.trim()+"'";
				else
					sql="select count(*) from news where laiyuan='"+bumen.trim()+"' and time>='"+startTime.trim()+"' and time <='"+endTime.trim()+"'";
			}
		}
			try{
			conn=db.getConn();
			pStatement=conn.prepareStatement(sql);
			rs=pStatement.executeQuery();
			if(rs.next())
				count=rs.getInt(1);
			db.closeAll(conn);
		}catch(Exception e){
			log.error(e);
		}
		return count;		
	}
	/**
	 * 新闻检索(用于主页显示)
	 * @param type  检索的新闻类型，共七类（校园快讯、通知公告、职教资讯、行业动态、合作交流、校报校刊、工院讲坛） 
	 * @param rows  检索出的记录数;top  是否检索置顶新闻=true--检索；=false--不检索
	 * 编写：石彦杰  2011-10-20
	 * 修改：
	 */	
	public List<News> getNewsToMainIndex(String lm,String lm2,String lm3,int rows,boolean top)
	{
		List<News> newsList=new ArrayList<News>();
		News news=null;
		//
		try{
			conn=db.getConn();
			if(top)
			{
				pStatement=conn.prepareStatement("SELECT * FROM news WHERE lm='"+lm.trim()+"' AND lm2='"+lm2.trim()+"' and lm3='"+lm3.trim()+"' and ontop=1  and sh=1 ORDER BY updat desc LIMIT 1");
				rs=pStatement.executeQuery();
				if(rs.next())
				{
					news=new News();
					news.setId(rs.getInt("id"));
					news.setTitle(rs.getString("title").trim());
					news.setContent(rs.getString("content").trim());
					news.setAdduser(rs.getString("adduser").trim());
					news.setTime(rs.getString("time").trim());
					news.setLaiyuan(rs.getString("laiyuan").trim());
					news.setHit(rs.getInt("hit"));
					news.setOntop(1);
					news.setTopNew(true);
					//
					newsList.add(news);
				}
			}
			//
			pStatement=conn.prepareStatement("SELECT * FROM news WHERE lm='"+lm.trim()+"' AND lm2='"+lm2.trim()+"' and lm3='"+lm3.trim()+"' and ontop<>1  and sh=1 ORDER BY updat desc LIMIT "+String.valueOf(rows));
			rs=pStatement.executeQuery();
			while(rs.next())
			{
				news=new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title").trim());
				news.setContent(rs.getString("content").trim());
				news.setAdduser(rs.getString("adduser").trim());
				news.setTime(rs.getString("time").trim());
				news.setLaiyuan(rs.getString("laiyuan").trim());
				news.setHit(rs.getInt("hit"));
				news.setOntop(0);
				news.setTopNew(false);
				//
				newsList.add(news);
			}
			db.closeAll(conn);
		}catch(Exception e)
		{
			newsList=null;
			e.printStackTrace();
		}
		return newsList;
	}
	
	/**
	 * 删除新闻
	 * @param id 新闻编号
	 * @return boolean
	 */
	public boolean deleteNews(int id){
		boolean flag = false;
		int result = 0;
		conn=db.getConn();
		
		String sql = "";
		try{
			//1、先移动到news1，后删除  syj 2011-11-10
			sql = "INSERT INTO news1(lm,lm2,lm3,title,content,zz,time,hit,URL,titlecolor,pic,tj,ontop,html,adduser,xgnews,htitle,sh,shUsername,GoogleMap,filename,updat,piczz,LaiYuan) SELECT lm,lm2,lm3,title,content,zz,time,hit,URL,titlecolor,pic,tj,ontop,html,adduser,xgnews,htitle,sh,shUsername,GoogleMap,filename,updat,piczz,LaiYuan FROM news where id=?";
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, id);
			pStatement.executeUpdate();
			//2、删除
			sql = "DELETE FROM news WHERE id=?";
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, id);
			pStatement.executeUpdate();
			
			flag = true;
		}catch(Exception e){
			flag = false;
			log.error(e);
		}
		db.closeAll(conn);
		
		
		return flag;
		
	}
	
	/**
	 * 获取新闻，准备编辑
	 * @param id 新闻编号
	 * @return ArrayList
	 */
	public ArrayList newsLocate(int id){
		ArrayList list = new ArrayList();
		//UtilTools ut = new UtilTools();
		
		conn=db.getConn();
		String sql = "SELECT * FROM news WHERE id=?";
		try{
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			News news=new News();
			while(rs.next())
			{
				
				news.setId(rs.getInt("id"));
				news.setLm(rs.getString("lm"));
				news.setLm2(rs.getString("lm2"));
				news.setLm3(rs.getString("lm3"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setZz(rs.getString("zz"));
				news.setTime(rs.getString("time"));
				news.setHit(rs.getInt("hit"));
				news.setUrl(rs.getString("URL"));
				news.setTitlecolor(rs.getString("titlecolor"));
				news.setPic(rs.getString("pic"));
				news.setTj(rs.getString("tj"));
				news.setOntop(rs.getInt("ontop"));
				news.setHtml(rs.getString("html"));
				news.setAdduser(rs.getString("adduser"));
				news.setXgnews(rs.getString("xgnews"));
				news.setHtitle(rs.getString("htitle"));
				news.setSh(rs.getInt("sh"));
				news.setShusername(rs.getString("shUsername"));
				news.setZtid(rs.getInt("ZTID"));
				news.setGooglemap(rs.getString("GoogleMap"));
				news.setFilename(rs.getString("filename"));
				news.setUpdat(rs.getInt("updat"));
				news.setPiczz(rs.getString("piczz"));
				news.setLaiyuan(rs.getString("LaiYuan"));
				
				list.add(news);
				
			}
			//先计数，后返回检索值  syj alter 2011-10-27
			pStatement=conn.prepareStatement("update news set hit="+String.valueOf(news.getHit()+1)+" where id=?");
			pStatement.setInt(1, id);
			pStatement.executeUpdate();
			db.closeAll(conn);
		}catch(Exception e){
			log.error(e);
		}
		return list;
	}
	
	/**
	 * 得到新闻栏目字符序列,如"69,70,0"
	 * @param id
	 * @return String
	 * 编写：
	 */
	public String getLmIdString(int id){
		String returnValue = "";
		conn=db.getConn();
		String sql = "SELECT lm,lm2,lm3 FROM news WHERE id=?";
		try{
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			while(rs.next())
			{
				returnValue = rs.getString("lm") + "," + rs.getString("lm2") + "," + rs.getString("lm3");
				log.debug(returnValue);
			}
			db.closeAll(conn);
		}catch(Exception e){
			log.error(e);
		}
		return returnValue;
	}
	
	/**
	 * 设置新闻为推荐  设置tj=1
	 * @param id
	 * @return boolean
	 */
	public boolean tjNews(int id){
		boolean flag = false;
		int result = 0;
		conn=db.getConn();
		String sql = "UPDATE news SET tj=1 WHERE id=?";
		try{
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, id);
			result = pStatement.executeUpdate();
		}catch(Exception e){
			log.error(e);
		}
		db.closeAll(conn);
		if(result > 0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 设置新闻为置顶  设置ontop=1
	 * @param id
	 * @return
	 */
	public boolean ontopNews(int id){
		boolean flag = false;
		int result1 = 0;
		int result2 = 0;
		conn=db.getConn();
		String sql1="UPDATE news SET ontop=0 WHERE ontop=1";
		String sql2 = "UPDATE news SET ontop=1 WHERE id=?";
		try{
			//将所有ontop置顶标记设为0
			pStatement=conn.prepareStatement(sql1);
			result1 = pStatement.executeUpdate();
			//将指定记录的置顶标记设为1
			pStatement=conn.prepareStatement(sql2);
			pStatement.setInt(1, id);
			result2 = pStatement.executeUpdate();
			
			log.debug("取消了"+result1+"条置顶记录，成功添加了"+result2+"条置顶。");
					
		}catch(Exception e){
			log.error(e);
		}
		db.closeAll(conn);
		if(result2 > 0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 审核文章并加入审核人帐号信息
	 * @param id
	 * @param shUser
	 * @return
	 */
	public boolean shNews(int id, String shUser){
		boolean flag = false;
		int result = 0;
		conn=db.getConn();
		String sql = "UPDATE news SET sh=1,shUsername=? WHERE id=?";
		try{
			pStatement=conn.prepareStatement(sql);
			pStatement.setString(1, shUser);
			pStatement.setInt(2, id);
			
			result = pStatement.executeUpdate();
		}catch(Exception e){
			log.error(e);
		}
		db.closeAll(conn);
		if(result > 0){
			flag = true;
		}
		return flag;
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
	boolean flag = false;
	try{
		
		
		int state = 0;
				
		int hit_int = 0;
		int ontop_int = 0;
		int sh_int = 0;
		int ZDIT_int = 0;
		//int updat_int = 0;
		
		conn=db.getConn();
		if(content==null){ 
			  content="";
		  }
		
		if(hit!=null&&ontop!=null&&sh!=null&&ztid!=null&&updat!=null){
			log.debug("hit:"+hit+"\n ontop:"+ontop+"\n sh:"+sh+"\n ztid:"+ztid+"\n updat:"+updat);
			hit_int = Integer.parseInt(hit);  //点击量由String转换为int
			ontop_int = Integer.parseInt(ontop);  //固顶由String转换为int
			sh_int = Integer.parseInt(sh);  //审核由String转换为int
			ZDIT_int = Integer.parseInt(ztid);  //转帖由String转换为int
			//updat_int = Integer.parseInt(updat);  //转帖由String转换为int
		}
		/*
		 * 为日期加入当前时间
		 */
		SimpleDateFormat sDateFormat = new SimpleDateFormat("HH:mm:ss");
		time = time + " " + sDateFormat.format(new java.util.Date());
		log.debug(time);
		
		String sql = "UPDATE news SET lm=?,lm2=?,lm3=?,title=?,content=?,zz=?,time=?,hit=?,URL=?,titlecolor=?,pic=?,tj=?,ontop=?,html=?,adduser=?,xgnews=?,htitle=?,sh=?,shUsername=?,ZTID=?,GoogleMap=?,filename=?,piczz=?,LaiYuan=? WHERE id=? ";
		pStatement = conn.prepareStatement(sql);
		pStatement.setString(1, lm);
		pStatement.setString(2, lm2);
		pStatement.setString(3, lm3);
		pStatement.setString(4, title);
		pStatement.setString(5, content);
		pStatement.setString(6, zz);
		pStatement.setString(7, time);
		pStatement.setInt(8, hit_int);
		pStatement.setString(9, url);
		pStatement.setString(10, titlecolor);
		pStatement.setString(11, pic);
		pStatement.setString(12, tj);
		pStatement.setInt(13, ontop_int);
		pStatement.setString(14, html);
		pStatement.setString(15, adduser);
		pStatement.setString(16, xgnews);
		pStatement.setString(17, htitle);
		pStatement.setInt(18, sh_int);
		pStatement.setString(19, shUsername);
		pStatement.setInt(20, ZDIT_int);
		pStatement.setString(21, googlemap);
		pStatement.setString(22, filename);
		//pStatement.setInt(23, updat_int);
		pStatement.setString(23, piczz);
		pStatement.setString(24, laiyuan);
		pStatement.setInt(25, id);
	
		state = pStatement.executeUpdate();
		db.closeAll(conn);
		if(state > 0){
			  flag = true;
		  }
	}catch(Exception e){
		log.error(e.getMessage());
		}
	
	return flag;
}
	
	/**
	 * 图片新闻轮转
	 * @param id
	 * @return ArrayList
	 */



	public ArrayList<News> getImageNews(){
		ArrayList<News> list = new ArrayList<News>();

		conn=db.getConn();
		String sql = "SELECT id,title,pic FROM news WHERE tj='1' AND sh='1' ORDER BY updat desc LIMIT 5";
		try{
			pStatement=conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			
			while(rs.next())
			{
				News news=new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setPic(rs.getString("pic"));
				
				list.add(news);
			}
			db.closeAll(conn);
		}catch(Exception e){
			log.error(e);
		}
		return list;
	}
	/**
	 * 交换新闻的updat，以达到上移或下移新闻的目的
	 * @param newsid  移动的新闻的id
	 * * @param moveType  移动的类型：=1上移；=2下移
	 * * @param moveRows  移动的行数
	 * @return boolean
	 * 编写：syj
	 */
	public boolean moveNewsToView(int newsupdat,String moveType,int moveRows){
		boolean returnValue = false;
		
		try{
			conn=db.getConn();
			if(moveType.trim().equals("1"))//上移
			{
				int[] updateUpdat=new int[moveRows];//moveRows值最大20
				int[] updateId=new int[moveRows];//moveRows值最大20
				String[] sqlup=new String[moveRows];
				//
				String sql = "SELECT id,updat FROM news WHERE updat>=? order by updat  limit "+moveRows;
				pStatement=conn.prepareStatement(sql);
				pStatement.setInt(1, newsupdat);
				rs = pStatement.executeQuery();
				int rows=0;
				int targetupdate=0;
				int targetRowId=0;
				//得到第一行的updat和最后一行的id及updat.切记：第一行就是要移动的行。采用冒泡方式
				while(rs.next())
				{
					updateUpdat[rows]=rs.getInt("updat");
					updateId[rows]=rs.getInt("id");

					if(rows>=1)
						sqlup[rows-1]="update news set updat="+String.valueOf(updateUpdat[rows-1])+" where id="+String.valueOf(rs.getInt("id"));
					//
					targetupdate=rs.getInt("updat");
					targetRowId=rs.getInt("id");
					
					rows++;
				}
				//开始替换
				for(int r=0;r<rows;r++)
				{
					if(sqlup[r]!=null)
					{
						pStatement=conn.prepareStatement(sqlup[r]);
						pStatement.executeUpdate();
					}
					
				}
				//
				sql = "update news set updat="+targetupdate+" WHERE id="+String.valueOf(updateId[0]);
				pStatement=conn.prepareStatement(sql);
				pStatement.executeUpdate();
			}
			if(moveType.trim().equals("2"))//下移
			{
				int[] updateUpdat=new int[moveRows];//moveRows值最大20
				int[] updateId=new int[moveRows];//moveRows值最大20
				String[] sqlup=new String[moveRows];
				//
				String sql = "SELECT id,updat FROM news WHERE updat<=? order by updat desc limit "+moveRows;
				pStatement=conn.prepareStatement(sql);
				pStatement.setInt(1, newsupdat);
				rs = pStatement.executeQuery();
				int rows=0;
				int targetupdate=0;
				int targetRowId=0;
				//得到第一行的updat和最后一行的id及updat.切记：第一行就是要移动的行。采用冒泡方式
				while(rs.next())
				{
					updateUpdat[rows]=rs.getInt("updat");
					updateId[rows]=rs.getInt("id");

					if(rows>=1)
						sqlup[rows-1]="update news set updat="+String.valueOf(updateUpdat[rows-1])+" where id="+String.valueOf(rs.getInt("id"));
					//
					targetupdate=rs.getInt("updat");
					targetRowId=rs.getInt("id");
					
					rows++;
				}
				//开始替换
				for(int r=0;r<rows;r++)
				{
					if(sqlup[r]!=null)
					{
						pStatement=conn.prepareStatement(sqlup[r]);
						pStatement.executeUpdate();
					}
					
				}
				//
				sql = "update news set updat="+targetupdate+" WHERE id="+String.valueOf(updateId[0]);
				pStatement=conn.prepareStatement(sql);
				pStatement.executeUpdate();
				
			}
			returnValue = true;
			
			db.closeAll(conn);
		}catch(Exception e){
			returnValue = false;
			log.error(e);
		}
		return returnValue;
	}
}
