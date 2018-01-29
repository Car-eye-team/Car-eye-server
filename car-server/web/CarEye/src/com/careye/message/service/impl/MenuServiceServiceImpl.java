/**
* Description: 多森商用车平台
* 文件名：CarServiceImpl.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.message.domain.MenuCar;
import com.careye.message.domain.MenuSendRecord;
import com.careye.message.domain.MenuSystem;
import com.careye.message.domain.SendRecord;
import com.careye.message.domain.ServiceInfo;
import com.careye.message.domain.ServiceSend;
import com.careye.message.service.EventService;
import com.careye.message.service.MenuServiceService;
import com.careye.utils.DateUtil;

/**
 * @项目名称：FMS
 * @类名称：QuestionServiceImpl
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:05:25
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:05:25
 * @修改备注：
 * @version 1.0
 */
public class MenuServiceServiceImpl extends GenericService implements MenuServiceService{

	/**
	 * 查询车辆菜单
	 * @param menuSystem
	 * @return
	 */
	@Override
	public List<MenuCar> selectCheckCarMenu(MenuCar menuCar) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-menuserviceSQL.selectCheckCarMenu", menuCar);
	}

	/**
	 * 查询服务信息
	 * @param menuSystem
	 * @return
	 */
	@Override
	public ServiceSend selectServiceInfoById(ServiceSend serviceSend) {
		// TODO Auto-generated method stub
		return (ServiceSend)this.baseDao.queryForObject("oracle-menuserviceSQL.selectServiceInfoById", serviceSend);
	}
	
	/**
	 * 查询车辆菜单
	 * @param menuSystem
	 * @return
	 */
	@Override
	public MenuCar selectCheckCarMenuByID(int id) {
		// TODO Auto-generated method stub
		return (MenuCar)this.baseDao.queryForObject("oracle-menuserviceSQL.selectCheckCarMenuByID", id);
	}
	
	/**
	 * 通过点播菜单ID查询 点播菜单答案表
	 * @param menuSystem
	 * @return
	 */
	@Override
	public List<ServiceInfo> selectServiceInfoByQid(int menuid) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-menuserviceSQL.selectServiceInfoByQid", menuid);
	}

	/**
	 * 通过点播菜单ID查询 点播菜单答案表   临时表
	 * @param menuSystem
	 * @return
	 */
	@Override
	public List<ServiceInfo> selectServiceInfoByQidLin(int menuid) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-menuserviceSQL.selectServiceInfoByQidLin", menuid);
	}
	/**
	 * 通过点播菜单ID查询 点播菜单答案表   临时表
	 * @param menuSystem
	 * @return
	 */
	@Override
	public List<ServiceInfo> selectServiceInfoLin() {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-menuserviceSQL.selectServiceInfoLin");
	}

	/**
	 * 通过点播菜单ID查询 点播菜单表
	 * @return
	 */
	@Override
	public MenuSystem selectCheckMenuSystemById(int id) {
		// TODO Auto-generated method stub
		return (MenuSystem)this.baseDao.queryForObject("oracle-menuserviceSQL.selectCheckMenuSystemById", id);
	}
	/**
	 * 添加点播菜单发送记录
	 * @param menuSystem
	 * @return
	 */
	@Override
	public int insertMenuSendRecord(MenuSendRecord menuSendRecord) {
		// TODO Auto-generated method stub
		menuSendRecord.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-menuserviceSQL.insertMenuSendRecord", menuSendRecord);
	}


	
	/**
	 * 添加车辆菜单
	 * @param menuCar
	 * @return
	 */
	@Override
	public int insertMenuCar(MenuCar menuCar) {
		// TODO Auto-generated method stub
		menuCar.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-menuserviceSQL.insertMenuCar", menuCar);
	}


	/**
	 * 根据流水号更新车辆菜单处理结果
	 * @param menuCar
	 * @return
	 */
	@Override
	public int updateMenuCar(MenuCar menuCar) {
		
		this.baseDao.update("oracle-menuserviceSQL.updateMenuRecordCar", menuCar);
		return this.baseDao.update("oracle-menuserviceSQL.updateMenuCar", menuCar);
	}
	
	/**
	 * 添加点播菜单发送记录
	 * @param serviceSend
	 * @return
	 */
	@Override
	public int insertServiceSend(ServiceSend serviceSend) {
		// TODO Auto-generated method stub
		serviceSend.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-menuserviceSQL.insertServiceSend", serviceSend);
	}


	/**
	 * 根据流水号更新信息服务下发记录处理结果
	 * @param serviceSend
	 * @return
	 */
	@Override
	public int updateServiceSendResult(ServiceSend serviceSend) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-menuserviceSQL.updateServiceSendResult", serviceSend);
	}
	
	
	
	/**
	 * 添加点播菜单答案 
	 * @param menuSystem
	 * @return
	 */
	@Override
	public int insertServiceInfo(ServiceInfo eventCar) {
		// TODO Auto-generated method stub
		eventCar.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-menuserviceSQL.insertServiceInfo", eventCar);
	}


	/**
	 * 更新点播菜单答案 
	 * @param menuSystem
	 * @return
	 */
	@Override
	public int updateServiceInfo(ServiceInfo serviceInfo) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-menuserviceSQL.updateServiceInfo", serviceInfo);
	}
	/**
	 * 更新点播菜单答案 
	 * @param menuSystem
	 * @return
	 */
	@Override
	public int updateServiceInfoByQid(MenuSystem menuSystem) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-menuserviceSQL.updateServiceInfoByQid", menuSystem);
	}
	
	/**
	 *  菜单是否已存在
	 * @param id
	 * @return
	 */
	@Override
	public int queryMenuSystemIsExist(MenuSystem menuSystem) {
		// TODO Auto-generated method stub
		return this.baseDao.getCount("oracle-menuserviceSQL.queryMenuSystemIsExist", menuSystem);
	}

	/**
	 *  菜单是否已存在
	 * @param id
	 * @return
	 */
	@Override
	public String selectCheckCarNumberById(int id) {
		// TODO Auto-generated method stub
		return (String)this.baseDao.queryForObject("oracle-menuserviceSQL.selectCheckCarNumberById", id);
	}
	
	
	/**
	 * 删除菜单时  查看信息服务表 中是否使用
	 * @param id
	 * @return
	 */
	@Override
	public int selectServiceInfoByMenuId(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.getCount("oracle-menuserviceSQL.selectServiceInfoByMenuId", id);
	}
	
	/**
	 * 删除车辆菜单
	 * @param id
	 * @return
	 */
	@Override
	public int deleteCarMenu(MenuSendRecord menuSendRecord) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-menuserviceSQL.deleteCarMenu", menuSendRecord);
	}
	/**
	 * 删除车辆菜单BY carnumber 
	 * @param id
	 * @return
	 */
	@Override
	public int deleteCarMenuByCarnumber(String carnumber) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-menuserviceSQL.deleteCarMenuByCarnumber", carnumber);
	}
	/**
	 * 删除点播菜单
	 * @param id
	 * @return
	 */
	@Override
	public int deleteMenuSystem(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-menuserviceSQL.deleteMenuSystem", id);
	}

	/**
	 * 删除点播菜单答案
	 * @param id
	 * @return
	 */
	@Override
	public int deleteServiceInfo(int menuid) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-menuserviceSQL.deleteServiceInfo", menuid);
	}
	/**
	 * 删除临时点播菜单答案
	 * @param id
	 * @return
	 */
	@Override
	public int deleteServiceInfoLin() {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-menuserviceSQL.deleteServiceInfoLin",null);
	}
	/**
	 * 删除临时点播菜单答案
	 * @param id
	 * @return
	 */
	@Override
	public int deleteServiceInfoLinByFlag(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-menuserviceSQL.deleteServiceInfoLinByFlag",id);
	}
	/**
	 * 删除临时点播菜单答案
	 * @param id
	 * @return
	 */
	@Override
	public int deleteServiceInfoLinById(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-menuserviceSQL.deleteServiceInfoLinById",id);
	}
	/**
	 * 添加点播菜单
	 * @param menuSystem
	 * @return
	 */
	@Override
	public int insertMenuSystem(MenuSystem menuSystem) {
		// TODO Auto-generated method stub
		menuSystem.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-menuserviceSQL.insertMenuSystem", menuSystem);
	}


	/**
	 * 更新点播菜单
	 * @param menuSystem
	 * @return
	 */
	@Override
	public int updateMenuSystem(MenuSystem menuSystem) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-menuserviceSQL.updateMenuSystem", menuSystem);
	}

	/**
	 * 根据流水号更新点播处理结果
	 * @param menuSendRecord
	 * @return
	 */
	@Override
	public int updateMenuSendRecordResult(MenuSendRecord menuSendRecord) {
		return this.baseDao.update("oracle-menuserviceSQL.updateMenuSendRecordResult", menuSendRecord);
	}

    /**
	 * 查询点播菜单
	 * @param menuSystem
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckMenuSystem(int currentPage, int everyPage,
			MenuSystem menuSystem) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-menuserviceSQL.selectCheckMenuSystem",
				"oracle-menuserviceSQL.selectMenuSystem", menuSystem,currentPage,everyPage);
	}

	
    /**
	 * 查询点播菜单发送记录
	 * @param menuSendRecord
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckMenuSendRecord(int currentPage, int everyPage,
			MenuSendRecord menuSendRecord) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-menuserviceSQL.selectCheckMenuSendRecord",
				"oracle-menuserviceSQL.selectMenuSendRecord", menuSendRecord,currentPage,everyPage);
		
	}
	
    /**
	 * 条件查询信息服务下发记录
	 * @param serviceSend
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckServiceSend(int currentPage, int everyPage,
			ServiceSend serviceSend) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-menuserviceSQL.selectCheckServiceSend",
				"oracle-menuserviceSQL.selectServiceSend", serviceSend,currentPage,everyPage);
		
	}
	 /**
	 * 条件查询车辆菜单
	 * @param serviceSend
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckCarMenu(int currentPage, int everyPage,
			MenuCar menuCar) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-menuserviceSQL.selectCheckCarMenu",
				"oracle-menuserviceSQL.selectCarMenu", menuCar,currentPage,everyPage);
		
	}
	 /**
	 * 条件查询信息服务
	 * @param serviceInfo
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectServiceInfoByCarNumber(int currentPage, int everyPage,
			ServiceInfo serviceInfo) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-menuserviceSQL.selectServiceInfoByCarNumber",
				"oracle-menuserviceSQL.selectServiceInfoByCarNumberCount", serviceInfo,currentPage,everyPage);
		
	}
	/**
	 * 更新点播状态
	 * @param menuCar
	 * @return
	 */
	@Override
	public int updateCarMenuState(MenuCar menuCar) {
		return this.baseDao.update("oracle-menuserviceSQL.updateCarMenuState", menuCar);
	}

}
