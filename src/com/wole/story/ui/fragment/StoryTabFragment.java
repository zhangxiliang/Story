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
import com.wole.story.presenter.StoryNewPresenter;
import com.wole.story.presenter.StoryNewPresenter.INewStory;
import com.wole.story.ui.BaseActivity;
import com.wole.story.ui.R;
import com.wole.story.ui.R.id;
import com.wole.story.ui.R.layout;

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

public class StoryTabFragment extends BaseFragment implements INewStory{
     
    private StoryNewPresenter mStoryPresenter;
	 private static final String[] CONTENT = new String[] { "Recent", "Artists", "Albums", "Songs", "Playlists", "Genres" };

	 public static StoryTabFragment newInstance(){
		 StoryTabFragment tabFragment=new StoryTabFragment();
		 return tabFragment;
	 }
	 
	 
		@Override
		public View initViews(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			 mStoryPresenter=new StoryNewPresenter(this);

			mView=inflater.inflate(R.layout.simple_tabs, null);

	        FragmentPagerAdapter adapter = new GoogleMusicAdapter(mActivity.getSupportFragmentManager());

	        ViewPager pager = (ViewPager)mView.findViewById(R.id.pager);
	        pager.setAdapter(adapter);

	        TabPageIndicator indicator = (TabPageIndicator)mView.findViewById(R.id.indicator);
	        indicator.setViewPager(pager);
	        mStoryPresenter.reqNewStory();
	        return mView;
		}

	

	    class GoogleMusicAdapter extends FragmentPagerAdapter {
	        public GoogleMusicAdapter(FragmentManager fm) {
	            super(fm);
	        }

	        @Override
	        public Fragment getItem(int position) {
	            return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
	        }

	        @Override
	        public CharSequence getPageTitle(int position) {
	            return CONTENT[position % CONTENT.length].toUpperCase();
	        }

	        @Override
	        public int getCount() {
	          return CONTENT.length;
	        }
	    }



		@Override
		public void addNewStory(List<Story> storys) {
			
		}





	
	
	
	

}
