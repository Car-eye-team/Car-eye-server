/**
* Description: car-eye车辆管理平台
* 文件名：CarTypeNameServiceImpl.java
* 版本信息：1.0
* 日期：2014-3-6
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.sysset.service.impl;

import java.util.List;
import java.util.Map;


import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.sysset.domain.CarType;
import com.careye.sysset.service.CarTypeService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：car-eye
 * @类名称：CarTypeServiceImpl
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-9 上午10:25:07
 * @修改人：huangqin
 * @修改时间：2015-3-9 上午10:25:07
 * @修改备注：
 * @version 1.0
 */
public class CarTypeServiceImpl extends GenericService implements CarTypeService{
	
	private SysOperateLogService logService;
	
	@Override
	public int carTypeNameIsExist(CarType carType) {
		return (Integer) this.baseDao.queryForObject("oracle-carTypeSQL.carTypeNameIsExist",carType);
	}

	@Override
	public int deleteCarType(List<String> list) {
		
		//判断是否不让删除
		int number=this.judgeTypeIsDelete(list);
		if(number>0){
			return -3;
		}else{
			for(int i=0;i<list.size();i++){
				CarType carType=getCarTypeById(Integer.parseInt(list.get(i)));
				logService.addLogInfo(SessionUtils.getUser().getId(), "删除车辆类型记录:类型名称:"+carType.getTypename()
					+";创建时间:"+
					carType.getCreatetime()+";影响数据ID:"+carType.getId(),3);
			}
			return this.baseDao.delete("oracle-carTypeSQL.deleteCarType", list);
		}
	}

	@Override
	public CarType getCarTypeById(int id) {
		return (CarType) this.baseDao.queryForObject("oracle-carTypeSQL.getCarTypeById",id);
	}

	@Override
	public int insertCarType(CarType carType) {
		    int areaName = queryCarTypeNameIsExits(carType);
			int result=0;
			if(areaName>0){
				result=-3;
			}else{
				carType.setCreatetime(DateUtil.getSQLDate());
				result=this.baseDao.save("oracle-carTypeSQL.insertCarType", carType);
				if(result>0){
					logService.addLogInfo(carType.getUserid(), "添加车辆类型记录:类型名称:"+carType.getTypename()
							+";创建时间:"+
							carType.getCreatetime()+";影响数据ID:"+carType.getId(), 1);
							}
			}
			return result;
		
	}

	@Override
	public Map selectPageCarType(int currentPage, int everyPage, CarType carType) {
		return this.baseDao.findPageList("oracle-carTypeSQL.selectPageCarType", "oracle-carTypeSQL.selectPageCarTypeCount", carType, currentPage, everyPage);
	}

	@Override
	public int updateCarType(CarType carType) {
		    int areaName = queryCarTypeNameIsExits(carType);
			int result=0;
			if(areaName>0){
				result=-3;
			}else{
				carType.setCreatetime(DateUtil.getSQLDate());
				if(!SessionUtils.getUser().equals("admin")){	//普通用户
					carType.setUserid(SessionUtils.getUser().getId());
				}
				result=this.baseDao.update("oracle-carTypeSQL.updateCarType", carType);
				if(result>0){
					logService.addLogInfo(carType.getUserid(), "更新车辆类型记录:类型名称:"+carType.getTypename()
							+";创建时间:"+
							carType.getCreatetime()+";影响数据ID:"+carType.getId(),2);				}
				return result;
			}
			
			return result;
	}

	private Integer  queryCarTypeNameIsExits(CarType carType){
		
		return  (Integer) this.baseDao.queryForObject("oracle-carTypeSQL.queryCarTypeNameIsExits",carType);
	}
	
	
	
	/**
	 * 判断是否能删除当前记录
	 * @return
	 */
	public  Integer  judgeTypeIsDelete(List<String>  list){
		
	   return	(Integer) this.baseDao.queryForObject("oracle-carTypeSQL.judgeTypeIsDelete",list);
	}
	
	
	
	@Override
	public List getAllCarType(CarType carType) {
		logService.addLogInfo(carType.getUserid(), "Excel导出车辆类型信息", 4);
		
		return this.baseDao.queryForList("oracle-carTypeSQL.selectPageCarType",carType);
	}

	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}
}
