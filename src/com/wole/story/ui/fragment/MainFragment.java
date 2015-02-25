package com.wole.story.ui.fragment;

import java.util.ArrayList;
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
import com.wole.story.entity.StoryCategory;
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
import android.support.v4.app.FragmentStatePagerAdapter;
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

public class MainFragment extends BaseFragment implements INewStory, IStoryCategory {

	private StoryNewPresenter mStoryPresenter;
	private StoryCategoryPresenter mCategoryPresenter;
	private  List<StoryCategory> categorys;
	private List<Fragment> mTabFragmentList;
	private ViewPager pager;
	private TabPageIndicator indicator;
	
	FragmentPagerAdapter adapter;
	
	private static final String[] CONTENT = new String[] { "Recent", "Artists", "Albums", "Songs", "Playlists", "Genres" };

	public static MainFragment newInstance() {
		MainFragment tabFragment = new MainFragment();
		return tabFragment;
	}

	@Override
	public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		mView = inflater.inflate(R.layout.simple_tabs, null);
        Logs.info("MainFragment initViews");
		pager = (ViewPager) mView.findViewById(R.id.pager);
		indicator = (TabPageIndicator) mView.findViewById(R.id.indicator);
		adapter = new CategoryAdapter(mActivity.getSupportFragmentManager());
		mTabFragmentList=new ArrayList<Fragment>();
		pager.setAdapter(adapter);
		indicator.setViewPager(pager);
		mStoryPresenter = new StoryNewPresenter(this);
		mCategoryPresenter = new StoryCategoryPresenter(this);
		mStoryPresenter.reqNewStory();
		mCategoryPresenter.reqStoryCategroy();
		return mView;
	}

	
	class CategoryAdapter extends FragmentPagerAdapter {
	

		@Override
		public Object instantiateItem(View container, int position) {
			return super.instantiateItem(container, position);
		}

		public CategoryAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			if(categorys==null){
				return null;
			}
			Logs.error("getItem="+position);
		
			return mTabFragmentList.get(position);
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

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
            Logs.error("destroyItem="+position);
			super.destroyItem(container, position, object);
		}
		
	}

	@Override
	public void onNewStory(List<Story> storys) {

	}

	@Override
	public void onStoryCategory(List<StoryCategory> categorys) {
		this.categorys = categorys;
		for(StoryCategory category:categorys){
			mTabFragmentList.add(StoryTabFragment.newInstance(category));
		}
		adapter.notifyDataSetChanged();
		indicator.setViewPager(pager);
		indicator.notifyDataSetChanged();
	}

}
