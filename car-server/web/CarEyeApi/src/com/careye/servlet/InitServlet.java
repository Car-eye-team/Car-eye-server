package com.careye.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;


public class InitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(InitServlet.class);

	public void destroy() {
		super.destroy(); 
	}

	public void init() throws ServletException {
		try {
			logger.info("初始化加载");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
