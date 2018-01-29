/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.job;

import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.careye.dsparse.constant.Constant;
import com.careye.dsparse.redis.RedisManager;
import com.careye.redis.domain.AuthInfo;


/**    
 *     
 * 项目名称：dsbusiness    
 * 类名称：SynAuthInfoJob    
 * 类描述： 同步鉴权信息定时任务   
 * 创建人：Administrator    
 * 创建时间：2015-11-3 上午09:10:54    
 * 修改人：Administrator    
 * 修改时间：2015-11-3 上午09:10:54    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class SynAuthInfoJob  implements Job{

	private final static Logger logger = Logger.getLogger(SynAuthInfoJob.class);

	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			Map<String, AuthInfo> authinfomap = (Map<String, AuthInfo>) RedisManager.getInstance().getObject("authinfo");
			if(authinfomap != null){
				Constant.AUTHINFO_MAP = authinfomap;
			}
			logger.info("系统定时同步鉴权数据......");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
