/**
 * 
 */
package com.wxxr.mobile.callhelper.model;

import com.wxxr.mobile.android.ui.AndroidBindingType;
import com.wxxr.mobile.android.ui.annotation.AndroidBinding;
import com.wxxr.mobile.callhelper.app.bean.SMSInfoBean;
import com.wxxr.mobile.callhelper.bind.TimestampConverter;
import com.wxxr.mobile.callhelper.service.IMissCallService;
import com.wxxr.mobile.core.ui.annotation.Bean;
import com.wxxr.mobile.core.ui.annotation.Convertor;
import com.wxxr.mobile.core.ui.annotation.Field;
import com.wxxr.mobile.core.ui.annotation.Parameter;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.annotation.Bean.BindingType;
import com.wxxr.mobile.core.ui.api.IModelUpdater;
import com.wxxr.mobile.core.ui.common.ViewBase;

/**
 * @author fudapeng
 *
 */

@View(name = "SMSSessionRightItem")
@AndroidBinding(type=AndroidBindingType.VIEW,layoutId="R.layout.sms_detail_item_right")
public class SMSSessionRightItem extends ViewBase implements IModelUpdater{
	@Bean(type=BindingType.Pojo)
	SMSInfoBean bean;
	
	
	@Field(valueKey="text",binding="${bean.getContent()}")
	String content;
	
	@Field(valueKey="visible",binding="${false}")
	boolean selectedChockBox;
	
	@Convertor(params={
			@Parameter(name="format",value="yyyy-M-d-HH-mm")
	})
	TimestampConverter timeConverter;
	
	@Field(valueKey="text",binding="${bean.getCdate()}",converter="timeConverter")
	String time;
	
	@Bean(type=BindingType.Pojo,express="${missCallSerivce.showTime(bean)}")
	boolean showTime;
	
	@Field(valueKey="visible",binding="${!showTime}")
	boolean emptyLine;
	
	@Override
	public void updateModel(Object data) {
		registerBean("bean", data);
	}
	
	@Bean(type=BindingType.Service)
	IMissCallService missCallSerivce;
	
	void xx(){
		bean.getContent();
		bean.getCdate();
		missCallSerivce.showTime(bean);
	}
}
