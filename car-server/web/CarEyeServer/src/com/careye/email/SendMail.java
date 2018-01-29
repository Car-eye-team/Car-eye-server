/**    
 * Description: car-eye车辆管理平台    
 * 文件名：SendMail.java   
 * 版本信息：1.0    
 * 日期：2013-7-20  
 * Copyright car-eye车辆管理平台 Copyright (c) 2013     
 * 版权所有    
 *    
 */
package com.careye.email;

import java.io.UnsupportedEncodingException;
import javax.mail.internet.MimeUtility;
import org.apache.log4j.Logger;

import com.careye.constant.ConfigProperties;

/**    
 *     
 * car-eye车辆管理平台业务处理
 * 类名称：SendMail
 * 类描述：邮件发送
 * 创建人：zr
 * 创建时间：2013-7-20 下午03:13:04
 * 修改人：zr 
 * 修改时间：2013-7-20 下午03:13:04
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
	public static boolean send(String toAddress,String title,String content){
		
		logger.info("给"+toAddress+",发送邮件，标题："+title+",内容："+content);
		boolean flag = false;
		try {
			//这个类主要是设置邮件   
			MailSenderInfo mailInfo = new MailSenderInfo();   
			//发送服务器
			mailInfo.setMailServerHost(ConfigProperties.smtphost); 
			//发送服务器端口
			mailInfo.setMailServerPort("25");    
			mailInfo.setValidate(true);   
			//发送邮箱
			mailInfo.setUserName(ConfigProperties.sendUserAdr);
			//发送邮箱密码
			mailInfo.setPassword(ConfigProperties.sendUserPassword);
			
			//发件人邮箱地址
			mailInfo.setFromAddress(formatAddress("FMS",ConfigProperties.sendUserAdr));    
			mailInfo.setToAddress(toAddress); 
			
			mailInfo.setSubject(title);    
			mailInfo.setContent(content);    
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
			send("zhangrong@duosensoft.com","收到没-"+i,"56gate http//www.56gate.com<a href='http://www.baidu.com'>百度</a>");
		}

	}

}
