package com.wole.story.ui.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment{
	
	protected FragmentActivity mActivity;
	protected View mView;
	protected LayoutInflater mLayoutInflater;
	protected Handler mUiHandler;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mUiHandler=new Handler();
		mActivity=this.getActivity();
		mLayoutInflater = LayoutInflater.from(mActivity);
	}
	
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mActivity=(FragmentActivity)activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return initViews(inflater, container, savedInstanceState);
	}
	
	/**
	 * 初始化界面控件
	 */
	public abstract View initViews(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState);
	
	
	protected void startActivity(Class clazz){
		Intent intent=new Intent(mActivity,clazz);
		startActivity(intent);
	}
}
