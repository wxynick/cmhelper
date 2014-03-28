/**
 * 
 */
package com.wxxr.mobile.callhelper.model;

import java.util.List;

import com.wxxr.mobile.android.ui.AndroidBindingType;
import com.wxxr.mobile.android.ui.annotation.AndroidBinding;
import com.wxxr.mobile.callhelper.app.bean.SMSSessionGroupBean;
import com.wxxr.mobile.callhelper.service.ICallRecorderService;
import com.wxxr.mobile.callhelper.service.IDXHZService;
import com.wxxr.mobile.callhelper.service.IMissCallService;
import com.wxxr.mobile.callhelper.service.IPrivateSMService;
import com.wxxr.mobile.core.log.api.Trace;
import com.wxxr.mobile.core.ui.annotation.Bean;
import com.wxxr.mobile.core.ui.annotation.Bean.BindingType;
import com.wxxr.mobile.core.ui.annotation.Command;
import com.wxxr.mobile.core.ui.annotation.Menu;
import com.wxxr.mobile.core.ui.annotation.Navigation;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.api.IMenu;
import com.wxxr.mobile.core.ui.api.InputEvent;
import com.wxxr.mobile.core.ui.common.ViewBase;

@View(name="MainHomeView", description="通信助手")
@AndroidBinding(type=AndroidBindingType.FRAGMENT,layoutId="R.layout.main_home_view")
public abstract class MainHomeView extends ViewBase {
	static Trace log = Trace.getLogger(MainHomeView.class);
	
	@Menu(items = { "left", "right" })
	IMenu toolbar;
	
	@Bean(type=BindingType.Service)
	IMissCallService missCallSerivce;
	
	@Bean(type=BindingType.Service)
	IDXHZService dxhzService;
	
	@Bean(type=BindingType.Service)
	IPrivateSMService privateService;
	
	@Bean(type=BindingType.Service)
	ICallRecorderService callRecordService;
	
	/**
	 * 是否开通了来电提醒,没有开通来电提醒导航到来电提醒导航页，如果已经开通导航到来电提醒列表页
	 */
	@Bean(type=BindingType.Pojo,express="${missCallSerivce.isOpen()}")
	boolean isOpenMissCall;
	
	/**
	 * 是否开通了短信,没有开通来电提醒导航到短信回执导航页，如果已经开通导航到短信回执列表页
	 */
	@Bean(type=BindingType.Pojo,express="${dxhzService.isOpen()}")
	boolean isOpenDXHZ;
	
	
	/**
	 * 是否开通私密锁,也就是bindingmail没有
	 */
	@Bean(type=BindingType.Pojo,express="${privateService.getBindedEmail() != null}")
	boolean isSetPrivateLock;
	
	@Bean(type=BindingType.Pojo,express="${callRecordService.getAllAudioTrack().size() > 0}")
	boolean isHasRecord;
	
	
	@Command(navigations={@Navigation(on="missCall",showView="MissCallSessionListView")
						 ,@Navigation(on="missCallGuide",showView="MissCallGuideView")})
	String navToMissCallView(InputEvent event){
//		List<SMSSessionGroupBean> l = missCallSerivce.getShowList();
//		 && l.size() > 0
		if(isOpenMissCall){
			return "missCall";
		}else{
			return "missCallGuide";
		}
	}
	
	
	public void xx(){
		if(privateService.getBindedEmail() != null){
			if(callRecordService.getAllAudioTrack().size() > 0){
				
			}
		}
	}
}
 