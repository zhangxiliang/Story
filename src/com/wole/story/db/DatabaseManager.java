package com.wole.story.db;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

import android.content.Context;

public class DatabaseManager {
	private static Context context;
	private static DatabaseHelper mDatabaseHelper;
    private  static int dataBaseVersion;
    
    //后台去控制数据库版本号
	public static void init(Context context,int dataBaseVersion) {
		DatabaseManager.context = context;
		DatabaseManager.dataBaseVersion=dataBaseVersion;
		mDatabaseHelper = new DatabaseHelper(context,dataBaseVersion);
	}

	public static DatabaseHelper getHelper() {
		if (mDatabaseHelper == null) {
			mDatabaseHelper = new DatabaseHelper(context,1);
		}
		return mDatabaseHelper;
	}
	

}
