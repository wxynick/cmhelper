package com.wxxr.mobile.callhelper.model;

import java.util.List;

import com.wxxr.mobile.android.ui.AndroidBindingType;
import com.wxxr.mobile.android.ui.annotation.AndroidBinding;
import com.wxxr.mobile.callhelper.app.model.SMSSessionGroup;
import com.wxxr.mobile.callhelper.service.IMissCallService;
import com.wxxr.mobile.core.ui.annotation.Bean;
import com.wxxr.mobile.core.ui.annotation.Field;
import com.wxxr.mobile.core.ui.annotation.Bean.BindingType;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.common.ViewBase;

@View(name = "MissCallView", description = "通信")
@AndroidBinding(type = AndroidBindingType.FRAGMENT, layoutId = "R.layout.common_session_list")
abstract class MissCallView extends ViewBase{
	
	@Bean(type=BindingType.Service)
	IMissCallService missCallSerivce;
	
	@Field(valueKey="options",binding="${missCallSerivce.getShowList()}")
	List<SMSSessionGroup> smsgroup;
	
	void xx(){
	}
	
	
	
}
