package cn.edu.hbcit.services;

import java.util.ArrayList;

import net.sf.json.JSONArray;

import cn.edu.hbcit.dao.GoogleAnalyticsDAO;
import cn.edu.hbcit.dao.LogDAO;

public class LogService {
	LogDAO log4j = new LogDAO();
	GoogleAnalyticsDAO ga = new GoogleAnalyticsDAO();
	
	/**
	 * 检索日志
	 * 李玮 2011-10-31
	 * @return
	 */
	public ArrayList selectLog(){
		return log4j.selectLog();
	}
	
	/**
	 * 删除若干天前的日志记录
	 * @param beforeDate 删除多少天前的记录
	 * @return 删除记录数
	 */
	public int delLog(int beforeDate){
		return log4j.delLog(beforeDate);
		}
	
	/**
	 * 统计浏览量
	 * @param startDay
	 * @param endDay
	 * @return JSON 例：[["2011-10-01",23],["2011-10-02",29],["2011-10-03",48],["2011-10-04",88]]
	 */
	public JSONArray selectPageviewsJSON(String startDay, String endDay){
		return ga.selectPageviewsJSON(startDay, endDay);
	}
	
	/**
	 * 统计访问次数
	 * @param startDay
	 * @param endDay
	 * @return JSON 例：[["2011-10-01",23],["2011-10-02",29],["2011-10-03",48],["2011-10-04",88]]
	 */
	public JSONArray selectVisitsJSON(String startDay, String endDay){
		return ga.selectVisitsJSON(startDay, endDay);
	}
	
	
	/**
	 * 统计访问人数
	 * @param startDay
	 * @param endDay
	 * @return JSON 例：[["2011-10-01",23],["2011-10-02",29],["2011-10-03",48],["2011-10-04",88]]
	 */
	public JSONArray selectVisitorsJSON(String startDay, String endDay){
		return ga.selectVisitorsJSON(startDay, endDay);
	}


}
