package com.careye.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.mina.filter.reqres.Request;

import com.careye.common.domain.MemoCache;
import com.careye.constant.Constant;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(InitServlet.class);
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		try {
			logger.info("系统加载缓存");
			MemoCache.load();           //加载缓存
			Constant.BASE_PATH = this.getServletContext().getRealPath("/");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
