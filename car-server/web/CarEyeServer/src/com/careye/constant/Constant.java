package com.careye.constant;

import java.util.HashMap;
import java.util.Map;

import com.careye.car.domain.CarInfo;


public class Constant {
	
	/**
	 * car-eye车辆管理平台平台业务类型
	 */
	public static final int TAXI_DEVICE_TYPE = 29;
	/**
	 * 手机app业务类型
	 */
	public static final int APP_DEVICE_TYPE = 27;

	/**
	 * 连接主机的超时时间（单位：毫秒）
	 */
	public static final int CONNECT_TIMEOUT = 60000;			
	
	/**
	 * 从主机读取数据的超时时间（单位：毫秒）
	 */
	public static final int READ_TIMEOUT = 60000;
	
	
	/**
	 * 离线时间超过7天，状态变成长离线(单位： 天)
	 */
	public static final int CAR_OFF_LINE_TIME = 7;
	
	
	/**
	 * 在线到离线的时间差(单位： 分钟)
	 */
	public static final int CAR_ONLINE_TIME = 5;
	
	/**
	 * 系统路径
	 */
	public static String BASE_PATH = "";
	
	/**
	 * 订单超时时间设置（分钟）
	 */
	public static int ORDER_OUTDATE = 30;
	
	/**
	 * 电召费多媒体图片路径
	 */
	public static String MEDIA_DATA_PATH = "/picture/mediadata/";
	
	/**微信订单开头数字*/
	public static Integer WECHAT_ORDER = 5;
	
	/**乐嘀订单开头数字*/
	public static Integer APP_ORDER = 3;
	
	/**车辆缓存信息**/
    public static Map<String, CarInfo> CAR_MAP = new HashMap<String, CarInfo>();
	
	
}
