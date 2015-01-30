package com.wole.story.ui.fragment;

import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wole.story.adapter.StoryAdapter;
import com.wole.story.entity.Story;
import com.wole.story.entity.StoryCategory;
import com.wole.story.presenter.StoryListPresenter;
import com.wole.story.presenter.StoryListPresenter.IStoryListListener;
import com.wole.story.ui.R;
import com.wole.story.utils.Logs;
import com.wole.story.utils.ViewUtils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public final class TestFragment extends BaseFragment implements IStoryListListener, OnScrollListener {
	private static final String KEY_CONTENT = "TestFragment:Content";

	private PullToRefreshListView mListView;
	private StoryListPresenter mStoryListPresenter;
	private StoryCategory mStoryCategory;
	private StoryAdapter mAdapter;
	private int visibleItemCount;
	private int visibleLastIndex = 0;
	private boolean isloading;
	private int total;
	private View mLoadMoreView;

	public static TestFragment newInstance(StoryCategory storyCategory) {
		TestFragment fragment = new TestFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable("category", storyCategory);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_story_list, null);
		mLoadMoreView = ViewUtils.getView(mActivity, R.layout.layout_loading_more);
		mListView = (PullToRefreshListView) mView.findViewById(R.id.story_listview);
		mListView.setOnScrollListener(this);
		mAdapter = new StoryAdapter(mActivity);
		mListView.setAdapter(mAdapter);

		mStoryListPresenter = new StoryListPresenter(this);
		this.mStoryCategory = (StoryCategory) getArguments().getSerializable("category");
		mStoryListPresenter.reqStoryList(mStoryCategory);
		return mView;

	}

	@Override
	public void onStoryList(List<Story> storys) {
		mAdapter.appendToList(storys);
	}

	@Override
	public void onMoreStoryList(List<Story> storys) {
		
		
		mAdapter.appendToList(storys);
		mListView.getRefreshableView().removeFooterView(mLoadMoreView);
		isloading=false;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	
		int allCount = mAdapter.getCount();
		Logs.debug("mAdapter.getCount()="+allCount+",mStoryCategory.getTotal()="+mStoryCategory.getTotal()+",scrollState="+scrollState);
		if (allCount ==mStoryCategory.getTotal()) {
			mListView.getRefreshableView().removeFooterView(mLoadMoreView);
		} else if (!isloading && scrollState == 0 && visibleLastIndex == allCount) {
			isloading = true;
			mListView.getRefreshableView().addFooterView(mLoadMoreView);
			mStoryListPresenter.reqMoreStoryList(mStoryCategory, allCount / 20);
		}

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		this.visibleItemCount = visibleItemCount;
		this.visibleLastIndex = (-2 + (firstVisibleItem + visibleItemCount));
		Logs.debug("visibleItemCount="+visibleItemCount+",visibleLastIndex="+visibleLastIndex);
	}

}
