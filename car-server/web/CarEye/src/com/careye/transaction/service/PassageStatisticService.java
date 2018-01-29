package com.careye.transaction.service;

import java.util.Map;

import com.careye.transaction.domain.PassageStatistic;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-9-21 上午11:20:35
 * @修改人：ll
 * @修改时间：2015-9-21 上午11:20:35
 * @修改备注：
 * @version 1.0
 */
public interface PassageStatisticService {
	
	/**
	 * 得到乘客人数统计信息
	 * 
	 * @param passageStatistic
	 * @return
	 */
	public Map<Object, Object> getPassageStatisticList(final int currentPage,
			final int everyPage, PassageStatistic passageStatistic);
}
