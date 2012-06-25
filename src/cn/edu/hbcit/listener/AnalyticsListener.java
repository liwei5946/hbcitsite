package cn.edu.hbcit.listener;

import java.util.Calendar;
import java.util.Timer;//定时器类
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
 * Company: 河北工业职业技术学院
 * </p>
 * 
 * @author 作者 : liwei5946@gmail.com
 * @version 创建时间：Nov 10, 2011 9:25:35 PM
 */

public class AnalyticsListener implements ServletContextListener{
	private Timer timer = null;
	//public static final long PERIOD_DAY = 24 * 60 * 60 * 1000;  //每日执行
	public static final long PERIOD_DAY = 2 * 60 * 60 * 1000; //每2小时执行一次
	//public static final long PERIOD_DAY = 10 * 60 * 1000; //每10分钟执行一次
	//设置启动时间为2点;
	private static final int hours = 2;
	private static final int minutes = 0;
	private static final int seconds = 0;
	protected final Logger log = Logger.getLogger(AnalyticsListener.class.getName());

	public void contextInitialized(ServletContextEvent event) {
		// 在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
		timer = new Timer(true);
		// 添加日志，可在tomcat日志中查看到
		event.getServletContext().log("定时器已启动");
		//log.debug("定时器已启动");
		
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, seconds);   		
		// 调用GoogleTimer，0表示任务无延迟，5*1000表示每隔5秒执行任务，60*60*1000表示一个小时。
		//timer.schedule(new AnalyticsServices(event.getServletContext()), calendar.getTime(), PERIOD_DAY);
		timer.schedule(new AnalyticsServices(event.getServletContext()), 0, PERIOD_DAY);
		event.getServletContext().log("已经添加任务");
		//log.debug("定时器已添加");
	}

	public void contextDestroyed(ServletContextEvent event) {// 在这里关闭监听器，所以在这里销毁定时器。
		timer.cancel();
		event.getServletContext().log("定时器销毁");
	}

}
