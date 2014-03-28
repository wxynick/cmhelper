package com.wxxr.mobile.callhelper.service;

/**
 * 
 * @author fudapeng
 *
 */
public interface IFeedBackService {
	/**
	 * 增加反馈 
	 * @param content
	 * @param deviceId
	 * @param type
	 */
	void addFeedBack(String content,String deviceId,Integer type);
}
