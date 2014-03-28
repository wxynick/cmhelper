package com.wxxr.mobile.callhelper.ui;


import android.widget.ProgressBar;
import android.widget.TextView;

import com.wxxr.mobile.android.app.AppUtils;
import com.wxxr.mobile.android.ui.MainActivity;
import com.wxxr.mobile.callhelper.Contants;
import com.wxxr.mobile.callhelper.R;
import com.wxxr.mobile.core.ui.api.IView;
import com.wxxr.mobile.core.ui.api.IViewNavigationCallback;
import com.wxxr.mobile.core.ui.api.IWorkbenchManager;
import com.wxxr.mobile.core.util.StringUtils;
import com.wxxr.mobile.preference.api.IPreferenceManager;
public class SplashPage extends MainActivity {

	private ProgressBar progressBar;
	private TextView textView;
	private boolean inDebugMode = false;
	
	@Override
	protected void setupContentView() {
		setContentView(R.layout.logo);
		this.progressBar = (ProgressBar)findViewById(R.id.loading_progressBar);
		setMinStartupTime(2);
		this.inDebugMode = AppUtils.getFramework().isInDebugMode();
	}
	
	@Override
	protected void showProgressBar(final int totalwork) {
		AppUtils.runOnUIThread(new Runnable() {			
			@Override
			public void run() {
				if(progressBar != null){
					progressBar.setMax(totalwork);
				}
			}
		});
	}

	@Override
	protected void updateProgress(final int workDone, final String message) {
		AppUtils.runOnUIThread(new Runnable() {			
			@Override
			public void run() {
				if(progressBar != null){
					progressBar.setProgress(workDone);
				}
				if(inDebugMode && (message != null)&&(textView != null)){
					textView.setText(message);
				}
			}
		});

	}

	@Override
	protected void startupFailed(Throwable t, final String message) {
		AppUtils.runOnUIThread(new Runnable() {	
			@Override
			public void run() {
				if(textView != null){
					textView.setText("系统启动失败 :["+message+"]");
				}
			}
		});
	}

	@Override
	protected void updateProgressDone() {
		AppUtils.runOnUIThread(new Runnable() {	
			@Override
			public void run() {
				if(textView != null){
					textView.setText("系统启动完成 ！");
				}
			}
		});
	}
	
	
	protected void showHomePage(){
		String value = AppUtils.getService(IPreferenceManager.class).getPreference(Contants.config,"isFromLoading");
		if(StringUtils.isNotBlank(value)){
			AppUtils.getService(IWorkbenchManager.class).getWorkbench().showHomePage();
		}else{
			AppUtils.getService(IWorkbenchManager.class).getWorkbench().showPage("GuidePage",null,new IViewNavigationCallback(){

				@Override
				public void onCreate(IView arg0) {
					
				}

				@Override
				public void onDestroy(IView arg0) {
					
				}

				@Override
				public void onHide(IView arg0) {
					
				}

				@Override
				public void onShow(IView arg0) {
					
				}
				
			});
		}
		
		this.finish();
	}
}
