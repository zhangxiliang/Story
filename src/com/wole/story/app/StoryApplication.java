package com.wole.story.app;


import com.avos.avoscloud.AVOSCloud;
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
		AVOSCloud.initialize(this, "pmmb8rh75f5wl3bk7nqi9gmhz1v2c6reklawzjfxwpd9em4w", "7tocbx9qq8yajun8avz8xkpl7clce05dlbvy0iy6xt8v4gjw");
		MobclickAgent.updateOnlineConfig(this);

	}
}
