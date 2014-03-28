/**
 * 
 */
package com.wxxr.mobile.callhelper.bind;

import android.view.View;
import android.widget.TextView;

import com.wxxr.mobile.android.ui.RUtils;
import com.wxxr.mobile.core.ui.api.AttributeKey;
import com.wxxr.mobile.core.ui.api.IAttributeUpdater;
import com.wxxr.mobile.core.ui.api.IUIComponent;

/**
 * @author fudapeng
 *
 */
public class TextAttributeExtUpdater implements IAttributeUpdater<View> {

	@Override
	public boolean acceptable(Object arg0) {
		return arg0 instanceof TextView;
	}

	@Override
	public <T> void updateControl(View control, AttributeKey<T> attrType,
			IUIComponent field, Object value) {
		TextView tv = (TextView)control;
		if(tv != null){
			if(RUtils.isResourceIdURI(value.toString())){
				tv.setText(RUtils.getInstance().getResourceIdByURI(value.toString()));
			}else{
				tv.setText(value.toString());
			}
		}
	}

	@Override
	public <T> T updateModel(View arg0, AttributeKey<T> arg1,
			IUIComponent arg2) {
		return null;
	}

}
