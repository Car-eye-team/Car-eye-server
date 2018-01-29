/**
* Description: car-eye车辆管理平台
* 文件名：XMLUtils.java
* 版本信息：1.0
* 日期：2013-10-12
* Copyright car-eye车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.utils;

import java.io.InputStream;
import java.text.MessageFormat;

import javax.xml.parsers.SAXParser;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jdom.input.SAXBuilder;

/**
 * @car-eye车辆管理平台业务处理
 * @类名称：XMLUtils
 * @类描述：
 * @创建人：liliang
 * @创建时间：2013-10-12 下午05:03:31
 * @修改人：liliang
 * @修改时间：2013-10-12 下午05:03:31
 * @修改备注：
 * @version 1.0
 */
public class XMLUtils {
	
	private static SAXReader reader = new SAXReader();
	private static Document dom = null;
	public static Document load(String fileName) {
		InputStream in = PropertiesHandler.class.getClassLoader()
		.getResourceAsStream(PropertiesHandler.PROP_DIR + fileName);
		try {
			dom = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return dom;
	}
	
	
	public static void main(String[] args) {
		Document dom = load("pushInfo.xml");
		String content = dom.getRootElement().element("sourceGoodsSMS").element("content").getText();
		String detail = MessageFormat.format(content, 
				"goodsname",
				"contact",
				"phone",
				"address",
				"bulk",
				"weight",
				"shipping",
				"startTime",
				"fromCityName" + "-" + "toCityName"
				);
		System.out.println(detail);
		
	}
	

}
