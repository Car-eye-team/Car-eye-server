/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.constant;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：Constant    
 * 类描述：公用配置    
 * 创建人：zr    
 * 创建时间：2015-5-19 下午07:29:23    
 * 修改人：zr    
 * 修改时间：2015-5-19 下午07:29:23    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class Constant {
	
	/**左上经度(北京 115.75，39.44  117.48,41.63 深圳：113.46,22.27 114.37,22.52)*//*
	public static final double LEFT_LNG = 113.46;
	
	*//**左上纬度*//*
	public static final double LEFT_LAT = 22.27;
	
	*//**右下经度*//*
	public static final double RIGHT_LNG = 114.37;
	
	*//**右下纬度*//*
	public static final double RIGHT_LAT = 22.86;*/
	
	/**经度数组*/
	public static int[] LNG_ARRAY = null;
	
	/**纬度数组*/
	public static int[] LAT_ARRAY = null;
	
	/**搜索当前位置1千米范围内的空车信息*/
	public static final int DISTANCE = 1;
	
	/**车辆信息在redis缓存中的名称  格式：carinfo_终端设备号*/
	public static final String CARINFO_REDIS_NAME = "carinfo_";
	
	/**车辆终端信息在redis缓存中的名称  格式：terminalmap_经度数组中下标纬度数组下标*/
	public static final String TERMINALINFO_REDIS_NAME = "terminalmap_";
	
	/**设备号在map中的key值*/
	public static final String TERMINAL_MAP_KEY = "terminalmapkey_";
	
	/**新调度规则在redis缓存中的名称*/
	public static final String DISPATCH_RULE_NEW = "dispatch_new_";
	
	/**历史调度规则在redis缓存中的名称*/
	public static final String DISPATCH_RULE_OLD = "dispatch_old_";
	
	/**调度订单在redis缓存中的名称*/
	public static final String DISPATCH_ORDERID = "orderid_";
	
	/**预约订单号在redis缓存的名称*/
	public static final String BOOKING_ORDERID = "bookingorderid";
	
	/**终端路由信息在redis缓存的名称*/
	public static final String CLIENT_ROUTE = "clientroute_";
	
	/**最后一次接收接入服务器上行消息时间,初始化为当前时间*/
	public static String LAST_RECEIVE_COMM_HEARTBEAT_TIME = null;
	
		
}
