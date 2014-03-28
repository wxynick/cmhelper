/**
 * 
 */
package com.wxxr.mobile.callhelper.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.wxxr.mobile.callhelper.app.model.CallAudioTrack;

/**
 * 录音服务，调用IPhoneSystemService 服务的录音 生成 CallAudioTrack
 * @author fudapeng
 */
public interface ICallRecorderService {
	public static final int CALL_TYPE_OUTGOING = 0;
	
	public static final int CALL_TYPE_INCOMING = 1;

	void startCallRecord() throws IOException;
	
	void stopCallRecord() throws IOException;
	
	List<CallAudioTrack> getAllAudioTrack();
	
	boolean removeAudioTrack(CallAudioTrack track);
	
	public void refreshTracks();
	
}
