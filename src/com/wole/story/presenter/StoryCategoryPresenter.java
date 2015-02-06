package com.wole.story.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.wole.story.config.CommonConfig;
import com.wole.story.entity.StoryCategory;
import com.wole.story.framework.BaseService;
import com.wole.story.utils.JsoupUtil;
import com.wole.story.utils.Logs;

public class StoryCategoryPresenter extends BasePresenter {

	private IStoryCategory iStoryCategory;

	public StoryCategoryPresenter(IStoryCategory iStoryCategory) {
		this.iStoryCategory = iStoryCategory;
	}

	public void reqStoryCategroy() {
		BaseService task = new BaseService(this);
		task.sync(CommonConfig.CATEGROY, null);
	}

	public interface IStoryCategory {
		public void onStoryCategory(List<StoryCategory> storys);
	}

	@Override
	public void onSuccess(String text) {
		Logs.debug(text);

		Document document = JsoupUtil.parse(text);
		if (document != null) {
			iStoryCategory.onStoryCategory(getCategoryList(document));
		}

	}

	private List<StoryCategory> getCategoryList(Document document) {

		List<StoryCategory> categorys = new ArrayList<StoryCategory>();
		for (Element element : document.getElementById("shoppintcar_step").getElementsByTag("dd").get(1).getElementsByTag("a")) {
			StoryCategory stroyCategory = new StoryCategory();
			stroyCategory.setType(element.text());
			stroyCategory.setUrl(String.valueOf(CommonConfig.BASE_URL) + element.attr("href"));
			categorys.add(stroyCategory);
		}
		return categorys;
	}

}
