/**
 * 
 */
package com.wxxr.mobile.callhelper.bind;

import android.view.View;
import android.widget.TextView;

import com.wxxr.mobile.core.ui.api.AttributeKey;
import com.wxxr.mobile.core.ui.api.IAttributeUpdater;
import com.wxxr.mobile.core.ui.api.IUIComponent;

/**
 * @author fudapeng
 *
 */
public class SelectedAttributeUpdater implements IAttributeUpdater<View> {

	@Override
	public boolean acceptable(Object arg0) {
		return arg0 instanceof View;
	}

	@Override
	public <T> void updateControl(View control, AttributeKey<T> attrType,
			IUIComponent field, Object value) {
		View tv = (View)control;
		if(value instanceof Boolean){
			tv.setSelected((Boolean) value);
		}
	}

	@Override
	public <T> T updateModel(View arg0, AttributeKey<T> arg1,
			IUIComponent arg2) {
		return null;
	}

}
