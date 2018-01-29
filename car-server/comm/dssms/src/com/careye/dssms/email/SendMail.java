/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dssms.email;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;

import com.careye.dssms.constant.ConfigProperties;
import com.careye.dssms.utlis.DateUtil;


/**
 *     
 * 项目名称：dsparse    
 * 类名称：SendMail    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-8-25 上午09:23:47    
 * 修改人：zr    
 * 修改时间：2015-8-25 上午09:23:47    
 * 修改备注：    
 * @version 1.0  
 *
 */
public class SendMail {
	protected static final Logger logger = Logger.getLogger(SendMail.class);
	/**
	 * 邮件发送
	 * @param toAddress 收件人地址
	 * @param title  邮件标题
	 * @param content 邮件内容
	 * @return
	 */
	public static boolean send(String content){
		
		logger.info("给"+ConfigProperties.RECEIVE_EMAIL+",发送邮件，内容："+content);
		
		boolean flag = false;
		try {
			//这个类主要是设置邮件   
			MailSenderInfo mailInfo = new MailSenderInfo();   
			//发送服务器
			mailInfo.setMailServerHost(ConfigProperties.SMTP_HOST); 
			//发送服务器端口
			mailInfo.setMailServerPort("25");    
			mailInfo.setValidate(true);   
			//发送邮箱
			mailInfo.setUserName(ConfigProperties.SEND_USER_ADDR);
			//发送邮箱密码
			mailInfo.setPassword(ConfigProperties.SEND_USER_PASSWD);
			
			//发件人邮箱地址
			mailInfo.setFromAddress(formatAddress("通讯服务器邮件服务",ConfigProperties.SEND_USER_ADDR));    
			mailInfo.setToAddress(ConfigProperties.RECEIVE_EMAIL); 
			
			mailInfo.setSubject(ConfigProperties.SEND_USER_NAME);    
			mailInfo.setContent(String.format("[%s] %s%s", DateUtil.getSQLDate(),"<br />",content));    
			SimpleMailSender sms = new SimpleMailSender();   
			//sms.sendTextMail(mailInfo);//发送文体格式    
			sms.sendHtmlMail(mailInfo);  //发送格式 
			flag = true;
			logger.info("邮件发送成功!");
			
		} catch (Exception e) {
			logger.error("邮件发送异常"+e);
		}
		
		
		return flag;
		
	}
	
	/**
	 * 格式化 Name <email@address.com> 的地址
	 * @param name 名字
	 * @param email Email地址
	 * @return 格式化的地址
	 */
	public static String formatAddress(String name, String email) {
		try {
			return String.format("%1$s <%2$s>", MimeUtility.encodeText(name, "UTF-8", "B"), email);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return email;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i = 1; i<2; i++){
			send("56gate http//www.56gate.com");
		}

	}

}
