/**
 * 
 */
package com.wxxr.mobile.callhelper.service.impl;

import com.wxxr.mobile.callhelper.ICallHeplerAppContext;
import com.wxxr.mobile.callhelper.service.IFeedBackService;
import com.wxxr.mobile.core.microkernel.api.AbstractModule;

/**
 * @author fudapeng
 *
 */
public class FeedBackService extends AbstractModule<ICallHeplerAppContext> implements
		IFeedBackService {

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IFeedBackService#addFeedBack(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public void addFeedBack(String content, String deviceId, Integer type) {

	}

	@Override
	protected void initServiceDependency() {
		
		
	}

	@Override
	protected void startService() {
		context.registerService(IFeedBackService.class, this);
	}

	@Override
	protected void stopService() {
		
		
	}

}
