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
 * 类名称：PhoneBook    
 * 类描述：电话本    
 * 创建人：zr    
 * 创建时间：2015-6-5 上午10:40:09    
 * 修改人：zr    
 * 修改时间：2015-6-5 上午10:40:09    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class PhoneBook extends BaseInfo{
	
	/**标志*/
	private int mark;
	
	/**电话号码*/
	private String tel;
	
	/**设置类型*/
	private int type;
	
	/**联系人总数*/
	private int count;
	
	/**联系人项*/
	private  List<PhoneBookItems> items = new ArrayList<PhoneBookItems>();

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<PhoneBookItems> getItems() {
		return items;
	}

	public void setItems(List<PhoneBookItems> items) {
		this.items = items;
	}
	
	
	
}
