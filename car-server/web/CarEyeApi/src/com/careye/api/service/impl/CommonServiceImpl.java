/**
* Description: car-eye车辆管理平台
* 文件名：CommonServiceImpl.java
* 版本信息：1.0
* 日期：2015-11-12
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.api.service.impl;

import com.careye.api.domain.PhoneAuthCode;
import com.careye.api.service.CommonService;
import com.careye.base.service.GenericService;

/**
 * @项目名称：DSTAXIAPI
 * @类名称：CommonServiceImpl
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-11-12 下午05:00:30
 * @修改人：zhangrong
 * @修改时间：2015-11-12 下午05:00:30
 * @修改备注：
 * @version 1.0
 */
public class CommonServiceImpl extends GenericService implements CommonService {

	/**
	 * 保存验证码
	 */
	public Integer saveAppCode(PhoneAuthCode phoneAuthCode){
		Integer count = (Integer) this.baseDao.queryForObject("oracle-commonSQL.getPhoneCount", phoneAuthCode.getPhone());
		
		if (count == 0) {
			return this.baseDao.save("oracle-commonSQL.addPhoneAuthCode",phoneAuthCode);
		} else {
			return this.baseDao.update("oracle-commonSQL.updatePhoneAuthCode",phoneAuthCode);
		}
	}
	
	/**
	 * 根据手机号得到验证码
	 * 
	 * @param orderid
	 * @return
	 */
	public PhoneAuthCode queryAuthCodeByPhone(String phone) {
		return (PhoneAuthCode) this.baseDao.queryForObject(
				"oracle-commonSQL.queryAuthCodeByPhone", phone);
	}

	/**
	 * 根据typeid得到内容
	 */
	public String getContentByType(Integer typeid){
		return (String)this.baseDao.queryForObject("oracle-commonSQL.getContentByType", typeid);
	}
}
