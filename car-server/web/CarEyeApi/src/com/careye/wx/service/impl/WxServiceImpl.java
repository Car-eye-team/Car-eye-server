/**
 * Description: car-eye车辆管理平台
 * 文件名：ServiceWinServiceImpl.java
 * 版本信息：1.0
 * 日期：2015-1-20
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.wx.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.action.Page;
import com.careye.base.service.GenericService;
import com.careye.constant.Constant;
import com.careye.taxi.domain.DriverInfo;
import com.careye.wx.domain.OrderInfo;
import com.careye.wx.service.WxService;



/**
 * @项目名称：OCS
 * @类名称：ServiceWinServiceImpl
 * @类描述：微信订单类
 * @创建人：zhangrong
 * @创建时间：2015-11-13 上午11:39:08
 * @修改人：zhangrong
 * @修改时间：2015-1-20 上午11:39:33
 * @修改备注：
 * @version 1.0
 */
public class WxServiceImpl extends GenericService implements WxService {

	/**
	 * 保存订单信息
	 * 
	 * @param orderInfo
	 * @return
	 */
	@Override
	public Integer addOrderInfo(OrderInfo orderInfo) {
		if(orderInfo.getSourcetype() == 1){ //微信公众号
			return this.baseDao.save("oracle-wxSQL.addOrderInfo", orderInfo);
		}else if(orderInfo.getSourcetype() == 2){	//手机app
			return this.baseDao.save("oracle-appOrderSQL.addOrderInfo", orderInfo);
		}else{
			return this.baseDao.save("oracle-wxSQL.addOrderInfo", orderInfo);
		}
	}

	/**
	 * 更新订单信息
	 * 
	 * @param orderInfo
	 * @return
	 */
	@Override
	public Integer updateOrderInfo(OrderInfo orderInfo) {
		if(orderInfo.getSourcetype() == 1){ //微信公众号
			return this.baseDao.update("oracle-wxSQL.updateOrderInfo", orderInfo);
		}else if(orderInfo.getSourcetype() == 2){	//手机app
			return this.baseDao.update("oracle-appOrderSQL.updateOrderInfo", orderInfo);
		}else{
			return this.baseDao.update("oracle-wxSQL.updateOrderInfo", orderInfo);
		}
		
	}


	/**
	 * 更新订单信息
	 * 
	 * @param orderInfo
	 * @return
	 */
	@Override
	public Integer updateOrderInfoUpTaix(OrderInfo orderInfo) {
		if(orderInfo.getSourcetype() == 1){ //微信公众号
			return this.baseDao.update("oracle-wxSQL.updateOrderInfoUpTaix",orderInfo);
		}else if(orderInfo.getSourcetype() == 2){	//手机app
			return this.baseDao.update("oracle-appOrderSQL.updateOrderInfoUpTaix", orderInfo);
		}else{
			return this.baseDao.update("oracle-wxSQL.updateOrderInfoUpTaix",orderInfo);
		}
		
	}

	/**
	 * 更新订单支付信息
	 * 
	 * @param orderInfo
	 * @return
	 */
	@Override
	public Integer updateOrderPaymentInfo(OrderInfo orderInfo) {
		return this.baseDao.update("oracle-wxSQL.updateOrderPaymentInfo",orderInfo);
	}


	/**
	 * 根据订单ID获取订单详情
	 * 
	 * @param orderid
	 * @return
	 */
	@Override
	public OrderInfo getOrderdetail(String orderid) {
		if(orderid.startsWith(Constant.APP_ORDER+"")){
			return (OrderInfo) this.baseDao.queryForObject("oracle-appOrderSQL.getOrderdetail", orderid);
		}else if(orderid.startsWith(Constant.WECHAT_ORDER+"")){
			return (OrderInfo) this.baseDao.queryForObject("oracle-wxSQL.getOrderdetail", orderid);
		}else{
			return (OrderInfo) this.baseDao.queryForObject("oracle-wxSQL.getOrderdetail", orderid);
		}
	}



	/**
	 * 根据订单号更新订单状态
	 * 
	 * @param orderid
	 * @return
	 */
	@Override
	public Integer updateOrderStatus(OrderInfo orderInfo) {
		if(orderInfo.getSourcetype() == 1){ //微信公众号
			return this.baseDao.update("oracle-wxSQL.updateOrderStatus", orderInfo);
		}else if(orderInfo.getSourcetype() == 2){	//手机app
			return this.baseDao.update("oracle-appOrderSQL.updateOrderStatus", orderInfo);
		}else{
			return this.baseDao.update("oracle-wxSQL.updateOrderStatus", orderInfo);
		}
	}

	/**
	 * 根据商户唯一订单号更新交易状态
	 * 
	 * @param orderid
	 * @return
	 */
	public Integer updateOrderStatusByOuttradeno(OrderInfo orderInfo) {
		return this.baseDao.update(
				"oracle-appOrderSQL.updateOrderStatusByOuttradeno", orderInfo);
	}


	/**
	 * 获取飞嘀打车订单列表
	 * 
	 * @param map
	 * @return
	 */
	public List<OrderInfo> queryOrderInfoByphone(Map map) {
		return this.baseDao.queryForList("oracle-appOrderSQL.queryOrderInfoByphone",map);
	}

	/**
	 * 司机端-获取飞嘀打车订单列表
	 * @param map
	 * @return
	 */
	public List<OrderInfo> queryDriverOrderInfoByphone(Map map) {
		return this.baseDao.queryForList("oracle-appOrderSQL.queryDriverOrderInfoByphone",
				map);
	}
	
	/**
	 *  司机端-获取飞嘀打车订单分页列表
	 * @param currentPage
	 * @param everyPage
	 * @param map
	 * @return
	 */
	public Page<OrderInfo> getPageDriverOrderInfoList(int currentPage, int everyPage,Map<String, Object> paramsMap){
		return this.baseDao.findPageObject("oracle-appOrderSQL.getPageDriverOrderInfoList",
				"oracle-appOrderSQL.getPageDriverOrderInfoListCount", paramsMap,
				currentPage, everyPage);
	}
	


}



