package cn.edu.hbcit.services;

import java.util.ArrayList;

import net.sf.json.JSONArray;

import cn.edu.hbcit.dao.GoogleAnalyticsDAO;
import cn.edu.hbcit.dao.LogDAO;

public class LogService {
	LogDAO log4j = new LogDAO();
	GoogleAnalyticsDAO ga = new GoogleAnalyticsDAO();
	
	/**
	 * ������־
	 * ���� 2011-10-31
	 * @return
	 */
	public ArrayList selectLog(){
		return log4j.selectLog();
	}
	
	/**
	 * ɾ��������ǰ����־��¼
	 * @param beforeDate ɾ��������ǰ�ļ�¼
	 * @return ɾ����¼��
	 */
	public int delLog(int beforeDate){
		return log4j.delLog(beforeDate);
		}
	
	/**
	 * ͳ�������
	 * @param startDay
	 * @param endDay
	 * @return JSON ����[["2011-10-01",23],["2011-10-02",29],["2011-10-03",48],["2011-10-04",88]]
	 */
	public JSONArray selectPageviewsJSON(String startDay, String endDay){
		return ga.selectPageviewsJSON(startDay, endDay);
	}
	
	/**
	 * ͳ�Ʒ��ʴ���
	 * @param startDay
	 * @param endDay
	 * @return JSON ����[["2011-10-01",23],["2011-10-02",29],["2011-10-03",48],["2011-10-04",88]]
	 */
	public JSONArray selectVisitsJSON(String startDay, String endDay){
		return ga.selectVisitsJSON(startDay, endDay);
	}
	
	
	/**
	 * ͳ�Ʒ�������
	 * @param startDay
	 * @param endDay
	 * @return JSON ����[["2011-10-01",23],["2011-10-02",29],["2011-10-03",48],["2011-10-04",88]]
	 */
	public JSONArray selectVisitorsJSON(String startDay, String endDay){
		return ga.selectVisitorsJSON(startDay, endDay);
	}


}
