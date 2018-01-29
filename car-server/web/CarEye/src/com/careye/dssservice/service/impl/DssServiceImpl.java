/**
* Description: car-eye车辆管理平台
* 文件名：DssServiceImpl.java
* 版本信息：1.0
* 日期：2013-8-7
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.dssservice.service.impl;


import java.util.List;

import com.careye.base.service.GenericService;
import com.careye.dssservice.domain.TerminalAuth;
import com.careye.dssservice.service.DssService;

/**
* @项目名称：FMS
* @类名称：DssServiceImpl
* @类描述：
* @创建人：zr
* @创建时间：2014-5-24 下午03:14:35
* @修改人：zr
* @修改时间：2014-5-24 下午03:14:35
* @修改备注：
* @version 1.0
 */
public class DssServiceImpl extends GenericService implements DssService{

	/**
	 * 插入终端鉴权
	 * @param terminalAuth
	 */
	@Override
	public void insertTerminalAuth(TerminalAuth terminalAuth) {
		baseDao.save("mysql-dssServiceSQL.insertTerminalAuth", terminalAuth);
	}
	/**
	 * 批量插入终端鉴权
	 * @param terminalAuth
	 */
	public void insertTerminalAuthBitch(List<TerminalAuth> addlist){};
	/**
	 * 删除终端鉴权
	 * @param carTel
	 * @return
	 */
	@Override
	public int deleteTerminalAuth(TerminalAuth tAuth) {
		return baseDao.delete("mysql-dssServiceSQL.deleteTerminalAuth", tAuth);
	}

	/**
	 * 根据设备号查询是否已经鉴权
	 * @param carTel
	 * @return
	 */
	@Override
	public int getTerminalAuth(TerminalAuth terminalAuth) {
		return (Integer) this.baseDao.queryForObject("mysql-dssServiceSQL.getTerminalAuth", terminalAuth);
	}
	
	/**
	 * 根据设备号更新鉴权用户类型
	 * @param carTel
	 * @return
	 */
	public int updateTerminalAuth(TerminalAuth terminalAuth){
		return this.baseDao.update("mysql-dssServiceSQL.updateTerminalAuth", terminalAuth);
	}
	
	/**
	 * 得到鉴权表列表
	 */
	public List<TerminalAuth> getTerminalAuthList(){
		return this.baseDao.queryForList("mysql-dssServiceSQL.getTerminalAuthList");
	}
	

}
