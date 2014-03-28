package com.wxxr.mobile.callhelper.service;

import java.util.List;

import com.wxxr.mobile.callhelper.app.bean.SMSInfoBean;
import com.wxxr.mobile.callhelper.app.bean.SMSSessionGroupBean;

/**
 * @author fudapeng
 */
public interface IMissCallService extends ICommmonService,ISmsInterceptRule{
	
	/**
	 * 获得指定时间段的回执信息
	 * @param start
	 * @param end
	 * @return
	 */
	List<SMSSessionGroupBean> getShowListByTime(long start, long end);
	
	/**
	 * 是否开通来电提醒
	 * @return
	 */
	boolean isOpen();
	
	/**
	 * 设置是否开通来电提醒
	 */
	void switchSet();
	
	/**
	 * 获取开通来电提醒的短信命令
	 * @return
	 */
	String getOpenCommand(String province);
	
	/**
	 * 开通业务
	 */
	void openBusiness(String content);
	
	
	/**
	 * 是否显示时间
	 * @param bean
	 * @return
	 */
	boolean showTime(SMSInfoBean bean);
	
	
}	
