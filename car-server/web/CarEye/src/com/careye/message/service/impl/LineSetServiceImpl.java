/**
* Description: 多森商用车平台
* 文件名：LineSetServiceImpl.java
* 版本信息：1.0
* 日期：2014-6-3
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.message.domain.LineSet;
import com.careye.message.service.LineSetService;
import com.careye.mq.domain.Line;
import com.careye.mq.domain.ZoneAlarm;
import com.careye.utils.DateUtil;

/**
 * @项目名称：FMS
 * @类名称：LineSetServiceImpl
 * @类描述：
 * @创建人：Administrator
 * @创建时间：2014-6-3 下午03:41:56
 * @修改人：Administrator
 * @修改时间：2014-6-3 下午03:41:56
 * @修改备注：
 * @version 1.0
 */
public class LineSetServiceImpl extends GenericService implements LineSetService {

	/**
	 * 根据车牌号得到终端号码
	 * @param lineSet
	 * @return
	 */
	public String queryTerminalByCarnumber(String carnumber){
		return (String)this.baseDao.queryForObject("oracle-lineSetSQL.queryTerminalByCarnumber", carnumber);
	}
	
	/**
	 * 根据车辆id得到车牌号
	 * @param lineSet
	 * @return
	 */
	public String getCarnumberByCarid(int carid){
		return (String)this.baseDao.queryForObject("oracle-lineSetSQL.getCarnumberByCarid", carid);
	}
	
	
	/**
	 * 更新缓冲临时数据入库明细信息
	 * @param repairRegist
	 * @return
	 */
	public int updateTempLineSetDetail(int lineid){
		return this.baseDao.update("oracle-lineSetSQL.updateTempLineSetDetail", lineid);
	}
	
	/**
	 * 删除系统线路所对应的系统线路明细信息记录
	 * @param registid
	 * @return
	 */
	public int deleteLineSetDetailByLineid(int lineid){
		return this.baseDao.delete("oracle-lineSetSQL.deleteLineSetDetailByLineid", lineid);
	}
	
	/**
	 * 删除系统线路信息
	 * @param id
	 * @return
	 */
	public int deleteLineSet(int id){
		return this.baseDao.delete("oracle-lineSetSQL.deleteLineSet", id);
	}
	
	/**
	 * 删除系统线路明细信息
	 * @param id
	 * @return
	 */
	public int deleteLineSetDetail(int id){
		return this.baseDao.delete("oracle-lineSetSQL.deleteLineSetDetail", id);
	}
	
	/**
	 * 添加系统线路信息
	 * @param lineSet
	 * @return
	 */
	public int insertLineSet(LineSet lineSet){
		lineSet.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-lineSetSQL.insertLineSet", lineSet);
	}
	
	
	/**
	 * 系统线路详情
	 * @param lineSet
	 * @return
	 */
	public LineSet queryLineSetById(int id){
		return (LineSet)this.baseDao.queryForObject("oracle-lineSetSQL.queryLineSetById", id);
	}
	
	/**
	 * 根据车辆线路id得到系统线路id
	 * @param lineSet
	 * @return
	 */
	public Integer queryLineIdByDetailId(int id){
		return (Integer)this.baseDao.queryForObject("oracle-lineSetSQL.queryLineIdByDetailId", id);
	}
	
	/**
	 * 条件查询系统线路信息分页
	 * @param lineSet
	 * @return
	 */
	public Map queryPageLineSetList(final int currentPage, final int everyPage,LineSet lineSet){
		return this.baseDao.findPageList("oracle-lineSetSQL.queryPageLineSetList",
				"oracle-lineSetSQL.queryPageLineSetListCount", lineSet,currentPage,everyPage);
	}
	
	/**
	 * 条件查询系车辆线路下发记录信息
	 * @param lineSet
	 * @return
	 */
	public Map queryLineSendRecordList(final int currentPage, final int everyPage,LineSet lineSet){
		return this.baseDao.findPageList("oracle-lineSetSQL.queryLineSendRecordList",
				"oracle-lineSetSQL.queryLineSendRecordListCount", lineSet,currentPage,everyPage);
	}
	
	/**
	 * 删除系统线路明细临时信息
	 * @param id
	 * @return
	 */
	public int deleteTempLineSetDetail(){
		return this.baseDao.delete("oracle-lineSetSQL.deleteTempLineSetDetail",null);
	}
	
	
	/**
	 * 系统线路明细列表不分页
	 * @param 
	 * @return
	 */
	public List<LineSet> selectLineSetDetailList(Integer lineid){
		return this.baseDao.queryForList("oracle-lineSetSQL.selectLineSetDetailList", lineid);
	}
	
	public List<Line> getLineList(Integer lineid){
		return this.baseDao.queryForList("oracle-lineSetSQL.getLineList", lineid);
	}
	/**
	 * 系统线路临时数据列表不分页
	 * @param 
	 * @return
	 */
	public List<LineSet> selectTempLineSetDetail(){
		return this.baseDao.queryForList("oracle-lineSetSQL.selectTempLineSetDetail");
	}
	
	/**
	 * 添加系统线路明细信息
	 * @param partsPurchase
	 * @return
	 */
	public int insertLineSetDetail(LineSet lineSet){
		lineSet.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-lineSetSQL.insertLineSetDetail", lineSet);
	}
	
	/**
	 * 添加车辆线路操作记录表
	 * @param areaCar
	 * @return
	 */
	public int insertLineCarRecord(LineSet lineSet){
		lineSet.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-lineSetSQL.insertLineCarRecord", lineSet);
	}
	
	/**
	 * 更新系统线路明细信息
	 * @param partsPurchase
	 * @return
	 */
	public int updateLineSetDetail(LineSet lineSet){
		return this.baseDao.update("oracle-lineSetSQL.updateLineSetDetail", lineSet);
	}
	
	
	
	/**
	 * 更新车辆线路信息
	 * @param lineCar
	 * @return
	 */
	public int updateLineCar(LineSet lineCar){
		return this.baseDao.update("oracle-lineSetSQL.updateLineSet", lineCar);
	}
	
	
	/**
	 * 删除车辆线路信息
	 * @param id
	 * @return
	 */
	public int deleteLineCar(int id){
		return this.baseDao.delete("oracle-lineSetSQL.deleteLineCar", id);
	}
	
	/**
	 * 添加车辆线路信息
	 * @param lineCar
	 * @return
	 */
	public int insertLineCar(LineSet lineCar){
		lineCar.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-lineSetSQL.insertLineCar", lineCar);
	}
	
	/**
	 * 添加车辆线路操作记录表
	 * @param lineCar
	 * @return
	 */
	public int insertLineSetRecord(ZoneAlarm zoneAlarm){
		zoneAlarm.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-lineSetSQL.insertLineSetRecord", zoneAlarm);
	}
	
	/**
	 * 查看系统线路是否存在
	 * @param lineCar
	 * @return
	 */
	public int queryLineSetIsExist(LineSet lineCar){
		return (Integer)this.baseDao.queryForObject("oracle-lineSetSQL.queryLineSetIsExist", lineCar);
	}
	/**
	 * 查看车辆线路是否存在
	 * @param lineCar
	 * @return
	 */
	public int queryLineCarIsExist(LineSet lineCar){
		return (Integer)this.baseDao.queryForObject("oracle-lineSetSQL.queryLineCarIsExist", lineCar);
	}
	
	/**
	 * 查看车辆线路追加数量
	 * @param lineCar
	 * @return
	 */
	public int queryLineSetCount(int lineid){
		return (Integer)this.baseDao.queryForObject("oracle-lineSetSQL.queryLineSetCount", lineid);
	}
	
	/**
	 * 根据车辆线路id查看线路详情
	 * @param lineCar
	 * @return
	 */
	public LineSet getLineSetDetail(int id){
		return (LineSet)this.baseDao.queryForObject("oracle-lineSetSQL.getLineSetDetail", id);
	}
	
	/**
	 * 查看线路详情
	 * @param lineSet
	 * @return
	 */
	public Line queryLinetByLineId(int id){
		return (Line)this.baseDao.queryForObject("oracle-lineSetSQL.queryLinetByLineId", id);
	}
	
	/**
	 * 得到车辆线路列表
	 * @param lineCar
	 * @return
	 */
	public List<LineSet> getLineSetByLineId(int lineid){
		return this.baseDao.queryForList("oracle-lineSetSQL.getLineSetByLineId", lineid);
	}
	
	/**
	 * 条件查询车辆线路信息分页
	 * @param lineCar
	 * @return
	 */
	public Map queryPageLineCarList(final int currentPage, final int everyPage,LineSet lineSet){
		return this.baseDao.findPageList("oracle-lineSetSQL.queryPageLineCarList",
				"oracle-lineSetSQL.queryPageLineCarListCount", lineSet,currentPage,everyPage);
	}

	/**
	 * 根据路段ID获取路段信息
	 * @param id
	 * @return
	 */
	@Override
	public LineSet getRoadSection(int id) {
		Object object = this.baseDao.queryForObject("oracle-lineSetSQL.getRoadSection", id);
		if(object == null){
			return null;
		}else{
			return (LineSet) object;
		}
		
	}

	/**
	 * 更新线路操作结果
	 * @param lineSet
	 * @return
	 */
	@Override
	public int updateLineSetResult(LineSet lineSet) {
		return this.baseDao.update("oracle-lineSetSQL.updateLineSetResult", lineSet);
	}

	/**
	 * 更新系统线路信息
	 * @param lineSet
	 * @return
	 */
	public int updateLineSet(LineSet lineSet){
		return this.baseDao.update("oracle-lineSetSQL.updateLineSet", lineSet);
	}


}
