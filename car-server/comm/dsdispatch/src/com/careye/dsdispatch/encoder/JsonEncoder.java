/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.encoder;

import org.apache.log4j.Logger;

import com.careye.dsdispatch.utlis.StringUtils;



/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：JsonEncoder    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-23 下午03:53:29    
 * 修改人：zr    
 * 修改时间：2015-5-23 下午03:53:29    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class JsonEncoder {
	
	private final static Logger logger = Logger.getLogger(JsonEncoder.class);
	/**
	 * 招标发送车辆 JSON
	 * @param orderid 订单号
	 * @param ters 发送设备设备号，设备号之间用”,”隔开
	 * @return
	 */
	public static String encoderBidSendCar(String orderid,String ters){
		try {
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", 20753, 0));
			buffer.append(StringUtils.jsonChar("subpacket", 0, 0));
			buffer.append(StringUtils.jsonChar("encryption",0, 0));
			buffer.append(StringUtils.jsonChar("bodysize", 0, 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr("0"),0));
			buffer.append(StringUtils.jsonChar("seq",0, 0));
			buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(orderid),0));
			buffer.append(StringUtils.jsonChar("ters",StringUtils.charStr(ters),1));
			buffer.append("}");
			logger.info("[招标发送车辆]json数据包："+buffer.toString());

			return buffer.toString();
		} catch (Exception e) {
			logger.error("编码招标发送车辆数据包编码异常",e);
			return null;
		}
	}
	
}
