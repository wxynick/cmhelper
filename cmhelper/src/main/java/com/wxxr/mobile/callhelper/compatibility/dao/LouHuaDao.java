package com.wxxr.mobile.callhelper.compatibility.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wxxr.mobile.callhelper.compatibility.bean.BodyBean;
import com.wxxr.mobile.callhelper.compatibility.db.SMSLouHuaOpenHelper;
import com.wxxr.mobile.callhelper.utils.Tools;

public class LouHuaDao {
	SMSLouHuaOpenHelper dbOpenHelper;
	SQLiteDatabase db;
	private static LouHuaDao csi = null;

	private LouHuaDao(Context context) {

		dbOpenHelper = new SMSLouHuaOpenHelper(context);
	}

	public static LouHuaDao getInstance(Context context) {
		if (csi == null) {
			synchronized (LouHuaDao.class) {
				if (csi == null) {
					csi = new LouHuaDao(context);
				}
			}
		}
		return csi;
	}

	public boolean insert(BodyBean body) {
		if (db == null) {
			db = dbOpenHelper.getWritableDatabase();
		}
		ContentValues values = new ContentValues();
		values.put("smsnumber", body.address);
		values.put("smstime", body.cdate);
		values.put("smscontent", body.content);
		values.put("smsstyle", body.mstyle);
		values.put("yuefen", body.amonth);
		values.put("state", body.state);
		db.insert("tablelouhua", null, values); // insert into person (name)
		return true;
	}

	public int findLouHuaCount(String number, String month) {
		int num = 0;
		if (db == null) {
			db = dbOpenHelper.getReadableDatabase();
		}
		if (db.isOpen()) {
			Cursor cursor = db
					.rawQuery(
							"select * from tablelouhua where smsnumber = ? and yuefen = ? and smsstyle = 0",
							new String[] { number, month });
			try {
				if (cursor != null && cursor.getCount() > 0) {
					num = cursor.getCount();
				}
			} finally {
				if (cursor != null)
					cursor.close();
			}
		}
		return num;

	}

	public void deleteSomeSMS(String content) {
		if (db == null) {
			db = dbOpenHelper.getWritableDatabase();
		}
		if (db.isOpen()) {
			db.execSQL("delete from tablelouhua where smscontent = ?",
					new Object[] { content });
		}
	}

	public boolean hasRecord(long time) {
		boolean hasrecord = false;
		if (db == null) {
			db = dbOpenHelper.getReadableDatabase();
		}
		if (db.isOpen()) {
			Cursor cursor = db.rawQuery(
					"select * from tablelouhua where smstime = ?",
					new String[] { "" + time });
			try {
				if (cursor.moveToFirst()) {
					hasrecord = true;
				}
			} finally {
				if (cursor != null)
					cursor.close();
			}
		}
		return hasrecord;
	}

	public void deleteSMSLouHua(int id) {
		if (db == null) {
			db = dbOpenHelper.getWritableDatabase();
		}
		if (db.isOpen()) {
			// sql = '" +telnumber + "'
			db.execSQL("delete from tablelouhua where _id = ?",
					new Object[] { id });

		}
	}

	// 只按月份，跨年会有问题,添加 time来区分
	public void deleteSMSAccordingtoNumberAndMonth(String telnumber,
			String month, long time) {
		if (db == null) {
			db = dbOpenHelper.getWritableDatabase();
		}
		if (db.isOpen()) {
			// sql = '" +telnumber + "'
			String[] times = getOneYearTime(time);
			String sql="delete from tablelouhua where  smscontent  like '%"+telnumber+"%'";
			db.execSQL(sql);

		}
	}
	
	
	// 只按月份，跨年会有问题,添加 time来区分
	public void deleteSMSAccordingtoNumber(String telnumber) {
		if (db == null) {
			db = dbOpenHelper.getWritableDatabase();
		}
		if (db.isOpen()) {
		
			db.execSQL(
					"delete from tablelouhua where smsnumber = ? ",
					new Object[] { telnumber });

		}
	}

	private String[] getOneYearTime(long time) {
		SimpleDateFormat format_yue = new SimpleDateFormat("yyyyMM");
		String year = format_yue.format(new Date(time)).substring(0, 4);
		String end = Tools.getMaxMillisByDay(year, "12", "31") + "";
		String start = Tools.getMillisByDay(year, "1", "1") + "";
		return new String[] { start, end };
	}

	public ArrayList<BodyBean> queryAllLouHua(long start, long end) {
		ArrayList<BodyBean> bodys = new ArrayList<BodyBean>();
		if (db == null) {
			db = dbOpenHelper.getReadableDatabase();
		}
		if (db.isOpen()) {
			Cursor cursor = null;
			if (start == -1 || end == -1) {
				cursor = db
						.rawQuery(
								"select * from tablelouhua where smsstyle = 0 order by smstime desc",
								null);
			} else {
				cursor = db.rawQuery(
						"select * from tablelouhua where   smstime > " + start
								+ " and smstime  < " + end
								+ " and smsstyle = 0 order by smstime desc",
						null);
			}

			while (cursor.moveToNext()) {

				BodyBean bb = new BodyBean();
				String number = cursor.getString(cursor
						.getColumnIndex("smsnumber"));
				String content = cursor.getString(cursor
						.getColumnIndex("smscontent"));
				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
				String amonth = cursor.getString(cursor
						.getColumnIndex("yuefen"));
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				bb.mstyle = smsstyle;
				bb.content = content;
				bb.cdate = date;
				bb.address = number;
				bb.amonth = amonth;
				bb.bodyID = id;
				bb.realnum=Tools.getMisdnByContent(content);
				bodys.add(bb);

			}
			cursor.close();
		}
		return bodys;
	}

	public ArrayList<BodyBean> queryAllLouHua_Send(String telnumber) {
		ArrayList<BodyBean> bodys = new ArrayList<BodyBean>();
		if (db == null) {
			db = dbOpenHelper.getReadableDatabase();
		}
		if (db.isOpen()) {
			Cursor cursor = db.rawQuery(
					"select * from tablelouhua where smsstyle = 1 and smsnumber = '"
							+ telnumber + "' order by smstime desc", null);
			while (cursor.moveToNext()) {
				BodyBean bb = new BodyBean();
				String number = cursor.getString(cursor
						.getColumnIndex("smsnumber"));
				String content = cursor.getString(cursor
						.getColumnIndex("smscontent"));
				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
				String amonth = cursor.getString(cursor
						.getColumnIndex("yuefen"));
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				bb.mstyle = smsstyle;
				bb.content = content;
				bb.cdate = date;
				bb.address = number;
				bb.amonth = amonth;
				bb.bodyID = id;
				bodys.add(bb);

			}
			cursor.close();
		}
		return bodys;
	}

//	public BodyBean queryMostNewLouHua() {
//		// ArrayList<BodyBean> bodys = new ArrayList<BodyBean>();
//		BodyBean bb = null;
//		if (db == null) {
//			db = dbOpenHelper.getReadableDatabase();
//		}
//		if (db.isOpen()) {
//			Cursor cursor = db
//					.rawQuery(
//							"select * from tablelouhua where smsstyle = 0 order by smstime desc",
//							null);
//			if (cursor.moveToNext()) {
//
//				bb = new BodyBean();
//				String number = cursor.getString(cursor
//						.getColumnIndex("smsnumber"));
//				String content = cursor.getString(cursor
//						.getColumnIndex("smscontent"));
//				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
//				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
//				String amonth = cursor.getString(cursor
//						.getColumnIndex("yuefen"));
//				bb.mstyle = smsstyle;
//				bb.content = content;
//				bb.cdate = date;
//				bb.address = number;
//				bb.amonth = amonth;
//				// bodys.add(bb);
//
//			}
//			cursor.close();
//		}
//		return bb;
//	}

//	public ArrayList<BodyBean> queryAll(String telnumber, String month) {
//		ArrayList<BodyBean> bodys = new ArrayList<BodyBean>();
//		if (db == null) {
//			db = dbOpenHelper.getReadableDatabase();
//		}
//		if (db.isOpen()) {
//			String sql = "select * from tablelouhua where smsnumber ='"
//					+ telnumber + "' and yuefen = '" + month
//					+ "' and smsstyle = 0 order by smstime desc";
//			// where current_user ='" +current_user +
//			// "' order by datetime desc";
//			Cursor cursor = db.rawQuery(sql, null);
//			while (cursor.moveToNext()) {
//				BodyBean bb = new BodyBean();
//				String number = cursor.getString(cursor
//						.getColumnIndex("smsnumber"));
//				String content = cursor.getString(cursor
//						.getColumnIndex("smscontent"));
//				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
//				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
//				String amonth = cursor.getString(cursor
//						.getColumnIndex("yuefen"));
//				int id = cursor.getInt(cursor.getColumnIndex("_id"));
//				bb.mstyle = smsstyle;
//				bb.content = content;
//				bb.cdate = date;
//				bb.address = number;
//				bb.amonth = amonth;
//				bb.bodyID = id;
//				bodys.add(bb);
//
//			}
//			cursor.close();
//		}
//		return bodys;
//	}
//	
	
	
	public ArrayList<BodyBean> queryAllByNumFromContent(String telnumber) {
		ArrayList<BodyBean> bodys = new ArrayList<BodyBean>();
		if (db == null) {
			db = dbOpenHelper.getReadableDatabase();
		}
		if (db.isOpen()) {
			String sql = "select * from tablelouhua where smscontent   like '%"
					+ telnumber + "%' and smsstyle = 0 order by smstime desc";
			// where current_user ='" +current_user +
			// "' order by datetime desc";
			Cursor cursor = db.rawQuery(sql, null);
			while (cursor.moveToNext()) {
				BodyBean bb = new BodyBean();
				String number = cursor.getString(cursor
						.getColumnIndex("smsnumber"));
				String content = cursor.getString(cursor
						.getColumnIndex("smscontent"));
				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
				String amonth = cursor.getString(cursor
						.getColumnIndex("yuefen"));
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				bb.mstyle = smsstyle;
				bb.content = content;
				bb.cdate = date;
				bb.address = number;
				bb.amonth = amonth;
				bb.bodyID = id;
				bb.realnum=Tools.getMisdnByContent(content);
				bodys.add(bb);

			}
			cursor.close();
		}
		return bodys;
	}

	// 更新短信的状态，是否由未读变成已读---根据号码和月份
	public void update(String telnumber, String month, Integer state, long time) {
		String times[]=getOneYearTime(time);
		if (db == null) {
			db = dbOpenHelper.getWritableDatabase();
		}
		ContentValues values = new ContentValues();
		values.put("state", state);
		db.update("tablelouhua", values, "smsnumber=? and yuefen=? and smstime > ? and smstime < ?",
				new String[] { telnumber, month,times[0],times[1]});
	}

	// 查询所有的未读短信---根据手机号码和月份 ,跨年会有问题，添加时间
	//LouHuaRealBaoGaoActivity  中，调用的都是，实时的数据，不会跨年的，所以传递进来的，就是当前的时间
	public int queryAllLouhua_accordingtoNumberAndMonth(String telnumber,
			String month, Integer state, long time) {

		String times[] = getOneYearTime(time);

		if (db == null) {
			db = dbOpenHelper.getReadableDatabase();
		}
		Cursor c = null;
		try {
			c = db.query(
					"tablelouhua",
					null,
					"smsnumber=? and state=? and yuefen=? and smstime > ? and smstime < ?",
					new String[] { telnumber, state.toString(), month,
							times[0], times[1] }, null, null, "smstime");
			if (c != null) {
				return c.getCount();
			}
			return 0;
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (Throwable t) {
				}
			}
		}
	}
}
