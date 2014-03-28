package com.wxxr.mobile.callhelper.constant;

import java.text.SimpleDateFormat;

public class TimeFormat {
	
	public static SimpleDateFormat format_yue = new SimpleDateFormat("yyyyMM");
	public static SimpleDateFormat format_yue_day = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat format_day = new SimpleDateFormat("MMdd");
	public static SimpleDateFormat format_today = new SimpleDateFormat("HH:mm");
	public static SimpleDateFormat format_time = new SimpleDateFormat("M月dd日");
	
	public  static SimpleDateFormat format_time_for_sms_content =  new SimpleDateFormat("yyyy-M-d-HH-mm");;
}
