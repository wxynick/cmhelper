package com.wxxr.mobile.callhelper.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.SmsManager;
import android.text.ClipboardManager;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.wxxr.mobile.android.app.AppUtils;

public class Tools {
	private final static String[] CONTACT_PROJECTION = new String[] {
			PhoneLookup.DISPLAY_NAME, PhoneLookup.PHOTO_ID };
	private final static int DISPLAY_NAME_COLUMN_INDEX = 0;
	public final static Hashtable<String, String> remove_to_sysbox = new Hashtable<String, String>();
	private static Animation push_in, push_out;
	public static final float roundPX = 8f;// 图片圆角

	public static void showToast(Context context, String content) {
		Toast.makeText(context, content, 0).show();
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		boolean flag = true;
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	public static void toggleSoftInput(Context context, IBinder token) {
		((InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE))
				.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
						InputMethodManager.HIDE_NOT_ALWAYS);
	}


	public static boolean equals(Object obj1, Object obj2) {
		if ((obj1 == null) && (obj2 == null)) {
			return true;
		}
		if ((obj1 == null) && (obj2 != null)) {
			return false;
		}
		if ((obj1 != null) && (obj2 == null)) {
			return false;
		}
		return obj1.equals(obj2);
	}

	/**
	 * 加粗
	 * 
	 * @param textView
	 */
	public static void changeTextViewBold(TextView textView) {
		TextPaint paint = textView.getPaint();
		paint.setFakeBoldText(true);
	}

	/**
	 * 转化成sp
	 * 
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 */
	public static int px2sp(float pxValue, float fontScale) {
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 转化成dp
	 * 
	 * @param pxValue
	 * @param scale
	 * @return
	 */
	public static int px2dip(float pxValue, float scale) {
		return (int) (pxValue / scale + 0.5f);
	}

	/** 是否是竖屏 */
	public static boolean isPortrait(Context c) {
		if (c.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 录音时长
	 * 
	 * @param time
	 * @return
	 */
	public static String toTime(int time) {

		Date date = new Date(time);
		SimpleDateFormat format = new SimpleDateFormat("mm''ss''''");
		return format.format(date);
		// time /= 1000;
		// int minute = time / 60;
		// int hour = minute / 60;
		// int second = time % 60;
		// minute %= 60;
		// return String.format("%02d:%02d", minute, second);
	}

	public static Bitmap readBitmap(Context context, int id) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		// opt.inPreferredConfig=Bitmap.Config.RGB_565;//表示16位位图 565代表对应三原色占的位数
		opt.inInputShareable = true;
		opt.inPurgeable = true;// 设置图片可以被回收
		opt.inSampleSize = 8;
		opt.inJustDecodeBounds = false;
		InputStream is = context.getResources().openRawResource(id);
		return BitmapFactory.decodeStream(is, null, opt);
	}

	/**
	 * 根据宽和高压缩载入raw图片
	 * 
	 * @param context
	 * @param path
	 * @param width
	 * @param heigh
	 * @param id
	 * @return
	 */
	public static Bitmap getBitmap(Context context, int width, int height,
			int id) {
		Log.i("图片宽和高", width + "------" + height);
		Options op = new Options();
		op.inJustDecodeBounds = true;
		int xScale = op.outWidth / width;
		int yScale = op.outHeight / height;
		op.inSampleSize = xScale > yScale ? xScale : yScale;
		op.inJustDecodeBounds = false;
		InputStream is = context.getResources().openRawResource(id);
		return BitmapFactory.decodeStream(is, null, op);
	}

	/**
	 * 根据宽高压缩载入网络图片
	 * 
	 * @param path
	 * @param width
	 * @param height
	 * @return
	 */

	public static Bitmap getBitmap(String path) {
		FileInputStream fis = null;
		Options op = null;
		try {
			if (path.contains("http")) {
				URL url = new URL(path);
				HttpURLConnection httpURLconnection = (HttpURLConnection) url
						.openConnection();
				httpURLconnection.setRequestMethod("GET");
				httpURLconnection.setReadTimeout(6 * 1000);
				fis = (FileInputStream) httpURLconnection.getInputStream();

			} else {
				fis = new FileInputStream(new File(path));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
        Bitmap   bit=null;
        try{
		op = new Options();
	//	op.inJustDecodeBounds = true;
		op.inPurgeable = true;// 设置图片可以被回收
		op.inSampleSize = 1;
		op.inJustDecodeBounds = false;
		bit= BitmapFactory.decodeStream(fis, null, op);
		}catch(Throwable ee){
			  try{
				  fis = new FileInputStream(new File(path));
		    		op = new Options();
		    	//	op.inJustDecodeBounds = true;
		    		op.inPurgeable = true;// 设置图片可以被回收
		    		op.inSampleSize = 4;
		    		op.inJustDecodeBounds = false;
		    		bit=BitmapFactory.decodeStream(fis, null, op);
		    		}catch(Throwable e0e){
		    			
		    		}
		}
        
        return bit;
	}

	// public static Bitmap getBitmap(String path, int width, int height) {
	// FileInputStream fis = null;
	// Options op = null;
	// try {
	// if(path.contains("http")){
	// URL url = new URL(path);
	// HttpURLConnection httpURLconnection =
	// (HttpURLConnection)url.openConnection();
	// httpURLconnection.setRequestMethod("GET");
	// httpURLconnection.setReadTimeout(6*1000);
	// fis = (FileInputStream) httpURLconnection.getInputStream();
	//
	// }else{
	// fis = new FileInputStream(new File(path));
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// op = new Options();
	// op.inJustDecodeBounds = true;
	// int xScale = op.outWidth / width;
	// int yScale = op.outHeight / height;
	// op.inSampleSize = xScale > yScale ? xScale : yScale;
	// op.inPurgeable = true;// 设置图片可以被回收
	// op.inSampleSize = 8;
	// op.inJustDecodeBounds = false;
	// return BitmapFactory.decodeStream(fis, null, op);
	// }

	// public static byte[] getImage(String path) throws Exception {
	// URL url = new URL(path);
	// HttpURLConnection httpURLconnection =
	// (HttpURLConnection)url.openConnection();
	// httpURLconnection.setRequestMethod("GET");
	// httpURLconnection.setReadTimeout(6*1000);
	// InputStream in = null;
	// byte[] b = new byte[1024];
	// int len = -1;
	// if (httpURLconnection.getResponseCode() == 200) {
	// in = httpURLconnection.getInputStream();
	// byte[] result = readStream(in);
	// in.close();
	// return result;
	//
	// }
	// return null;
	// }
	//
	// public static byte[] readStream(InputStream in) throws Exception{
	// ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	// byte[] buffer = new byte[1024];
	// int len = -1;
	// while((len = in.read(buffer)) != -1) {
	// outputStream.write(buffer, 0, len);
	// }
	// outputStream.close();
	// in.close();
	// return outputStream.toByteArray();
	// }

	// public static void animPushIn(Context context, View view) {
	// Animation push_in = AnimationUtils.loadAnimation(context,
	// R.anim.push_in);
	// view.setAnimation(push_in);
	// }

	public static String getContactsName(Context context, String contactsNumber) {
		String sms_name = null;
		if (StringUtil.isEmpty(contactsNumber)) {
			return null;
		}

		Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
				Uri.encode(contactsNumber));

		Cursor contactCurosr = context.getContentResolver().query(uri,
				CONTACT_PROJECTION, null, null, null);
		if (contactCurosr != null) {
			if (contactCurosr.moveToNext()) {
				sms_name = checking(contactCurosr
						.getString(DISPLAY_NAME_COLUMN_INDEX));
				// if(!contactsName.equals(contactsNumber)){
				// sms_name = contactsName;
				// }
			}
			contactCurosr.close();
		}
		return sms_name;
	}

	/**
	 * 获得联系人号码（带空格）
	 * 
	 * @param context
	 * @param contactsNumber
	 * @return
	 */

	public static String getContactsStoreNumber(Context context,
			String contactsNumber) {
		String sms_name = null;
		if (StringUtil.isEmpty(contactsNumber)) {
			return null;
		}

		Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
				Uri.encode(contactsNumber));

		Cursor contactCurosr = context.getContentResolver().query(uri, null,
				null, null, null);
		if (contactCurosr != null) {
			if (contactCurosr.moveToNext()) {
				sms_name = contactCurosr.getString(contactCurosr
						.getColumnIndex(PhoneLookup.NUMBER));
			}
			contactCurosr.close();
		}
		return sms_name;
	}

	/**
	 * 获得联系人表里id
	 * 
	 * @param context
	 * @return
	 */

	public static String getContactsId(Context context, String telphone) {
		String number = getContactsStoreNumber(context, telphone);
		Cursor contactCurosr = context.getContentResolver().query(

				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				null,
				ContactsContract.CommonDataKinds.Phone.NUMBER + " = '" + number
						+ "'", null, null);

		String contact_id = "";
		if (contactCurosr.moveToFirst()) {

			contact_id = contactCurosr
					.getString(contactCurosr
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
		}
		contactCurosr.close();
		return contact_id;

	}

	public static boolean hasNetwork(Context context) {
		ConnectivityManager conManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networInfo = conManager.getActiveNetworkInfo(); // 在取得相關資訊
		if (networInfo == null || !networInfo.isAvailable()) {
			return false;
		}
		return true;
	}

	/**
	 * 打电话
	 */
	public static void call(Context context, String phoneNumer) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.CALL");
		intent.setData(Uri.parse("tel:" + phoneNumer));
		context.startActivity(intent);
	}

	/**
	 * 发送短信
	 * 
	 * @param context
	 * @param msgContent
	 * @param address
	 * @param type
	 *            如果type=1,不需要弹出发送成功或者失败的提示框
	 */
	public static void sendMsg(Context context, String msgContent,
			String address, int type) {
//		SmsManager mSmsManager = SmsManager.getDefault();
//		Intent sent = new Intent(Sms.SENT_SMS_ACTION);
//		PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, sent,
//				0);
//
//		Intent delivery = new Intent(Sms.DELIVERED_SMS_ACTION);
//		PendingIntent deliveryIntent = PendingIntent.getBroadcast(context, 0,
//				delivery, 0);
//
//		ArrayList<String> messages = mSmsManager.divideMessage(msgContent);
//		for (String message : messages) {
//			if (type == Constant.SEND_MSG_NO_TIP) {
//				mSmsManager.sendTextMessage(address, null, message, null, null);
//			} else {
//				mSmsManager.sendTextMessage(address, null, message, sentIntent,
//						deliveryIntent);
//			}
//			// save message
//			Uri uri = Sms.Sent.CONTENT_URI;
//			ContentValues values = new ContentValues();
//			// address and body
//			values.put(Sms.ADDRESS, address);
//			values.put(Sms.BODY, message);
//			context.getContentResolver().insert(uri, values);
//		}
	}

	/**
	 * 得到年和月
	 * 
	 * @param date
	 * @return
	 */
	public static String getCallRecordDate(String strDate) {

		SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
		Date date = null;
		SimpleDateFormat format = null;
		try {
			date = sdf.parse(strDate);
			format = new SimpleDateFormat("MM月dd日 HH:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return format.format(date);
	}

	/** 获得今天的日期2012-06-24 */
	public static String getToday() {
		String pattern = "yyyy-MM-dd";
		Calendar c = Calendar.getInstance();
		return dateToString(c.getTime(), pattern);
	}

	/** 将Date转化成String7月26日 13：56 */
	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat d = new SimpleDateFormat(pattern);
		return d.format(date);
	}

	/**
	 * 将Long转化成String型日期
	 * 
	 * @param l
	 * @param pattern
	 * @return
	 */
	public static String longToString(long l, String pattern) {
		Date date = new Date(l);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public static ArrayList<String> parseSmsContent(String content) {
		ArrayList<String> list = new ArrayList<String>();
		if (content != null) {
			Pattern pattern = Pattern
					.compile("(?<!\\d)(?:(?:1[3458]\\d{9})|(?:861[3458]\\d{9}))(?!\\d)");
			Matcher matcher = pattern.matcher(content);
			while (matcher.find()) {
				list.add(matcher.group());
			}
		}
		return list;

	}

	// 查找内容中的手机号码
	public static String getMisdnByContent(String content) {
		ArrayList<String> l = Tools.parseSmsContent(content);
		if (l != null && !l.isEmpty()) {
			return l.get(0);
		}
		return null;
	}

	// 根据出生年月算年龄yyyy-MM-dd
	public static int getCurrentAgeByBirthdate(String brithday)
			throws ParseException, Exception {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
			String currentTime = formatDate.format(calendar.getTime());
			Date today = formatDate.parse(currentTime);
			Date brithDay = formatDate.parse(brithday);

			return today.getYear() - brithDay.getYear();
		} catch (Exception e) {
			return 0;
		}
	}

	public static String getGendar(boolean isMale) {
		return isMale == true ? "男" : "女";
	}

	public static boolean isMale(String gendar) {
		return gendar.equals("男") ? true : false;
	}



	public static boolean hasInboxSmsOfPhoneNum(Context context, String phonenum) {
		boolean reuslt = false;
//		Cursor c = context
//				.getApplicationContext()
//				.getContentResolver()
//				.query(Sms.Inbox.CONTENT_URI, null,
//						Sms.ADDRESS + " = " + phonenum, null, null);
//		if (c.moveToFirst()) {
//			reuslt = true;
//		}
//		c.close();
		return reuslt;
	}



	// 根据年，月，日，给出毫秒数,一天的开始时间
	public static long getMillisByDay(String year, String month, String day) {
		Calendar birth = Calendar.getInstance();
		birth.set(Integer.parseInt(year), Integer.parseInt(month) - 1,
				Integer.parseInt(day), 0, 0);
		return birth.getTimeInMillis();
	}

	// 根据年，月，日，给出毫秒数,一天的结束时间
	public static long getMaxMillisByDay(String year, String month, String day) {
		Calendar birth = Calendar.getInstance();
		birth.set(Integer.parseInt(year), Integer.parseInt(month) - 1,
				Integer.parseInt(day), 23, 59);
		return birth.getTimeInMillis();
	}

	// 得到本周第一天的毫秒值
	public static long getMondayOfWeek() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		String[] dates = preMonday.split("-");
		return getMillisByDay(dates[0], dates[1], dates[2]);
	}

	// 得到本周第七天的毫秒值
	public static long getSundayOfWeek() {
		int mondayPlus = getMondayPlus() + 6;
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		String[] dates = preMonday.split("-");
		return getMaxMillisByDay(dates[0], dates[1], dates[2]);
	}

	// 不许乱改了
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 0) {
			return -6;
		} else {
			return 1 - dayOfWeek;
		}
	}

	// 不许乱改了
	public static int getlastDay() {
		int day = 0;
		Calendar cd = Calendar.getInstance();
		int year = cd.get(Calendar.YEAR);
		int month = cd.get(Calendar.MONTH);
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			day = 30;
			break;
		case 2:
			if (year % 400 == 0 || year % 100 != 0 && year % 4 == 0) {
				day = 29;
			} else {
				day = 28;
			}

			break;
		}

		return day;
	}

	/**
	 * 去除电话中空格或者横线
	 * 
	 * @param number
	 * @return
	 */
	public static String goneSpace(String number) {
		if (number.contains(" ") || number.contains("-")) {
			String phoneNum = number.replaceAll(" ", "").replace("-", "");
			return phoneNum;
		}
		return number;
	}

	/**
	 * 去除电话号码中的前缀
	 * 
	 * @param number
	 * @return
	 */
	public static String checking(String number) {

		String numWithNoSpacAndPre = goneSpace(number);

		if (numWithNoSpacAndPre.startsWith("86")) {

			numWithNoSpacAndPre = numWithNoSpacAndPre.substring(2);

		} else if (numWithNoSpacAndPre.startsWith("+86")) {

			numWithNoSpacAndPre = numWithNoSpacAndPre.substring(3);

		} else if (numWithNoSpacAndPre.startsWith("17951")) {

			numWithNoSpacAndPre = numWithNoSpacAndPre.substring(5);

		} else if (numWithNoSpacAndPre.startsWith("12520")) {

			numWithNoSpacAndPre = numWithNoSpacAndPre.substring(5);
		}
		return numWithNoSpacAndPre;
	}

	/**
	 * 判断号码是否是手机号码
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isMobileNum(String number) {
		String phoneNum = checking(number);
		if (phoneNum.startsWith("1") && phoneNum.length() == 11) {
			return true;
		}
		// if (phoneNum.length() == 11 || phoneNum.length() == 6) {
		// return true;
		// }
		return false;
	}

	/**
	 * 判断当前应用是否活着
	 */
	public static boolean isLiving(Context context) {
		String mypackage = "com.wxxr.callhelper";
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);

		List<RunningTaskInfo> taskInfos = activityManager.getRunningTasks(100);

		if (taskInfos.size() > 0) {
			for (int i = 0; i < taskInfos.size(); i++) {
				if (mypackage.equals(taskInfos.get(i).topActivity
						.getPackageName())) {
					return true;
				}
			}

		}

		return false;

	}

	/**
	 * animation
	 * 
	 * @param context
	 * @return push_in
	 */
	public static void pushIn(Context context, ViewGroup vg) {
//		if (push_in == null) {
//			push_in = AnimationUtils.loadAnimation(context, R.anim.push_in);
//			vg.setAnimation(push_in);
//		}
//		vg.startAnimation(push_in);
	}

	/**
	 * animation
	 * 
	 * @param context
	 * @return push_out
	 */
	public static void pushOut(Context context, ViewGroup vg) {
//		if (push_out == null) {
//			push_out = AnimationUtils.loadAnimation(context, R.anim.push_out);
//			vg.setAnimation(push_out);
//		}
//		vg.startAnimation(push_out);
	}

	/**
	 * 获取头像
	 * 
	 * @param mContext
	 * @param address
	 * @return
	 */
	public static Bitmap getContactsHeadBitmap(Context mContext, String address) {
		Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
				Uri.encode(address));
		String sms_imgid = null;
		Bitmap head = null;
		Cursor contactCurosr = mContext.getContentResolver().query(uri,
				CONTACT_PROJECTION, null, null, null);
		if (contactCurosr != null) {
			if (contactCurosr.moveToNext()) {
				sms_imgid = contactCurosr.getString(1);
				if (sms_imgid != null && sms_imgid.length() > 0) {
					Cursor img = mContext.getContentResolver()
							.query(Data.CONTENT_URI,
									new String[] { Data.MIMETYPE, Data._ID,
											Data.DATA15 },
									Data._ID + " = " + sms_imgid, null, null);
					if (img.moveToFirst()) {
						// String type=img.getString(0);
						byte[] aa = img.getBlob(2);
						head = BitmapFactory.decodeByteArray(aa, 0, aa.length);
					}
					img.close();
				}
			}
			contactCurosr.close();
		}
		return head;
	}

	private static Notification getNotify(String title, Context mcontext) {
//		Notification notify = new Notification(R.drawable.app_icon_small,
//				title, System.currentTimeMillis());
//		notify.flags = Notification.FLAG_AUTO_CANCEL;
		// if(PushMessagePre.getputPushSetting(GlobalContext.mContext).indexOf("1#")!=-1){
		// notify.sound = Uri.parse("android.resource://com.wxxr.app.kid/" +
		// R.raw.alarm);
		// }

		// if(PushMessagePre.getputPushSetting(GlobalContext.mContext).indexOf("#1")!=-1){
		// notify.vibrate
		// long[] pattern
		// ：自定义震动模式 。数组中数字的含义依次是静止的时长，震动时长，静止时长，震动时长。。。时长的单位是毫秒

		// 受手机设置的限制
		// notify.defaults|=Notification.DEFAULT_VIBRATE;

		// 不受 手机 设置的限制
//		long pattern[] = new long[] { 0, 100, 200, 300, 100, 1000 };
//		Vibrator vib = (Vibrator) mcontext
//				.getSystemService(Service.VIBRATOR_SERVICE);
//		vib.vibrate(pattern, -1);
		// }

		return null;
	}

	/**
	 * 将textview中的字符全角化。
	 */

	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	public static void copyToClipboard(Context montext, String text) {
		((ClipboardManager) montext.getSystemService(Context.CLIPBOARD_SERVICE))
				.setText(text);
	}

	public static void copyDBToSdcard(Context montext, String text) {
		File f = new File("/data/data/com.wxxr.callhelper/databases/" + text);
		try {
			FileInputStream input = new FileInputStream(f);

			byte[] buf = new byte[input.available()];
			FileOutputStream out = new FileOutputStream(new File("mnt/sdcard/"
					+ text));
			input.read(buf);
			out.write(buf);
			input.close();
			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 判断是否是汉字
	 */
	public static String getLastChar(String str) {
		String lastChar = null;
		if (!TextUtils.isEmpty(str)) {
			// 判断是否为汉字字符
			if (str.matches("[\\u4E00-\\u9FA5]+")) {
				lastChar = str.substring(str.length() - 1);
			}
		}
		return lastChar;
	}

	public static boolean isEMailStr(String mailstr) {

		return mailstr
				.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");

	}

	public static String getDeviceID(Activity activity) {
		// TelephonyManager tManager = (TelephonyManager) activity
		// .getSystemService(Context.TELEPHONY_SERVICE);
		// String deviceId = tManager.getDeviceId();
		// // deviceId="20131209";
		return AppUtils.getFramework().getDeviceId();
	}

	public static HashMap<String, String> divParameFromURL(String url) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			// detail?channelCd=RWLS&amp;channelName=人文历史
			int index = url.indexOf("?");
			url = url.substring(index + 1);
			String[] value = url.split("&");
			int leng = value.length;
			for (int i = 0; i < leng; i++) {
				String[] keyvalue = value[i].split("=");
				if (keyvalue.length > 1) {
					map.put(keyvalue[0], keyvalue[1]);
				}
			}

		} catch (Exception e) {

		}
		return map;
	}

	/**
	 * 广场在调用
	 * 
	 * @param url
	 * @param baseContext
	 * @return
	 */
	public static boolean processAction(String url, Context baseContext) {
		boolean result = false;
		// public final String ACTION_LOGIN="action=login";
		// public final String ACTION_SUMMITIDERA="action=submitidear";
		// public final String ACTION_OPENHUIZHI="action=openhuizhi";
		// public final String ACTION_SMSSET="action=smsset";

//		if (url != null) {
//			url = URLDecoder.decode(url);
//			if (url.contains(Constant.ACTION_LOGIN)) {
//				Intent t = new Intent(baseContext,
//						GDPrivateZooLoginActivity.class);
//				t.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				baseContext.startActivity(t);
//				result = true;
//			} else if (url.contains(Constant.ACTION_SUMMITIDERA)) {
//
//				Intent t = new Intent(baseContext,
//						GDPrivateZooInformCommonActivity.class);
//				t.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				t.putExtra(Constant.PRIVATE_INFORM_EDIT, Constant.IDEA_BACK);
//				baseContext.startActivity(t);
//				result = true;
//			} else if (url.contains(Constant.ACTION_OPENHUIZHI)) {
//				Intent intent = new Intent(baseContext,
//						ConfirmDialogActivity.class);
//				intent.putExtra(Constant.DIALOG_KEY, Constant.NOW_OPEN);
//				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				baseContext.startActivity(intent);
//				result = true;
//			} else if (url.contains(Constant.ACTION_SMSSET)) {
//				Intent t = new Intent(baseContext,
//						GDSmsHuiZhiSettingActivity.class);
//				t.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				baseContext.startActivity(t);
//				result = true;
//
//			} else if (url.contains(Constant.ACTION_ACTIVITY_GUIZE)) {
//				Intent t = new Intent(baseContext,
//						GDPushMsgWebLinkActivity.class);
//				t.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//				// HtmlMessage html = new HtmlMessage();
//				// html.setTitle("活动规则");
//				// html.setOrigUrl(url);
//
//				t.putExtra(Constant.DIALOG_KEY, Constant.LOAD_DETAIL);
//				t.putExtra("url", url);
//				baseContext.startActivity(t);
//				result = true;
//
//			}
//		}

		return result;
	}

	/**
	 * 登录验证是否为合法的手机号码或者密码
	 * 
	 * @param num
	 * @param pwd
	 * @return
	 */
	public static boolean isValidNumOrPwd(Context context, String num,
			String pwd) {
		String trimedNum = num.trim();
		String trimedPwd = pwd.trim();
		if (trimedNum == null || trimedNum.length() != 11
				|| !trimedNum.startsWith("1")) {
			showToast(context, "账号须为1开头的11位手机号码，请重新输入");
			return false;
		}
		if (trimedPwd == null || trimedPwd.length() != 6) {
			showToast(context, "动态密码须为6位数字，请重新输入");
			return false;
		}
		return true;
	}

	public static boolean isValidNumOrPwd(Context context, String num) {
		String trimedNum = num.trim();
		if (trimedNum == null || trimedNum.length() != 11
				|| !trimedNum.startsWith("1")) {
			showToast(context, "账号须为1开头的11位手机号码，请重新输入");
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param context
	 * @param clazz
	 *            是否在登录完成后要跳转到其他Activity，如果不需要输入null.
	 */
	public static void Login(Context context, Class<? extends Context> clazz) {
//		Intent intent = new Intent(context, GDPrivateZooLoginActivity.class);
//		intent.putExtra(Constant.REDIRECT_ACTIVITY, clazz);
//		context.startActivity(intent);
	}

	/**
	 * 短信分享
	 * 
	 * @param context
	 * @param content
	 *            需要通过短信分享的内容
	 */
	public static void ShareBySMS(Context context, String content) {
		Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("smsto",
				"", null));
		intent.putExtra("sms_body", content);
		context.startActivity(intent);
	}

	/**
	 * 图片增加圆角
	 * 
	 * @param bitmap
	 * @param roundPX
	 * @return
	 */
	public static Bitmap getRCB(Bitmap bitmap, float roundPX) {
		Bitmap dstbmp = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(dstbmp);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPX, roundPX, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return dstbmp;
	}

	public static String getChatTime(String now, String chattime) {
		StringBuffer sb = new StringBuffer();

		// yyyy-M-d-HH-mm
		String nows[] = now.split("-");
		String chats[] = chattime.split("-");
		int leng = nows.length;
		boolean result[] = new boolean[leng];

		for (int i = 0; i < leng; i++) {
			result[i] = nows[i].equals(chats[i]);
		}
		// 按照年月日 权重是4,2,1 做 计算 区分 什么情况 3以上 表示年不一致了，所以要显示 年月日
		int total = (result[0] ? 0 : 4) + (result[1] ? 0 : 2)
				+ (result[2] ? 0 : 1);
		switch (total) {
		case 0:
			sb.append(chats[3]).append(":").append(chats[4]);
			break;
		case 1:
		case 2:
		case 3:
			sb.append(chats[1]).append("月").append(chats[2]).append("日")
					.append(" ").append(chats[3]).append(":").append(chats[4]);
			break;
		case 4:
		case 5:
		case 6:
		case 7:
			sb.append(chats[0]).append("年").append(chats[1]).append("月")
					.append(chats[2]).append("日").append(" ").append(chats[3])
					.append(":").append(chats[4]);
		}

		return sb.toString();
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
}
