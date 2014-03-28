package com.wxxr.mobile.callhelper.app.model;

import android.graphics.Bitmap;

import com.wxxr.mobile.core.annotation.BindableBean;

@BindableBean(pkg="com.wxxr.mobile.callhelper.app.bean",className="UserDetailBean")
public class UserDetail {
	/**用户名*/
	private String userName;
	/**昵称*/
	private String nickName;
	/**手机*/
	private String moblie;
	/**头像*/
	private Bitmap icon;
	
	//日期格式 20080101
	private int birthday;
	private boolean male;
	
	private Boolean updated;

    private String province;
}
