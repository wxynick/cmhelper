package com.wxxr.mobile.callhelper.compatibility.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wxxr.mobile.callhelper.compatibility.db.RemindSettingOpenHelper;

public class RemindSettingDao
{
	RemindSettingOpenHelper dbOpenHelper;

	private static RemindSettingDao csi = null;

	private RemindSettingDao(Context context)
	{

		dbOpenHelper = new RemindSettingOpenHelper(context);
	}

	public static RemindSettingDao getInstance(Context context)
	{
		if (csi == null)
		{
			synchronized (RemindSettingDao.class)
			{
				if (csi == null)
				{
					csi = new RemindSettingDao(context);
				}
			}
		}
		return csi;
	}


	public boolean updateHuiZhiStatus( int status)
	{
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update table_remindsetting set huizhisetting = ? where _id = 1 ",
		new Object[]
		{ status });

		db.close();
		return true;
	}

	public boolean updateLouHuaStatus( int status)
	{
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update table_remindsetting set louhuasetting = ? where _id = 1 ",
		new Object[]
		{ status });

		db.close();
		return true;
	}
	
	
	public int queryHuiZhiSetting()
	{
		int inv = 0;
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen())
		{
			Cursor cursor = db.rawQuery("select * from table_remindsetting where _id = 1",
					null);
			if (cursor != null && cursor.getCount() > 0)
			{
				if (cursor.moveToNext())
				{
					int mmm = cursor.getInt(cursor.getColumnIndex("huizhisetting"));
					inv = mmm;
					cursor.close();
					db.close();
				}
			}

		}
		return inv;
	}
	
	
	public int queryLouHuaSetting()
	{
		int inv = 0;
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		if (db.isOpen())
		{
			Cursor cursor = db.rawQuery("select * from table_remindsetting where _id = 1",
					null);
			if (cursor != null && cursor.getCount() > 0)
			{
				if (cursor.moveToNext())
				{
					int mmm = cursor.getInt(cursor.getColumnIndex("louhuasetting"));
					inv = mmm;
					cursor.close();
					db.close();
				}
			}

		}
		return inv;
	}
	
	

	
	

}
