package com.wxxr.mobile.callhelper.service;


public interface ISmsContentParseService {
	/*
	 * 解析短信内容归属地及手机号码 的通讯录名称
	 */
	public String parseSmsContentAsyn(String content);
	/**
	 * 解析短信内容归属地及手机号码 的通讯录名称
	 * @param content
	 * @param m
	 */
	public String parseSmsContent(String content);


}
