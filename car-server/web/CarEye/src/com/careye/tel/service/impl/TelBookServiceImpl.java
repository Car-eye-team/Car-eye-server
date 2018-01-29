/**
* Description: 多森商用车平台
* 文件名：CarServiceImpl.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.tel.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.tel.domain.TelBookCar;
import com.careye.tel.domain.TelBookSend;
import com.careye.tel.domain.TelBookSystem;
import com.careye.tel.service.TelBookService;
import com.careye.utils.DateUtil;

/**
 * @项目名称：FMS
 * @类名称：EventServiceImpl
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:05:25
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:05:25
 * @修改备注：
 * @version 1.0
 */
public class TelBookServiceImpl extends GenericService implements TelBookService{

	/**
	 * id查询系统事件
	 * @param telBookSystem
	 * @return
	 */
	@Override
	public TelBookSystem selectTelBookSystemById(int id) {
		// TODO Auto-generated method stub
		return (TelBookSystem)this.baseDao.queryForObject("oracle-telbookSQL.selectTelBookSystemById", id);
	}
	
	/**
	 * id查询系统事件
	 * @param telBookSystem
	 * @return
	 */
	@Override
	public int queryTelBookIsExist(TelBookSystem telBookSystem) {
		// TODO Auto-generated method stub
		return this.baseDao.getCount("oracle-telbookSQL.queryTelBookIsExist", telBookSystem);
	}
	
	
	/**
	 * 通 过   车牌号   查询车辆事件列表
	 * @param telBookSystem
	 * @return
	 */
	@Override
	public List<TelBookCar> selectChecktelBookCarByCarNumber(TelBookCar telBookCar) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-telbookSQL.selectChecktelBookCarByCarNumber", telBookCar);
	}
	/**
	 * 通 过   ID   查询车辆事件列表
	 * @param telBookSystem
	 * @return
	 */
	@Override
	public TelBookCar selectTelBookCarById(int id) {
		// TODO Auto-generated method stub
		return (TelBookCar)this.baseDao.queryForObject("oracle-telbookSQL.selectTelBookCarById", id);
	}
	/**
	 * 删除系统事件时查看车辆事件中是否已存在
	 * @param telBookSystem
	 * @return
	 */
	@Override
	public int queryTelBookCarIsExist(int eventid) {
		// TODO Auto-generated method stub
		return this.baseDao.getCount("oracle-telbookSQL.queryTelBookCarIsExist", eventid);
	}
	
	/**
	 * 添加事件发送记录
	 * @param telBookSystem
	 * @return
	 */
	@Override
	public int insertTelBookSend(TelBookCar telBookCar) {
		// TODO Auto-generated method stub
		telBookCar.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-telbookSQL.insertTelBookSend", telBookCar);
	}


	/**
	 * 更新事件发送记录
	 * @param telBookSystem
	 * @return
	 */
	@Override
	public int updateTelBookSend(TelBookCar telBookCar) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-telbookSQL.updateTelBookSend", telBookCar);
	}

	/**
	 * 添加车辆事件
	 * @param telBookSystem
	 * @return
	 */
	@Override
	public int insertTelBookCar(TelBookCar telBookCar) {
		// TODO Auto-generated method stub
		telBookCar.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-telbookSQL.insertTelBookCar", telBookCar);
	}


	/**
	 * 更新车辆事件
	 * @param telBookSystem
	 * @return
	 */
	@Override
	public int updateTelBookCar(TelBookCar telBookCar) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-telbookSQL.updateTelBookCar", telBookCar);
	}
	
	/**
	 * 删除车辆事件
	 * @param id
	 * @return
	 */
	@Override
	public int deleteTelBookCar(TelBookCar telBookCar) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-telbookSQL.deleteTelBookCar", telBookCar);
	}
	
	/**
	 * 删除系统事件
	 * @param id
	 * @return
	 */
	@Override
	public int deleteTelBookSystem(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-telbookSQL.deleteTelBookSystem", id);
	}

	/**
	 * 添加系统事件
	 * @param telBookSystem
	 * @return
	 */
	@Override
	public int insertTelBookSystem(TelBookSystem telBookSystem) {
		// TODO Auto-generated method stub
		telBookSystem.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-telbookSQL.insertTelBookSystem", telBookSystem);
	}

     /**
	 * 查询系统事件
	 * @param telBookSystem
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckTelBookSystem(int currentPage, int everyPage,
			TelBookSystem telBookSystem) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-telbookSQL.selectCheckTelBookSystem",
				"oracle-telbookSQL.selectTelBookSystem", telBookSystem,currentPage,everyPage);
	}

	/**
	 * 更新系统事件
	 * @param telBookSystem
	 * @return
	 */
	@Override
	public int updateTelBookSystem(TelBookSystem telBookSystem) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-telbookSQL.updateTelBookSystem", telBookSystem);
	}

    /**
	 * 查询事件下发记录列表
	 * @param TelBookSend
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckTelBookSend(int currentPage, int everyPage,
			TelBookSend eventTelBookSend) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-telbookSQL.selectCheckTelBookSend",
				"oracle-telbookSQL.selectTelBookSendCount", eventTelBookSend,currentPage,everyPage);
	}    
	/**
	 *  通 过   车牌号   查询车辆事件列表
	 * @param telBookSystem
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectChecktelBookCarByCarNumber(int currentPage, int everyPage,
			TelBookCar telBookCar) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-telbookSQL.selectChecktelBookCarByCarNumber",
				"oracle-telbookSQL.selectTelBookCarByCarNumberCount", telBookCar,currentPage,everyPage);
	}

	/**
	 * 根据流水号更新时间处理结果
	 * @param telBookCar
	 * @return
	 */
	@Override
	public int updateTelBookSendResult(TelBookSend telBookSend) {
		return this.baseDao.update("oracle-telbookSQL.updateTelBookSendResult", telBookSend);
	}

	/**
	 * 更新事件报告
	 * @param telBookCar
	 * @return
	 */
	@Override
	public int updateTelBookCarResult(TelBookCar telBookCar) {
		return this.baseDao.update("oracle-telbookSQL.updateTelBookCarResult", telBookCar);
	}
	
}
