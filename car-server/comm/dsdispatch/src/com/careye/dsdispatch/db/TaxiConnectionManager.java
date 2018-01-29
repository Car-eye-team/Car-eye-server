/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.db;

import java.beans.PropertyVetoException;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.careye.dsdispatch.utlis.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：TaxiConnectionManager    
 * 类描述：数据库连接池管理    
 * 创建人：zr    
 * 创建时间：2015-5-19 下午01:39:12    
 * 修改人：zr    
 * 修改时间：2015-5-19 下午01:39:12    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TaxiConnectionManager {


	private final static Logger logger = Logger.getLogger(TaxiConnectionManager.class);
	private static TaxiConnectionManager instance;
	private static ComboPooledDataSource dataSource;

	private TaxiConnectionManager() throws SQLException, PropertyVetoException {
		
		String proPath = "." + File.separator + "conf" + File.separator + "jdbc.properties";
		Configuration sysConfg = new Configuration(proPath);
		if(sysConfg == null) {
			logger.info("错误：初始化conf\\jdbc.properties文件错误");
			return;
		}

		String user = null;
		String password = null;
		String jdbcUrl = null;
		String driverClass = null;
		int initPoolSize = 5;
		int minPoolSize = 1;
		int maxPoolSize = 10;
		int maxStatements = 0;
		int maxIdleTime = 60;

		user = new String(sysConfg.getValue("taxi_user").trim());
		password = new String(sysConfg.getValue("taxi_password").trim());
		jdbcUrl = new String(sysConfg.getValue("taxi_jdbcUrl").trim());
		driverClass = new String(sysConfg.getValue("taxi_driverClass").trim());
		initPoolSize = Integer.parseInt(sysConfg.getValue("taxi_initPoolSize").trim());
		minPoolSize = Integer.parseInt(sysConfg.getValue("taxi_minPoolSize").trim());
		maxPoolSize = Integer.parseInt(sysConfg.getValue("taxi_maxPoolSize").trim());
		maxStatements = Integer.parseInt(sysConfg.getValue("taxi_maxStatements").trim());
		maxIdleTime = Integer.parseInt(sysConfg.getValue("taxi_maxIdleTime").trim());

		// new
		dataSource = new ComboPooledDataSource();

		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setDriverClass(driverClass);

		dataSource.setInitialPoolSize(initPoolSize);
		dataSource.setMinPoolSize(minPoolSize);
		dataSource.setMaxPoolSize(maxPoolSize);
		dataSource.setMaxStatements(maxStatements);

		dataSource.setMaxIdleTime(maxIdleTime);        	

	}

	public static final TaxiConnectionManager getInstance() {
		if (instance == null) {
			try {
				instance = new TaxiConnectionManager();
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("异常："+e.getMessage());
			}
		}
		return instance;
	}

	public synchronized final Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			logger.info("异常："+e.getMessage());
		} 		
		return conn;
	}    
	/**
	 * 用于释放数据库连接
	 * @author ght
	 * @param conn
	 * @since 2010-06-11 10:54
	 */
	public static void releaseConn(Connection conn) {
		try { //释放Connection对象
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException sqle) {
			logger.info("数据库释放连接出错，将重试");
			try { //重新试图释放Connection对象
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle1) {
				logger.info("数据库释放连接失败");
			}
		} 
	}

}
