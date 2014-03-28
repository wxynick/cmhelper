package com.wxxr.mobile.callhelper.app.model;


import com.wxxr.mobile.core.annotation.BindableBean;


/**
 * @author fudapeng
 */
@BindableBean(pkg="com.wxxr.mobile.callhelper.app.bean",className="ContactPersonBean")
public class ContactPerson {
	/**
	 * 名字 
	 */
	private String fristName;
	
	/**
	 * 姓
	 */
	private String lastName;
	
	/**
	 * 头像
	 */
	private String portrait;
	
	/**
	 * 手机电话号码
	 */
	private String phoneNumber;
}
