package com.wole.story.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.umeng.update.UmengUpdateAgent;
import com.viewpagerindicator.TabPageIndicator;
import com.wole.story.ui.fragment.StoryTabFragment;
import com.wole.story.ui.fragment.TestFragment;
import com.wole.story.utils.ActivtyUtil;
import com.wole.story.utils.ToastOf56;

public class MainActivity extends BaseActivity {

	private Fragment mTabFragmet;
	private TextView mHeadTv;
	private FrameLayout mContentLayout;
	private long exitTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContentLayout=(FrameLayout)this.findViewById(R.id.content);
		mHeadTv=(TextView)this.findViewById(R.id.head_tv);
		mHeadTv.setText("推荐");
		UmengUpdateAgent.setDefault();
		UmengUpdateAgent.update(this);
		intFragement();
		
    }

    private void intFragement(){
    	mTabFragmet=StoryTabFragment.newInstance();
    	addFragment(R.id.content, mTabFragmet);
    }
    
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
			 if((System.currentTimeMillis()-exitTime) > 2000){  
				    ToastOf56.showToast(mActivity, "再按一次退出程序");
		            exitTime = System.currentTimeMillis();   
		        } else {
		           getApplicationContext().sendBroadcast(new Intent("finish"));
		        }
		        return true;   
			
		}
		return super.onKeyDown(keyCode, event);

	}

}
