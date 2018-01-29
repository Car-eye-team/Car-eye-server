/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.processing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.careye.dsdispatch.constant.Constant;
import com.careye.dsdispatch.memcached.MemCachedManager;
import com.careye.dsdispatch.redis.RedisManager;
import com.careye.dsdispatch.utlis.DateUtil;
import com.careye.dsdispatch.utlis.DispatchUtil;
import com.careye.redis.domain.CarInfo;
import com.careye.redis.domain.TerminalInfo;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：TerminalDataProcessing    
 * 类描述：终端数据处理    
 * 创建人：zr    
 * 创建时间：2015-5-20 上午10:26:10    
 * 修改人：zr    
 * 修改时间：2015-5-20 上午10:26:10    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TerminalDataProcessing_memcached {

	private final static Logger logger = Logger.getLogger(TerminalDataProcessing_memcached.class);

	/**
	 * 处理终端数据
	 * @param msgid
	 * @param jsonmsgbody
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void processing(int msgid,JSONObject jsonObject){

		try {
			if(msgid == 512){

				if(jsonObject != null){
					//终端设备号
					String terminal = jsonObject.getString("terminal");
					//空车/重车 0 空车 1 重车
					int s9 = 0;
					try {
						s9 = jsonObject.getInt("s9");
					} catch (Exception e) {
						e.printStackTrace();
					}

					int lng = jsonObject.getInt("lng");
					int lat = jsonObject.getInt("lat");
					if(lng == 0 || lat == 0){
						logger.info("经纬度信息无效,将不处理终端设备MAP信息");
					}else{

						double dlng = new BigDecimal(lng).doubleValue() / 1000000;
						double dlat = new BigDecimal(lat).doubleValue() / 1000000;

						int ilng = (int) new BigDecimal(lng).doubleValue() / 10000;
						int ilat = (int) new BigDecimal(lat).doubleValue() / 10000;

						//二分法获取经度、纬度分别在初始化经度数组、初始化纬度数组中的下标
						int m = DispatchUtil.binarySearch(Constant.LNG_ARRAY, ilng);
						int n = DispatchUtil.binarySearch(Constant.LAT_ARRAY, ilat);

						StringBuffer keyBuffer = new StringBuffer();
						keyBuffer.append(Constant.TERMINALINFO_REDIS_NAME);
						keyBuffer.append(m);
						keyBuffer.append("_");
						keyBuffer.append(n);
												
						StringBuffer mapkeybBuffer = new StringBuffer();
						mapkeybBuffer.append(Constant.TERMINAL_MAP_KEY);
						mapkeybBuffer.append(terminal);
						
						boolean delflg = false;
						
						MemCachedManager cache = MemCachedManager.getInstance();
						
						//获取上一次位置的mapkey值
						TerminalInfo tInfo = (TerminalInfo) cache.get(mapkeybBuffer.toString());
						if(tInfo == null){
							tInfo = new TerminalInfo();
							delflg = false;
						}else{
							//判断终端设备上一次mapkey与当前mapkey是否一致，如果不一次则删除上一次的值
							if(keyBuffer.toString().equals(tInfo.getMapkey())){
								delflg = false;
							}else{
								delflg = true;
							}
						}

						Map<String,CarInfo> map = (Map<String, CarInfo>) cache.get(keyBuffer.toString());
						
						if(map == null){
							map = new HashMap<String, CarInfo>();
						}
						
						if(delflg){
							map.remove(terminal);
						}
						
						logger.info("redis缓存中获取终端设备MAP,["+keyBuffer.toString()+"] 当前终端设备MAP中设备数:["+map.size()+"]个");

						//如果车辆为重车,则删除对应空车MAP中车辆信息
						if(s9 == 1){
							map.remove(terminal);
						}else{
							CarInfo carInfo = new CarInfo();
							carInfo.setKzstate(s9);
							carInfo.setLat(dlat);
							carInfo.setLng(dlng);
							carInfo.setTerminal(terminal);
							carInfo.setAddtime(DateUtil.getSQLDate());
							map.put(terminal, carInfo);
						}
						//加入至缓存中
						cache.set(keyBuffer.toString(), map);
						
						//保存当前设备号在map中的下key值
						tInfo.setMapkey(keyBuffer.toString());
						tInfo.setAddtime(DateUtil.getSQLDate());
						tInfo.setTerminal(terminal);
						cache.set(mapkeybBuffer.toString(),tInfo);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
