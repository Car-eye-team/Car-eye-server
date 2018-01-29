package com.careye.common.service;

import com.careye.car.domain.CarInfo;
import com.careye.common.domain.Account;
import com.careye.common.domain.DevStatus;
import com.careye.common.domain.UserInfo;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-27 上午11:54:28
 * @修改人：ll
 * @修改时间：2016-5-27 上午11:54:28
 * @修改备注：
 * @version 1.0
 */
public interface CmssService {
	
	/**
	 * 添加account
	 */
	public int saveAccount(Account account);
	
	/**
	 * 删除account
	 */
	public int deleteAccount(String account);
	
	/**
	 * 添加DevStatus
	 */
	public int saveDevstatus(DevStatus devStatus);
	
	/**
	 * 删除DevStatus
	 */
	public int deleteDevStatus(String devidno);
	
	/**
	 * 添加userinfo
	 */
	public int saveUserinfo(UserInfo userInfo);
	
	/**
	 * 删除userinfo
	 */
	public int deleteUserinfo(String accountid);
	
	/**
	 * 终端信息保存
	 */
	public int saveTer(CarInfo carInfo);
	
	/**
	 * 终端信息删除
	 */
	public int deleteTer(String ter);
}
