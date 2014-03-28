package com.wxxr.mobile.callhelper.bind;

import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.wxxr.mobile.core.ui.api.IUIComponent;
import com.wxxr.mobile.core.ui.api.IView;
import com.wxxr.mobile.core.ui.common.AbstractEventBinding;
import com.wxxr.mobile.core.ui.common.SimpleInputEvent;

public class SlideLastEventBinding extends AbstractEventBinding {

	private View control;
	
	private boolean isLastPage;
	
	private int dispatchOnlyOne = 0;;
	
	float startx = 0;
	public SlideLastEventBinding(View bindingControl, String cmdName, String field) {
		control = bindingControl;
		super.setCommandName(cmdName);
		super.setFieldName(field);
		if(control != null && control instanceof ViewPager){
			final ViewPager vp = (ViewPager)control;
			ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
				@Override
				public void onPageSelected(int arg0) {
					Integer count = (Integer) vp.getTag();
					if( count -1 == arg0){
						isLastPage = true;
					}else{
						isLastPage = false;
					}
				}
				
				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
				}
				
				@Override
				public void onPageScrollStateChanged(int arg0) {
				}
			};
			
			vp.setOnPageChangeListener(listener);
			vp.setOnTouchListener(new View.OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int action=event.getAction();
					
					if(action==MotionEvent.ACTION_DOWN){
						startx=event.getX();
					}
					
					if(action==MotionEvent.ACTION_MOVE){
						if(startx-event.getX() > 100 && isLastPage && dispatchOnlyOne == 0){
							IUIComponent field = getField();
							SimpleInputEvent e = new SimpleInputEvent("LashSlide",field);
							handleInputEvent(e);
							dispatchOnlyOne++;
						}
					}
					return false;
				}
			});
			
		}
	}
	
	@Override
	public void activate(IView model) {
		super.activate(model);
	}
	
	@Override
	public void deactivate(){
		control = null;
	}

}
