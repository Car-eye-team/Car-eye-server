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
 * 类名称：FileInfo    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-7-17 下午12:39:01    
 * 修改人：zr    
 * 修改时间：2015-7-17 下午12:39:01    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class FileInfo extends BaseInfo{
	
	/**SD卡存储位置*/
	private String sdpath;
	
	/**文件类型*/
	private int filetype;
	
	/**版本号*/
	private String version;
	
	/**文件名*/
	private String filename;
	
	/**窗口大小*/
	private int windowsize;
	
	/**文件校验*/
	private int verify;
	
	/**文件内容*/
	private String filedata;

	public String getSdpath() {
		return sdpath;
	}

	public void setSdpath(String sdpath) {
		this.sdpath = sdpath;
	}

	public int getFiletype() {
		return filetype;
	}

	public void setFiletype(int filetype) {
		this.filetype = filetype;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getWindowsize() {
		return windowsize;
	}

	public void setWindowsize(int windowsize) {
		this.windowsize = windowsize;
	}

	public int getVerify() {
		return verify;
	}

	public void setVerify(int verify) {
		this.verify = verify;
	}

	public String getFiledata() {
		return filedata;
	}

	public void setFiledata(String filedata) {
		this.filedata = filedata;
	}
	
	
}
