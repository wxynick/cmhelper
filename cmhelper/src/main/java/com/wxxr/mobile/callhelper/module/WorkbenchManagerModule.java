/**
 * 
 */
package com.wxxr.mobile.callhelper.module;


import com.wxxr.mobile.android.ui.module.AbstractWorkbenchManagerModule;
import com.wxxr.mobile.callhelper.ICallHeplerAppContext;
import com.wxxr.mobile.callhelper.bind.BitmapAttributeUpdater;
import com.wxxr.mobile.callhelper.bind.IconAttributeUpdater;
import com.wxxr.mobile.callhelper.bind.ItemLongClickEventBinder;
import com.wxxr.mobile.callhelper.bind.SelectedAttributeUpdater;
import com.wxxr.mobile.callhelper.bind.SlideLastEventBinder;
import com.wxxr.mobile.callhelper.bind.TextAttributeExtUpdater;
import com.wxxr.mobile.callhelper.bind.TypeFaceAttributeUpdater;
import com.wxxr.mobile.callhelper.decorator.ItemClickDecorator;
import com.wxxr.mobile.core.log.api.Trace;
import com.wxxr.mobile.core.ui.api.AttributeKey;
import com.wxxr.mobile.core.ui.api.IBindingDecoratorRegistry;
import com.wxxr.mobile.core.ui.api.IEventBinderManager;
import com.wxxr.mobile.core.ui.api.IFieldAttributeManager;
import com.wxxr.mobile.core.ui.api.IFieldBinderManager;
import com.wxxr.mobile.core.ui.api.IWorkbenchRTContext;
import com.wxxr.mobile.core.ui.api.InputEvent;

/**
 * @author fudapeng
 *
 */
public class WorkbenchManagerModule extends AbstractWorkbenchManagerModule<ICallHeplerAppContext> {

	@Override
	protected void initAttributeUpdaters(IFieldAttributeManager fm) {
		fm.registerAttribute(new AttributeKey(String.class, "typeface"));
		fm.registerAttribute(new AttributeKey(Integer.class, "textsize"));
		fm.registerAttribute(new AttributeKey(String.class, "icon"));
		fm.registerAttribute(new AttributeKey(Byte[].class, "iconByte"));
		
		fm.registerAttributeUpdater("bitmap",new BitmapAttributeUpdater());
		fm.registerAttributeUpdater("textsize",new TextAttributeExtUpdater());
		fm.registerAttributeUpdater("typeface", new TypeFaceAttributeUpdater());
		fm.registerAttributeUpdater("selected",new SelectedAttributeUpdater());
		fm.registerAttributeUpdater("iconByte",new IconAttributeUpdater());
	}

	@Override
	protected void initBindingDecorators(IBindingDecoratorRegistry br) {
		br.registerDecorator("ItemClickDecorator", ItemClickDecorator.class);
	}

	@Override
	protected void initEventBinders(IEventBinderManager em) {
		em.registerFieldBinder("LashSlide", new SlideLastEventBinder());
		em.registerFieldBinder(InputEvent.EVENT_TYPE_ITEM_LONGCLICK, new ItemLongClickEventBinder());
		
	}

	@Override
	protected void initFieldBinders(IFieldBinderManager arg0) {
		
	}

	@Override
	protected void initPresentationModels(IWorkbenchRTContext arg0) {
		try {
			Class.forName("com.wxxr.mobile.callhelper.view.DeclarativePModelProvider").getMethod("updatePModel", new Class[]{IWorkbenchRTContext.class}).invoke(null,arg0);
		}catch(Throwable t){
			Trace.getLogger(WorkbenchManagerModule.class).fatal("Failed to load in presentation model !!!",t);
		}
	}
	

}
