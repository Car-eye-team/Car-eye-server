package com.careye.gtalk.service.impl;

import com.careye.base.service.GenericService;
import com.careye.gtalk.domain.GtalkInfo;
import com.careye.gtalk.service.GtalkService;


public class GtalkServiceImpl extends GenericService implements GtalkService{
	/**
	 * 根据用户名获取是否存在对应记录的用户
	 * @param username
	 * @return
	 */
	@Override
	public Integer getOfuserCount(String username) {
		return  (Integer) this.baseDao.queryForObject("oracle-gtalkSQL.getOfuserCount", username);
	}

	/**
	 * 插入用户记录
	 * @param gtalkInfo
	 * @return
	 */
	@Override
	public int addOfuser(GtalkInfo gtalkInfo) {
		return this.baseDao.save("oracle-gtalkSQL.addOfuser", gtalkInfo);
	}

}
