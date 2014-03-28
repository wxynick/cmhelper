package com.wxxr.mobile.callhelper.utils;

import java.util.Hashtable;

import android.content.Context;

import com.wxxr.mobile.android.app.AppUtils;
import com.wxxr.mobile.core.log.api.Trace;
import com.wxxr.mobile.preference.api.IPreferenceManager;

public class ManagerSP {
	private static final String PID = "config";
	private static final Trace log = Trace.register(ManagerSP.class);
	
	private static ManagerSP instance =new ManagerSP();

	private IPreferenceManager mgr;
//	private ClientInfo clientInfo;
	private long lastCheckTime, checkInterval = 2*60*60*1000L;
//	private Runnable checkTask = new Runnable() {
//		
//		@Override
//		public void run() {
//			try {
//				while(AppUtils.getService(IDataExchangeCoordinator.class)==null || AppUtils.getService(IClientUpdateService.class)==null){
//					Thread.sleep(1000L);
//				}
//				if(((System.currentTimeMillis() - lastCheckTime) > checkInterval)&&(AppUtils.getService(IDataExchangeCoordinator.class).checkAvailableNetwork() > 0)){
//					clientInfo = AppUtils.getService(IClientUpdateService.class).getClientInfo();
//					lastCheckTime = System.currentTimeMillis();
//				}
//			}catch(Throwable t){
//				log.error("Failed to check updated application", t);
//			}
//			AppUtils.getFramework().invokeLater(checkTask, 10*60, TimeUnit.SECONDS);
//		}
//	};
	
	@Deprecated
	public static ManagerSP getInstance(Context ctx){
		return instance;
	}
	
	public static ManagerSP getInstance(){
		return instance;
	}

	
	private ManagerSP(){
//		AppUtils.getFramework().getExecutor().execute(checkTask);
		if(!getPrefManager().hasPreference(PID)){
			getPrefManager().newPreference(PID, new Hashtable<String, String>());
		}
	}
	
	private IPreferenceManager getPrefManager() {
		if(mgr == null){
			mgr = AppUtils.getService(IPreferenceManager.class);
		}
		return mgr;
	}
	
	
	public boolean update(String key, int value){
		getPrefManager().updatePreference(PID, key, String.valueOf(value));
		return true;
	}
	
	public int get(String key, int value){
		String val = getPrefManager().getPreference(PID, key);
		if(val == null){
			return value;
		}
		return Integer.parseInt(val);
	}
	
	public boolean update(String key, String value){
		getPrefManager().updatePreference(PID, key, value);
		return true;
	}
	
	public String get(String key, String value){
		String val = getPrefManager().getPreference(PID, key);
		if(val == null){
			return value;
		}
		return val;
	}
	
	public boolean hasUpdatedApp() {
		return checkVersion();
		
	}
	
	
	private boolean checkVersion() {
		boolean val = false;
//		int unInstall = get(Constant.UNINSTALL, 0);
//		if(clientInfo != null){
//			log.info("network_Version:" + clientInfo.toString());
//			String network_version = clientInfo.getVersion();
//			double d_location_version = Double.parseDouble(Version.getVersionNumber());
//			double d_network_version = Double.parseDouble(network_version);
//			
//			if(d_location_version < d_network_version && unInstall == 1){
//				update("guide", 1);
//				val = true;
//			}
//			
//			if(d_location_version < d_network_version){
//				update(Constant.VERSION_STATUS, 1);
//				update("network_version", network_version);
//				update("description", clientInfo.getDescription());
//				update("url", clientInfo.getUrl());
//				val=true;
//			}
//			
//		}else{
//			//从push 来的，可能 clientInfo 还没有值，但是  network_version ，已经有新值了，
//			String network_version = get("network_version", "0");
//			double d_location_version = Double.parseDouble(Version.getVersionNumber());
//			double d_network_version = Double.parseDouble(network_version);
//			
//			if(d_location_version < d_network_version && unInstall == 1){
//				update("guide", 1);
//				val = true;
//			}
//			
//			if(d_location_version < d_network_version){
//				update(Constant.VERSION_STATUS, 1);
//				update("network_version", network_version);			
//				val=true;
//			}
//		}
		return val;
		
	}

}
