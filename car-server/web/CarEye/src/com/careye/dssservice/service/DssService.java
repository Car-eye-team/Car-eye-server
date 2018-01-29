/**
* Description: car-eye车辆管理平台
* 文件名：DssService.java
* 版本信息：1.0
* 日期：2013-8-7
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.dssservice.service;

import java.util.List;

import com.careye.car.domain.CarInfo;
import com.careye.dssservice.domain.TerminalAuth;

/**
* @项目名称：FMS
* @类名称：DssService
* @类描述：
* @创建人：zr
* @创建时间：2014-5-24 下午03:14:19
* @修改人：zr
* @修改时间：2014-5-24 下午03:14:19
* @修改备注：
* @version 1.0
 */
public interface DssService {
	
	/**
	 * 插入终端鉴权
	 * @param terminalAuth
	 */
	public void insertTerminalAuth(TerminalAuth terminalAuth);
	/**
	 * 批量插入终端鉴权
	 * @param terminalAuth
	 */
	public void insertTerminalAuthBitch(List<TerminalAuth> terminalAuth);
	
	/**
	 * 删除终端鉴权
	 * @param carTel
	 * @return
	 */
	public int deleteTerminalAuth(TerminalAuth tAuth);
	
	/**
	 * 根据设备号查询是否已经鉴权
	 * @param carTel
	 * @return
	 */
	public int getTerminalAuth(TerminalAuth terminalAuth);
	
	/**
	 * 根据设备号更新鉴权用户类型
	 * @param carTel
	 * @return
	 */
	public int updateTerminalAuth(TerminalAuth terminalAuth);
	
	/**
	 * 得到鉴权表列表
	 */
	public List<TerminalAuth> getTerminalAuthList();
	
}
