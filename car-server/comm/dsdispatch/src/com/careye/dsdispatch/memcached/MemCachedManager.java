/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.memcached;

import java.util.Date;

import com.careye.dsdispatch.constant.ConfigProperties;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：MemCachedManager    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-7-8 上午11:12:34    
 * 修改人：zr    
 * 修改时间：2015-7-8 上午11:12:34    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class MemCachedManager {
	// 创建全局的唯一实例
	protected static MemCachedClient mcc = new MemCachedClient();
	protected static MemCachedManager memCached = new MemCachedManager();
	public static Object object = new Object();

	/**
	 * 保护型构造方法，不允许实例化！
	 *
	 */
	public MemCachedManager(){
		initMemCached();
	}

	/**
	 * 获取唯一实例.
	 * @return
	 */
	public static MemCachedManager getInstance(){
		if (memCached == null) {
			memCached = new MemCachedManager();
		}
		return memCached;
	}

	public void initMemCached(){
		try {

			String ip = ConfigProperties.MEMCACHED_IP;
			String port = ConfigProperties.MEMCACHED_PORT;
			
			/*String ip = "202.104.150.177";
			String port = "11111";*/

			// 服务器列表和其权重
			String[] servers = { ip+":"+port };
			Integer[] weights = { 3 };

			// 获取socke连接池的实例对象
			SockIOPool pool = SockIOPool.getInstance();

			// 设置服务器信息
			pool.setServers( servers );
			pool.setWeights( weights );

			// 设置初始连接数、最小和最大连接数以及最大处理时间
			pool.setInitConn( 200 );
			pool.setMinConn( 500 );
			pool.setMaxConn( 1000 );
			pool.setMaxIdle( 1000 * 60 * 60 * 6 );

			// 设置主线程的睡眠时间
			pool.setMaintSleep( 30 );

			// 设置TCP的参数，连接超时等
			pool.setNagle( false );
			pool.setSocketTO( 3000 );
			pool.setSocketConnectTO( 0 );

			// 初始化连接池
			pool.initialize();

			// 压缩设置，超过指定大小（单位为K）的数据都会被压缩
			mcc.setCompressEnable( true );
			mcc.setCompressThreshold( 64 * 1024 );
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	/**
	 * 加入到内存
	 * @param key
	 * @param value
	 */
	public void setObject(String key, Object value){
		try {
			if(mcc.keyExists(key)){
				replace(key, value);
			}else{
				add(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean set(String key, Object value){
		return mcc.set(key, value);
	}

	/**
	 * 添加一个指定的值到缓存中.
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, Object value){
		return mcc.add(key, value);
	}

	public boolean add(String key, Object value, Date expiry){
		return mcc.add(key, value, expiry);
	}

	public boolean replace(String key, Object value){
		return mcc.replace(key, value);
	}

	public boolean replace(String key, Object value, Date expiry){
		return mcc.replace(key, value, expiry);
	}

	public boolean delete(String key){
		return mcc.delete(key);
	}

	public boolean flushAll(){
		return mcc.flushAll();
	}

	/**
	 * 根据指定的关键字获取对象.
	 * @param key
	 * @return
	 */
	public Object get(String key){
		return mcc.get(key);
	}

	public static void main(String[] args){
		
		//CarInfo cInfo = CarInfoCache.get("18668171282");
		//System.out.println("cInfo=="+cInfo);

		for (int i = 0; i < 100000; i++) {
			
			System.out.println("i============="+i);
			
		    MemCachedManager cache = MemCachedManager.getInstance();
		    mcc.set("66666", "3333");
		   // mcc.set("3333", "4444");
			System.out.println(cache.get("66666"));
			/*CarInfo carInfo = new CarInfo();

			carInfo.setCarnumber("粤B888888"+i);

			cache.add("粤B888888",carInfo);*/


			//cache.flushAll();
			//System.out.print( " get value : " + cInfo);
		}
	}
}
