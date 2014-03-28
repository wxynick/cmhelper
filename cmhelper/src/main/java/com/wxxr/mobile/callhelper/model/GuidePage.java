package com.wxxr.mobile.callhelper.model;

import java.util.Hashtable;
import java.util.List;

import com.wxxr.mobile.android.ui.AndroidBindingType;
import com.wxxr.mobile.android.ui.annotation.AndroidBinding;
import com.wxxr.mobile.callhelper.Contants;
import com.wxxr.mobile.core.ui.annotation.Bean;
import com.wxxr.mobile.core.ui.annotation.Bean.BindingType;
import com.wxxr.mobile.core.ui.annotation.Command;
import com.wxxr.mobile.core.ui.annotation.Field;
import com.wxxr.mobile.core.ui.annotation.Navigation;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.api.InputEvent;
import com.wxxr.mobile.core.ui.common.PageBase;
import com.wxxr.mobile.preference.api.IPreferenceManager;


@View(name = "GuidePage", withToolbar = false, description = "介绍", provideSelection = true)
@AndroidBinding(type = AndroidBindingType.FRAGMENT_ACTIVITY, layoutId = "R.layout.guide_page")
abstract class GuidePage extends PageBase {
	
	
	@Bean(type=BindingType.Service)
	IPreferenceManager preferenceService;
	
	@Field
	String viewPager;
	
	
	/**
	 * 滑动到最后一张图片再次滑动进入首页
	 * @param event
	 * @return
	 */
	@Command(navigations={@Navigation(on="*", showPage="home")})
	String entryHomePage(InputEvent event){
		if(!preferenceService.hasPreference(Contants.config)){
			preferenceService.newPreference(Contants.config, new Hashtable<String, String>());
		}
		preferenceService.updatePreference(Contants.config, "isFromLoading", "1");
		hide();
		return "";
	}
	
	
	/**
	 *  
	 */
	@Field(valueKey="options")
	List<String> pngs; 
}
