/**
 * 
 */
package com.careye.common.service.impl;

import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.domain.WorkPlace;
import com.careye.common.service.OcsWorkPlaceService;

/**
 * @author Administrator
 *
 */
public class OcsWorkPlaceServiceImpl extends GenericService implements OcsWorkPlaceService{

	@Override
	public WorkPlace loadOperationStaForOcs(Map map) {
		// TODO Auto-generated method stub
		return (WorkPlace)this.baseDao.queryForObject("oracle-workplaceSQL.loadOperationStaForOcs", map);
	}

}
