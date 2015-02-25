package com.wole.story.presenter;

import java.util.List;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.wole.story.entity.Story;

public class LovePresenter extends SavePresenter{

	private LoveListener loveListener;
	public LovePresenter(LoveListener loveListener){
		this.loveListener=loveListener;
	}
	
	public void saveLove(Story story){
		AVObject storyLove = new AVObject("StoryLove");
		storyLove.put("Author", story.getAuthor());
		storyLove.put("content", story.getContent());
		storyLove.put("date", story.getDate());
		storyLove.put("url", story.getUrl());
		storyLove.put("title", story.getTitle());
		storyLove.put("userid", AVUser.getCurrentUser().getUuid());
		storyLove.saveInBackground(this);
	}
	
	public void queryLoveList(){
		AVQuery<AVObject> query = new AVQuery<AVObject>("StoryLove");
		query.whereEqualTo("userid", AVUser.getCurrentUser().getUuid());
		query.findInBackground(new FindCallback<AVObject>() {
		    public void done(List<AVObject> avObjects, AVException e) {
		        if (e != null) {
		        	//lovSaveListener.onLoveList(storys);
		        }
		    }
		});
	}
	
	@Override
	public void done(AVException arg0) {
		if(arg0==null){
			loveListener.onLoveSaveOk();
		}
		
	}
	
	
	
	public interface LoveListener{
		public void onLoveSaveOk();
		public void onLoveList(List<Story> storys);
	}

}
