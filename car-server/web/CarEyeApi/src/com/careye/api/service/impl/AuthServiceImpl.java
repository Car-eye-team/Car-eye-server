/**
 * 
 */
package com.careye.api.service.impl;

import java.util.Map;

import com.careye.api.domain.AuthCompanyCode;
import com.careye.api.service.AuthService;
import com.careye.base.service.GenericService;

/**
 * @author Administrator
 *
 */
public class AuthServiceImpl extends GenericService implements AuthService{

	/**
	 * authcodeIsExistByAuthCode   鉴权码是否存在
	 */
	public AuthCompanyCode authcodeIsExistByAuthCode(Map map) {
		return  (AuthCompanyCode) this.baseDao.queryForObject("oracle-apiSQL.authcodeIsExistByAuthCode", map);
	}
	
	/**
	 * imeiAndIccidIsExist     IEMI号和ICCID号  是否存在
	 */
	public Integer imeiAndIccidIsExist(Map map) {
		return (Integer) this.baseDao.queryForObject("oracle-apiSQL.imeiAndIccidIsExist", map);
	}
	
	/**
	 * updateAuthCodeStatus   更新鉴权码状态
	 */
	public Integer updateAuthCodeStatus(Map map) {
		return this.baseDao.update("oracle-apiSQL.updateAuthCodeStatus", map);
	}

	

}
