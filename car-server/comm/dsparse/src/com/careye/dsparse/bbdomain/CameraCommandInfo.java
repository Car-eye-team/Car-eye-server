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
 * 类名称：CameraCommandInfo    
 * 类描述：摄像头立即拍摄    
 * 创建人：zr    
 * 创建时间：2015-6-6 下午02:14:36    
 * 修改人：zr    
 * 修改时间：2015-6-6 下午02:14:36    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class CameraCommandInfo extends BaseInfo{

	/** 通道 ID  **/
	private int channel;
	
	/**  拍摄命令 **/
	private int cmd;
	
	/**  拍照间隔/录像时间 **/
	private int inv;
	
	/** 保存标志  **/
	private int save;
	
	/** 分辨率**/
	private int screen;
	
	/** 图像/视频质量  **/
	private int quality;
	
    /**  亮度 **/
	private int bright;
	/** 对比度 **/
	private int contrast;
	
	/**饱和度 **/
	private int saturation;
	
	/**色度 **/
	private int chroma;

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public int getInv() {
		return inv;
	}

	public void setInv(int inv) {
		this.inv = inv;
	}

	public int getSave() {
		return save;
	}

	public void setSave(int save) {
		this.save = save;
	}

	public int getScreen() {
		return screen;
	}

	public void setScreen(int screen) {
		this.screen = screen;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getBright() {
		return bright;
	}

	public void setBright(int bright) {
		this.bright = bright;
	}

	public int getContrast() {
		return contrast;
	}

	public void setContrast(int contrast) {
		this.contrast = contrast;
	}

	public int getSaturation() {
		return saturation;
	}

	public void setSaturation(int saturation) {
		this.saturation = saturation;
	}

	public int getChroma() {
		return chroma;
	}

	public void setChroma(int chroma) {
		this.chroma = chroma;
	}
	
	
}
