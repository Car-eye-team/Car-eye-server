/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dssms.business;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.jms.MapMessage;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.careye.dssms.constant.Constant;
import com.careye.dssms.domain.SmsInfo;
import com.careye.dssms.utlis.SmsUtil;

/**    
 *     
 * 项目名称：gtsms    
 * 类名称：ReceiveBusiness    
 * 类描述：处理业务接收    
 * 创建人：zr    
 * 创建时间：2015-6-24 下午07:35:13    
 * 修改人：zr    
 * 修改时间：2015-6-24 下午07:35:13    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ReceiveBusiness {

	private final static Logger logger = Logger.getLogger(ReceiveBusiness.class);

	/**
	 * 处理MQ消息队列接收
	 * @param msg
	 */
	public static void mqMessageReceived(MapMessage msg){

		try {

			if(msg != null){

				String jsonStr = msg.getString("jsonMsg");
				logger.info("接收到短信服务器上行消息队列消息:"+jsonStr);
				if(jsonStr != null){
					JSONObject jsonObject = JSONObject.fromObject(jsonStr);
					SmsInfo smsInfo = (SmsInfo) JSONObject.toBean(jsonObject, SmsInfo.class);
					if(smsInfo != null){
						String phone = smsInfo.getPhone();
						if(phone != null){
							String systemdate = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()); // 获取系统当前时间
							int i=(int)(Math.random()*900)+100; 
							//生成6位随机验证码
							int code = Integer.parseInt(smsInfo.getContent());
							Map <String,Object>  map = new LinkedHashMap<String,Object>();
							map.put("code",code);
							logger.info("短信发送:[手机号:"+smsInfo.getPhone()+"] [验证码:"+code+"]");
							String templateParam = JSON.toJSONString(map,false);
							//发送短信
							String outid = String.format("%s%d", systemdate,i);
							SendSmsResponse response = SmsUtil.sendSms(smsInfo.getPhone(),Constant.SMS_SIGN,Constant.SMS_TEMPLATE_CODE,templateParam,outid);
							logger.info("发送结果 ["+response.getCode()+"] ["+response.getMessage()+"]");

						}else{
							logger.info("短信发送手机号为空");
						}
					}else{
						logger.info("短信接口关闭,将不发送短信.");
					}
				}
			}else{
				logger.info("terminal为null,系统不进行处理");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("接收到业务分发上行消息队列消息处理异常"+e);
		}

	}

}
