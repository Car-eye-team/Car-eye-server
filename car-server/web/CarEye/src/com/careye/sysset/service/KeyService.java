/**
 * Description: car-eye车辆管理平台
 * 文件名：KeyService.java
 * 版本信息：1.0
 * 日期：2015-8-25
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */ 
package com.careye.sysset.service;

import java.util.List;
import java.util.Map;

import com.careye.sysset.domain.Key;


/**
 * @项目名称：car-eye
 * @类名称：KeyService
 * @类描述：
 * @创建人：Administrator 
 * @创建时间：2015-8-25 下午02:18:33
 * @修改人：Administrator
 * @修改时间：2015-8-25 下午02:18:33
 * @修改备注：
 * @version 1.0
 */
public interface KeyService {

	/**
	 * 分页密钥列表
	 * @param page
	 * @param limit
	 * @param key
	 * @return
	 */
	public Map findPageKeyList(Integer currentPage, Integer everyPage, Key key);

	/**
	 * 版本类型下拉列表
	 * @return
	 */
	public List<Key> selAppTypeList();

	/**
	 * 密钥名是否存在
	 * @param key
	 * @return
	 */
	public Integer keyIsExist(Key key);

	/**
	 * 增加密钥信息
	 * @param key
	 * @return
	 */
	public int addKey(Key key);

	/**
	 * 修改密钥信息
	 * @param key
	 * @return
	 */
	public int updateKey(Key key);

	/**
	 * 删除密钥信息
	 * @param parseInt
	 * @return
	 */
	public int deleteKey(int id);

	/**
	 * 激活密钥
	 * @param parseInt
	 * @return
	 */
	public int activeKey(int id);

	/**
	 * 停用密钥
	 * @param parseInt
	 * @return
	 */
	public int inactiveKey(int id);

}
