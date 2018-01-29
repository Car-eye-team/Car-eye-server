/**    
 * Description: 晟鸿科技通讯平台    
 * 文件名：Constant.java   
 * 版本信息：    
 * 日期：2015-5-29  
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015     
 * 版权所有    
 *    
 */
package com.careye.dssms.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 项目名称：dsparse    
 * 类名称：MyAuthenticator    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-8-25 上午09:23:32    
 * 修改人：zr    
 * 修改时间：2015-8-25 上午09:23:32    
 * 修改备注：    
 * @version 1.0  
 *
 */
public class MyAuthenticator extends Authenticator{  

	String userName=null;   
	String password=null;   

	public MyAuthenticator(){   
	}   
	public MyAuthenticator(String username, String password) {    
		this.userName = username;    
		this.password = password;    
	}    
	protected PasswordAuthentication getPasswordAuthentication(){   
		return new PasswordAuthentication(userName, password);   
	}   
}   
