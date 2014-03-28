package com.wxxr.mobile.callhelper.model;

import java.util.HashMap;
import java.util.Map;

import com.wxxr.mobile.android.app.AppUtils;
import com.wxxr.mobile.android.ui.AndroidBindingType;
import com.wxxr.mobile.android.ui.annotation.AndroidBinding;
import com.wxxr.mobile.callhelper.app.bean.SMSSessionGroupBean;
import com.wxxr.mobile.callhelper.event.CloseContextMenuEvent;
import com.wxxr.mobile.callhelper.service.IMissCallService;
import com.wxxr.mobile.callhelper.service.IPhoneSystemService;
import com.wxxr.mobile.core.event.api.IEventRouter;
import com.wxxr.mobile.core.ui.annotation.Bean;
import com.wxxr.mobile.core.ui.annotation.Bean.BindingType;
import com.wxxr.mobile.core.ui.annotation.Command;
import com.wxxr.mobile.core.ui.annotation.Field;
import com.wxxr.mobile.core.ui.annotation.Navigation;
import com.wxxr.mobile.core.ui.annotation.OnUIDestroy;
import com.wxxr.mobile.core.ui.annotation.Parameter;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.api.CommandResult;
import com.wxxr.mobile.core.ui.api.IModelUpdater;
import com.wxxr.mobile.core.ui.api.InputEvent;
import com.wxxr.mobile.core.ui.common.ViewBase;

/**
 * 通话录音，漏话，回执，私密长按弹出框
 * @author fudapeng
 */
@View(name = "ContextMenu")
@AndroidBinding(type = AndroidBindingType.VIEW,layoutId = "R.layout.context_menu")
abstract class ContextMenu extends ViewBase implements IModelUpdater{
	
	@Field(valueKey="text",binding="选择操作方式")
	String title;
	
	@Bean(type=BindingType.Service)
	IPhoneSystemService phoneService;
	
	@Bean(type=BindingType.Service)
	IMissCallService missCallService;
	
	String phoneNumber;
	
	SMSSessionGroupBean sessionBean;
	
	@Command
	String callUp(InputEvent event){
		phoneService.callUp(sessionBean.getPhoneNumber());
		return null;
	}
	
	@Command(navigations={@Navigation(on="*",showView="MissCallSessionView")})
	String sentMessage(InputEvent event){
		return "";
	}
	
	@Command
	String addContactPerson(InputEvent event){
		phoneService.addContactPerson(sessionBean.getPhoneNumber());
		hide();
		return null;
	}
	
	@Command(navigations={@Navigation(on = "*",showDialog="ConfirmDialog",
			params={@Parameter(name="operation",value="deleteMissCallSession"),
			@Parameter(name = "icon", value = "resourceId:drawable/gd_dialog_icon"),
			@Parameter(name = "title", value = "提示"),
			@Parameter(name = "message", value = "删除联系人漏接电话记录？")})})
//	@Command(navigations={@Navigation(on = "*",message="删除联系人漏接电话记录？",
//			params = {
//			@Parameter(name = "title", value = "提示"),
//			@Parameter(name = "icon", value = "resourceId:drawable/gd_dialog_icon"),
//			@Parameter(name = "onOK", value = "leftok"),
//			@Parameter(name = "cancelable", value = "false")}
//	)})
	CommandResult remove(InputEvent event){
		CommandResult result = new CommandResult();
		Map<String,Object> map = new HashMap();
		map.put("currentSelected", sessionBean);
		map.put("currentView", currentView);
		
		result.setPayload(map);
		result.setResult("");
		hide();
		return result;
	}
	
//	@Command(uiItems = @UIItem(id = "leftok", label = "确定", icon = "resourceId:drawable/home", visibleWhen="true"))
//	String confirmOkClick(InputEvent event) {
//		IView v = (IView) event.getProperty(InputEvent.PROPERTY_SOURCE_VIEW);
//		if (v != null)
//			v.hide();
//		hide();
//		return null;
//	}
	private String currentView;
	@Override
	public void updateModel(Object value) {
		if(value instanceof Map){
			Map<String,Object> map = (Map<String,Object>)value;
			sessionBean = (SMSSessionGroupBean) map.get("currentSelected");
			currentView = (String) map.get("currentView");
		}
		
	}
	
	
	@OnUIDestroy
	void destroyUI(){
		CloseContextMenuEvent event = CloseContextMenuEvent.build(currentView);
		AppUtils.getService(IEventRouter.class).routeEvent(event);
	}
	
}
