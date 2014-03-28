/**
 * 
 */
package com.wxxr.mobile.callhelper.app.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;

import com.wxxr.mobile.core.annotation.BindableBean;
import com.wxxr.mobile.core.util.StringUtils;

/**
 * @author fudapeng
 *
 */
@BindableBean(pkg="com.wxxr.mobile.callhelper.app.bean",className="PrivateSMSettingBean")
public class PrivateSMSetting extends Setting{
	
	/**
	 * 是否开启私密锁
	 */
	private boolean isOpenPrivateSms;
	
	/**
	 * 是否在进入已经建立私密锁密码
	 */
	private boolean isPasswordSetup;
	
	/**
	 * 绑定的mail
	 */
	private String bindingMail;
	
	/**
	 * 添加的私密人的手记号
	 */
	private List<String> phoneNumber;
	
	/**
	 * 私密锁密码
	 */
	private String password;
	
}
