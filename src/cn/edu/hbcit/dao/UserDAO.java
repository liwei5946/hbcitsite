package cn.edu.hbcit.dao;

/*
 * 功能：系统用户的CRUD
 * 编写：石彦杰  2011-9-28
 * 修改：
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
	 * 功能：增加系统用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean addUser(String name,String pws,String bumen,String xingming,String type)
	{
		boolean retuValue=false;
		String userType="3";//默认一般管理员
		if(type.trim().equals("1"))//审核员
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
	 * 功能：删除系统用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
	 * 功能：删除系统用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
	 * 功能：删除系统所有用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
	 * 功能：检索系统用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public List<Admin> selectAllUsers()
	{
		List<Admin> userList= new ArrayList<Admin>();
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from admin ");
			//打包
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
	 * 功能：检索系统一个组用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public List<Admin> selectUsersByName(String name)
	{
		List<Admin> userList= new ArrayList<Admin>();
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from admin where user='"+name.trim()+"'");
			//打包
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
	 * 功能：检索系统部门
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
			//打包
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
	 * 功能：检索系统一个用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public Admin selectUsersById(int id)
	{
		Admin admin= new Admin();
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from admin where id="+String.valueOf(id)+"  and user<>'admin'");
			//打包
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
	 * 功能：检索系统一个用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public Admin selectUsersByNameAndPws(String name,String pws)
	{
		Admin admin= null;
		Connection conn=null;
		try{
			conn=baseDAO.getConn();
			rs=conn.createStatement().executeQuery("select * from admin where pass='"+pws.trim()+"'  and user='"+name.trim()+"'");
			//打包
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
	 * 功能：更新系统一个用户的用户名
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
	 * 功能：更新系统一个用户用户名
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
	 * 功能：更新系统一个用户的口令
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
	 * 功能：更新系统一个用户用户名
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
	 * 功能：更新系统一个用户的权限
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
	 * 功能：更新系统一个用户权限
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
	 * 功能：更新系统一个用户的等级
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
	 * 功能：更新系统一个用户等级
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
	 * 功能：更新系统一个用户的部门
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
	 * 功能：更新系统一个用户的部门,user,bumen,dj,username
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
	 * 功能：更新系统一个用户部门
	 * 编写：石彦杰  2011-9-28
	 * 修改：
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
