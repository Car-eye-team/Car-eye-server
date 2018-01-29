package com.careye.transaction.service.impl;

import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.transaction.domain.PassageStatistic;
import com.careye.transaction.service.PassageStatisticService;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-9-21 上午11:24:44
 * @修改人：ll
 * @修改时间：2015-9-21 上午11:24:44
 * @修改备注：
 * @version 1.0
 */
public class PassageStatisticServiceImpl extends GenericService implements
		PassageStatisticService {
	
	/**
	 * 得到乘客人数统计信息
	 * 
	 * @param passageStatistic
	 * @return
	 */
	@Override
	public Map<Object, Object> getPassageStatisticList(int currentPage,
			int everyPage, PassageStatistic passageStatistic) {
		return this.baseDao.findPageList(
				"oracle-PassageStatisticSQL.getPassageStatisticList",
				"oracle-PassageStatisticSQL.getPassageStatisticListCount", passageStatistic,
				currentPage, everyPage);
	}

}
