/**
 * 
 */
package com.careye.utils;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;



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
     * 从网络Url中下载文件 
     * @param urlStr 
     * @param fileName 
     * @param savePath 
     * @throws IOException 
     */ 
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{  
        URL url = new URL(urlStr);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
                //设置超时间为3秒  
        conn.setConnectTimeout(3*1000);  
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
  
        //得到输入流  
        InputStream inputStream = conn.getInputStream();    
        //获取自己数组  
        byte[] getData = readInputStream(inputStream);      
  
        //文件保存位置  
        File saveDir = new File(savePath);  
        if(!saveDir.exists()){  
            saveDir.mkdir();  
        }  
        File file = new File(saveDir+File.separator+fileName);      
        FileOutputStream fos = new FileOutputStream(file);       
        fos.write(getData);   
        if(fos!=null){  
            fos.close();    
        }  
        if(inputStream!=null){  
            inputStream.close();  
        }  
        if(fileName.indexOf("amr") > 0){
        	try {
				FileSizeUtil.amrToMp3(saveDir+File.separator+fileName);
			} catch (EncoderException e) {
			}
        }
  
  
        System.out.println("info:"+url+" download success");   
  
    } 
    /** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }   
    
    public static void amrToMp3(String filename) throws EncoderException{
		File source = new File(filename);
		File target = new File(filename.replace(".amr", ".mp3"));
		AudioAttributes audio = new AudioAttributes();
		Encoder encoder = new Encoder();
		
		audio.setCodec("libmp3lame");
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp3");
		attrs.setAudioAttributes(audio);
		
		encoder.encode(source, target, attrs);
	}
    
    /**
     * 下载url链接文件byte数组
     * @param path
     * @return
     */
    public static byte[] getImageBinaryByUrl(String urlStr){      
    	 byte[] buffer = null;  
         try {  
        	 URL url = new URL(urlStr);  
        	 HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
             //设置超时间为3秒  
		     conn.setConnectTimeout(3*1000);  
		     //防止屏蔽程序抓取而返回403错误  
		     conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
        	
		    //得到输入流  
	        InputStream inputStream = conn.getInputStream();    
	        //获取文件数组  
	        buffer = readInputStream(inputStream);  
         } catch (Exception e) {  
             e.printStackTrace();  
         }  
         return buffer;  
    }    
    
    /**
     * 二进制字节转16进制字符串
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
    
    /** 
     * splitAry方法<br> 
     * @param ary 要分割的数组 
     * @param subSize 分割的块大小 
     * @return 
     * 
     */  
    public static Object[] splitAry(byte[] ary, int subSize) {  
          int count = ary.length % subSize == 0 ? ary.length / subSize: ary.length / subSize + 1;  
  
          List<List<Byte>> subAryList = new ArrayList<List<Byte>>();  
  
          for (int i = 0; i < count; i++) {  
           int index = i * subSize;  
           List<Byte> list = new ArrayList<Byte>();  
           int j = 0;  
               while (j < subSize && index < ary.length) {  
                    list.add(ary[index++]);  
                    j++;  
               }  
           subAryList.add(list);  
          }  
            
          Object[] subAry = new Object[subAryList.size()];  
            
          for(int i = 0; i < subAryList.size(); i++){  
               List<Byte> subList = subAryList.get(i);  
               byte[] subAryItem = new byte[subList.size()];  
               for(int j = 0; j < subList.size(); j++){  
                   subAryItem[j] = subList.get(j);  
               }  
               subAry[i] = subAryItem;  
          }  
            
          return subAry;  
         }  
    
    public static void main(String[] args) throws EncoderException {
    	byte [] data = FileSizeUtil.getImageBinaryByUrl("http://localhost:8080/DSTAXI/upload/servicelicense/201511061406553284.png");
		System.out.println(data.length);
		System.out.println(FileSizeUtil.byte2hex(data));
		Object[] objdata = FileSizeUtil.splitAry(data, 512);
		int count = 0;
		for(Object obj : objdata){
			byte [] bytedata = (byte [])obj;
			System.out.println(bytedata.length);
			System.out.println(FileSizeUtil.byte2hex(bytedata));
			count = count + 1;
		}
		System.out.println(count);
	}
    
}
