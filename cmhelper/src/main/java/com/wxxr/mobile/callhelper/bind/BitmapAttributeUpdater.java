/**
 * 
 */
package com.wxxr.mobile.callhelper.bind;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

import com.wxxr.mobile.android.app.AppUtils;
import com.wxxr.mobile.callhelper.service.support.ItemPortraitRule;
import com.wxxr.mobile.core.ui.api.AttributeKey;
import com.wxxr.mobile.core.ui.api.IAttributeUpdater;
import com.wxxr.mobile.core.ui.api.IUIComponent;

/**
 * @author fudapeng
 *
 */
public class BitmapAttributeUpdater implements IAttributeUpdater<View> {

	@Override
	public boolean acceptable(Object arg0) {
		return arg0 instanceof ImageView;
	}

	@Override
	public <T> void updateControl(View control, AttributeKey<T> attrType,
			IUIComponent field, Object value) {
		View tv = (View)control;
		if(value instanceof Bitmap){
			tv.setBackgroundDrawable(new BitmapDrawable((Bitmap)value));
		}else if(value instanceof String){//给电话名字和号码给个头像
			String[] array = ((String) value).split(":");
			if(array.length == 2){
				ItemPortraitRule r = ItemPortraitRule.getInstance(AppUtils.getFramework().getAndroidApplication());
				Bitmap headIcon = r.getItemPortrait(array[0], array[1]);
				tv.setBackgroundDrawable(new BitmapDrawable(headIcon));
				
			}
			
		}
	}

	@Override
	public <T> T updateModel(View arg0, AttributeKey<T> arg1,
			IUIComponent arg2) {
		return null;
	}

}
