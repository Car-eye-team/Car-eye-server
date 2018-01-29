package com.careye.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.careye.constant.ConfigProperties;
import com.careye.constant.Constant;
import com.careye.mq.MqUtil;
import com.careye.threadpool.ThreadPoolManager;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(InitServlet.class);

	/**
	 * 销毁
	 */
	public void destroy() {
		super.destroy(); 
	}

	/**
	 * 系统初始化
	 */
	public void init() throws ServletException {
		try {
			Constant.BASE_PATH = this.getServletContext().getRealPath("/");

			//初始化系统线程池
			ThreadPoolManager.getInstance().initThreadPool();

			logger.info("=========================系统初始化JAVA通讯平台普通消息MQ===========================");
			MqUtil.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
