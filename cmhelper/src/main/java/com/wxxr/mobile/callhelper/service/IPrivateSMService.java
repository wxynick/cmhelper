/**
 * 
 */
package com.wxxr.mobile.callhelper.service;

import java.util.List;

import com.wxxr.mobile.callhelper.app.model.SMSInfo;
import com.wxxr.mobile.callhelper.app.model.SMSSessionGroup;


/**
 * @author neillin
 *
 */
public interface IPrivateSMService extends ICommmonService{
	/**
	 * 得到绑定的mail
	 * @return
	 */
	String getBindedEmail();
	
	/**
	 * 设置绑定的mail
	 * @return
	 */
	void setBindedEmail(String email);
    
	/**
	 * 是否密码建立 
	 * @return
	 */
	boolean isPasswordSetup();
	
	/**
	 * 设置密码
	 * @param passwd
	 */
	void setupPassword(String passwd);
	
	/**
	 * 验证密码
	 * @param password
	 * @return
	 */
	boolean verifyPassword(String password);
	
	
	/**
	 * 获取验证密码
	 * @param password
	 * @return
	 */
	String getPassword();
	
	/**
	 * 添加单个联系人
	 * @param number
	 * @return
	 */
	boolean addPrivateNumber(String number);
	
	/**
	 * 添加多个联系人
	 * @param numbers
	 */
	void addPrivateNumberMore(List<String> numbers);
	
	/**
	 * 判断号码是否已经在私密联系人
	 * @param number
	 * @return
	 */
	boolean isAPrivateNumber(String number);
	
	/**
	 * 暂时好象没有用上
	 * @return
	 */
	Boolean getMoveOutgoing2PrivateOutbox();
	
	/**
	 * 同上
	 * @param bool
	 */
	void setMoveOutgoing2PrivateOutbox(boolean bool);
	
	/**
	 * 增加私密短信 开始私密人没有短信，后来的短信
	 * @param msg
	 */
	void addPrivateMessage(SMSSessionGroup msg);
	
	
	/**
	 * 某个人来了短信, 添加进去
	 * @param msg
	 */
	void addSMSInfo(SMSInfo msg);
	
	/**
	 * 删除私密联系人
	 * @param number
	 * @return
	 */
	boolean removePrivateNumber(String number);
	
	/**
	 * 得到所有的私密联系人的电话号码
	 * @return
	 */
	List<String> getAllPrivateNumber();
	
	/**
	 * 通过号码得到联系人的名字
	 * @param number
	 * @return
	 */
	String getContactName(String number);
	
	
//	/**
//	 * 删除短信
//	 * @param msgId
//	 */
//	void deleteMessage(Integer msgId);
//	
//	/**
//	 * 删除短信
//	 * @param msg
//	 */
//	void deleteMessage(SMSInfo msg);
//	
//	
//	/**
//	 * 删除短信分组
//	 * @param msg
//	 */
//	void deleteMessageGroup(SMSSessionGroup msg);
//	
//	/**
//	 * 删除短信
//	 * @param msg
//	 */
//	void deleteMessage(String number,boolean needfreshcache);
}
