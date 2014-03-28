package com.wxxr.mobile.callhelper.compatibility.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RemindSettingOpenHelper extends SQLiteOpenHelper
{
	public static final String KEY_TABLE_REMINDSETTING = "table_remindsetting";
	public static final String KEY_ROWID = "_id";

	public RemindSettingOpenHelper(Context context)
	{
		super(context, "wxxr_remind_setting.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE " + KEY_TABLE_REMINDSETTING + "(" + KEY_ROWID + " INTEGER PRIMARY KEY," + "huizhisetting"
				+ " INTEGER,"+ "louhuasetting"+ " INTEGER)";
		db.execSQL(sql);

		String h = "insert into table_remindsetting (huizhisetting,louhuasetting) values";
		sql = h + "('2','2')";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		try
		{
			db.execSQL("DROP TABLE IF EXISTS " + KEY_TABLE_REMINDSETTING);
			onCreate(db);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

}
