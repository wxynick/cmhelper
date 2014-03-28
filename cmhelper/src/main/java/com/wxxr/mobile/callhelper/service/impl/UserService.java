/**
 * 
 */
package com.wxxr.mobile.callhelper.service.impl;

import com.wxxr.mobile.callhelper.ICallHeplerAppContext;
import com.wxxr.mobile.callhelper.app.bean.UserDetailBean;
import com.wxxr.mobile.callhelper.app.model.UserDetail;
import com.wxxr.mobile.callhelper.service.IUserService;
import com.wxxr.mobile.core.microkernel.api.AbstractModule;

/**
 * @author fudapeng
 * @param <T>
 *
 */
public class UserService extends AbstractModule<ICallHeplerAppContext> implements IUserService {

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IUserService#getUserDetail()
	 */
	
	private UserDetailBean currentUserDetail = new UserDetailBean();
	@Override
	public UserDetailBean getUserDetail() {
		return currentUserDetail;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IUserService#updateUserDetail(com.wxxr.mobile.callhelper.app.model.UserDetail)
	 */
//	@Override
	public boolean updateUserDetail(UserDetailBean newValue) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IUserService#uploadIcon(java.lang.String, byte[])
	 */
	@Override
	public boolean uploadIcon(String deviceId, byte[] icon) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IUserService#findIcon(java.lang.String)
	 */
	@Override
	public byte[] findIcon(String deviceId) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IUserService#setNickName(java.lang.String)
	 */
	@Override
	public void setNickName(String name) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IUserService#setGender(java.lang.String)
	 */
	@Override
	public void setGender(String gender) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IUserService#setAge(int)
	 */
	@Override
	public void setAge(int age) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IUserService#setProvince(java.lang.String)
	 */
	@Override
	public void setProvince(String province) {
		

	}

	@Override
	protected void initServiceDependency() {
		
		
	}

	@Override
	protected void startService() {
		context.registerService(IUserService.class, this);
		
	}

	@Override
	protected void stopService() {
		
		
	}

}
