/**
* Description: 多森商用车平台
* 文件名：CarService.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.tel.service;

import java.util.Map;

import com.careye.tel.domain.TelCall;
import com.careye.tel.domain.TelCallSend;


/**
 * @项目名称：FMS
 * @类名称：TelCallService
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:00:03
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public interface TelCallService {
	
	/**
	 * id查询电话回拨消息 
	 * @param telCall
	 * @return
	 */
	public TelCall selectTelCallById(int id);
	
	/**
	 * 电话号码是否已存在
	 * @param telCall
	 * @return
	 */
	public int queryTelIsExist(TelCall telCall);
	
	
	/**
	 * 根据流水号修改电话回拨消息 发送结果
	 * @param telCallSend
	 * @return
	 */
	public int updateTelCallResule(TelCallSend telCallSend);
	
	
	/**
	 * 添加电话回拨消息 发送记录
	 * @param telCall
	 * @return
	 */
	public int insertTelCallTelCallSend(TelCallSend telCallSend);
	

	/**
	 * 删除电话回拨消息 
	 * @param id
	 * @return
	 */
	public int deleteTelCall(int id);
	
	/**
	 * 添加电话回拨消息 
	 * @param telCall
	 * @return
	 */
	public int insertTelCall(TelCall telCall);
	
	/**
	 * 更新电话回拨消息
	 * @param telCall
	 * @return
	 */
	public int updateTelCall(TelCall telCall);
	
	/**
	 * 条件查询电话回拨消息 
	 * @param telCall
	 * @return
	 */
	public Map selectCheckTelCall(final int currentPage, final int everyPage,TelCall telCall);

	/**
	 * 条件查询电话回拨消息 发送记录
	 * @param TelCallSend
	 * @return
	 */
	public Map selectCheckTelCallTelCallSend(final int currentPage, final int everyPage,TelCallSend telCallSend);

	
	
}
