/**
* Description: car-eye车辆管理平台
* 文件名：MemeoCache.java
* 版本信息：1.0
* 日期：2013-8-12
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.common.domain;

import java.sql.SQLException;



/**
 * @项目名称：car-eye4S
 * @类名称：MemeoCache
 * @类描述：数据缓存类
 * @创建人：liliang
 * @创建时间：2013-8-12 下午07:26:12
 * @修改人：liliang
 * @修改时间：2013-8-12 下午07:26:12
 * @修改备注：
 * @version 1.0
 */
public class MemoCache {
	
	public static void load() {
		
		try {
			CityInfoCache.load();    		//加载地区列表缓存
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
