/**
 * 
 */
package com.wxxr.mobile.callhelper.service;

import java.util.List;

import com.wxxr.mobile.callhelper.app.model.Channel;
import com.wxxr.mobile.callhelper.app.model.DXHZSetting;
import com.wxxr.mobile.callhelper.app.model.SMSInfo;
import com.wxxr.mobile.callhelper.app.model.SMSSessionGroup;

/**
 * @author fudapeng
 *
 */
public interface IDXHZService extends ICommmonService {
	
	/**
	 * 是否开通短信回执
	 * @return
	 */
	boolean isOpen();
	
	/**
	 * 获得回执
	 * @return
	 */
	List<SMSInfo> getSessionSmsByPhoneNumber(String number);
	
	/**
	 * 短信回执设置 --- 除了频道设置
	 * @param what
	 * @return
	 */
	boolean switchSet(int what);
	
	
	/**
	 * 短信回执设置 --- 频道设置
	 * @param what
	 * @return
	 */
	boolean setChannels(List<Channel> channels);
	
	
	/**
	 * 获取用户定制短信的频道信息
	 * @param what
	 * @return
	 */
	List<Channel>  getChannels();
	
	
	/**
	 * 获取短信回执设置 --- 除了频道设置
	 * @param what
	 * @return
	 */
	boolean getSomeOneSet(int what);
	
	
//	/**
//	 * 获取短信回执设置
//	 * @return
//	 */
//	DXHZSetting getDXHZSetting();
//	
//	
//	/**
//	 * 设置短信回执
//	 * @param setting
//	 * @return
//	 */
//	boolean setDXHZSetting(DXHZSetting setting);
	
}
