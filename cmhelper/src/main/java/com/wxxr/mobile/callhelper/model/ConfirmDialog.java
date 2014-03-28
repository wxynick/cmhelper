/**
 * 
 */
package com.wxxr.mobile.callhelper.model;

import java.util.Map;

import com.wxxr.mobile.android.app.AppUtils;
import com.wxxr.mobile.android.ui.AndroidBindingType;
import com.wxxr.mobile.android.ui.annotation.AndroidBinding;
import com.wxxr.mobile.callhelper.app.bean.SMSSessionGroupBean;
import com.wxxr.mobile.callhelper.event.CloseConfirmDialogEvent;
import com.wxxr.mobile.callhelper.event.CloseContextMenuEvent;
import com.wxxr.mobile.callhelper.service.IMissCallService;
import com.wxxr.mobile.core.event.api.IEventRouter;
import com.wxxr.mobile.core.ui.annotation.Bean;
import com.wxxr.mobile.core.ui.annotation.Bean.BindingType;
import com.wxxr.mobile.core.ui.annotation.Command;
import com.wxxr.mobile.core.ui.annotation.Field;
import com.wxxr.mobile.core.ui.annotation.OnUIDestroy;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.api.IModelUpdater;
import com.wxxr.mobile.core.ui.api.InputEvent;
import com.wxxr.mobile.core.ui.common.DataField;
import com.wxxr.mobile.core.ui.common.PageBase;
import com.wxxr.mobile.core.ui.common.ViewBase;

/**
 * @author fudapeng
 *
 */
@View(name = "ConfirmDialog")
@AndroidBinding(type = AndroidBindingType.VIEW,layoutId = "R.layout.confirm_dialog")
abstract class ConfirmDialog extends ViewBase implements IModelUpdater{
	
	@Bean(type=BindingType.Service)
	IMissCallService missCallSerivce;
	
//	@Bean(type=BindingType.Pojo,express="${missCallSerivce.getOpenCommand(null)}")
//	String command;
	
	@Field(valueKey="text")
	String message;
	
	
	@Field(valueKey="text")
	String title;
	
	@Field(valueKey="backgroundImageURI")
	String icon;
	
	String currentOperation;
	
	Object selected;
	
	@Command
	String right_button(InputEvent event){
		if("deleteMissCallSession".equalsIgnoreCase(currentOperation)){
			missCallSerivce.deleteMessageGroup((SMSSessionGroupBean) selected);
			CloseConfirmDialogEvent e = CloseConfirmDialogEvent.build(currentView,"deleteMissCallSession");
			AppUtils.getService(IEventRouter.class).routeEvent(e);
		}else{
			missCallSerivce.openBusiness(message);
		}
		hide();
		return "";
	}
	
	
	@Command
	String left_button(InputEvent event){
		hide();
		return "";
	}
	private String currentView;
	@Override
	public void updateModel(Object value){
		if(value instanceof Map){
			Map map = (Map)value;
			String operation = (String) map.get("operation");
			currentOperation = operation;
			selected = map.get("currentSelected");
			currentView = (String) map.get("currentView");
			
			DataField df = null;
			if("openImmediately".equalsIgnoreCase(operation)){
				String command = missCallSerivce.getOpenCommand(null);
				df = (DataField) getChild("icon");
				df.setValue("resourceId:drawable/gd_dialog_open_now_icon");
				
				df = (DataField) getChild("message");
			    String message = "将协助您发送免费短信" + command + "到10086。需要您查收并回复10086发送到您手机的确认短信，才能成功办理。";
				df.setValue(message);
				
				df = (DataField) getChild("title");
				df.setValue("办理通讯助手");
				
			}else{
				
				df = (DataField) getChild("icon");
				df.setValue(map.get("icon"));
				
				df = (DataField) getChild("message");
				df.setValue(map.get("message"));
				
				df = (DataField) getChild("title");
				df.setValue(map.get(title));
				
				
			}
		}
	}
	
	
	void registryBeans(){
		registerBean("icon", icon);
		registerBean("message", message);
	}
	
	
	
	public Object getAdaptor(Class clazz) {
		if(clazz == IModelUpdater.class){
			return clazz.cast(this);
		}
		return super.getAdaptor(clazz);
	}
}
