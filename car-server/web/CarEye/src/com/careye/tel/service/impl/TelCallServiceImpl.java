/**
* Description: 多森商用车平台
* 文件名：CarServiceImpl.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.tel.service.impl;

import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.tel.domain.TelCall;
import com.careye.tel.domain.TelCallSend;
import com.careye.tel.service.TelCallService;
import com.careye.utils.DateUtil;

/**
 * @项目名称：FMS
 * @类名称：TelCallServiceImpl
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:05:25
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:05:25
 * @修改备注：
 * @version 1.0
 */
public class TelCallServiceImpl extends GenericService implements TelCallService{

	/**
	 * id查询电话回拨消息 
	 * @param telCall
	 * @return
	 */
	@Override
	public TelCall selectTelCallById(int id) {
		// TODO Auto-generated method stub
		return (TelCall)this.baseDao.queryForObject("oracle-telCallQL.selectTelCallById", id);
	}

	
	/**
	 * 添加电话回拨消息 
	 * @param telCall
	 * @return
	 */
	@Override
	public int insertTelCallTelCallSend(TelCallSend telCallSend) {
		// TODO Auto-generated method stub
		telCallSend.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-telCallQL.insertTelCallTelCallSend", telCallSend);
	}
	/**
	 * 电话号码是否已存在
	 * @param telCall
	 * @return
	 */
	@Override
	public int queryTelIsExist(TelCall telCall) {
		// TODO Auto-generated method stub
		return this.baseDao.getCount("oracle-telCallQL.queryTelIsExist", telCall);
	}

	/**
	 * 更新电话回拨消息 
	 * @param telCall
	 * @return
	 */
	@Override
	public int updateTelCall(TelCall telCall) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-telCallQL.updateTelCall", telCall);
	}

	
	/**
	 * 删除电话回拨消息 
	 * @param id
	 * @return
	 */
	@Override
	public int deleteTelCall(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-telCallQL.deleteTelCall", id);
	}

	/**
	 * 添加电话回拨消息 
	 * @param telCall
	 * @return
	 */
	@Override
	public int insertTelCall(TelCall telCall) {
		// TODO Auto-generated method stub
		telCall.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-telCallQL.insertTelCall", telCall);
	}

     /**
	 * 查询电话回拨消息 
	 * @param telCall
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckTelCall(int currentPage, int everyPage,
			TelCall telCall) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-telCallQL.selectCheckTelCall",
				"oracle-telCallQL.selectTelCall", telCall,currentPage,everyPage);
	}

	
	 /**
	 * 查询电话回拨消息 发送记录
	 * @param TelCallSend
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckTelCallTelCallSend(int currentPage, int everyPage,
			TelCallSend telCallSend) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-telCallQL.selectCheckTelCallTelCallSend",
				"oracle-telCallQL.selectTelCallTelCallSend", telCallSend,currentPage,everyPage);
	}

	/**
	 * 根据流水号修改电话回拨消息 发送结果
	 * @param telCallSend
	 * @return
	 */
	@Override
	public int updateTelCallResule(TelCallSend telCallSend) {
		return this.baseDao.update("oracle-telCallQL.updateTelCallResule", telCallSend);
	}
	
}
