/**
 * Description: car-eye车辆管理平台
 * 文件名：KeyServiceImpl.java
 * 版本信息：1.0
 * 日期：2015-8-25
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */ 
package com.careye.sysset.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.sysset.domain.Key;
import com.careye.sysset.service.KeyService;
import com.careye.utils.DateUtil;


/**
 * @项目名称：car-eye
 * @类名称：KeyServiceImpl
 * @类描述：
 * @创建人：Administrator 
 * @创建时间：2015-8-25 下午02:19:50
 * @修改人：Administrator
 * @修改时间：2015-8-25 下午02:19:50
 * @修改备注：
 * @version 1.0
 */
public class KeyServiceImpl extends GenericService implements KeyService {

	/**
	*分页密钥列表 
	* findPageKeyList
	* TODO
	* @param currentPage
	* @param everyPage
	* @param key
	* @return
	* @see com.duosen.gate.set.service.KeyService#findPageKeyList(java.lang.Integer, java.lang.Integer, com.duosen.gate.set.domain.Key)
	*/
	@Override
	public Map findPageKeyList(Integer currentPage, Integer everyPage, Key key) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-KeySQL.findPageKeyList", "oracle-KeySQL.findPageKeyListCount", key, currentPage, everyPage);
	}

	/**
	*版本类型下拉列表 
	* selAppTypeList
	* TODO
	* @return
	* @see com.duosen.gate.set.service.KeyService#selAppTypeList()
	*/
	@Override
	public List<Key> selAppTypeList() {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-KeySQL.selAppTypeList");
	}

	/**
	* 密钥名是否存在
	* keyIsExist
	* TODO
	* @param key
	* @return
	* @see com.duosen.gate.set.service.KeyService#keyIsExist(com.duosen.gate.set.domain.Key)
	*/
	@Override
	public Integer keyIsExist(Key key) {
		// TODO Auto-generated method stub
		return (Integer) this.baseDao.queryForObject("oracle-KeySQL.keyIsExist",key);
	}

	/**
	* 增加密钥信息
	* addKey
	* TODO
	* @param key
	* @return
	* @see com.duosen.gate.set.service.KeyService#addKey(com.duosen.gate.set.domain.Key)
	*/
	@Override
	public int addKey(Key key) {
		// TODO Auto-generated method stub
		String current_time=DateUtil.getSQLDate();
		key.setCreatetime(current_time);
		return this.baseDao.save("oracle-KeySQL.saveKey", key);
	}

	/**
	*修改密钥信息 
	* updateKey
	* TODO
	* @param key
	* @return
	* @see com.duosen.gate.set.service.KeyService#updateKey(com.duosen.gate.set.domain.Key)
	*/
	@Override
	public int updateKey(Key key) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-KeySQL.updateKey", key);
	}

	/**
	*删除密钥信息 
	* deleteKey
	* TODO
	* @param parseInt
	* @return
	* @see com.duosen.gate.set.service.KeyService#deleteKey(int)
	*/
	@Override
	public int deleteKey(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-KeySQL.deleteKey", id);
	}

	/**
	*激活密钥 
	* activeKey
	* TODO
	* @param parseInt
	* @return
	* @see com.duosen.gate.set.service.KeyService#activeKey(int)
	*/
	@Override
	public int activeKey(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-KeySQL.activeKey", id);
	}

	/**
	* 停用密钥
	* inactiveKey
	* TODO
	* @param parseInt
	* @return
	* @see com.duosen.gate.set.service.KeyService#inactiveKey(int)
	*/
	@Override
	public int inactiveKey(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-KeySQL.inactiveKey", id);
	}

}
