/**
* Description: car-eye车辆管理平台
* 文件名：CascadingMenu.java
* 版本信息：1.0
* 日期：2013-8-3
* Copyright car-eye车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


/**
 * @car-eye车辆管理平台业务处理
 * @类名称：CascadingMenu
 * @类描述：级联菜单
 * @创建人：liliang
 * @创建时间：2013-8-3 下午07:00:54
 * @修改人：liliang
 * @修改时间：2013-8-3 下午07:00:54
 * @修改备注：
 * @version 1.0
 */
public class CascadingMenu {
	private static List<String> list = new ArrayList();
	
	private static final String RESOURCE_PATH = "resource/cascadingMenu.xml";
	public static void getProvince(String code) {
		SAXParserFactory sf = SAXParserFactory.newInstance();
		sf.setValidating(true);
		try {
			SAXParser sp = sf.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
	}

}
