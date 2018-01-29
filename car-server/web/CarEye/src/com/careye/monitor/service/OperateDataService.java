package com.careye.monitor.service;

import java.util.List;
import java.util.Map;

import com.careye.monitor.domain.OperateData;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-10 上午11:05:34
 * @修改人：ll
 * @修改时间：2015-11-10 上午11:05:34
 * @修改备注：
 * @version 1.0
 */
public interface OperateDataService {
	
	/**
	 * 分页查询营运信息
	 * @param currentPage
	 * @param everyPage
	 * @param operateData
	 * @return
	 */
	public Map getOperateDataList(final int currentPage, final int everyPage, OperateData operateData);
	
	/**
	 * 条件导出营运信息
	 * @param operateData
	 * @return
	 */
	public List<OperateData> exportOperateData(OperateData operateData);
}
