/**
 * 
 */
package com.careye.transaction.service.impl;

import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.transaction.domain.Intercom;
import com.careye.transaction.service.IntercomService;

/**
 * @author Administrator
 *
 */
public class IntercomServiceImpl extends GenericService implements IntercomService{

	/**
	 * 组列表selGroupList
	 */
	public Map selGroupList(Integer currentPage, Integer everyPage,
			Intercom intercom) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-intercomSQL.selGroupList",
				"oracle-intercomSQL.selGroupListCount", intercom, currentPage, everyPage);
	}
	/**
	 * 删除组deleteGroup
	 */
	public int queryGroup(int id) {
		// TODO Auto-generated method stub
		return (Integer)this.baseDao.queryForObject("oracle-intercomSQL.queryGroup",id);
	}
	public int deleteGroup(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-intercomSQL.deleteGroup", id);
	}
	

	/**
	 * 组成员列表selMemberList
	 */
	public Map selMemberList(Integer currentPage, Integer everyPage,
			Intercom intercom) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-intercomSQL.selMemberList",
				"oracle-intercomSQL.selMemberListCount", intercom, currentPage, everyPage);
	}
	/**
	 * 删除组成员deleteMember
	 */
	public int deleteMember(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-intercomSQL.deleteMember", id);
	}

	

}
