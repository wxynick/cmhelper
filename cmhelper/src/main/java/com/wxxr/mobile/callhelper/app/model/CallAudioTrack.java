/**
 * 
 */
package com.wxxr.mobile.callhelper.app.model;

import java.io.File;
import java.util.Date;

import android.graphics.Bitmap;

import com.wxxr.mobile.core.annotation.BindableBean;

/**
 * @author neillin
 *
 */
@BindableBean(pkg="com.wxxr.mobile.callhelper.app.bean",className="CallAudioTrackBean")
public class CallAudioTrack {
	private Date timestamp;
	private String phoneNumber,contactName;
	private int callType;
	private File dataFile;
	
	/**
	 * 是否正在录音
	 */
	private boolean isRecording;
	
	/**
	 * 联系人头像
	 */
	private Bitmap portrait;
	
	
}
