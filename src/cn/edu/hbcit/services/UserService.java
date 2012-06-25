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
	 * ���ܣ�����ϵͳ�û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 */
	@SuppressWarnings("static-access")
	public boolean addUser(String name,String pws,String bumen,String xingming,String type)
	{
	
		return userDAO.addUser(name, pws,bumen,xingming,type);
	}
	/*
	 * ���ܣ�ɾ��ϵͳ�û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean deleteUserByName(String name)
	{
		return userDAO.deleteUserByName(name);
	}
	/*
	 * ���ܣ�ɾ��ϵͳ�û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean deleteUserById(int id)
	{
		return userDAO.deleteUserById(id);
	}
	/*
	 * ���ܣ�ɾ��ϵͳ�����û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean deleteAllUser()
	{
		return userDAO.deleteAllUser();
	}
	/*
	 * ���ܣ�����ϵͳ�û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public List<Admin> selectAllUsers()
	{
		return userDAO.selectAllUsers();
	}
	/*
	 * ���ܣ�����ϵͳ����
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public List<String> selectUsersBumen(String user)
	{
		return userDAO.selectUsersBumen(user);
	}
	//
	/*
	 * ���ܣ�����ϵͳһ�����û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public List<Admin> selectUsersByName(String name)
	{
		return userDAO.selectUsersByName(name);
	}
	/*
	 * ���ܣ�����ϵͳһ���û�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public Admin selectUsersById(int id)
	{
		return userDAO.selectUsersById(id);
	}
	/*
	 * ���ܣ�����ϵͳһ���û�(login)
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public Admin selectUsersByNameAndPws(String name,String pws)
	{
		return userDAO.selectUsersByNameAndPws(name, PasswordEncodeBean.MD5Encode(pws));
	}	
	/*
	 * ���ܣ�����ϵͳһ���û����û���
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateUserNameByName(String name)
	{
		return userDAO.updateUserNameByName(name);
	}
	/*
	 * ���ܣ�����ϵͳһ���û��û���
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateUserNameById(int id,String name)
	{
		return userDAO.updateUserNameById(id, name);
	}
	/*
	 * ���ܣ�����ϵͳһ���û��Ŀ���
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updatePwsByName(String name,String pws)
	{
		return userDAO.updatePwsByName(name, PasswordEncodeBean.MD5Encode(pws));
	}
	/*
	 * ���ܣ�����ϵͳһ���û�����
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updatePwsById(int id,String oldpws,String newpws)
	{
		return userDAO.updatePwsById(id, PasswordEncodeBean.MD5Encode(oldpws),PasswordEncodeBean.MD5Encode(newpws));
	}
	/*
	 * ���ܣ�����ϵͳһ���û���Ȩ��
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updatePermissionsByName(String name,String Permissions)
	{
		
		return userDAO.updatePermissionsByName(name, Permissions);
	}
	/*
	 * ���ܣ�����ϵͳһ���û�Ȩ��
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updatePermissionsById(int id,String Permissions)
	{
	
		return userDAO.updatePermissionsById(id, Permissions);
	}
	/*
	 * ���ܣ�����ϵͳһ���û��ĵȼ�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateLevelByName(String name,String level)
	{
		
		return userDAO.updateLevelByName(name, level);
	}
	/*
	 * ���ܣ�����ϵͳһ���û��ȼ�
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateLevelById(int id,String level)
	{
		return userDAO.updateLevelById(id, level);
	}
	/*
	 * ���ܣ�����ϵͳһ���û��Ĳ���
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateDeptByName(String name,String dept)
	{
		
		return userDAO.updateDeptByName(name, dept);
	}
	/*
	 * ���ܣ�����ϵͳһ���û�����
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateDeptById(int id,String dept)
	{
		
		return userDAO.updateDeptById(id, dept);
	}
	/*
	 * ���ܣ�����ϵͳһ���û��Ĳ���,user,bumen,dj,username
	 * ��д��ʯ���  2011-9-28
	 * �޸ģ�
	 * 
	 */
	public boolean updateInfoById(int id,String user,String bumen,String dj,String username)
	{
		return userDAO.updateInfoById(id, user, bumen, dj, username);
	}
}
