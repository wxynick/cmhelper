/**
 * 
 */
package com.wxxr.mobile.callhelper.service;

/**
 * @author fudapeng
 *
 */
public interface ITimeService {
	TimeBean getTime();
	void startTicking();
	void stopTicking();
}
