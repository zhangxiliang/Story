package com.wole.story.app;


import com.umeng.analytics.MobclickAgent;

import android.app.Application;

public class StoryApplication extends Application{
	private static StoryApplication instance;

	public static StoryApplication getInstance() {
		return instance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance=this;
		MobclickAgent.updateOnlineConfig(this);

	}
}
