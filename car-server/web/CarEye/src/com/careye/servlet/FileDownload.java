package com.careye.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.careye.constant.WebConstants;
import com.careye.system.domain.BlocUser;

public class FileDownload extends HttpServlet {
	private int bufferSize = 2048;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
	
			// 文件名必须带后缀名，否则 下载后是未知类型的文件
			String filename = new String(request.getParameter("filename").getBytes("iso8859-1"),"utf-8");
			String path = new String(request.getParameter("path").getBytes("iso8859-1"),"utf-8");
			response.reset();   
			//设置内容文件的类型  一般有pdf , word execl 各有不同的设置。  
			response.setContentType("APPLICATION/OCTET-STREAM");  
			
			path = request.getSession().getServletContext().getRealPath("/") + path;

			BlocUser sessionuser = (BlocUser) request.getSession(true).getAttribute(WebConstants.LOGIN_USER);
			if (sessionuser != null) {
				// 检测文件是否存在
				File file = new File(path);

				if (!file.exists()) {
					return;
				} else {
					response.reset();
					// 将 文件流写入到前端浏览器
					response.setHeader("Content-disposition","attachment;filename=" + convertFileName(filename));
					ServletOutputStream sops = response.getOutputStream();
					FileInputStream fis = new FileInputStream(file);
					copyStream(fis, sops, true);
					fis.close();
					sops.close();
					fis = null;
					sops = null;
					file = null;
				}

			} else {
				response.reset();  
				String loginaddr = request.getContextPath() + "/index.jsp";
				response.getWriter().print("<script type=\"text/javascript\">top.location.href='"+ loginaddr + "';</script>");
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}

	}
	
	/**
	 * 复制流 到 前端浏览器
	 * @param source 源文件输入流
	 * @param dest 输出流
	 * @param flush
	 * @return
	 */
	private final long copyStream(InputStream source, OutputStream dest,boolean flush) {
		int bytes;
		long total = 0l;
		byte[] buffer = new byte[bufferSize];
		try {
			while ((bytes = source.read(buffer)) != -1) {
				if (bytes == 0) {
					bytes = source.read();
					if (bytes < 0)
						break;
					dest.write(bytes);
					if (flush)
						dest.flush();
					total += bytes;
				}
				dest.write(buffer, 0, bytes);
				if (flush)
					dest.flush();
				total += bytes;
			}

		} catch (IOException e) {
			throw new RuntimeException("IOException caught while copying.", e);
		}
		return total;
	}

	// 转换文件名的方法 在strust.xml中会用到
	public String convertFileName(String cfilename) {
		try {
			cfilename = new String(cfilename.getBytes(), "ISO-8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cfilename;
	}

	public void init() throws ServletException {
	}

}
