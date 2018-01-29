/**
 * Description: car-eye车辆管理平台
 * 文件名：DialRuleServiceImpl.java
 * 版本信息：1.0
 * 日期：2015-4-17
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.sysset.service.impl;

import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.sysset.domain.DialRule;
import com.careye.sysset.domain.LastDialRule;
import com.careye.sysset.service.DialRuleService;

/**
 * @项目名称：car-eye
 * @类名称：DialRuleServiceImpl
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-17 上午09:08:57
 * @修改人：Yuqk
 * @修改时间：2015-4-17 上午09:08:57
 * @修改备注：
 * @version 1.0
 */
public class DialRuleServiceImpl extends GenericService implements
		DialRuleService {
	private SysOperateLogService logService;
	/**
	 * 新增记录
	 * 
	 * @param dialRule
	 * @return
	 */
	@Override
	public int addRecord(DialRule dialRule) {
		return this.baseDao.save("oracle-dialRuleSQL.addRecord", dialRule);
	}

	/**
	 * 查询分页列表
	 * 
	 * @param page
	 * @param limit
	 * @param dialRule
	 * @return
	 */
	@Override
	public Map findRecordList(Integer page, Integer limit, DialRule dialRule) {
		return this.baseDao
				.findPageList("oracle-dialRuleSQL.findRecordList",
						"oracle-dialRuleSQL.findRecordListCount", dialRule,
						page, limit);
	}

	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}
	/**
	* addHistoryDialRule
	* TODO
	* @param type
	* @see com.careye.sysset.service.DialRuleService#addHistoryDialRule(java.lang.Integer)
	*/
	@Override
	public int addHistoryDialRule(DialRule dialRule) {
		return this.baseDao.save("oracle-dialRuleSQL.addHistoryDialRule", dialRule);
	}

	/**
	* addLastDialRule
	* TODO
	* @param dialRule
	* @return
	* @see com.careye.sysset.service.DialRuleService#addLastDialRule(com.careye.sysset.domain.DialRule)
	*/
	@Override
	public int addLastDialRule(DialRule dialRule) {
		return this.baseDao.save("oracle-dialRuleSQL.addLastDialRule", dialRule);
	}

	/**
	* delLastDialRule
	* TODO
	* @param type
	* @return
	* @see com.careye.sysset.service.DialRuleService#delLastDialRule(java.lang.Integer)
	*/
	@Override
	public int delLastDialRule(Integer type) {
		return this.baseDao.delete("oracle-dialRuleSQL.delLastDialRule", type);
	}

	/**
	* findLastDialRule
	* TODO
	* @param type
	* @return
	* @see com.careye.sysset.service.DialRuleService#findLastDialRule(java.lang.Integer)
	*/
	@Override
	public LastDialRule findLastDialRule(LastDialRule lastDialRule) {
		return (LastDialRule) this.baseDao.queryForObject("oracle-dialRuleSQL.findLastDialRule", lastDialRule);
	}
}
