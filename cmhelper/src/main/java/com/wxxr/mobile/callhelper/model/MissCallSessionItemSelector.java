/**
 * 
 */
package com.wxxr.mobile.callhelper.model;


import com.wxxr.mobile.android.ui.ItemViewSelector;
import com.wxxr.mobile.callhelper.app.bean.SMSInfoBean;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.common.ViewBase;

/**
 * @author fudapeng
 *
 */
@View(name = "MissCallSessionItemSelector",description="13651129876")
public class MissCallSessionItemSelector extends ViewBase implements ItemViewSelector{
	
	
	@Override
	public String getItemViewId(Object value) {
		if (value instanceof SMSInfoBean) {
			SMSInfoBean order = (SMSInfoBean)value;
			if(order.getMstyle() == 0){	
				return "SMSSessionleftItem";
			}else if(order.getMstyle() == 1){
				return "SMSSessionRightItem";
			}
		}
		return "";
	}
	
	@Override
	public String[] getAllViewIds() {
		return new String[] {"SMSSessionleftItem","SMSSessionRightItem"};
	}
}
