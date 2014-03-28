/**
 * 
 */
package com.wxxr.mobile.callhelper.service;

/**
 * @author fudapeng
 *
 */
public interface IMobileSupportService {
	enum MobileServerType{
		DXHZ,LIANTXING
	}
	String getServicePhoneNumber(MobileServerType type);
}
