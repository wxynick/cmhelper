package com.wxxr.mobile.callhelper.model;

import java.util.List;
import java.util.Map;

import com.wxxr.mobile.android.app.AppUtils;
import com.wxxr.mobile.android.ui.AndroidBindingType;
import com.wxxr.mobile.android.ui.annotation.AndroidBinding;
import com.wxxr.mobile.callhelper.app.bean.RegionBean;
import com.wxxr.mobile.callhelper.app.bean.SMSInfoBean;
import com.wxxr.mobile.callhelper.app.bean.SMSSessionGroupBean;
import com.wxxr.mobile.callhelper.service.IAffiliationAreaService;
import com.wxxr.mobile.callhelper.service.IPhoneSystemService;
import com.wxxr.mobile.core.log.api.Trace;
import com.wxxr.mobile.core.ui.annotation.Bean;
import com.wxxr.mobile.core.ui.annotation.Bean.BindingType;
import com.wxxr.mobile.core.ui.annotation.Command;
import com.wxxr.mobile.core.ui.annotation.Field;
import com.wxxr.mobile.core.ui.annotation.Menu;
import com.wxxr.mobile.core.ui.annotation.Navigation;
import com.wxxr.mobile.core.ui.annotation.OnCreate;
import com.wxxr.mobile.core.ui.annotation.OnShow;
import com.wxxr.mobile.core.ui.annotation.UIItem;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.api.IMenu;
import com.wxxr.mobile.core.ui.api.IModelUpdater;
import com.wxxr.mobile.core.ui.api.IViewDescriptor;
import com.wxxr.mobile.core.ui.api.IWorkbenchManager;
import com.wxxr.mobile.core.ui.api.InputEvent;
import com.wxxr.mobile.core.ui.common.AbstractViewDescriptor;
import com.wxxr.mobile.core.ui.common.ViewBase;
import com.wxxr.mobile.core.util.StringUtils;

@View(name = "MissCallSessionView",description="13651129876")
@AndroidBinding(type=AndroidBindingType.FRAGMENT,layoutId="R.layout.sms_session")
public class MissCallSessionView extends ViewBase implements IModelUpdater {
	
	Trace log = Trace.getLogger(MissCallSessionView.class);
	
	@Menu(items = { "left", "right" })
	IMenu toolbar;
	
	@Field(binding="${currentSelectedBean.getSms()}")
	List<SMSInfoBean> sms;
	
	@Bean(type=BindingType.Pojo)
	SMSSessionGroupBean currentSelectedBean;
	
	
	@Bean(type=BindingType.Service)
	IAffiliationAreaService areaService;
	
	
	@OnCreate
	protected void onModelCreate(){
//		IViewDescriptor descriptor = AppUtils.getService(IWorkbenchManager.class).getViewDescriptor("MissCallSessionView");
//		if(descriptor instanceof AbstractViewDescriptor){
//			String name = currentSelectedBean.getContactName();
//			String number = currentSelectedBean.getPhoneNumber();
//			((AbstractViewDescriptor)descriptor).setViewDescription(StringUtils.isBlank(name) ? number : name);
//		}
//		String desc = descriptor.getViewDescription();
		 log.debug("===================MissCallSessionView onshow=======================");
	}
	
	@OnShow
	void onModelShow(){
		 log.debug("===================MissCallSessionView onshow=======================");
	}
	
	
	@Bean(type=BindingType.Service)
	IPhoneSystemService service;
	
	
	void xx(){
		currentSelectedBean.getSms();
	}
	
	
	
	
	@Command(navigations={@Navigation(on="",showView="MissCallSessionListView")},uiItems = { @UIItem(id = "left", label = "左菜单", icon = "resourceId:drawable/gd_titlebar_left_selector",visibleWhen="${true}")})
	String toolbarLifeHandler(InputEvent event){
		return "";
	}
	
	@Command(uiItems = { @UIItem(id = "right", label = "右菜单", icon = "resourceId:drawable/gd_call_detail_btn",visibleWhen="${true}")})
	String toolbarRightHandler(InputEvent event){
		if(service != null && currentSelectedBean != null){
			service.callUp(currentSelectedBean.getPhoneNumber());
		}
		return "";
	}

	@Override
	public void updateModel(Object data) {
		if (data instanceof Map) {
			Map m = (Map)data;
			registerBean("currentSelectedBean", m.get("currentSelected"));
	        IViewDescriptor descriptor = AppUtils.getService(IWorkbenchManager.class).getViewDescriptor("MissCallSessionView");
	        if (descriptor instanceof AbstractViewDescriptor) {
            	currentSelectedBean = (SMSSessionGroupBean) m.get("currentSelected");
                String name = currentSelectedBean.getContactName();
                String number = currentSelectedBean.getPhoneNumber();
                currentSelectedBean.getContactName();
                String regionName = null;
                if(areaService != null){
                	RegionBean  r=	areaService.getRegionByDialogNumber(number);
                	if(r != null){
                		regionName = r.getRegionName();
//                		m.put("addTitle", StringUtils.isBlank(regionName) ? "未知" : regionName);
                	}
                }
                ((AbstractViewDescriptor)descriptor).setViewDescription((StringUtils.isBlank(name) ? number : name) + ":" + (StringUtils.isBlank(regionName) ? "未知" : regionName));
                
            }
		}
	}
	
}
