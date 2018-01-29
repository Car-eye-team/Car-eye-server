/**
* Description: car-eye车辆管理平台
* 文件名：AlarmService.java
* 版本信息：1.0
* 日期：2014-5-23
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.service;

import java.util.List;

import com.careye.base.service.GenericService;
import com.careye.common.domain.AlarmType;
import com.careye.common.service.AlarmService;

/**
 * @项目名称：FMS
 * @类名称：AlarmService
 * @类描述：报警处理接口
 * @创建人：zr
 * @创建时间：2014-5-23 上午11:43:59
 * @修改人：zr
 * @修改时间：2014-5-23 上午11:43:59
 * @修改备注：
 * @version 1.0
 */
public interface AlarmService {

	/**
	 * 获取报警类型列表
	 * @return
	 */
	public List<AlarmType> selectAlarmTypeList();
	
	/**
	 * 删除
	 * @return
	 */
	public int deleteInfo(int id);
	
}





