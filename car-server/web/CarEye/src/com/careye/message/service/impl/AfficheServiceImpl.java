/**
* Description: 多森商用车平台
* 文件名：AfficheServiceImpl.java
* 版本信息：1.0
* 日期：2014-6-25
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.service.impl;

import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.message.domain.Affiche;
import com.careye.message.domain.FixedShortMessage;
import com.careye.message.service.AfficheService;

/**
 * @项目名称：FMS
 * @类名称：AfficheServiceImpl
 * @类描述：系统公告接口实现
 * @创建人：zr
 * @创建时间：2014-6-25 上午11:36:48
 * @修改人：zr
 * @修改时间：2014-6-25 上午11:36:48
 * @修改备注：
 * @version 1.0
 */
public class AfficheServiceImpl  extends GenericService implements AfficheService{

	/**
	 * 增加系统公告
	 * @param affiche
	 * @return
	 */
	@Override
	public int addAffiche(Affiche affiche) {
		return this.baseDao.save("oracle-afficheSQL.addAffiche", affiche);
	}

	/**
	 * 获取系统公告列表
	 * @param currentPage
	 * @param everyPage
	 * @param affiche
	 * @return
	 */
	@Override
	public Map getAfficheList(int currentPage, int everyPage, Affiche affiche) {
		return this.baseDao.findPageList("oracle-afficheSQL.getAfficheList",
				"oracle-afficheSQL.getAfficheListCount", affiche,currentPage,everyPage);
	}

	/**
	 * 获取短消息列表
	 * @param currentPage
	 * @param everyPage
	 * @param fixedShortMessage
	 * @return
	 */
	@Override
	public Map getFixedShortMsgList(int currentPage, int everyPage,
			FixedShortMessage fixedShortMessage) {
		return this.baseDao.findPageList("oracle-afficheSQL.getFixedShortMsgList",
				"oracle-afficheSQL.getFixedShortMsgListCount", fixedShortMessage,currentPage,everyPage);
	}

}
