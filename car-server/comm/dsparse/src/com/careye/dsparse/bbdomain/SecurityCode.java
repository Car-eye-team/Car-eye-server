/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：SecurityCode    
 * 类描述：防伪码    
 * 创建人：zr    
 * 创建时间：2015-5-15 上午11:53:14    
 * 修改人：zr    
 * 修改时间：2015-5-15 上午11:53:14    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class SecurityCode extends BaseInfo{
	
	/**显示方式 */
	private int style;
	
	/**显示字符的颜色*/
	private int color;
	
	/**显示内容*/
	private int content;
	
	/**延迟*/
	private int delayed;
	
	/**显示时间*/
	private int time;

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getContent() {
		return content;
	}

	public void setContent(int content) {
		this.content = content;
	}

	public int getDelayed() {
		return delayed;
	}

	public void setDelayed(int delayed) {
		this.delayed = delayed;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
	
}
