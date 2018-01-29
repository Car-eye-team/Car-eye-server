package com.careye.mq;


import com.careye.constant.ConfigProperties;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * @项目名称：FMSSERVER
 * @类名称：MemCachedUtil
 * @类描述：
 * @创建人：zr
 * @创建时间：2014-6-26 下午08:19:46
 * @修改人：zr
 * @修改时间：2014-6-26 下午08:19:46
 * @修改备注：
 * @version 1.0
 */
public class MemCachedManager {

	protected static MemCachedClient mcc = new MemCachedClient();

	private static MemCachedManager memCached = new MemCachedManager();

	static {

		try {
			// 设置与缓存服务器的连接池
			String[] servers = { ConfigProperties.MEMCACHED_IP+":"+ConfigProperties.MEMCACHED_PORT };
			// 服务器列表和其权重
			Integer[] weights = { 3 };

			// 获取socke连接池的实例对象
			SockIOPool pool = SockIOPool.getInstance();

			// 设置服务器信息
			pool.setServers(servers);
			pool.setWeights(weights);

			// 设置初始连接数、最小和最大连接数以及最大处理时间
			pool.setInitConn(5);
			pool.setMinConn(5);
			pool.setMaxConn(250);
			pool.setMaxIdle(1000 * 60 * 60 * 6);

			//维护线程的间隔激活时间，下面设置为60秒（单位s），设置为0表示不启用维护线程
			pool.setMaintSleep(0);

			// Tcp的规则就是在发送一个包之前，本地机器会等待远程主机
			// 对上一次发送的包的确认信息到来；这个方法就可以关闭套接字的缓存，
			// 以至这个包准备好了就发；
			pool.setNagle(false);
			// 连接建立后对超时的控制
			pool.setSocketTO(3000);
			// 连接建立时对超时的控制
			pool.setSocketConnectTO(0);
			
			// 初始化连接池
			pool.initialize();

			// 压缩设置，超过指定大小（单位为K）的数据都会被压缩
//			mcc.setCompressEnable( true );
//			mcc.setCompressThreshold( 64 * 1024 );

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
     * 保护型构造方法，不允许实例化！
     *
     */
    protected MemCachedManager()
    {
        
    }

	public static MemCachedManager getInstance() {
		return memCached;
	}
	
	/**
     * 添加一个指定的键值对到缓存中.
     * 
     * @param key
     * @param value
     * @param expiry 多久之后过期
     * @return
     */
    public boolean add(String key, Object value) {
        return mcc.add(key, value);
    }

	public boolean put(String key, Object value) {
		try {
			return mcc.set(key, value);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean delete(String key) {
		try {
			return mcc.delete(key);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public Object get(String key) {
		try {
			return mcc.get(key);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean replace(String key, Object value) {
		try {
			return mcc.replace(key, value, 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
