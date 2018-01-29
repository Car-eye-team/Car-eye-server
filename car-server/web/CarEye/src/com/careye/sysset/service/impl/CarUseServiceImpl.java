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
import com.careye.sysset.domain.CarUse;
import com.careye.sysset.service.CarUseService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：car-eye
 * @类名称：CarUseServiceImpl
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-9 上午10:25:07
 * @修改人：huangqin
 * @修改时间：2015-3-9 上午10:25:07
 * @修改备注：
 * @version 1.0
 */
public class CarUseServiceImpl extends GenericService implements CarUseService{
	
	private SysOperateLogService logService;
	
	@Override
	public int carUseNameIsExist(CarUse carUse) {
		return (Integer) this.baseDao.queryForObject("oracle-carTypeSQL.carUseNameIsExist",carUse);
	}

	@Override
	public int deleteCarUse(List<String> list) {
		//判断是否不让删除
		int number=this.judgeUseIsDelete(list);
		if(number>0){
			return -3;
		}else{
			for(int i=0;i<list.size();i++){
				CarUse carUse=getCarUseById(Integer.parseInt(list.get(i)));
				logService.addLogInfo(SessionUtils.getUser().getId(), "删除车辆用途记录:车辆用途名称:"+carUse.getUsename()
					+";创建时间:"+
					carUse.getCreatetime()+";影响数据ID:"+carUse.getId(),3);
			}
			return this.baseDao.delete("oracle-carUseSQL.deleteCarUse", list);
		}
	}

	@Override
	public CarUse getCarUseById(int id) {
		return (CarUse) this.baseDao.queryForObject("oracle-carUseSQL.getCarUseById",id);
	}

	@Override
	public int insertCarUse(CarUse carUse) {
		    int areaName = queryCarUseNameIsExits(carUse);
			int result=0;
			if(areaName>0){
				result=-3;
			}else{
				carUse.setCreatetime(DateUtil.getSQLDate());
				result= this.baseDao.save("oracle-carUseSQL.insertCarUse", carUse);
				if(result>0){
					logService.addLogInfo(carUse.getUserid(), "添加车辆用途记录:车辆用途名称:"+carUse.getUsename()
							+";创建时间:"+
							carUse.getCreatetime()+";影响数据ID:"+carUse.getId(), 1);
				
				}
			}
			return result;
	}

	
	private Integer queryCarUseNameIsExits(CarUse carUse){
		
		return (Integer) this.baseDao.queryForObject("oracle-carUseSQL.queryCarUseNameIsExits",carUse);
	}
	
	@Override
	public Map selectPageCarUse(int currentPage, int everyPage, CarUse carUse) {
		return this.baseDao.findPageList("oracle-carUseSQL.selectPageCarUse", "oracle-carUseSQL.selectPageCarUseCount", carUse, currentPage, everyPage);
	}

	@Override
	public int updateCarUse(CarUse carUse) {
		int areaName = queryCarUseNameIsExits(carUse);
		int result=0;
		if(areaName>0){
			result=-3;
		}else{
			if(!SessionUtils.getUser().equals("admin")){	//普通用户
				carUse.setUserid(SessionUtils.getUser().getId());
			}
			result=this.baseDao.update("oracle-carUseSQL.updateCarUse", carUse);
			if(result>0){
				logService.addLogInfo(carUse.getUserid(), "更新车辆用途记录:车辆用途名称:"+carUse.getUsename()
						+";创建时间:"+
						DateUtil.getSQLDate()+";影响数据ID:"+carUse.getId(),2);
			    	return result;
		     }
		}
		
		return result;
	}

	
	/**
	 * 判断是否能删除当前记录
	 * @return
	 */
	public  Integer  judgeUseIsDelete(List<String>  list){
		
	   return	(Integer) this.baseDao.queryForObject("oracle-carUseSQL.judgeUseIsDelete",list);
	}
	
	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}
	

}
