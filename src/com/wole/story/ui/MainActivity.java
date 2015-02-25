package com.wole.story.ui;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuDrawable.Stroke;
import com.balysv.materialmenu.MaterialMenuIcon;
import com.umeng.update.UmengUpdateAgent;
import com.wole.story.adapter.MenuAdapter;
import com.wole.story.db.StoryDao;
import com.wole.story.entity.SampleItem;
import com.wole.story.ui.fragment.MainFragment;
import com.wole.story.ui.fragment.MineFragment;
import com.wole.story.ui.fragment.MineFragment.MineListener;
import com.wole.story.ui.fragment.SettingFragment;
import com.wole.story.utils.ToastOf56;

@SuppressLint("NewApi")
public class MainActivity extends BaseActivity implements MineListener {

	private MainFragment mTabFragmet;
	private MineFragment mineFragment;
	private SettingFragment mSettingFragment;
	private TextView mHeadTv;
	private FrameLayout mContentLayout;
	private long exitTime;
	/** DrawerLayout */
	private DrawerLayout mDrawerLayout;
	/** 左边栏菜单 */
	private ListView mMenuListView;
	
	/** 菜单列表 */
	private String[] mMenuTitles;
	/** Material Design风格 */
	private MaterialMenuIcon mMaterialMenuIcon;
	/** 菜单打开/关闭状态 */
	private boolean isDirection_left = false;
	/** 右边栏打开/关闭状态 */
	private boolean isDirection_right = false;
	private View showView;
	private MenuAdapter menuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContentLayout=(FrameLayout)this.findViewById(R.id.content);
		mHeadTv=(TextView)this.findViewById(R.id.head_tv);
		mHeadTv.setText("推荐");
		UmengUpdateAgent.setDefault();
		UmengUpdateAgent.update(this);
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mMenuListView = (ListView) findViewById(R.id.left_drawer);
		this.showView = mMenuListView;

		// 初始化菜单列表
		menuAdapter = new MenuAdapter(mActivity);
		menuAdapter.add(new SampleItem("首页", R.drawable.ic_account));
		menuAdapter.add(new SampleItem("我的", R.drawable.ic_account));
		menuAdapter.add(new SampleItem("收藏", R.drawable.ic_drawer_collect_normal));
		menuAdapter.add(new SampleItem("设置", R.drawable.ic_drawer_setting_normal));
		menuAdapter.setSelectIndex(-1);
		//mMenuTitles = getResources().getStringArray(R.array.menu_array);
		
		mMenuListView.setAdapter(menuAdapter);
		mMenuListView.setOnItemClickListener(new DrawerItemClickListener());

		// 设置抽屉打开时，主要内容区被自定义阴影覆盖
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// 设置ActionBar可见，并且切换菜单和内容视图
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		mMaterialMenuIcon = new MaterialMenuIcon(this, Color.WHITE, Stroke.THIN);
		mDrawerLayout.setDrawerListener(new DrawerLayoutStateListener());
		
		intFragement();
				
    }


   
	/**
	 * ListView上的Item点击事件
	 * 
	 */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	/**
	 * DrawerLayout状态变化监听
	 */
	private class DrawerLayoutStateListener extends
			DrawerLayout.SimpleDrawerListener {
		/**
		 * 当导航菜单滑动的时候被执行
		 */
		@Override
		public void onDrawerSlide(View drawerView, float slideOffset) {
			showView = drawerView;
			if (drawerView == mMenuListView) {// 根据isDirection_left决定执行动画
				mMaterialMenuIcon.setTransformationOffset(
						MaterialMenuDrawable.AnimationState.BURGER_ARROW,
						isDirection_left ? 2 - slideOffset : slideOffset);
			} 
		}

		/**
		 * 当导航菜单打开时执行
		 */
		@Override
		public void onDrawerOpened(android.view.View drawerView) {
			if (drawerView == mMenuListView) {
				isDirection_left = true;
			} 
		}

		/**
		 * 当导航菜单关闭时执行
		 */
		@Override
		public void onDrawerClosed(android.view.View drawerView) {
			if (drawerView == mMenuListView) {
				isDirection_left = false;
			} 
		}
	}

	/**
	 * 切换主视图区域的Fragment
	 * 
	 * @param position
	 */
	private void selectItem(int position) {
		switch (position) {
		case 0:
			setTitle("故事会");
			replaceFragment(R.id.content,mTabFragmet);
			break;
		case 1:
			if(mStoryApplication.isLoginUser()){
				replaceFragment(R.id.content, mineFragment);
	
				setTitle("我的");
			}else{
			  startActivity(LoginActivity.class);
			}
			break;
		case 2:
			setTitle("收藏");
			break;
		case 3:
			setTitle("设置");
			replaceFragment(R.id.content, mSettingFragment);
			break;
		default:
			break;
		}
		menuAdapter.setSelectIndex(position);
		mDrawerLayout.closeDrawer(mMenuListView);
		
	}

	/**
	 * 点击ActionBar上菜单
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case android.R.id.home:
			if (showView == mMenuListView) {
				if (!isDirection_left) { // 左边栏菜单关闭时，打开
					mDrawerLayout.openDrawer(mMenuListView);
				} else {// 左边栏菜单打开时，关闭
					mDrawerLayout.closeDrawer(mMenuListView);
				}
			} 
			break;
		case R.id.action_personal:
			
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * 根据onPostCreate回调的状态，还原对应的icon state
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mMaterialMenuIcon.syncState(savedInstanceState);
	}

	/**
	 * 根据onSaveInstanceState回调的状态，保存当前icon state
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		mMaterialMenuIcon.onSaveInstanceState(outState);
		super.onSaveInstanceState(outState);
	}

	/**
	 * 加载菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    
    private void intFragement(){
    	mTabFragmet=MainFragment.newInstance();
    	mineFragment=MineFragment.newInstance();
    	mSettingFragment=SettingFragment.newInstance();
    	replaceFragment(R.id.content,mTabFragmet );
    /*	mFragmentTransaction=getTransaction();
    	mFragmentTransaction.add(R.id.content, mTabFragmet);
    	mFragmentTransaction.add(R.id.content, mineFragment);
    	mFragmentTransaction.hide( mineFragment);
    	mFragmentTransaction.commitAllowingStateLoss();*/
    }
    

    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (mFragmentManager.getBackStackEntryCount()==1 && keyCode == KeyEvent.KEYCODE_BACK) {
			
			 if((System.currentTimeMillis()-exitTime) > 2000){  
				    ToastOf56.showToast(mActivity, "再按一次退出程序");
		            exitTime = System.currentTimeMillis();   
		        } else {
		           getApplicationContext().sendBroadcast(new Intent("finish"));
		        }
		        return true;   
			
		}else{
			setTitle("故事会");
		}
		return super.onKeyDown(keyCode, event);

	}

	@Override
	public void mineBack() {
	    setTitle("故事会");
		replaceFragment(R.id.content, mTabFragmet);
	}

    
   
}
