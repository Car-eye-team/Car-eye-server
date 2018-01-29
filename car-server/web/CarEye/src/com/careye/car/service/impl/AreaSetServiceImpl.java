/**
* Description: car-eye车辆管理平台
* 文件名：AreaSetServiceImpl.java
* 版本信息：1.0
* 日期：2014-6-3
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.service.impl;


import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.car.domain.AreaCar;
import com.careye.car.domain.AreaSet;
import com.careye.car.domain.CarDriver;
import com.careye.car.service.AreaSetService;
import com.careye.common.service.SysOperateLogService;
import com.careye.mq.domain.ZoneAlarm;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：FMS
 * @类名称：AreaSetServiceImpl
 * @类描述：
 * @创建人：Administrator
 * @创建时间：2014-6-3 下午03:41:56
 * @修改人：Administrator
 * @修改时间：2014-6-3 下午03:41:56
 * @修改备注：
 * @version 1.0
 */
public class AreaSetServiceImpl extends GenericService implements AreaSetService {
	
	private SysOperateLogService logService;
	/**
	 * 条件查询系统区域信息分页
	 * @param areaSet
	 * @return
	 */
	public Map queryPageAreaSetList(final int currentPage, final int everyPage,AreaSet areaSet){
		return this.baseDao.findPageList("oracle-areaSetSQL.queryPageAreaSetList",
				"oracle-areaSetSQL.queryPageAreaSetListCount", areaSet,currentPage,everyPage);
	}
	
	/**
	 * 添加系统区域信息
	 * @param areaSet
	 * @return
	 */
	public int insertAreaSet(AreaSet areaSet){
		 int areaName = queryAreaSetNameIsExits(areaSet);
		 int result=0;
		 if(areaName>0){
			result=-3;
		 }else{
			    areaSet.setCreatetime(DateUtil.getSQLDate());
			    result=this.baseDao.save("oracle-areaSetSQL.insertAreaSet", areaSet);
				if(result>0){
					int type=areaSet.getAreatype();
					String typeString=null;
					if(type==1){
						typeString="圆形区域";
					}else if(type==2){
						typeString="矩形区域";
					}else if(type==3){
						typeString="多边形区域";
					}
					logService.addLogInfo(areaSet.getUserid(), "添加系统区域信息记录:区域名称:"+areaSet.getAreaname()
							+";区域类型:"+typeString
							+";影响数据ID:"+areaSet.getId(), 1);
				}
		 }
		 return result;
	}
	
	/**
	 * 删除系统区域信息
	 * @param id
	 * @return
	 */
	public int deleteAreaSet(int id){
		AreaSet areaSet=this.getAreaSetById(id);
		int type=areaSet.getAreatype();
		String typeString=null;
		if(type==1){
			typeString="圆形区域";
		}else if(type==2){
			typeString="矩形区域";
		}else if(type==3){
			typeString="多边形区域";
		}
		logService.addLogInfo(SessionUtils.getUserId(), "删除系统区域信息记录:区域名称:"+areaSet.getAreaname()
				+";区域类型:"+typeString
				+";影响数据ID:"+id, 3);
			
		return this.baseDao.delete("oracle-areaSetSQL.deleteAreaSet", id);
	}
	
	
	/**
	 * 根据id得到系统区域信息对象
	 * @param id
	 * @return
	 */
	private AreaSet getAreaSetById(int id){
		return  (AreaSet) this.baseDao.queryForObject("oracle-areaSetSQL.getAreaSetById",id);
	}
	
	/**
	 * 更新系统区域信息
	 * @param areaSet
	 * @return
	 */
	public int updateAreaSet(AreaSet areaSet){
		 int areaName = queryAreaSetNameIsExits(areaSet);
		 int result=0;
		 if(areaName>0){
			result=-3;
		 }else{
			 result=this.baseDao.update("oracle-areaSetSQL.updateAreaSet", areaSet);
				if(result>0){
					int type=areaSet.getAreatype();
					String typeString=null;
					if(type==1){
						typeString="圆形区域";
					}else if(type==2){
						typeString="矩形区域";
					}else if(type==3){
						typeString="多边形区域";
					}
					logService.addLogInfo(SessionUtils.getUserId(), "更新系统区域信息记录:区域名称:"+areaSet.getAreaname()
							+";区域类型:"+typeString
							+";影响数据ID:"+areaSet.getId(), 2);
				}
		   
		 }
		 return result;
	}

	/**
	 * 查询是否相同的名字存在
	 * 
	 * @param name
	 * @return
	 */
	public Integer queryAreaSetNameIsExits(AreaSet areaSet) {
		
		return (Integer) this.baseDao.queryForObject("oracle-areaSetSQL.queryAreaSetNameIsExits",areaSet);
	}

	@Override
	public int queryAreaSetCount(int areaid) {
		return (Integer)this.baseDao.queryForObject("oracle-areaSetSQL.queryAreaSetCount", areaid);
	}

	/**
	 * 系统区域详情
	 * @param areaSet
	 * @return
	 */
	public ZoneAlarm queryAreaById(int id){
		return (ZoneAlarm)this.baseDao.queryForObject("oracle-areaSetSQL.queryAreaById", id);
	}

	@Override
	public Map queryAreaSendRecordList(int currentPage, int everyPage,
			AreaSet areaSet) {
		return this.baseDao.findPageList("oracle-areaSetSQL.queryAreaSendRecordList",
				"oracle-areaSetSQL.queryAreaSendRecordListCount", areaSet,currentPage,everyPage);
	}

	
	/**
	 * 条件查询车辆区域信息分页
	 * @param areaCar
	 * @return
	 */
	public Map queryPageAreaCarList(final int currentPage, final int everyPage,AreaCar areaCar){
		return this.baseDao.findPageList("oracle-areaSetSQL.queryPageAreaCarList",
				"oracle-areaSetSQL.queryPageAreaCarListCount", areaCar,currentPage,everyPage);
	}

	@Override
	public int deleteAreaCar(int id) {
		return this.baseDao.delete("oracle-areaSetSQL.deleteAreaCar", id);
	}

	@Override
	public AreaSet getAreaCarDetail(int id) {
		return (AreaSet)this.baseDao.queryForObject("oracle-areaSetSQL.getAreaCarDetail", id);

	}

	/**
	 * 添加车辆区域操作记录表
	 * @param areaCar
	 * @return
	 */
	public int insertAreaCarRecord(ZoneAlarm zoneAlarm){
		zoneAlarm.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-areaSetSQL.insertAreaCarRecord", zoneAlarm);
	}

	
	public String getCarnumberByTer(String terminal){
		return (String)this.baseDao.queryForObject("oracle-areaSetSQL.getCarnumberByTer", terminal);
	}
	
	/**
	 * 添加车辆区域信息
	 * @param areaCar
	 * @return
	 */
	public int insertAreaCar(AreaCar areaCar){
		areaCar.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-areaSetSQL.insertAreaCar", areaCar);
	}
	
	/**
	 * 查看车辆区域是否存在
	 * @param areaCar
	 * @return
	 */
	public int queryAreaCarIsExist(AreaCar areaCar){
		return (Integer)this.baseDao.queryForObject("oracle-areaSetSQL.queryAreaCarIsExist", areaCar);
	}

	
	/**
	 * 更新区域处理结果
	 * @param areaSet
	 * @return
	 */
	@Override
	public int updateAreaCarResult(AreaSet areaSet) {
		return this.baseDao.update("oracle-areaSetSQL.updateAreaCarResult", areaSet);
	}
	
	/**
	 * 根据区域id查看区域详情
	 * @param areaCar
	 * @return
	 */
	public AreaSet getAreaSetDetail(int id){
		return (AreaSet)this.baseDao.queryForObject("oracle-areaSetSQL.getAreaSetDetail", id);
	}
	
	
	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}
	
	
}
