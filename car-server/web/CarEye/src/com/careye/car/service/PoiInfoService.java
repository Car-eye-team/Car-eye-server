/**
* Description: car-eye车辆管理平台
* 文件名：PoiInfoService.java
* 版本信息：1.0
* 日期：2014-6-4
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.service;

import java.util.List;
import java.util.Map;

import com.careye.car.domain.PoiInfo;
import com.careye.common.domain.TerminalPositionInfo;

/**
 * @项目名称：FMS
 * @类名称：PoiInfoService
 * @类描述：POI信息点服务
 * @创建人：zr
 * @创建时间：2014-6-4 上午11:43:12
 * @修改人：zr
 * @修改时间：2014-6-4 上午11:43:12
 * @修改备注：
 * @version 1.0
 */
public interface PoiInfoService {
	
	/**
	 * deleteRecord 删除
	 */
	public int deleteRecord(int id);
	/**
	 * Excel导出
	 * @param poiInfo
	 * @return
	 */
	public List<PoiInfo> selectPoiInfoRecordList(PoiInfo poiInfo);
	
	/**
	 * 增加POI信息发送记录
	 * @param poiInfo
	 * @return
	 */
	public int insertPoiInfo(PoiInfo poiInfo);
	
	/**
	 * 获取POI信息发送记录
	 * @param currentPage
	 * @param everyPage
	 * @param poiInfo
	 * @return
	 */
	public Map queryPoiInfoRecordList(final int currentPage, final int everyPage,PoiInfo poiInfo);
	
	
	/**
	 * 根据流水号更新处理结果
	 * @param poiInfo
	 * @return
	 */
	public int updatePoiInfoResult(PoiInfo poiInfo);
	
	public List<TerminalPositionInfo> queryCarInfoList(TerminalPositionInfo poiInfo);

	/**
	 * 根据距离经纬度条件查询车辆信息
	 * @param TerminalPositionInfo
	 * @return
	 */
	public Map queryCarInfoList(final int currentPage, final int everyPage,TerminalPositionInfo poiInfo);
	
	/**
	 * 参数设置车辆列表
	 * @param carInfo
	 * @return
	 */
	public Map queryDsobdParamSetList(final int currentPage, final int everyPage,PoiInfo poiInfo);
	
	
}
