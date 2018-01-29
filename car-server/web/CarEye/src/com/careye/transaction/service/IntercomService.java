/**
 * 
 */
package com.careye.transaction.service;

import java.util.Map;

import com.careye.transaction.domain.Intercom;


/**
 * @author Administrator
 *
 */
public interface IntercomService {
	/**
	 * 组列表selGroupList
	 */
	public Map selGroupList(Integer currentPage, Integer everyPage, Intercom intercom);
	
	/**
	 * 删除组deleteGroup
	 */
	public int queryGroup(int id);
	public int deleteGroup(int id);
	/**
	 * 组成员列表selMemberList
	 */
	public Map selMemberList(Integer currentPage, Integer everyPage, Intercom intercom);
	/**
	 * 删除组成员deleteMember
	 */
	public int deleteMember(int id);
}
