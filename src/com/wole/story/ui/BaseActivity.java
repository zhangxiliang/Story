package com.wole.story.ui;

import com.umeng.analytics.MobclickAgent;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;

public class BaseActivity extends FragmentActivity{
	private FragmentManager mFragmentManager;

	private View mLoadingView;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
        mFragmentManager=this.getSupportFragmentManager();

	}
	
	
	protected void addLoadingView() {
		mLoadingView= LayoutInflater.from(this).inflate(R.layout.layout_load, null);
		LayoutParams params=new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
		getWindow().addContentView(mLoadingView, params);
	}
	
	
/*	protected void  removeLoadingView() {
		ViewGroup viewGroup=(ViewGroup)getWindow().getDecorView().getParent();
		//viewGroup.removeViewInLayout(mLoadingView);
		viewGroup.removeView(mLoadingView);
		viewGroup.invalidate();
	}
	*/
	/*
	 * ���fragment
	 */
	protected void addFragment(int containerId, Fragment fragment) {

		addFragment(containerId, fragment,null);
	}

	
	protected void addFragment(int containerId, Fragment fragment,String tag) {
		if (null == fragment) {
			return;
		}

		FragmentTransaction mFragmentTransaction = mFragmentManager
				.beginTransaction();
		mFragmentTransaction.add(containerId, fragment,tag);
		mFragmentTransaction.addToBackStack(null);
		mFragmentTransaction.commitAllowingStateLoss();
		
	}
	/*
	 * �Ƴ�fagment
	 */
	protected void removeFragment(Fragment fragment) {
		if (null != fragment && fragment.isAdded()) {
			FragmentTransaction mFragmentTransaction = mFragmentManager
					.beginTransaction();
			mFragmentTransaction.remove(fragment);
			mFragmentTransaction.commitAllowingStateLoss();
		}
	}

	/*
	 * �滻fragment
	 */
	protected void replaceFragment(int containerId, Fragment fragment) {

		if (fragment != null && !fragment.isAdded()) {
			FragmentTransaction mFragmentTransaction = mFragmentManager
					.beginTransaction();
			mFragmentTransaction.replace(containerId, fragment);
			mFragmentTransaction.commitAllowingStateLoss();
		}
	}

	/*
	 * ����fragment
	 */
	protected void hidenFragment(Fragment fragment) {

		if (null != fragment && fragment.isVisible()) {
			FragmentTransaction mFragmentTransaction = mFragmentManager
					.beginTransaction();
			mFragmentTransaction.hide(fragment);
			mFragmentTransaction.commitAllowingStateLoss();
		}
	}

	/*
	 * ��ʾfragment
	 */
	protected void showFragment(Fragment fragment) {

		if (null != fragment && fragment.isHidden()) {
			FragmentTransaction mFragmentTransaction = mFragmentManager
					.beginTransaction();
			mFragmentTransaction.show(fragment);
			mFragmentTransaction.commitAllowingStateLoss();
		}
	}
	
	/****************** ��ȡ��Դ�ļ� ********************/

	/*
	 * ��ȡstring
	 */
	protected String getResourceString(int stringId) {
		return getResources().getString(stringId);
	}

	/*
	 * ��ȡcolor
	 */
	protected int getResourceColor(int colorId) {
		return getResources().getColor(colorId);
	}

	/*
	 * ��ȡͼƬ
	 */

	protected Drawable getResourceDrawable(int drawableId) {
		return getResources().getDrawable(drawableId);
	}

	/*
	 * ����activity
	 */
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	protected void startActivityForResult(Class<?> cls, int requestCode) {
		startActivity(cls, null);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
