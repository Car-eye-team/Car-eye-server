/**
* Description: car-eye车辆管理平台
* 文件名：ServiceConfig.java
* 版本信息：1.0
* 日期：2013-12-3
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.constant;

import com.careye.component.springhelper.BeanLocator;
import com.careye.wx.service.WxService;


/**
* @项目名称：DSTAXI
* @类名称：ServiceConfig
* @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-11-13 上午11:39:08
 * @修改人：zhangrong
* @修改时间：2015-11-13 上午11:39:08
* @修改备注：
* @version 1.0
 */
public class ServiceConfig {
	
	public static WxService wxService;
	
	static{
		try {
			if(wxService == null){
				wxService = (WxService)BeanLocator.getInstance().getBean("wxService");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
