/**
* Description: 多森商用车平台
* 文件名：AfficheService.java
* 版本信息：1.0
* 日期：2014-6-25
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.service;

import java.util.Map;

import com.careye.message.domain.Affiche;
import com.careye.message.domain.FixedShortMessage;

/**
 * @项目名称：FMS
 * @类名称：AfficheService
 * @类描述：系统公告
 * @创建人：zr
 * @创建时间：2014-6-25 上午11:36:18
 * @修改人：zr
 * @修改时间：2014-6-25 上午11:36:18
 * @修改备注：
 * @version 1.0
 */
public interface AfficheService {
	
	/**
	 * 增加系统公告
	 * @param affiche
	 * @return
	 */
	public int addAffiche(Affiche affiche);
	
	/**
	 * 获取系统公告列表
	 * @param currentPage
	 * @param everyPage
	 * @param affiche
	 * @return
	 */
	public Map getAfficheList(final int currentPage, final int everyPage,Affiche affiche);
	
	/**
	 * 获取短消息列表
	 * @param currentPage
	 * @param everyPage
	 * @param fixedShortMessage
	 * @return
	 */
	public Map getFixedShortMsgList(final int currentPage, final int everyPage,FixedShortMessage fixedShortMessage);
	
}
