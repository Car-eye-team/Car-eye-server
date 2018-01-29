/**
* Description: car-eye车辆管理平台
* 文件名：AlarmServiceImpl.java
* 版本信息：1.0
* 日期：2014-5-23
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.service.impl;

import java.util.List;

import com.careye.base.service.GenericService;
import com.careye.common.domain.AlarmType;
import com.careye.common.service.AlarmService;


/**
 * @项目名称：FMS
 * @类名称：AlarmServiceImpl
 * @类描述：报警处理接口实现
 * @创建人：zr
 * @创建时间：2014-5-23 上午11:44:37
 * @修改人：zr
 * @修改时间：2014-5-23 上午11:44:37
 * @修改备注：
 * @version 1.0
 */
public class AlarmServiceImpl extends GenericService implements AlarmService{
	
	/**
	 * 获取报警类型列表
	 * @return
	 */
	@Override
	public List<AlarmType> selectAlarmTypeList() {
		
		return (List<AlarmType>)this.baseDao.queryForList("oracle-alarmSQL.selectAlarmTypeList");
	}


	/**
	 * 删除
	 * @return
	 */
	public int deleteInfo(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-alarmSQL.deleteInfo", id);
	}
	
}




