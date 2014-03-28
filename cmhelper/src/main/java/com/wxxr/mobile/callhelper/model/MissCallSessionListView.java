package com.wxxr.mobile.callhelper.model;

import java.util.HashMap;
import java.util.List;

import com.wxxr.mobile.android.app.AppUtils;
import com.wxxr.mobile.android.ui.AndroidBindingType;
import com.wxxr.mobile.android.ui.annotation.AndroidBinding;
import com.wxxr.mobile.callhelper.app.bean.SMSSessionGroupBean;
import com.wxxr.mobile.callhelper.compatibility.constant.Constant;
import com.wxxr.mobile.callhelper.event.CloseConfirmDialogEvent;
import com.wxxr.mobile.callhelper.event.CloseContextMenuEvent;
import com.wxxr.mobile.callhelper.event.NotifyRefreshEvent;
import com.wxxr.mobile.callhelper.service.IMissCallService;
import com.wxxr.mobile.core.event.api.IBroadcastEvent;
import com.wxxr.mobile.core.event.api.IEventListener;
import com.wxxr.mobile.core.event.api.IEventRouter;
import com.wxxr.mobile.core.ui.annotation.Bean;
import com.wxxr.mobile.core.ui.annotation.Bean.BindingType;
import com.wxxr.mobile.core.ui.annotation.Command;
import com.wxxr.mobile.core.ui.annotation.Field;
import com.wxxr.mobile.core.ui.annotation.Menu;
import com.wxxr.mobile.core.ui.annotation.Navigation;
import com.wxxr.mobile.core.ui.annotation.OnCreate;
import com.wxxr.mobile.core.ui.annotation.OnDestroy;
import com.wxxr.mobile.core.ui.annotation.Parameter;
import com.wxxr.mobile.core.ui.annotation.UIItem;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.api.CommandResult;
import com.wxxr.mobile.core.ui.api.IMenu;
import com.wxxr.mobile.core.ui.api.IUIComponent;
import com.wxxr.mobile.core.ui.api.InputEvent;
import com.wxxr.mobile.core.ui.common.DataField;
import com.wxxr.mobile.core.ui.common.SimpleInputEvent;
import com.wxxr.mobile.core.ui.common.UICommand;
import com.wxxr.mobile.core.ui.common.ViewBase;

@View(name = "MissCallSessionListView", description="来电提醒")
@AndroidBinding(type = AndroidBindingType.FRAGMENT, layoutId = "R.layout.common_session_list")
abstract class MissCallSessionListView extends ViewBase implements IEventListener{
	
	@Menu(items = { "left", "right" })
	IMenu toolbar;
	
	@Bean(type=BindingType.Service)
	IMissCallService missCallSerivce;
	
	@Bean(type=BindingType.Pojo,express="${missCallSerivce.getShowList()}")
	List<SMSSessionGroupBean> lists;
	
	
	@Field(valueKey="options",binding="${lists}")
	List<SMSSessionGroupBean> smsgroup;
	
	@Command(navigations = {
			@Navigation(on = "session", showView = "MissCallSessionView")})
	CommandResult itemClicked(InputEvent event){
		CommandResult result = new CommandResult();
		if (InputEvent.EVENT_TYPE_ITEM_CLICK.equals(event.getEventType())) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (event.getProperty("position") instanceof Integer && lists != null) {
				int position = (Integer) event.getProperty("position");
				SMSSessionGroupBean currentSelected = lists.get(position);
				map.put("currentSelected", currentSelected);
				result.setPayload(map);
				result.setResult("session");
			}
		}
		return result;
	}
	
	
	
	
	
	@Command(navigations = {
			@Navigation(on = "delDialog", showDialog = "ContextMenu",
					params={@Parameter(name="currentView",value="MissCallSessionListView")})})
	CommandResult itemLongClick(InputEvent event){
		if(event instanceof SimpleInputEvent){
			CommandResult result = new CommandResult();
			SimpleInputEvent e = (SimpleInputEvent)event;
			IUIComponent ui = e.getEventSource();
			if (event.getProperty("position") instanceof Integer && lists != null) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				int position = (Integer) event.getProperty("position");
				SMSSessionGroupBean currentSelected = lists.get(position);
				map.put(Constant.DIALOG_KEY,Constant.LH_DETAIL_LONG_DELETE_RECORD);
				map.put("currentSelected", currentSelected);
				currentSelected.setSelected(true);
				result.setPayload(map);
				result.setResult("delDialog");
				return result;
			}
		}
		return null;
	}
	
	
	@Command(navigations={@Navigation(on="",showView="MainHomeView")},uiItems = { @UIItem(id = "left", label = "左菜单", icon = "resourceId:drawable/gd_titlebar_left_selector",visibleWhen="${true}")})
	String toolbarLifeHandler(InputEvent event){
		return "";
	}
	
	@Command(navigations={@Navigation(on="",showPage="MissCallSetPage")},uiItems = { @UIItem(id = "right", label = "右菜单", icon = "resourceId:drawable/gd_titlebar_right_selector",visibleWhen="${true}")})
	String toolbarRightHandler(InputEvent event){
		return "";
	}
	
	@OnCreate
	void create(){
		AppUtils.getService(IEventRouter.class).registerEventListener(NotifyRefreshEvent.class, this);
		
		AppUtils.getService(IEventRouter.class).registerEventListener(CloseContextMenuEvent.class,this);
	}
	
	@OnDestroy
	void uncreate(){
		AppUtils.getService(IEventRouter.class).unregisterEventListener(NotifyRefreshEvent.class, this);
		AppUtils.getService(IEventRouter.class).unregisterEventListener(CloseContextMenuEvent.class,this);
	}
	
	
	@Command(navigations={@Navigation(on="missCall",showView="MissCallSessionListView")
	 ,@Navigation(on="missCallGuide",showView="MissCallGuideView",params={@Parameter(name = "add2BackStack", value = "false")})})
	String navToMissCallView(InputEvent event){
		return "missCallGuide";
	}


	@Override
	public void onEvent(IBroadcastEvent e) {
		if(e instanceof CloseConfirmDialogEvent){
			CloseConfirmDialogEvent event = (CloseConfirmDialogEvent)e;
			if("MissCallSessionListView".equalsIgnoreCase(event.getCurrentView()) && event.getOperation().equalsIgnoreCase("deleteMissCallSession")){
				updateListView();
				if(lists == null || lists.size() == 0){
					SimpleInputEvent eventInput = new SimpleInputEvent("", null);
					eventInput.setTargetCommand("navToMissCallView");
					handleInputEvent(eventInput);
				}
			}
		}
	}
	
	void updateListView(){
		lists = missCallSerivce.getShowList();
		registerBean("lists", lists);
	}
	
	
	
}
