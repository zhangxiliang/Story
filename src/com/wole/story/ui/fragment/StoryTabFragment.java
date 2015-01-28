package com.wole.story.ui.fragment;

import java.util.List;

import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.update.UmengUpdateAgent;
import com.viewpagerindicator.TabPageIndicator;
import com.wole.story.entity.Story;
import com.wole.story.entity.StroyCategory;
import com.wole.story.presenter.StoryCategoryPresenter;
import com.wole.story.presenter.StoryNewPresenter;
import com.wole.story.presenter.StoryCategoryPresenter.IStoryCategory;
import com.wole.story.presenter.StoryNewPresenter.INewStory;
import com.wole.story.ui.BaseActivity;
import com.wole.story.ui.R;
import com.wole.story.ui.R.id;
import com.wole.story.ui.R.layout;
import com.wole.story.utils.Logs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StoryTabFragment extends BaseFragment implements INewStory, IStoryCategory {

	private StoryNewPresenter mStoryPresenter;
	private StoryCategoryPresenter mCategoryPresenter;
	private List<StroyCategory> categorys;
	private ViewPager pager;
	private TabPageIndicator indicator;
	FragmentPagerAdapter adapter;
	private static final String[] CONTENT = new String[] { "Recent", "Artists", "Albums", "Songs", "Playlists", "Genres" };

	public static StoryTabFragment newInstance() {
		StoryTabFragment tabFragment = new StoryTabFragment();
		return tabFragment;
	}

	@Override
	public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		mView = inflater.inflate(R.layout.simple_tabs, null);

		pager = (ViewPager) mView.findViewById(R.id.pager);
		indicator = (TabPageIndicator) mView.findViewById(R.id.indicator);
		 adapter = new GoogleMusicAdapter(mActivity.getSupportFragmentManager());
		pager.setAdapter(adapter);
		indicator.setViewPager(pager);
		
		mStoryPresenter = new StoryNewPresenter(this);
		mCategoryPresenter = new StoryCategoryPresenter(this);
		mStoryPresenter.reqNewStory();
		mCategoryPresenter.reqStoryCategroy();
		return mView;
	}

	class GoogleMusicAdapter extends FragmentPagerAdapter {
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			if(categorys==null){
				return null;
			}
			return TestFragment.newInstance(categorys.get(position).getType());
		}

		@Override
		public CharSequence getPageTitle(int position) {
			if(categorys==null){
				return "";
			}
			return categorys.get(position).getType();
		}

		@Override
		public int getCount() {
			if(categorys==null){
				return 0;
			}
			return categorys.size();

		}
	}

	@Override
	public void onNewStory(List<Story> storys) {

	}

	@Override
	public void onStoryCategory(List<StroyCategory> categorys) {
		this.categorys = categorys;
		adapter.notifyDataSetChanged();
		indicator.setViewPager(pager);
		indicator.notifyDataSetChanged();
	}

}
