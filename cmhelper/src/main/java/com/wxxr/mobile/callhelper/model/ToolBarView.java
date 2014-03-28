/**
 * 
 */
package com.wxxr.mobile.callhelper.model;

import java.util.Map;

import com.wxxr.mobile.android.ui.AndroidBindingType;
import com.wxxr.mobile.android.ui.annotation.AndroidBinding;
import com.wxxr.mobile.callhelper.widget.CallhelperAppToolbar;
import com.wxxr.mobile.core.ui.annotation.Attribute;
import com.wxxr.mobile.core.ui.annotation.Bean;
import com.wxxr.mobile.core.ui.annotation.Command;
import com.wxxr.mobile.core.ui.annotation.Field;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.api.IMenu;
import com.wxxr.mobile.core.ui.api.IUICommand;
import com.wxxr.mobile.core.ui.api.IUIComponent;
import com.wxxr.mobile.core.ui.api.InputEvent;
import com.wxxr.mobile.core.ui.common.AttributeKeys;
import com.wxxr.mobile.core.ui.common.DataField;
import com.wxxr.mobile.core.ui.common.UIComponent;

@View(name="toolbarView",singleton=true)
@AndroidBinding(type = AndroidBindingType.VIEW, layoutId = "R.layout.callhelper_tool_bar_view")
public abstract class ToolBarView extends CallhelperAppToolbar {


	@Field(valueKey="imageURI")
	String leftIcon;
	
	
	DataField<String> leftIconField;
	
	@Field(valueKey="imageURI")
	String searchIcon;

	DataField<String> rightIconField;

	@Field(valueKey="imageURI")
	String rightIcon;

	DataField<String> searchIconField;
	
	@Field(valueKey="text")
	String message;
	
	DataField<String> messageField;
	
	@Field(valueKey="text")
	String title;
	DataField<String> titleField;
	
	@Field(valueKey="text",attributes={@Attribute(name="visible",value="${isShowAddTitle}")})
	String additionalTitle;
	

	@Field(valueKey="text")
	String status;
	
	@Field(valueKey="visible")
	boolean pushMessage;
	
	
	@Bean
	boolean isShowAddTitle;
	
	DataField<Boolean> pushMessageField;
	@Command
	String handleClick(InputEvent event){
		String name = event.getEventSource().getName();
		if("leftIcon".equals(name)){
			name = "left";
		} else if("rightIcon".equals(name)){
			name = "right";
		} else if("searchIcon".equals(name)){
			name = "search";
		}
		IUICommand cmd = getMenuItem(name);
		if(cmd != null){
			event.setTargetCommand(null);
			cmd.handleInputEvent(event);
		}
		return null;
	}
	
	@Override
	public void setTitle(String message, Map<String, String> parameters) {
		String title = null;
		String addTitle = null;
		if(message.indexOf(":") > -1){
			String[] meges = message.split(":");
			if(meges.length > 1){
				addTitle = meges[1];
			}
			title = meges[0];
		}else{
			title = message;
		}
		this.titleField.setValue(title);
		IUIComponent component = getChild("additionalTitle");
		if(component != null && component instanceof DataField && addTitle != null){
			((DataField)component).setValue(addTitle);
			isShowAddTitle = true;
		}else{
			((DataField)component).setValue("");
			isShowAddTitle = false;
		}
		registerBean("isShowAddTitle", isShowAddTitle);
	}

	@Override
	public IUIComponent getChild(String name) {
		if("leftIcon".equals(name)){
			IMenu menu = getToolbarMenu();
			if(menu != null){
				IUICommand cmd  = menu.getCommand("left");
				if(cmd != null){
//					this.leftIconField.setValue(cmd.getAttribute(AttributeKeys.icon));
//					this.leftIconField.setAttribute(AttributeKeys.visible,cmd.getAttribute(AttributeKeys.visible)==null?true:cmd.getAttribute(AttributeKeys.visible));
					return cmd;
				}else{
					this.leftIconField.setAttribute(AttributeKeys.visible,false);
					this.leftIconField.setAttribute(AttributeKeys.takeSpaceWhenInvisible,true);
				}
			}
		}else if("rightIcon".equals(name)){
			IMenu menu = getToolbarMenu();
			if(menu != null){
				IUICommand cmd  = menu.getCommand("right");
				if(cmd != null){
//					this.rightIconField.setValue(cmd.getAttribute(AttributeKeys.icon));
//					this.rightIconField.setAttribute(AttributeKeys.visible,cmd.getAttribute(AttributeKeys.visible)==null?true:cmd.getAttribute(AttributeKeys.visible));
					return cmd;
				}else{
					this.rightIconField.setAttribute(AttributeKeys.visible,false);
					this.rightIconField.setAttribute(AttributeKeys.takeSpaceWhenInvisible,true);
				}
			}
		}else if("searchIcon".equals(name)){
			IMenu menu = getToolbarMenu();
			if(menu != null){
				IUICommand cmd  = menu.getCommand("search");
				if(cmd != null){
//					this.searchIconField.setValue(cmd.getAttribute(AttributeKeys.icon));
//					this.searchIconField.setAttribute(AttributeKeys.visible,cmd.getAttribute(AttributeKeys.visible)==null?true:cmd.getAttribute(AttributeKeys.visible));
					return cmd;
				}else{
					this.searchIconField.setAttribute(AttributeKeys.visible,false);
					this.searchIconField.setAttribute(AttributeKeys.takeSpaceWhenInvisible,true);
				}
			}
		}
		return super.getChild(name);
	}
}
