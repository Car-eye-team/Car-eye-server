/**    
 * Description: car-eye车辆管理平台    
 * 文件名：GenericService.java   
 * 版本信息：    
 * 日期：2013-7-20  
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013     
 * 版权所有    
 *    
 */
package com.careye.base.service;

import com.careye.base.dao.GenericDao;

/**    
 *     
 * 项目名称：car-eye    
 * 类名称：GenericService    
 * 类描述：泛型服务接口实现 
 * 创建人：zr    
 * 创建时间：2013-7-20 下午02:19:57    
 * 修改人：zr    
 * 修改时间：2013-7-20 下午02:19:57    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class GenericService {

	protected GenericDao baseDao;

	public GenericDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(GenericDao baseDao) {
		this.baseDao = baseDao;
	}

	
}
