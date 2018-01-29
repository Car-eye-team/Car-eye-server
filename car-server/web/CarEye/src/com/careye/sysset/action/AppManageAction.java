/**
 * Description: car-eye车辆管理平台
 * 文件名：AppManageAction.java
 * 版本信息：1.0
 * 日期：2015-8-20
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.sysset.action;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.careye.base.action.BasePageAction;
import com.careye.constant.WebConstants;
import com.careye.sysset.domain.AppType;
import com.careye.sysset.domain.AppVersion;
import com.careye.sysset.service.AppTypeService;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;


import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：AppManageAction
 * @类描述：
 * @创建人：Administrator
 * @创建时间：2015-8-20 上午11:46:26
 * @修改人：Administrator
 * @修改时间：2015-8-20 上午11:46:26
 * @修改备注：
 * @version 1.0
 */
public class AppManageAction extends BasePageAction {
	private static final Logger logger = Logger
			.getLogger(AppManageAction.class);
	private AppTypeService apptypeservice;
	private AppType apptype;
	private AppVersion appversion;

	private String typename;
	private String versionname;
	private Integer typeid;

	private String success;
	private Map result;
	private String ids;
	private List list;
	private String s_time;
	private String e_time;

	private String fileInputFileName;
	private File fileInput;
	private String Filename;
	private String Upload;
	private String fileInputContentType;
	private String fileext;
	private String folder;
	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}

	public String selAppVersionList() {
		try {
			initMap();
			list = apptypeservice.selAppVersionList();
			result.put("list", list);

			return SUCCESS;
		} catch (Exception e) {
			logger.error("AppManageAction的方法 selAppVersionList执行出错，原因：" + e, e);
			return ERROR;
		}

	}

	public String appTypeList() {
		try {
			initMap();
			if (apptype == null) {
				apptype = new AppType();
			}
			if (StringUtils.isNotEmty(typename)) {
				apptype
						.setTypename(URLDecoder.decode(typename, "UTF-8")
								.trim());
			}
			result = apptypeservice.findPageAppTypeList(this.getPage(), this
					.getLimit(), apptype);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AppManageAction的方法 appTypeList执行出错，原因：" + e, e);
			return ERROR;
		}

	}

	public String appVersionList() {
		try {
			initMap();
			if (appversion == null) {
				appversion = new AppVersion();
			}
			if (StringUtils.isNotEmty(versionname)) {
				appversion.setVersionname(URLDecoder.decode(versionname,
						"UTF-8").trim());
			}
			if (typeid!=null){
				appversion.setTypeid(typeid);
			}
			if (StringUtils.isNotEmty(s_time)) {
				appversion.setStime(s_time);
			}
			if (StringUtils.isNotEmty(e_time)) {
				appversion.setEtime(e_time);
			}
			if (!StringUtils.isEmty(ids)) {
				String[] datas = ids.split(",");
				for (int j = 0; j < datas.length; j++) {
					appversion.getIds().add(Integer.parseInt(datas[j]));
				}
			}

			result = apptypeservice.findPageAppVersionList(this.getPage(), this
					.getLimit(), appversion);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AppManageAction的方法 appVersionList执行出错，原因：" + e, e);
			return ERROR;
		}

	}

	public String appTypeSave() {
		try {
			initMap();
			if (apptype == null) {
				return ERROR;
			}
			int record = apptypeservice.appTypeIsExist(apptype);
			if (record > 0) {
				// 用户名已经存在
				result.put("returnType", 1);
				this.success = "true";
				return SUCCESS;
			}
			int count = 0;
			if (apptype.getId() != null) {
				count = apptypeservice.updateAppType(apptype);
			} else {
				apptype.setUserid(SessionUtils.getUserId());
				count = apptypeservice.addAppType(apptype);
			}
			if (count > 0) {
				result.put("returnType", 0);
				this.success = "true";
			} else {
				result.put("returnType", -2);
			}

			return SUCCESS;

		} catch (Exception e) {
			this.success = "false";
			logger.error("AppManageAction的方法 appTypeSave执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	public String appVersionSave(){

		initMap();
		if (appversion == null) {
			return ERROR;
		}
		appversion.setVersion(appversion.getVersion().toLowerCase());
		int record = apptypeservice.appVersionIsExist(appversion);
		if (record > 0) {
			// 用户名已经存在
			result.put("returnType", 1);
			this.success = "true";
			return SUCCESS;
		}
		int count = 0;
		if (appversion.getId() != null) {
			count = apptypeservice.updateAppVersion(appversion);
		} else {
			appversion.setUserid(SessionUtils.getUserId());
			count = apptypeservice.addAppVersion(appversion);
		}
		if (count > 0) {
			result.put("returnType", 0);
			this.success = "true";
		} else {
			result.put("returnType", -2);
		}

		return SUCCESS;

	
		
	}

	public String deleteAppType() {
		try {
			initMap();
			String id[] = ids.split(",");
			result.put("su", 0);
			for (int i = 0; i < id.length; i++) {
				int count = apptypeservice.queryGorupVersion(Integer.parseInt(id[i]));
				if(count==0){
					int re = apptypeservice.deleteAppType(Integer.parseInt(id[i]));
				}else {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AppManageAction 的方法 deleteAppType执行出错，原因：" + e, e);
			return ERROR;
		}

	}
	public String deleteAppVersion() {
		try {
			initMap();
			String id[] = ids.split(",");
			result.put("su", 0);
			for (int i = 0; i < id.length; i++) {
				int re = apptypeservice.deleteAppVersion(Integer.parseInt(id[i]));
				if (re <= 0) {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AppManageAction 的方法 deleteAppVersion执行出错，原因：" + e, e);
			return ERROR;
		}

	}
	public String appVersionUpload(){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();   
			response.setCharacterEncoding("utf-8"); 
			//上传文件保存路径
			String savePath = ServletActionContext.getServletContext().getRealPath("")+"/";
			String folder = WebConstants.appDir + "/" + WebConstants.APP + "/";
			savePath = savePath + folder;
			//判断文件夹是否存在,如果不存在则创建文件夹
			File file = new File(savePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			String picturename =fileInputFileName;
			fileInput.renameTo(new File(savePath + picturename));
			response.getWriter().println(folder + picturename);  
		} catch (Exception e) {
			logger.error("AppManageAction的方法 appVersionUpload执行出错，原因：" + e, e);
		}
		return null;
	}
	

	public AppTypeService getApptypeservice() {
		return apptypeservice;
	}

	public void setApptypeservice(AppTypeService apptypeservice) {
		this.apptypeservice = apptypeservice;
	}

	public AppType getApptype() {
		return apptype;
	}

	public void setApptype(AppType apptype) {
		this.apptype = apptype;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public AppVersion getAppversion() {
		return appversion;
	}

	public void setAppversion(AppVersion appversion) {
		this.appversion = appversion;
	}

	public String getVersionname() {
		return versionname;
	}

	public void setVersionname(String versionname) {
		this.versionname = versionname;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getS_time() {
		return s_time;
	}

	public void setS_time(String sTime) {
		s_time = sTime;
	}

	public String getE_time() {
		return e_time;
	}

	public void setE_time(String eTime) {
		e_time = eTime;
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public String getFilename() {
		return Filename;
	}

	public void setFilename(String filename) {
		Filename = filename;
	}

	public String getUpload() {
		return Upload;
	}

	public void setUpload(String upload) {
		Upload = upload;
	}

	public String getFileInputContentType() {
		return fileInputContentType;
	}

	public void setFileInputContentType(String fileInputContentType) {
		this.fileInputContentType = fileInputContentType;
	}

	public String getFileext() {
		return fileext;
	}

	public void setFileext(String fileext) {
		this.fileext = fileext;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

}
