/**
* Description: car-eye车辆管理平台
* 文件名：OrgazicationDeptService.java
* 版本信息：1.0
* 日期：2014-5-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.system.service;

import java.util.List;
import java.util.Map;

import com.careye.system.domain.Bloc;


/**
 * @项目名称：FMS
 * @类名称：OrgazicationDeptService
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-5-20 下午02:30:41
 * @修改人：zhangrong
 * @修改时间：2014-5-20 下午02:30:41
 * @修改备注：
 * @version 1.0
 */
public interface OrgazicationDeptService {
	
	/**
	 * 根据当前级别ID获取上一级别ID
	 * @param id
	 * @return
	 */
	public List<Bloc> getNextHigherLevel(int id);
	
	/**
	 * 根据当前级别ID获取下一级别ID
	 * @param id
	 * @return
	 */
	public List<Integer> getNextDowmLevel(int id);

	/**
	 * 根据部门id得到部门详细信息
	 */
	public Bloc quertDeptById(int id);
	
	/**
	 * 组织机构列表不分页
	 * @return
	 */
	public List<Bloc> selectOrgazicationDeptList(Bloc orgazicationDept);
	/**
	 * 分页组织机构列表
	 * @return
	 */
	public Map findPageOrgazicationDeptList(int currentPage, int everyPage,Bloc orgazicationDept);
	/**
	 * 添加组织机构
	 * @param Bloc
	 */
	public int addOrgazicationDept(Bloc orgazicationDept);
	/**
	 * 更新组织机构
	 * @param Bloc
	 */
	public int updateOrgazicationDept(Bloc orgazicationDept);
	/**
	 * 组织机构名是否存在
	 * @param orgazicationDeptInfo
	 * @return
	 */
	public Integer deptNameIsExist(Bloc orgazicationDept);
	/**
	 * 组织机构名下是否有用户组
	 * @param orgazicationDeptInfo
	 * @return
	 */
	public Integer containUserGroupNum(int id);
	
	/**
	 * 组织机构名下是否有车辆
	 * @param orgazicationDeptInfo
	 * @return
	 */
	public Integer containCarNum(int id);
	
	/**
	 * 删除组织机构信息
	 * @param id
	 * @return
	 */
	public int deleteOrgazicationDept(int id);
	
	/**
	 * 检测组织机构下面是否还有组织
	 * @param id
	 * @return
	 */
	public int queryDeptCount(int id);
	
	/**
	 * 得到组织机构下面车辆列表，不分页
	 */
	public List<Bloc> getCarCountList(Bloc orgazicationDept);
	
	/**
	 * 导出组织机构下面车辆列表
	 */
	public List<Bloc> exportCarCountList(Map<String, String> paramsMap);
	
	/**
	 * 得到组织机构下面车辆列表，分页
	 */
	public Map findPageCarCountList (int currentPage, int everyPage,Bloc orgazicationDept);
	
	/**
	 * 更新机构表中的车辆总数、离线数、长离数
	 * @param map
	 * @return
	 */
	public int updateDeptCarNum(Map map);
	
	/**
	 * 根据机构名称获取对应企业ID
	 * @param blocname
	 * @return
	 */
	public Integer getDeptIdByDeptName(String blocname);
	
}






