package cn.edu.hbcit.util;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import cn.edu.hbcit.pojo.PageBean;

import org.apache.log4j.Logger;

import cn.edu.hbcit.dao.BaseDAO;
import cn.edu.hbcit.dao.NewsDAO;

public class Pagination {

	private static final Map HashMap = null;  
    private PreparedStatement pstmt = null;  
    private ResultSet rs = null;  
    private Connection con = null;  
    protected final Logger log = Logger.getLogger(Pagination.class.getName());
    BaseDAO db=new BaseDAO(); 
    
/**
 * ��ѯSQL 
 * @param sql
 * @return
 */
    public ArrayList querySQL(String sql){  
        ArrayList list=new ArrayList();  
        try {
        	con=db.getConn();
            pstmt=con.prepareStatement(sql);  
            rs=pstmt.executeQuery();  
            ResultSetMetaData rsmd=rs.getMetaData();  
            int count=rsmd.getColumnCount();  
            while(rs.next()){
            	//log.debug("������-->"+rsmd.getColumnName(i)+"\t �õ���object��-->"+rs.getObject(i)+"   "+i);
                HashMap map=new HashMap();  
                for(int i=0;i<count;i++){  
                    map.put(rsmd.getColumnName(i+1), rs.getObject(i+1));  
                }  
                list.add(map);  
            }  
            db.closeAll(con);
        } catch (SQLException e) {  
        	 log.error(e);
        }  
        return list;  
        }  
    
/**
 * ��ѯ������  (��ͨȨ����֪�������ʺ�)
 * @param tableName
 * @param userName
 * @return
 */
    public int countAll(String tableName, String userName,String condition){
    	String sql="";
    	if(!userName.equals("admin"))
    	{
	    	if(condition.trim().length()==0)
	    		sql="SELECT count(*) AS aa FROM " + tableName + " WHERE adduser=?";
	    	else
	    		sql="SELECT count(*) AS aa FROM " + tableName + " WHERE adduser=? and "+condition;
    	}
    	else
    	{
    		if(condition.trim().length()==0)
	    		sql="SELECT count(*) AS aa FROM " + tableName ;
	    	else
	    		sql="SELECT count(*) AS aa FROM " + tableName + " WHERE "+condition;
    	}
        log.debug(sql);
        int i=0;  
        try {  
        	con=db.getConn();
            pstmt=con.prepareStatement(sql);
            if(!userName.equals("admin"))
            	pstmt.setString(1, userName);
            rs=pstmt.executeQuery();  
            if(rs.next()){  
                i=rs.getInt("aa");  
            }  
            db.closeAll(con);
        } catch (SQLException e) {  
        	 log.error(e);
        }  
        return i;  
    }
    /**
     * ��ѯ������  (�߼�Ȩ�޲���֪�������ʺ�)
     * @param tableName
     * @return
     */
    public int countAll(String tableName){
        String sql="SELECT count(*) AS aa FROM " + tableName ;  
        int i=0;  
        try {  
        	con=db.getConn();
            pstmt=con.prepareStatement(sql);
            //pstmt.setString(1, userName);
            rs=pstmt.executeQuery();  
            if(rs.next()){  
                i=rs.getInt("aa");  
            }  
            db.closeAll(con);
        } catch (SQLException e) {  
        	 log.error(e);
        }  
        return i;  
    }
    
  /**
   * ��ѯSQL����ҳ (�߼�Ȩ�޲���֪�������ʺ�)
   * @param sql
   * @param tableName
   * @param page
   * @return
   */
    public ArrayList querySQL(String sql,String tableName,PageBean page){  
        ArrayList list=new ArrayList();  
        if(page!=null){  
            page.setTotalCount(this.countAll(tableName));  
            sql=sql+" limit "+page.getStart()+","+page.getPageSize();  
        }
        log.debug(sql);
        try {  
        	con=db.getConn();
            pstmt=con.prepareStatement(sql);  
            rs=pstmt.executeQuery();  
            ResultSetMetaData rsmd=rs.getMetaData();  
            int count=rsmd.getColumnCount();//�õ������ֶε�����  
            while(rs.next()){  
                HashMap map=new HashMap();  
                for(int i=0;i<count;i++){  
                    map.put(rsmd.getColumnName(i+1), rs.getObject(i+1));//���ֺ�ֵ  
                }
                map.put("totalCount", page.getTotalCount());
                map.put("hasNextPage", page.hasNextPage());
                map.put("hasLastPage", page.hasLastPage());
                map.put("lastPage", page.getLastPage());
                map.put("nextPage", page.getNextPage());
                map.put("pageSize", page.getPageSize());
                map.put("totalPage", page.getTotalPage());
                //log.debug("�ܼ�¼����" + page.getTotalCount());
                list.add(map); 
            }
            db.closeAll(con);
              
        } catch (SQLException e) {  
            //e.printStackTrace();
            log.error(e);
        }  
        return list;  
  
}
    
    /**
     * ��ѯSQL����ҳ(��ͨȨ����֪�������ʺ�) 
     * @param sql
     * @param tableName
     * @param page
     * @param username
     * @return
     */
      public ArrayList<HashMap> querySQL(String sql,String tableName,PageBean page, String username,String condition){  
          ArrayList<HashMap> list=new ArrayList<HashMap>();  
          if(page!=null){  
              page.setTotalCount(this.countAll(tableName, username,condition));  
              sql=sql+" limit "+page.getStart()+","+page.getPageSize();  
          }
          log.debug(sql);
          try {  
          	con=db.getConn();
              pstmt=con.prepareStatement(sql);  
              rs=pstmt.executeQuery();  
              ResultSetMetaData rsmd=rs.getMetaData();  
              int count=rsmd.getColumnCount();//�õ������ֶε�����  
              while(rs.next()){  
//                System.out.println("������-->"+rsmd.getColumnName(i)+"\t �õ���object��-->"+rs.getObject(i)+"   "+i);  
                  HashMap map=new HashMap();  
                  for(int i=0;i<count;i++){  
                      map.put(rsmd.getColumnName(i+1), rs.getObject(i+1));//���ֺ�ֵ  
                  }
                  log.debug("page.getTotalCount():"+page.getTotalCount());
                  map.put("totalCount", page.getTotalCount());
                  map.put("hasNextPage", page.hasNextPage());
                  map.put("hasLastPage", page.hasLastPage());
                  map.put("lastPage", page.getLastPage());
                  map.put("nextPage", page.getNextPage());
                  map.put("pageSize", page.getPageSize());
                  map.put("totalPage", page.getTotalPage());
                  //log.debug("�ܼ�¼����" + page.getTotalCount());
                  list.add(map); 
              }  
              db.closeAll(con);
                
          } catch (SQLException e) {  
              //e.printStackTrace();
              log.error(e);
          }  
          return list;  
    
  }  
    
	
}
