package com.careye.car.service;

import java.util.List;

import com.careye.car.domain.CarInfo;
import com.careye.car.domain.PositionInfo;

/**
 * @项目名称：TAXISERVER
 * @类名称：PositionInfoService
 * @类描述：车辆位置信息服务接口
 * @创建人：zr
 * @创建时间：2015-3-13 下午01:56:16
 * @修改人：zr
 * @修改时间：2015-3-13 下午01:56:16
 * @修改备注：
 * @version 1.0
 */
public interface PositionInfoService {
	
	/**
	 * 根据设备号获取车辆上一次里程数
	 * @param terminal
	 * @return
	 */
	public Float getTerminalmileage(String terminal);
	
	/**
	 * 更新设备位置信息
	 * @param terminalPositionInfo
	 * @return
	 */
	public Integer updateTerminalPositionInfo(PositionInfo positionInfo);
	
	/**
	 * 更新雅讯设备位置信息
	 * @param terminalPositionInfo
	 * @return
	 */
	public Integer updateYxTerminalPositionInfo(PositionInfo positionInfo);
	
	/**
	 * 插入历史位置表
	 * @param positionInfo
	 * @return
	 */
	public Integer insertTerminalPositionInfo(PositionInfo positionInfo);
	
	/**
	 * 插入雅讯历史位置表
	 * @param positionInfo
	 * @return
	 */
	public Integer insertYxTerminalPositionInfo(PositionInfo positionInfo);
	
	/**
	 * 离线时间超过7天，状态变成长离线
	 * @param dff
	 * @return
	 */
	public List<CarInfo> getCarOffLine(String queryDate);
	
	/**
	 * 获取超过指定时间的记录
	 * @param dff
	 * @return
	 */
	public List<CarInfo> getCarOnlineStatus(String queryDate);
	
	
	
}
