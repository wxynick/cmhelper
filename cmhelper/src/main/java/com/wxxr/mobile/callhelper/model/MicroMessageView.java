/**
 * 
 */
package com.wxxr.mobile.callhelper.model;


import com.wxxr.mobile.android.ui.AndroidBindingType;
import com.wxxr.mobile.android.ui.annotation.AndroidBinding;
import com.wxxr.mobile.core.log.api.Trace;
import com.wxxr.mobile.core.ui.annotation.Command;
import com.wxxr.mobile.core.ui.annotation.Menu;
import com.wxxr.mobile.core.ui.annotation.Navigation;
import com.wxxr.mobile.core.ui.annotation.View;
import com.wxxr.mobile.core.ui.api.CommandResult;
import com.wxxr.mobile.core.ui.api.IMenu;
import com.wxxr.mobile.core.ui.api.InputEvent;
import com.wxxr.mobile.core.ui.common.ViewBase;

@View(name="MicroMessageView", description="微资讯", provideSelection = true)
@AndroidBinding(type=AndroidBindingType.FRAGMENT,layoutId="R.layout.micro_message_view")
public abstract class MicroMessageView extends ViewBase{
	static Trace log = Trace.getLogger(MicroMessageView.class);
	
 
	@Menu(items={"right"})
	private IMenu toolbar;
	
 
	@Command(description="Invoke when a toolbar item was clicked",navigations = { @Navigation(on = "*", showPage = "GeGuStockPage")}
	)
	String toolbarClickedLeft(InputEvent event) {
		return "";
	}
	
	@Command
	String handleRefresh(InputEvent event) {
		if("TopRefresh".equals(event.getEventType())) {
		}
		return null;
	}	
	
	@Command(navigations={@Navigation(on="web",showPage="webPage")})
	CommandResult goWebPageOnItemClick(InputEvent event){
		return null;
	}
	
}
 