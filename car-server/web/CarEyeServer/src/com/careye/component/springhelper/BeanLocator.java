/**
 * <p>System: duosen_ecs</p>
 * <p>Description: duosen 56gate物流平台</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 广东深圳多森软件有限公司</p>
 * @author: zr
 * @createDate: 17 07, 2013
 * @version 1.0
 */
package com.careye.component.springhelper;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 加载spring配置文件，提供getBean接口.
 * @author xiongzy
 *
 */
public final class BeanLocator {
	private static final Logger logger = Logger.getLogger(BeanLocator.class);
    /**
     * 单实例.
     */
    private static BeanLocator instance = null;
    
    /**
     * 缺省配置文件名称.
     */
    private static final String DEFAULT_CONFIGURATION_FILENAME = "spring/applicationContext.xml";
    
    /**
     * 加载配置文件名.
     */
    private static String configurationFileName = null;
    
    /**
     * spring环境.
     */
    private ApplicationContext applicationContext = null;

    /**
     * 单例模式.
     * @return 接口
     */
    public static BeanLocator getInstance() {
        if (instance == null) {
            // 同步控制代码, 防止初始化多次.
            synchronized(logger) {
                if (instance == null) {
                    instance = new BeanLocator();
                }
            }
        }
        return instance;
    }

    /**
     * 设置配置文件名.
     * @param fileName 配置文件名
     */
    public static void setConfigurationFileName(String fileName) {
        configurationFileName = fileName;
    }

    
    /**
     * 私有构造.
     */
    private BeanLocator() {
        if (configurationFileName == null || configurationFileName.length() == 0) {
            configurationFileName = DEFAULT_CONFIGURATION_FILENAME;
        }
        // 得到spring框架bean环境
        try{
        	applicationContext = new ClassPathXmlApplicationContext(configurationFileName);
        }catch(Exception e){
        	logger.error("初始化spring配置文件时发生异常:" + e.getMessage(), e);
        	throw new RuntimeException("初始化spring配置文件时发生异常:" + e.getMessage(), e);
        }
    }

    /**
     * spring getBean 接口.
     * @param beanName 接口名称
     * @return 接口
     */
    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
