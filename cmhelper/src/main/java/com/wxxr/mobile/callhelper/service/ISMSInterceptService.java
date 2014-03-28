package com.wxxr.mobile.callhelper.service;

import com.wxxr.mobile.callhelper.app.bean.SMSInfoBean;

public interface ISMSInterceptService {
	
	void addInterceptHandle(ISMSInterceptHandler handler);
	
	void removeInterceptHandle(ISMSInterceptHandler handler);
	
	/**
	 * 当接收到短信时，遍历handler 处理短信
	 * @param smsInfo
	 */
	void onReceive(SMSInfoBean smsInfo);
	
}
