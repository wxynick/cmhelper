/**
 * 
 */
package com.wxxr.mobile.callhelper.bind;


import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.wxxr.mobile.core.ui.api.IView;
import com.wxxr.mobile.core.ui.api.InputEvent;
import com.wxxr.mobile.core.ui.common.AbstractEventBinding;
import com.wxxr.mobile.core.ui.common.SimpleInputEvent;

/**
 * @author neillin
 *
 */
public class ItemLongClickEventBinding extends AbstractEventBinding implements OnItemLongClickListener {

	private AdapterView<?> control;
	
	public ItemLongClickEventBinding(View view,String cmdName,String field){
		this.control = (AdapterView<?>)view;
		super.setUIControl(this.control);
		super.setCommandName(cmdName);
		super.setFieldName(field);
	}

	@Override
	public void activate(IView model) {
		super.activate(model);
		this.control.setOnItemLongClickListener(this);
	}

	@Override
	public void deactivate() {
		this.control.setOnItemClickListener(null);
		super.deactivate();
	}


	@Override					  //AdapterView<?> parent, View view, int position, long id
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
			long id) {
		SimpleInputEvent event = new SimpleInputEvent(InputEvent.EVENT_TYPE_ITEM_LONGCLICK,getModel());
		Object list = getUIControl();
		if (list instanceof ListView) {
			ListView l = ((ListView) list);
			if(position < l.getHeaderViewsCount()) {
				return false;
			} else if(position >= l.getCount() + l.getHeaderViewsCount()) {
				return false;
			} else {
				position -= l.getHeaderViewsCount();
			}
		}
		event.addProperty("position", position);
		event.addProperty("viewItem", view.getTag());
		handleInputEvent(event);
		return true;
	}

}
