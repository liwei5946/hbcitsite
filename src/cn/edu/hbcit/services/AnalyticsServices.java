package cn.edu.hbcit.services;

import java.util.TimerTask;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import cn.edu.hbcit.dao.GoogleAnalyticsDAO;
import cn.edu.hbcit.listener.AnalyticsListener;

/**
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: �ӱ���ҵְҵ����ѧԺ</p>
 *
 * @author ���� : liwei5946@gmail.com
 * @version ����ʱ�䣺Nov 10, 2011 9:36:38 PM
 */

public class AnalyticsServices extends TimerTask{
	protected final Logger log = Logger.getLogger(AnalyticsServices.class.getName());
	private ServletContext context = null;
	GoogleAnalyticsDAO ga = new GoogleAnalyticsDAO();
		
	public AnalyticsServices(ServletContext context){
		this.context = context;
	}

	public void run(){
		log.debug("��ʱ����ִ��");
		ga.getAnalytics();
		log.debug("��ʱ��ִ�����");
	}
	
}
