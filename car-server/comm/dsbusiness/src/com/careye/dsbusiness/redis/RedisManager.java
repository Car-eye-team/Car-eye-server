/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsbusiness.redis;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import com.careye.dsbusiness.constant.ConfigProperties;
import com.careye.dsbusiness.utlis.ExceptionUtil;
import com.careye.dsbusiness.utlis.SerializeUtil;



/**    
 *     
 * 项目名称：dsauth    
 * 类名称：RedisManager    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-11 下午02:51:01    
 * 修改人：zr    
 * 修改时间：2015-5-11 下午02:51:01    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class RedisManager {

	private final static Logger logger = Logger.getLogger(RedisManager.class);

	private static RedisManager redisManager = null;

	/**
	 * 非切片链接池
	 */

	private static  JedisPool jedisPool;

	/**
	 * 切片链接池
	 */
	private ShardedJedisPool shardedJedisPool;

	/**
	 * 构造函数
	 */
	public RedisManager(){

		initialPool();

	}

	public static RedisManager getInstance(){
		if(redisManager == null){
			redisManager =  new RedisManager();
		}
		return redisManager;
	}

	/**
	 * 初始化非切片池
	 */
	private void initialPool(){
		try {
			// 池基本配置
			JedisPoolConfig config = new JedisPoolConfig();
			//最大连接数, 默认8个
			config.setMaxTotal(50);
			//最大能够保持idel状态的对象数 
			config.setMaxIdle(20);
			//最小空闲连接数
			config.setMinIdle(5);
			//当池内没有返回对象时，最大等待时间  
			config.setMaxWaitMillis(10000);

			//当调用borrow Object方法时，是否进行有效性检查 
			config.setTestOnBorrow(true);

			//当调用return Object方法时，是否进行有效性检查  
			config.setTestOnReturn(true);

			jedisPool = new JedisPool(config, ConfigProperties.REDIS_IP, ConfigProperties.REDIS_PORT,10000,ConfigProperties.REDIS_PASSWORD);

			//jedis = jedisPool.getResource();

		} catch (Exception e) {
			logger.info("初始化非切片池异常"+e.getMessage());
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}
	}

	/**
	 * 初始化切片池
	 */
	@SuppressWarnings("unused")
	private void initialShardedPool(){

		try {
			// 池基本配置
			JedisPoolConfig config = new JedisPoolConfig();
			//最大连接数, 默认8个
			config.setMaxTotal(20);
			//最大能够保持idel状态的对象数 
			config.setMaxIdle(5);
			//最小空闲连接数
			config.setMinIdle(0);
			//当池内没有返回对象时，最大等待时间  
			config.setMaxWaitMillis(1000l);
			//当调用borrow Object方法时，是否进行有效性检查 
			config.setTestOnBorrow(false);
			// slave链接
			List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
			shards.add(new JedisShardInfo(ConfigProperties.REDIS_IP, ConfigProperties.REDIS_PORT, "master"));
			// 构造池
			shardedJedisPool = new ShardedJedisPool(config, shards);
		} catch (Exception e) {
			logger.info("初始化切片池异常"+e.getMessage());
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

	}

	/**
	 * 保存字符串
	 * @param key
	 * @param value
	 */
	public void set(String key,String value){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			// 销毁对象  
			returnBrokenResource(jedisPool,jedis,e);
		}finally{
			//返还到连接池
			returnResource(jedisPool,jedis);
		}
	}

	/**
	 * 根据key获取字符串
	 * @param key
	 */
	public String get(String key){
		String value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			value = jedis.get(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			// 销毁对象  
			returnBrokenResource(jedisPool,jedis,e);
			return null;
		}finally{
			//返还到连接池
			returnResource(jedisPool,jedis);
		}
	}


	/**
	 * 删除制定key数据
	 * @param key
	 */
	public void delete(String key){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
			// 销毁对象  
			returnBrokenResource(jedisPool,jedis,e);
		}finally{
			//返还到连接池
			returnResource(jedisPool,jedis);
		}
	}

	/**
	 * 保存实体
	 * @param key
	 * @param value
	 */
	public  void setObject(String key,Object value){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			jedis.set(key.getBytes(), SerializeUtil.serialize(value));
		} catch (Exception e) {
			e.printStackTrace();
			// 销毁对象  
			returnBrokenResource(jedisPool,jedis,e);
		}finally{
			//返还到连接池
			returnResource(jedisPool,jedis);
		}
	}

	/**
	 * 根据key获取缓存中实体
	 * @param key
	 * @return
	 */
	public  Object getObject(String key){
		Object object = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			byte[] value = jedis.get(key.getBytes());
			if(value != null){
				object = SerializeUtil.unserialize(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 销毁对象  
			returnBrokenResource(jedisPool,jedis,e);
		}finally{
			//返还到连接池
			returnResource(jedisPool,jedis);
		}
		return object;
	}

	/**
	 * 删除离线消息
	 * @param key
	 */
	public void delObject(String key){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			// 销毁对象  
			returnBrokenResource(jedisPool,jedis,e);
		}finally{
			//返还到连接池
			returnResource(jedisPool,jedis);
		}

	}

	/**
	 * 清空数据
	 */
	public void flushDB(){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.flushDB();
		} catch (Exception e) {
			e.printStackTrace();
			// 销毁对象  
			returnBrokenResource(jedisPool,jedis,e);
		}finally{
			//返还到连接池
			returnResource(jedisPool,jedis);
		}
	}

	/**
	 * 返还到连接池
	 * @param pool 
	 * @param redis
	 */
	public void returnResource(JedisPool jedisPool, Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 销毁对象  
	 * @param jedisPool
	 * @param jedis
	 * @param e
	 */
	public void returnBrokenResource(JedisPool jedisPool, Jedis jedis,Exception e){
		if (jedis != null) {
			jedisPool.returnBrokenResource(jedis);
		}
		ExceptionUtil.getInfo(e);
	}

}
