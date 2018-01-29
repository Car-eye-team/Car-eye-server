package com.careye.car.service.impl;

import java.util.List;

import com.careye.base.service.GenericService;
import com.careye.car.domain.CarInfo;
import com.careye.car.domain.PositionInfo;
import com.careye.car.service.PositionInfoService;

/**
 * @项目名称：TAXISERVER
 * @类名称：PositionInfoServiceImpl
 * @类描述：位置信息服务
 * @创建人：zr
 * @创建时间：2015-3-13 下午01:56:43
 * @修改人：zr
 * @修改时间：2015-3-13 下午01:56:43
 * @修改备注：
 * @version 1.0
 */
public class PositionInfoServiceImpl extends GenericService implements PositionInfoService{

	/**
	 * 根据设备号获取车辆上一次里程数
	 * @param terminal
	 * @return
	 */
	@Override
	public Float getTerminalmileage(String terminal) {
		Object mileage = this.baseDao.queryForObject("oracle-positionInfoSQL.getTerminalmileage", terminal);
		if(mileage == null){
			return 0f;
		}
		return Float.parseFloat(mileage.toString());
	}

	/**
	 * 更新设备位置信息
	 * @param terminalPositionInfo
	 * @return
	 */
	@Override
	public Integer updateTerminalPositionInfo(PositionInfo positionInfo) {
		if(positionInfo.getLng() == null){
			return this.baseDao.update("oracle-positionInfoSQL.updateTerminalPositionInfo", positionInfo);
		}else{
			return this.baseDao.update("oracle-positionInfoSQL.updateTerminalPositionInfo_procedure", positionInfo);
		}
	}
	
	/**
	 * 更新雅讯设备位置信息
	 * @param terminalPositionInfo
	 * @return
	 */
	public Integer updateYxTerminalPositionInfo(PositionInfo positionInfo){
		return this.baseDao.update("oracle-positionInfoSQL.updateYxTerminalPositionInfo_procedure", positionInfo);
	}

	/**
	 * 插入历史位置表
	 * @param positionInfo
	 * @return
	 */
	@Override
	public Integer insertTerminalPositionInfo(PositionInfo positionInfo) {
		//return this.baseDao.save("oracle-positionInfoSQL.insertTerminalPositionInfo", positionInfo);
		return this.baseDao.save("oracle-positionInfoSQL.insertTerminalPositionInfo_procedure", positionInfo);
	}

	/**
	 * 插入雅讯历史位置表
	 * @param positionInfo
	 * @return
	 */
	public Integer insertYxTerminalPositionInfo(PositionInfo positionInfo){
		return this.baseDao.save("oracle-positionInfoSQL.insertYxTerminalPositionInfo_procedure", positionInfo);
	}
	
	/**
	 * 离线时间超过7天，状态变成长离线
	 * @param dff
	 * @return
	 */
	@Override
	public List<CarInfo> getCarOffLine(String queryDate) {
		return this.baseDao.queryForList("oracle-positionInfoSQL.getCarOffLine", queryDate);
	}

	/**
	 * 获取超过指定时间的记录
	 * @param dff
	 * @return
	 */
	@Override
	public List<CarInfo> getCarOnlineStatus(String queryDate) {
		return this.baseDao.queryForList("oracle-positionInfoSQL.getCarOnlineStatus", queryDate);
	}

}
