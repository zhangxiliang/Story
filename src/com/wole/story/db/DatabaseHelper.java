package com.wole.story.db;

import java.sql.SQLException;

import android.content.ClipData.Item;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableUtils;
import com.wole.story.entity.Story;
import com.wole.story.utils.Logs;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper{

	private static final String DATABASE_NAME = "woxiu.db";  
    public DatabaseConnection conn;
    
    
	public DatabaseHelper(Context context,int dataBaseVesion) {
		super(context, DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}
 
	
	/**创建表**/
	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
		
		   try {
			    Logs.debug("onCreate table");
	            conn = connectionSource.getSpecialConnection();
	            TableUtils.createTableIfNotExists(connectionSource, Story.class);
	    
	            Logs.debug("Create table success");
	        } catch (SQLException e) {
	            Logs.debug("Can't create database:"+e.getMessage());
	        }
	}

	
	
	/***更新表*/
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connect, int oldVer, int newVer) {
		
		 try {
	           Logs.debug("onUpgrade");
	           TableUtils.dropTable(connect, Story.class, true);  
	            onCreate(db, connect);
	        } catch (Exception e) {
	           Logs.debug("Can't drop databases");
	        }
	}
	

	
	

}
