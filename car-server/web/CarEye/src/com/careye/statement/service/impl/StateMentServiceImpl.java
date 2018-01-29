/**
 * Description: car-eye车辆管理平台
 * 文件名：CarServiceImpl.java
 * 版本信息：1.0
 * 日期：2014-5-22
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.statement.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.views.xslt.ArrayAdapter;

import com.careye.base.service.GenericService;
import com.careye.statement.domain.AreaMore;
import com.careye.statement.domain.CarPosition;
import com.careye.statement.domain.DrivingState;
import com.careye.statement.service.StateMentService;


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
public class StateMentServiceImpl extends GenericService implements StateMentService{
	

	/**
	 * 查询车辆里程统计报表
	 * @param TelCallSend
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public List<DrivingState> selectCarKiloList(DrivingState drivingState,List<DrivingState> listtable) {
		
		List<DrivingState> dlist = new ArrayList<DrivingState>(); 

		try {
			for (DrivingState dState : listtable) {
				drivingState.setPositiontable(dState.getPositiontable());
				//获取车辆里程
				DrivingState drivingState2= (DrivingState)this.baseDao.queryForObject("oracle-carkiloSQL.selectCarKiloList", drivingState);
				
				//获取车辆信息
				DrivingState dState2= (DrivingState)this.baseDao.queryForObject("oracle-carkiloSQL.selectCheckCarInfo", dState.getCarid());
				if (dState2!=null) {
					if (drivingState2.getMileage()!=null) {
						dState2.setMileage(drivingState2.getMileage());
					} else {
						dState2.setMileage(0.0);
					}
				}
				dlist.add(dState2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dlist;



	}


	/**
	 * 根据Acc状态统计车辆位置总数
	 * @param carPosition
	 * @return
	 */
	@Override
	public Map<Object, Object> selectCarPosition(int currentPage, int everyPage,CarPosition carPosition) {
		return this.baseDao.findPageList("oracle-accstatusSQL.selectCarPosition",
				"oracle-accstatusSQL.selectCarPositionCount", carPosition, currentPage,
				everyPage);
	}

	
}
