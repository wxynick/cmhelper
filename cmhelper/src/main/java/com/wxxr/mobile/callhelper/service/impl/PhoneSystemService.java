/**
 * 
 */
package com.wxxr.mobile.callhelper.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.PhoneLookup;
import android.text.TextUtils;
import android.widget.Toast;

import com.wxxr.mobile.android.app.AppUtils;
import com.wxxr.mobile.android.ui.RUtils;
import com.wxxr.mobile.callhelper.ICallHeplerAppContext;
import com.wxxr.mobile.callhelper.app.bean.ContactPersonBean;
import com.wxxr.mobile.callhelper.app.bean.SMSInfoBean;
import com.wxxr.mobile.callhelper.app.bean.SMSSessionGroupBean;
import com.wxxr.mobile.callhelper.app.model.SMSInfo;
import com.wxxr.mobile.callhelper.app.model.SMSSessionGroup;
import com.wxxr.mobile.callhelper.app.model.SMSStatus;
import com.wxxr.mobile.callhelper.service.IPhoneSystemService;
import com.wxxr.mobile.callhelper.utils.Tools;
import com.wxxr.mobile.core.microkernel.api.AbstractModule;
import com.wxxr.mobile.core.microkernel.api.KUtils;

/**
 * @author fudapeng
 *
 */
public class PhoneSystemService extends AbstractModule<ICallHeplerAppContext> implements
		IPhoneSystemService {

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#deleteMessage(java.lang.Integer)
	 */
	@Override
	public void deleteMessage(Integer msgId) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#deleteMessage(com.wxxr.mobile.callhelper.app.model.SMSInfo)
	 */
	@Override
	public void deleteMessage(SMSInfo msg) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#deleteMessageGroup(com.wxxr.mobile.callhelper.app.model.SMSSessionGroup)
	 */
	@Override
	public void deleteMessageGroup(SMSSessionGroupBean msg) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#deleteMessage(java.lang.String, boolean)
	 */
	@Override
	public void deleteMessage(String number, boolean needfreshcache) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#getAllUnreadSize()
	 */
	@Override
	public int getAllUnreadSize() {
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#getUnreadSizeOfPhoneNumber(java.lang.String)
	 */
	@Override
	public int getUnreadSizeOfPhoneNumber(String num) {
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#setReadMsgOfPhoneNumber(java.lang.String)
	 */
	@Override
	public void setReadMsgOfPhoneNumber(String num) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#setUnReadMsgOfPhoneNumber(java.lang.String)
	 */
	@Override
	public void setUnReadMsgOfPhoneNumber(String num) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.ICommmonService#getShowList()
	 */
	@Override
	public List<SMSSessionGroupBean> getShowList() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPhoneSystemService#sentMessage(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean sentMessage(String targetNumber, String content) {
		if (!TextUtils.isEmpty(content))
			Tools.sendMsg(AppUtils.getFramework().getAndroidApplication(), content, targetNumber, 0);
		else
			Toast.makeText(AppUtils.getFramework().getAndroidApplication(), "系统忙，请稍候再试...", 0).show();
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPhoneSystemService#setMessage(com.wxxr.mobile.callhelper.app.model.SMSStatus, java.lang.String, java.lang.String, java.lang.Long, int)
	 */
	@Override
	public boolean setMessage(SMSStatus status, String phoneNumber,
			String content, Long date, int style) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPhoneSystemService#sentNotification(java.lang.String)
	 */
	@Override
	public void sentTextNotification(String tickerText) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPhoneSystemService#sentNotification(java.lang.String, java.util.Map)
	 */
	@Override
	public void sentNotification(String tickerText, Map<String, Object> args) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPhoneSystemService#getAllContactPerson()
	 */
	@Override
	public List<ContactPersonBean> getAllContactPerson() {
		
		return null;
	}
	
	
	
	public static byte[] bitmapToByte(Bitmap b) {
	    if (b == null) {
	        return null;
	    }

	    ByteArrayOutputStream o = new ByteArrayOutputStream();
	    // 压缩成制定的.PNG格式
	    b.compress(Bitmap.CompressFormat.PNG, 100, o);
	    return o.toByteArray();
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPhoneSystemService#getContactPersonByPhoneNumber(java.lang.String)
	 */
	@Override
	public ContactPersonBean getContactPersonByPhoneNumber(String phoneNumber) {
		Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
				Uri.encode(phoneNumber));
		String[] CONTACT_PROJECTION = new String[] {
				PhoneLookup.DISPLAY_NAME, PhoneLookup.PHOTO_ID };
		Cursor contactCurosr = AppUtils.getFramework().getAndroidApplication().getContentResolver().query(uri,
				CONTACT_PROJECTION, null, null, null);
		String sms_imgid = null;
		String sms_name = null;
		byte[] head = null;
		if (contactCurosr != null) {
			if (contactCurosr.moveToNext()) {
				sms_name = contactCurosr
						.getString(0);
				
				sms_imgid = contactCurosr.getString(1);
				if (sms_imgid != null && sms_imgid.length() > 0) {
					Cursor img = AppUtils.getFramework().getAndroidApplication().getContentResolver()
							.query(Data.CONTENT_URI,
									new String[] { Data.MIMETYPE, Data._ID,
											Data.DATA15 },
									Data._ID + " = " + sms_imgid, null, null);
					if (img.moveToFirst()) {
						// String type=img.getString(0);
						head = img.getBlob(2);
//						head = BitmapFactory.decodeByteArray(aa, 0, aa.length);
					}
					img.close();
				}
			}
			contactCurosr.close();
		}
		
		if(head == null){
			Resources res = AppUtils.getFramework().getAndroidApplication().getResources();
			Bitmap bitmap = BitmapFactory.decodeResource(res, RUtils.getInstance().getResourceIdByURI("resourceId:drawable/gd_callrecord_item_portrait"));
			head = bitmapToByte(bitmap);
		}
		
		ContactPersonBean bean = new ContactPersonBean();
		bean.setFristName(sms_name);
		bean.setLastName(sms_name);
		bean.setLastChar(Tools.getLastChar(sms_name));
		Byte[] h= new Byte[head.length];
		for(int i = 0; i< head.length; i++){
			h[i] = head[i];
		}
		bean.setPortrait(h	);
		bean.setPhoneNumber(phoneNumber);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPhoneSystemService#callUp()
	 */
	@Override
	public void callUp(String num){
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		intent.setAction(Intent.ACTION_CALL);
    	intent.setData(Uri.parse("tel:" + num));
		AppUtils.getFramework().getAndroidApplication().startActivity(intent);

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPhoneSystemService#takePicture(com.wxxr.mobile.callhelper.service.IPhoneSystemService.PictureCallback, java.util.HashMap)
	 */
	@Override
	public void takePicture(PictureCallback callback,
			HashMap<String, String> Parameters) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPhoneSystemService#startRecorder(java.lang.String, java.util.HashMap)
	 */
	@Override
	public void startRecorder(String path, HashMap<String, String> parameters) {
		

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.callhelper.service.IPhoneSystemService#stopRecorder()
	 */
	@Override
	public void stopRecorder() {
		

	}

	@Override
	protected void initServiceDependency() {
		
		
	}

	@Override
	protected void startService() {
		context.registerService(IPhoneSystemService.class, this);
	}

	@Override
	protected void stopService() {
		
		
	}

	@Override
	public void sentSoundNotification(String resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveMessage(SMSInfoBean bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addContactPerson(String phoneNumber) {
	}


}
