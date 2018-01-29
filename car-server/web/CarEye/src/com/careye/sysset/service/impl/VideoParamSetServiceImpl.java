package com.careye.sysset.service.impl;

import com.careye.base.service.GenericService;
import com.careye.sysset.domain.VideoParamSet;
import com.careye.sysset.service.VideoParamSetService;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-10-10 下午06:06:08
 * @修改人：ll
 * @修改时间：2016-10-10 下午06:06:08
 * @修改备注：
 * @version 1.0
 */
public class VideoParamSetServiceImpl extends GenericService implements
		VideoParamSetService {
	
	/**
	 * queryVideoParamSet  获取参数
	 */
	public VideoParamSet queryVideoParamSet(){
		return (VideoParamSet) this.baseDao.queryForObject("oracle-videoParamSetSQL.queryVideoParamSet");
	}
	
	/**
	 * updateVideoParamSet   修改参数
	 */
	public Integer updateVideoParamSet(VideoParamSet videoParamSet){
		return this.baseDao.update("oracle-videoParamSetSQL.updateVideoParamSet", videoParamSet);
	}

}
