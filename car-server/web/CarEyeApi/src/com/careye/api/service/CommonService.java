/**
* Description: car-eye车辆管理平台
* 文件名：CommonService.java
* 版本信息：1.0
* 日期：2015-11-12
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.api.service;

import com.careye.api.domain.PhoneAuthCode;

/**
 * @项目名称：DSTAXIAPI
 * @类名称：CommonService
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-11-12 下午04:56:18
 * @修改人：zhangrong
 * @修改时间：2015-11-12 下午04:56:18
 * @修改备注：
 * @version 1.0
 */
public interface CommonService {

	/**
	 * 保存验证码
	 */
	public Integer saveAppCode(PhoneAuthCode phoneAuthCode);
	
	/**
	 * 根据手机号得到验证码
	 * @param orderid
	 * @return
	 */
	public PhoneAuthCode queryAuthCodeByPhone(String phone);
	
	/**
	 * 根据typeid得到内容
	 */
	public String getContentByType(Integer typeid);
}
