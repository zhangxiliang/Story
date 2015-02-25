package com.wole.story.app;


import java.util.List;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.umeng.analytics.MobclickAgent;
import com.wole.story.config.StorySetting;
import com.wole.story.db.DatabaseManager;
import com.wole.story.db.StoryDao;
import com.wole.story.entity.Story;
import com.wole.story.entity.StoryUser;

import android.app.Application;

public class StoryApplication extends Application{
	private static StoryApplication instance;
	private StoryDao mStoryDao;
    private List<Story> mReadList;
	public static StoryApplication getInstance() {
		return instance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance=this;
    	DatabaseManager.init(this, 1);
    	mStoryDao=new StoryDao();

		AVOSCloud.initialize(this, "pmmb8rh75f5wl3bk7nqi9gmhz1v2c6reklawzjfxwpd9em4w", "7tocbx9qq8yajun8avz8xkpl7clce05dlbvy0iy6xt8v4gjw");
		MobclickAgent.updateOnlineConfig(this);
		mReadList=mStoryDao.queryAll();

	}
	
	public AVUser getLoginUser(){
		  return AVUser.getCurrentUser();
	}
	
	public boolean isLoginUser(){
		AVUser user=AVUser.getCurrentUser();
		if(user!=null){
			return true;
		}
		return false;
	}

	public List<Story> getmReadList() {
		return mReadList;
	}

	
}
