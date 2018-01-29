/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import java.util.ArrayList;
import java.util.List;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：QuestionsSend    
 * 类描述：提问下发    
 * 创建人：zr    
 * 创建时间：2015-6-4 下午07:06:06    
 * 修改人：zr    
 * 修改时间：2015-6-4 下午07:06:06    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class QuestionsSend extends BaseInfo{
	
	/** 标志  **/
	private  int  flag;
	
	/** 问题内容长度  **/
	private  int  len;
	
	/** 问题  **/
	private  String  content;
	
	/** 候选答案列表  **/
	private  List<QuesSendCandidateAnswer> items = new ArrayList<QuesSendCandidateAnswer>();

	/** 1：紧急   **/
	private  int tag0;
	
	/** 1：保留   **/
	private  int tag1;
	
	/** 1：保留   **/
	private  int tag2;
	
	/** 1：终端TTS播读  **/
	private  int tag3;
	
	/** 1：广告屏显示   **/
	private  int tag4;
	
	/** 1：保留    **/
	private  int tag5;
	
	/** 1：保留     **/
	private  int tag6;
	
	/** 保留   **/
	private  int tag7;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<QuesSendCandidateAnswer> getItems() {
		return items;
	}

	public void setItems(List<QuesSendCandidateAnswer> items) {
		this.items = items;
	}

	public int getTag0() {
		return tag0;
	}

	public void setTag0(int tag0) {
		this.tag0 = tag0;
	}

	public int getTag1() {
		return tag1;
	}

	public void setTag1(int tag1) {
		this.tag1 = tag1;
	}

	public int getTag2() {
		return tag2;
	}

	public void setTag2(int tag2) {
		this.tag2 = tag2;
	}

	public int getTag3() {
		return tag3;
	}

	public void setTag3(int tag3) {
		this.tag3 = tag3;
	}

	public int getTag4() {
		return tag4;
	}

	public void setTag4(int tag4) {
		this.tag4 = tag4;
	}

	public int getTag5() {
		return tag5;
	}

	public void setTag5(int tag5) {
		this.tag5 = tag5;
	}

	public int getTag6() {
		return tag6;
	}

	public void setTag6(int tag6) {
		this.tag6 = tag6;
	}

	public int getTag7() {
		return tag7;
	}

	public void setTag7(int tag7) {
		this.tag7 = tag7;
	}

	
}
