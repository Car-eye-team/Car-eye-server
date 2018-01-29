/**
 * Description: car-eye车辆管理平台
 * 文件名：MultiMediaAction.Java
 * 版本信息：1.0
 * 日期：2015-3-26
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.monitor.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Encoder;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarDriver;
import com.careye.car.domain.CarInfo;
import com.careye.car.service.CarService;
import com.careye.constant.WebConstants;
import com.careye.message.domain.MultimediaSearchRecord;
import com.careye.monitor.domain.MultiMedia;
import com.careye.monitor.domain.OnlineRate;
import com.careye.monitor.domain.Photo;
import com.careye.monitor.service.MultiMediaService;
import com.careye.mq.HandleUtil;
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.ExportToWord;
import com.careye.utils.FileSizeUtil;
import com.careye.utils.ImageUtils;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：MultiMediaAction
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-26 下午07:38:25
 * @修改人：Yuqk
 * @修改时间：2015-3-26 下午07:38:25
 * @修改备注：
 * @version 1.0
 */
public class MultiMediaAction extends BasePageAction {
	private static final long serialVersionUID = -6536527247674646740L;
	private static final Logger logger = Logger
			.getLogger(MultiMediaAction.class);

	private Map result;
	private MultiMedia multiMedia;
	private List<MultiMedia> mlist = new ArrayList<MultiMedia>();
	private MultimediaSearchRecord multimediaSearchRecord;
	private MultiMediaService multiMediaService;
	private CarService carService;

	private String id;
	private String carid;

	private String type;
	private String carids;
	private String carnumber;
	private String mediatype;
	private String accessid;
	private String eventcode;
	private String stime;
	private String etime;
	private String stime1;
	private String etime1;
	private String flg;

	private String fileName;

	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 根据MSId查询多媒体数据的图片路径，便于显示图片
	 * 
	 * @return
	 */
	public String findMultiMediaByMSId() {
		try {
			initMap();
			if (id == null) {
				return ERROR;
			}
			if (SessionUtils.getUser() == null) {
				return SUCCESS;
			}
			if (multiMedia == null) {
				multiMedia = new MultiMedia();
			}
			multiMedia.setMsid(Integer.parseInt(id));
			List<MultiMedia> list = multiMediaService
					.queryMultiMediaByMSId(multiMedia);
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("MultiMediaAction的方法 queryMultiMediaByMSId执行出错，原因："
					+ e, e);
			return ERROR;
		}

	}

	/**
	 * 查询多媒体数据列表
	 * 
	 * @return
	 */
	public String findMultiMediaList() {

		try {
			initMap();
			if (multiMedia == null) {
				multiMedia = new MultiMedia();
			}
			if (SessionUtils.getUser() == null) {
				return SUCCESS;
			}
			if (!SessionUtils.getUser().getLoginname().equals("admin")) {
				multiMedia.setUserid(SessionUtils.getUserId());
			}

			if (StringUtils.isNotEmty(carnumber)) {
				multiMedia.setCarnumber(URLDecoder.decode(carnumber, "UTF-8")
						.toUpperCase().trim());
			}

			if (StringUtils.isNotEmty(mediatype)) {
				multiMedia.setMediatype(Integer.parseInt(mediatype));
			}

			if (StringUtils.isNotEmty(eventcode)) {
				multiMedia.setEventcode(Integer.parseInt(eventcode));
			}

			if (StringUtils.isNotEmty(stime)) {
				multiMedia.setStime(URLDecoder.decode(stime, "UTF-8"));
			}

			if (StringUtils.isNotEmty(etime)) {
				multiMedia.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			result = multiMediaService.queryMultiMediaList(this.getPage(), this
					.getLimit(), multiMedia);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("MultiMediaAction的方法 queryMultiMediaList执行出错，原因：" + e,
					e);
			return ERROR;
		}

	}

	/**
	 * Excel导出
	 * 
	 * @throws IOException
	 */
	public void exportMultiMediaList() {
		try {
			// 1.设置名字
			fileName = "多媒体事件上传报表";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,20,20,15,15,15,15,30,25";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			if (multiMedia == null) {
				multiMedia = new MultiMedia();
			}

			multiMedia.setUserid(SessionUtils.getUserId());
			if (SessionUtils.getUser() == null) {
				return;
			}
			if (!SessionUtils.getUser().getLoginname().equals("admin")) {
				multiMedia.setUserid(SessionUtils.getUserId());
			}

			if (StringUtils.isNotEmty(carnumber)) {
				multiMedia.setCarnumber(new String(carnumber
						.getBytes("ISO-8859-1"), "UTF-8").toUpperCase().trim());
			}

			if (StringUtils.isNotEmty(mediatype)) {
				multiMedia.setMediatype(Integer.parseInt(new String(mediatype
						.getBytes("ISO-8859-1"), "UTF-8")));
			}

			if (StringUtils.isNotEmty(eventcode)) {
				multiMedia.setEventcode(Integer.parseInt(new String(eventcode
						.getBytes("ISO-8859-1"), "UTF-8")));
			}

			if (StringUtils.isNotEmty(stime)) {
				multiMedia.setStime(new String(stime.getBytes("ISO-8859-1"),
						"UTF-8"));
			}

			if (StringUtils.isNotEmty(etime)) {
				multiMedia.setEtime(new String(etime.getBytes("ISO-8859-1"),
						"UTF-8"));
			}

			List<MultiMedia> list = multiMediaService
					.exportPhotoRecordList(multiMedia);

			titleRow.createCell(0).setCellValue("序号");
			titleRow.createCell(1).setCellValue("组织机构");
			titleRow.createCell(2).setCellValue("终端号码");
			titleRow.createCell(3).setCellValue("车牌号");
			titleRow.createCell(4).setCellValue("多媒体类型");
			titleRow.createCell(5).setCellValue("格式");
			titleRow.createCell(6).setCellValue("事件项编码");
			titleRow.createCell(7).setCellValue("通道ID");
			titleRow.createCell(8).setCellValue("创建时间");

			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.setHeightInPoints(15);
					MultiMedia data = (MultiMedia) list.get(i);
					contentRow.createCell(0).setCellValue(index);
					contentRow.createCell(1).setCellValue(data.getBlocname());
					contentRow.createCell(2).setCellValue(data.getTerminal());
					contentRow.createCell(3).setCellValue(data.getCarnumber());
					Integer media = data.getMediatype();
					String mString = null;
					if (media != null) {
						if (media == 0) {
							mString = "图像";
						} else if (media == 1) {
							mString = "音频";
						} else if (media == 2) {
							mString = "视频";
						}
					}
					contentRow.createCell(4).setCellValue(mString);

					if (data.getFormat() == null) {
						contentRow.createCell(5).setCellValue("");
					} else if (data.getFormat() == 0) {
						contentRow.createCell(5).setCellValue("JPEG");
					} else if (data.getFormat() == 1) {
						contentRow.createCell(5).setCellValue("TIF");
					} else if (data.getFormat() == 2) {
						contentRow.createCell(5).setCellValue("MP3");
					} else if (data.getFormat() == 3) {
						contentRow.createCell(5).setCellValue("WAV");
					} else if (data.getFormat() == 4) {
						contentRow.createCell(5).setCellValue("WMV");
					}
					if (data.getEventcode() == null) {
						contentRow.createCell(6).setCellValue("");
					} else if (data.getEventcode() == 0) {
						contentRow.createCell(6).setCellValue("平台下发指令");
					} else if (data.getEventcode() == 1) {
						contentRow.createCell(6).setCellValue("定时动作");
					} else if (data.getEventcode() == 2) {
						contentRow.createCell(6).setCellValue("抢劫报警触发");
					} else if (data.getEventcode() == 3) {
						contentRow.createCell(6).setCellValue("碰撞侧翻报警触发");
					}
					contentRow.createCell(7).setCellValue(data.getAccessid());
					contentRow.createCell(8).setCellValue(data.getCreatetime());
//					for (int m = 0; m < contentRow.getLastCellNum(); m++) {
//						contentRow.getCell(m).setCellStyle(
//								exceldownway.setBookListStyle(book));
//					}
				}
			}

			exceldownway.getCommonExcelListWay(book, fileName);

		} catch (Exception e) {
			try {
				getResponse()
						.getWriter()
						.print(
								"<script language=javascript>alert('Error!');history.back();</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 多媒体检索指令
	 * @return
	 */
	public String searchSet() {
		try {
			
			if(StringUtils.isEmty(carids)){
				return ERROR;
			}
			
			if(StringUtils.isNotEmty(stime)){
				stime = URLDecoder.decode(stime,"UTF-8");
				stime1 = DateUtil.dateToNumber(stime);
			}else{
				stime1 = "000000000000";
			}
				
			if(StringUtils.isNotEmty(etime)){
				etime = URLDecoder.decode(etime,"UTF-8");
				etime1 = DateUtil.dateToNumber(etime);
			}else{
				etime1 = "000000000000";
			}
			
			
			for (String carid : carids.split(",")) {
				CarInfo carInfo = new CarInfo();
				if (StringUtils.isNotEmty(carid)) {
					carInfo = carService.getCarInfoCarId(Integer.parseInt(carid));
				}
				int seq = HandleUtil.getSerialId();
				String data = "";
				if(type.equals("1")){
					data = HandleUtil.multimediaDataRetrieval(carInfo.getUsertype(), carInfo.getTerminal(), Integer.parseInt(mediatype),
							Integer.parseInt(accessid), Integer.parseInt(eventcode), stime1, etime1, seq);
				}else{
					data = HandleUtil.multimediaDataUpload(carInfo.getUsertype(), carInfo.getTerminal(), Integer.parseInt(mediatype),
							Integer.parseInt(accessid), Integer.parseInt(eventcode), stime1, etime1,Integer.parseInt(flg), seq);
				}
				 
				// 指令发送完后添加记录
				MultimediaSearchRecord search = new MultimediaSearchRecord();
				search.setBlocid(carInfo.getBlocid());
				search.setUserid(SessionUtils.getUserId());
				search.setAccessid(Integer.parseInt(accessid));
				search.setEventcode(Integer.parseInt(eventcode));
				search.setStime(stime);
				search.setEtime(etime);
				search.setDel(Integer.parseInt(flg));
				search.setMultimediatype(Integer.parseInt(mediatype));
				search.setType(Integer.parseInt(type));
				search.setCarid(Integer.parseInt(carid));
				search.setCreatetime(DateUtil.getSQLDate());
				search.setSeq(seq);
				search.setData(data);
				multiMediaService.addMultimediaSearchRecord(search);
			}
		} catch (Exception e) {
			logger.error("PhotoRecordAction 的方法 searchSet方法执行出错，原因：" + e, e);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	/**
	 * 查询多媒体下发记录
	 * 
	 * @return
	 */
	public String findMultimediaSearchRecordList() {
		try {
			initMap();
			if (multimediaSearchRecord == null) {
				multimediaSearchRecord = new MultimediaSearchRecord();
			}

			multimediaSearchRecord.setUserid(SessionUtils.getUserId());
			if (SessionUtils.getUser() == null) {
				return SUCCESS;
			}
			if (StringUtils.isNotEmty(carnumber)) {
				multimediaSearchRecord.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			if (StringUtils.isNotEmty(mediatype)) {
				multimediaSearchRecord.setMultimediatype(Integer.parseInt(mediatype));
			}
			if (StringUtils.isNotEmty(eventcode)) {
				multimediaSearchRecord.setEventcode(Integer.parseInt(eventcode));
			}
			if (StringUtils.isNotEmty(stime)) {
				multimediaSearchRecord.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				multimediaSearchRecord.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			result = multiMediaService.queryMultimediaSearchRecordList(this.getPage(), this.getLimit(), multimediaSearchRecord);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("PhotoRecordAction的方法 findMultimediaSearchRecordList执行出错，原因：" + e,
					e);
			return ERROR;
		}

	}
	
	/**
	 * Excel导出多媒体下发记录
	 * 
	 * @throws IOException
	 */
	public void exportMultimediaSearchRecord() {
		try {
			// 1.设置名字
			fileName = "多媒体下发记录报表";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,20,20,15,15,15,15,25,25,25,25,25";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			if (multimediaSearchRecord == null) {
				multimediaSearchRecord = new MultimediaSearchRecord();
			}

			multimediaSearchRecord.setUserid(SessionUtils.getUserId());
			if (SessionUtils.getUser() == null) {
				return;
			}

			if (StringUtils.isNotEmty(carnumber)) {
				multimediaSearchRecord.setCarnumber(new String(carnumber
						.getBytes("ISO-8859-1"), "UTF-8").toUpperCase().trim());
			}
			if (StringUtils.isNotEmty(mediatype)) {
				multimediaSearchRecord.setMultimediatype(Integer.parseInt(mediatype));
			}
			if (StringUtils.isNotEmty(eventcode)) {
				multimediaSearchRecord.setEventcode(Integer.parseInt(eventcode));
			}
			if (StringUtils.isNotEmty(stime)) {
				multimediaSearchRecord.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				multimediaSearchRecord.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}

			List<MultimediaSearchRecord> list = multiMediaService.exportMultimediaSearchRecordList(multimediaSearchRecord);

			titleRow.createCell(0).setCellValue("序号");
			titleRow.createCell(1).setCellValue("组织机构");
			titleRow.createCell(2).setCellValue("终端号码");
			titleRow.createCell(3).setCellValue("车牌号");
			titleRow.createCell(4).setCellValue("多媒体类型");
			titleRow.createCell(5).setCellValue("通道ID");
			titleRow.createCell(6).setCellValue("事件项编码");
			titleRow.createCell(7).setCellValue("起始时间");
			titleRow.createCell(8).setCellValue("结束时间");
			titleRow.createCell(9).setCellValue("创建时间");
			titleRow.createCell(10).setCellValue("处理结果");
			titleRow.createCell(11).setCellValue("序列号");

			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.setHeightInPoints(15);
					MultimediaSearchRecord data = (MultimediaSearchRecord) list.get(i);
					contentRow.createCell(0).setCellValue(index);
					contentRow.createCell(1).setCellValue(data.getBlocname());
					contentRow.createCell(2).setCellValue(data.getTerminal());
					contentRow.createCell(3).setCellValue(data.getCarnumber());
					Integer media = data.getMultimediatype();
					String mString = null;
					if (media != null) {
						if (media == 0) {
							mString = "图像";
						} else if (media == 1) {
							mString = "音频";
						} else if (media == 2) {
							mString = "视频";
						}
					}
					contentRow.createCell(4).setCellValue(mString);
					if (data.getAccessid() == null) {
						contentRow.createCell(5).setCellValue("");
					} else {
						contentRow.createCell(5).setCellValue("通道"+data.getAccessid());
					}
					if (data.getEventcode() == null) {
						contentRow.createCell(6).setCellValue("");
					} else if (data.getEventcode() == 0) {
						contentRow.createCell(6).setCellValue("平台下发指令");
					} else if (data.getEventcode() == 1) {
						contentRow.createCell(6).setCellValue("定时动作");
					} else if (data.getEventcode() == 2) {
						contentRow.createCell(6).setCellValue("抢劫报警触发");
					} else if (data.getEventcode() == 3) {
						contentRow.createCell(6).setCellValue("碰撞侧翻报警触发");
					} else if (data.getEventcode() == 4) {
						contentRow.createCell(6).setCellValue("进入重车拍照（抬表）");
					} else if (data.getEventcode() == 5) {
						contentRow.createCell(6).setCellValue("进入空车拍照（压表）");
					} else if (data.getEventcode() == 6) {
						contentRow.createCell(6).setCellValue("服务评价拍照");
					}
					contentRow.createCell(7).setCellValue(data.getStime());
					contentRow.createCell(8).setCellValue(data.getEtime());
					contentRow.createCell(9).setCellValue(data.getCreatetime());
					if (data.getResult() == null
							|| (!data.getResult().equals(1) && !data
									.getResult().equals(2))) {
						contentRow.createCell(10).setCellValue("");
					} else if (data.getResult().equals(1)) {
						contentRow.createCell(10).setCellValue("成功");
					} else if (data.getResult().equals(2)) {
						contentRow.createCell(10).setCellValue("失败");
					}
					contentRow.createCell(11).setCellValue(data.getSeq());
					
					for (int m = 0; m < contentRow.getLastCellNum(); m++) {
						contentRow.getCell(m).setCellStyle(
								exceldownway.setBookListStyle(book));
					}
				}
			}

			exceldownway.getCommonExcelListWay(book, fileName);

		} catch (Exception e) {
			try {
				getResponse()
						.getWriter()
						.print(
								"<script language=javascript>alert('Error!');history.back();</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 查询多媒体拍照列表
	 * 
	 * @return
	 */
	public String queryCarPhotos() {

		try {
			initMap();
			if (multiMedia == null) {
				multiMedia = new MultiMedia();
			}
			if (SessionUtils.getUser() == null) {
				return SUCCESS;
			}

			if (StringUtils.isNotEmty(carid)) {
				multiMedia.setCarid(Integer.parseInt(carid));
			}
			if (StringUtils.isNotEmty(eventcode)) {
				multiMedia.setEventcode(Integer.parseInt(eventcode));
			}

			if (StringUtils.isNotEmty(stime)) {
				multiMedia.setStime(URLDecoder.decode(stime, "UTF-8"));
			}

			if (StringUtils.isNotEmty(etime)) {
				multiMedia.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			List list = multiMediaService.queryCarPhotos(multiMedia);
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("MultiMediaAction的方法 queryCarPhotos执行出错，原因：" + e,
					e);
			return ERROR;
		}

	}
	
	/**
	 * 导出word文档
	 * @return
	 */
	public void exportWord(){
		try {	
			initMap();
			
			Map<String,Object> dataMap=new HashMap<String,Object>();
			
			dataMap.put("title", "车辆上传照片查询信息");
			
			String st = DateUtil.getSQLDate();
		    String y = st.substring(0, 4);
		    String m = st.substring(5, 7);
		    String today = st.substring(8, 10);
			
		    if (multiMedia == null) {
				multiMedia = new MultiMedia();
			}
			if (SessionUtils.getUser() == null) {
				return;
			}
			if (StringUtils.isNotEmty(carid)) {
				multiMedia.setCarid(Integer.parseInt(carid));
//				String bname = multiMediaService.getCaridByBname(Integer.parseInt(carid));
//				String bcarnumber = multiMediaService.getCaridByBcarnumber(Integer.parseInt(carid));
				dataMap.put("bname", "企业【"+multiMediaService.getCaridByBname(Integer.parseInt(carid))+"】,");
				dataMap.put("bcarnumber", "车牌号为:"+multiMediaService.getCaridByBcarnumber(Integer.parseInt(carid))+",");
			}else{
				dataMap.put("bname", "");
				dataMap.put("bcarnumber", "");
			}
			if (StringUtils.isNotEmty(eventcode)) {
				multiMedia.setEventcode(Integer.parseInt(eventcode));
			}

			if (StringUtils.isNotEmty(stime)) {
				multiMedia.setStime(URLDecoder.decode(stime, "UTF-8"));
			}

			if (StringUtils.isNotEmty(etime)) {
				multiMedia.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			if(StringUtils.isNotEmty(multiMedia.getStime()) && StringUtils.isNotEmty(multiMedia.getEtime())){
				dataMap.put("btime", multiMedia.getStime()+"到"+multiMedia.getEtime()+",");
			}else if(StringUtils.isEmty(multiMedia.getStime()) && StringUtils.isNotEmty(multiMedia.getEtime())){
				dataMap.put("btime", "时间截止到"+multiMedia.getEtime()+",");
			}else if(StringUtils.isNotEmty(multiMedia.getStime()) && StringUtils.isEmty(multiMedia.getEtime())){
				dataMap.put("btime", "从"+multiMedia.getStime()+"开始,");
			}else{
				dataMap.put("btime", y+"年"+m+"月"+today+"日");
			}
			dataMap.put("bremark", "车辆上传照片查询数据如下：");
			
			mlist = multiMediaService.queryCarPhotos(multiMedia);
			if(mlist != null){
				for(MultiMedia info : mlist ){
					if(info.getEventcode() == 0){
						info.setEventcodeStr("平台下发指令");
					}else if(info.getEventcode() == 1){
						info.setEventcodeStr("定时动作");
					}else if(info.getEventcode() == 2){
						info.setEventcodeStr("抢劫报警触发");
					}else if(info.getEventcode() == 3){
						info.setEventcodeStr("碰撞侧翻报警触发");
					}else if(info.getEventcode() == 4){
						info.setEventcodeStr("司机上班签到");
					}else if(info.getEventcode() == 5){
						info.setEventcodeStr("司机下班签退");
					}else if(info.getEventcode() == 6){
						info.setEventcodeStr("空车转重车");
					}else if(info.getEventcode() == 7){
						info.setEventcodeStr("重车转空车");
					}
					
					if(info.getMultimediapath() != null){
						String urlStr = info.getMultimediapath();
						byte[] b = FileSizeUtil.getImageBinaryByUrl(urlStr);
//						String imgUrla = FileSizeUtil.byte2hex(b);
						String imgUrla = new BASE64Encoder().encode(b);
						
						
//						String img = getImageStr("C:/Users/Administrator/Desktop/pic_16527175215.jpg");  
						info.setMultimediapath(imgUrla);
					}
				}
			}
			
			
			dataMap.put("list",mlist);
			
			ExportToWord.getInstance().exportWord(dataMap,WebConstants.FM_CARPHOTO_WORD,"车辆上传照片信息");
			
		} catch (Exception e) {
				logger.error("MultiMediaAction的方法 exportWord执行出错，原因：" + e, e);
		}
		
	}
	
	
	
	
	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public MultiMedia getMultiMedia() {
		return multiMedia;
	}

	public void setMultiMedia(MultiMedia multiMedia) {
		this.multiMedia = multiMedia;
	}

	public MultiMediaService getMultiMediaService() {
		return multiMediaService;
	}

	public void setMultiMediaService(MultiMediaService multiMediaService) {
		this.multiMediaService = multiMediaService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getMediatype() {
		return mediatype;
	}

	public void setMediatype(String mediatype) {
		this.mediatype = mediatype;
	}

	public String getEventcode() {
		return eventcode;
	}

	public void setEventcode(String eventcode) {
		this.eventcode = eventcode;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCarids() {
		return carids;
	}

	public void setCarids(String carids) {
		this.carids = carids;
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public String getAccessid() {
		return accessid;
	}

	public void setAccessid(String accessid) {
		this.accessid = accessid;
	}

	public MultimediaSearchRecord getMultimediaSearchRecord() {
		return multimediaSearchRecord;
	}

	public void setMultimediaSearchRecord(
			MultimediaSearchRecord multimediaSearchRecord) {
		this.multimediaSearchRecord = multimediaSearchRecord;
	}

	public String getStime1() {
		return stime1;
	}

	public void setStime1(String stime1) {
		this.stime1 = stime1;
	}

	public String getEtime1() {
		return etime1;
	}

	public void setEtime1(String etime1) {
		this.etime1 = etime1;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	

}
