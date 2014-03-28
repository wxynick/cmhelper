package com.wxxr.mobile.callhelper.app.model;

import java.util.List;

import com.wxxr.mobile.core.annotation.BindableBean;

/**
 * @author fudapeng
 */
@BindableBean(pkg="com.wxxr.mobile.callhelper.app.bean",className="DXHZSettingBean")
public class DXHZSetting {
	
	/**
	 * 那些是你感兴趣的附件咨询
	 */
	private List<Channel> channels;
	
	/**
	 * 是否每条短信都要保存
	 */
	private boolean everyHZ;
	
	/**
	 * 是否保存在手机收件箱中
	 */
	private boolean isSaveInPhoneBox;
	
	/**
	 * 聊天模式
	 */
	private boolean chatMode;
	
	/**
	 * 夜间免打扰
	 */
	private boolean noDisturbingInNight;
	
	/**
	 * 回执报告提醒
	 */
	private boolean hzreportRemind; 
	
	
}
