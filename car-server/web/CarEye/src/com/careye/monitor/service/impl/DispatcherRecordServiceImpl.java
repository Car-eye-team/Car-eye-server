/**
 * Description: car-eye车辆管理平台
 * 文件名：RemoteControlReocordServiceImp.java
 * 版本信息：1.0
 * 日期：2015-3-24
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.monitor.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.car.domain.CarDriver;
import com.careye.car.domain.CarInfo;
import com.careye.common.service.SysOperateLogService;
import com.careye.message.domain.SendRecord;
import com.careye.monitor.domain.DispatcherInfo;
import com.careye.monitor.domain.DispatcherRecord;
import com.careye.monitor.domain.RemoteControl;
import com.careye.monitor.service.DispatcherRecordService;
import com.careye.monitor.service.RemoteControlRecordService;
import com.careye.mq.HandleUtil;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：car-eye
 * @类名称：RemoteControlReocordServiceImp
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-24 上午09:36:12
 * @修改人：Yuqk
 * @修改时间：2015-3-24 上午09:36:12
 * @修改备注：
 * @version 1.0
 */
public class DispatcherRecordServiceImpl extends GenericService implements
DispatcherRecordService {
	private  SysOperateLogService logService;
	
	/**
	 *分页查询调度记录
	 */
	@Override
	public Map queryDispatcherRecord(Integer page, Integer limit,DispatcherRecord dispatcherRecord) {
		return this.baseDao.findPageList(
				"oracle-dispatcherRecordSQL.queryDispatcherRecord",
				"oracle-dispatcherRecordSQL.queryDispatcherRecordCount",
				dispatcherRecord, page, limit);
	}

	/**
	 * 不分页查询调度记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DispatcherRecord> selectRecordList(DispatcherRecord dispatcherRecord) {
		logService.addLogInfo(SessionUtils.getUserId(), "Excel导出调度记录信息", 4);
		return this.baseDao.queryForList(
				"oracle-dispatcherRecordSQL.queryDispatcherRecord",
				dispatcherRecord);
	}

	public SysOperateLogService getLogService() {
		return logService;
	}
	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	
	/**
	 *分页查询调度记录
	 */
	@Override
	public Map queryDispatcherInfo(Integer page, Integer limit,DispatcherInfo dispatcherInfo) {
		return this.baseDao.findPageList(
				"oracle-dispatcherRecordSQL.queryDispatcherInfo",
				"oracle-dispatcherRecordSQL.queryDispatcherInfoCount",
				dispatcherInfo, page, limit);
	}
	
	
	/**
	 * 添加调度记录信息
	 * @param carDriver
	 * @return
	 */
	//@Log(operationType="add操作:",operationName="添加调度信息记录")
	@Override
	public int insertDispatcherInfo(DispatcherInfo dispatcherInfo) {
		int result=-1;
		if(dispatcherInfo.getFlag()==1){      //发给车辆和服务器
			dispatcherInfo.setCreatetime(DateUtil.getSQLDate());
			//判断是否存在
			int re=this.getJudgeDispatcherInfoCount(dispatcherInfo);
			if(re<=0){
			    this.baseDao.save("oracle-dispatcherRecordSQL.insertDispatcherInfo", dispatcherInfo);
				if(result>0){
					logService.addLogInfo(dispatcherInfo.getUserid(), "添加调度信息记录:调度车辆id:"+dispatcherInfo.getCaridsStr()+
							";调度内容:"+dispatcherInfo.getRemark()+
							";调度时间:"+dispatcherInfo.getCreatetime()+
							";影响数据ID:"+dispatcherInfo.getId(), 1);
					
					
				}
			}
		}
		
		result=CheckTextInfo(dispatcherInfo);
		
		return result;
	}
	
	
	private Integer getJudgeDispatcherInfoCount(DispatcherInfo dispatcherInfo){
		
		Object obj=this.baseDao.queryForObject("oracle-dispatcherRecordSQL.getJudgeDispatcherInfoCount",dispatcherInfo);
		Integer objInteger=0;
		if(obj!=null){
			objInteger=(Integer)obj;
		}
		return  objInteger;
	}
	
	/**
	 * 调度下发 并添加记录
	 * @param textInfo
	 * @return
	 */
	@SuppressWarnings("static-access")
	private Integer  CheckTextInfo(DispatcherInfo dispatcherInfo){
		   int su=1;
		    try {
		    	List<CarInfo> list=this.SelectCarInfo(dispatcherInfo.getCarids());
				for (CarInfo car: list) {
						//下发
						int seq=HandleUtil.getSerialId(); 
						HandleUtil.textInfo(car.getUsertype(),car.getTerminal(),
								                    dispatcherInfo.getEmergency(),
													dispatcherInfo.getLcd(),
													dispatcherInfo.getTts(),
													dispatcherInfo.getAdv(),
													dispatcherInfo.getAction(),
													0,//dispatcherInfo.getDist(),
													dispatcherInfo.getRemark(),
													seq,0,car.getCarnumber());
						DispatcherRecord  dispatcherRecord=new DispatcherRecord();
					    
						
						//dispatcherRecord.setStatus(1);   //成功
					    
					    dispatcherRecord.setBlocid(dispatcherInfo.getBlocid());
					    dispatcherRecord.setUserid(dispatcherInfo.getUserid());
					    dispatcherRecord.setRemark(dispatcherInfo.getRemark());
					    dispatcherRecord.setEmergency(dispatcherInfo.getEmergency());
					    dispatcherRecord.setLcd(dispatcherInfo.getLcd());
					    dispatcherRecord.setTts(dispatcherInfo.getTts());
					    dispatcherRecord.setAdv(dispatcherInfo.getAdv());
					    dispatcherRecord.setAction(dispatcherInfo.getAction());
					    dispatcherRecord.setDist(dispatcherInfo.getDist());
					    dispatcherRecord.setCarnumber(car.getCarnumber());
					    dispatcherRecord.setSeq(seq);
					    
					    dispatcherRecord.setCreatetime(DateUtil.getSQLDate());
						this.baseDao.save("oracle-dispatcherRecordSQL.insertDispatcherRecord", dispatcherRecord);
						
			}
			} catch (Exception e) {
				su=-1;
			}
			
			return su;
	}
	

	private  List<CarInfo>  SelectCarInfo(List<Integer> carids){
		return this.baseDao.queryForList("oracle-dispatcherRecordSQL.selectCarInfo", carids);
	}
	
	
	/**
	 * 更新调度信息
	 * @param carDriver
	 * @return
	 */
	//@Log(operationType="update操作:",operationName="更新调度信息记录")
	@Override
	public int updateDispatcherInfo(DispatcherInfo dispatcherInfo) {
		int result=-1;
	    result=  this.baseDao.update("oracle-dispatcherRecordSQL.updateDispatcherInfo", dispatcherInfo);
		if(result>0){
			logService.addLogInfo(dispatcherInfo.getUserid(), "更新调度信息记录:调度内容:"+
					dispatcherInfo.getRemark()+";影响数据ID:"+dispatcherInfo.getId(), 2);
		}
		return result;
	}

}
