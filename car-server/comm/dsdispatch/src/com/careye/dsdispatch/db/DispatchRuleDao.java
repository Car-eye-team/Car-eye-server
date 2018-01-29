/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.careye.dsdispatch.constant.Constant;
import com.careye.dsdispatch.redis.RedisManager;
import com.careye.dsdispatch.utlis.ExceptionUtil;
import com.careye.redis.domain.DispatchRule;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：DispatchRuleDao    
 * 类描述：调度规则    
 * 创建人：zr    
 * 创建时间：2015-5-22 下午01:53:04    
 * 修改人：zr    
 * 修改时间：2015-5-22 下午01:53:04    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DispatchRuleDao {

	private final static Logger logger = Logger.getLogger(DispatchRuleDao.class);

	/**
	 * 获取新的调度规则
	 */
	public static void getNewDispatchRule(){


		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;  
		try {
			conn = TaxiConnectionManager.getInstance().getConnection();
			String sql = "select t.type,t.radius,t.cartype,t.carcount,t.carstatus,t.zkstate," +
			"t.assigncount,t.assignmin,to_char(t.effecttime, 'yyyy-MM-dd HH24:mi:ss') effecttime," +
			"t.totalassigncount,t.aheadassignmin,t.immassigncount,t.assignwaitmin,t.traincount," +
			"to_char(t.stime, 'yyyy-MM-dd HH24:mi:ss') stime,to_char(t.etime, 'yyyy-MM-dd HH24:mi:ss') etime," +
			"t.principle,t.arrearage,t.breach,t.blacklist from to_dial_last_strategy t";

			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while(rs.next()){

				try {
					DispatchRule dispatchRule = new DispatchRule();
					dispatchRule.setType(rs.getInt("type"));
					dispatchRule.setRadius(rs.getInt("radius"));
					dispatchRule.setCartype(rs.getString("cartype"));
					dispatchRule.setCarnum(rs.getInt("carcount"));
					dispatchRule.setCarstatus(rs.getString("carstatus"));
					dispatchRule.setZkstate(rs.getString("zkstate"));
					dispatchRule.setDisnumber(rs.getInt("assigncount"));
					dispatchRule.setDispatchinterval(rs.getInt("assignmin"));
					dispatchRule.setEffecttime(rs.getString("effecttime"));
					dispatchRule.setCountdisnum(rs.getInt("totalassigncount"));
					dispatchRule.setAdvancedistime(rs.getInt("aheadassignmin"));
					dispatchRule.setInstantdisnum(rs.getInt("immassigncount"));
					dispatchRule.setWaittime(rs.getInt("assignwaitmin"));
					dispatchRule.setTrainnum(rs.getInt("traincount"));
					dispatchRule.setStime(rs.getString("stime"));
					dispatchRule.setEtime(rs.getString("etime"));
					dispatchRule.setPrinciple(rs.getString("principle"));
					dispatchRule.setArrearage(rs.getInt("arrearage"));
					dispatchRule.setBreach(rs.getInt("breach"));
					dispatchRule.setBlacklist(rs.getInt("blacklist"));

					//将新规则加入至redis缓存中
					RedisManager.getInstance().setObject(Constant.DISPATCH_RULE_NEW+dispatchRule.getType(), dispatchRule);

					//获取最后一条历史规则存入redis缓存中
					getOldDispatchRule(dispatchRule.getType());

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
	}


	/**
	 * 获取历史规则
	 * @param type
	 */
	public static void getOldDispatchRule(int type){


		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;  
		try {
			conn = TaxiConnectionManager.getInstance().getConnection();
			String sql = "select * from (select t.type,t.radius,t.cartype,t.carcount,t.carstatus," +
			"t.zkstate,t.assigncount,t.assignmin,to_char(t.effecttime, 'yyyy-MM-dd HH24:mi:ss') effecttime," +
			"t.totalassigncount,t.aheadassignmin,t.immassigncount,t.assignwaitmin,t.traincount," +
			"to_char(t.stime, 'yyyy-MM-dd HH24:mi:ss') stime,to_char(t.etime, 'yyyy-MM-dd HH24:mi:ss') etime," +
			"t.principle,t.arrearage,t.breach,t.blacklist from to_dial_history_strategy t where t.type=? order by id desc) " +
			" where rownum = 1";
			pst = conn.prepareStatement(sql);
			pst.setInt(1,type);
			rs = pst.executeQuery();

			while(rs.next()){

				try {
					DispatchRule dispatchRule = new DispatchRule();
					dispatchRule.setType(rs.getInt("type"));
					dispatchRule.setRadius(rs.getInt("radius"));
					dispatchRule.setCartype(rs.getString("cartype"));
					dispatchRule.setCarnum(rs.getInt("carcount"));
					dispatchRule.setCarstatus(rs.getString("carstatus"));
					dispatchRule.setZkstate(rs.getString("zkstate"));
					dispatchRule.setDisnumber(rs.getInt("assigncount"));
					dispatchRule.setDispatchinterval(rs.getInt("assignmin"));
					dispatchRule.setEffecttime(rs.getString("effecttime"));
					dispatchRule.setCountdisnum(rs.getInt("totalassigncount"));
					dispatchRule.setAdvancedistime(rs.getInt("aheadassignmin"));
					dispatchRule.setInstantdisnum(rs.getInt("immassigncount"));
					dispatchRule.setWaittime(rs.getInt("assignwaitmin"));
					dispatchRule.setTrainnum(rs.getInt("traincount"));
					dispatchRule.setStime(rs.getString("stime"));
					dispatchRule.setEtime(rs.getString("etime"));
					dispatchRule.setPrinciple(rs.getString("principle"));
					dispatchRule.setArrearage(rs.getInt("arrearage"));
					dispatchRule.setBreach(rs.getInt("breach"));
					dispatchRule.setBlacklist(rs.getInt("blacklist"));

					//将历史规则加入至redis缓存中
					RedisManager.getInstance().setObject(Constant.DISPATCH_RULE_OLD+type, dispatchRule);

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
	}
}
