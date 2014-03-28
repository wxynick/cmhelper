/**
 * 
 */
package com.wxxr.mobile.callhelper.service.impl;

import java.util.List;

import com.wxxr.mobile.callhelper.ICallHeplerAppContext;
import com.wxxr.mobile.callhelper.app.bean.SMSInfoBean;
import com.wxxr.mobile.callhelper.app.bean.SMSSessionGroupBean;
import com.wxxr.mobile.callhelper.app.model.Channel;
import com.wxxr.mobile.callhelper.app.model.DXHZSetting;
import com.wxxr.mobile.callhelper.app.model.SMSInfo;
import com.wxxr.mobile.callhelper.app.model.SMSSessionGroup;
import com.wxxr.mobile.callhelper.service.IDXHZService;
import com.wxxr.mobile.core.microkernel.api.AbstractModule;

/**
 * @author fudapeng
 *
 */
public class DXHZService extends AbstractModule<ICallHeplerAppContext> implements IDXHZService {

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#deleteMessage(java.lang.Integer)
	 */
	@Override
	public void deleteMessage(Integer msgId) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#deleteMessage(com.wxxr.mobile.callhelper.app.model.SMSInfo)
	 */
	@Override
	public void deleteMessage(SMSInfo msg) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#deleteMessageGroup(com.wxxr.mobile.callhelper.app.model.SMSSessionGroup)
	 */
	@Override
	public void deleteMessageGroup(SMSSessionGroupBean msg) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#deleteMessage(java.lang.String, boolean)
	 */
	@Override
	public void deleteMessage(String number, boolean needfreshcache) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#getAllUnreadSize()
	 */
	@Override
	public int getAllUnreadSize() {
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#getUnreadSizeOfPhoneNumber(java.lang.String)
	 */
	@Override
	public int getUnreadSizeOfPhoneNumber(String num) {
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#setReadMsgOfPhoneNumber(java.lang.String)
	 */
	@Override
	public void setReadMsgOfPhoneNumber(String num) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#setUnReadMsgOfPhoneNumber(java.lang.String)
	 */
	@Override
	public void setUnReadMsgOfPhoneNumber(String num) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#getShowList()
	 */
	@Override
	public List<SMSSessionGroupBean> getShowList() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IDXHZService#isOpen()
	 */
	@Override
	public boolean isOpen() {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IDXHZService#getSessionSmsByPhoneNumber(java.lang.String)
	 */
	@Override
	public List<SMSInfo> getSessionSmsByPhoneNumber(String number) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IDXHZService#switchSet(int)
	 */
	@Override
	public boolean switchSet(int what) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IDXHZService#setChannels(java.util.List)
	 */
	@Override
	public boolean setChannels(List<Channel> channels) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IDXHZService#getChannels()
	 */
	@Override
	public List<Channel> getChannels() {
		
		return null;
	}


	@Override
	protected void initServiceDependency() {
		
		
	}

	@Override
	protected void startService() {
		context.registerService(IDXHZService.class, this);
	}

	@Override
	protected void stopService() {
		
		
	}

	@Override
	public boolean getSomeOneSet(int what) {
		return false;
	}

	@Override
	public void saveMessage(SMSInfoBean bean) {
	}

}
