/**
* Description: car-eye车辆管理平台
* 文件名：CarSourceCache.java
* 版本信息：1.0
* 日期：2013-8-13
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.common.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.component.springhelper.BeanLocator;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @项目名称：car-eye
 * @类名称：CarSourceCache
 * @类描述：
 * @创建人：liliang
 * @创建时间：2013-8-13 下午12:47:00
 * @修改人：liliang
 * @修改时间：2013-8-13 下午12:47:00
 * @修改备注：
 * @version 1.0
 */
public class CarSourceInfoCache {
	
	/**
	 * 车辆品牌列表
	 */
	private static List<String> carFactoryList = new ArrayList<String>();
	/**
	 * 车辆品牌对应的型号列表
	 */
	private static Map<String,List<String>> carModelListOfBrand = new HashMap<String,List<String>>();
	
	/**
	 * 车型列表
	 */
	private static List<Entry> carModelList = new ArrayList<Entry>();
	
	public static void load() {
		loadCarFactoryList();	//车辆品牌
	}
	@SuppressWarnings("unchecked")
	private static void loadCarFactoryList() {
		SqlMapClient sqlMapClient = (SqlMapClient) BeanLocator.getInstance().getBean("sqlMapClient");
		try {
			 List<Entry> list = sqlMapClient.queryForList("oracle-carInfoSQL.loadCarBrandList");
			 for (Entry entry : list) {
				 String key = entry.getEntryKey();
				 if (!carFactoryList.contains(key)) {
					 carFactoryList.add(key);
				 }
				 List<String> carModelList = null;
				 if ((carModelList = carModelListOfBrand.get(key)) == null) {	//如果map取得的key的集合为空，创建
					 carModelList = new ArrayList<String>();
					 carModelList.add(entry.getEntryValue());
					 carModelListOfBrand.put(key, carModelList);
				 } else {
					 carModelList.add(entry.getEntryValue());
				 }
				 
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static List<String> getCarFactoryList() {
		return carFactoryList;
	}

	public static List<Entry> getCarModelList() {
		return carModelList;
	}

	public static List<String> getCarModelListOfBrand(String key) {
		if (key == null || key.equals(""))
			return Collections.emptyList();
		
		List<String> temp = carModelListOfBrand.get(key);
		if (temp == null)
			return Collections.emptyList();
		
		return temp;
	}
}


