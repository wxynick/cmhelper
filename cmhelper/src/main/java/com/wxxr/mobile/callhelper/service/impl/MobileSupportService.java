/**
 * 
 */
package com.wxxr.mobile.callhelper.service.impl;

import com.wxxr.mobile.callhelper.ICallHeplerAppContext;
import com.wxxr.mobile.callhelper.service.IMobileSupportService;
import com.wxxr.mobile.core.microkernel.api.AbstractModule;

/**
 * @author fudapeng
 *
 */
public class MobileSupportService extends AbstractModule<ICallHeplerAppContext> implements IMobileSupportService{

	@Override
	public String getServicePhoneNumber(MobileServerType type) {
		
		return null;
	}

	@Override
	protected void initServiceDependency() {
		
		
	}

	@Override
	protected void startService() {
		context.registerService(IMobileSupportService.class, this);
	}

	@Override
	protected void stopService() {
		
	}

}
