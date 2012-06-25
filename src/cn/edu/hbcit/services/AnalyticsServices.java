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
 * <p>Company: 河北工业职业技术学院</p>
 *
 * @author 作者 : liwei5946@gmail.com
 * @version 创建时间：Nov 10, 2011 9:36:38 PM
 */

public class AnalyticsServices extends TimerTask{
	protected final Logger log = Logger.getLogger(AnalyticsServices.class.getName());
	private ServletContext context = null;
	GoogleAnalyticsDAO ga = new GoogleAnalyticsDAO();
		
	public AnalyticsServices(ServletContext context){
		this.context = context;
	}

	public void run(){
		log.debug("定时器已执行");
		ga.getAnalytics();
		log.debug("定时器执行完毕");
	}
	
}
