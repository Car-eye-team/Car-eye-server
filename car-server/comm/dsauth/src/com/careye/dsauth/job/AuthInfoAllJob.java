/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */



package com.careye.dsauth.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.careye.dsauth.db.AuthInfoDao;
import com.careye.dsauth.redis.RedisManager;
import com.careye.redis.domain.AuthInfo;

/**    
 *     
 * 项目名称：dsauth    
 * 类名称：AuthInfoJob    
 * 类描述：获取鉴权信息定时任务    
 * 创建人：zr    
 * 创建时间：2015-5-11 下午02:15:01    
 * 修改人：zr    
 * 修改时间：2015-5-11 下午02:15:01    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class AuthInfoAllJob implements Job{

	private final static Logger logger = Logger.getLogger(AuthInfoAllJob.class);

	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		logger.info("获取全部鉴权信息定时任务启动......");
		try {
			List<AuthInfo> list = AuthInfoDao.getAuthInfoList(0);
			if(list != null){
				if(list.size() > 0){
					//删除缓存数据重新加载
					RedisManager.getInstance().delete("authinfo");

					Map<String, AuthInfo> map = (Map<String, AuthInfo>) RedisManager.getInstance().getObject("authinfo");
					if(map == null){
						map = new HashMap<String, AuthInfo>();
					}
					logger.info("获取全部鉴权信息记录"+list.size()+"条");
					for (AuthInfo authInfo : list) {
						System.out.println("获取到鉴权信息:"+authInfo.getTerminal());
						map.put(authInfo.getTerminal(), authInfo);
					}
					//存入redis缓存中
					RedisManager.getInstance().setObject("authinfo", map);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
