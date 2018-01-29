/**
 * Description: 多森商用车平台
 * 文件名：ExportToWord.java
 * 版本信息：1.0
 * 日期：2015-7-29
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.careye.base.action.BasePageAction;
import com.careye.constant.WebConstants;


import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @项目名称：car-eye
 * @类名称：ExportToWord
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-7-29 下午03:05:20
 * @修改人：zhangrong
 * @修改时间：2015-7-29 下午03:05:20
 * @修改备注：
 * @version 1.0
 */

public class ExportToWord extends BasePageAction {

	private Configuration configuration = null;
	private static Map<String, Object> dataMap = new HashMap<String, Object>();
	private static ExportToWord exportToWord = new ExportToWord();

	public static ExportToWord getInstance() {
		if (exportToWord == null) {
			exportToWord = new ExportToWord();
		}
		return exportToWord;
	}

	public ExportToWord() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
	}

	/**
	 * @param ftlname
	 *            模板文件名
	 * @param filepath
	 *            word下载目录
	 */
	public void createWord(Map<String, Object> dataMap, String ftlname,
			String filename, String filePath) {

		try {
			// 创建配置实例
			Configuration configuration = new Configuration();
			// 设置编码
			configuration.setDefaultEncoding("UTF-8");
			// ftl模板文件统一放至 com.lun.template 包下面
			configuration.setClassForTemplateLoading(ExportToWord.class,WebConstants.FM_WORDFTL_DIC);
			// 获取模板
			Template template = configuration.getTemplate(ftlname);
			
			File dic = new File(filePath);  
	        if(!dic.exists()){
	        	dic.mkdirs();
	        }else{
	        	//清空目录下面文件
	        	File[] files = dic.listFiles();  
	            for (int i = 0; i < files.length; i++) {  
		            files[i].delete(); 
	            }
	        }
	        // 输出文件
			File outFile = new File(filePath + File.separator + filename + ".doc");
			// 将模板和数据模型合并生成文件
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile), "UTF-8"));
			// 生成文件
			template.process(dataMap, out);
			// 关闭流
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param data
	 *            数据
	 * @param ftlname
	 *            模板文件
	 * @param fileName
	 *            保存文件名称
	 */
	public void exportWord(Map<String, Object> data, String ftlname,String fileName) {
		try {

			HttpServletRequest request = getRequest();
			HttpServletResponse response = getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/msword;charset=UTF-8");
			response.reset();
			String path = request.getSession().getServletContext().getRealPath("/");

			createWord(data, ftlname, fileName, path + WebConstants.FM_FILE_DIC);

			response.setHeader("content-disposition", "attachment;filename="
					+ new String((fileName + ".doc").getBytes(), "ISO-8859-1"));
			InputStream in = new FileInputStream(path
					+ WebConstants.FM_FILE_DIC + "/" + fileName + ".doc");
			OutputStream out = response.getOutputStream();
			int len = 0;
			byte buffer[] = new byte[1024];
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.close();
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getData(Map<String, Object> dataMap) {
		dataMap.put("Title", "报警报表");
		dataMap.put("Year", "2015");
		dataMap.put("Month", "7");
		dataMap.put("Day", "29");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("num", i);
			map.put("carnumber", "粤B8888" + i);
			map.put("alarmcont", "1" + i);
			map.put("percent", "2" + i);
			list.add(map);
		}
		dataMap.put("list", list);
	}

}
