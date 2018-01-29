/**
* Description: 多森商用车平台
* 文件名：EventSystemAction.java
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
import com.careye.message.domain.EventCar;
import com.careye.message.domain.EventSendRecord;
import com.careye.message.domain.EventSystem;
import com.careye.message.service.EventService;
import com.careye.mq.HandleUtil;
import com.careye.mq.domain.Event;
import com.careye.system.domain.BlocUser;
import com.careye.utils.SessionUtils;

import common.Logger;

/**
 * @项目名称：FMS
 * @类名称：EventSystemAction
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-26 下午01:47:32
 * @修改人：lxh
 * @修改时间：2014-5-26 下午01:47:32
 * @修改备注：
 * @version 1.0
 */
public class EventAction extends BasePageAction{
	private static final Logger logger = Logger.getLogger(EventAction.class);
	private EventService eventService;
	private CarService carService;
	private CarInfo carInfo;
	private EventSystem eventSystem;
	private EventCar eventCar;
	private HandleUtil handleUtil;
	private EventSendRecord eventSendRecord;
	
	private Map result;
	private String success;
	private List list;
	private String ids;
	private String eve;
	private BlocUser user;
	private int su;
	
	private String carnumber;
	private String content;
	private String terminal;
	private int type;
	private String stime;
	private String etime;
	private String opertype;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	/**
	 * 分页查询事件下发记录列表
	 * @return
	 */
	public String queryCheckEventSendRecordList() {
		
		try {
			initMap();
			if(eventSendRecord==null){
				eventSendRecord=new EventSendRecord();
			}
			if(stime!=null&&!stime.equals("")&&!stime.equals("null")){
				eventSendRecord.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(etime!=null&&!etime.equals("")&&!etime.equals("null")){
				eventSendRecord.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(opertype!=null&&!opertype.equals("")&&!opertype.equals("null")){
				eventSendRecord.setOpertype(Integer.parseInt(opertype));
			}
			if(content!=null&&!content.equals("")&&!content.equals("null")){
				eventSendRecord.setContent(URLDecoder.decode(content,"UTF-8"));
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				eventSendRecord.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				eventSendRecord.setUserid(SessionUtils.getUserId());
			}
			result=eventService.selectCheckEventSendRecord(this.getPage(),this.getLimit(), eventSendRecord);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("EventSystemAction的方法 queryCheckEventSendRecordList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 分页查询车辆事件列表
	 * @return
	 */
	public String queryCheckEventCarByCarNumberList() {
		try {
			initMap();
			if(eventCar==null){
				eventCar=new EventCar();
			}
			if(stime!=null&&!stime.equals("")&&!stime.equals("null")){
				eventCar.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(etime!=null&&!etime.equals("")&&!etime.equals("null")){
				eventCar.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(content!=null&&!content.equals("")&&!content.equals("null")){
				eventCar.setContent(URLDecoder.decode(content,"UTF-8"));
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				eventCar.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				eventCar.setUserid(SessionUtils.getUserId());
			}
			result=eventService.selectCheckeventCarByCarNumber(this.getPage(),this.getLimit(), eventCar);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("EventSystemAction的方法 queryCheckEventCarByCarNumberList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 分页查询系统事件
	 * @return
	 */
	public String queryEventSystemList() {
		
		try {
			initMap();
			if(eventSystem==null){
				eventSystem=new EventSystem();
			}
			
			if(content!=null&&!content.equals("")&&!content.equals("null")){
				eventSystem.setContent(URLDecoder.decode(content,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				eventSystem.setUserid(SessionUtils.getUserId());
			}
			result=eventService.selectCheckEventSystem(this.getPage(),this.getLimit(), eventSystem);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("EventSystemAction的方法 queryEventSystemList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 添加或修改系统事件
	 * @param eventSystem
	 * @return
	 */
	public String saveEventSystem(){
		try {
			initMap();
			if(eventSystem==null){
				eventSystem=new EventSystem();
			}
//			this.user = (User) ServletActionContext.getRequest().getSession().getAttribute(WebConstants.LOGIN_USER);	
			this.user = SessionUtils.getUser();
			eventSystem.setUserid(SessionUtils.getUserId());
			eventSystem.setBlocid(this.user.getBlocid());
			
				if(eventSystem.getId()==null){
					int count = eventService.insertEventSystem(eventSystem);
					if(count<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					}
				}else{
					int re=eventService.updateEventSystem(eventSystem);
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
			logger.error("EventSystemAction的方法 saveEventSystem执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	//删除所有事件
	@SuppressWarnings("static-access")
	public String deleteAllEventCar(){
		try {
			initMap();
			if(eventCar==null){
				eventCar=new EventCar();
			}
			//设置事件列表
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				eventCar.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			List<Event> listEvent=this.ByCarNumberSelectEvent();
			List<EventCar> listcar=eventService.selectCheckeventCarByCarNumber(eventCar);
			String data = null;
			int i = 0;
			
			CarInfo carInfo = carService.getCarInfoByCarNumber(eventCar.getCarnumber());
			int seq = HandleUtil.getSerialId();
			
			if(listcar.size() == 0){
				data = handleUtil.setEvent(carInfo.getUsertype(),carInfo.getTerminal(),listEvent,0,seq,eventCar.getCarnumber());
			}
			
			for (EventCar eventCar : listcar) {
				if(i==0){
					data = handleUtil.setEvent(carInfo.getUsertype(),eventCar.getTerminal(),listEvent,0,seq,eventCar.getCarnumber());
				}
				
				
				//删除所有
				int s=eventService.deleteEventCar(eventCar);
				if(s<=0){
					result.put("su", -1);
				}else{
					eventCar.setData(data);
					eventCar.setOpertype(4);
					eventCar.setSeq(seq);
					//添加删除记录
					int e=eventService.insertEventSendRecord(eventCar);
					if(e<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					 }
				}
				i++;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("EventSystemAction的方法 deleteAllEventCar执行出错，原因：" + e, e);
			result.put("su", -1);
			return ERROR;
		}
	}
	
	//删除选定的事件
	public String deleteSelectEventCar(){
		try {
			initMap();
			if(eventCar==null){
				eventCar=new EventCar();
			}
			//设置事件列表
			List<Event> listEvent=this.ByCarEventSelectEvent();
			String id[] = ids.split(",");
			eventCar = eventService.selectEventCarById(Integer.parseInt(id[0]));
			terminal=eventCar.getTerminal();
			int seq=HandleUtil.getSerialId();
			
			Integer devcicetype = carService.getUserTypeByTerminal(terminal);
			String data=handleUtil.setEvent(devcicetype,terminal,listEvent,4,seq,eventCar.getCarnumber());
			for (int i = 0; i < id.length; i++) {
				eventCar=eventService.selectEventCarById(Integer.parseInt(id[i]));
                //删除指定的车辆事件
				int r=eventService.deleteEventCar(eventCar);
				if(r<=0){
					result.put("su", -1);
				}else{
					eventCar.setData(data);
					eventCar.setOpertype(3);
					eventCar.setSeq(seq);
					//添加删除记录
					int e=eventService.insertEventSendRecord(eventCar);
					if(e<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					}
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("EventSystemAction的方法 deleteSelectEventCar执行出错，原因：" + e, e);
			result.put("su", -1);
			return ERROR;
		}
	}
	
	//更新选定的事件
	@SuppressWarnings("static-access")
	public String updateEventCar(){
		try {
			initMap();
			if(eventCar==null){
				eventCar=new EventCar();
			}
			//设置事件列表
			List<Event> listEvent=this.ByCarEventSelectEvent();
			String id[] = ids.split(",");
			eventCar = eventService.selectEventCarById(Integer.parseInt(id[0]));
			String terminal = eventCar.getTerminal();
			int seq = HandleUtil.getSerialId();
			Integer devcicetype = carService.getUserTypeByTerminal(terminal);
			String data=handleUtil.setEvent(devcicetype,terminal,listEvent,1,seq,eventCar.getCarnumber());
			for (int i = 0; i < id.length; i++) {
				eventCar=eventService.selectEventCarById(Integer.parseInt(id[i]));
					eventCar.setData(data);
					eventCar.setOpertype(2);
					eventCar.setSeq(seq);
					//添加更新记录
					int e=eventService.insertEventSendRecord(eventCar);
					if(e<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					}
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("EventSystemAction的方法 updateEventCar执行出错，原因：" + e, e);
			result.put("su", -1);
			return ERROR;
		}
	}
	
	/**
	 * 追加事件
	 * @param eventSystem
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String CheckEventSystem(){
		try {
			initMap();

			if(eventCar==null){
				eventCar=new EventCar();
			}
			//车辆信息        获取其中的车牌号
			List<CarInfo> listCar=this.SelectCarInfo();
			//设置事件列表
			List<Event> listEvent=this.SelectEvent();
			//系统事件
			List<EventSystem> listEventSystem=this.SelectEventSystem();
			
			for (CarInfo car: listCar) {
				//追加事件
				//选择的系统事件信息
				int seq=HandleUtil.getSerialId();
				String data=handleUtil.setEvent(car.getUsertype(),car.getTerminal(),listEvent,2,seq,car.getCarnumber());
				
				for (EventSystem eventSystem : listEventSystem) {
				
					eventCar.setBlocid(SessionUtils.getBlocId());
					eventCar.setUserid(SessionUtils.getUserId());
					eventCar.setContent(eventSystem.getContent());
					eventCar.setEventid(eventSystem.getId());
					eventCar.setSeq(seq);
					eventCar.setCarnumber(car.getCarnumber());
					//覆盖掉重复的事件
					eventService.deleteEventCar(eventCar);
					//增加车辆事件
					int s=eventService.insertEventCar(eventCar);
					if(s<=0){
						result.put("su", -1);
					}else{
						eventCar.setOpertype(1);
						eventCar.setData(data);
						//增加事件记录
						int e=eventService.insertEventSendRecord(eventCar);
						if(e<=0){
							result.put("su", -1);
						}else{
							result.put("su", 1);
						}
					}
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("EventSystemAction的方法 CheckEventSystem执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 查询事件信息
	 * @param eventSystem
	 * @return
	 */
	public List<EventSystem> SelectEventSystem(){
		try {
			initMap();
			if(eventSystem==null){
				eventSystem=new EventSystem();
			}
			List<EventSystem> list=new ArrayList<EventSystem>();
			String id[] = eve.split(",");
			for (int i = 0; i < id.length; i++) {
				eventSystem=eventService.selectEventSystemById(Integer.parseInt(id[i]));
				list.add(eventSystem);
			}
			return list;
		} catch (Exception e) {
			logger.error("EventSystemAction的方法 SelectEventSystem执行出错，原因：" + e, e);
			return null;
		}
	}
	
	/**
	 * 查询事件信息Event
	 * @return
	 */
	public List<Event> SelectEvent(){
		try {
			initMap();
			if(eventSystem==null){
				eventSystem=new EventSystem();
			}
			List<Event> list=new ArrayList<Event>();
			String id[] = eve.split(",");
			for (int i = 0; i < id.length; i++) {
				eventSystem=eventService.selectEventSystemById(Integer.parseInt(id[i]));
				Event e=new Event();
				e.setId(eventSystem.getId());
				e.setLen(eventSystem.getContent().getBytes().length);
				e.setContent(eventSystem.getContent());
				list.add(e);
			}
			return list;
		} catch (Exception e) {
			logger.error("EventSystemAction的方法 SelectEvent执行出错，原因：" + e, e);
			return null;
		}
	}
	/**
	 * 查询事件信息Event
	 * @return
	 */
	public List<Event> ByCarEventSelectEvent(){
		try {
			initMap();
			if(eventCar==null){
				eventCar=new EventCar();
			}
			List<Event> list=new ArrayList<Event>();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				eventCar=eventService.selectEventCarById(Integer.parseInt(id[i]));
				Event e=new Event();
				e.setId(eventCar.getEventid());
				e.setLen(eventCar.getContent().getBytes().length);
				e.setContent(eventCar.getContent());
				list.add(e);
			}
			return list;
		} catch (Exception e) {
			logger.error("EventSystemAction的方法 ByCarEventSelectEvent执行出错，原因：" + e, e);
			return null;
		}
	}
	/**
	 * 查询事件信息Event
	 * @return
	 */
	public List<Event> ByCarNumberSelectEvent(){
		try {
			initMap();
			if(eventCar==null){
				eventCar=new EventCar();
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				eventCar.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			List<Event> list=new ArrayList<Event>();
			List<EventCar> listcar=eventService.selectCheckeventCarByCarNumber(eventCar);
			for (EventCar eventCar : listcar) {
				Event e=new Event();
				e.setId(eventCar.getId());
				e.setLen(eventCar.getContent().getBytes().length);
				e.setContent(eventCar.getContent());
				list.add(e);
			}
			return list;
		} catch (Exception e) {
			logger.error("EventSystemAction的方法 ByCarEventSelectEvent执行出错，原因：" + e, e);
			return null;
		}
	}
	
	/**
	 * 查询车辆事件信息  BY 车牌号
	 * @return
	 */
	public List<EventCar> SelectCarEventByCarNumber(EventCar eventCar){
		try {
			initMap();
			List<EventCar> ec=eventService.selectCheckeventCarByCarNumber(eventCar);
			return ec;
		} catch (Exception e) {
			logger.error("EventSystemAction的方法 SelectCarEventByCarNumber执行出错，原因：" + e, e);
			return null;
		}
	}
	
	/**
	 * 查询事件信息AllEvent
	 * @return
	 */
	public List<Event> SelectAllEvent(EventCar eventCar){
		try {
			initMap();
			if(eventSystem==null){
				eventSystem=new EventSystem();
			}
			List<Event> list=new ArrayList<Event>();
			List<EventCar> ec=eventService.selectCheckeventCarByCarNumber(eventCar);
			for (EventCar eventCar2 : ec) {
				Event e=new Event();
				e.setId(eventCar2.getId());
				e.setLen(eventCar2.getContent().getBytes().length);
				e.setContent(eventCar2.getContent());
				list.add(e);
			}
			return list;
		} catch (Exception e) {
			logger.error("EventSystemAction的方法 SelectEvent执行出错，原因：" + e, e);
			return null;
		}
	}
	
	/**
	 * 查询车辆信息
	 * @param eventSystem
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
			logger.error("EventSystemAction的方法 SelectCarInfo执行出错，原因：" + e, e);
			return null;
		}
	}
	
	/**
	 * 删除系统事件
	 * @param eventSystem
	 * @return
	 */
	public String deleteEventSystem(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				//删除之前向记录表中添加记录
				/*int seq=HandleUtil.getSerialId();
				eventSystem=eventService.selectEventSystemById(Integer.parseInt(id[i]));
				eventCar.setDeptid(eventSystem.getDeptid());
				eventCar.setUserid(eventSystem.getUserid());
				eventCar.setContent(eventSystem.getContent());
				eventCar.setSeq(seq);*/
				//删除之前查看车辆事件中是否使用
				int count=eventService.queryEventCarIsExist(Integer.parseInt(id[i]));
				if(count>0){
					result.put("su", -1);
				}else{
					eventService.deleteEventSystem(Integer.parseInt(id[i]));
					result.put("su", 1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("EventSystemAction的方法 deleteEventSystem执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	
	public EventService getEventService() {
		return eventService;
	}
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	public CarService getCarService() {
		return carService;
	}
	public void setCarService(CarService carService) {
		this.carService = carService;
	}
	public CarInfo getCarInfo() {
		return carInfo;
	}
	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}
	public EventSystem getEventSystem() {
		return eventSystem;
	}
	public void setEventSystem(EventSystem eventSystem) {
		this.eventSystem = eventSystem;
	}
	public EventCar getEventCar() {
		return eventCar;
	}
	public void setEventCar(EventCar eventCar) {
		this.eventCar = eventCar;
	}
	public HandleUtil getHandleUtil() {
		return handleUtil;
	}
	public void setHandleUtil(HandleUtil handleUtil) {
		this.handleUtil = handleUtil;
	}
	public EventSendRecord getEventSendRecord() {
		return eventSendRecord;
	}
	public void setEventSendRecord(EventSendRecord eventSendRecord) {
		this.eventSendRecord = eventSendRecord;
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

	public String getEve() {
		return eve;
	}
	public void setEve(String eve) {
		this.eve = eve;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public BlocUser getUser() {
		return user;
	}
	public void setUser(BlocUser user) {
		this.user = user;
	}
	
}
