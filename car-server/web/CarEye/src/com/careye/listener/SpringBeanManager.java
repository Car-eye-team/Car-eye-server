package com.careye.listener;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * spring 手动获取bean
 */
public class SpringBeanManager {
	
	private static WebApplicationContext context;
    
	public static void initContext(ServletContext sc){
		context=WebApplicationContextUtils.getWebApplicationContext(sc);
	}
	
	public static Object getBean(String name){
		return context.getBean(name);
	}
	
}
