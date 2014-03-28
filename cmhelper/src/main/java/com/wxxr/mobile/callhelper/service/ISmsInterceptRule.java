package com.wxxr.mobile.callhelper.service;
/**
 * 拦截短信拦截规则
 * @author zhengjincheng
 *
 */
public interface ISmsInterceptRule {
	public boolean isMatch(String smsContent, String targetnumber);
}
