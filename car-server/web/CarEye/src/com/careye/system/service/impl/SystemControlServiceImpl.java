/**
* Description: car-eye车辆管理平台
* 文件名：SystemSetServiceImpl.java
* 版本信息：1.0
* 日期：2013-11-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.system.service.impl;

import java.util.List;
import java.util.Map;


import com.careye.base.service.GenericService;
import com.careye.system.domain.SystemControl;
import com.careye.system.service.SystemControlService;
import com.careye.utils.DateUtil;

/**
 * @项目名称：car-eye
 * @类名称：SystemControlServiceImpl
 * @类描述：
 * @创建人：zxt
 * @创建时间：2013-11-20 下午04:44:04
 * @修改人：zxt
 * @修改时间：2013-11-20 下午04:44:04
 * @修改备注：
 * @version 1.0
 */
public class SystemControlServiceImpl extends GenericService implements SystemControlService{

	
	/**
	 * 获取系统控制项总数
	 * 
	 * @return
	 */
	@Override
	public Integer findSystemControlCount(Map<String, String> paramsMap) {
		return (Integer) this.baseDao.queryForObject("oracle-systemControlSQL.findSystemControlCount", paramsMap);
	}


	/**
	 * 分页获取系统控制项列表
	 * @return
	 */
	public Map findPageSystemControlList(int currentPage, int everyPage,SystemControl systemControl) {
		return this.baseDao.findPageList("oracle-systemControlSQL.findPageSystemControlList", "oracle-systemControlSQL.findPageSystemControlListCount", systemControl, currentPage, everyPage);
	}
	
	/**
	 * 验证添加时系统控制项名称是否存在
	 * 
	 * @param terminalid
	 * @return
	 */
	@Override
	public int validateSystemControl(String scname) {
		return (Integer) this.baseDao.queryForObject("oracle-systemControlSQL.validateSystemControl", scname);
	}

	/**
	 * 验证添加时系统控制项别名是否存在
	 * 
	 * @param scname
	 * @return
	 */
	public int validateSystemControlAlias(String scalias){
		return (Integer) this.baseDao.queryForObject("oracle-systemControlSQL.validateSystemControlAlias", scalias);
	}
	
	/**
	 * 验证更新时系统控制项是否存在
	 * 
	 * @param systemControl
	 * @return
	 */
	public int validateupSystemControl(SystemControl systemControl) {
		return (Integer) this.baseDao.queryForObject("oracle-systemControlSQL.validateupSystemControl", systemControl);
	}
	
	/**
	 * 添加系统控制项
	 * 
	 * @param systemControl
	 * @return
	 */
	@Override
	public int addSystemControl(SystemControl systemControl) {
		systemControl.setOperationtime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-systemControlSQL.addSystemControl", systemControl);
	}

	/**
	 * 删除系统控制项
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public int deleteSystemControl(int id) {
		return this.baseDao.delete("oracle-systemControlSQL.deleteSystemControl", id);
	}

	
	/**
	 * 更新系统控制项
	 * 
	 * @param systemControl
	 * @return
	 */
	@Override
	public int updateSystemControl(SystemControl systemControl) {
		return this.baseDao.update("oracle-systemControlSQL.updateSystemControl",systemControl);
	}

	/**
	 * 更新系统控制项
	 * 
	 * @param systemControl
	 * @return
	 */
	public SystemControl findSystemControl(SystemControl systemControl) {
		return (SystemControl) this.baseDao.queryForObject(
				"oracle-systemControlSQL.findSystemControl", systemControl);
	}
	
	/**
	 * 系统设置导出列表
	 */
	public List<SystemControl> exportSystemControl (Map<String, String> paramsMap){
		return this.baseDao.queryForList("oracle-systemControlSQL.exportSystemControl",paramsMap);
	}


	/**
	 * 获取系统控制项列表
	 * @return
	 */
	public  List<SystemControl> findSystemControlList(){
		return this.baseDao.queryForList("oracle-systemControlSQL.selectSystemControl");
	}
	
	
	/**
	 * 得到系统控制项相关值
	 * @param scalias
	 * @return
	 */
	@Override
	public SystemControl getScnumByScalias(String scalias) {
		return (SystemControl)this.baseDao.queryForObject("oracle-systemControlSQL.getScnumByScalias", scalias);
	}

	/**
	 * 通过控制名找出对应控制值
	 * @param scalias
	 * @return
	 */
	public SystemControl getScnumByScnum(String scnum) {
		return (SystemControl)this.baseDao.queryForObject("oracle-systemControlSQL.getScnumByScnum", scnum);
	}
	
	
}








