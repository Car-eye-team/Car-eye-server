package com.careye.monitor.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.monitor.domain.OperateData;
import com.careye.monitor.service.OperateDataService;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-10 上午11:11:38
 * @修改人：ll
 * @修改时间：2015-11-10 上午11:11:38
 * @修改备注：
 * @version 1.0
 */
public class OperateDataServiceImpl extends GenericService implements
		OperateDataService {

	/**
	 * 分页查询营运信息
	 * @param currentPage
	 * @param everyPage
	 * @param operateData
	 * @return
	 */
	public Map getOperateDataList(final int currentPage, final int everyPage, OperateData operateData){
		return this.baseDao.findPageList("oracle-OperateDataSQL.getOperateDataList",
				"oracle-OperateDataSQL.getOperateDataListCount", operateData,currentPage,everyPage);
	}
	
	/**
	 * 条件导出营运信息
	 * @param operateData
	 * @return
	 */
	public List<OperateData> exportOperateData(OperateData operateData){
		return this.baseDao.queryForList("oracle-OperateDataSQL.getOperateDataList", operateData);
	}

}
