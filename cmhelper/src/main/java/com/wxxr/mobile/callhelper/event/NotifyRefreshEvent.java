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
public class NotifyRefreshEvent extends GenericEventObject implements IBroadcastEvent {
   private String desc;
   public NotifyRefreshEvent() {     
      super();
   }
   
   
}
