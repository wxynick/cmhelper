package com.wxxr.mobile.callhelper.service.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wxxr.mobile.android.app.AppUtils;
import com.wxxr.mobile.callhelper.ICallHeplerAppContext;
import com.wxxr.mobile.callhelper.app.bean.RegionBean;
import com.wxxr.mobile.callhelper.compatibility.constant.Constant;
import com.wxxr.mobile.callhelper.service.IAffiliationAreaService;
import com.wxxr.mobile.callhelper.utils.Tools;
import com.wxxr.mobile.core.log.api.Trace;
import com.wxxr.mobile.core.microkernel.api.AbstractModule;
import com.wxxr.mobile.core.util.StringUtils;

public class AffiliationAreaService extends AbstractModule<ICallHeplerAppContext>
		implements
			IAffiliationAreaService {
	private static final Trace log = Trace.register(AffiliationAreaService.class);
//	private final String path = Constant.SDPATH_LOCATION + "/"
//			+ Constant.DB_NAME;
	SQLiteDatabase db;

	protected SQLiteDatabase getDatabase() {
		if (db == null || !db.isOpen()) {
			String packageName = AppUtils.getFramework().getAndroidApplication().getPackageName();
			String path = Constant.SDPATH_LOCATION + packageName + "/" + Constant.DB_NAME;
			db = SQLiteDatabase.openDatabase(path, null,
					SQLiteDatabase.NO_LOCALIZED_COLLATORS);
		}
		return db;
	}

	@Override
	public RegionBean getRegionByPhoneNumber(String phoneNumber) {
		RegionBean r = null;
		// （广东版前七位开始匹配，不做11位的手机号判断）判断是否是手机号,如果有根据手机号码查找归属地，如果没有则取三位，或者四位作为区号查询归属地
		if (phoneNumber.startsWith("0")) {
			r = getRegionbyAreaCode(phoneNumber);

		} else if (phoneNumber.startsWith("1")) {
			r = getRegionByMsisdn(phoneNumber);
		}
		return r;
	}

	public RegionBean getRegionByDialogNumber(String dialogNumber) {
		RegionBean r = null;
		// 判断是否是手机号,如果有根据手机号码查找归属地，如果没有则取三位，或者四位作为区号查询归属地
		String msisdn = Tools.getMisdnByContent(dialogNumber);
		if (StringUtils.isBlank(msisdn)) {
			if (dialogNumber.startsWith("0")) {
				if (dialogNumber.length() >= 4) {
					r = getRegionbyAreaCode(dialogNumber
							.substring(0, 4));
					if (r == null) {
						r = getRegionbyAreaCode(dialogNumber.substring(
								0, 3));
					}
				}

			}
		} else {
			r = getRegionByMsisdn(msisdn);
		}
		return r;
	}

	public RegionBean getRegionByDialogNumberAsyn(String dialogNumber) {
		RegionBean r = null;
		// 判断是否是手机号,如果有根据手机号码查找归属地，如果没有则取三位，或者四位作为区号查询归属地
		String msisdn = Tools.getMisdnByContent(dialogNumber);
		if (StringUtils.isBlank(msisdn)) {
			if (dialogNumber.startsWith("0")) {
				if (dialogNumber.length() >= 4) {
					r = getRegionbyAreaCode(dialogNumber.substring(0, 4));
					if (r == null) {
						r = getRegionbyAreaCode(dialogNumber.substring(0, 3));
					}
				}

			}
		} else {
			r = getRegionByMsisdn(msisdn);
		}
		return r;
	}
	@Override
	public RegionBean getRegionBySmsContentAsyn(final String smsContent) {
		RegionBean r = getRegionBySmsContent(smsContent);
		return r;
	}

	/**
	 * 根据手机号码（必须是11位的手机号码）
	 * 
	 * @param phoneNum
	 * @return
	 */

	public RegionBean getRegionByMsisdn(String phoneNum) {
		Cursor c = null;
		RegionBean r = null;
		if (phoneNum.length() > 10) {
			phoneNum = phoneNum.substring(phoneNum.length() - 11,
					phoneNum.length());
		}
		if (phoneNum.length() < 7) {
			return null;
		}
		try {
			String sql = "select r.name , b.brandname,r.region_id ,r.p_region_id from phone_range p , region r , brand b where p.range_id = ? and p.brand_id = b.brandid and p.region_id = r.region_id";
			String value = phoneNum.substring(0, 7);
			c = getDatabase().rawQuery(sql, new String[]{value});
			r = new RegionBean();

			r.setPhoneNumber(phoneNum);

			if (c.moveToNext()) {
				r.setRegionName(c.getString(0));
				r.setBrandName(c.getString(1));
				r.setRegionId(c.getLong(2));
				r.setPRegionId(c.getLong(3));
				// 查找上一级归属地
				RegionBean p = getRegionbyParentRegionid(r.getPRegionId());
				r.setPRegionName(p.getRegionName());
			} else {
				return null;
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return r;

	}

	// 查找上一级归属地
	protected RegionBean getRegionbyParentRegionid(long regionId) {
		Cursor c = null;
		try {
			String sql = "select r.region_id,r.name ,r.p_region_id from region r  where r.region_id = ? ";
			c = getDatabase().rawQuery(sql,
					new String[]{String.valueOf(regionId)});

			if (c.moveToNext()) {
				RegionBean r = new RegionBean();
				r.setRegionId(c.getLong(0));
				r.setRegionName(c.getString(1));
				r.setPRegionId(c.getLong(2));
				return r;
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return null;
	}

	/**
	 * 根据用户拨打的号码找出区号，再根据区号找归属地
	 * 
	 * @param phonenumber
	 * @return
	 */
	public RegionBean getRegionbyAreaCode(String areaCode) {

		if (StringUtils.isNotBlank(areaCode)) {
			RegionBean r = null;
			if (areaCode.length() == 4 || areaCode.length() == 3) {
				r = getRegionbycode(areaCode.substring(0, areaCode.length()));
			}
			return r;
		}
		return null;
	}

	// 根据区号查找归属地
	protected RegionBean getRegionbycode(String code) {
		log.info("begin getRegionbycode code=" + code);
		Cursor c = null;
		try {
			String sql = "select r.region_id,r.name ,r.p_region_id from region r  where r.code = ? ";
			c = getDatabase().rawQuery(sql, new String[]{String.valueOf(code)});

			if (c.moveToNext()) {
				RegionBean r = new RegionBean();
				r.setRegionId(c.getLong(0));
				r.setRegionName(c.getString(1));
				r.setPRegionId(c.getLong(2));
				// 查找上一级的归属地名称
				RegionBean p = getRegionbyParentRegionid(r.getPRegionId());
				r.setPRegionName(p.getRegionName());
				log.info("getRegionbycode RegionBean=" + r.toString());

				return r;
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return null;
	}
	// 是否是移动的手机号码
	public boolean isChinaMobileMsisdn(String msisdn) {
		log.info("begin isChinaMobileMsisdn code=" + msisdn);
		if (StringUtils.isBlank(msisdn) || msisdn.length() != 11) {
			return false;
		}

		String prefix = msisdn.substring(0, 7);
		Cursor c = null;
		try {
			String sql = "select a.telecom from phone_range a where   a.range_id = ? ";
			c = getDatabase().rawQuery(sql,
					new String[]{String.valueOf(prefix)});
			if (c.moveToNext()) {
				return "1".equals(c.getString(0));// 1 是移动 2是电信
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return false;
	}

	public RegionBean getRegionBySmsContent(String smsContent) {
		String msisdn = Tools.getMisdnByContent(smsContent);
		if (StringUtils.isNotBlank(msisdn)) {
			RegionBean r = this.getRegionByMsisdn(msisdn);
			if (r != null) {
				return r;
			} else {
				r = new RegionBean();
				r.setPhoneNumber(msisdn);
				return r;
			}
		}
		return null;
	}

	@Override
	protected void initServiceDependency() {

	}

	@Override
	protected void startService() {
		context.registerService(IAffiliationAreaService.class, this);
	}

	@Override
	protected void stopService() {
		context.unregisterService(IAffiliationAreaService.class, this);
		if (db != null && db.isOpen()) {
			db.close();
		}
	}


}
