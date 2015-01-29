package com.wole.story.presenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;

import com.wole.story.config.CommonConfig;
import com.wole.story.entity.Story;
import com.wole.story.entity.StoryCategory;
import com.wole.story.framework.BaseService;
import com.wole.story.utils.JsoupUtil;

public class StoryListPresenter extends BasePresenter {

	private IStoryListListener mIStoryListListener;
	private int visibleLastIndex = 0;
	private boolean isloading = false;
	private String cbopage = "ctl00$ContentPlaceHolder1$GSHPageController_PopAuthor$cboPage";
	private String pageIndex = "ctl00$ContentPlaceHolder1$GSHPageController_PopAuthor$hidPageIndex";
	private int pageTag = 0;
	private String tabTag = "";
	private int total = 0;
	public StoryListPresenter(Context context){
		mIStoryListListener=(IStoryListListener)context;
	}
	
	public void reqStoryList(StoryCategory storyCategory) {
		BaseService task = new BaseService(this);
		task.sync(storyCategory.getUrl(), null);
	}

	public void reqMoreStoryList(){
		
	}
	
	@Override
	public void onSuccess(String text) {
		Document document = JsoupUtil.parse(text);
		if (document != null) {
			mIStoryListListener.onStoryList(getStoryListByCategory(document));
		}
	}

	public List<Story> getStoryListByCategory(Document document) {
		final ArrayList<Story> list = new ArrayList<Story>();
		final Elements elementsByTag = document.getElementsByClass("table2").first().getElementsByTag("tr");
		elementsByTag.remove(0);
		final Iterator<Element> iterator = elementsByTag.iterator();
		while (iterator.hasNext()) {
			final Elements elementsByTag2 = iterator.next().getElementsByTag("td");
			final Story story = new Story();
			story.setTitle(elementsByTag2.get(1).text());
			story.setAuthor(elementsByTag2.get(2).text());
			story.setViewCount(Integer.parseInt(elementsByTag2.get(3).text()));
			story.setDate(elementsByTag2.get(6).text());
			story.setUrl(String.valueOf(CommonConfig.BASE_URL) + elementsByTag2.get(1).child(0).attr("href"));
			list.add(story);
		}
		return list;
	}

	public interface IStoryListListener {
		public void onStoryList(List<Story> storys);
		public void onMoreStoryList(List<Story> storys);
	}

}
