/**
 * Description: car-eye车辆管理平台
 * 文件名：DialRuleService.java
 * 版本信息：1.0
 * 日期：2015-4-17
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.sysset.service;

import java.util.Map;

import com.careye.sysset.domain.DialRule;
import com.careye.sysset.domain.LastDialRule;

/**
 * @项目名称：car-eye
 * @类名称：DialRuleService
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-17 上午09:08:09
 * @修改人：Yuqk
 * @修改时间：2015-4-17 上午09:08:09
 * @修改备注：
 * @version 1.0
 */
public interface DialRuleService {
	/**
	 * 查询分页列表
	 * 
	 * @param page
	 * @param limit
	 * @param dialRule
	 * @return
	 */
	public Map findRecordList(Integer page, Integer limit, DialRule dialRule);

	/**
	 * 新增记录
	 * 
	 * @param dialRule
	 * @return
	 */
	public int addRecord(DialRule dialRule);

	/**
	 * 查询最新设置记录
	 * @param dialRule
	 * @return
	 */
	public LastDialRule findLastDialRule(LastDialRule lastDialRule);

	/**
	 * 将最新表中的某个类型记录插入到历史表，之后再由delLastDialRule和addLastDialRule方法更新最新记录表
	 * @param dialRule
	 */
	public int addHistoryDialRule(DialRule dialRule);

	/**
	 * 删除最新表中该类型数据
	 * @param type
	 */
	public int delLastDialRule(Integer type);

	/**
	 * 增加最新表数据记录
	 * @param dialRule
	 * @return
	 */
	public int addLastDialRule(DialRule dialRule);
}
