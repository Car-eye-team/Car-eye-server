/**
* Description: car-eye车辆管理平台
* 文件名：PoiInfoServiceImpl.java
* 版本信息：1.0
* 日期：2014-6-4
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.service.impl;

import java.util.List;
import java.util.Map;


import com.careye.base.service.GenericService;
import com.careye.car.domain.PoiInfo;
import com.careye.car.service.PoiInfoService;
import com.careye.common.domain.TerminalPositionInfo;
import com.careye.common.service.SysOperateLogService;

/**
 * @项目名称：FMS
 * @类名称：PoiInfoServiceImpl
 * @类描述：POI信息点服务实现
 * @创建人：zr
 * @创建时间：2014-6-4 上午11:43:44
 * @修改人：zr
 * @修改时间：2014-6-4 上午11:43:44
 * @修改备注：
 * @version 1.0
 */
public class PoiInfoServiceImpl extends GenericService implements PoiInfoService{

	private SysOperateLogService logService;
	/**
	 * 增加POI信息发送记录
	 * @param poiInfo
	 * @return
	 */
	//@Log(operationType="add操作:",operationName=" 增加POI信息发送记录")
	@Override
	public int insertPoiInfo(PoiInfo poiInfo) {
		int result=  this.baseDao.save("oracle-poiInfoSQL.insertPoiInfo", poiInfo);
		if(result>0){
			logService.addLogInfo(poiInfo.getUserid(), " 增加POI信息发送记录:车牌号:"+poiInfo.getCarnumber()
					+";poi点信息名称:"+poiInfo.getPoiname()
					+";经度:"+poiInfo.getLng()
					+";纬度:"+poiInfo.getLat(), 1);
		}
		return result;
	}

	/**
	 * 获取POI信息发送记录
	 * @param currentPage
	 * @param everyPage
	 * @param poiInfo
	 * @return
	 */
	@Override
	public Map queryPoiInfoRecordList(int currentPage, int everyPage,
			PoiInfo poiInfo) {
		//根据车辆编号查询车辆id
		return this.baseDao.findPageList("oracle-poiInfoSQL.queryPoiInfoRecordList",
				"oracle-poiInfoSQL.queryPoiInfoRecordListCount", poiInfo,currentPage,everyPage);
	}

	/**
	 * Excel导出
	 */
	public List<PoiInfo> selectPoiInfoRecordList(PoiInfo poiInfo) {
		
		return this.baseDao.queryForList("oracle-poiInfoSQL.queryPoiInfoRecordList", poiInfo);
	}
	/**
	 * 根据流水号更新处理结果
	 * @param poiInfo
	 * @return
	 */
	@Override
	public int updatePoiInfoResult(PoiInfo poiInfo) {
		
		int result=this.baseDao.update("oracle-poiInfoSQL.updatePoiInfoResult", poiInfo);
		if(result>0){
			logService.addLogInfo(poiInfo.getUserid(), " 更新POI信息发送记录:车牌号:"+poiInfo.getCarnumber()
					+";poi点信息名称:"+poiInfo.getPoiname()
					+";经度:"+poiInfo.getLng()
					+";纬度:"+poiInfo.getLat(), 2);
		}
		return result;
	}
	

	/**
	 * 根据距离经纬度条件查询车辆信息
	 * @param TerminalPositionInfo
	 * @return
	 */
	public Map queryCarInfoList(final int currentPage, final int everyPage,TerminalPositionInfo poiInfo){
			return this.baseDao.findPageList("oracle-poiInfoSQL.queryCarInfoList",
					"oracle-poiInfoSQL.queryCarInfoListCount", poiInfo,currentPage,everyPage);
	}
	public List<TerminalPositionInfo> queryCarInfoList(TerminalPositionInfo poiInfo){
		return this.baseDao.queryForList("oracle-poiInfoSQL.queryCarInfoList", poiInfo);
	}
	
	/**
	 * 参数设置车辆列表
	 * @param carInfo
	 * @return
	 */
	public Map queryDsobdParamSetList(final int currentPage, final int everyPage,PoiInfo poiInfo){
		return this.baseDao.findPageList("oracle-poiInfoSQL.queryDsobdParamSetList",
				"oracle-poiInfoSQL.queryDsobdParamSetListCount", poiInfo,currentPage,everyPage);
	}

	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	@Override
	public int deleteRecord(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-poiInfoSQL.deleteRecord", id);
	}

	
	

	

}
