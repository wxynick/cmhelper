package com.wxxr.mobile.callhelper.app.model;

import com.wxxr.mobile.core.annotation.BindableBean;

/**
 * 
 * @author fudapeng
 * 短信的基本信息
 */
@BindableBean(pkg="com.wxxr.mobile.callhelper.app.bean",className="SMSInfoBean")
public class SMSInfo {
	private Integer mstyle;//短信类型  0-收  1-发
	private String phoneNumber;//短信号码  --- 发送方的电话号码  通过这个可以找到联系人
	private String content;//短信内容 ---
	private long cdate;// 短信时间 
	private Integer id;// ---
	private String amonth;//短信时间  月份
	private boolean isStorage;//是否存储
	private SMSType type;
	private boolean read;//是否已读
}
