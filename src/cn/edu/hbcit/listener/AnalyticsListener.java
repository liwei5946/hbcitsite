package cn.edu.hbcit.listener;

import java.util.Calendar;
import java.util.Timer;//��ʱ����
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import cn.edu.hbcit.services.AnalyticsServices;

/**
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * 
 * <p>
 * Company: �ӱ���ҵְҵ����ѧԺ
 * </p>
 * 
 * @author ���� : liwei5946@gmail.com
 * @version ����ʱ�䣺Nov 10, 2011 9:25:35 PM
 */

public class AnalyticsListener implements ServletContextListener{
	private Timer timer = null;
	//public static final long PERIOD_DAY = 24 * 60 * 60 * 1000;  //ÿ��ִ��
	public static final long PERIOD_DAY = 2 * 60 * 60 * 1000; //ÿ2Сʱִ��һ��
	//public static final long PERIOD_DAY = 10 * 60 * 1000; //ÿ10����ִ��һ��
	//��������ʱ��Ϊ2��;
	private static final int hours = 2;
	private static final int minutes = 0;
	private static final int seconds = 0;
	protected final Logger log = Logger.getLogger(AnalyticsListener.class.getName());

	public void contextInitialized(ServletContextEvent event) {
		// �������ʼ������������tomcat������ʱ�����������������������ʵ�ֶ�ʱ������
		timer = new Timer(true);
		// �����־������tomcat��־�в鿴��
		event.getServletContext().log("��ʱ��������");
		//log.debug("��ʱ��������");
		
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, seconds);   		
		// ����GoogleTimer��0��ʾ�������ӳ٣�5*1000��ʾÿ��5��ִ������60*60*1000��ʾһ��Сʱ��
		//timer.schedule(new AnalyticsServices(event.getServletContext()), calendar.getTime(), PERIOD_DAY);
		timer.schedule(new AnalyticsServices(event.getServletContext()), 0, PERIOD_DAY);
		event.getServletContext().log("�Ѿ��������");
		//log.debug("��ʱ�������");
	}

	public void contextDestroyed(ServletContextEvent event) {// ������رռ��������������������ٶ�ʱ����
		timer.cancel();
		event.getServletContext().log("��ʱ������");
	}

}
