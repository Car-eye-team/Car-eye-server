/**
* Description: car-eye车辆管理平台
* 文件名：BitchExportTms.java
* 版本信息：1.0
* 日期：2014-5-14
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.careye.constant.Constant;
import com.careye.system.domain.SysAuthLoginLog;




import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @项目名称：app
 * @类名称：BitchExportExcel
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-5-14 下午02:48:31
 * @修改人：zhangrong
 * @修改时间：2014-5-14 下午02:48:31
 * @修改备注：
 * @version 1.0
 */
public class BitchExportExcel {
	/**导出
	 * @param list 需要导出的记录列表
	 * @param type:导出类型，详情见ManageConstants.java
	 */
	@SuppressWarnings("unchecked")
	public static String export(List list,int type,HttpServletResponse response,String OneName,String SheetnName){
		try {
//			OutputStream os = response.getOutputStream();
//	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");// 定义输出类型  
//			WritableWorkbook workbook = Workbook.createWorkbook(os); 
//			
			FileOutputStream fileOut;
//			OneName = new String(OneName.getBytes(), "iso8859-1");
	        String filePath = ServletActionContext.getServletContext().getRealPath("/")+"upload/用户登陆日志报表.xls";
	        System.out.println(filePath);
	        fileOut  = new FileOutputStream(filePath);
			WritableWorkbook workbook = Workbook.createWorkbook(fileOut);
			
			WritableSheet sheet = workbook.createSheet(SheetnName, 0);
			//公共样式
			   //sheet.getSettings().setDefaultRowHeight(500);//设置行高
			   sheet.getSettings().setDefaultColumnWidth(15);//设置列宽 
	           sheet.getSettings().setShowGridLines(true);  //整个sheet中的网格线  
			   sheet.setRowView(0,600); //将第一行的高度设为600
			   sheet.setRowView(1,550); //将第二行的高度设为500
			//内容样式
			   WritableCellFormat wcf = new WritableCellFormat();
			   wcf.setBorder(Border.ALL, BorderLineStyle.THIN);// 设置边框线   
			   wcf.setVerticalAlignment(VerticalAlignment.CENTRE); //设置垂直居中
			   wcf.setWrap(true);//单元格的文字按照单元格的列宽来自动换行显示。 
			//第二行样式
			   WritableCellFormat wc = new WritableCellFormat();   
	           wc.setBackground(jxl.format.Colour.OLIVE_GREEN);// 设置单元格的背景颜色    
	           wc.setBorder(Border.ALL, BorderLineStyle.THIN);// 设置边框线     
	           wc.setVerticalAlignment(VerticalAlignment.CENTRE); //设置垂直居中
	           WritableFont f=new WritableFont(WritableFont.createFont("宋体"),12,WritableFont.BOLD
	        		   ,false,UnderlineStyle.NO_UNDERLINE,Colour.WHITE);
	           wc.setFont(f);
	          
	         //第一行样式
	           WritableCellFormat cf = new WritableCellFormat(); 
	           cf.setBackground(jxl.format.Colour.DARK_BLUE);
	           cf.setBorder(Border.ALL, BorderLineStyle.THIN);// 设置下边框线 
	           cf.setAlignment(Alignment.CENTRE); 
	           cf.setVerticalAlignment(VerticalAlignment.CENTRE); //设置垂直居中
	           cf.setFont(new WritableFont(WritableFont.createFont("宋体"),12,WritableFont.BOLD
	        		   ,false,UnderlineStyle.NO_UNDERLINE,Colour.WHITE));
	           
	           
	            /*if(type==Constant.USER_LOGINFO_EXPORT){
	        	   //系统操作日志报表
	        	   sheet.mergeCells(0, 0, 3, 0);//合并单元格
	        	   sheet.addCell(new Label(0,0,OneName,cf));//第一行内容
//	        	   List<LogInfo> terminalPurch= (List<LogInfo>)list;
	        	   sheet.setColumnView(0,20);  //将第1列的宽度设为25
	        	   sheet.setColumnView(2,80);  //将第3列的宽度设为25
	        	   sheet.setColumnView(3,20);  //将第4列的宽度设为25
	        	   
	        	   for (int i = 1; i <= list.size()+1; i++) {
	        		   if(i==1){
	        			   sheet.addCell(new Label(0,i,"操作用户",wc));
	        			   sheet.addCell(new Label(1,i,"操作类型",wc));
	        			   sheet.addCell(new Label(2,i,"日志内容",wc));
	        			   sheet.addCell(new Label(3,i,"操作时间",wc));
	        		   }else{
	        			   sheet.setRowView(i,450); //将行的高度设为450
	        			   sheet.addCell(new Label(0,i,terminalPurch.get(i-2).getLoginname(),wcf));
	        			   if (terminalPurch.get(i-2).getOperattype()==1) {
	        				   sheet.addCell(new Label(1,i,"添加",wcf));
	        			   } else if(terminalPurch.get(i-2).getOperattype()==2){
	        				   sheet.addCell(new Label(1,i,"修改",wcf));
	        			   }else if(terminalPurch.get(i-2).getOperattype()==4){
	        				   sheet.addCell(new Label(1,i,"导出",wcf));
	        			   }else if(terminalPurch.get(i-2).getOperattype()==5){
	        				   sheet.addCell(new Label(1,i,"导入",wcf));
	        			   }else{
	        				   sheet.addCell(new Label(1,i,"删除",wcf));
	        			   }
	        			   sheet.addCell(new Label(2,i,terminalPurch.get(i-2).getContent(),wcf));
	        			   sheet.addCell(new Label(3,i,terminalPurch.get(i-2).getCreatetime(),wcf));
	        		   }
	        	   }
	           }else*/
	        	   
	        	   if(type==Constant.USER_LOGINFO_EXPORT){
	        	   //系统操作日志报表
	        	   sheet.mergeCells(0, 0, 7, 0);//合并单元格
	        	   sheet.addCell(new Label(0,0,OneName,cf));//第一行内容
	        	   List<SysAuthLoginLog> data= (List<SysAuthLoginLog>)list;
	        	   sheet.setColumnView(2,20);  //将第1列的宽度设为25
	        	   sheet.setColumnView(3,20);  //将第3列的宽度设为25
	        	   sheet.setColumnView(7,20);  //将第4列的宽度设为25
	        	   
	        	   for (int i = 1; i <= list.size()+1; i++) {
	        		   if(i==1){
	        			   sheet.addCell(new Label(0,i,"用户登陆名",wc));
	        			   sheet.addCell(new Label(1,i,"用户姓名",wc));
	        			   sheet.addCell(new Label(2,i,"集团",wc));
	        			   sheet.addCell(new Label(3,i,"登陆时间",wc));
	        			   sheet.addCell(new Label(4,i,"登陆IP",wc));
	        			   sheet.addCell(new Label(5,i,"登陆状态",wc));
	        			   sheet.addCell(new Label(6,i,"状态",wc));
	        			   sheet.addCell(new Label(7,i,"备注",wc));
	        		   }else{
	        			   sheet.setRowView(i,450); //将行的高度设为450
	        			   sheet.addCell(new Label(0,i,data.get(i-2).getLoginname(),wcf));
	        			   sheet.addCell(new Label(1,i,data.get(i-2).getUsername(),wcf));
	        			   sheet.addCell(new Label(2,i,data.get(i-2).getBlocname(),wcf));
	        			   sheet.addCell(new Label(3,i,data.get(i-2).getLogindate(),wcf));
	        			   sheet.addCell(new Label(4,i,data.get(i-2).getLoginip(),wcf));
	        			   sheet.addCell(new Label(5,i,data.get(i-2).getLoginflag(),wcf));
	        			   if (data.get(i-2).getStatus()==1) {
	        				   sheet.addCell(new Label(6,i,"登陆",wcf));
	        			   } else if(data.get(i-2).getStatus()==2){
	        				   sheet.addCell(new Label(6,i,"退出",wcf));
	        			   }else{
	        				   sheet.addCell(new Label(6,i,"",wcf));
	        			   }
	        			   sheet.addCell(new Label(7,i,data.get(i-2).getRemark(),wcf));
	        		   }
	        	   }
	           }
	           
			workbook.write();
			workbook.close();
		    fileOut.close();
//			os.close();
			
			return filePath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
