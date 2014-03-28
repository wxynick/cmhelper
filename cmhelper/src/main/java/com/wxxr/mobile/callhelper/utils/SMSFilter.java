package com.wxxr.mobile.callhelper.utils;


public class SMSFilter {
	public static final String FILTER_LOUHUA_SQL =null;

	public static boolean isSmsOKHuizhi(String body, String number) {
		if(number.startsWith("10658007")&&(body.contains("短信送达") || body.contains("成功")))
				{
					return true;
				}
		return false;
	}

	public static boolean isSmsFailHuizhi(String body, String number) {		
			if (number.startsWith("10658007")&&(body.contains("未收到") 
					||body.contains("暂未") || body.contains("失败")))
					{
			return true;
			}
	
		return false;
	}

	public static boolean isLouHua(String body) {
		if (body.contains("中国移动北京公司来电提醒") || body.contains("广东移动提醒您")
				&& body.contains("给您来电") && body.contains("次")
				&& body.contains("您可按通话键或选项键直接回拨")) {

			return true;
		}
		return false;
	}
}
