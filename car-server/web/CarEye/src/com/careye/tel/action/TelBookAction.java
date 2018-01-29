/**
* Description: 多森商用车平台
* 文件名：TelBookSystemAction.java
* 版本信息：1.0
* 日期：2014-5-26
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.tel.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarInfo;
import com.careye.car.service.CarService;
import com.careye.mq.HandleUtil;
import com.careye.mq.domain.TelephoneBook;
import com.careye.system.domain.BlocUser;
import com.careye.tel.domain.TelBookCar;
import com.careye.tel.domain.TelBookSend;
import com.careye.tel.domain.TelBookSystem;
import com.careye.tel.service.TelBookService;
import com.careye.utils.SessionUtils;
import common.Logger;

/**
 * @项目名称：FMS
 * @类名称：TelBookSystemAction
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-26 下午01:47:32
 * @修改人：lxh
 * @修改时间：2014-5-26 下午01:47:32
 * @修改备注：
 * @version 1.0
 */
public class TelBookAction extends BasePageAction{
	private static final Logger logger = Logger.getLogger(TelBookAction.class);
	private TelBookService telBookService;
	private CarService carService;
	private CarInfo carInfo;
	private TelBookSystem telBookSystem;
	private TelBookCar telBookCar;
	private HandleUtil handleUtil;
	private TelBookSend telBookSend;
	
	private Map result;
	private String success;
	private List list;
	private String ids;
	private String eve;
	private BlocUser user;
	private int su;
	
	private String carnumber;
	private String contacts;
	private String tel;
	private String opertype;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	/**
	 * 分页查询电话本下发记录列表
	 * @return
	 */
	public String queryCheckTelBookSendList() {
		
		try {
			initMap();
			if(telBookSend==null){
				telBookSend=new TelBookSend();
			}
			if(tel!=null&&!tel.equals("")&&!tel.equals("null")){
				telBookSend.setTel(URLDecoder.decode(tel,"UTF-8"));
			}
			if(opertype!=null&&!opertype.equals("")&&!opertype.equals("null")){
				telBookSend.setOpertype(Integer.parseInt(opertype));
			}
			if(contacts!=null&&!contacts.equals("")&&!contacts.equals("null")){
				telBookSend.setContacts(URLDecoder.decode(contacts,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			telBookSend.setUserid(SessionUtils.getUserId());
			result=telBookService.selectCheckTelBookSend(this.getPage(),this.getLimit(), telBookSend);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TelBookSystemAction的方法 queryCheckTelBookSendList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 分页查询车辆电话本列表
	 * @return
	 */
	public String queryCheckTelBookCarByCarNumberList() {
		try {
			initMap();
			if(telBookCar==null){
				telBookCar=new TelBookCar();
			}
			if(tel!=null&&!tel.equals("")&&!tel.equals("null")){
				telBookCar.setTel(URLDecoder.decode(tel,"UTF-8"));
			}
			if(contacts!=null&&!contacts.equals("")&&!contacts.equals("null")){
				telBookCar.setContacts(URLDecoder.decode(contacts,"UTF-8"));
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				telBookCar.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			telBookCar.setUserid(SessionUtils.getUserId());
			result=telBookService.selectChecktelBookCarByCarNumber(this.getPage(),this.getLimit(), telBookCar);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TelBookSystemAction的方法 queryCheckTelBookCarByCarNumberList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 分页查询系统电话本
	 * @return
	 */
	public String queryTelBookSystemList() {
		
		try {
			initMap();
			if(telBookSystem==null){
				telBookSystem=new TelBookSystem();
			}
			
			if(contacts!=null&&!contacts.equals("")&&!contacts.equals("null")){
				telBookSystem.setContacts(URLDecoder.decode(contacts,"UTF-8"));
			}
			if(tel!=null&&!tel.equals("")&&!tel.equals("null")){
				telBookSystem.setTel(URLDecoder.decode(tel,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			telBookSystem.setUserid(SessionUtils.getUserId());
			result=telBookService.selectCheckTelBookSystem(this.getPage(),this.getLimit(), telBookSystem);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TelBookSystemAction的方法 queryTelBookSystemList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 添加或修改系统电话本
	 * @param telBookSystem
	 * @return
	 */
	public String saveTelBookSystem(){
		try {
			initMap();
			if(telBookSystem==null){
				telBookSystem=new TelBookSystem();
			}
			telBookSystem.setUserid(SessionUtils.getUserId());
			telBookSystem.setDeptid(SessionUtils.getUser().getBlocid());
			//判断电话号码是否已经存在
			int resu=telBookService.queryTelBookIsExist(telBookSystem);
			if(resu>0){
				result.put("su", -2);
			}else{
				if(telBookSystem.getId()==null){
					int count = telBookService.insertTelBookSystem(telBookSystem);
					if(count<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					}
				}else{
					int re=telBookService.updateTelBookSystem(telBookSystem);
					if(re<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					}
				}
			}
			this.success="true";
			return SUCCESS;
			
		} catch (Exception e) {
			this.success="false";
			logger.error("TelBookSystemAction的方法 saveTelBookSystem执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	//删除所有电话本
	public String deleteAllTelBookCar(){
		try {
			initMap();
			if(telBookCar==null){
				telBookCar=new TelBookCar();
			}
			//设置电话本列表
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				telBookCar.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			List<TelephoneBook> listEvent=this.ByCarNumberSelecttelBookCar();
			List<TelBookCar> listcar=telBookService.selectChecktelBookCarByCarNumber(telBookCar);
			CarInfo carInfo = carService.getCarInfoByCarNumber(telBookCar.getCarnumber());
			int seq=HandleUtil.getSerialId();
			String data=handleUtil.setTelephoneBook(carInfo.getUsertype(),carInfo.getTerminal(),listEvent,0,seq,telBookCar.getCarnumber());
			for (TelBookCar telBookCar : listcar) {
				//删除所有
				int s=telBookService.deleteTelBookCar(telBookCar);
				if(s<=0){
					result.put("su", -1);
				}else{
					
					telBookCar.setDeptid(telBookCar.getDeptid());
					telBookCar.setUserid(SessionUtils.getUserId());
					telBookCar.setTelbookid(telBookCar.getId());
					telBookCar.setSeq(seq);
					telBookCar.setCarnumber(telBookCar.getCarnumber());
					telBookCar.setCalltype(telBookCar.getCalltype());
					telBookCar.setContacts(telBookCar.getContacts());
					telBookCar.setTel(telBookCar.getTel());
					telBookCar.setRemark(telBookCar.getRemark());
					telBookCar.setOpertype(0);
					telBookCar.setData(data);
					//添加删除记录
					int e=telBookService.insertTelBookSend(telBookCar);
					if(e<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					 }
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TelBookSystemAction的方法 deleteAllTelBookCar执行出错，原因：" + e, e);
			result.put("su", -1);
			return ERROR;
		}
	}
	
	//修改选定的电话本
	public String editSelectTelBookCar(){
		try {
			initMap();
			if(telBookCar==null){
				telBookCar=new TelBookCar();
			}
			//设置电话本列表
			List<TelephoneBook> listEvent=this.ByCarTelBookSelectByID();
			String id[] = ids.split(",");
			telBookCar = telBookService.selectTelBookCarById(Integer.parseInt(id[0]));
			String c = telBookCar.getDevicenumber();
			int seq=HandleUtil.getSerialId();
			Integer devicetype = carService.getUserTypeByTerminal(c);
			String data=handleUtil.setTelephoneBook(devicetype,c,listEvent,3,seq,telBookCar.getCarnumber());
			List<TelBookCar> list=this.SelectCarEventById();
			for (TelBookCar telBookCar : list) {
				telBookCar.setOpertype(3);
				telBookCar.setData(data);
				telBookCar.setSeq(seq);
				//添加更新记录
				int e=telBookService.insertTelBookSend(telBookCar);
				if(e<=0){
					result.put("su", -1);
				}else{
					result.put("su", 1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TelBookSystemAction的方法 editSelectTelBookCar执行出错，原因：" + e, e);
			result.put("su", -1);
			return ERROR;
		}
	}
	
	//更新选定的电话本
	public String updateTelBookCar(){
		try {
			initMap();
			if(telBookCar==null){
				telBookCar=new TelBookCar();
			}
			//设置电话本列表
			List<TelephoneBook> listEvent=this.ByCarTelBookSelectByID();
			String id[] = ids.split(",");
			telBookCar = telBookService.selectTelBookCarById(Integer.parseInt(id[0]));
			String c=telBookCar.getDevicenumber();
			int seq=HandleUtil.getSerialId();
			Integer devicetype = carService.getUserTypeByTerminal(c);
			String data=handleUtil.setTelephoneBook(devicetype,c,listEvent,1,seq,telBookCar.getCarnumber());
			List<TelBookCar> list=this.SelectCarEventById();
			//更新之前删除终端中已有全部联系人
			TelBookCar s=new TelBookCar();
			s.setCarnumber(telBookService.selectTelBookCarById(Integer.parseInt(id[0])).getCarnumber());
			telBookService.deleteTelBookCar(s);
			for (TelBookCar telBookCar : list) {
				//重新追加联系人
				telBookService.insertTelBookCar(telBookCar);
				telBookCar.setOpertype(1);
				telBookCar.setData(data);
				telBookCar.setSeq(seq);
				telBookCar.setUserid(SessionUtils.getUserId());
				telBookCar.setDeptid(SessionUtils.getUser().getBlocid());
				//添加更新记录
				int e=telBookService.insertTelBookSend(telBookCar);
				if(e<=0){
					result.put("su", -1);
				}else{
					result.put("su", 1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("TelBookSystemAction的方法 updateTelBookCar执行出错，原因：" + e, e);
			result.put("su", -1);
			return ERROR;
		}
	}
	
	/**
	 * 追加电话本
	 * @param telBookSystem
	 * @return
	 */
	public String CheckTelBookSystem(){
		try {
			initMap();

			if(telBookCar==null){
				telBookCar=new TelBookCar();
			}
			//车辆信息        获取其中的车牌号
			List<CarInfo> listCar=this.SelectCarInfo();
			//设置电话本列表
			List<TelephoneBook> listEvent=this.SelectTelephoneBook();
			//系统电话本
			List<TelBookSystem> listTelBookSystem=this.SelectTelBookSystem();
			
			for (CarInfo car: listCar) {
				//追加电话本
				//选择的系统电话本信息
				int seq = HandleUtil.getSerialId();
				
				String data = "";
				data = handleUtil.setTelephoneBook(car.getUsertype(),car.getTerminal(),listEvent,2,seq,car.getCarnumber());
				
				for (TelBookSystem telBookSystem : listTelBookSystem) {
				
					telBookCar.setDeptid(telBookSystem.getDeptid());
					telBookCar.setUserid(SessionUtils.getUserId());
					telBookCar.setTelbookid(telBookSystem.getId());
					telBookCar.setSeq(seq);
					telBookCar.setCarnumber(car.getCarnumber());
					//覆盖掉重复的电话本
					telBookService.deleteTelBookCar(telBookCar);
					//增加车辆电话本
					int s=telBookService.insertTelBookCar(telBookCar);
					if(s<=0){
						result.put("su", -1);
					}else{
						telBookCar.setCalltype(telBookSystem.getCalltype());
						telBookCar.setContacts(telBookSystem.getContacts());
						telBookCar.setTel(telBookSystem.getTel());
						telBookCar.setRemark(telBookSystem.getRemark());
						telBookCar.setOpertype(2);
						telBookCar.setData(data);
						//增加电话本记录
						int e=telBookService.insertTelBookSend(telBookCar);
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
			// TODO: handle exception
			logger.error("TelBookSystemAction的方法 CheckTelBookSystem执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 查询电话本信息
	 * @param telBookSystem
	 * @return
	 */
	public List<TelBookSystem> SelectTelBookSystem(){
		try {
			initMap();
			if(telBookSystem==null){
				telBookSystem=new TelBookSystem();
			}
			List<TelBookSystem> list=new ArrayList<TelBookSystem>();
			String id[] = eve.split(",");
			for (int i = 0; i < id.length; i++) {
				telBookSystem=telBookService.selectTelBookSystemById(Integer.parseInt(id[i]));
				list.add(telBookSystem);
			}
			return list;
		} catch (Exception e) {
			logger.error("TelBookSystemAction的方法 SelectTelBookSystem执行出错，原因：" + e, e);
			return null;
		}
	}
	
	/**
	 * 查询电话本实体TelephoneBook
	 * @return
	 */
	public List<TelephoneBook> SelectTelephoneBook(){
		try {
			initMap();
			if(telBookSystem==null){
				telBookSystem=new TelBookSystem();
			}
			List<TelephoneBook> list=new ArrayList<TelephoneBook>();
			String id[] = eve.split(",");
			for (int i = 0; i < id.length; i++) {
				telBookSystem=telBookService.selectTelBookSystemById(Integer.parseInt(id[i]));
				TelephoneBook e=new TelephoneBook();
				e.setTag(telBookSystem.getCalltype());
				e.setTelLen(telBookSystem.getTel().getBytes().length);
				e.setTel(telBookSystem.getTel());
				e.setCtcLen(telBookSystem.getContacts().getBytes().length);
				e.setContact(telBookSystem.getContacts());
				list.add(e);
			}
			return list;
		} catch (Exception e) {
			logger.error("TelBookSystemAction的方法 SelectEvent执行出错，原因：" + e, e);
			return null;
		}
	}
	/**
	 * 查询电话本信息TelephoneBook
	 * @return
	 */
	public List<TelephoneBook> ByCarTelBookSelectByID(){
		try {
			initMap();
			if(telBookCar==null){
				telBookCar=new TelBookCar();
			}
			List<TelephoneBook> list=new ArrayList<TelephoneBook>();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				telBookCar=telBookService.selectTelBookCarById(Integer.parseInt(id[i]));
				TelephoneBook e=new TelephoneBook();
				e.setTag(telBookCar.getCalltype());
				e.setTelLen(telBookCar.getTel().getBytes().length);
				e.setTel(telBookCar.getTel());
				e.setCtcLen(telBookCar.getContacts().getBytes().length);
				e.setContact(telBookCar.getContacts());
				list.add(e);
			}
			return list;
		} catch (Exception e) {
			logger.error("TelBookSystemAction的方法 ByCarTelBookSelectByID执行出错，原因：" + e, e);
			return null;
		}
	}
	/**
	 * 查询电话本信息Event
	 * @return
	 */
	public List<TelephoneBook> ByCarNumberSelecttelBookCar(){
		try {
			initMap();
			if(telBookCar==null){
				telBookCar=new TelBookCar();
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				telBookCar.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			List<TelephoneBook> list=new ArrayList<TelephoneBook>();
			List<TelBookCar> listcar=telBookService.selectChecktelBookCarByCarNumber(telBookCar);
			for (TelBookCar telBookCar : listcar) {
				TelephoneBook e=new TelephoneBook();
				e.setTag(telBookCar.getCalltype());
				e.setTelLen(telBookCar.getTel().getBytes().length);
				e.setTel(telBookCar.getTel());
				e.setCtcLen(telBookCar.getContacts().getBytes().length);
				e.setContact(telBookCar.getContacts());
				list.add(e);
			}
			return list;
		} catch (Exception e) {
			logger.error("TelBookSystemAction的方法 ByCarNumberSelecttelBookCar执行出错，原因：" + e, e);
			return null;
		}
	}
	
	/**
	 * 查询车辆电话本信息  BY Id
	 * @return
	 */
	public List<TelBookCar> SelectCarEventById(){
		try {
			initMap();
			String id[] = ids.split(",");
			List<TelBookCar> list=new ArrayList<TelBookCar>();
			for (int i = 0; i < id.length; i++) {
				telBookCar=telBookService.selectTelBookCarById(Integer.parseInt(id[i]));
				list.add(telBookCar);
			}
			return list;
		} catch (Exception e) {
			logger.error("TelBookSystemAction的方法 SelectCarEventById执行出错，原因：" + e, e);
			return null;
		}
	}
	
	/**
	 * 查询车辆电话本信息  BY 车牌号
	 * @return
	 */
	public List<TelBookCar> SelectCarEventByCarNumber(TelBookCar telBookCar){
		try {
			initMap();
			List<TelBookCar> ec=telBookService.selectChecktelBookCarByCarNumber(telBookCar);
			return ec;
		} catch (Exception e) {
			logger.error("TelBookSystemAction的方法 SelectCarEventByCarNumber执行出错，原因：" + e, e);
			return null;
		}
	}
	
	/**
	 * 查询电话本信息AllEvent
	 * @return
	 *//*
	public List<Event> SelectAllEvent(TelBookCar telBookCar){
		try {
			initMap();
			if(telBookSystem==null){
				telBookSystem=new TelBookSystem();
			}
			List<Event> list=new ArrayList<Event>();
			List<TelBookCar> ec=telBookService.selectChecktelBookCarByCarNumber(telBookCar);
			for (TelBookCar telBookCar2 : ec) {
				Event e=new Event();
				e.setId(telBookCar2.getId());
				e.setLen(telBookCar2.getContent().getBytes().length);
				e.setContent(telBookCar2.getContent());
				list.add(e);
			}
			return list;
		} catch (Exception e) {
			logger.error("TelBookSystemAction的方法 SelectEvent执行出错，原因：" + e, e);
			return null;
		}
	}*/
	
	/**
	 * 查询车辆信息
	 * @param telBookSystem
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
			logger.error("TelBookSystemAction的方法 SelectCarInfo执行出错，原因：" + e, e);
			return null;
		}
	}
	
	/**
	 * 删除系统电话本
	 * @param telBookSystem
	 * @return
	 */
	public String deleteTelBookSystem(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				//删除之前查看车辆电话本中是否使用
				int count=telBookService.queryTelBookCarIsExist(Integer.parseInt(id[i]));
				if(count>0){
					result.put("su", -1);
				}else{
					telBookService.deleteTelBookSystem(Integer.parseInt(id[i]));
					result.put("su", 1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("TelBookSystemAction的方法 deleteTelBookSystem执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	public TelBookService getTelBookService() {
		return telBookService;
	}
	public void setTelBookService(TelBookService telBookService) {
		this.telBookService = telBookService;
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
	public TelBookSystem getTelBookSystem() {
		return telBookSystem;
	}
	public void setTelBookSystem(TelBookSystem telBookSystem) {
		this.telBookSystem = telBookSystem;
	}
	public TelBookCar getTelBookCar() {
		return telBookCar;
	}
	public void setTelBookCar(TelBookCar telBookCar) {
		this.telBookCar = telBookCar;
	}
	public HandleUtil getHandleUtil() {
		return handleUtil;
	}
	public void setHandleUtil(HandleUtil handleUtil) {
		this.handleUtil = handleUtil;
	}
	public TelBookSend getTelBookSend() {
		return telBookSend;
	}
	public void setTelBookSend(TelBookSend telBookSend) {
		this.telBookSend = telBookSend;
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
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
