/**
* Description: car-eye车辆管理平台
* 文件名：SetServiceImpl.java
* 版本信息：1.0
* 日期：2014-7-13
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.sysset.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.sysset.domain.AlarmtypeSet;
import com.careye.sysset.domain.DeviceType;
import com.careye.sysset.domain.PageSet;
import com.careye.sysset.domain.PaymentParamSet;
import com.careye.sysset.service.SetService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：car-eye
 * @类名称：SetServiceImpl
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-9 上午10:25:07
 * @修改人：huangqin
 * @修改时间：2015-3-9 上午10:25:07
 * @修改备注：
 * @version 1.0
 */
public class SetServiceImpl extends GenericService implements SetService{

	private SysOperateLogService logService;
	
	/*********************************报警提醒声音设置**********************************/
	
	/**
	 * 更新报警声音
	 * @param alarmmusicSet
	 * @return
	 */
	public int updateAlarmmusicSet(AlarmtypeSet alarmmusicSet) {
		
		if(!alarmmusicSet.getOldmusicaddr().equals(alarmmusicSet.getMusicaddr())){
			//删除之前的文件
			//1.先删除图片地址
			String savePath = ServletActionContext.getServletContext().getRealPath("")+"/";
			deleteFile(savePath+alarmmusicSet.getOldmusicaddr());
		}
		logService.addLogInfo(SessionUtils.getUserId(), "修改报警类型设置:报警类型名称:"+alarmmusicSet.getAlarmname(), 2);
		return this.baseDao.update("oracle-setSQL.updateAlarmmusicSet", alarmmusicSet);
	}
	
	 /** 
     * 删除单个文件 
     * @param   sPath    被删除文件的文件名 
     * @return 单个文件删除成功返回true，否则返回false 
     */  
    public  boolean deleteFile(String sPath) {  
        boolean flag = false;  
        File  file = new File(sPath);  
              // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) {  
                  file.delete();  
                  flag = true;  
        }  
        return flag;  
    }  
	
	/**
	 * 分页报警声音列表
	 * @return
	 */
	public Map findPageAlarmmusicSetList(int currentPage, int everyPage,AlarmtypeSet alarmmusicSet){
		logService.addLogInfo(SessionUtils.getUserId(), "查询报警类型设置", 5);
		return this.baseDao.findPageList("oracle-setSQL.findPageAlarmmusicSetList", "oracle-setSQL.findPageAlarmmusicSetListCount", alarmmusicSet, currentPage, everyPage);
	}
	

	/**
	 * 删除报警类型设置
	 */
	public int deleteAlarmType(int id){
		//通过报警类型id得到声音路径
		String musicaddrString = (String)this.baseDao.queryForObject("oracle-setSQL.getMusicaddr",id);
		int re = this.baseDao.delete("oracle-setSQL.deleteAlarmType", id);
		if (re >0) {
			if (musicaddrString!=null) {
				String savePath = ServletActionContext.getServletContext().getRealPath("")+"/";
				deleteFile(savePath+musicaddrString);
			}
		}
		logService.addLogInfo(SessionUtils.getUserId(), "删除报警类型设置:报警类型id:"+id, 3);
		return re;
	}


	/**
	 * 是否已存在报警类型缩写设置
	 */
	public int alarmNameKeyIsExist(AlarmtypeSet alarmtypeset){
		return this.baseDao.getCount("oracle-setSQL.alarmNameKeyIsExist", alarmtypeset);
	}
	/**
	 * 是否已存在报警类型设置
	 */
	public int alarmNameIsExist(AlarmtypeSet alarmtypeset){
		return this.baseDao.getCount("oracle-setSQL.alarmNameIsExist", alarmtypeset);
	}
	
	/**
	 * 保存报警类型设置
	 */
	public Integer saveAlarmType(AlarmtypeSet alarmtypeset){
		alarmtypeset.setCreatetime(DateUtil.getSQLDate());
		Integer integer = (Integer)this.baseDao.queryForObject("oracle-setSQL.selectAlarmTypeMaxId");
		if (integer!=null) {
			alarmtypeset.setAlarmkey("a"+integer);
		}else {
			alarmtypeset.setAlarmkey("a1");
		}
		logService.addLogInfo(SessionUtils.getUserId(), "添加报警类型设置:报警类型名称:"+alarmtypeset.getAlarmname(), 1);
		return this.baseDao.save("oracle-setSQL.addAlarmType", alarmtypeset);
	}

	
	

	

					/**=======================3.2.5	设备类型设置=====================**/
	/**
	 * 获取最大设备类型id
	 */
	public int selectDeviceTypeMaxTypeId(){
		Integer  re= (Integer)this.baseDao.queryForObject("oracle-setSQL.selectDeviceTypeMaxTypeId");
		if (re==null) {
			return 0;
		} else {
            return re;
		}
	}
	
	
	/**
	* 保存设备类型设置
	*/
	public Integer saveDeviceType(DeviceType deviceType){
		int count = (Integer)this.getDeviceTypeCount(deviceType);
		if(count > 0){	//存在
			return -3;
		}else {
			if (deviceType.getId()==null) {
				if (deviceType.getUsertype()==null) {
					deviceType.setUsertype(11);
				}
				deviceType.setCreatetime(DateUtil.getSQLDate());
				deviceType.setTypeid(this.selectDeviceTypeMaxTypeId()+1);
				logService.addLogInfo(SessionUtils.getUserId(), "添加终端类型:终端类型名称:"+deviceType.getTypename(), 1);
				return this.baseDao.save("oracle-setSQL.addDeviceType", deviceType);
			}else {
				logService.addLogInfo(SessionUtils.getUserId(), "修改终端类型:终端类型名称:"+deviceType.getTypename(), 2);
				return this.baseDao.update("oracle-setSQL.updateDeviceType", deviceType);
			}
		}
		//不存在添加
	}
	
	/**
	* 删除设备类型设置
	*/
	public int deleteDeviceType(int id){
		logService.addLogInfo(SessionUtils.getUserId(), "删除终端类型:终端类型ID:"+id, 3);
		return this.baseDao.delete("oracle-setSQL.deleteDeviceType", id);
	}

	/**
	 * 删除设备类型设置之前查看车辆中是否已使用
	 */
	public int selectDeviceTypeByCar(int id){
		return this.baseDao.getCount("oracle-setSQL.selectDeviceTypeByCar", id);
	}

	
	/**
	* 是否存在设备类型设置
	*/
	public int getDeviceTypeCount(DeviceType deviceType){
		return this.baseDao.getCount("oracle-setSQL.getDeviceTypeCount", deviceType);
	}
	
	/**
	* 分页设备类型设置列表
	* @return
	*/
	public Map findPageDeviceTypeList(int currentPage, int everyPage,DeviceType deviceType){
		logService.addLogInfo(SessionUtils.getUserId(), "查询终端类型", 5);
		return this.baseDao.findPageList("oracle-setSQL.findPageDeviceTypeList", "oracle-setSQL.findPageDeviceType",deviceType, currentPage, everyPage);
	}
	/**
	 * Excel设备类型设置列表
	 * @return
	 */
	public List<DeviceType> findPageDeviceTypeList(DeviceType deviceType){
		logService.addLogInfo(SessionUtils.getUserId(), "Excel导出终端类型", 4);
		return this.baseDao.queryForList("oracle-setSQL.findPageDeviceTypeList", deviceType);
	}
	
	/**
	 * 界面展开关闭设置
	 * @param setParamSet
	 * @return
	 */
	public Integer savePageSet(PageSet pageSet){
		//根据用户得到是否存在设置记录
		int count = (Integer)this.baseDao.queryForObject("oracle-setSQL.getPageSetCount", pageSet.getUserid());
		if(count > 0){	//存在更新
			return this.baseDao.update("oracle-setSQL.updatePageSet", pageSet);
		}
		//不存在添加
		pageSet.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-setSQL.addPageSet", pageSet);
	}
	
	
/*******************支付参数设置start***************************/
	
	/**
	 * 添加收款公司===支付参数设置 
	 */
	public Integer addCompany(PaymentParamSet paymentParamSet){
		paymentParamSet.setCreatetime(DateUtil.getSQLDate());
		//收款公司是否已存在
		Integer count = (Integer) this.baseDao.queryForObject("oracle-setSQL.companyIsExist", paymentParamSet);
		if(count > 0){
			return -1;
		}else{
			//编号是否已存在
			Integer tcount = (Integer) this.baseDao.queryForObject("oracle-setSQL.typeIsExist", paymentParamSet);
			if(tcount > 0){
				return -3;
			}else{
				if(paymentParamSet.getId() == null){
					logService.addLogInfo(SessionUtils.getUserId(), "添加付款参数设置:ID:"+paymentParamSet.getCompany(), 1);
					return this.baseDao.save("oracle-setSQL.addCompany", paymentParamSet);
				}else{
					logService.addLogInfo(SessionUtils.getUserId(), "修改付款参数设置:收款公司:"+paymentParamSet.getCompany(), 2);
					return this.baseDao.update("oracle-setSQL.updateCompany", paymentParamSet);
				}
			}
		}
		
		
	}
	
	/**
	 * 更新收款公司===支付参数设置
	 */
	public Integer updatePaymentParamSet(PaymentParamSet paymentParamSet){
		logService.addLogInfo(SessionUtils.getUserId(), "修改付款参数设置:ID:"+paymentParamSet.getId(), 2);
		return this.baseDao.update("oracle-setSQL.updatePaymentParamSet", paymentParamSet);
	}
	
	/**
	 * 删除收款公司===支付参数设置
	 */
	public Integer deleteCompany(String id){
		logService.addLogInfo(SessionUtils.getUserId(), "删除付款参数设置:ID:"+id, 3);
		return this.baseDao.delete("oracle-setSQL.deleteCompany", id);
	}
	
	/**
	 * 获取收款公司分页列表
	 */
	public Map findCompanyList(int currentPage, int everyPage, PaymentParamSet paymentParamSet){
		return this.baseDao.findPageList("oracle-setSQL.findCompanyList", 
				"oracle-setSQL.findCompanyListCount", paymentParamSet, currentPage, everyPage);
	}
	
	/**
	 * 根据ID获取付款参数信息
	 */
	public PaymentParamSet findPaymentParamById(String id){
		return (PaymentParamSet) this.baseDao.queryForObject("oracle-setSQL.findPaymentParamById", id);
	}
	
	/*******************支付参数设置end***************************/

	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}
	
	
}






