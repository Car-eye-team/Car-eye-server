package com.careye.sysset.action;

import java.util.HashMap;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.mq.HandleUtil;
import com.careye.sysset.domain.VideoParamSet;
import com.careye.sysset.service.VideoParamSetService;
import com.careye.utils.ExceptionUtil;
import com.careye.utils.StringUtils;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-10-10 下午06:10:03
 * @修改人：ll
 * @修改时间：2016-10-10 下午06:10:03
 * @修改备注：
 * @version 1.0
 */
public class VideoParamSetAction extends BasePageAction {
	
	private static final long serialVersionUID = 1L;
	
	private VideoParamSetService videoParamSetService;
	private VideoParamSet videoParamSet;
	
	private String terminal;
	private String id;
	private String type;
	private String stime;
	private String etime;
	private String filename;
	private String splaysec;
	private String eplaysec;
	
	private VideoParamSet data;
	private String success;
	private Map result;
	
	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 获取设置参数
	 * @return
	 */
	public String queryVideoParamSet(){
		try {
			initMap();
			data = videoParamSetService.queryVideoParamSet();
			if(data == null){
				data = new VideoParamSet();
			}
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			return ERROR;
		}
	}
	
	/**
	 * updateSysParamset  修改参数
	 * @return
	 */
	
	public String updateVideoParamSet(){
		try {
			initMap();
			if(videoParamSet == null){
				return ERROR;
			}
			int count = videoParamSetService.updateVideoParamSet(videoParamSet);
			if(count<=0){
				result.put("su", -1);
			}else{
				result.put("su", 1);
			}
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			return ERROR;
		}
	}
	
	/**
	 * 播放和停止视频协议下发
	 * @return
	 */
	public String playOrStopSend(){
		try {
			initMap();
			
			if(StringUtils.isEmty(terminal) || StringUtils.isEmty(id) || StringUtils.isEmty(type)){
				return ERROR;
			}
			
			//播放和停止视频协议下发
			HandleUtil.playOrStopSend(terminal,id,type);
			
			this.success="true";
			result.put("su", 0);
			return SUCCESS;
		} catch (Exception e) {
			this.success="false";
			result.put("su", -1);
			ExceptionUtil.getInfo(e);
			return ERROR;
		}
	}
	
	/**
	 * 回放视频协议下发
	 * @return
	 */
	public String playbackSend(){
		try {
			initMap();
			
			if(StringUtils.isEmty(terminal) || StringUtils.isEmty(id) || StringUtils.isEmty(type) 
					|| StringUtils.isEmty(stime) || StringUtils.isEmty(etime)){
				return ERROR;
			}
			
			//回放视频协议下发
			HandleUtil.playbackSend(terminal,id,type,stime,etime);
			
			this.success="true";
			result.put("su", 0);
			return SUCCESS;
		} catch (Exception e) {
			this.success="false";
			result.put("su", -1);
			ExceptionUtil.getInfo(e);
			return ERROR;
		}
	}
	
	/**
	 * 回放指定文件
	 * @return
	 */
	public String playbackAppoint(){
		try {
			initMap();
			
			if(StringUtils.isEmty(terminal) || StringUtils.isEmty(id) || StringUtils.isEmty(filename) 
					|| StringUtils.isEmty(splaysec) || StringUtils.isEmty(eplaysec)){
				return ERROR;
			}
			
			//回放指定文件协议下发
			HandleUtil.playbackAppoint(terminal,id,filename,splaysec,eplaysec);
			
			this.success="true";
			result.put("su", 0);
			return SUCCESS;
		} catch (Exception e) {
			this.success="false";
			result.put("su", -1);
			ExceptionUtil.getInfo(e);
			return ERROR;
		}
	}
	
	/**
	 * 视频回放结束
	 * @return
	 */
	public String playbackStop(){
		try {
			initMap();
			
			if(StringUtils.isEmty(terminal) || StringUtils.isEmty(id)){
				return ERROR;
			}
			
			//视频回放结束协议下发
			HandleUtil.playbackStop(terminal,id);
			
			this.success="true";
			result.put("su", 0);
			return SUCCESS;
		} catch (Exception e) {
			this.success="false";
			result.put("su", -1);
			ExceptionUtil.getInfo(e);
			return ERROR;
		}
	}

	public VideoParamSetService getVideoParamSetService() {
		return videoParamSetService;
	}

	public void setVideoParamSetService(VideoParamSetService videoParamSetService) {
		this.videoParamSetService = videoParamSetService;
	}

	public VideoParamSet getVideoParamSet() {
		return videoParamSet;
	}

	public void setVideoParamSet(VideoParamSet videoParamSet) {
		this.videoParamSet = videoParamSet;
	}

	public VideoParamSet getData() {
		return data;
	}

	public void setData(VideoParamSet data) {
		this.data = data;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getSplaysec() {
		return splaysec;
	}

	public void setSplaysec(String splaysec) {
		this.splaysec = splaysec;
	}

	public String getEplaysec() {
		return eplaysec;
	}

	public void setEplaysec(String eplaysec) {
		this.eplaysec = eplaysec;
	}
	
}
