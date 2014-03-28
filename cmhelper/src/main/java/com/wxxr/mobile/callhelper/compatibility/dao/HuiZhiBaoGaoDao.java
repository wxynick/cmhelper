package com.wxxr.mobile.callhelper.compatibility.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wxxr.mobile.callhelper.compatibility.bean.BodyBeanHuiZhi;
import com.wxxr.mobile.callhelper.compatibility.db.HuiZhiOpenHelper;
import com.wxxr.mobile.callhelper.utils.Tools;

public class HuiZhiBaoGaoDao {
	HuiZhiOpenHelper dbOpenHelper;

	private static HuiZhiBaoGaoDao csi = null;

	private HuiZhiBaoGaoDao(Context context) {
		dbOpenHelper = new HuiZhiOpenHelper(context);
	}

	public static HuiZhiBaoGaoDao getInstance(Context context) {
		if (csi == null) {
			synchronized (HuiZhiBaoGaoDao.class) {
				if (csi == null) {
					csi = new HuiZhiBaoGaoDao(context);
				}
			}
		}
		return csi;
	}

	public synchronized boolean insert(BodyBeanHuiZhi body) {

		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("smsnumber", body.address);
		values.put("smstime", body.cdate);
		values.put("smscontent", body.content);
		values.put("smsstyle", body.mstyle);
		values.put("yuefen", body.amonth);
		values.put("tosomebody", body.tosomebody);
		values.put("state", body.state);
		db.insert("tablehuizhibaogao", null, values); // insert into person
														// (name)
		// values NULL
		db.close();
		return true;
	}

	//隔年就不好用了，需要添加时间段
	public int findHuiZhiCount(String number, String month,long time) {
		String times[]=getOneYearTime(time);
		int num = 0;
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			Cursor cursor = db
					.rawQuery(
							"select * from tablehuizhibaogao where tosomebody = ? and yuefen = ? and smstime > ? and smstime < ?",
							new String[] { number, month ,times[0],times[1]});
			try {
				if (cursor != null && cursor.getCount() > 0) {
					num = cursor.getCount();
	
					while (cursor.moveToNext()) {
						int smsstyle = cursor.getInt(cursor
								.getColumnIndex("smsstyle"));
						if (smsstyle == 3) {
							num = num - 1;
						}
					}
				}
			}finally{
				if(cursor != null)
					cursor.close();
				db.close();
			}

		}
		return num;

	}
    //隔年的话，只有月份是不够用的，只能按照时间段来区分了
	public int findHuiZhi_ArriveCount(String number, String month,long time) {
		
		String times[]=getOneYearTime(time);		
		
		int num = 0;
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			Cursor cursor = db
					.rawQuery(
							"select * from tablehuizhibaogao where tosomebody = ? and smsstyle = 0 and yuefen = ? and smstime > ? and smstime < ?",
							new String[] { number, month,times[0],times[1]});
			try {
				if (cursor != null && cursor.getCount() > 0) {
					num = cursor.getCount();
				}
			}finally{
				if(cursor != null)
					cursor.close();
				db.close();
			}

		}
		return num;

	}
	 //隔年的话，只有月份是不够用的，只能按照时间段来区分了
	public int findHuiZhi_NotArriveCount(String number, String month,long time) {
		String times[]=getOneYearTime(time);
		int num = 0;
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			Cursor cursor = db
					.rawQuery(
							"select * from tablehuizhibaogao where tosomebody = ? and smsstyle = 1 and yuefen = ?" +
							" and smstime > ?  and smstime < ?",
							new String[] { number, month,times[0],times[1]});
			try {
				if (cursor != null && cursor.getCount() > 0) {
					num = cursor.getCount();
				}
			}finally{
				if(cursor != null)
					cursor.close();
				db.close();
			}
		}
		return num;

	}

	 //隔年的话，只有月份是不够用的，只能按照时间段来区分了
	public void deleteSMSAccordingtoNumberAndMonth(String telnumber,
			String month,long time) {
		String times[]=getOneYearTime(time);
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		if (db.isOpen()) {
			db.execSQL(
					"delete from tablehuizhibaogao where tosomebody = ? and yuefen = ? and smstime > ? and smstime < ?",
					new Object[] {telnumber, month,times[0],times[1]});

			db.close();
		}
	}

//	public void deleteSMSAccordingtoNumberAndMonth_NotArrive(String telnumber,
//			String month,long time) {
//		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
//		if (db.isOpen()) {
//			db.execSQL(
//					"delete from tablehuizhibaogao where tosomebody = ? and yuefen = ? and smsstyle = 1",
//					new Object[] { telnumber, month });
//
//			db.close();
//		}
//	}

//	public void deleteSMSAccordingtoNumberAndMonth_Arrive(String telnumber,
//			String month,long time) {
//		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
//		if (db.isOpen()) {
//			db.execSQL(
//					"delete from tablehuizhibaogao where tosomebody = ? and yuefen = ? and smsstyle = 0",
//					new Object[] { telnumber, month });
//
//			db.close();
//		}
//	}

	public void deleteSMSAccordingtoNumber(String telnumber) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		if (db.isOpen()) {
			db.execSQL("delete from tablehuizhibaogao where smsnumber = ?",
					new Object[] { telnumber });

			db.close();
		}
	}

	public void deleteSMSHuiZhi(int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		if (db.isOpen()) {
			db.execSQL("delete from tablehuizhibaogao where _id = ?",
					new Object[] { id });

			db.close();
		}
	}

	public ArrayList<BodyBeanHuiZhi> queryAllHuiZhi() {
		ArrayList<BodyBeanHuiZhi> bodys = new ArrayList<BodyBeanHuiZhi>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			Cursor cursor = db
					.rawQuery(
							"select * from tablehuizhibaogao where smsstyle in (0,1) order by smstime desc",
							null);
			while (cursor.moveToNext()) {
				BodyBeanHuiZhi bb = new BodyBeanHuiZhi();
				String number = cursor.getString(cursor
						.getColumnIndex("smsnumber"));
				String content = cursor.getString(cursor
						.getColumnIndex("smscontent"));
				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
				String amonth = cursor.getString(cursor
						.getColumnIndex("yuefen"));
				String tosomebody = cursor.getString(cursor
						.getColumnIndex("tosomebody"));
				bb.mstyle = smsstyle;
				bb.content = content;
				bb.cdate = date;
				bb.address = number;
				bb.amonth = amonth;
				bb.tosomebody = tosomebody;
				bodys.add(bb);
			}
			cursor.close();
			db.close();
		}
		return bodys;
	}

	public BodyBeanHuiZhi queryMostNewHuiZhi() {
		// ArrayList<BodyBean> bodys = new ArrayList<BodyBean>();
		BodyBeanHuiZhi bb = null;
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			Cursor cursor = db.rawQuery(
					"select * from tablehuizhibaogao order by smstime desc",
					null);
			if (cursor.moveToNext()) {

				bb = new BodyBeanHuiZhi();
				String number = cursor.getString(cursor
						.getColumnIndex("smsnumber"));
				String content = cursor.getString(cursor
						.getColumnIndex("smscontent"));
				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
				String amonth = cursor.getString(cursor
						.getColumnIndex("yuefen"));
				String tosomebody = cursor.getString(cursor
						.getColumnIndex("tosomebody"));
				bb.mstyle = smsstyle;
				bb.content = content;
				bb.cdate = date;
				bb.address = number;
				bb.amonth = amonth;
				bb.tosomebody = tosomebody;
				// bodys.add(bb);

			}
			cursor.close();
			db.close();
		}
		return bb;
	}

	public ArrayList<BodyBeanHuiZhi> queryAllHuiZhiArrive() {
		ArrayList<BodyBeanHuiZhi> bodys = new ArrayList<BodyBeanHuiZhi>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			// String sql = "select * from tablehuizhibaogao where smsnumber ='"
			// +telnumber + "' and smsstyle = 0 order by smstime desc";
			Cursor cursor = db
					.rawQuery(
							"select * from tablehuizhibaogao where smsstyle = 0 order by smstime desc",
							null);
			while (cursor.moveToNext()) {
				BodyBeanHuiZhi bb = new BodyBeanHuiZhi();
				String number = cursor.getString(cursor
						.getColumnIndex("smsnumber"));
				String content = cursor.getString(cursor
						.getColumnIndex("smscontent"));
				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
				String amonth = cursor.getString(cursor
						.getColumnIndex("yuefen"));
				String tosomebody = cursor.getString(cursor
						.getColumnIndex("tosomebody"));
				bb.mstyle = smsstyle;
				bb.content = content;
				bb.cdate = date;
				bb.address = number;
				bb.amonth = amonth;
				bb.tosomebody = tosomebody;
				bodys.add(bb);

			}
			cursor.close();
			db.close();
		}
		return bodys;
	}

	public ArrayList<BodyBeanHuiZhi> queryAllHuiZhiSend(String telnumber) {
		ArrayList<BodyBeanHuiZhi> bodys = new ArrayList<BodyBeanHuiZhi>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			Cursor cursor = db.rawQuery(
					"select * from tablehuizhibaogao where smsstyle = 3 and tosomebody = '"
							+ telnumber + "' order by smstime desc", null);
			while (cursor.moveToNext()) {
				BodyBeanHuiZhi bb = new BodyBeanHuiZhi();
				String number = cursor.getString(cursor
						.getColumnIndex("smsnumber"));
				String content = cursor.getString(cursor
						.getColumnIndex("smscontent"));
				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
				String amonth = cursor.getString(cursor
						.getColumnIndex("yuefen"));
				String tosomebody = cursor.getString(cursor
						.getColumnIndex("tosomebody"));
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				bb.mstyle = smsstyle;
				bb.content = content;
				bb.cdate = date;
				bb.address = number;
				bb.amonth = amonth;
				bb.tosomebody = tosomebody;
				bb.bodyID = id;
				bodys.add(bb);
			}
			cursor.close();
			db.close();
		}
		return bodys;
	}

	public ArrayList<BodyBeanHuiZhi> queryAllHuiZhiSend2(String telnumber) {
		ArrayList<BodyBeanHuiZhi> bodys = new ArrayList<BodyBeanHuiZhi>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			Cursor cursor = db.rawQuery(
					"select * from tablehuizhibaogao where smsstyle = 3 and smsnumber = '"
							+ telnumber + "' order by smstime desc", null);
			while (cursor.moveToNext()) {
				BodyBeanHuiZhi bb = new BodyBeanHuiZhi();
				String number = cursor.getString(cursor
						.getColumnIndex("smsnumber"));
				String content = cursor.getString(cursor
						.getColumnIndex("smscontent"));
				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
				String amonth = cursor.getString(cursor
						.getColumnIndex("yuefen"));
				String tosomebody = cursor.getString(cursor
						.getColumnIndex("tosomebody"));
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				bb.mstyle = smsstyle;
				bb.content = content;
				bb.cdate = date;
				bb.address = number;
				bb.amonth = amonth;
				bb.tosomebody = tosomebody;
				bb.bodyID = id;
				bodys.add(bb);
			}
			cursor.close();
			db.close();
		}
		return bodys;
	}

	public ArrayList<BodyBeanHuiZhi> queryAllHuiZhiAccordingto_TelnumberAndMonth(
			String telnumber, String month,long time) {
		String times[]=getOneYearTime(time);
    	
		ArrayList<BodyBeanHuiZhi> bodys = new ArrayList<BodyBeanHuiZhi>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			String sql = "select * from tablehuizhibaogao where tosomebody ='"
					+ telnumber + "' and yuefen = '" + month
					+ "'  and smstime > "+times[0]+ " and smstime < "+times[1] +" order by smstime desc";
			Cursor cursor = db.rawQuery(sql, null);
			while (cursor.moveToNext()) {

				BodyBeanHuiZhi bb = new BodyBeanHuiZhi();
				String number = cursor.getString(cursor
						.getColumnIndex("smsnumber"));
				String content = cursor.getString(cursor
						.getColumnIndex("smscontent"));
				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
				String amonth = cursor.getString(cursor
						.getColumnIndex("yuefen"));
				String tosomebody = cursor.getString(cursor
						.getColumnIndex("tosomebody"));
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				bb.mstyle = smsstyle;
				bb.content = content;
				bb.cdate = date;
				bb.address = number;
				bb.amonth = amonth;
				bb.tosomebody = tosomebody;
				bb.bodyID = id;
				if (smsstyle != 3) {
					bodys.add(bb);
				}

			}
			cursor.close();
			db.close();
		}
		return bodys;
	}

	public ArrayList<BodyBeanHuiZhi> queryAllHuiZhiArrive_accordingtoNumber(
			String telnumber, String month,long time) {
		
		
		String times[]=getOneYearTime(time);
		
		
		ArrayList<BodyBeanHuiZhi> bodys = new ArrayList<BodyBeanHuiZhi>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			String sql = "select * from tablehuizhibaogao where tosomebody ='"
					+ telnumber + "' and smsstyle = 0 and yuefen = '" + month
					+ "'    and smstime > "+times[0]+ " and smstime < "+times[1] + " order by smstime desc";
			Cursor cursor = db.rawQuery(sql, null);
			while (cursor.moveToNext()) {
				BodyBeanHuiZhi bb = new BodyBeanHuiZhi();
				String number = cursor.getString(cursor
						.getColumnIndex("smsnumber"));
				String content = cursor.getString(cursor
						.getColumnIndex("smscontent"));
				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
				String amonth = cursor.getString(cursor
						.getColumnIndex("yuefen"));
				String tosomebody = cursor.getString(cursor
						.getColumnIndex("tosomebody"));
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				bb.mstyle = smsstyle;
				bb.content = content;
				bb.cdate = date;
				bb.address = number;
				bb.amonth = amonth;
				bb.tosomebody = tosomebody;
				bb.bodyID = id;
				bodys.add(bb);

			}
			cursor.close();
			db.close();
		}
		return bodys;
	}

	public ArrayList<BodyBeanHuiZhi> queryAllHuiZhiNotArrive() {
		ArrayList<BodyBeanHuiZhi> bodys = new ArrayList<BodyBeanHuiZhi>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			Cursor cursor = db
					.rawQuery(
							"select * from tablehuizhibaogao where smsstyle = 1 order by smstime desc",
							null);
			while (cursor.moveToNext()) {

				BodyBeanHuiZhi bb = new BodyBeanHuiZhi();
				String number = cursor.getString(cursor
						.getColumnIndex("smsnumber"));
				String content = cursor.getString(cursor
						.getColumnIndex("smscontent"));
				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
				String amonth = cursor.getString(cursor
						.getColumnIndex("yuefen"));
				String tosomebody = cursor.getString(cursor
						.getColumnIndex("tosomebody"));
				int state = cursor.getInt(cursor.getColumnIndex("state"));
				bb.mstyle = smsstyle;
				bb.content = content;
				bb.cdate = date;
				bb.address = number;
				bb.amonth = amonth;
				bb.tosomebody = tosomebody;
				bb.state = state;
				bodys.add(bb);

			}
			cursor.close();
			db.close();
		}
		return bodys;
	}
    //隔年的话，月份不好用了，需要按时间来排序了，
	public ArrayList<BodyBeanHuiZhi> queryAllHuiZhiNotArrive_accordingtoNumber(
			String telnumber, String month,long time) {
		
		String times[]=getOneYearTime(time);
		
		
		ArrayList<BodyBeanHuiZhi> bodys = new ArrayList<BodyBeanHuiZhi>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			String sql = "select * from tablehuizhibaogao where tosomebody ='"
					+ telnumber + "' and smsstyle = 1 and yuefen = '" + month
					+ "'  and smstime > "+times[0]+ " and smstime < "+ times[1]+" order by smstime desc";
			Cursor cursor = db.rawQuery(sql, null);
			while (cursor.moveToNext()) {
				BodyBeanHuiZhi bb = new BodyBeanHuiZhi();
				String number = cursor.getString(cursor
						.getColumnIndex("smsnumber"));
				String content = cursor.getString(cursor
						.getColumnIndex("smscontent"));
				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
				String amonth = cursor.getString(cursor
						.getColumnIndex("yuefen"));
				String tosomebody = cursor.getString(cursor
						.getColumnIndex("tosomebody"));
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				bb.mstyle = smsstyle;
				bb.content = content;
				bb.cdate = date;
				bb.address = number;
				bb.amonth = amonth;
				bb.tosomebody = tosomebody;
				bb.bodyID = id;
				bodys.add(bb);

			}
			cursor.close();
			db.close();
		}
		return bodys;
	}

	public synchronized ArrayList<BodyBeanHuiZhi> queryAll(String telnumber) {
		ArrayList<BodyBeanHuiZhi> bodys = new ArrayList<BodyBeanHuiZhi>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			String sql = "select * from tablehuizhibaogao where tosomebody ='"+ telnumber + "' order by smstime desc";
			Cursor cursor = db.rawQuery(sql, null);
			if(cursor.moveToFirst()){
				do{
					BodyBeanHuiZhi bb = new BodyBeanHuiZhi();
					String number = cursor.getString(cursor
							.getColumnIndex("smsnumber"));
					String content = cursor.getString(cursor
							.getColumnIndex("smscontent"));
					long date = cursor.getLong(cursor.getColumnIndex("smstime"));
					int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
					String amonth = cursor.getString(cursor
							.getColumnIndex("yuefen"));
					bb.mstyle = smsstyle;
					bb.content = content;
					bb.cdate = date;
					bb.address = number;
					bb.amonth = amonth;
					bb.bodyID=cursor.getInt(cursor.getColumnIndex("_id"));
					bodys.add(bb);
					
				}while (cursor.moveToNext());
			}
			
			cursor.close();
			db.close();
		}
		return bodys;
	}
	
	
	public  synchronized int queryAllSizeOFNum(String telnumber) {
		int size=0;
		ArrayList<BodyBeanHuiZhi> bodys = new ArrayList<BodyBeanHuiZhi>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			String sql = "select * from tablehuizhibaogao where tosomebody ='"+ telnumber + "' order by smstime desc";
			Cursor cursor = db.rawQuery(sql, null);
			if(cursor.moveToFirst()){
				size=cursor.getCount();
			}
			cursor.close();
			db.close();
		}
		return size;
	}
	
	
//	public ArrayList<BodyBeanHuiZhi> queryAllOfPortNotSend(String telnumber) {
//		ArrayList<BodyBeanHuiZhi> bodys = new ArrayList<BodyBeanHuiZhi>();
//		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
//		if (db.isOpen()) {
//			String sql = "select * from tablehuizhibaogao where smsnumber ='"
//					+ telnumber + "'  and smsstyle !=3 order by smstime desc";
//			Cursor cursor = db.rawQuery(sql, null);
//			while (cursor.moveToNext()) {
//
//				BodyBeanHuiZhi bb = new BodyBeanHuiZhi();
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
//				bb.bodyID=cursor.getInt(cursor.getColumnIndex("_id"));
//				bodys.add(bb);
//
//			}
//			cursor.close();
//			db.close();
//		}
//		return bodys;
//	}
	
	public ArrayList<BodyBeanHuiZhi> queryPortListOfTelnum(String telnumber,String port) {
		ArrayList<BodyBeanHuiZhi> bodys = new ArrayList<BodyBeanHuiZhi>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			String sql = "select * from tablehuizhibaogao where smsnumber ='"
					+ port + "' and tosomebody ='"+telnumber+"' and smsstyle = 3 order by smstime desc";
			Cursor cursor = db.rawQuery(sql, null);
			while (cursor.moveToNext()) {

				BodyBeanHuiZhi bb = new BodyBeanHuiZhi();
				String number = cursor.getString(cursor
						.getColumnIndex("smsnumber"));
				String content = cursor.getString(cursor
						.getColumnIndex("smscontent"));
				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
				String amonth = cursor.getString(cursor
						.getColumnIndex("yuefen"));
				bb.mstyle = smsstyle;
				bb.content = content;
				bb.cdate = date;
				bb.address = number;
				bb.amonth = amonth;
				bb.bodyID=cursor.getInt(cursor.getColumnIndex("_id"));
				bodys.add(bb);

			}
			cursor.close();
			db.close();
		}
		return bodys;
	}
	
	
	

	// 更新短信的状态，是否由未读变成已读---根据号码和月份
	public void update(String telnumber, String month, Integer state) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("state", state);
		db.update("tablehuizhibaogao", values, "tosomebody=?and yuefen=?",
				new String[] { telnumber, month });
	}

	// 查询所有的未读短信---根据手机号码和月份
	public int queryAllLouhua_accordingtoNumberAndMonth(String telnumber,
			String month, Integer state,long time) {
		String[]times=getOneYearTime(time);
	
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor c = null;
		try {
			c = db.query("tablehuizhibaogao", null,
					"tosomebody=? and state=? and yuefen=? and smstime > ? and smstime < ?", new String[] {
							telnumber, state.toString(), month,times[0],times[1]}, null, null,
					"smstime");
			if (c != null) {
				return c.getCount();
			}
			return 0;
		}finally{
			if(c != null){
				try {
				c.close();
				}catch(Throwable t){}
			}
		}
	}
     //返回，记录所在的年份 的开始和结束 的毫秒数
	private String[] getOneYearTime(long time) {
		SimpleDateFormat format_yue = new SimpleDateFormat("yyyyMM");
    	String year=format_yue.format(new Date(time)).substring(0, 4);
    	String end=Tools.getMaxMillisByDay(year, "12", "31")+"";
    	String start=Tools.getMillisByDay(year, "1", "1")+"";
    	
//    	SimpleDateFormat format_yue0 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
//    	format_yue0.format(new Date(Long.parseLong(end)));
//    	format_yue0.format(new Date(Long.parseLong(start)));
		return new String[]{start,end};
	}

	// 查询所有的短信的个数--根据号码，月份，送达状态
//	public int findAllLouhua_accordingtoNumAndMonAndSty(String telnumber,
//			String month, Integer style) {
//		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
//		Cursor c = null;
//		try {
//			c = db.query("tablehuizhibaogao", null,
//					"tosomebody=?and state=?and yuefen=?", new String[] {
//							telnumber, style.toString(), month }, null, null,
//					"smstime");
//			if (c != null) {
//				return c.getCount();
//			}
//			return 0;
//		}finally{
//			if(c != null){
//				try {
//				c.close();
//				}catch(Throwable t){}
//			}
//		}
//		return 0;
//
//	}

	// 查询所有的短信--根据号码，月份，送达状态
//	public ArrayList<BodyBeanHuiZhi> queryAllAccordingtoNumMonAndSty(
//			String telnumber, String month, Integer style) {
//		ArrayList<BodyBeanHuiZhi> bodys = new ArrayList<BodyBeanHuiZhi>();
//		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
//		if (db.isOpen()) {
//			Cursor cursor = db.query("tablehuizhibaogao", new String[] { "*" },
//					"tosomebody=?and yefen=?and smsstyle=?", new String[] {
//							telnumber, month, style.toString() }, null, null,
//					null);
//			while (cursor.moveToNext()) {
//				BodyBeanHuiZhi bb = new BodyBeanHuiZhi();
//				String number = cursor.getString(cursor
//						.getColumnIndex("smsnumber"));
//				String content = cursor.getString(cursor
//						.getColumnIndex("smscontent"));
//				long date = cursor.getLong(cursor.getColumnIndex("smstime"));
//				int smsstyle = cursor.getInt(cursor.getColumnIndex("smsstyle"));
//				String amonth = cursor.getString(cursor
//						.getColumnIndex("yuefen"));
//				String tosomebody = cursor.getString(cursor
//						.getColumnIndex("tosomebody"));
//				int id = cursor.getInt(cursor.getColumnIndex("_id"));
//				bb.mstyle = smsstyle;
//				bb.content = content;
//				bb.cdate = date;
//				bb.address = number;
//				bb.amonth = amonth;
//				bb.tosomebody = tosomebody;
//				bb.bodyID = id;
//				if (smsstyle != 3) {
//					bodys.add(bb);
//				}
//			}
//			cursor.close();
//			db.close();
//		}
//		return null;

//	}
	
	
	public boolean hasRec(long time) {
		boolean hasrecord=false;
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor c = null;
		try {
			c = db.query("tablehuizhibaogao", null,
					"smstime= ? ", new String[] {
							""+time }, null, null,
					null);
			if (c.moveToFirst()) {
				hasrecord=true;
			}		
		}finally{
			if(c != null){
				try {
				c.close();
				}catch(Throwable t){}
			}
		}
		return hasrecord;	
		
	}
}
