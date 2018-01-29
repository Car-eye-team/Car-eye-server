/**
* Description: car-eye车辆管理平台
* 文件名：CarService.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.service;

import java.util.List;
import java.util.Map;

import com.careye.message.domain.SendRecord;
import com.careye.message.domain.TextInfo;


/**
 * @项目名称：FMS
 * @类名称：TextInfoService
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:00:03
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public interface TextInfoService {
	
	/**
	 * id查询文本信息
	 * @param textInfo
	 * @return
	 */
	public TextInfo selectTextInfoById(int id);
	
	
	/**
	 * 更新文本信息发送记录
	 * @param textInfo
	 * @return
	 */
	public int updateTextInfoSendRecord(SendRecord sendRecord);
	
	/**
	 * 根据流水号修改文本信息发送结果
	 * @param sendRecord
	 * @return
	 */
	public int updateTextInfoResule(SendRecord sendRecord);
	
	
	/**
	 * 添加文本信息发送记录
	 * @param textInfo
	 * @return
	 */
	public int insertTextInfoSendRecord(SendRecord sendRecord);
	

	
	/**
	 * 更新文本信息
	 * @param textInfo
	 * @return
	 */
	public int updateTextInfo(TextInfo textInfo);
	
	
	/**
	 * 删除文本信息
	 * @param id
	 * @return
	 */
	public int deleteTextInfo(int id);
	
	/**
	 * 添加文本信息
	 * @param textInfo
	 * @return
	 */
	public int insertTextInfo(TextInfo textInfo);
	
	
	/**
	 * 条件查询文本信息
	 * @param textInfo
	 * @return
	 */
	public Map selectCheckTextInfo(final int currentPage, final int everyPage,TextInfo textInfo);

	/**
	 * 条件查询文本信息发送记录
	 * @param TelCallSend
	 * @return
	 */
	public Map selectCheckTextInfoSendRecord(final int currntPage, final int everyPage,SendRecord sendRecord);
	
	/**
	 * 获取文本信息类型列表
	 * @return
	 */
	public List<TextInfo> getTextInfoTypeList();

	
	
}
