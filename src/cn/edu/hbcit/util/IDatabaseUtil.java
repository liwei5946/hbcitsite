package cn.edu.hbcit.util;

import java.sql.ResultSet;

/*
 * 接口：IDatabaseUtil
 * 功能：所有DAO的父类，统一DAO的动作
 * 编写：石彦杰，2011-9-28
 * 修改：
 */

public interface IDatabaseUtil {
	//获取数据
	public ResultSet getDatas(String Sql);
	
	//完成insert/delete/update
	public boolean executeNonQuery(String Sql);
	
}
