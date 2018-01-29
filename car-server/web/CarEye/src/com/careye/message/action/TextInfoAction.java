/**
* Description: car-eye车辆管理平台
* 文件名：TextInfoAction.java
* 版本信息：1.0
* 日期：2014-5-26
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarInfo;
import com.careye.car.service.CarService;
import com.careye.common.service.MenuTreeService;
import com.careye.message.domain.SendRecord;
import com.careye.message.domain.TextInfo;
import com.careye.message.service.TextInfoService;
import com.careye.mq.HandleUtil;
import com.careye.system.domain.BlocUser;
import com.careye.system.domain.UserCar;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：FMS
 * @类名称：TextInfoAction
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-26 下午01:47:32
 * @修改人：lxh
 * @修改时间：2014-5-26 下午01:47:32
 * @修改备注：
 * @version 1.0
 */
public class TextInfoAction extends BasePageAction{
	private static final Logger logger = Logger.getLogger(TextInfoAction.class);
	private TextInfoService textInfoService;
	private MenuTreeService menuTreeService;
	private CarService carService;
	
	private TextInfo textInfo;
	private SendRecord sendRecord;
	private CarInfo carInfo;
	
	private Map result;
	private String success;
	private List list;
	private String ids;
	private String tes;
	private BlocUser user;
	private int su;
	
	private String carnumber;
	private String content;
	private String textinfotype;
	private String terminal;
	private String deptid;
	private String blocid;
	private String stime;
	private String etime;
	private String opertype;
	private int type;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	/**
	 * 分页查询车辆信息
	 * @return
	 */
	public String queryCarInfoList() {
		
		try {
			initMap();
			if(carInfo==null){
				carInfo=new CarInfo();
			}
			
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				carInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}
			if(terminal!=null&&!terminal.equals("")&&!terminal.equals("null")){
				carInfo.setTerminal(URLDecoder.decode(terminal,"UTF-8"));
			}
			if(StringUtils.isNotEmty(deptid)){
				carInfo.setBlocid(Integer.parseInt(deptid));
			}
			if(StringUtils.isNotEmty(blocid)){
				carInfo.setBlocid(Integer.parseInt(blocid));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				carInfo.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(stime)){
				carInfo.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				carInfo.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			result=carService.queySomeCarInfo(this.getPage(),this.getLimit(), carInfo);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TextInfoAction的方法 queryCarInfoList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 分页查询LED车辆信息
	 * @return
	 */
	public String queryLedCarList() {
		
		try {
			initMap();
			if(carInfo==null){
				carInfo=new CarInfo();
			}
			
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				carInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}
			if(terminal!=null&&!terminal.equals("")&&!terminal.equals("null")){
				carInfo.setTerminal(URLDecoder.decode(terminal,"UTF-8"));
			}
			if(StringUtils.isNotEmty(deptid)){
				carInfo.setBlocid(Integer.parseInt(deptid));
			}
			if(StringUtils.isNotEmty(blocid)){
				carInfo.setBlocid(Integer.parseInt(blocid));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(StringUtils.isNotEmty(stime)){
				carInfo.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				carInfo.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				carInfo.setUserid(SessionUtils.getUserId());
			}
			result=carService.queryLedCarList(this.getPage(),this.getLimit(), carInfo);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TextInfoAction的方法 queryCarInfoList执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 分页查询文本信息发送记录
	 * @return
	 */
	public String queryTextInfoListSendRecord() {
		
		try {
			initMap();
			if(sendRecord==null){
				sendRecord=new SendRecord();
			}
			if(stime!=null&&!stime.equals("")&&!stime.equals("null")){
				sendRecord.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(etime!=null&&!etime.equals("")&&!etime.equals("null")){
				sendRecord.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(opertype!=null&&!opertype.equals("")&&!opertype.equals("null")){
				sendRecord.setOpertype(Integer.parseInt(opertype));
			}
			if(content!=null&&!content.equals("")&&!content.equals("null")){
				sendRecord.setContent(URLDecoder.decode(content,"UTF-8"));
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				sendRecord.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				sendRecord.setUserid(SessionUtils.getUserId());
			}
			result=textInfoService.selectCheckTextInfoSendRecord(this.getPage(),this.getLimit(), sendRecord);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TextInfoAction的方法 queryTextInfoListSendRecord执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 分页查询文本信息
	 * @return
	 */
	public String queryTextInfoList() {
		
		try {
			initMap();
			if(textInfo==null){
				textInfo=new TextInfo();
			}
			
			if(content!=null&&!content.equals("")&&!content.equals("null")){
				textInfo.setContent(URLDecoder.decode(content,"UTF-8"));
			}
			if(textinfotype!=null&&!textinfotype.equals("")&&!textinfotype.equals("null")){
				textInfo.setTextinfotype(Integer.parseInt(textinfotype));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				textInfo.setUserid(SessionUtils.getUserId());
			}
			result=textInfoService.selectCheckTextInfo(this.getPage(),this.getLimit(), textInfo);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TextInfoAction的方法 queryTextInfoList执行出错，原因：" + e, e);
			return ERROR;
		}
		
	}
	
	/**
	 * 添加或修改文本信息
	 * @param textInfo
	 * @return
	 */
	public String saveTextInfo(){
		try {
			initMap();
			if(textInfo==null){
				textInfo=new TextInfo();
			}
			this.user = SessionUtils.getUser();
			textInfo.setUserid(SessionUtils.getUserId());
			textInfo.setBlocid(this.user.getBlocid());
			if (textInfo.getEmergency()==null||textInfo.getEmergency().equals("")||textInfo.getEmergency().equals("null")) {
				textInfo.setEmergency(0);
			} 
		    if (textInfo.getLcd()==null||textInfo.getLcd().equals("")||textInfo.getLcd().equals("null")) {
				textInfo.setLcd(0);
			} 
		    if (textInfo.getTts()==null||textInfo.getTts().equals("")||textInfo.getTts().equals("null")) {
				textInfo.setTts(0);
			} 
		    if (textInfo.getAdv()==null||textInfo.getAdv().equals("")||textInfo.getAdv().equals("null")) {
				textInfo.setAdv(0);
			} 
		    if (textInfo.getAction()==null||textInfo.getAction().equals("")||textInfo.getAction().equals("null")) {
				textInfo.setAction(0);
			} 
		    if (textInfo.getDist()==null||textInfo.getDist().equals("")||textInfo.getDist().equals("null")) {
				textInfo.setDist(0);
			} 
				if(textInfo.getId()==null){
					int count = textInfoService.insertTextInfo(textInfo);
					if(count<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					}
				}else{
					int re=textInfoService.updateTextInfo(textInfo);
					if(re<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					}
				
			}
			this.success="true";
			return SUCCESS;
			
		} catch (Exception e) {
			this.success="false";
			logger.error("TextInfoAction的方法 saveTextInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 信息下发
	 * @param textInfo
	 * @return
	 */
	public String CheckTextInfo(){
		try {
			initMap();
			if(textInfo==null){
				textInfo=new TextInfo();
			}
			if(sendRecord==null){
				sendRecord=new SendRecord();
			}
			String id[] = tes.split(",");
			List<CarInfo> list=this.SelectCarInfo();
				for (CarInfo car: list) {
					for (int i = 0; i < id.length; i++) {
						//下发
						textInfo=textInfoService.selectTextInfoById(Integer.parseInt(id[i]));
						int seq=HandleUtil.getSerialId(); 
						
						String data = null;
						
						data=HandleUtil.textInfo(car.getUsertype(),car.getTerminal(),textInfo.getEmergency(),
								textInfo.getLcd(),textInfo.getTts(),textInfo.getAdv()
								,textInfo.getAction(),textInfo.getDist(),textInfo.getContent(),seq,
								textInfo.getTime()==null?0:textInfo.getTime(),car.getCarnumber());
						
						sendRecord.setSeq(seq);
						sendRecord.setBlocid(SessionUtils.getBlocId());
						sendRecord.setUserid(SessionUtils.getUserId());
						sendRecord.setContent(textInfo.getContent());
						sendRecord.setEmergency(textInfo.getEmergency());
						sendRecord.setLcd(textInfo.getLcd());
						sendRecord.setTts(textInfo.getTts());
						sendRecord.setAdv(textInfo.getAdv());
						sendRecord.setAction(textInfo.getAction());
						sendRecord.setDist(textInfo.getDist());
						sendRecord.setOpertype(1);
						sendRecord.setCarnumber(car.getCarnumber());
						sendRecord.setTime(textInfo.getTime());
						textInfoService.insertTextInfoSendRecord(sendRecord);
					}	
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("TextInfoAction的方法 CheckTextInfo执行出错，原因：" + e, e);
			return ERROR;
		}
		
	}
	
	
	
	/**
	 * 根据列表选中的车辆-查询车辆信息
	 * @param textInfo
	 * @return
	 */
	public List<CarInfo> SelectCarInfo(){
		try {
			initMap();
			List<CarInfo> list=new ArrayList<CarInfo>();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				list.add(carService.getCarInfoCarId(Integer.parseInt(id[i])));
			}
			return list;
		} catch (Exception e) {
			logger.error("TextInfoAction的方法 deleteTextInfo执行出错，原因：" + e, e);
			return null;
		}
	}
	
	
	/**
	 * 获取文本信息类型列表
	 * @return
	 */
	public String getTextInfoTypeList(){

		initMap();
		if(carInfo==null){
			carInfo=new CarInfo();
		}
		try {
			List<TextInfo> list = textInfoService.getTextInfoTypeList();
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("TextInfoAction的方法 getTextInfoTypeList执行出错，原因：" + e, e);
			return ERROR;
		}

	}
	
	/**
	 * 删除文本信息
	 * @param textInfo
	 * @return
	 */
	public String deleteTextInfo(){
		try {
			initMap();
			if(textInfo==null){
				textInfo=new TextInfo();
			}
			if(sendRecord==null){
				sendRecord=new SendRecord();
			}
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				/*//删除之前向记录表中添加记录
				int seq=HandleUtil.getSerialId();
				textInfo=textInfoService.selectTextInfoById(Integer.parseInt(id[i]));
				sendRecord.setDeptid(textInfo.getDeptid());
				sendRecord.setUserid(textInfo.getUserid());
				sendRecord.setContent(textInfo.getContent());
				sendRecord.setEmergency(textInfo.getEmergency());
				sendRecord.setLcd(textInfo.getLcd());
				sendRecord.setTts(textInfo.getTts());
				sendRecord.setAdv(textInfo.getAdv());
				sendRecord.setAction(textInfo.getAction());
				sendRecord.setDist(textInfo.getDist());
				sendRecord.setSeq(seq);
				sendRecord.setOpertype(2);
				textInfoService.insertTextInfoSendRecord(sendRecord);*/
				textInfoService.deleteTextInfo(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("TextInfoAction的方法 deleteTextInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * LED屏信息查询
	 * @param textInfo
	 * @return
	 */
	public String queryLedMessage(){
		try {
			initMap();
			
			List<CarInfo> list=this.SelectCarInfo();
			if(list.size() > 0){
				for (CarInfo car: list) {
					HandleUtil.queryLedMessage(car.getUsertype(), car.getTerminal(), HandleUtil.getSerialId());
				}
				Thread.sleep(5000);
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TextInfoAction的方法 queryLedMessage执行出错，原因：" + e, e);
			return ERROR;
		}
		
	}
	
	
	
	public TextInfoService getTextInfoService() {
		return textInfoService;
	}
	public void setTextInfoService(TextInfoService textInfoService) {
		this.textInfoService = textInfoService;
	}
	public CarService getCarService() {
		return carService;
	}
	public void setCarService(CarService carService) {
		this.carService = carService;
	}
	public TextInfo getTextInfo() {
		return textInfo;
	}
	public void setTextInfo(TextInfo textInfo) {
		this.textInfo = textInfo;
	}
	public SendRecord getSendRecord() {
		return sendRecord;
	}
	public void setSendRecord(SendRecord sendRecord) {
		this.sendRecord = sendRecord;
	}
	public CarInfo getCarInfo() {
		return carInfo;
	}
	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
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
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public int getSu() {
		return su;
	}
	public void setSu(int su) {
		this.su = su;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getTes() {
		return tes;
	}
	public void setTes(String tes) {
		this.tes = tes;
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
	public String getOpertype() {
		return opertype;
	}
	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}
	public String getTextinfotype() {
		return textinfotype;
	}
	public void setTextinfotype(String textinfotype) {
		this.textinfotype = textinfotype;
	}
	public MenuTreeService getMenuTreeService() {
		return menuTreeService;
	}
	public void setMenuTreeService(MenuTreeService menuTreeService) {
		this.menuTreeService = menuTreeService;
	}
	public BlocUser getUser() {
		return user;
	}
	public void setUser(BlocUser user) {
		this.user = user;
	}
	public String getBlocid() {
		return blocid;
	}
	public void setBlocid(String blocid) {
		this.blocid = blocid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}


}
