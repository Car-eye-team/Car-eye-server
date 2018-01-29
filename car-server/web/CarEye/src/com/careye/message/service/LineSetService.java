/**
* Description: 多森商用车平台
* 文件名：CarService.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.service;

import java.util.List;
import java.util.Map;

import com.careye.car.domain.AreaCar;
import com.careye.message.domain.LineSet;
import com.careye.mq.domain.Line;
import com.careye.mq.domain.ZoneAlarm;

/**
 * @项目名称：FMS
 * @类名称：LineSetService
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:00:03
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public interface LineSetService {

	
	/**
	 * 根据车牌号得到终端号码
	 * @param lineSet
	 * @return
	 */
	public String queryTerminalByCarnumber(String carnumber);
	
	/**
	 * 根据车辆id得到车牌号
	 * @param lineSet
	 * @return
	 */
	public String getCarnumberByCarid(int carid);
	
	/**
	 * 更新系统线路信息
	 * @param lineSet
	 * @return
	 */
	public int updateLineSet(LineSet lineSet);
	
	/**
	 * 更新缓冲临时数据入库明细信息
	 * @param repairRegist
	 * @return
	 */
	public int updateTempLineSetDetail(int lineid);
	
	/**
	 * 删除系统线路所对应的系统线路明细信息记录
	 * @param registid
	 * @return
	 */
	public int deleteLineSetDetailByLineid(int lineid);
	
	/**
	 * 删除系统线路信息
	 * @param id
	 * @return
	 */
	public int deleteLineSet(int id);
	
	/**
	 * 删除系统线路明细信息
	 * @param id
	 * @return
	 */
	public int deleteLineSetDetail(int id);
	
	/**
	 * 添加系统线路信息
	 * @param lineSet
	 * @return
	 */
	public int insertLineSet(LineSet lineSet);
	
	/**
	 * 系统线路详情
	 * @param lineSet
	 * @return
	 */
	public LineSet queryLineSetById(int id);
	
	/**
	 * 根据车辆线路id得到系统线路id
	 * @param lineSet
	 * @return
	 */
	public Integer queryLineIdByDetailId(int id);
	
	
	/**
	 * 条件查询系统线路信息分页
	 * @param lineSet
	 * @return
	 */
	public Map queryPageLineSetList(final int currentPage, final int everyPage,LineSet lineSet);
	
	/**
	 * 条件查询系车辆线路下发记录信息
	 * @param lineSet
	 * @return
	 */
	public Map queryLineSendRecordList(final int currentPage, final int everyPage,LineSet lineSet);
	
	/**
	 * 删除系统线路明细临时信息
	 * @param id
	 * @return
	 */
	public int deleteTempLineSetDetail();
	
	
	/**
	 * 系统线路明细列表不分页
	 * @param 
	 * @return
	 */
	public List<LineSet> selectLineSetDetailList(Integer lineid);
	
	public List<Line> getLineList(Integer lineid);
	
	/**
	 * 系统线路临时数据列表不分页
	 * @param 
	 * @return
	 */
	public List<LineSet> selectTempLineSetDetail();
	
	/**
	 * 添加系统线路明细信息
	 * @param partsPurchase
	 * @return
	 */
	public int insertLineSetDetail(LineSet lineSet);
	
	/**
	 * 添加车辆线路操作记录表
	 * @param areaCar
	 * @return
	 */
	public int insertLineCarRecord(LineSet lineSet);
	
	/**
	 * 更新系统线路明细信息
	 * @param partsPurchase
	 * @return
	 */
	public int updateLineSetDetail(LineSet lineSet);
	
	
	
	
	/**
	 * 更新车辆线路信息
	 * @param lineSet
	 * @return
	 */
	public int updateLineCar(LineSet lineSet);
	
	
	/**
	 * 删除车辆线路信息
	 * @param id
	 * @return
	 */
	public int deleteLineCar(int id);
	
	/**
	 * 添加车辆线路信息
	 * @param lineSet
	 * @return
	 */
	public int insertLineCar(LineSet lineSet);
	
	/**
	 * 添加车辆线路操作记录表
	 * @param lineSet
	 * @return
	 */
	public int insertLineSetRecord(ZoneAlarm zoneAlarm);
	
	/**
	 * 查看系统线路是否存在
	 * @param lineSet
	 * @return
	 */
	public int queryLineSetIsExist(LineSet lineSet);
	
	/**
	 * 查看车辆线路是否存在
	 * @param lineSet
	 * @return
	 */
	public int queryLineCarIsExist(LineSet lineSet);
	
	
	/**
	 * 查看车辆线路追加数量
	 * @param lineSet
	 * @return
	 */
	public int queryLineSetCount(int lineid);
	
	/**
	 * 根据车辆线路id查看线路详情
	 * @param lineSet
	 * @return
	 */
	public LineSet getLineSetDetail(int id);
	
	/**
	 * 查看线路详情
	 * @param lineSet
	 * @return
	 */
	public Line queryLinetByLineId(int id);
	
	/**
	 * 得到车辆线路列表
	 * @param lineSet
	 * @return
	 */
	public List<LineSet> getLineSetByLineId(int lineid);
	
	
	/**
	 * 条件查询车辆线路信息分页
	 * @param lineSet
	 * @return
	 */
	public Map queryPageLineCarList(final int currentPage, final int everyPage,LineSet lineSet);
	
	/**
	 * 根据路段ID获取路段信息
	 * @param id
	 * @return
	 */
	public LineSet getRoadSection(int id); 
	
	/**
	 * 更新线路操作结果
	 * @param lineSet
	 * @return
	 */
	public int updateLineSetResult(LineSet lineSet);
	
	
}
