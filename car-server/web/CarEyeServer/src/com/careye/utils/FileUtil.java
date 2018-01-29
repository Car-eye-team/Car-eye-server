/**    
 * Description: 多森软件物联网车联网平台    
 * 文件名：FileUtil.java   
 * 版本信息：    
 * 日期：2015-10-16  
 * Copyright car-eye车辆管理平台 Copyright (c) 2015     
 * 版权所有    
 *    
 */
package com.careye.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;


/**    
 *     
 * 项目名称：dsparse    
 * 类名称：FileUtil    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2015-10-16 下午03:33:14    
 * 修改人：Administrator    
 * 修改时间：2015-10-16 下午03:33:14    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class FileUtil {

	/**
     * 文件本地路径转二进制数组
     * @param path
     * @return
     */
    public static byte[] getImageBinary(String filePath){      
    	 byte[] buffer = null;  
         try {  
             File file = new File(filePath);  
             FileInputStream fis = new FileInputStream(file);  
             ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
             byte[] b = new byte[1000];  
             int n;  
             while ((n = fis.read(b)) != -1) {  
                 bos.write(b, 0, n);  
             }  
             fis.close();  
             bos.close();  
             buffer = bos.toByteArray();  
         } catch (Exception e) {  
             e.printStackTrace();  
         }  
         return buffer;  
    }    
	
	/**
	 * 将接收的字符串转换成图片保存
	 * @param imgStr 二进制流转换的字符串
	 * @param imgPath 图片的保存路径
	 * @param imgName 图片的名称
	 * @return 
	 *      1：保存正常
	 *      0：保存失败
	 */
	public static int saveToImgByStr(byte[] imgByte,String imgPath,String imgName){
		int stateInt = 1;
		if(imgByte != null){
			try {

				InputStream in = new ByteArrayInputStream(imgByte);
				
				if(!new File(imgPath).exists()){ //目录不存在则创建
					new File(imgPath).mkdir();
				}
				
				File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
				FileOutputStream fos=new FileOutputStream(file);

				byte[] b = new byte[imgByte.length];
				int nRead = 0;
				while ((nRead = in.read(b)) != -1) {
					fos.write(b, 0, nRead);
				}
				fos.flush();
				fos.close();
				in.close();

			} catch (Exception e) {
				stateInt = 0;
				e.printStackTrace();
			} finally {
			}
		}
		return stateInt;
	}


	/**
	 * 将二进制转换成图片保存
	 * @param imgStr 二进制流转换的字符串
	 * @param imgPath 图片的保存路径
	 * @param imgName 图片的名称
	 * @return 
	 *      1：保存正常
	 *      0：保存失败
	 */
	public static int saveToImgByBytes(File imgFile,String imgPath,String imgName){

		int stateInt = 1;
		if(imgFile.length() > 0){
			try {
				File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
				FileOutputStream fos=new FileOutputStream(file);

				FileInputStream fis = new FileInputStream(imgFile);

				byte[] b = new byte[1024];
				int nRead = 0;
				while ((nRead = fis.read(b)) != -1) {
					fos.write(b, 0, nRead);
				}
				fos.flush();
				fos.close();
				fis.close();

			} catch (Exception e) {
				stateInt = 0;
				e.printStackTrace();
			} finally {
			}
		}
		return stateInt;
	}
}
