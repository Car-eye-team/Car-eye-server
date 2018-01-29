package com.careye.sysset.service;

import com.careye.sysset.domain.VideoParamSet;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-10-10 下午06:05:35
 * @修改人：ll
 * @修改时间：2016-10-10 下午06:05:35
 * @修改备注：
 * @version 1.0
 */
public interface VideoParamSetService {
	
	/**
	 * queryVideoParamSet  获取参数
	 */
	public VideoParamSet queryVideoParamSet();
	
	/**
	 * updateVideoParamSet   修改参数
	 */
	public Integer updateVideoParamSet(VideoParamSet videoParamSet);
}
