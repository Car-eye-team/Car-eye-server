/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.utlis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：Configuration    
 * 类描述：读取文件内容    
 * 创建人：zr    
 * 创建时间：2015-5-9 上午10:42:33    
 * 修改人：zr    
 * 修改时间：2015-5-9 上午10:42:33    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class Configuration {
	
	private Properties propertie;
	private FileInputStream inputFile;
	private FileOutputStream outputFile;

	private final static Logger logger = Logger.getLogger(Configuration.class);	
	
	/** 
     * 初始化Configuration类
     */
    public Configuration() {
        propertie = new Properties();
    }
    
    /**
     * 初始化Configuration类
     * @param filePath 要读取的配置文件的路径+名称
     */
    public Configuration(String filePath)
    {
        propertie = new Properties();
        try {
            inputFile = new FileInputStream(filePath);
            propertie.load(new InputStreamReader(inputFile, "utf-8"));
            inputFile.close();
        } catch (FileNotFoundException ex) {
            logger.info("读取属性文件" + filePath + " ->失败！- 原因：文件路径错误或者文件不存在");
            ex.printStackTrace();
        } catch (IOException ex) {
        	logger.info("装载文件" + filePath + " --->失败!");

            ex.printStackTrace();
        }
    }//end ReadConfigInfo(...)
    
    
    /** 
     * 重载函数，得到key的值
     * @param key 取得其值的键
     * @return key的值
     */
    public String getValue(String key)
    {
        if(propertie.containsKey(key)) {
            String value = propertie.getProperty(key).trim();//得到某一属性的值
            return value;
        }
        else 
            return "";
    }//end getValue(...)
    
    /** 
     * 重载函数，得到key的值
     * @param fileName properties文件的路径+文件名
     * @param key 取得其值的键
     * @return key的值
     */
    public String getValue(String fileName, String key)
     {
        try {
            String value = "";
            inputFile = new FileInputStream(fileName);
            propertie.load(inputFile);
            inputFile.close();
            if(propertie.containsKey(key)) {
                value = propertie.getProperty(key);
                return value;
            }else
                return value;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }//end getValue(...)
    
    /**
     * 清除properties文件中所有的key和其值
     */
    public void clear()
    {
        propertie.clear();
    }//end clear();
    
    /** 
     * 改变或添加一个key的值，当key存在于properties文件中时该key的值被value所代替，
     * 当key不存在时，该key的值是value
     * @param key 要存入的键
     * @param value 要存入的值
     */
    public void setValue(String key, String value)
	{
        propertie.setProperty(key, value);
    }//end setValue(...)
    
    /** 
     * 将更改后的文件数据存入指定的文件中，该文件可以事先不存在。
     * @param fileName 文件路径+文件名称
     * @param description 对该文件的描述
     */
    public void saveFile(String fileName, String description)
	{
        try {
            outputFile = new FileOutputStream(fileName);
            propertie.store(outputFile, description);
            outputFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }//end saveFile(...)
}
