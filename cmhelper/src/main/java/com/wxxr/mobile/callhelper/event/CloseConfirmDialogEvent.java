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
public class CloseConfirmDialogEvent extends GenericEventObject implements IBroadcastEvent {
   private String currentView;
   private String operation;
   public String getOperation() {
	return operation;
}

public String getCurrentView() {
	return currentView;
}

   private CloseConfirmDialogEvent(String currentView,String operation) {  
      super();
      this.currentView = currentView;
      this.operation = operation;
   }
   
   public static CloseConfirmDialogEvent build(String currentView,String operation){
	   return new CloseConfirmDialogEvent(currentView,operation);
   }
   
   
}
