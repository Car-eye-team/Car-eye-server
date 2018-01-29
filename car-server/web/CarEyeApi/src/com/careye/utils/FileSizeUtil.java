/**
 * 
 */
package com.careye.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Random;

import com.careye.utils.DateUtil;
import com.careye.utils.FileSizeUtil;

/**
 * @author zr
 *
 */
public class FileSizeUtil {
	public static final int SIZETYPE_B = 1;//获取文件大小单位为B的double值
	public static final int SIZETYPE_KB = 2;//获取文件大小单位为KB的double值
	public static final int SIZETYPE_MB = 3;//获取文件大小单位为MB的double值
	public static final int SIZETYPE_GB = 4;//获取文件大小单位为GB的double值
	/**
	 * 获取文件指定文件的指定单位的大小
	 * @param filePath 文件路径
	 * @param sizeType 获取大小的类型1为B、2为KB、3为MB、4为GB
	 * @return double值的大小
	 */
	public static double getFileOrFilesSize(String filePath,int sizeType){
		File file=new File(filePath);
		long blockSize=0;
		try {
			if(file.isDirectory()){
				blockSize = getFileSizes(file);
			}else{
				blockSize = getFileSize(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormetFileSize(blockSize, sizeType);
	}
	/**
	 * 调用此方法自动计算指定文件或指定文件夹的大小
	 * @param filePath 文件路径
	 * @return 计算好的带B、KB、MB、GB的字符串
	 */
	public static String getAutoFileOrFilesSize(String filePath){
		File file=new File(filePath);
		long blockSize=0;
		try {
			if(file.isDirectory()){
				blockSize = getFileSizes(file);
			}else{
				blockSize = getFileSize(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormetFileSize(blockSize);
	}
	/**
	 * 获取指定文件大小
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private static long getFileSize(File file) throws Exception
	{
		long size = 0;
		if (file.exists()){
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			size = fis.available();
		}
		else{
			file.createNewFile();
		}
		return size;
	}

	/**
	 * 获取指定文件夹
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private static long getFileSizes(File f) throws Exception
	{
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++){
			if (flist[i].isDirectory()){
				size = size + getFileSizes(flist[i]);
			}
			else{
				size =size + getFileSize(flist[i]);
			}
		}
		return size;
	}
	/**
	 * 转换文件大小
	 * @param fileS
	 * @return
	 */
	private static String FormetFileSize(long fileS)
	{
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		String wrongSize="0B";
		if(fileS==0){
			return wrongSize;
		}
		if (fileS < 1024){
			fileSizeString = df.format((double) fileS) + "B";
		}
		else if (fileS < 1048576){
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		}
		else if (fileS < 1073741824){
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		}
		else{
			fileSizeString = df.format((double) fileS / 1073741824) + "GB";
		}
		return fileSizeString;
	}
	/**
	 * 转换文件大小,指定转换的类型
	 * @param fileS 
	 * @param sizeType 
	 * @return
	 */
	private static double FormetFileSize(long fileS,int sizeType)
	{
		DecimalFormat df = new DecimalFormat("#.00");
		double fileSizeLong = 0;
		switch (sizeType) {
		case SIZETYPE_B:
			fileSizeLong=Double.valueOf(df.format((double) fileS));
			break;
		case SIZETYPE_KB:
			fileSizeLong=Double.valueOf(df.format((double) fileS / 1024));
			break;
		case SIZETYPE_MB:
			fileSizeLong=Double.valueOf(df.format((double) fileS / 1048576));
			break;
		case SIZETYPE_GB:
			fileSizeLong=Double.valueOf(df.format((double) fileS / 1073741824));
			break;
		default:
			break;
		}
		return fileSizeLong;
	}
	
	/** 
     * 获得指定文件的byte数组 
     */  
    public static byte[] getBytes(String filePath,int size){  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(size);  
            byte[] b = new byte[size];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    } 
    
    /**
	 * 得到随机生成文件名，防止保存文件时重复
	 * 当前时间加随机数形式
	 * @return
	 */
	private static Random random = new Random();
	public static String getRandomFileName() {
		return DateUtil.fomatDate(DateUtil.getSQLDate()) + random.nextInt(10000);
	}
	
	 /**
     * 将接收的字符串转换成文件保存
     * @param fileStr 二进制流转换的字符串
     * @param filePath 文件的保存路径
     * @param fileName 文件的名称
     * @return 
     *      1：保存正常
     *      0：保存失败
     */
    public static int saveToFileByStr(String fileStr,String filePath,String fileName){
		try {
		    System.out.println("===fileStr.length()====>" + fileStr.length()
		            + "=====fileStr=====>" + fileStr);
		} catch (Exception e) {
		    e.printStackTrace();
		}
        int stateInt = 1;
        if(fileStr != null && fileStr.length() > 0){
            try {
                 
                // 将字符串转换成二进制，用于显示文件  
                // 将上面生成的文件格式字符串 fileStr，还原成文件显示  
                byte[] fileByte = hex2byte( fileStr );  
     
                InputStream in = new ByteArrayInputStream(fileByte);
     
                File file=new File(filePath,fileName);//可以是任何文件格式
                FileOutputStream fos=new FileOutputStream(file);
                   
                byte[] b = new byte[1024];
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
     * 字符串转二进制
     * @param str 要转换的字符串
     * @return  转换后的二进制数组
     */
    public static byte[] hex2byte(String str) { // 字符串转二进制
        if (str == null)
            return null;
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1)
            return null;
        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer
                        .decode("0X" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 文件路径转字符串url传递
     * @param path
     * @return
     */
    public static String getImageBinary(String filePath){      
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
         return FileSizeUtil.byte2hex(buffer);  
    }  
    
    /**
     * 二进制转字符串
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) // 二进制转字符串
    {
        StringBuffer sb = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                sb.append("0" + stmp);
            } else {
                sb.append(stmp);
            }
 
        }
        return sb.toString();
    }
}
