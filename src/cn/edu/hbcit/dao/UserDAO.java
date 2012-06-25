package cn.edu.hbcit.dao;

/*
 * ���ܣ�ϵͳ�û���CRUD
 * ��д��ʯ���  2011-9-28
 * �޸ģ�
 * 
 */

import cn.edu.hbcit.dao.BaseDAO;
import cn.edu.hbcit.pojo.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
	BaseDAO baseDAO=new BaseDAO(); 
	ResultSet rs=null;
	Connection conn=null;
	/*
	 * ���ܣ�����ϵͳ�û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean addUser(String name,String pws,String bumen,String xingming,String type)
	{
		boolean retuValue=false;
		String userType="3";//Ĭ��һ�����Ա
		if(type.trim().equals("1"))//���Ա
			userType="2";
		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("insert into admin(user,pass,dj,bumen,username) values('"+name.trim()+"','"+pws.trim()+"','"+userType+"','"+bumen.trim()+"','"+xingming.trim()+"')");
			retuValue=true;
		}catch(Exception ee){
			retuValue=false;
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�ɾ��ϵͳ�û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean deleteUserByName(String name)
	{
		boolean retuValue=false;
		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("delete from admin where user='"+name.trim()+"'  and user<>'admin'");
			retuValue=true;
		}catch(Exception ee){retuValue=false;ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�ɾ��ϵͳ�û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean deleteUserById(int id)
	{
		boolean retuValue=false;
		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("delete from admin where id="+String.valueOf(id)+" and user<>'admin'");
			retuValue=true;
		}catch(Exception ee){retuValue=false;ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�ɾ��ϵͳ�����û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean deleteAllUser()
	{
		boolean retuValue=false;
		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("delete from admin where user<>'admin'");
			retuValue=true;
		}catch(Exception ee){retuValue=false;ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�����ϵͳ�û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public List<Admin> selectAllUsers()
	{
		List<Admin> userList= new ArrayList<Admin>();
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from admin ");
			//���
			while(rs.next())
		    {
		    	
				Admin admin=new Admin();
				admin.setId(rs.getInt("id"));
				admin.setUser(rs.getString("user"));
				admin.setPass(rs.getString("pass"));
				admin.setBumen(rs.getString("bumen"));
				admin.setDj(rs.getString("dj"));
				admin.setLmid(rs.getString("lmid"));
				admin.setNewsSL(rs.getInt("NewsSL"));
		    	//
				userList.add(admin);
		    }
		}catch(Exception ee){ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return userList;
	}
	/*
	 * ���ܣ�����ϵͳһ�����û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public List<Admin> selectUsersByName(String name)
	{
		List<Admin> userList= new ArrayList<Admin>();
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from admin where user='"+name.trim()+"'");
			//���
			while(rs.next())
		    {
		    	
				Admin admin=new Admin();
				admin.setId(rs.getInt("id"));
				admin.setUser(rs.getString("user"));
				admin.setPass(rs.getString("pass"));
				admin.setBumen(rs.getString("bumen"));
				admin.setDj(rs.getString("dj"));
				admin.setLmid(rs.getString("lmid"));
				admin.setNewsSL(rs.getInt("NewsSL"));
		    	//
				userList.add(admin);
		    }
		}catch(Exception ee){ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return userList;
	}
	/*
	 * ���ܣ�����ϵͳ����
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public List<String> selectUsersBumen(String user)
	{
		List<String> userList= new ArrayList<String>();
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			if(user.trim().equals("admin"))
				rs=conn.createStatement().executeQuery("select bumen from admin where bumen<>''");
			else
				rs=conn.createStatement().executeQuery("select bumen from admin where user='"+user.trim()+"'");
			//���
			while(rs.next())
		    {
		    	//
				userList.add(rs.getString("bumen").trim());
		    }
		}catch(Exception ee){ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return userList;
	}
	/*
	 * ���ܣ�����ϵͳһ���û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public Admin selectUsersById(int id)
	{
		Admin admin= new Admin();
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from admin where id="+String.valueOf(id)+"  and user<>'admin'");
			//���
			while(rs.next())
		    {
				admin.setId(rs.getInt("id"));
				admin.setUser(rs.getString("user"));
				admin.setPass(rs.getString("pass"));
				admin.setBumen(rs.getString("bumen"));
				admin.setDj(rs.getString("dj"));
				admin.setLmid(rs.getString("lmid"));
				admin.setNewsSL(rs.getInt("NewsSL"));
				admin.setUsername(rs.getString("username"));
		    	
		    }
		}catch(Exception ee){ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return admin;
	}
	/*
	 * ���ܣ�����ϵͳһ���û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public Admin selectUsersByNameAndPws(String name,String pws)
	{
		Admin admin= null;
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from admin where pass='"+pws.trim()+"'  and user='"+name.trim()+"'");
			//���
			if(rs.next())
			{
				admin=new Admin();
				admin.setId(rs.getInt("id"));
				admin.setUser(rs.getString("user"));
				admin.setPass(rs.getString("pass"));
				admin.setBumen(rs.getString("bumen"));
				admin.setDj(rs.getString("dj"));
				admin.setLmid(rs.getString("lmid"));
				admin.setNewsSL(rs.getInt("NewsSL"));
				
			}
			baseDAO.closeAll(conn);	
		}catch(Exception ee){ee.printStackTrace();}
		
		return admin;
	}
	/*
	 * ���ܣ�����ϵͳһ���û����û���
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateUserNameByName(String name)
	{
		boolean retuValue=false;
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("update admin set user='"+name.trim()+"' where user='"+name.trim()+"'");
			
		}catch(Exception ee){ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�����ϵͳһ���û��û���
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateUserNameById(int id,String name)
	{
		boolean retuValue=false;
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("update admin set user='"+name.trim()+"' where id="+String.valueOf(id).trim());
			
		}catch(Exception ee){ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�����ϵͳһ���û��Ŀ���
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updatePwsByName(String name,String pws)
	{
		boolean retuValue=false;
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("update admin set pass='"+pws.trim()+"' where user='"+name.trim()+"'");
			
		}catch(Exception ee){ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�����ϵͳһ���û��û���
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updatePwsById(int id,String oldpws,String newpws)
	{
		boolean retuValue=false;
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			//rs=conn.createStatement().executeQuery("select * from admin where pass='"+oldpws.trim()+"' and id="+String.valueOf(id));
			//if(rs.next())
			//{
				conn.createStatement().executeUpdate("update admin set pass='"+newpws.trim()+"' where id="+String.valueOf(id).trim());
				retuValue=true;
			//}else
			//{
				////retuValue=false;
			//}
			
		}catch(Exception ee){ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�����ϵͳһ���û���Ȩ��
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updatePermissionsByName(String name,String Permissions)
	{
		boolean retuValue=false;
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("update admin set lmid='"+Permissions.trim()+"' where user='"+name.trim()+"'");
			
		}catch(Exception ee){ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�����ϵͳһ���û�Ȩ��
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updatePermissionsById(int id,String Permissions)
	{
		boolean retuValue=false;
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			String sql="update admin set lmid='"+Permissions.trim()+"' where id="+String.valueOf(id).trim();
			//System.out.println(sql);
			conn.createStatement().executeUpdate(sql);
			baseDAO.closeAll(conn);
		}catch(Exception ee){ee.printStackTrace();}
		
		return retuValue;
	}
	/*
	 * ���ܣ�����ϵͳһ���û��ĵȼ�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateLevelByName(String name,String level)
	{
		boolean retuValue=false;
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("update admin set dj='"+level.trim()+"' where user='"+name.trim()+"'");
			
		}catch(Exception ee){ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�����ϵͳһ���û��ȼ�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateLevelById(int id,String level)
	{
		boolean retuValue=false;
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("update admin set dj='"+level.trim()+"' where id="+String.valueOf(id).trim());
			
		}catch(Exception ee){ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�����ϵͳһ���û��Ĳ���
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateDeptByName(String name,String dept)
	{
		boolean retuValue=false;
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("update admin set bumen='"+dept.trim()+"' where user='"+name.trim()+"'");
			
		}catch(Exception ee){ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�����ϵͳһ���û��Ĳ���,user,bumen,dj,username
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateInfoById(int id,String user,String bumen,String dj,String username)
	{
		boolean retuValue=false;
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("update admin set user='"+user.trim()+"', bumen='"+bumen.trim()+"',dj='"+dj.trim()+"',username='"+username.trim()+"' where id="+String.valueOf(id));
			retuValue=true;
		}catch(Exception ee){ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return retuValue;
	}
	/*
	 * ���ܣ�����ϵͳһ���û�����
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateDeptById(int id,String dept)
	{
		boolean retuValue=false;
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			conn.createStatement().executeUpdate("update admin set bumen='"+dept.trim()+"' where id="+String.valueOf(id).trim());
			
		}catch(Exception ee){ee.printStackTrace();}
		baseDAO.closeAll(conn);
		return retuValue;
	}
}
