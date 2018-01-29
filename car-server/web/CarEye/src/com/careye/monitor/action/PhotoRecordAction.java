/**
 * Description: car-eye车辆管理平台
 * 文件名：PhotoSetAction.java
 * 版本信息：1.0
 * 日期：2015-3-25
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.monitor.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarInfo;
import com.careye.car.service.CarService;
import com.careye.monitor.domain.Photo;
import com.careye.monitor.service.MultiMediaService;
import com.careye.monitor.service.PhotoRecordService;
import com.careye.mq.HandleUtil;
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;
import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：PhotoRecordAction
 * @类描述：拍照设置 拍照记录Action
 * @创建人：Yuqk
 * @创建时间：2015-3-25 上午11:54:21
 * @修改人：Yuqk
 * @修改时间：2015-3-25 上午11:54:21
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PhotoRecordAction extends BasePageAction {
	private static final Logger logger = Logger
			.getLogger(PhotoRecordAction.class);
	
	private PhotoRecordService photoRecordService;
	private MultiMediaService multiMediaService;
	private CarService carService;
	/**
	 * 照片javaBean
	 */
	private Photo photo;
	/**
	 * 需要拍照的车辆ids，以逗号连接
	 */
	private String carids;
	/*
	 * 部分照片记录参数,也是部分查询条件
	 */
	private String carnumber;
	private String stime;
	private String etime;
	/*
	 * 相机参数，部分照片记录参数
	 */
	private Integer channel;// 通道id
	private String cmd;// 拍摄命令command
	private Integer inv;// pstime
	private String save;// 实时上传还是保存
	private Integer screen;// 分辨率
	private Integer quality;// 图像质量
	private Integer bright;// 亮度
	private Integer contrast;// 对比度
	private Integer saturation;// 饱和度
	private Integer chroma;// 色度
	
	private Map result;
	private String success;

	private String fileName;

	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 拍照设置并下发指令开始拍照
	 * 
	 * @return
	 */
	public String takePhotos() {
		try {
			for (String carid : carids.split(",")) {
				CarInfo carInfo = new CarInfo();
				if (StringUtils.isNotEmty(carid)) {
					carInfo = carService.getCarInfoCarId(Integer
							.parseInt(carid));
				}
				carnumber = carInfo.getCarnumber();
				int seq = HandleUtil.getSerialId();
				String data = HandleUtil.remoteCamera(carInfo.getUsertype(),carInfo.getTerminal(),
						channel, Integer.parseInt(cmd), inv, Integer
								.parseInt(save), screen, quality, bright,
						contrast, saturation, chroma,seq,
						carnumber);
				// 指令发送完后添加记录
				Photo photo = new Photo();
				photo.setBlocid(carInfo.getBlocid());
				photo.setUserid(SessionUtils.getUserId());
				photo.setChannel(channel);
				photo.setCommand(Integer.parseInt(cmd));
				photo.setPstime(inv);
				photo.setSaveflag(Integer.parseInt(save));
				photo.setResolutionratio(screen);
				photo.setPicturequality(quality);
				photo.setLighteness(bright);
				photo.setContrast(contrast);
				photo.setSaturation(saturation);
				photo.setChroma(chroma);
				photo.setCarid(Integer.parseInt(carid));
				photo.setCreatetime(DateUtil.getSQLDate());
				photo.setSeq(seq);
				photo.setData(data);
				photoRecordService.addPhotoRecord(photo);
			}
		} catch (Exception e) {
			logger.error("PhotoRecordAction 的方法 takePhotos方法执行出错，原因：" + e, e);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 查询摄像头立即拍照记录
	 * 
	 * @return
	 */
	public String findPhotoRecordList() {
		try {
			initMap();
			if (photo == null) {
				photo = new Photo();
			}
			if (SessionUtils.getUser() == null) {
				return SUCCESS;
			}
			if (!SessionUtils.getUser().getLoginname().equals("admin")) {
				photo.setUserid(SessionUtils.getUserId());
			}
			if (StringUtils.isNotEmty(carnumber)) {
				photo.setCarnumber(URLDecoder.decode(carnumber, "UTF-8")
						.toUpperCase().trim());
			}
			if (StringUtils.isNotEmty(cmd)) {
				photo.setCommand(Integer.parseInt(cmd));
			}
			if (StringUtils.isNotEmty(save)) {
				photo.setSaveflag(Integer.parseInt(save));
			}
			if (StringUtils.isNotEmty(stime)) {
				photo.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				photo.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			result = photoRecordService.findPageRecordList(this.getPage(), this
					.getLimit(), photo);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("PhotoRecordAction的方法 findPageRecordList执行出错，原因：" + e,
					e);
			return ERROR;
		}

	}

	public void exportPhotoRecordList() {
		try {
		     //1.设置名字
			 fileName = "拍摄记录报表";
	    	 HSSFWorkbook book = new HSSFWorkbook();
	         Sheet sheet= book.createSheet(fileName);
	         //sheet.setDefaultColumnWidth(15);
	         
	         ExcelDownWay exceldownway= new ExcelDownWay();
	         
	         //2.设置列宽（列数要对应上）
	         String str="7,15,25,20,15,15,20,15,15,20,15,15,20,30,20";
	         List<String> numberList=Arrays.asList(str.split(","));
	         sheet= exceldownway.setColumnWidth(sheet,numberList);
	     	 String[] resolutionratioArray = { "320*210", "640*480", "800*600",
					"1024*768", "176*144", "352*288", "704*288", "701*576" };
	     	
	         sheet.setDefaultRowHeight((short) 18);
	         Row titleRow= sheet.createRow(0);
	         titleRow.setHeightInPoints(20);
	         if (photo == null) {
					photo = new Photo();
				}
				if (SessionUtils.getUser() == null) {
					return;
				}
				if (!SessionUtils.getUser().getLoginname().equals("admin")) {
					photo.setUserid(SessionUtils.getUserId());
				}
				if (StringUtils.isNotEmty(carnumber)) {
					photo.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"),
							"utf-8").toUpperCase().trim());
				}
				if (StringUtils.isNotEmty(cmd)) {
					photo.setCommand(Integer.parseInt(new String(cmd
							.getBytes("ISO-8859-1"), "utf-8")));
				}
				if (StringUtils.isNotEmty(save)) {
					photo.setSaveflag(Integer.parseInt(new String(save
							.getBytes("ISO-8859-1"), "utf-8")));
				}
				if (StringUtils.isNotEmty(stime)) {
					photo
							.setStime(new String(stime.getBytes("ISO-8859-1"),
									"utf-8"));
				}
				if (StringUtils.isNotEmty(etime)) {
					photo
							.setEtime(new String(etime.getBytes("ISO-8859-1"),
									"utf-8"));
				}
				List<Photo> list = photoRecordService.exportPhotoRecordList(photo);
	         
				titleRow.createCell(0).setCellValue("序号");
				titleRow.createCell(1).setCellValue("集团");
				titleRow.createCell(2).setCellValue("终端号码");
				titleRow.createCell(3).setCellValue("车牌号");
				titleRow.createCell(4).setCellValue("拍摄命令");
				titleRow.createCell(5).setCellValue("拍摄间隔/录像时间");
				titleRow.createCell(6).setCellValue("保存标志");
				titleRow.createCell(7).setCellValue("分辨率");
				titleRow.createCell(8).setCellValue("图像/视频质量");
				titleRow.createCell(9).setCellValue("亮度");
				titleRow.createCell(10).setCellValue("对比度");
				titleRow.createCell(11).setCellValue("饱和度");
				titleRow.createCell(12).setCellValue("色度");
				titleRow.createCell(13).setCellValue("处理结果");
				titleRow.createCell(14).setCellValue("创建时间");
	         
	         for(int i=0;i<titleRow.getLastCellNum();i++){
	        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
	         }
	         
	         
	         if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						int index = i + 1;
						Row contentRow = sheet.createRow(index);
						contentRow.setHeightInPoints(15);
						Photo data = (Photo) list.get(i);
						contentRow.createCell(0).setCellValue(index);
						contentRow.createCell(1).setCellValue(data.getBlocname());
						contentRow.createCell(2).setCellValue(data.getTerminal());
						contentRow.createCell(3).setCellValue(data.getCarnumber());
						if (data.getCommand() == null) {
							contentRow.createCell(4).setCellValue("");
						} else if (data.getCommand().equals(0)) {
							contentRow.createCell(4).setCellValue("停止拍摄");
						} else if (data.getCommand().equals(65535)) {
							contentRow.createCell(4).setCellValue("录像");
						} else {
							contentRow.createCell(4).setCellValue(
									data.getCommand() + "张");
						}
						contentRow.createCell(5).setCellValue(data.getPstime());
						if (data.getSaveflag() == null
								|| (!data.getSaveflag().equals(0) && !data
										.getSaveflag().equals(1))) {
							contentRow.createCell(6).setCellValue("");
						} else if (data.getSaveflag().equals(0)) {
							contentRow.createCell(6).setCellValue("实时上传");
						} else if (data.getSaveflag().equals(1)) {
							contentRow.createCell(6).setCellValue("保存");
						}
						for (int j = 0, len = resolutionratioArray.length; j < len; j++) {
							if ((data.getResolutionratio()).equals(j + 1)) {
								contentRow.createCell(7).setCellValue(
										resolutionratioArray[j]);
								break;
							} else if (j == len - 1) {
								contentRow.createCell(7).setCellValue("");
							}
						}
						contentRow.createCell(8).setCellValue(
								data.getPicturequality());
						contentRow.createCell(9).setCellValue(data.getLighteness());
						contentRow.createCell(10).setCellValue(data.getContrast());
						contentRow.createCell(11)
								.setCellValue(data.getSaturation());
						contentRow.createCell(12).setCellValue(data.getChroma());
						if (data.getResult() == null
								|| (!data.getResult().equals(1) && !data
										.getResult().equals(2))) {
							contentRow.createCell(13).setCellValue("");
						} else if (data.getResult().equals(1)) {
							contentRow.createCell(13).setCellValue("成功");
						} else if (data.getResult().equals(2)) {
							contentRow.createCell(13).setCellValue("失败");
						}
						contentRow.createCell(14)
								.setCellValue(data.getCreatetime());
						for(int m=0;m<contentRow.getLastCellNum();m++){
		                	 contentRow.getCell(m).setCellStyle(exceldownway.setBookListStyle(book));
		                 }
					}
				}
	         
	         exceldownway.getCommonExcelListWay(book,fileName);
	         
		} catch (Exception e) {
			try {
				getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	
	
	public PhotoRecordService getPhotoRecordService() {
		return photoRecordService;
	}

	public void setPhotoRecordService(PhotoRecordService photoRecordService) {
		this.photoRecordService = photoRecordService;
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public String getCarids() {
		return carids;
	}

	public void setCarids(String carids) {
		this.carids = carids;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
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

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public Integer getInv() {
		return inv;
	}

	public void setInv(Integer inv) {
		this.inv = inv;
	}

	public String getSave() {
		return save;
	}

	public void setSave(String save) {
		this.save = save;
	}

	public Integer getScreen() {
		return screen;
	}

	public void setScreen(Integer screen) {
		this.screen = screen;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Integer getBright() {
		return bright;
	}

	public void setBright(Integer bright) {
		this.bright = bright;
	}

	public Integer getContrast() {
		return contrast;
	}

	public void setContrast(Integer contrast) {
		this.contrast = contrast;
	}

	public Integer getSaturation() {
		return saturation;
	}

	public void setSaturation(Integer saturation) {
		this.saturation = saturation;
	}

	public Integer getChroma() {
		return chroma;
	}

	public void setChroma(Integer chroma) {
		this.chroma = chroma;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MultiMediaService getMultiMediaService() {
		return multiMediaService;
	}

	public void setMultiMediaService(MultiMediaService multiMediaService) {
		this.multiMediaService = multiMediaService;
	}
}
