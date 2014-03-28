package com.wxxr.mobile.callhelper.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.wxxr.mobile.android.app.AppUtils;
import com.wxxr.mobile.callhelper.app.bean.SMSInfoBean;
import com.wxxr.mobile.callhelper.app.model.SMSInfo;
import com.wxxr.mobile.callhelper.compatibility.bean.BodyBean;
import com.wxxr.mobile.callhelper.compatibility.dao.LouHuaDao;
import com.wxxr.mobile.callhelper.compatibility.dao.RemindSettingDao;
import com.wxxr.mobile.callhelper.service.IMissCallService;
import com.wxxr.mobile.callhelper.service.IPhoneSystemService;
import com.wxxr.mobile.callhelper.service.ISMSInterceptHandler;
import com.wxxr.mobile.callhelper.service.ISmsContentParseService;

/**
 * 拦截电话漏接的短信
 * @author fudapeng
 *
 */
public class MissCallInterceptHandler implements ISMSInterceptHandler {

	private SimpleDateFormat format_month = new SimpleDateFormat("MM");
	
	private IMissCallService missService;
	
	
	private ISmsContentParseService parseService;
	
	private IPhoneSystemService phoneService;
	
	
	public IPhoneSystemService getPhoneSystemService() {
        if (phoneService == null) {
        	phoneService = AppUtils.getService(IPhoneSystemService.class);
        }
        return phoneService;
    }
	
	
	public ISmsContentParseService getSmsContentParseService() {
        if (parseService == null) {
        	parseService = AppUtils.getService(ISmsContentParseService.class);
        }
        return parseService;
    }

	
	private synchronized IMissCallService getMissCallService(){
		if(missService == null){
			missService = AppUtils.getService(IMissCallService.class);
		}
		return missService;
	}
	
	
	/**
     * 获取当前手机号码的省份，根据省份找到相应的规则，只要满足其中的一条规则则返回ture，否则flase
     * @param smsContent
     * @param targetnumber
     * @return
     */
    private boolean isMatch(String smsContent, String targetnumber) {
        return getMissCallService().isMatch(smsContent, targetnumber);
    }
    
	@Override
	public boolean handle(SMSInfoBean smsInfo) {
		if (!isMatch(smsInfo.getContent(), smsInfo.getPhoneNumber())) {
            return false;
        }

        String parseBody = getSmsContentParseService().parseSmsContent(smsInfo.getContent());

        LouHuaDao ldao = LouHuaDao.getInstance(AppUtils.getFramework().getAndroidApplication());
        BodyBean bb = new BodyBean();
        bb.address = smsInfo.getPhoneNumber();
        bb.content = parseBody;
        bb.cdate = new Date().getTime();
        bb.mstyle = 0;
        String mmonth = format_month.format(bb.cdate);
        bb.amonth = mmonth;
        bb.state = 1;
        ldao.insert(bb);
        RemindSettingDao rsdao = RemindSettingDao.getInstance(AppUtils.getFramework().getAndroidApplication());
        int status = rsdao.queryLouHuaSetting();
        
        if(smsInfo.getIsStorage() == false){
	        // 插入系统收件箱
	        ContentValues values = new ContentValues();
	        values.put("address", smsInfo.getPhoneNumber());
	        values.put("type", "1");
	        if(status==2){
	        	   values.put("read", "1");// 已读
	        }else{
	        	   values.put("read", "0");// 未读
	        }
	     
	        values.put("body", smsInfo.getContent());
	        values.put("date", new Date().getTime());
	        Uri uri = AppUtils.getFramework().getAndroidApplication().getApplicationContext().getContentResolver().insert(Uri.parse("content://sms/inbox"), values);
        }
        Intent intent2 = new Intent();
        intent2.setAction("com.wxxr.home.refreshlouhuaorhuizhi");
        AppUtils.getFramework().getAndroidApplication().sendBroadcast(intent2);

      
        if (status == 2) {
            ActivityManager am = (ActivityManager) AppUtils.getFramework().getAndroidApplication().getSystemService(Context.ACTIVITY_SERVICE);
            ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
            String currentClassName = cn.getClassName();
            getPhoneSystemService().sentSoundNotification(null);
//            sendNotifySound(AppUtils.getFramework().getAndroidApplication());
//            Intent in = new Intent();
//            in.setClass(AppUtils.getFramework().getAndroidApplication(), LouHuaRealBaoGaoActivity.class);
//            in.putExtra("inter_number", number);
//            in.putExtra("inter_body", parseBody);
//            in.putExtra("infor_style", 1);
//            in.putExtra("infor_month", mmonth);
//            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            AppUtils.getFramework().getAndroidApplication().startActivity(in);
        }
        return true;
	}


}
