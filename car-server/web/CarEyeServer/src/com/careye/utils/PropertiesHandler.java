package com.careye.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesHandler {
	
	//目录
	public static String PROP_DIR= "properties/";
	
	//邮件文件
	public static String MAIL_PROPERTIES = "mail.xml"; 
	
	//手机验证码文件
	public static String PHONE_PROPERTIES = "phone.xml"; 
	
	public static Properties loadProperties(String file) {
		
		
		Properties prop = new Properties();
		InputStream in = PropertiesHandler.class.getClassLoader()
			.getResourceAsStream(PROP_DIR + file);
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
