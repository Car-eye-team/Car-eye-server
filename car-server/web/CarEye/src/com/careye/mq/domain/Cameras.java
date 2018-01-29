/**
* Description: 多森商用车平台
* 文件名：Cameras.java
* 版本信息：1.0
* 日期：2014-5-29
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.mq.domain;

/**
 * @项目名称：FMS
 * @类名称：Cameras
 * @类描述：摄像机属性
 * @创建人：zr
 * @创建时间：2014-5-29 下午03:59:13
 * @修改人：zr
 * @修改时间：2014-5-29 下午03:59:13
 * @修改备注：
 * @version 1.0
 */
public class Cameras {

	/**通道ID*/
	private int channel;
	
	/**拍摄命令 0表示停止拍摄；0xFFFF表示录像；其他表示拍照张数*/
	private int cmd;
	
	/**拍照间隔/录像时间 秒，0表示按最小间隔拍照或一直录像*/
	private int inv;
	
	/**保持标志 1：保存；0：实时上传*/
	private int save;
	
	/**分辨率 0x01:320*210;
			0x02:640*480:
			0x03:800*600;
			0x04:1024*768;
			0x05:176*144;[Qcif];
			0x06:352*288;[Cif];
			0x07:704*288;[HALF D1];
			0x08:701*576;[D1];
			*/
	private int screen;
	
	/**图像/视频质量 1-10,  1代表质量损失最小，10表示压缩比最大*/
	private int quality;
	
	/**亮度*/
	private int bright;
	
	/**对比度*/
	private int contrast;
	
	/**饱和度*/
	private int saturation;
	
	/**色度*/
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
