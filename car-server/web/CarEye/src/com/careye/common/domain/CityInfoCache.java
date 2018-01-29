/**
* Description: car-eye车辆管理平台
* 文件名：LoadCityInfo.java
* 版本信息：1.0
* 日期：2013-8-10
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
 * @类名称：LoadCityInfoEntry
 * @类描述：读取地区信息到内存，加快查找速度
 * @创建人：liliang
 * @创建时间：2013-8-10 上午10:47:27
 * @修改人：liliang
 * @修改时间：2013-8-10 上午10:47:27
 * @修改备注：
 * @version 1.0
 */
public class CityInfoCache {
	/**
	 * 省级行政区,用于列表
	 */
	private static List<Entry> provinceList = new ArrayList<Entry>();
	
	/**
	 * 与list不同之处在于，该map用于查询
	 */
	private static Map<String, String> provinceMap = new HashMap<String, String>();
	/**
	 * 市级行政区,键值对 存储省级行政区下级区域
	 * key是省级行政区的行政编号
	 */
	private static Map<String, List<Entry>> cityMap = new HashMap<String, List<Entry>>(); //列表用
	
	/**
	 * 该集合用于查询城市信息，加快速度
	 */
	private static Map<String, Map<String, String>> cityQueryMap = new HashMap<String, Map<String, String>>();
	
	
	/**
	 * 县级行政区，键值对 存储市级行政区下级区域
	 * key是市级行政区的编号
	 */
	private static  Map<String, List<Entry>> districtMap = new HashMap<String, List<Entry>>(); //列表用
	
	/**
	 * 该集合用于查询县级地区信息，加快速度
	 */
	private static Map<String, Map<String, String>> districtQueryMap = new HashMap<String, Map<String, String>>();
	
	/**
	 * 初始化加载
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static void load() throws SQLException {
		Map<String,String> queryParams = new HashMap<String, String>();
		SqlMapClient sqc = (SqlMapClient) BeanLocator.getInstance().getBean("sqlMapClient");
		if (sqc == null)
			throw new NullPointerException("获取SqlMapClient失败。。。");
		sqc.startTransaction();
		//读取省级行政区
		queryParams.put("clevel", "1");
		List<CityInfoEntry> provinces = sqc.queryForList("oracle-cityInfoSQL.getCityListForMemCache", queryParams);
		for(CityInfoEntry c : provinces) {
			provinceList.add(new Entry(c.getSzcode(), c.getCnname()));
			provinceMap.put(c.getSzcode(), c.getCnname());
		}
		//读取市级行政区
		queryParams.put("clevel", "2");
		List<CityInfoEntry> citys = sqc.queryForList("oracle-cityInfoSQL.getCityListForMemCache", queryParams);
		for(CityInfoEntry c : citys) {
			String parentszcode = c.getParentszcode();
			
			List<Entry> list = cityMap.get(parentszcode);
			if (list == null) {
				list = new ArrayList<Entry>();
				cityMap.put(parentszcode, list);
			}
			list.add(new Entry(c.getSzcode(), c.getCnname()));
			
			Map<String, String> map = cityQueryMap.get(parentszcode);         //map
			if (map == null) {
				map = new HashMap<String, String>();
				cityQueryMap.put(parentszcode, map);
			}
			map.put(c.getSzcode(), c.getCnname());
			
			
		}
		//读取县级行政区
		queryParams.put("clevel", "3");
		List<CityInfoEntry>  district= sqc.queryForList("oracle-cityInfoSQL.getCityListForMemCache", queryParams);
		for(CityInfoEntry c :  district) {
			String parentszcode = c.getParentszcode();
			
			List<Entry> list = districtMap.get(parentszcode);
			if (list == null) {
				list = new ArrayList<Entry>();
				 districtMap.put(parentszcode, list);
			}
			list.add(new Entry(c.getSzcode(), c.getCnname()));
			
			Map<String, String> map = districtQueryMap.get(parentszcode);         //map
			if (map == null) {
				map = new HashMap<String, String>();
				districtQueryMap.put(parentszcode, map);
			}
			map.put(c.getSzcode(), c.getCnname());
			
		}
		sqc.commitTransaction();
		
	}
		/**
		 * 得到省级行政列表
		 * @return
		 */
        public static  List<Entry> getProvinceList() {
                if (provinceList == null)
                	return Collections.emptyList();
                
                return provinceList;
        }
        
        /**
        * 通过父级行政编码得到市级列表
        */
        public static List<Entry> getCityListByParentCode(String parentCode) {
        	
        	if (parentCode == null || parentCode.equals("-1")) {
        		return Collections.emptyList();
        	}
        	List<Entry> list = cityMap.get(parentCode);
        	
        		
        	if (list == null)
        		return Collections.emptyList();
        		
                return list;

        }
        /**
        * 通过父级行政编码得到县级区域列表
        */
        public static List<Entry> getDistrictByParentCode(String parentCode) {
                
            	if (parentCode == null || parentCode.equals("-1")) {
            		return Collections.emptyList();
            	}
            	List<Entry> list = districtMap.get(parentCode);
            	
            		
            	if (list == null)
            		return Collections.emptyList();
            		
                    return list;
        }
        
        
        /**
         * 通过省行政编号得到名称
         * @param code  省级行政区编号
         * @return
         */
        public static String getProvinceNameByCode(String code) {
        	if (code == null || code.equals("-1"))
        		return null;
        	
        	return provinceMap.get(code);
        }
        
        /**
         * 通过编号得到城市名称，为了加快查询速度，需要传递一个父级省级行政编码参数
         * @param parentCode
         * @param code
         * @return
         */
        public static String getCityNameByCode(String parentCode, String code) {
        	if (parentCode == null || code == null || parentCode.equals("-1") || code.equals("-1"))
        		return null;
        	
        	Map<String, String> map = cityQueryMap.get(parentCode);
        	return map == null ? null : map.get(code);
        }
        
        /**
         * 通过编号得到县级区域名称，为了加快查询速度，需要传递一个父级市级行政编码参数
         * @param parentCode
         * @param code
         * @return
         */
        public static String getDistrictNameByCode(String parentCode, String code) {
        	if (parentCode == null || code == null || parentCode.equals("-1") || code.equals("-1"))
        		return null;
        	
        	Map<String, String> map = districtQueryMap.get(parentCode);
        	return map == null ? null : map.get(code);
        }
	

}
