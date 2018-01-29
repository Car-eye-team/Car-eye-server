/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.processing;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.careye.dsdispatch.constant.Constant;
import com.careye.dsdispatch.db.CarInfoDao;
import com.careye.dsdispatch.redis.RedisManager;
import com.careye.redis.domain.CarInfo;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：CarInfoProcessing    
 * 类描述：  车辆信息业务处理 
 * 创建人：zr    
 * 创建时间：2015-5-21 下午04:26:06    
 * 修改人：zr    
 * 修改时间：2015-5-21 下午04:26:06    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class CarInfoProcessing {
	
	private final static Logger logger = Logger.getLogger(CarInfoProcessing.class);
	
	/**
	 * 业务处理
	 * @param jsonObject json数据包
	 */
	public static void processing(JSONObject jsonObject){
		
		try {
			if(jsonObject != null){
				int type = jsonObject.getInt("type");
				String terminal = jsonObject.getString("terminal");
				String carnumber = jsonObject.getString("carnumber");
				logger.info("操作类型 ["+terminal+"] ["+carnumber+"] [1 增加车辆 2 修改车辆 3 删除车辆 ] ["+type+"]");
				
				//1 增加车辆 2 修改车辆 3 删除车辆
				if(type == 1 || type == 2){
					
					//根据设备号获取最新车辆信息
					CarInfo carInfo = CarInfoDao.getCarInfo(carnumber);
					if(carInfo != null){
						RedisManager.getInstance().setObject(Constant.CARINFO_REDIS_NAME+carInfo.getTerminal(), carInfo);
					}
					
				}else {
					//删除redis缓存中的车辆信息
					RedisManager.getInstance().delObject(Constant.CARINFO_REDIS_NAME+terminal);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
