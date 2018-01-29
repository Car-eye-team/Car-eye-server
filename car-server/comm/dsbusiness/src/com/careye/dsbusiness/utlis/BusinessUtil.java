/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsbusiness.utlis;

import com.careye.dsbusiness.constant.ConfigProperties;


/**    
 *     
 * 项目名称：dsbusiness    
 * 类名称：BusinessUtil    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-25 下午05:02:33    
 * 修改人：zr    
 * 修改时间：2015-5-25 下午05:02:33    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BusinessUtil {
	
	/**
	 * 判断消息ID是否需要发到调度服务器
	 * @param msgid 消息ID
	 * @return true 发送 false 不发送
	 */
	public static boolean judgetMsgid(int msgid){

		try {
			String[] dmsgid = ConfigProperties.CALL_MSGID.split(",");
			int size = dmsgid.length;
			for (int i = 0; i < size; i++) {
				if(Integer.parseInt(dmsgid[i]) == msgid){
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
