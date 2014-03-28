/*
 * @(#)MessageReceivedEvent.java	 2013-12-20
 *
 * Copyright 2004-2013 WXXR Network Technology Co. Ltd. 
 * All rights reserved.
 * 
 * WXXR PROPRIETARY/CONFIDENTIAL.
 */

package com.wxxr.mobile.callhelper.event;

import com.wxxr.mobile.core.event.api.GenericEventObject;
import com.wxxr.mobile.core.event.api.IBroadcastEvent;

/**
 * @author FUDAPENG
 */
public class CloseContextMenuEvent extends GenericEventObject implements IBroadcastEvent {
   private String currentView;
   public String getCurrentView() {
	return currentView;
}

   private CloseContextMenuEvent(String currentView) {  
      super();
      this.currentView = currentView;
   }
   
   public static CloseContextMenuEvent build(String currentView){
	   return new CloseContextMenuEvent(currentView);
   }
   
   
}
