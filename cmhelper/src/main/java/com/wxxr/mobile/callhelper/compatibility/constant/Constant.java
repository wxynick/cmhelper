/*
 * @(#)Constant.java	 2012-7-13
 *
 * Copyright 2004-2012 WXXR Network Technology Co. Ltd. 
 * All rights reserved.
 * 
 * WXXR PROPRIETARY/CONFIDENTIAL.
 */

package com.wxxr.mobile.callhelper.compatibility.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import android.os.Environment;

public class Constant {
	
	public static final int  SIMI_NEWSMS_ID = 20140209;
	public static final Integer HUIZHI_ARRIVED_MSTYLE = 0;
	public static final Integer HUIZHI_NOT_ARRIVED_MSTYLE = 1;
	public static final int LOADING = 0;
	public static final String PACKAGE_NAME = "com.wxxr.callhelper";

	public static final String HUIZHI_STYLE = "huizhi_style";

	public static final String SIMISUO = "wxxr_simisuo";

	public static final String haved_intercept = "haved_intercept";

	public static final String HUIZHILANJIE = "wxxr_huizhilanjie";

	// 对话框key
	public static final String DIALOG_KEY = "number";
	public static final String DIALOG_ICON_KEY = "boxIcon";
	public static final String DIALOG_TITLE_KEY = "boxTitle";
	public static final String DIALOG_HEADER_KEY = "boxHeader";
	// 对话框key
	public static final String DIALOG_MESSAGE = "message";
	public static final String DIALOG_INTERACTIVE_MODE = "interactive_mode";

	public static final int DIALOG_INTERACTIVE_MODE_NONE = 0;
	public static final int DIALOG_INTERACTIVE_MODE_OK = 1;
	public static final int DIALOG_INTERACTIVE_MODE_OK_CANCEL = 2;
	public static final int DIALOG_INTERACTIVE_MODE_EMPTY = 3;
	public static final int DIALOG_INTERACTIVE_MODE_3BUTTON = 4;
	// 私密锁邮箱
	public static final int EMAIL_EMPTY = 16340;
	// 私密锁密码不一致
	public static final int PWD_DIFFERENT = 12345680;
	// 私密锁密码修改成功
	public static final int PWD_EDIT_SUCCESS = 20131224;
	// 录音退出
	public static final int CALLRECORD_EXIT = 1010;

	// 客服电话
	public static final int SERVICE_PHONE = 5487;

	// 马上开通
	public static final int NOW_OPEN = 2543;

	// 特别提示
	public static final int SPECIAL_PROMPT = 4521;

	// 版本
	public static final int VERSION = 9587;
	// 版本
	public static final String CLIENT_INFO = "client_info";
	// 手机号
	public static final int TELPHONE = 1389;

	// 验证码
	public static final int AUTHCODE = 123456;

	// 验证码
	public static final int TERM = 1380;

	// 接收场景
	public static final int STAGE_MODE = 6543;
	// 密码
	public static final int PASSWORLD_LENGTH = 5621;
	public static final int PASSWORLD_NOT_SAME = 1407;
	public static final int PASSWORLD_NULL = 1254;
	public static final int SCUCESS_REGISTER = 1255;
	// 移动查询
	public static final int MOBILE_QUERY = 1256;
	// 头像
	public static final int ICON = 1258;
	// 性别选择
	public static final int GENDER = 1257;
	// 年龄选择
	public static final int AGE = 1259;
	public static final int DATE_DIALOG_ID = 1;
	public static final int TIME_DIALOG_ID = 0;
	// 所在地选择
	public static final int REGION = 1260;
	// 移动查询
	public static final int COUNT_QUERY = 1261;
	public static final int ORDER_BUSINESS = 1262;
	public static final int GPRS_REMANNING = 1263;
	public static final int CALL_QUERY = 1264;
	public static final int SEND_MSG_NO_TIP = 1;
	public static final int FIRST_TIME_SHOW = 1265;
	public static final int DELETE_SINGLE_ITEM = 1266;
	public static final String SWITCH_SETTING_FLAG = "setting_flag";
	public static final int CONGZHI_REMINDER = 1267;
	public static final String CONGZHI_SETTING = "congzhi_open_close";
	public static final String PRIVATE_INFORM_EDIT = "private_inform_edit";
	public static final int NICK_NAME_EIDT = 1268;
	public static final int TEL_NUM_EIDT = 1269;
	public static final int GENDAR_EIDT = 1270;
	public static final int IDEA_BACK = 1271;
	public static final int CONFIRMED_CONGZHI = 1272;
	public static final int DIALOG_CHONGZHI = 1273;
	public static final int DIALOG_SHARE = 1274;
	public final static String IMAGE_URI = "iamge_uri";
	public final static String CROP_IMAGE_URI = "crop_image_uri";
	public final static String CACHEDUSER = "cacheduser";
	public final static int APP_RECOMMENED = 1200;
	public final static int PRIVACY_ITEM = 1300;
	public final static int NICKNAME = 1280;
	public final static String PINGDAO_INFO = "pingdao_info";
	public final static String SHARE_ID = "share_id";//区分，是那个模块的分享
	
	public final static String REDIRECT_ACTIVITY = "redirect_activity";
	public final static int GO_LOGIN = 1281;
	public final static int CONGZHI_MENU_DELETE = 1282;
	public final static String PUSH_IN = "frompush";
	public static final String LOAD_DETAIL = "load_detail";
	// sd卡头像路径
	public static String SD_ICON = Environment.getExternalStorageDirectory()
			.getPath() + "/MyIcon";
	// sd卡录音路径
	public static String SDPATH = Environment.getExternalStorageDirectory()
			.getPath() + "/MyCallRecorder";

	// MAGNOLIA_URL
	public static String MAGNOLIA_URL = null;

	// buildtime
	public static String BUILDTIME = null;

	// target
	public static String TARGET = null;
	// 退出激活
	public static final int DE_ACTIVE = 444;
	// 去激活
	public static final int DO_ACTIVE = 443;
	// 新对话框（删除录音）
	public static final int DELETE_RECORD = 333;

	// 删除漏化详情 和 回执详情的 数据
	public static final int DETAIL_DELETE_RECORD = 20131129;
	// 从菜单删除漏化详情 和 回执详情的 数据
	public static final int DETAIL_MULT_DELETE_RECORD = 2013112902;

	// 长按删除
	public static final int LONG_DELETE_RECORD = 333333;

	// 长按复制
	public static final int LONG_COPY_RECORD = 20131127;

	// 私密锁解锁,
	public static final int SM_LOCK_OPEN = 7878;

	// 私密锁对话框
	public static final int SM_LOCK_SETTING_DIALOG = 3838;

	// 录音设置对话框
	public static final int CALLRECORDER_SETTING_DIALOG = 9494;

	// 漏话详情页长按删除
	public static final int LH_DETAIL_LONG_DELETE_RECORD = 353535;

	// 回执详情页长按删除
	public static final int HZ_DETAIL_LONG_DELETE_RECORD = 363636;
	// 回执页弹出框点击删除单条短信
	public static final int HZ_DETAIL_DELETE_SINGLE_RECORD = 363635;
	// 长按解锁
	public static final int LONG_SM_LOCK_OPEN = 787878;

	// 删除单条 聊天记录 记录
	public static final int DEL_ONE_CHAT_ITME = 886886;

	// 删除多条 聊天记录 记录
	public static final int DEL_MANY_CHAT_ITME = 886887;

	// 询问导系统短信数据
	public static final int CONFIRM_IMPORSYS = 901;

	// 导入短信
	public static final int CONFIRM_DOIMPORSYS = 702;
	// 取消导入短信
	public static final int CONFIRM_NOTIMPORSYS = 201;

	public static final int IMPORT_SYSMES = 401;

	public static final int DEL_SMMSG = 402;

	public static final int DEL_SMMSG_ATDIALG = 403;

	public static final String REQUEST_CODE = "2013092499";

	// 需要导入短信的手机号
	public static final String IMPORT_NUM = "import_phonenum";

	// 需要导入短信或者删除的联系人的手机号
	public static final String IMPORT_OR_DEL_NUMS = "all_phonenum";

	// 是否是导入数据,有的话，value长度大于0
	public static final String IS_IMPORT_NUMS = "isimport_phonenum";

	// 屏幕的宽度
	public static int ScreenWidth = 0;
	// sd卡归属地表路径
	// public static String SDPATH_LOCATION =
	// Environment.getExternalStorageDirectory().getPath() + "/MyDB";

	// 应用目录下
	public static final String SDPATH_LOCATION = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/";
//			+ PACKAGE_NAME;

	// 数据库名字
	public static String DB_NAME = "phone_range.db";

	// 归属地开关（通话）
	public static final String LOCATION_PHONE = "location_phone";

	// 归属地开关（短信）
	public static final String LOCATION_MSG = "location_msg";

	// 通话录音设置
	public static final String CALLRECORD_SETTING = "callrecord_setting";

	// 版本状态
	public static final String VERSION_STATUS = "version_status";

	// 刷新首页
	public static final String REFRESH_HOME = "refresh_home";

	// 未安装控制
	public static final String UNINSTALL = "unInstall";

	// 通话录音引导页
	public static final String GUIDE_CALLRECORDER = "guide_callrecorder";

	// 私密锁引导
	// 第一步
	public static final String SM_LOCK_FIRST = "sm_lock_first";

	// 第二步
	public static final String SM_LOCK_SECOND = "sm_lock_second";

	// 第三步
	public static final String SM_LOCK_THIRD = "sm_lock_third";

	// 微资讯跳转
	public static final String ISHYPERLINK = "isHyperlink";

	// 微资讯跳转地址
	public static final String HYPERLINK_ADDRESS = "hyperlink_address";

	// 微资讯跳转file地址
	public static final String PUSH_ISHYPERLINK = "push_isHyperlink";

	// 推送标题
	public static final String PUSH_TITLE = "push_title";

	// 推送摘要
	public static final String PUSH_ABSTRCT = "push_abstrct";

	// 推送分享地址
	public static final String PUSH_SHARE_ADDRESS = "push_share_address";

	// 推送图片
	public static final String PUSH_IMAGE = "push_image";

	// 控制通话录音的开和关
	public static final String CALLRECORDER_OPEN_CLOSE = "callrecorder_open_close";

	// 场景模式
	public static final String STAGE_MODE_VALUE = "stage_mode_value";

	// 保留私信所的数据到系统短信库
	public static final String SAVE_SMS_TOSYS = "stage_mode_value";

	// 对话框内容
	public static final String DIALOG_CONTENT = "dialog_content";

	private static Map<String, String> provices = new HashMap<String, String>();

	// 以下是有来电提醒规则的省份
	public static final String PROVICE_HB = "HB";// 湖北
	public static final String PROVICE_BJ = "BJ";// 北京
	public static final String PROVICE_LN = "LN";// 辽宁
	public static final String PROVICE_ZJ = "ZJ";// 浙江
	public static final String PROVICE_NMG = "NMG";// 内蒙古
	public static final String PROVICE_HEBEI = "HE";// 河北
	public static final String PROVICE_SX = "SX";// 山西
	public static final String PROVICE_GZ = "GZ";// 贵州
	public static final String PROVICE_GD = "GD";// 广东
	public static final String PROVICE_SH = "SH";// 上海
	public static final String PROVICE_SC = "SC";// 四川
	public static final String PROVICE_AH = "AH";// 安徽
	public static final String PROVICE_AM = "AM";// 澳门
	public static final String PROVICE_CQ = "CQ";// 重庆
	public static final String PROVICE_FJ = "FJ";// 福建
	public static final String PROVICE_GS = "GS";// 甘肃
	public static final String PROVICE_GX = "GX";// 广西
	public static final String PROVICE_HN = "HI";// 海南
	public static final String PROVICE_HUNAN = "HN";// 湖南
	public static final String PROVICE_HEINAN = "HA";// 河南
	public static final String PROVICE_HLJ = "HLJ";// 黑龙江
	public static final String PROVICE_JL = "JL";// 吉林
	public static final String PROVICE_JS = "JS";// 江苏
	public static final String PROVICE_JX = "JX";// 江西
	public static final String PROVICE_NX = "NX";// 宁夏
	public static final String PROVICE_QH = "QH";// 青海
	public static final String PROVICE_SD = "SD";// 山东
	public static final String PROVICE_SHANXI = "SN";// 陕西
	public static final String PROVICE_TW = "TW";// 台湾
	public static final String PROVICE_TJ = "TJ";// 天津
	public static final String PROVICE_XZ = "XZ";// 西藏
	public static final String PROVICE_XG = "XG";// 香港
	public static final String PROVICE_XJ = "XJ";// 新疆
	public static final String PROVICE_YN = "YN";// 云南

	static {
		provices.put(PROVICE_HB, "湖北");
		provices.put(PROVICE_BJ, "北京");
		provices.put(PROVICE_LN, "辽宁");
		provices.put(PROVICE_ZJ, "浙江");
		provices.put(PROVICE_NMG, "内蒙古");
		provices.put(PROVICE_HEBEI, "河北");
		provices.put(PROVICE_SX, "山西");
		provices.put(PROVICE_GZ, "贵州");
		provices.put(PROVICE_GD, "广东");
		provices.put(PROVICE_SH, "上海");
		provices.put(PROVICE_AH, "安徽");
		provices.put(PROVICE_AM, "澳门");
		provices.put(PROVICE_CQ, "重庆");
		provices.put(PROVICE_FJ, "福建");
		provices.put(PROVICE_GS, "甘肃");
		provices.put(PROVICE_GX, "广西");
		provices.put(PROVICE_SC, "四川");
		provices.put(PROVICE_HN, "海南");
		provices.put(PROVICE_HUNAN, "湖南");
		provices.put(PROVICE_HEINAN, "河南");
		provices.put(PROVICE_HLJ, "黑龙江");
		provices.put(PROVICE_JL, "吉林");
		provices.put(PROVICE_JS, "江苏");
		provices.put(PROVICE_JX, "江西");
		provices.put(PROVICE_NX, "宁夏");
		provices.put(PROVICE_QH, "青海");
		provices.put(PROVICE_SD, "山东");
		provices.put(PROVICE_SHANXI, "陕西");
		provices.put(PROVICE_TW, "台湾");
		provices.put(PROVICE_TJ, "天津");
		provices.put(PROVICE_XZ, "西藏");
		provices.put(PROVICE_XG, "香港");
		provices.put(PROVICE_XJ, "新疆");
		provices.put(PROVICE_YN, "云南");
	}

	public static final String ACTION_LOGIN = "action=login";// 去往 登陆页面
	public static final String ACTION_SUMMITIDERA = "action=submitidear";// 去往
																			// 意见反馈
	public static final String ACTION_OPENHUIZHI = "action=openhuizhi";// 去往开通
	public static final String ACTION_SMSSET = "action=smsset";// 去往 短信回执设置页面
	public static final String ACTION_ACTIVITY_GUIZE = "action=gz";// 去往/广场
	
	public static final String PHONE_NUMBER = "phone_number";//

	// 根据省份编码获取省份名称
	static public String getproviceName(String code) {
		return (String) provices.get(code);
	}

	static public String getProviceCode(String proviceName) {
		Set<Map.Entry<String, String>> set = provices.entrySet();
		for (Entry<String, String> entry : set) {
			String proviceCode = entry.getKey();
			if (getproviceName(proviceCode).equals(proviceName)) {
				return proviceCode;
			}
		}
		return null;
	}

	public static enum GOActivity {
		GO_NULL, // 占位置，对应索引
		GO_LOGIN, // 登陆
		GO_SUBIDEAR, // 意见反馈
		GO_SMSSET, // 频道设置
		GO_NONE, // 无跳转
		GO_OUTWAP, // 外部wap
		GO_INERWAP, // 内部wap
		GO_UPDATE, // 升级
		GO_SQUARE, // 广场
		GO_LOGIN2, // 登陆2
		GO_HOME// home

	}

}
