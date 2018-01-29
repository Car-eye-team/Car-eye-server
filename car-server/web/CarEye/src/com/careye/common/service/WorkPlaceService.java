/**
 * 
 */
package com.careye.common.service;

import java.util.Map;

import com.careye.common.domain.WorkPlace;


/**
 * @author Administrator
 *
 */
public interface WorkPlaceService {
	
	/**
	 * 加载基础数据
	 * loadBasicData
	 */
	public WorkPlace loadBasicData(Map map);
	
	
	/**
	 * 加载在线统计
	 * loadOnlineSta
	 */
	public WorkPlace loadOnlineSta(Map map);
	
	/**
	 * loadOperationSta
	 * 营运统计
	 */
	public WorkPlace loadOperationSta(Map map);
	
	/**
	 * 服务质量
	 * loadServiceQuality
	 */
	public WorkPlace loadServiceQuality(Map map);
}
