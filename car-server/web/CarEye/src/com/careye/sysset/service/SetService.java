package com.careye.sysset.service;

import java.util.List;
import java.util.Map;

import com.careye.sysset.domain.AlarmtypeSet;
import com.careye.sysset.domain.DeviceType;
import com.careye.sysset.domain.PageSet;
import com.careye.sysset.domain.PaymentParamSet;

/**
* @项目名称：FMS
* @类名称：SetService
* @类描述：
* @创建人：lenovo
* @创建时间：2014-7-13 上午10:24:30
* @修改人：lenovo
* @修改时间：2014-7-13 上午10:24:30
* @修改备注：
* @version 1.0
 */
public interface SetService {
	
	
	
	/*********************************报警类型设置**********************************/
	
	
	/**
	 * 更新报警类型
	 * @param alarmmusicSet
	 * @return
	 */
	public int updateAlarmmusicSet(AlarmtypeSet alarmmusicSet);
	
	/**
	 * 分页报警类型列表
	 * @return
	 */
	public Map findPageAlarmmusicSetList(int currentPage, int everyPage,AlarmtypeSet alarmmusicSet);
	
	/**
	 * 删除报警类型设置
	 */
	public int deleteAlarmType(int id);


	/**
	 * 是否已存在报警类型缩写设置
	 */
	public int alarmNameKeyIsExist(AlarmtypeSet alarmtypeset);

	/**
	 * 是否已存在报警类型设置
	 */
	public int alarmNameIsExist(AlarmtypeSet alarmtypeset);
	
	/**
	 * 保存报警类型设置
	 */
	public Integer saveAlarmType(AlarmtypeSet alarmtypeset);

	
	
						/**=======================3.2.5	设备类型设置=====================**/
	
	/**
	 * 保存设备类型设置
	 */
	public Integer saveDeviceType(DeviceType deviceType);

	/**
	 * 获取最大设备类型id
	 */
	public int selectDeviceTypeMaxTypeId();
	
	/**
	 * 删除设备类型设置
	 */
	public int deleteDeviceType(int id);

	/**
	 * 删除设备类型设置之前查看车辆中是否已使用
	 */
	public int selectDeviceTypeByCar(int id);

	/**
	 * 是否存在设备类型设置
	 */
	public int getDeviceTypeCount(DeviceType deviceType);

	/**
	 * 分页设备类型设置列表
	 * @return
	 */
	public Map findPageDeviceTypeList(int currentPage, int everyPage,DeviceType deviceType);
	/**
	 * Excel设备类型设置列表
	 * @return
	 */
	public List<DeviceType> findPageDeviceTypeList(DeviceType deviceType);
	
	
	/**
	 * 界面展开关闭设置
	 * @param setParamSet
	 * @return
	 */
	public Integer savePageSet(PageSet pageSet);
	
	
	/*******************支付参数设置start***************************/
	
	/**
	 * 添加收款公司===支付参数设置 
	 */
	public Integer addCompany(PaymentParamSet paymentParamSet);
	
	/**
	 * 更新收款公司===支付参数设置
	 */
	public Integer updatePaymentParamSet(PaymentParamSet paymentParamSet);
	
	/**
	 * 删除收款公司===支付参数设置
	 */
	public Integer deleteCompany(String id);
	
	/**
	 * 获取收款公司分页列表
	 */
	public Map findCompanyList(int currentPage, int everyPage, PaymentParamSet paymentParamSet);
	
	/**
	 * 根据ID获取付款参数信息
	 */
	public PaymentParamSet findPaymentParamById(String id);
	
	/*******************支付参数设置end***************************/
	
}
