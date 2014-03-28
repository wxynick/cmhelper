/**
 * 
 */
package com.wxxr.mobile.callhelper.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Intent;

import com.wxxr.mobile.android.app.AppUtils;
import com.wxxr.mobile.callhelper.ICallHeplerAppContext;
import com.wxxr.mobile.callhelper.app.bean.SMSInfoBean;
import com.wxxr.mobile.callhelper.service.IMissCallService;
import com.wxxr.mobile.callhelper.service.IPhoneSystemService;
import com.wxxr.mobile.callhelper.service.ISMSInterceptHandler;
import com.wxxr.mobile.callhelper.service.ISMSInterceptService;
import com.wxxr.mobile.callhelper.service.ISmsContentParseService;
import com.wxxr.mobile.callhelper.shell.InterceptSmsShell;
import com.wxxr.mobile.core.microkernel.api.AbstractModule;

/**
 * @author fudapeng
 *
 */
public class SMSInterceptService extends AbstractModule<ICallHeplerAppContext> implements
		ISMSInterceptService {
	
	private List<ISMSInterceptHandler> handlers = Collections.synchronizedList(new ArrayList<ISMSInterceptHandler>());
	
	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ISMSInterceptService#addInterceptHandle(com.wxxr.mobile.callhelper.service.ISMSInterceptHandler)
	 */
	@Override
	public void addInterceptHandle(ISMSInterceptHandler handler) {
		if(!handlers.contains(handler)){
			handlers.add(handler);
		}
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ISMSInterceptService#remoteInterceptHandle(com.wxxr.mobile.callhelper.service.ISMSInterceptHandler)
	 */
	@Override
	public void removeInterceptHandle(ISMSInterceptHandler handler) {
		if(handlers.contains(handler)){
			handlers.remove(handler);
		}
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ISMSInterceptService#onReceive(com.wxxr.mobile.callhelper.app.model.SMSInfo)
	 */
	@Override
	public void onReceive(SMSInfoBean smsInfo) {
		if  (handlers!=null && !handlers.isEmpty()){
			for (ISMSInterceptHandler handler:handlers){
				if (handler.handle(smsInfo)){
					break;
				}
			}
		}
	}

	@Override
	protected void initServiceDependency() {
		addRequiredService(IMissCallService.class);
		addRequiredService(ISmsContentParseService.class);
		addRequiredService(IPhoneSystemService.class);
	}

	@Override
	protected void startService() {
		context.registerService(ISMSInterceptService.class, this);
		addInterceptHandle(new MissCallInterceptHandler());
		setUpShell();
	}
	
	private void setUpShell(){
		Intent in = new Intent(AppUtils.getFramework().getAndroidApplication(), InterceptSmsShell.class);
		AppUtils.getFramework().getAndroidApplication().startService(in);
	}

	@Override
	protected void stopService() {
		
		
	}

}
