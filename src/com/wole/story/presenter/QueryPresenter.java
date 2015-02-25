package com.wole.story.presenter;

import java.util.List;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.FindCallback;
import com.wole.story.entity.SampleItem;

public abstract class QueryPresenter extends FindCallback<AVObject>{

	@Override
	public abstract void done(List<AVObject> arg0, AVException arg1) ;
	


	
	
}
