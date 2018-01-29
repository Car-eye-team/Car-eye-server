package com.careye.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;


public class FileHandler {

	private static Random random = new Random();
	/**
	 * 得到随机生成文件名，防止保存文件时重复
	 * 当前时间加随机数形式
	 * @return
	 */
	public static String getRandomFileName() {

		return DateUtil.fomatDate(DateUtil.getSQLDate()) + random.nextInt(10000);

	}
	/**
	 * 得到文件扩展名,包含.
	 * @return
	 */
	public static String getFileExtName(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));

	}

	/**
	 * 文件移动
	 * @param filePathSrc   绝对路径名
	 * @param filePathDes
	 */
	public static void moveFileTo(String filePathSrc, String filePathDest) {
		File tmpFile = new File(filePathSrc);
		File saveFile = new File(filePathDest);
		tmpFile.renameTo(saveFile);

	}

	/**
	 * 文件移动 
	 * @param src   相对于tomcat 路径
	 * @param dest 
	 */
	public static void moveFileWithRelativePath(String src, String dest) {
		ServletContext sc = ServletActionContext.getServletContext();
		if (sc != null) {
			src = sc.getRealPath("") + src;
			dest = sc.getRealPath("") + dest;
			moveFileTo(src, dest);
		}
	}


	/**
	 * 删除文件
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String filePath,String fileName) {

		File file = new File(filePath+fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} else {
			file.delete();
			return true;
		}
	}
	/**
	 * 删除文件
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String path) {
		
		File file = new File(path);
		if(path.indexOf("resource") != -1){
			return false;
		}
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} else {
			file.delete();
			return true;
		}
	}

	/**
	 * 复制到当前文件夹路径并且重命名
	 * @param path
	 * @param fileName
	 * @throws IOException
	 */
	public static void copyAndRenameFile(String filePath,String fileName,int type)throws IOException{

		try
		{
			FileOutputStream fs = null;
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(filePath+fileName);
			if ( oldfile.exists() )
			{ //文件存在时
				InputStream inStream = new FileInputStream(filePath+fileName ); //读入原文件
				if(type >= 3){
					return;
				}
				switch (type){
				case 1:
					fs = new FileOutputStream(filePath+"Dss_56gate.apk");
					break;
				case 2:
					fs = new FileOutputStream(filePath+"Driver_56gate.apk");
					break;
				}

				byte[] buffer = new byte[ 1444 ];
				int length;
				while ( ( byteread = inStream.read( buffer ) ) != -1 )
				{
					bytesum += byteread; //字节数 文件大小
					fs.write( buffer, 0, byteread );
				}
				inStream.close();
			}
			fs.close();
		}
		catch ( Exception e )
		{
			System.out.println( "复制单个文件操作出错" );
			e.printStackTrace();

		}

	}
	
}




