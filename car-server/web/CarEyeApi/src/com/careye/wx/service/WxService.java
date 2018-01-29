/**
* Description: car-eye车辆管理平台
* 文件名：ServiceWinService.java
* 版本信息：1.0
* 日期：2015-1-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.wx.service;

import java.util.List;
import java.util.Map;

import com.careye.base.action.Page;
import com.careye.taxi.domain.DriverInfo;
import com.careye.wx.domain.OrderInfo;



/**
 * @项目名称：OCS
 * @类名称：WxService
 * @类描述：微信订单类
 * @创建人：zhangrong
 * @创建时间：2015-11-13 上午11:39:08
 * @修改人：zhangrong
 * @修改时间：2015-11-13 上午11:39:08
 * @修改备注：
 * @version 1.0
 */
public interface WxService {
	
	/**
	 * 保存订单信息
	 * @param orderInfo
	 * @return
	 */
	public Integer addOrderInfo(OrderInfo orderInfo);
	
	/**
	 * 更新订单信息
	 * @param orderInfo
	 * @return
	 */
	public Integer updateOrderInfo(OrderInfo orderInfo);
	
	
	/**
	 * 更新订单信息
	 * @param orderInfo
	 * @return
	 */
	public Integer updateOrderInfoUpTaix(OrderInfo orderInfo);
	
	/**
	 * 更新订单支付信息
	 * @param orderInfo
	 * @return
	 */
	public Integer updateOrderPaymentInfo(OrderInfo orderInfo);
	
	
	/**
	 * 根据订单ID获取订单详情
	 * @param orderid
	 * @return
	 */
	public OrderInfo getOrderdetail(String orderid);
	
	/**
	 * 根据订单号更新支付状态
	 * @param orderid
	 * @return
	 */
	
	
	/**
	 * 根据订单号更新订单状态
	 * @param orderid
	 * @return
	 */
	public Integer updateOrderStatus(OrderInfo orderInfo);
	
	/**
	 * 根据商户唯一订单号更新交易状态
	 * @param orderid
	 * @return
	 */
	public Integer updateOrderStatusByOuttradeno(OrderInfo orderInfo);
	
	/**
	 * 获取飞嘀打车订单列表
	 * @param map
	 * @return
	 */
	public List<OrderInfo> queryOrderInfoByphone(Map map);
	
	/**
	 *  司机端-获取飞嘀打车订单分页列表
	 * @param currentPage
	 * @param everyPage
	 * @param map
	 * @return
	 */
	public Page<OrderInfo> getPageDriverOrderInfoList(int currentPage, int everyPage,Map<String, Object> paramsMap);
	
	
	
}



