package com.wole.story.presenter;

import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;

import com.wole.story.entity.Story;
import com.wole.story.entity.StoryCategory;
import com.wole.story.framework.BaseService;
import com.wole.story.utils.JsoupUtil;

public class StoryMoreListPersenter extends BasePresenter{

	
	@Override
	public void onSuccess(String text) {
		Document document = JsoupUtil.parse(text);
		if (document != null) {
			//mIStoryListListener.onStoryList(getStoryListByCategory(document));
		}
	}

	
}
