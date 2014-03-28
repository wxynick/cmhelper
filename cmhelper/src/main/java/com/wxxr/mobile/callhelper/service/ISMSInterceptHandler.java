package com.wxxr.mobile.callhelper.service;

import com.wxxr.mobile.callhelper.app.bean.SMSInfoBean;
import com.wxxr.mobile.callhelper.app.model.SMSInfo;


public interface ISMSInterceptHandler {
    /**
     * @param smsContent 短信内容
     * @param targetnumber 接收短信的对方号码
     * @param dontstore  是否闪信
     * @return boolean 是否满足拦截条件
     */
    public boolean handle(SMSInfoBean smsInfo);
}
