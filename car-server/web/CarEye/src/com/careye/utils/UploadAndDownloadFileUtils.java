/**
* Description: car-eye车辆管理平台
* 文件名：UploadAndDownloadFileUtils.java
* 版本信息：1.0
* 日期：2013-8-19
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import com.careye.constant.Constant;
import com.careye.listener.ApplicationContextListener;

/**
 * @项目名称：car-eye
 * @类名称：UploadAndDownloadFileUtils
 * @类描述：图片下载
 * @创建人：zr
 * @创建时间：2013-8-19 下午08:10:05
 * @修改人：zr
 * @修改时间：2013-8-19 下午08:10:05
 * @修改备注：
 * @version 1.0
 */
public class UploadAndDownloadFileUtils {
	
	private static final Logger LOG = Logger.getLogger(UploadAndDownloadFileUtils.class);


	public static String uploadFile(File file, String fileName, final String savepath) {

		String url = null;
		InputStream is = null;
		OutputStream os = null;
		try {

			is = new FileInputStream(file);

			String name = getNewName(fileName);

			String root = ApplicationContextListener.getServletContext().getRealPath("/" + savepath);

			url = savepath + "/" + name;

			File destFile = new File(root, name);

			os = new FileOutputStream(destFile);

			byte[] buffer = new byte[1240];

			int length = 0;

			while ((length = is.read(buffer)) > 0)
			{
				os.write(buffer, 0, length);
			}

		} catch (Exception e) {
			url = null;
			System.err.println("上传文件异常！" + e);
		}finally {
			try {
				if(is!=null){
					is.close();
				}
				if(os!=null){
					os.close();
				}

			} catch (IOException e) {
				url = null;
				LOG.error("输入输出流关闭异常！" + e);
			}

		}


		return url;
	}

	/**重新命名文件*/
	private static String getNewName(String fileName)
	{
		int pos = fileName.lastIndexOf(".");
		String name = String.valueOf(getCurrentTimeMillis());
		name = name + fileName.substring(pos);		
		return name;
	}

	/**确保返回时间片唯一*/
	public static synchronized long getCurrentTimeMillis() {
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			LOG.error("线程休眠异常！" + e);
		}
		return System.currentTimeMillis();
	}
	
	/**
	 * 下载图片
	 */
	public static String downloadImg(String url,String path){
		
		System.out.println("url-===================================="+url);
		String savePath = null;
		try {
			URL con = new URL(url.replaceAll(":100", ""));
			URLConnection conn = con.openConnection();
			InputStream is = conn.getInputStream();
			String fileName = getNewName(url);
			savePath =  path + fileName;
			fileName = ApplicationContextListener.getServletContext().getRealPath(savePath);
			BufferedOutputStream  fos = new BufferedOutputStream(new FileOutputStream(fileName));
			byte[] buffer = new byte[10240]; 
			int length = 0;
			while (-1 != (length =is.read(buffer, 0, buffer.length))){
				fos.write(buffer, 0, length);
			}
			fos.flush();
			fos.close();
			is.close();
			if (fos != null){
				fos.close();
			}
			if (is != null){
				is.close();
			}
		} catch (Exception e) {
			savePath = null;
			LOG.error("下载图片异常！", e);
		}		
		return savePath;
	}

}
