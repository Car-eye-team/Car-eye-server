/**    
 * Description: 多森软件物联网车联网平台    
 * 文件名：GtalkService.java   
 * 版本信息：    
 * 日期：2016-1-7  
 * Copyright car-eye 车辆管理平台 Copyright (c) 2016     
 * 版权所有    
 *    
 */
package com.careye.gtalk.service;

import com.careye.gtalk.domain.GtalkInfo;


/**    
 *     
 * 项目名称：CVPAPI    
 * 类名称：GtalkService    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2016-1-7 下午03:24:24    
 * 修改人：Administrator    
 * 修改时间：2016-1-7 下午03:24:24    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public interface GtalkService {
	
	/**
	 * 根据用户名获取是否存在对应记录的用户
	 * @param username
	 * @return
	 */
	public Integer getOfuserCount(String username);
	
	/**
	 * 插入用户记录
	 * @param gtalkInfo
	 * @return
	 */
	public int addOfuser(GtalkInfo gtalkInfo);

}
