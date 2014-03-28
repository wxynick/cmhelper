package com.wxxr.mobile.callhelper.model;

import com.wxxr.mobile.android.ui.AndroidBindingType;
import com.wxxr.mobile.android.ui.annotation.AndroidBinding;
import com.wxxr.mobile.callhelper.service.IMissCallService;
import com.wxxr.mobile.core.ui.annotation.Bean;
import com.wxxr.mobile.core.ui.annotation.Command;
import com.wxxr.mobile.core.ui.annotation.Field;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.annotation.Bean.BindingType;
import com.wxxr.mobile.core.ui.api.InputEvent;
import com.wxxr.mobile.core.ui.common.PageBase;

@View(name = "MissCallSetView", withToolbar = true, description = "来电提醒", provideSelection = true)
@AndroidBinding(type = AndroidBindingType.FRAGMENT_ACTIVITY, layoutId = "R.layout.home_page_new")
public class MissCallSetView extends PageBase {
	@Bean(type=BindingType.Service)
	IMissCallService missCallSerivce;
	
	@Field(valueKey="enabled",binding="${missCallSerivce.isOpen()}")
	boolean isOpen;
	
	@Command
	String setSwitch(InputEvent envet){
		missCallSerivce.switchSet();
		return "";
	}
	void xx(){
		missCallSerivce.isOpen();
	}
}
