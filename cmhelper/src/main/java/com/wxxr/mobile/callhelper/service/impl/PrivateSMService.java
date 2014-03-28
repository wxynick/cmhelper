/**
 * 
 */
package com.wxxr.mobile.callhelper.service.impl;

import java.util.List;

import com.wxxr.mobile.callhelper.ICallHeplerAppContext;
import com.wxxr.mobile.callhelper.app.bean.SMSInfoBean;
import com.wxxr.mobile.callhelper.app.bean.SMSSessionGroupBean;
import com.wxxr.mobile.callhelper.app.model.SMSInfo;
import com.wxxr.mobile.callhelper.app.model.SMSSessionGroup;
import com.wxxr.mobile.callhelper.service.IPrivateSMService;
import com.wxxr.mobile.core.microkernel.api.AbstractModule;

/**
 * @author fudapeng
 *
 */
public class PrivateSMService extends AbstractModule<ICallHeplerAppContext> implements
		IPrivateSMService {

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
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#getBindedEmail()
	 */
	@Override
	public String getBindedEmail() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#setBindedEmail(java.lang.String)
	 */
	@Override
	public void setBindedEmail(String email) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#isPasswordSetup()
	 */
	@Override
	public boolean isPasswordSetup() {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#setupPassword(java.lang.String)
	 */
	@Override
	public void setupPassword(String passwd) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#verifyPassword(java.lang.String)
	 */
	@Override
	public boolean verifyPassword(String password) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#getPassword()
	 */
	@Override
	public String getPassword() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#addPrivateNumber(java.lang.String)
	 */
	@Override
	public boolean addPrivateNumber(String number) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#addPrivateNumberMore(java.util.List)
	 */
	@Override
	public void addPrivateNumberMore(List<String> numbers) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#isAPrivateNumber(java.lang.String)
	 */
	@Override
	public boolean isAPrivateNumber(String number) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#getMoveOutgoing2PrivateOutbox()
	 */
	@Override
	public Boolean getMoveOutgoing2PrivateOutbox() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#setMoveOutgoing2PrivateOutbox(boolean)
	 */
	@Override
	public void setMoveOutgoing2PrivateOutbox(boolean bool) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#addPrivateMessage(com.wxxr.mobile.callhelper.app.model.SMSSessionGroup)
	 */
	@Override
	public void addPrivateMessage(SMSSessionGroup msg) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#addSMSInfo(com.wxxr.mobile.callhelper.app.model.SMSInfo)
	 */
	@Override
	public void addSMSInfo(SMSInfo msg) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#removePrivateNumber(java.lang.String)
	 */
	@Override
	public boolean removePrivateNumber(String number) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#getAllPrivateNumber()
	 */
	@Override
	public List<String> getAllPrivateNumber() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#getContactName(java.lang.String)
	 */
	@Override
	public String getContactName(String number) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#deleteMessage(java.lang.Integer)
	 */
	@Override
	public void deleteMessage(Integer msgId) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#deleteMessage(com.wxxr.mobile.callhelper.app.model.SMSInfo)
	 */
	@Override
	public void deleteMessage(SMSInfo msg) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#deleteMessageGroup(com.wxxr.mobile.callhelper.app.model.SMSSessionGroup)
	 */
	@Override
	public void deleteMessageGroup(SMSSessionGroupBean msg) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPrivateSMService#deleteMessage(java.lang.String, boolean)
	 */
	@Override
	public void deleteMessage(String number, boolean needfreshcache) {
		

	}

	@Override
	protected void initServiceDependency() {
		
		
	}

	@Override
	protected void startService() {
		context.registerService(IPrivateSMService.class, this);
	}

	@Override
	protected void stopService() {
		
		
	}

	@Override
	public void saveMessage(SMSInfoBean bean) {
		// TODO Auto-generated method stub
		
	}

}
