package com.wxxr.mobile.callhelper.service;

public interface IMissCallSmsInterceptRule extends ISmsInterceptRule {
	/**
	 * 返回省份名称  如BJ 北京，FJ 福建
	 * @return
	 */
	public String getName();

}
