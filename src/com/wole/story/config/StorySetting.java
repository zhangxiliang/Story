package com.wole.story.config;

import com.wole.story.app.StoryApplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

/**
 * Created with IntelliJ IDEA.
 * User: Jace
 * Date: 13-10-29
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
public class StorySetting {

    private static StorySetting shareUtil ;

    public static final String SHARE_KEY = "ishow" ;
 
    private static final String SETTING_USERNAME="username";
    private static final String SETTING_FRONT="front";
    private static final String SETTING_FRONT_SIZE="front_size";

    private SharedPreferences settings ;

	private StorySetting(Context context) {
		//解决配置文件多进程共享问题，如在Service上的问题
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
			settings = context.getSharedPreferences(SHARE_KEY, Context.MODE_MULTI_PROCESS);
		}else{
			settings = context.getSharedPreferences(SHARE_KEY, Context.MODE_APPEND);
		}
	
	}

    public static StorySetting getInstance() {
    	//使用单列会导致跨进程访问，数据不同步的现象
       //if (shareUtil == null) {
            shareUtil = new StorySetting(StoryApplication.getInstance()) ;
       //}

        return shareUtil ;
    }

	
	public void saveUserName(String userName) {
		setString(SETTING_USERNAME, userName);
	}

	public String getUserName() {
		return getString(SETTING_USERNAME, null);
	}
	
	public void saveFront(String userName) {
		setString(SETTING_FRONT, userName);
	}

	public String getFront() {
		return getString(SETTING_FRONT, null);
	}
	
	
	public void saveFrontSize(int frontsize) {
		setInt(SETTING_FRONT_SIZE, frontsize);
	}

	public int getFrontSize() {
		return getInt(SETTING_FRONT_SIZE, 9);
	}
	public void setLong(String paramString, long paramValue) {
		
		settings.edit().putLong(paramString, paramValue).commit();
	}

	public long getLong(String paramString, long defaultValue) {
		return settings.getLong(paramString, defaultValue);
	}
	public void setString(String paramString, String paramValue) {
		settings.edit().putString(paramString, paramValue).commit();
	}


	public String getString(String paramString, String defaultValue) {
		return settings.getString(paramString, defaultValue);
	}
    
	public void setBoolean(String paramString, boolean paramBoolean) {
		settings.edit().putBoolean(paramString, paramBoolean).commit();
	}
    
	
	public boolean getBoolean(String paramString, boolean defaultValue) {
		return settings.getBoolean(paramString, defaultValue);
	}
		
	public void setInt(String paramString, int paramValue) {
		
		settings.edit().putInt(paramString, paramValue).commit();
	}

	public int getInt(String paramString, int defaultValue) {
		return settings.getInt(paramString, defaultValue);
	}
}
