package com.wole.story.ui.fragment;

import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wole.story.adapter.StoryAdapter;
import com.wole.story.entity.Story;
import com.wole.story.entity.StoryCategory;
import com.wole.story.presenter.StoryListPresenter;
import com.wole.story.presenter.StoryListPresenter.IStoryListListener;
import com.wole.story.ui.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public final class TestFragment extends BaseFragment implements IStoryListListener{
	private static final String KEY_CONTENT = "TestFragment:Content";

	private PullToRefreshListView mListView;
	private StoryListPresenter mStoryListPresenter;
	private StoryCategory mStoryCategory;
	private StoryAdapter mAdapter;

	public static TestFragment newInstance(StoryCategory storyCategory) {
		TestFragment fragment = new TestFragment();
		Bundle bundle=new Bundle();
		bundle.putSerializable("category", storyCategory);
		return fragment;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_story_list, null);
		mListView = (PullToRefreshListView) mView.findViewById(R.id.story_listview);
		mAdapter=new StoryAdapter(mActivity);
		mListView.setAdapter(mAdapter);
		
		mStoryListPresenter=new StoryListPresenter(mActivity);
		this.mStoryCategory=(StoryCategory)getArguments().getSerializable("category");
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
	}
}
