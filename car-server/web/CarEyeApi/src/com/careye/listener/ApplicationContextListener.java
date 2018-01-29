package com.careye.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class ApplicationContextListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(ApplicationContextListener.class);

	private static ServletContext servletContext;

	public void contextInitialized(ServletContextEvent event) {

		servletContext = event.getServletContext(); /***获取servletContext, 该对象不允许再次赋值***/

		SpringBeanManager.initContext(event.getServletContext());

	}

	public void contextDestroyed(ServletContextEvent event) {

	}

	public static ServletContext getServletContext() {
		return servletContext;
	}


}
