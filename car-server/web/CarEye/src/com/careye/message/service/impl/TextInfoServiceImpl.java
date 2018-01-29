/**
* Description: car-eye车辆管理平台
* 文件名：CarServiceImpl.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.message.domain.SendRecord;
import com.careye.message.domain.TextInfo;
import com.careye.message.service.TextInfoService;
import com.careye.utils.DateUtil;

/**
 * @项目名称：FMS
 * @类名称：TextInfoServiceImpl
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:05:25
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:05:25
 * @修改备注：
 * @version 1.0
 */
public class TextInfoServiceImpl extends GenericService implements TextInfoService{

	private SysOperateLogService logService;
	
	/**
	 * id查询文本信息
	 * @param textInfo
	 * @return
	 */
	@Override
	public TextInfo selectTextInfoById(int id) {
		// TODO Auto-generated method stub
		return (TextInfo)this.baseDao.queryForObject("oracle-textInfoQL.selectTextInfoById", id);
	}

	
	/**
	 * 添加文本信息
	 * @param textInfo
	 * @return
	 */
	@Override
	public int insertTextInfoSendRecord(SendRecord sendRecord) {
		// TODO Auto-generated method stub
		sendRecord.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-textInfoQL.insertTextInfoSendRecord", sendRecord);
	}


	/**
	 * 更新文本信息
	 * @param textInfo
	 * @return
	 */
	@Override
	public int updateTextInfoSendRecord(SendRecord sendRecord) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-textInfoQL.updateTextInfoSendRecord", sendRecord);
	}

	/**
	 *   删除文本信息
	 * @param id
	 * @return
	 */
	//@Log(operationType="delete操作:",operationName=" 删除文本信息")
	@Override
	public int deleteTextInfo(int id) {
		TextInfo textInfo=this.getTextInfoById(id);
		int result=   this.baseDao.delete("oracle-textInfoQL.deleteTextInfo", id);
		if(result>0){
			String flagString=null;
			switch (textInfo.getEmergency()) {
			case 0:
				flagString="不紧急";
				break;
			case 1:
				flagString="紧急";
				break;
			default:
				break;
			}
			logService.addLogInfo(textInfo.getUserid(), " 删除文本信息记录:问题内容:"+textInfo.getContent()
					+";是否紧急:"+flagString
					+";影响数据ID:"+textInfo.getId(),3);
		}
	
		return  result;
	}
	
	/**
	 * 根据id得到文本信息对象
	 * @param id
	 * @return
	 */
	private TextInfo getTextInfoById(int id){
		
		return (TextInfo)this.baseDao.queryForObject("oracle-textInfoQL.getTextInfoById", id);
	}

	/**
	 * 添加文本信息
	 * @param textInfo
	 * @return
	 */
	//@Log(operationType="add操作:",operationName="添加文本信息记录")
	@Override
	public int insertTextInfo(TextInfo textInfo) {
		textInfo.setCreatetime(DateUtil.getSQLDate());
		
		int result=  this.baseDao.save("oracle-textInfoQL.insertTextInfo", textInfo);
		if(result>0){
			String flagString=null;
			switch (textInfo.getEmergency()) {
			case 0:
				flagString="不紧急";
				break;
			case 1:
				flagString="紧急";
				break;
			default:
				break;
			}
			logService.addLogInfo(textInfo.getUserid(), "添加文本信息记录:问题内容:"+textInfo.getContent()
					+";是否紧急:"+flagString
					+";影响数据ID:"+textInfo.getId(), 1);
		}
		return result;
	}

     /**
	 * 查询文本信息
	 * @param textInfo
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckTextInfo(int currentPage, int everyPage,
			TextInfo textInfo) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-textInfoQL.selectCheckTextInfo",
				"oracle-textInfoQL.selectTextInfo", textInfo,currentPage,everyPage);
	}

	/**
	 * 更新文本信息
	 * @param questionInfo
	 * @return
	 */
	//@Log(operationType="update操作:",operationName="更新文本信息")
	@Override
	public int updateTextInfo(TextInfo textInfo) {
		
		int result=  this.baseDao.update("oracle-textInfoQL.updateTextInfo", textInfo);
		if(result>0){
			String flagString=null;
			switch (textInfo.getEmergency()) {
			case 0:
				flagString="不紧急";
				break;
			case 1:
				flagString="紧急";
				break;
			default:
				break;
			}
			logService.addLogInfo(textInfo.getUserid(), "更新问题信息记录:问题内容:"+textInfo.getContent()
					+";是否紧急:"+flagString
					+";影响数据ID:"+textInfo.getId(), 2);
		}
		return result;
	}

	 /**
	 * 查询文本信息发送记录
	 * @param TelCallSend
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckTextInfoSendRecord(int currentPage, int everyPage,
			SendRecord sendRecord) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-textInfoQL.selectCheckTextInfoSendRecord",
				"oracle-textInfoQL.selectTextInfoSendRecord", sendRecord,currentPage,everyPage);
	}

	/**
	 * 根据流水号修改文本信息发送结果
	 * @param sendRecord
	 * @return
	 */
	@Override
	public int updateTextInfoResule(SendRecord sendRecord) {
		return this.baseDao.update("oracle-textInfoQL.updateTextInfoResule", sendRecord);
	}


	/**
	 * 获取文本信息类型列表
	 * @return
	 */
	@Override
	public List<TextInfo> getTextInfoTypeList() {
		return this.baseDao.queryForList("oracle-textInfoQL.getTextInfoTypeList");
	}


	public SysOperateLogService getLogService() {
		return logService;
	}


	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	
	
}
