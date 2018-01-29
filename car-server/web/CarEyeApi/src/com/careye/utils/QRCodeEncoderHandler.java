/**
* Description: car-eye车辆管理平台
* 文件名：QRCodeEncoderHandler.java
* 版本信息：1.0
* 日期：2015-5-12
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * @项目名称：OCS
 * @类名称：QRCodeEncoderHandler
 * @类描述： 二维码生成器
 * @创建人：zhangrong
 * @创建时间：2015-5-12 上午10:03:47
 * @修改人：zhangrong
 * @修改时间：2015-5-12 上午10:03:47
 * @修改备注：
 * @version 1.0
 */
public class QRCodeEncoderHandler {

	 /**

     * 生成二维码(QRCode)图片

     * @param content

     * @param imgPath

     */ 

    public static boolean encoderQRCode(String content, String imgPath,String filename) { 

        try { 

            Qrcode qrcodeHandler = new Qrcode(); 

            qrcodeHandler.setQrcodeErrorCorrect('M'); 

            qrcodeHandler.setQrcodeEncodeMode('B'); 

            qrcodeHandler.setQrcodeVersion(5); 

            byte[] contentBytes = content.getBytes("gb2312"); 

            BufferedImage bufImg = new BufferedImage(115, 115, 

                    BufferedImage.TYPE_INT_RGB); 

            Graphics2D gs = bufImg.createGraphics(); 

            gs.setBackground(Color.WHITE); 

            gs.clearRect(0, 0, 115, 115); 

            // 设定图像颜色> BLACK 

            gs.setColor(Color.BLACK); 

            // 设置偏移量 不设置可能导致解析出错 

            int pixoff = 2; 

            // 输出内容> 二维码 

            if (contentBytes.length > 0 && contentBytes.length < 120) { 

                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes); 

                for (int i = 0; i < codeOut.length; i++) { 

                    for (int j = 0; j < codeOut.length; j++) { 

                        if (codeOut[j][i]) { 

                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3); 

                        } 

                    } 

                } 

            } else { 

                System.err.println("QRCode content bytes length = "  + contentBytes.length + " not in [ 0,120 ]. "); 

            } 

            gs.dispose(); 

            bufImg.flush(); 

            File pathfile = new File(imgPath);
            if(!pathfile.exists())
            	pathfile.mkdir();
            
            File imgFile = new File(imgPath+filename); 

            // 生成二维码QRCode图片 

            ImageIO.write(bufImg, "png", imgFile); 
            return true;

        } catch (Exception e) { 

            e.printStackTrace(); 
            return false;

        } 

    } 


    /**

     * @param args the command line arguments

     */ 

    public static void main(String[] args) { 

        String imgPath = "F:/code/"; 
        String filename = "cdode.png"; 
        String url = "哈哈哈哈哈哈哈"; 
        QRCodeEncoderHandler handler = new QRCodeEncoderHandler(); 
        handler.encoderQRCode(url, imgPath,filename); 

    } 
	
}
