/**
 * 
 */
package com.careye.common.service.impl;

import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.domain.WorkPlace;
import com.careye.common.service.WorkPlaceService;

/**
 * @author Administrator
 *
 */
public class WorkPlaceServiceImpl extends GenericService implements WorkPlaceService{

	/**
	 * 加载基础数据
	 * loadBasicData
	 */
	public WorkPlace loadBasicData(Map map) {
		// TODO Auto-generated method stub
		return (WorkPlace)this.baseDao.queryForObject("oracle-workplaceSQL.loadBasicData", map);
	}

	/**
	 * 加载在线统计
	 * loadOnlineSta
	 */
	public WorkPlace loadOnlineSta(Map map) {
		// TODO Auto-generated method stub
		return (WorkPlace)this.baseDao.queryForObject("oracle-workplaceSQL.loadOnlineSta", map);
	}
	
	/**
	 * 营运统计
	 * loadOperationSta
	 */
	public WorkPlace loadOperationSta(Map map) {
		// TODO Auto-generated method stub
		return (WorkPlace)this.baseDao.queryForObject("oracle-workplaceSQL.loadOperationSta", map);
	}
	

	/**
	 * 服务质量
	 * loadServiceQuality
	 */
	public WorkPlace loadServiceQuality(Map map) {
		// TODO Auto-generated method stub
		return (WorkPlace)this.baseDao.queryForObject("oracle-workplaceSQL.loadServiceQuality", map);
	}

	

}
