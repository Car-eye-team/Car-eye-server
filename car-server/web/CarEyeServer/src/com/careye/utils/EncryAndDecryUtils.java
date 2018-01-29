/**    
 * Description: car-eye车辆管理平台    
 * 文件名：EncryAndDecryUtils.java   
 * 版本信息：    
 * 日期：2013-7-20  
 * Copyright car-eye车辆管理平台 Copyright (c) 2013     
 * 版权所有    
 *    
 */
package com.careye.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**    
 *     
 * car-eye车辆管理平台业务处理    
 * 类名称：EncryAndDecryUtils    
 * 类描述： AES加密（128密钥加密）   
 * 创建人：zr    
 * 创建时间：2013-7-20 下午02:19:57    
 * 修改人：zr    
 * 修改时间：2013-7-20 下午02:19:57    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class EncryAndDecryUtils {
	
	private static final Logger logger = Logger.getLogger(EncryAndDecryUtils.class);
	
	
	/**
	 * 密钥路径
	 */
	public static String getKeyPath(){
		String keyPath = "/common/key/key.key";
		String path = EncryAndDecryUtils.class.getResource("/").getPath();
		int end = path.indexOf("WEB-INF");
		path = path.substring(0, end);
		keyPath = path + keyPath;
		return keyPath;
	}
	
	
	/**
	 * @param plaintext
	 * @return
	 * 加密
	 */
	public static String  encryption(String plaintext) {
		String ciphertext = null;
		KeyGenerator keygen;	
		SecretKey deskey;		
		Cipher c;		
		Security.addProvider(new com.sun.crypto.provider.SunJCE());		
		try {
			keygen = KeyGenerator.getInstance("AES");
			keygen.init(128);
			deskey = getSecretKey();
			c = Cipher.getInstance("AES");
			c.init(Cipher.ENCRYPT_MODE, deskey);		
			byte[] src = plaintext.getBytes();			
			byte[] enc = c.doFinal(src);
			BASE64Encoder base64en = new BASE64Encoder();
			ciphertext = base64en.encode(enc);
		} catch (Exception e) {
			logger.error("加密异常！", e); 
		}		
		return ciphertext;
	}
	
	/**
	 * @param plaintext
	 * @return
	 * 解密
	 */
	public static String decryption(String ciphertext){
		String plaintext = null;
		KeyGenerator keygen;	
		SecretKey deskey;		
		Cipher c;		
		Security.addProvider(new com.sun.crypto.provider.SunJCE());	
		try {
			BASE64Decoder base64de = new BASE64Decoder();	
			keygen = KeyGenerator.getInstance("AES");
			keygen.init(128);
			deskey = getSecretKey();
			c = Cipher.getInstance("AES");
			c.init(Cipher.DECRYPT_MODE, deskey);	
			byte[] src = base64de.decodeBuffer(ciphertext);		
			byte[] dec = c.doFinal(src);
			plaintext = new String(dec);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("解密异常！", e); 
		}
		return plaintext;
	}

	
	/**
	 * @param key
	 * 保存密钥
	 */
	public static void saveSecretKey(SecretKey key) {
        FileOutputStream fos = null;  
        ObjectOutputStream oos = null;  
        File f = new File(getKeyPath());  
        try {  
            fos = new FileOutputStream(f);  
            oos = new ObjectOutputStream(fos);  
            oos.writeObject(key);    //括号内参数为要保存java对象  
        } catch (FileNotFoundException e) {  
        	logger.error("路径错误异常！", e); 
        } catch (IOException e) {  
        	logger.error("输出流异常！", e);  
        }finally{  
            try {  
                oos.close();  
                fos.close();  
            } catch (IOException e) {  
            	logger.error("输出流关闭异常！", e); 
            }  
        }     
	}
	
	/***
	 * 获取密钥
	 */
	private static SecretKey getSecretKey(){
        FileInputStream fis = null;  
        ObjectInputStream ois = null; 
        SecretKey key = null;
        File f = new File(getKeyPath());  
        try {  
            fis = new FileInputStream(f);  
            ois = new ObjectInputStream(fis);  
            key = (SecretKey)ois.readObject();//强制类型转换   
              
        } catch (FileNotFoundException e) {  
        	logger.error("路径错误异常！", e);  
        } catch (IOException e) {  
        	logger.error("输入流异常！", e); 
        } catch (ClassNotFoundException e) {  
        	logger.error("强制类型转换 异常！", e); 
        }finally{  
            try {  
                ois.close();  
                fis.close();  
            } catch (IOException e) {  
            	logger.error("输入流关闭异常！", e); 
            }  
        }
        return key;
	}
	
	public static void main(String[] args) throws Exception {
		
//		KeyGenerator keygen;
//		
//		SecretKey deskey;
//			
//		Security.addProvider(new com.sun.crypto.provider.SunJCE());
//		
//		keygen = KeyGenerator.getInstance("AES");
//		
//		keygen.init(128);
//		
//		deskey = keygen.generateKey();
//	
//		
//		saveSecretKey(deskey);
		
//		long start = System.currentTimeMillis();
//		
//		String password = "123";
//		
//		String str = encryption(password);
//		
//		System.out.println("密文：" + str);
//		
//		System.out.println(str.length());
//		
//		str = decryption(str);
//		
//		System.out.println("明文："+ str);
//		
//		long end = System.currentTimeMillis();
//		
//		System.out.println(end - start);
//		BASE64Encoder base64en = new BASE64Encoder();
//		String mailCheckCode = base64en +"";
//		System.out.print(mailCheckCode);


		String name=java.net.URLEncoder.encode("测试", "UTF-8");
		System.out.println(name);
		System.out.println(java.net.URLDecoder.decode(name, "ISO-8859-1"));


		
	}
}
