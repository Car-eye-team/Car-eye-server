/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsbusiness.email;

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
