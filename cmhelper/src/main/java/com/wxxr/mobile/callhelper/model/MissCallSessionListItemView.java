package com.wxxr.mobile.callhelper.model;



import com.wxxr.mobile.android.ui.AndroidBindingType;
import com.wxxr.mobile.android.ui.annotation.AndroidBinding;
import com.wxxr.mobile.callhelper.app.bean.ContactPersonBean;
import com.wxxr.mobile.callhelper.app.bean.SMSSessionGroupBean;
import com.wxxr.mobile.callhelper.bind.TextConverter;
import com.wxxr.mobile.callhelper.service.IPhoneSystemService;
import com.wxxr.mobile.core.log.api.Trace;
import com.wxxr.mobile.core.ui.annotation.Attribute;
import com.wxxr.mobile.core.ui.annotation.Bean;
import com.wxxr.mobile.core.ui.annotation.Bean.BindingType;
import com.wxxr.mobile.core.ui.annotation.Convertor;
import com.wxxr.mobile.core.ui.annotation.Field;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.api.IModelUpdater;
import com.wxxr.mobile.core.ui.common.ViewBase;
import com.wxxr.mobile.core.util.StringUtils;

@View(name = "MissCallSessionListItemView")
@AndroidBinding(type=AndroidBindingType.VIEW,layoutId="R.layout.common_session_list_item")
public abstract class MissCallSessionListItemView extends ViewBase implements IModelUpdater {

	static Trace log = Trace.getLogger(MissCallSessionListItemView.class);
	
	@Bean(type=BindingType.Service)
	IPhoneSystemService phoneService;
	
	@Bean(type=BindingType.Pojo,express="${perseon == null || (perseon != null && perseon.getFristName() == null)}")
	boolean showProtrait;
	
	@Field(valueKey="visible",binding="${!showProtrait}")
	boolean showTextProtrait;
	
	ContactPersonBean perseon;
	
	@Field(valueKey="text",binding="${perseon != null ? perseon.getFristName() : null}",attributes={@Attribute(name="visible",value="${!showProtrait}")})
	String showFristName;
	
	@Field(valueKey="text",binding="${groupBean.getPhoneNumber() == null ? groupBean.getContactName() : groupBean.getPhoneNumber()}")
	String showNameOrNumber;
	
	@Field(binding="${perseon.getPortrait()}",attributes={@Attribute(name="iconByte",value="${perseon.getPortrait()}"),@Attribute(name="visible",value="${showProtrait}")})
	Byte[] portrait;
	
	@Field(valueKey = "text",binding="${groupBean.getShowSessionContent()}")
	String brief;	
	
	@Convertor
	TextConverter textConverter;
	
	@Field(valueKey="text", binding="${groupBean.getSize()}",attributes={@Attribute(name="visible",value="${groupBean.getSize() > 0}"),@Attribute(name="takeSpaceWhenInvisible",value="true")},converter="textConverter")
	String count;
	
	@Field(valueKey="text",binding="${groupBean.getDate()}")
	String date;
	
	
	@Field(valueKey="backgroundImageURI",binding="${groupBean.isSelected() ? 'resourceId:color/gd_callrecord_long_press' : 'resourceId:color/white'}")
	String backgroundColor;
	
	
	
	
	
	@Bean(type=BindingType.Pojo)
	SMSSessionGroupBean groupBean;
	
	void showViewExt(){
		if(perseon == null && groupBean != null){
			perseon = phoneService.getContactPersonByPhoneNumber(groupBean.getPhoneNumber());
		}
		if(perseon != null){
			registerBean("perseon", perseon);
			return;
		}
	}
	
	void xx(){
		groupBean.getDate();groupBean.isSelected();
		groupBean.getSize();
//		groupBean.getPortrait();
		groupBean.getContactName();
		groupBean.getPhoneNumber();
		groupBean.getShowSessionContent();if(perseon != null && StringUtils.isNotBlank(perseon.getFristName()))
		StringUtils.isNotBlank(phoneService.getContactPersonByPhoneNumber(groupBean.getPhoneNumber()).getFristName());
		String a = groupBean.getPhoneNumber() == null ? groupBean.getContactName() : groupBean.getPhoneNumber();
		perseon.getPortrait();
	}
	
	@Override
	public void updateModel(Object data) {
		if (data instanceof SMSSessionGroupBean) {
			groupBean = (SMSSessionGroupBean) data;
			registerBean("groupBean", data);
			showViewExt();
		}
	}
}
