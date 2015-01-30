package com.wole.story.presenter;

import java.util.Iterator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;

import com.wole.story.entity.Story;
import com.wole.story.framework.BaseService;
import com.wole.story.utils.JsoupUtil;

public class StoryPresenter extends BasePresenter {

	private IStoryListener mIStoryListener;

	public StoryPresenter(IStoryListener mIStoryListener) {
		this.mIStoryListener = mIStoryListener;
	}

	public void reqStory(String url) {
		BaseService task = new BaseService(this);
		task.sync(url, null);
	}
	
	

	@Override
	public void onSuccess(String text) {
		Document document = JsoupUtil.parse(text);
		if (document != null) {
			mIStoryListener.onStory(parseDocument(document));
		}
	}

	public Story parseDocument(Document document) {
		Elements elementsByClass = document.getElementsByClass("main_txt");
		Story story = new Story();
		StringBuffer sb = new StringBuffer();
		Elements elementsByTag = elementsByClass.first().getElementsByTag("div");
		String text = elementsByClass.get(0).getElementsByTag("h1").get(0).text();
		String text2 = elementsByClass.get(0).getElementsByTag("h2").get(0).text();
		String trim = text2.split(":")[1].trim();
		String trim2 = text2.split(":")[2].trim();
		int n = 0;
		int n2;
		if (elementsByTag.get(1).hasAttr("id")) {
			n2 = 1;
		} else {
			n2 = 0;
		}
		for (int i = 0; i < elementsByTag.size(); ++i) {
			if (elementsByTag.get(i).attr("id").equals("ContentPlaceHolder1_GSHStoryDetail_PopAuthor_divAuthorization")) {
				n = i;
			}
		}
		Iterator<Element> iterator = elementsByTag.subList(n2 + 1, n).iterator();
		while (iterator.hasNext()) {
			sb.append(iterator.next().toString());
		}
		story.setContent(sb.toString());
		story.setDate(trim2);
		story.setAuthor(trim);
		story.setTitle(text);
		return story;
	}

	public interface IStoryListener {
		public void onStory(Story story);
	}
}
