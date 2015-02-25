package com.wole.story.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;

import com.wole.story.app.StoryApplication;
import com.wole.story.config.CommonConfig;
import com.wole.story.entity.Story;
import com.wole.story.entity.StoryCategory;
import com.wole.story.framework.BaseService;
import com.wole.story.framework.TaskCallBack;
import com.wole.story.presenter.StoryPresenter.IStoryListener;
import com.wole.story.utils.JsoupUtil;
@SuppressWarnings("unchecked")
public class StoryListPresenter extends BasePresenter implements IStoryListener{

	private IStoryListListener mIStoryListListener;
	private StoryCategory mStoryCategory;
	private StoryPresenter mStoryPresenter;
	private int visibleLastIndex = 0;
	private boolean isloading = false;
	private String cbopage = "ctl00$ContentPlaceHolder1$GSHPageController_PopAuthor$cboPage";
	private String pageIndex = "ctl00$ContentPlaceHolder1$GSHPageController_PopAuthor$hidPageIndex";
	private int pageTag = 0;
	private String tabTag = "";
	private int total = 0;

	public StoryListPresenter(IStoryListListener mIStoryListListener) {
		this.mIStoryListListener = mIStoryListListener;
	}

	public void reqStoryList(StoryCategory storyCategory) {
		BaseService task = new BaseService(this);
		this.mStoryCategory = storyCategory;
		task.sync(storyCategory.getUrl(), null);
	}

	
	public void reqMoreStoryList(StoryCategory storyCategory, int pageNum) {
		HashMap params = storyCategory.getParamMap();
		if (params.containsKey("hidChoiced"))
			params.put("hidChoiced", String.valueOf(pageNum));
		if (params.containsKey("ctl00$GSHLoginBar1$cmdLogin")) {
			params.remove("ctl00$GSHLoginBar1$cmdLogin");
		}
		if (params.containsKey("ctl00$GSHLoginBar1$BestSenseInput_LoginBar$cmdSearch"))
			params.remove("ctl00$GSHLoginBar1$BestSenseInput_LoginBar$cmdSearch");
		if (params.containsKey("ctl00$GSHLoginBar1$cmdLogin2"))
			params.remove("ctl00$GSHLoginBar1$cmdLogin2");
		params.put("ctl00$GSHLoginBar1$txtPassword", "");
		params.put("txtSearchAuthor", "");
		params.put("__EVENTTARGET", "ctl00$ContentPlaceHolder1$GSHPageController_PopAuthor$cboPage");
		params.put("ctl00$GSHLoginBar1$BestSenseInput_LoginBar$cboSearchType", "人气与原创");
		if (params.containsKey(pageIndex))
			params.put(pageIndex, String.valueOf(pageNum));
		
		BaseService task = new BaseService(taskCallBack);
		task.sync(storyCategory.getUrl(), params);
	}


	
	@Override
	public void onSuccess(String text) {
		Document document = JsoupUtil.parse(text);
		if (document != null) {
			mIStoryListListener.onStoryList(getStoryListByCategory(document));
		}
	}

	public HashMap getPageParamsList(Document document) {
		Elements select = document.getElementsByTag("form").select("[method=post]").first().select("input[name]");
		HashMap<String, String> hashMap = new HashMap<String, String>();
		for (Element element : select) {
			hashMap.put(element.attr("name"), element.attr("value"));
		}
		return hashMap;
	}

	public int getTotalCount(Document document) {
		return Integer.parseInt(document.getElementById("ContentPlaceHolder1_GSHPageController_PopAuthor_labRecordCount").text());
	}

	//获取当前类别的故事文章
	public List<Story> getStoryListByCategory(Document document) {
		mStoryCategory.setParamMap(getPageParamsList(document));
		mStoryCategory.setTotal(getTotalCount(document));

		final ArrayList<Story> list = new ArrayList<Story>();
		Elements elementsByTag = document.getElementsByClass("table2").first().getElementsByTag("tr");
		elementsByTag.remove(0);
		Iterator<Element> iterator = elementsByTag.iterator();
		while (iterator.hasNext()) {
			
			Elements elementsByTag2 = iterator.next().getElementsByTag("td");
			Story story = new Story();
			story.setTitle(elementsByTag2.get(1).text());
			story.setAuthor(elementsByTag2.get(2).text());
			story.setViewCount(Integer.parseInt(elementsByTag2.get(3).text()));
			story.setDate(elementsByTag2.get(6).text());
			story.setUrl(String.valueOf(CommonConfig.BASE_URL) + elementsByTag2.get(1).child(0).attr("href"));
			
			//StoryPresenter mStoryPresenter=new StoryPresenter(story);
			//mStoryPresenter.reqStory(story.getUrl());
			list.add(story);
		}
		
		return addParam(list);
	}

	
	private List<Story> addParam(List<Story> storys){
		for(Story story :storys){
			if(inReadList(story.getUrl())){
				story.setReaded(true);
			}else{
				story.setReaded(false);
			}
		}
		
		return storys;
	}
	
	private boolean inReadList(String url){
		List<Story> readList=StoryApplication.getInstance().getmReadList();
		for(Story story :readList){
			if(story.getUrl().equals(url)){
				return true;
			}
		}
		return false;
	}
	private TaskCallBack taskCallBack=new TaskCallBack() {
		
		@Override
		public void onSuccess(String text) {
			Document document = JsoupUtil.parse(text);
			if (document != null) {
				mIStoryListListener.onMoreStoryList(getStoryListByCategory(document));
			}
			
		}
	
		
		@Override
		public void onError(Exception result) {
			
		}
	};
	
	public interface IStoryListListener {
		public void onStoryList(List<Story> storys);

		public void onMoreStoryList(List<Story> storys);
	}

	@Override
	public void onStory(Story story) {
		
		
	}

}
