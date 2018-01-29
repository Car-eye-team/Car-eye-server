/**    
 * Description: car-eye车辆管理平台    
 * 文件名：MyAuthenticator.java   
 * 版本信息：1.0    
 * 日期：2013-7-20  
 * Copyright car-eye车辆管理平台 Copyright (c) 2013     
 * 版权所有    
 *    
 */
package com.careye.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**    
 *     
 * car-eye车辆管理平台业务处理    
 * 类名称：MyAuthenticator    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2013-7-20 下午03:11:36    
 * 修改人：zr    
 * 修改时间：2013-7-20 下午03:11:36    
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
