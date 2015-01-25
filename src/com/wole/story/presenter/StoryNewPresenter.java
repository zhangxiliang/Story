package com.wole.story.presenter;

import java.util.HashMap;
import java.util.List;

import android.util.Log;

import com.wole.story.config.CommonConfig;
import com.wole.story.entity.Story;
import com.wole.story.framework.BaseService;

public class StoryNewPresenter extends BasePresenter{

	public INewStory iNewStory;
	  private static String para = "O04qc4bWkqzigKyYcZ8uCnx9YWR8dBuFNMakl%2bqPBPpzDo%2fLP4hfldlmRvoWLAJEOaM03xQ7ygnR4B1AQqnedzYSXWZHXtvlGntPUTlGLX9FvFXDryennN%2fLffWVKTkb";

	
	public StoryNewPresenter(INewStory iNewStory){
		this.iNewStory=iNewStory;
	}
	
	
	public void reqNewStory(){
		BaseService  task=new BaseService(this);
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("Para", para);
		params.put("TypeID", "");
		task.sync(CommonConfig.NEWESTSTORY, params);
	}
	
	
	public interface INewStory{
		public void addNewStory(List<Story> storys);
	}


	@Override
	public void onPostExecute(String text) {
	    Log.d("StoryNewPresenter", text);
	}
	
}
