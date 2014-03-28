package com.wxxr.mobile.callhelper.service;

import com.wxxr.mobile.callhelper.app.bean.RegionBean;

public interface IAffiliationAreaService {
	/**
	 * 根据手机号/区号 查找归属地 （异步） 1.手机号码 必须十一位 2.区号 必须是3位或者4位
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public RegionBean getRegionByPhoneNumber(String phoneNumber);

	/**
	 * 查找短信内容中，手机号码的归属地
	 * 
	 * @param smsContent
	 * @return
	 */
	public RegionBean getRegionBySmsContentAsyn(String smsContent);
	/**
	 * 根据拨打的号码 可能带前缀
	 * 
	 * @param dialogNumber
	 * @param m
	 */
	public RegionBean getRegionByDialogNumberAsyn(final String dialogNumber);
	/**
	 * 同步方式 根据拨打的号码 可能带前缀
	 * 
	 * @param dialogNumber
	 * @param m
	 */
	public RegionBean getRegionByDialogNumber(String dialogNumber);
	/**
	 * 同步方式
	 * 
	 * @param smsContent
	 * @return
	 */
	public RegionBean getRegionBySmsContent(String smsContent);
	/**
	 * 同步方式
	 * 
	 * @param msisdn
	 *            手机号码必须是11位
	 * @return
	 */
	public RegionBean getRegionByMsisdn(String msisdn);
	/**
	 * 是否是移动手机号码
	 * 
	 * @param msisdn
	 */

	public boolean isChinaMobileMsisdn(String msisdn);
}
