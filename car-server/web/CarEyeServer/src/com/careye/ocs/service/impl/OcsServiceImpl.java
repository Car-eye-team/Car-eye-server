/**
 * Description: car-eye车辆管理平台
 * 文件名：ServiceWinServiceImpl.java
 * 版本信息：1.0
 * 日期：2015-1-20
 * Copyright car-eye车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.ocs.service.impl;


import com.careye.base.service.GenericService;
import com.careye.ocs.domain.OrderInfo;
import com.careye.ocs.service.OcsService;




/**
 * @项目名称：OCS
 * @类名称：ServiceWinServiceImpl
 * @类描述：微信订单类
 * @创建人：wuyongde
 * @创建时间：2015-11-13 上午11:39:08
 * @修改人：wuyongde
 * @修改时间：2015-1-20 上午11:39:33
 * @修改备注：
 * @version 1.0
 */
public class OcsServiceImpl extends GenericService implements OcsService {
	/**
	 * 根据订单号更新订单状态
	 * 
	 * @param orderid
	 * @return
	 */
	public Integer updateOrderStatus(OrderInfo orderInfo) {
		if(orderInfo.getOrderid().startsWith("5")){ //微信公众号
			return this.baseDao.update("oracle-ocsSQL.updateOrderStatus", orderInfo);
		}else if(orderInfo.getOrderid().startsWith("3")){	//手机app
			return this.baseDao.update("oracle-ocsSQL.updateAppOrderStatus", orderInfo);
		}else{
			return this.baseDao.update("oracle-ocsSQL.updateOrderStatus", orderInfo);
		}
	}
}



