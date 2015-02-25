package com.wole.story.ui.fragment;

import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wole.story.adapter.StoryAdapter;
import com.wole.story.db.StoryDao;
import com.wole.story.entity.Story;
import com.wole.story.entity.StoryCategory;
import com.wole.story.presenter.LovePresenter;
import com.wole.story.presenter.LovePresenter.LoveListener;
import com.wole.story.presenter.StoryListPresenter;
import com.wole.story.ui.LoginActivity;
import com.wole.story.ui.R;
import com.wole.story.ui.StoryActivity;
import com.wole.story.utils.Logs;
import com.wole.story.utils.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class LoveFragment extends BaseFragment implements OnScrollListener,LoveListener{
	private PullToRefreshListView mListView;
    private LovePresenter mLovePresenter;
	private StoryAdapter mAdapter;
	private int visibleItemCount;
	private int visibleLastIndex = 0;
	private boolean isloading;
	private int total;
	private View mLoadMoreView;
	private StoryDao mStoryDao;
	@Override
	public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_love, null);
		Logs.info("StoryTabFragment initViews");
        mLovePresenter=new LovePresenter(this);
		mLoadMoreView = ViewUtils.getView(mActivity, R.layout.layout_loading_more);
		mListView = (PullToRefreshListView) mView.findViewById(R.id.story_listview);
		mListView.setOnScrollListener(this);
		mAdapter = new StoryAdapter(mActivity);
		mListView.setAdapter(mAdapter);
		mStoryDao=new StoryDao();
		
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(!mStoryApplication.isLoginUser()){
					Intent intent=new Intent(mActivity,LoginActivity.class);
					startActivity(intent);
					return ;
				}
				Story story=mAdapter.getItem(position-1);
				story.setReaded(true);
				mAdapter.notifyDataSetChanged();
				mStoryDao.add(story);
				Intent intent=new Intent(mActivity,StoryActivity.class);
				intent.putExtra("story", story);
				startActivity(intent);
			}
			
		});
		return mView;

	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	
	

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		this.visibleItemCount = visibleItemCount;
		this.visibleLastIndex = (-2 + (firstVisibleItem + visibleItemCount));
		Logs.debug("visibleItemCount="+visibleItemCount+",visibleLastIndex="+visibleLastIndex);
	}
	@Override
	public void onLoveSaveOk() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onLoveList(List<Story> storys) {
		// TODO Auto-generated method stub
		
	}


}
