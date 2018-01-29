/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：QuestioningAnswer    
 * 类描述：提问应答    
 * 创建人：zr    
 * 创建时间：2015-6-4 下午07:28:53    
 * 修改人：zr    
 * 修改时间：2015-6-4 下午07:28:53    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class QuestioningAnswer {

	/** 应答流水号  **/
	private  int seqR;
	
	/**答案id **/
	private  int answer;

	public int getSeqR() {
		return seqR;
	}

	public void setSeqR(int seqR) {
		this.seqR = seqR;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}
	
	
	
}
