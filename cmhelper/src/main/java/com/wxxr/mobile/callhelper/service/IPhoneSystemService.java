package com.wxxr.mobile.callhelper.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.wxxr.mobile.callhelper.app.bean.ContactPersonBean;
import com.wxxr.mobile.callhelper.app.model.SMSStatus;

public interface IPhoneSystemService extends ICommmonService{
	
	public interface PictureCallback {
        /**
         * Called when image data is available after a picture is taken.
         * The format of the data depends on the context of the callback
         * and {@link Camera.Parameters} settings.
         *
         * @param data   a byte array of the picture data
         * @param camera the Camera service object
         */
        void onPictureTaken(byte[] data);
    };
	
	/**
	 * 发送短信
	 * @param targetNumber
	 * @param content
	 * @return
	 */
	boolean sentMessage(String targetNumber,String content);
	
	
	/**
	 * 设置手机短信的状态
	 * @param status
	 * @param phoneNumber --- 电话号码
	 * @param content --- 短信的内容
	 * @param date --- 短信时间
	 * @return
	 */
	boolean setMessage(SMSStatus status,String phoneNumber,String content,Long date,int style);
	
	
	/**
	 * 发送通知
	 */
	void sentTextNotification(String tickerText);
	
	
	/**
	 * 发送通知
	 */
	void sentSoundNotification(String resource);
	
	
	/**
	 * 发送通知,带有系统参数
	 */
	void sentNotification(String tickerText,Map<String,Object> args);
	
	
	/**
	 * 返回电话的所有联系人的信息
	 * @return
	 */
	List<ContactPersonBean> getAllContactPerson();
	
	/**
	 * 一个电话号码对应一个联系人，如果查出某个联系人是多个因该返回null
	 * @param phoneNumber
	 * @return
	 */
	ContactPersonBean getContactPersonByPhoneNumber(String phoneNumber);
	
	/**
	 * 呼叫系统的打电话功能
	 */
	void callUp(String num);
	
	/**
	 * @param Parameters
	 * @param callback
	 */
	void takePicture(PictureCallback callback,HashMap<String, String> Parameters);
	
	
	/**
	 * 开始录音
	 */
	void startRecorder(String path,HashMap<String, String> parameters);
	
	/**
	 * 停止录音
	 */
	void stopRecorder();
	
	
	/**
	 * 添加联系人
	 * @param phoneNumber
	 */
	void addContactPerson(String phoneNumber);
}
