/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsauth.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.careye.dsauth.utlis.ExceptionUtil;
import com.careye.redis.domain.AuthInfo;


/**    
 *     
 * 项目名称：dsauth    
 * 类名称：CommDao    
 * 类描述：获取鉴权信息dao    
 * 创建人：zr    
 * 创建时间：2015-5-11 下午01:40:25    
 * 修改人：zr    
 * 修改时间：2015-5-11 下午01:40:25    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class AuthInfoDao {

	private final static Logger logger = Logger.getLogger(AuthInfoDao.class);

	/**
	 * 获取ID最大值
	 * @return
	 */
	public static int getAuthInfoMaxId(){
		int maxid = 0;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;  
		try {
			conn = CommConnectionManager.getInstance().getConnection();
			String sql = "select max(notify_id) notify_id from ecs_car_register_notify t";
			//logger.info("获取鉴权列表执行:"+sql);
			pst = conn.prepareStatement(sql);

			rs = pst.executeQuery();

			while(rs.next()){
				maxid = rs.getInt("notify_id");
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {   
			try {
				conn.close();
				// 释放
				pst = null;
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
				ExceptionUtil.getInfo(e);
			}
		}
		return maxid;
	}

	/**
	 * 获取鉴权列表
	 * @param id
	 * @return
	 */
	public static List<AuthInfo> getAuthInfoList(int id){

		List<AuthInfo> list = new ArrayList<AuthInfo>();

		try {
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;  
			try {
				conn = CommConnectionManager.getInstance().getConnection();
				if(id > 0){
					String sql = "select t.notify_id,t.car_tel,t.user_type,t.platform_type,t.vin from ecs_car_register_notify t where t.notify_id >? order by notify_id desc";
					//logger.info("获取鉴权列表执行:"+sql);
					pst = conn.prepareStatement(sql);
					pst.setInt(1, id);
				}else{
					String sql = "select t.notify_id,t.car_tel,t.user_type,t.platform_type,t.vin from ecs_car_register_notify t order by notify_id desc";
					//logger.info("获取鉴权列表执行:"+sql);
					pst = conn.prepareStatement(sql);
				}

				rs = pst.executeQuery();

				while(rs.next()){
					try {
						AuthInfo authInfo = new AuthInfo();
						authInfo.setId(rs.getInt("notify_id"));
						authInfo.setTerminal(rs.getString("car_tel"));
						authInfo.setDeviceType(rs.getInt("user_type"));
						authInfo.setPlatformType(rs.getString("platform_type"));
						authInfo.setVin(rs.getString("vin"));
						list.add(authInfo);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				rs.close();
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {   
				try {
					conn.close();
					// 释放
					pst = null;
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
					ExceptionUtil.getInfo(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

}
