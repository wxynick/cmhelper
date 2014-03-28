package com.wxxr.mobile.callhelper.shell;

import com.wxxr.mobile.core.log.api.Trace;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;


public class InterceptSmsShell extends Service
{
	private static final Trace log = Trace.register(InterceptSmsShell.class);
	InterceptSMSReceiver mReceiver;

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

	@Override
	public void onCreate()
	{
		log.info("onCreate", "onCreateLanJieSmsService");
		
		if(mReceiver != null)
		{
			unregisterReceiver(mReceiver);
			mReceiver = null;
		}
		IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		localIntentFilter.setPriority(2147483647);
		mReceiver = new InterceptSMSReceiver();
		registerReceiver(mReceiver, localIntentFilter);
		
		
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId)
	{
		
		log.info("onStart", "onStartLanJieSmsService");
		
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy()
	{
		unregisterReceiver(mReceiver);
		Intent it = new Intent(InterceptSmsShell.this, InterceptSmsShell.class);
		this.startService(it);
	}

}
