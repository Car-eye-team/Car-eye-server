package com.careye.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Sheet;

import com.careye.base.action.BasePageAction;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-10 下午03:42:58
 * @修改人：huangqin
 * @修改时间：2015-3-10 下午03:42:58
 * @修改备注：
 * @version 1.0
 */
public class ExcelDownWay  extends BasePageAction{
	
	/**公共的Excel导出方法**/
	public  void  getCommonExcelListWay(HSSFWorkbook book,String fileName){
		try{
			 fileName=javax.mail.internet.MimeUtility.encodeText(fileName,  "UTF-8" ,  "B" ); 
		     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	         book.write(outStream);
	         InputStream excel=new ByteArrayInputStream(outStream.toByteArray());
	         
	         // 以流的形式下载文件。  
	         InputStream fis = new BufferedInputStream(excel);  
	         byte[] buffer = new byte[fis.available()];  
	         fis.read(buffer);  
	         fis.close(); 
	         HttpServletRequest request=getRequest();
	         HttpServletResponse response=getResponse();
	         response.setCharacterEncoding("UTF-8");
	         // 清空response  
	         response.reset();  
	         // 设置response的Header  
	         response.addHeader("Content-Disposition", "attachment;filename="  
	                 + new String(fileName+".xls"));  
	         response.addHeader("Content-Length", "");  
	         OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
	         response.setContentType("application/vnd.ms-excel;charset=utf-8");  
	         toClient.write(buffer);  
	         toClient.flush();  
	         toClient.close();  
	} catch (Exception e) {
	}
	         
	}
	
	
	/**
	 * 设置列宽
	 * @param sheet
	 * @param numberList
	 * @return
	 */
	public Sheet  setColumnWidth(Sheet sheet,List <String> numberList){
		 for(int i=0;i<numberList.size();i++){
			 sheet.setColumnWidth(i,200 *Integer.parseInt(numberList.get(i)));
		 }
		 return sheet;
	}
	
	/**
	 * 设置标题部分
	 * @param book
	 * @return
	 */
	public  HSSFCellStyle  setBookHeadStyle(HSSFWorkbook book){
		 HSSFCellStyle headerStyle = (HSSFCellStyle) book.createCellStyle();// 设置字体样式  
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        HSSFFont headerFont = (HSSFFont) book.createFont();  
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗  
        headerFont.setFontName("Times New Roman");  
        headerFont.setFontHeightInPoints((short)10);  
        headerStyle.setFont(headerFont);  
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 
        headerStyle.setFillBackgroundColor((short)10);
        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerStyle.setFillForegroundColor(new HSSFColor.GREEN().getIndex());
        headerStyle.setFillBackgroundColor(new HSSFColor.GREEN().getIndex());
        return headerStyle;
	}
	 
	
	/**
	 * 设置列表部分
	 * @param book
	 * @return
	 */
	public  HSSFCellStyle  setBookListStyle(HSSFWorkbook book){
		 HSSFCellStyle headerStyle = (HSSFCellStyle) book.createCellStyle();// 设置字体样式  
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        HSSFFont headerFont = (HSSFFont) book.createFont();  
        headerFont.setFontName("Times New Roman");  
        headerFont.setFontHeightInPoints((short)10);  
        headerStyle.setFont(headerFont);  
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 
        headerStyle.setFillBackgroundColor((short)8);
        return headerStyle;
	}
	 
	
	
}
