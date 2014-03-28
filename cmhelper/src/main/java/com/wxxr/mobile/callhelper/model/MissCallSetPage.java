package com.wxxr.mobile.callhelper.model;

import java.util.Set;

import com.wxxr.mobile.android.ui.AndroidBindingType;
import com.wxxr.mobile.android.ui.annotation.AndroidBinding;
import com.wxxr.mobile.callhelper.service.IMissCallService;
import com.wxxr.mobile.core.ui.annotation.Attribute;
import com.wxxr.mobile.core.ui.annotation.Bean;
import com.wxxr.mobile.core.ui.annotation.Command;
import com.wxxr.mobile.core.ui.annotation.Field;
import com.wxxr.mobile.core.ui.annotation.FieldUpdating;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.annotation.Bean.BindingType;
import com.wxxr.mobile.core.ui.api.AttributeKey;
import com.wxxr.mobile.core.ui.api.InputEvent;
import com.wxxr.mobile.core.ui.common.DataField;
import com.wxxr.mobile.core.ui.common.PageBase;

@View(name = "MissCallSetPage", withToolbar = true, description = "来电提醒", provideSelection = true)
@AndroidBinding(type = AndroidBindingType.FRAGMENT_ACTIVITY, layoutId = "R.layout.misscall_setting")
public class MissCallSetPage extends PageBase {
	@Bean(type=BindingType.Service)
	IMissCallService missCallSerivce;
	
	
	@Field(valueKey="text",binding="开启漏接电话提醒")
	String lable;
	
	@Bean(type=BindingType.Pojo,express="${missCallSerivce.isOpen()}")
	Boolean open;
	
	@Field(binding="${open}",attributes={@Attribute(name="backgroundImageURI",value="${missCallSerivce.isOpen() ? 'resourceId:drawable/gd_setting_switch_open' : 'resourceId:drawable/gd_setting_switch_close'}")})
	Boolean isOpen;
	
	@Command
	String setSwitch(InputEvent envet){
		missCallSerivce.switchSet();
		DataField<String> child = (DataField<String>) getChild("isOpen");
		if(child != null){
			Set set = child.getAttributeKeys();
			for(Object key : set){
				 AttributeKey item = (AttributeKey) key;
				 if(item.getName().equals("backgroundImageURI")){
					 if(missCallSerivce.isOpen())
					 child.setAttribute(item,"resourceId:drawable/gd_setting_switch_open");
					 else
					 child.setAttribute(item,"resourceId:drawable/gd_setting_switch_close");
				 }
			}
		}
		return "";
	}
	void xx(){
		missCallSerivce.isOpen();
	}
}
