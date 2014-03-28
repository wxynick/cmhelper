package com.wxxr.mobile.callhelper.decorator;



import com.wxxr.mobile.android.ui.binding.ListViewPool;
import com.wxxr.mobile.android.ui.binding.ListViewPool.BindingBag;
import com.wxxr.mobile.core.log.api.Trace;
import com.wxxr.mobile.core.ui.api.IView;
import com.wxxr.mobile.core.ui.api.InputEvent;
import com.wxxr.mobile.core.ui.api.InputEventDecorator;
import com.wxxr.mobile.core.ui.api.InputEventHandlingContext;
import com.wxxr.mobile.core.ui.common.DataField;

public class ItemClickDecorator implements InputEventDecorator {

	private final InputEventDecorator next;
	Trace log = Trace.getLogger(ItemClickDecorator.class);
	
	public ItemClickDecorator(InputEventDecorator decor) {
		this.next = decor;
	}

	@Override
	public void handleEvent(InputEventHandlingContext context, InputEvent event) {
		final IView v = context.getViewModel();
//		String[] ids = v.getChild("smsgroup");
//		DataField df = (DataField) v.getChild("smsgroup");
		ListViewPool.BindingBag bag = (ListViewPool.BindingBag) event.getProperty("viewItem");
		IView itemView = bag.view;
		DataField df = (DataField) itemView.getChild("backgroundColor");
		df.setValue("resourceId:color/gd_callrecord_long_press");
		this.next.handleEvent(context, event);
	}

}
