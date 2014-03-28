/**
 * 
 */
package com.wxxr.mobile.callhelper.model;

import com.wxxr.mobile.android.ui.AndroidBindingType;
import com.wxxr.mobile.android.ui.annotation.AndroidBinding;
import com.wxxr.mobile.callhelper.service.IMissCallService;
import com.wxxr.mobile.callhelper.service.IMobileSupportService;
import com.wxxr.mobile.callhelper.service.IPhoneSystemService;
import com.wxxr.mobile.core.ui.annotation.Bean;
import com.wxxr.mobile.core.ui.annotation.Bean.BindingType;
import com.wxxr.mobile.core.ui.annotation.Command;
import com.wxxr.mobile.core.ui.annotation.Field;
import com.wxxr.mobile.core.ui.annotation.Navigation;
import com.wxxr.mobile.core.ui.annotation.Parameter;
import com.wxxr.mobile.core.ui.annotation.UIItem;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.api.InputEvent;
import com.wxxr.mobile.core.ui.common.ViewBase;

/**
 * @author fudapeng
 *
 */
@View(name="MissCallGuideView", description="来电提醒", provideSelection = true)
@AndroidBinding(type=AndroidBindingType.FRAGMENT,layoutId="R.layout.prompt")
public class MissCallGuideView extends ViewBase {
	@Bean(type=BindingType.Service)
	IMissCallService missCallSerivce;
	
	@Bean(type=BindingType.Service)
	IPhoneSystemService phoneSystemService;
	
	@Bean(type=BindingType.Service)
	IMobileSupportService mobileService;
	
	@Field(binding="您还没有漏接电话记录")
	String promptText;
	
	
	@Field(binding="如果您没办理通信助手业务，无法使用此功能")
	String promptText2;
	
	String sendMsgContent;
	
//	@Command(navigations={@Navigation(on = "*", message = "",params = {
//			@Parameter(name = "title", value = "办理通讯助手"),
//			@Parameter(name = "onOK", value = "leftok"),
//			@Parameter(name = "onCanceled", value = "取消")})})
	@Command(navigations={@Navigation(on = "*",showDialog="ConfirmDialog",params={
			@Parameter(name="operation",value="openImmediately")
	})})
	String openImmediately(InputEvent event){
		return "";
	}
	
	
//	@Command(uiItems=@UIItem(id="leftok",label="确定",icon=""))
//	String onOkClick(InputEvent event){
//		missCallSerivce.openBusiness(sendMsgContent);
//		return "";
//	}
}
