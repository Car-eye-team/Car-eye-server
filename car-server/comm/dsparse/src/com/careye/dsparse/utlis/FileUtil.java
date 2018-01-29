/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.utlis;

import java.io.ByteArrayInputStream;
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
