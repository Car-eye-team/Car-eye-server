/**
* Description: car-eye车辆管理平台
* 文件名：SystemSetService.java
* 版本信息：1.0
* 日期：2013-11-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.system.service;

import java.util.List;
import java.util.Map;

import com.careye.system.domain.SystemControl;

/**
 * @项目名称：car-eye
 * @类名称：SystemControlService
 * @类描述：
 * @创建人：zxt
 * @创建时间：2013-11-20 下午04:43:36
 * @修改人：zxt
 * @修改时间：2013-11-20 下午04:43:36
 * @修改备注：
 * @version 1.0
 */
public interface SystemControlService {
	
	/**
	 * 获取系统控制项总数
	 * 
	 * @return
	 */
	public Integer findSystemControlCount(Map<String, String> paramsMap);
	
	/**
	 * 分页获取系统控制项列表
	 * @return
	 */
	public Map findPageSystemControlList(int currentPage, int everyPage,SystemControl systemControl);

	/**
	 * 获取系统控制项列表
	 * @return
	 */
	public  List<SystemControl> findSystemControlList();
	
	/**
	 * 验证添加时系统控制项名称是否存在
	 * 
	 * @param scname
	 * @return
	 */
	public int validateSystemControl(String scname);
	
	/**
	 * 验证添加时系统控制项别名是否存在
	 * 
	 * @param scname
	 * @return
	 */
	public int validateSystemControlAlias(String scalias);
	
	/**
	 * 验证更新时系统控制项是否存在
	 * 
	 * @param systemControl
	 * @return
	 */
	public int validateupSystemControl(SystemControl systemControl);
	
	/**
	 * 添加系统控制项
	 * 
	 * @param systemControl
	 * @return
	 */
	public int addSystemControl(SystemControl systemControl);
	/**
	 * 删除系统控制项
	 * 
	 * @param id
	 * @return
	 */
	public int deleteSystemControl(int id);
	
	/**
	 * 更新系统控制项
	 * 
	 * @param systemControl
	 * @return
	 */
	public int updateSystemControl(SystemControl systemControl);
	/**
	 * 更新系统控制项
	 * 
	 * @param systemControl
	 * @return
	 */
	public SystemControl findSystemControl(SystemControl systemControl);
	
	/**
	 * 系统设置导出列表
	 */
	public List<SystemControl> exportSystemControl (Map<String, String> paramsMap);
	
	/**
	 * 得到系统控制项相关值
	 * @param scalias
	 * @return
	 */
	public SystemControl getScnumByScalias(String scalias);
	
	/**
	 * 通过控制名找出对应控制值
	 * @param scalias
	 * @return
	 */
	public SystemControl getScnumByScnum(String scnum);
	
}







