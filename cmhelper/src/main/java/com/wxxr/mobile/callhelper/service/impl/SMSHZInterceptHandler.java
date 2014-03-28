/**
 * 
 */
package com.wxxr.mobile.callhelper.service.impl;

import com.wxxr.mobile.callhelper.app.bean.SMSInfoBean;
import com.wxxr.mobile.callhelper.app.model.SMSInfo;
import com.wxxr.mobile.callhelper.service.ISMSInterceptHandler;

/**
 * 检查短信是否是回执短信
 * @author fudapeng
 *
 */
public class SMSHZInterceptHandler implements ISMSInterceptHandler {

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ISmsHandler#handle(com.wxxr.mobile.callhelper.app.model.SmsInfo)
	 */
	@Override
	public boolean handle(SMSInfoBean smsInfo) {
		return false;
	}

}
