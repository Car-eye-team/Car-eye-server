/**
* Description: 多森商用车平台
* 文件名：CarParamService.java
* 版本信息：1.0
* 日期：2014-7-13
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.api.service;

import java.util.List;
import java.util.Map;

import com.careye.api.domain.WeatherInfo;



/**
 * @项目名称：car-eyeAPI
 * @类名称：ApiService
 * @类描述：API接口
 * @创建人：zhangrong
 * @创建时间：2015-8-17 下午02:55:49
 * @修改人：zhangrong
 * @修改时间：2015-8-17 下午02:55:49
 * @修改备注：
 * @version 1.0
 */
public interface TspService {
	
	/**
	 * 获取天气预报当天详情
	 */
	public WeatherInfo getWeatherToday(Map map);
	
	/**
	 * 获取天气预报未来详情
	 */
	public List getWeatherForcastList(Map map);
	
	
}
