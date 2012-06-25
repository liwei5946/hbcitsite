package cn.edu.hbcit.services;


import cn.edu.hbcit.util.PasswordEncodeBean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.edu.hbcit.pojo.Admin;
import cn.edu.hbcit.dao.UserDAO;
public class UserService {
	UserDAO userDAO=new UserDAO();
	/*
	 * 功能：增加系统用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 */
	@SuppressWarnings("static-access")
	public boolean addUser(String name,String pws,String bumen,String xingming,String type)
	{
	
		return userDAO.addUser(name, pws,bumen,xingming,type);
	}
	/*
	 * 功能：删除系统用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean deleteUserByName(String name)
	{
		return userDAO.deleteUserByName(name);
	}
	/*
	 * 功能：删除系统用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean deleteUserById(int id)
	{
		return userDAO.deleteUserById(id);
	}
	/*
	 * 功能：删除系统所有用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean deleteAllUser()
	{
		return userDAO.deleteAllUser();
	}
	/*
	 * 功能：检索系统用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public List<Admin> selectAllUsers()
	{
		return userDAO.selectAllUsers();
	}
	/*
	 * 功能：检索系统部门
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public List<String> selectUsersBumen(String user)
	{
		return userDAO.selectUsersBumen(user);
	}
	//
	/*
	 * 功能：检索系统一个组用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public List<Admin> selectUsersByName(String name)
	{
		return userDAO.selectUsersByName(name);
	}
	/*
	 * 功能：检索系统一个用户
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public Admin selectUsersById(int id)
	{
		return userDAO.selectUsersById(id);
	}
	/*
	 * 功能：检索系统一个用户(login)
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public Admin selectUsersByNameAndPws(String name,String pws)
	{
		return userDAO.selectUsersByNameAndPws(name, PasswordEncodeBean.MD5Encode(pws));
	}	
	/*
	 * 功能：更新系统一个用户的用户名
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean updateUserNameByName(String name)
	{
		return userDAO.updateUserNameByName(name);
	}
	/*
	 * 功能：更新系统一个用户用户名
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean updateUserNameById(int id,String name)
	{
		return userDAO.updateUserNameById(id, name);
	}
	/*
	 * 功能：更新系统一个用户的口令
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean updatePwsByName(String name,String pws)
	{
		return userDAO.updatePwsByName(name, PasswordEncodeBean.MD5Encode(pws));
	}
	/*
	 * 功能：更新系统一个用户口令
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean updatePwsById(int id,String oldpws,String newpws)
	{
		return userDAO.updatePwsById(id, PasswordEncodeBean.MD5Encode(oldpws),PasswordEncodeBean.MD5Encode(newpws));
	}
	/*
	 * 功能：更新系统一个用户的权限
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean updatePermissionsByName(String name,String Permissions)
	{
		
		return userDAO.updatePermissionsByName(name, Permissions);
	}
	/*
	 * 功能：更新系统一个用户权限
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean updatePermissionsById(int id,String Permissions)
	{
	
		return userDAO.updatePermissionsById(id, Permissions);
	}
	/*
	 * 功能：更新系统一个用户的等级
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean updateLevelByName(String name,String level)
	{
		
		return userDAO.updateLevelByName(name, level);
	}
	/*
	 * 功能：更新系统一个用户等级
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean updateLevelById(int id,String level)
	{
		return userDAO.updateLevelById(id, level);
	}
	/*
	 * 功能：更新系统一个用户的部门
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean updateDeptByName(String name,String dept)
	{
		
		return userDAO.updateDeptByName(name, dept);
	}
	/*
	 * 功能：更新系统一个用户部门
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean updateDeptById(int id,String dept)
	{
		
		return userDAO.updateDeptById(id, dept);
	}
	/*
	 * 功能：更新系统一个用户的部门,user,bumen,dj,username
	 * 编写：石彦杰  2011-9-28
	 * 修改：
	 * 
	 */
	public boolean updateInfoById(int id,String user,String bumen,String dj,String username)
	{
		return userDAO.updateInfoById(id, user, bumen, dj, username);
	}
}
