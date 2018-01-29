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

import com.careye.message.domain.MenuCar;
import com.careye.message.domain.MenuSendRecord;
import com.careye.message.domain.MenuSystem;
import com.careye.message.domain.ServiceInfo;
import com.careye.message.domain.ServiceSend;


/**
 * @项目名称：FMS
 * @类名称：MenuServiceService
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:00:03
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public interface MenuServiceService {
	/**
	 * 查询车辆菜单
	 * @param serviceSend
	 * @return
	 */
	public List<MenuCar> selectCheckCarMenu(MenuCar menuCar);

	/**
	 * 查询服务信息
	 * @param serviceSend
	 * @return
	 */
	public ServiceSend selectServiceInfoById(ServiceSend serviceSend);

	/**
	 * 查询车辆菜单
	 * @param serviceSend
	 * @return
	 */
	public MenuCar selectCheckCarMenuByID(int id);

	
	/**
	 * 通过点播菜单ID查询 信息服务表
	 * @param ServiceInfo
	 * @return
	 */
	public List<ServiceInfo> selectServiceInfoByQid(int menuid);
	/**
	 * 通过点播菜单ID查询 信息服务表  临时表
	 * @param ServiceInfo
	 * @return
	 */
	public List<ServiceInfo> selectServiceInfoByQidLin(int menuid);
	
	/**
	 * 通过点播菜单ID查询 信息服务表  临时表
	 * @param ServiceInfo
	 * @return
	 */
	public List<ServiceInfo> selectServiceInfoLin();
	
	
	/**
	 * 通过点播菜单ID查询 点播菜单表  
	 * @return
	 */
	public MenuSystem selectCheckMenuSystemById(int id);
	
	/**
	 * 根据流水号更新车辆菜单处理结果
	 * @param MenuSendRecord
	 * @return
	 */
	public int updateMenuCar(MenuCar menuCar);
	
	
	/**
	 * 添加车辆菜单
	 * @param MenuSendRecord
	 * @return
	 */
	public int insertMenuCar(MenuCar menuCar);
	
	
	/**
	 * 根据流水号更新信息服务下发记录处理结果
	 * @param MenuSendRecord
	 * @return
	 */
	public int updateServiceSendResult(ServiceSend serviceSend);
	
	
	/**
	 * 添加点播菜单发送记录
	 * @param MenuSendRecord
	 * @return
	 */
	public int insertServiceSend(ServiceSend serviceSend);
	
	
	/**
	 * 根据流水号更新点播处理结果
	 * @param MenuSendRecord
	 * @return
	 */
	public int updateMenuSendRecordResult(MenuSendRecord menuSendRecord);
	
	
	/**
	 * 添加点播菜单发送记录
	 * @param MenuSendRecord
	 * @return
	 */
	public int insertMenuSendRecord(MenuSendRecord menuSendRecord);
	
	/**
	 * 更新信息服务
	 * @param ServiceInfo
	 * @return
	 */
	public int updateServiceInfo(ServiceInfo serviceInfo);
	
	/**
	 * 更新信息服务  临时数据变成真实数据
	 * @param ServiceInfo
	 * @return
	 */
	public int updateServiceInfoByQid(MenuSystem menuSystem);
	
	
	/**
	 * 添加信息服务
	 * @param ServiceInfo
	 * @return
	 */
	public int insertServiceInfo(ServiceInfo serviceInfo);
	
	/**
	 * 更新点播菜单
	 * @param menuSystem
	 * @return
	 */
	public int updateMenuSystem(MenuSystem menuSystem);
	
	/**
	 * 添加点播菜单
	 * @param menuSystem
	 * @return
	 */
	public int insertMenuSystem(MenuSystem menuSystem);
	
	/**
	 * 菜单是否已存在
	 * @param id
	 * @return
	 */
	public int queryMenuSystemIsExist(MenuSystem menuSystem);
	
	/**
	 * 查询车牌号
	 * @param id
	 * @return
	 */
	public String selectCheckCarNumberById(int id);
	
	
	/**
	 * 删除菜单时  查看信息服务表 中是否使用
	 * @param id
	 * @return
	 */
	public int selectServiceInfoByMenuId(int id);
	
	/**
	 * 删除车辆菜单BY carnumber 
	 * @param id
	 * @return
	 */
	public int deleteCarMenuByCarnumber(String carnumber);
	
	
	/**
	 * 删除车辆菜单
	 * @param id
	 * @return
	 */
	public int deleteCarMenu(MenuSendRecord menuSendRecord);
	
	/**
	 * 删除点播菜单
	 * @param id
	 * @return
	 */
	public int deleteMenuSystem(int id);
	
	/**
	 * 删除临时点播菜单答案
	 * @param id
	 * @return
	 */
	public int deleteServiceInfoLin();	
	/**
	 * 删除真实点播菜单答案
	 * @param id
	 * @return
	 */
	public int deleteServiceInfoLinByFlag(int id);	
	
	/**
	 * 删除临时点播菜单答案
	 * @param id
	 * @return
	 */
	public int deleteServiceInfoLinById(int id);	
	
	/**
	 *  删除点播菜单答案
	 * @param id
	 * @return
	 */
	public int deleteServiceInfo(int menuid);
	
	
	/**
	 * 条件查询点播菜单
	 * @param menuSystem
	 * @return
	 */
	public Map selectCheckMenuSystem(final int currentPage, final int everyPage,MenuSystem menuSystem);

	/**
	 * 条件查询点播菜单发送记录
	 * @param menuSendRecord
	 * @return
	 */
	public Map selectCheckMenuSendRecord(final int currentPage, final int everyPage,
			MenuSendRecord menuSendRecord);


	/**
	 * 条件查询信息服务下发记录
	 * @param serviceSend
	 * @return
	 */
	public Map selectCheckServiceSend(final int currentPage, final int everyPage,
			ServiceSend serviceSend);

	/**
	 * 查询车辆菜单
	 * @param serviceSend
	 * @return
	 */
	public Map selectCheckCarMenu(final int currentPage, final int everyPage,
			MenuCar menuCar);
	
	/**
	 * 更新点播状态
	 * @param menuCar
	 * @return
	 */
	public int updateCarMenuState(MenuCar menuCar);


	/**
	 * 查询信息服务
	 * @param serviceSend
	 * @return
	 */
	public Map selectServiceInfoByCarNumber(final int currentPage, final int everyPage,
			ServiceInfo serviceInfo);

	
}
