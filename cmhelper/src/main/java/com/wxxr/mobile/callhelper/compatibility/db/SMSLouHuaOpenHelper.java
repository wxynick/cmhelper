package com.wxxr.mobile.callhelper.compatibility.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SMSLouHuaOpenHelper extends SQLiteOpenHelper {
	public static final String KEY_TABLE_MYSMSLOUHUA = "tablelouhua";
	public static final String KEY_ROWID = "_id";

	public SMSLouHuaOpenHelper(Context context) {
		super(context, "wxxr_comhelper.db", null, 4);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + KEY_TABLE_MYSMSLOUHUA + "(" + KEY_ROWID
				+ " INTEGER PRIMARY KEY," + "smsnumber" + " TEXT NOT NULL,"
				+ "yuefen" + " TEXT NOT NULL," + "smstime" + " LONG,"
				+ "smsstyle" + " INTEGER," + "smscontent" + " TEXT NOT NULL ,"
				+ "state" + " INTEGER)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 升级为版本2,增加字段state，默认为1(未读)。
		db.execSQL("alter table " + KEY_TABLE_MYSMSLOUHUA
				+ " add column state  INTEGER default (1)");
	}
}
