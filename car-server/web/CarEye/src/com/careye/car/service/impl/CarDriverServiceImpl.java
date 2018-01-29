/**
* Description: car-eye车辆管理平台
* 文件名：CarServiceImpl.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.If;

import com.careye.base.service.GenericService;
import com.careye.car.domain.CarDriver;
import com.careye.car.domain.PreJobExam;
import com.careye.car.domain.ServiceLicense;
import com.careye.car.domain.TaxiMeter;
import com.careye.car.service.CarDriverService;
import com.careye.common.service.SysOperateLogService;
import com.careye.sysset.domain.CarType;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：car-eye
 * @类名称：CarDriverServiceImpl
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-10 上午10:00:03
 * @修改人：huangqin
 * @修改时间：2015-3-10 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public class CarDriverServiceImpl extends GenericService implements CarDriverService{

	private SysOperateLogService logService;
	
	/**
	 * 获取最新插入的id
	 * @return
	 */
	public int getNewId(){
		return (Integer)this.baseDao.queryForObject("oracle-carDriverQL.getNewId");
	}
	
	/**
	 * 更新司机代码
	 * @param carDriver
	 * @return
	 */
	public int updateDrivercode(CarDriver carDriver){
		return this.baseDao.update("oracle-carDriverQL.updateDrivercode", carDriver);
	}
	
	/**
	 * 司机是否已存在
	 * @param carnumber
	 * @param carstatus
	 * @return
	 */
	@Override
	public int queryDriverNameIsExist(CarDriver carDriver) {
		return (Integer)this.baseDao.queryForObject("oracle-carDriverQL.queryDriverNameIsExist", carDriver);
	}
	
	/**
	 * 查询服务监督卡号是否存在
	 * @param 
	 * @return
	 */
	@Override
	public int getDrivercodeIsExist(CarDriver carDriver){
		return (Integer)this.baseDao.queryForObject("oracle-carDriverQL.getDrivercodeIsExist", carDriver);
	}
	
	/**
	 * 删除司机信息
	 * @param id
	 * @return
	 */
	//@Log(operationType="delete操作:",operationName="删除司机信息记录")
	public int deleteCarDriver(List<String> list) {
		int result = -1;
		//判断被车辆使用的司机 是否存在
//		if(this.isExitsCarByCarDriverUse(list)>0){
		if(false){
			return  -3;
		}else{
			for(int i=0;i<list.size();i++){
				CarDriver carDriver=this.getCarDriverById(Integer.parseInt(list.get(i)));
				
				logService.addLogInfo(carDriver.getUserid(), "删除司机信息记录:司机姓名:"+carDriver.getDrivername()
						+";驾驶证号:"+
						carDriver.getDrivecrednum()+";影响数据ID:"+carDriver.getId(), 3);
				
			}
			result = this.baseDao.delete("oracle-carDriverQL.deleteCarDriver", list);
			deleteDriverLicense(list);//删除对应的驾驶证信息
			deletePreJobExam(list);//删除岗前考试信息
			deleteServiceLicense(list);//删除服务证信息
			return result;
		}
	}
	
	/**
	 * 判断被车辆使用的司机 是否存在
	 * @param list
	 * @return
	 */
	public Integer isExitsCarByCarDriverUse(List<String> list){
		
		 return  (Integer) this.baseDao.queryForObject("oracle-carDriverQL.isExitsCarByCarDriverUse",list);
	}
	
	/**
	 * 通过id得到车辆司机信息
	 * @param id
	 * @return
	 */
	private  CarDriver  getCarDriverById(int id){
		return (CarDriver) this.baseDao.queryForObject("oracle-carDriverQL.getCarDriverById",id);
	}
	
	/**
	 * 添加司机信息
	 * @param carDriver
	 * @return
	 */
	//@Log(operationType="add操作:",operationName="添加司机信息记录")
	@Override
	public int insertCarDriver(CarDriver carDriver) {
		int result=-1;
		carDriver.setCreatetime(DateUtil.getSQLDate());
	    result=  this.baseDao.save("oracle-carDriverQL.insertCarDriver", carDriver);
	    Integer driverid = getNewDriverid();//获取最新插入的驾驶员id
	    carDriver.setDriverid(driverid);
	    insertDriverLicense(carDriver);//插入驾驶证信息
		if(result>0){
			logService.addLogInfo(carDriver.getUserid(), "添加司机信息记录:司机姓名:"+carDriver.getDrivername()
					+";驾驶证号:"+
					carDriver.getDrivecrednum()+";影响数据ID:"+carDriver.getId(), 1);
		}
		return result;
	}
	

     /**
	 * 查询司机信息
	 * @param carDriver
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckCarDriver(int currentPage, int everyPage,
			CarDriver carDriver) {
		logService.addLogInfo(SessionUtils.getUserId(), "查询司机信息",5);
		return this.baseDao.findPageList("oracle-carDriverQL.selectCheckCarDriver",
				"oracle-carDriverQL.selectCarDriver", carDriver,currentPage,everyPage);
	}
	
	/**
	 * 分页查询司机服务证列表信息
	 * @param carDriver
	 * @return
	 */
	public Map queryServicePhotoList(final int currentPage, final int everyPage,ServiceLicense serviceLicense){
		return this.baseDao.findPageList("oracle-carDriverQL.queryServicePhotoList",
				"oracle-carDriverQL.queryServicePhotoListCount", serviceLicense,currentPage,everyPage);
	}
	
	/**
	 * Excel导出
	 * @param carDriver
	 * @return
	 */
	@Override
	public List<CarDriver> getAllCarDriver(CarDriver carDriver) {
		logService.addLogInfo(SessionUtils.getUserId(), "Excel导出司机信息", 4);
		return this.baseDao.queryForList("oracle-carDriverQL.selectCheckCarDriver",carDriver);
	}
	
	/**
	 * 更新司机信息
	 * @param carDriver
	 * @return
	 */
	//@Log(operationType="update操作:",operationName="更新司机信息记录")
	@Override
	public int updateCarDriver(CarDriver carDriver) {
		int result=-1;
	    result=  this.baseDao.update("oracle-carDriverQL.updateCarDriver", carDriver);
	    updateDriverLicense(carDriver);//更新驾驶证信息
		if(result>0){
			logService.addLogInfo(carDriver.getUserid(), "更新司机信息记录:司机姓名:"+carDriver.getDrivername()
					+";驾驶证号:"+
					carDriver.getDrivecrednum()+";影响数据ID:"+carDriver.getId(), 2);
		}
		return result;
	}

	/**
	 * 根据司机姓名获取司机ID
	 * @param drivername
	 * @return
	 */
	@Override
	public int getCarDriverId(String drivername) {
		int id = 0;
		Object re = this.baseDao.queryForObject("oracle-carDriverQL.getCarDriverId", drivername);
		if(re != null){
			id = (Integer) re;
		}
		return id;
	}

	
	  /**
	 * 查询当班司机信息
	 * @param carDriver
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectDutyCarDriver(int currentPage, int everyPage,
			CarDriver carDriver) {
		return this.baseDao.findPageList("oracle-carDriverQL.selectDutyCarDriver",
				"oracle-carDriverQL.selectDutyCarDriverCount", carDriver,currentPage,everyPage);
	}
	
	
	  /**
	 * 查询计价器信息
	 * @param carDriver
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectTaxiMeter(int currentPage, int everyPage,
			TaxiMeter taxiMeter) {
		return this.baseDao.findPageList("oracle-carDriverQL.selectTaxiMeter",
				"oracle-carDriverQL.selectTaxiMeterCount", taxiMeter,currentPage,everyPage);
	}
	
	/**
	 * 获取最新驾驶员id
	 * @param carDriver
	 * @return
	 */
	public Integer getNewDriverid(){
		return (Integer) this.baseDao.queryForObject("oracle-carDriverQL.getNewDriverid");
	}
	
	/**
	 * 添加驾驶证信息
	 * @param carDriver
	 * @return
	 */
	public int insertDriverLicense(CarDriver carDriver){
		return this.baseDao.save("oracle-carDriverQL.insertDriverLicense", carDriver);
	}
	
	/**
	 * 修改驾驶证信息
	 * @param carDriver
	 * @return
	 */
	public int updateDriverLicense(CarDriver carDriver){
		return this.baseDao.update("oracle-carDriverQL.updateDriverLicense", carDriver);
	}

	/**
	 * 删除驾驶员信息
	 * @param id
	 * @return
	 */
	public int deleteDriverLicense(List<String> list){
		return this.baseDao.delete("oracle-carDriverQL.deleteDriverLicense", list);
	}
	
	/**
	 * 根据driver获取岗前考试信息
	 * @param seq
	 * @return
	 */
	public PreJobExam findPreJobExamByDriverid(String driverid){
		return (PreJobExam) this.baseDao.queryForObject("oracle-carDriverQL.findPreJobExamByDriverid", driverid);
	}
	
	/**
	 * 新增岗前考试信息
	 * @param seq
	 * @return
	 */
	public int insertPreJobExam(PreJobExam preJobExam){
		preJobExam.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-carDriverQL.insertPreJobExam", preJobExam);
	}
	
	/**
	 * 根据id修改岗前考试信息
	 * @param seq
	 * @return
	 */
	public int updatePreJobExam(PreJobExam preJobExam){
		return this.baseDao.update("oracle-carDriverQL.updatePreJobExam", preJobExam);
	}
	
	/**
	 * 根据driverid删除岗前考试信息
	 * @param seq
	 * @return
	 */
	public int deletePreJobExam(List<String> list){
		return this.baseDao.delete("oracle-carDriverQL.deletePreJobExam", list);
	}
	
	/**
	 * 根据driver获取服务证信息
	 * @param seq
	 * @return
	 */
	public ServiceLicense findServiceLicenseByDriverid(String driverid){
		return (ServiceLicense) this.baseDao.queryForObject("oracle-carDriverQL.findServiceLicenseByDriverid", driverid);
	}
	
	/**
	 * 新增服务证信息
	 * @param seq
	 * @return
	 */
	public int insertServiceLicense(ServiceLicense serviceLicense){
		serviceLicense.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-carDriverQL.insertServiceLicense", serviceLicense);
	}
	
	/**
	 * 根据id修改服务证信息
	 * @param seq
	 * @return
	 */
	public int updateServiceLicense(ServiceLicense serviceLicense){
		return this.baseDao.update("oracle-carDriverQL.updateServiceLicense", serviceLicense);
	}
	
	/**
	 * 根据driverid删除服务证信息
	 * @param seq
	 * @return
	 */
	public int deleteServiceLicense(List<String> list){
		return this.baseDao.delete("oracle-carDriverQL.deleteServiceLicense", list);
	}
	
	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

}
