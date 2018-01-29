package com.careye.api.service;

import java.util.Map;

import com.careye.api.domain.AuthCompanyCode;

public interface AuthService {
	
	/**
	 * authcodeIsExistByAuthCode   鉴权码是否存在
	 */
	public AuthCompanyCode authcodeIsExistByAuthCode(Map map); 
	
	/**
	 * imeiAndIccidIsExist     IEMI号和ICCID号  是否存在
	 */
	public Integer imeiAndIccidIsExist(Map map);
	
	/**
	 * updateAuthCodeStatus   更新鉴权码状态
	 */
	public Integer updateAuthCodeStatus(Map map);
	
}
