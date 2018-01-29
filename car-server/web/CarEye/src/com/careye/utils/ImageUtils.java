package com.careye.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Encoder;

import com.careye.listener.ApplicationContextListener;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {
	private static final String[] IMG_SUFFIX = new String[]{".gif", ".bmp", ".jpg", ".jpeg", ".png"};
	/**
	 * 把图片印刷到图片上
	 * @param pressImg -- 水印文件
	 * @param targetImg --  目标文件
	 * @param x
	 * @param y
	 */
	public final static void pressImage(String pressImg, String targetImg, int x, int y) {
		try {
			if (!targetImgExists(targetImg)) {
				return;
			}
			
			targetImg = ApplicationContextListener.getServletContext().getRealPath(targetImg);
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			// 水印文件
			pressImg = ApplicationContextListener.getServletContext().getRealPath(pressImg);
			File _filebiao = new File(pressImg);
			Image src_biao = ImageIO.read(_filebiao);
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			g.drawImage(src_biao, wideth - wideth_biao - x, height - height_biao - y, wideth_biao, height_biao, null);
			
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
			param.setQuality(1, true);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean targetImgExists(String targetImg) {
		if (StringUtils.isEmpty(targetImg)) {
			return false;
		}
		
		//验证用户上传的文件是否是图片格式
		String[] imgSuffix = IMG_SUFFIX;
		for (String suff : imgSuffix) {
			if (targetImg.endsWith(suff)) {
				return true;
			}
		}
		return false;
	}

	/** */
	/**
	 * 打印文字水印图片
	 * @param pressText --文字
	 * @param targetImg --  目标图片
	 * @param fontName --  字体名
	 * @param fontStyle -- 字体样式
	 * @param color -- 字体颜色
	 * @param fontSize -- 字体大小
	 * @param x -- 偏移量
	 * @param y
	 */

	public static void pressText(String pressText, String targetImg, String fontName, int fontStyle, int color, int fontSize, int x,int y) {
		try {
			if (!targetImgExists(targetImg)) {
				return;
			}
			
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			g.setColor(Color.LIGHT_GRAY);
			Font mFont = new Font("微软雅黑",fontStyle,fontSize);
			g.setFont(mFont);

			g.drawString(pressText, wideth - fontSize - x, height - fontSize / 2 - y);
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getImageStr(String imgFile) {
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	public static void main(String[] args) {
		
		pressImage("D:/sylogo.png", "D:/1386669391765.jpg", 0,0);

	}

	
	
	
}
