/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.utlis;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;

import com.careye.dsparse.email.SendMail;


/**    
 *     
 * 项目名称：dscomm    
 * 类名称：ExceptionUtil    
 * 类描述：异常处理类    
 * 创建人：zr    
 * 创建时间：2015-5-9 下午02:57:49    
 * 修改人：zr    
 * 修改时间：2015-5-9 下午02:57:49    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ExceptionUtil {

	private final static Logger logger = Logger.getLogger(ExceptionUtil.class);	
	/**    
	 * 得到Exception所在代码的行数    
	 * 如果没有行信息,返回null    
	 */      
	public static void getInfo(Exception e){  
		StringBuffer errormsg = new StringBuffer();
		/*StackTraceElement[] trace =e.getStackTrace();   
		for (StackTraceElement stackTraceElement : trace) {
			String exclass = stackTraceElement.getClassName();
			String method = stackTraceElement.getMethodName();
			errormsg.append("[类:" + exclass + "]调用"+ method + "在第" + stackTraceElement.getLineNumber()+ "行代码处发生异常,异常类型:" + e.getClass().getName());
			errormsg.append("<br />");
		}*/
		
		
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
         PrintStream pout = new PrintStream(out);
         e.printStackTrace(pout);
         String ret = new String(out.toByteArray());
         pout.close();
         try {
              out.close();
         } catch (Exception ex) {
         }
         errormsg.append(ret);
		logger.error(errormsg.toString());
		//发送异常邮件
		SendMail.send(errormsg.toString());
	}

}
