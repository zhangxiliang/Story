package com.wole.story.ui.fragment;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.wole.story.ui.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MineFragment extends BaseFragment implements OnClickListener{

	private TextView mExitTv;
	private MineListener mineListener;
	public static MineFragment newInstance() {
		MineFragment mineFragment = new MineFragment();
		return mineFragment;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mineListener=(MineListener)activity;
	}
	
	@Override
	public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView=inflater.inflate(R.layout.fragment_mine, null);
		mExitTv=(TextView)mView.findViewById(R.id.exit_account_tv);
		mExitTv.setOnClickListener(this);
		return mView;
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.exit_account_tv){
			AVUser.logOut();
			mineListener.mineBack();
		}
		
	}


	 public interface MineListener{
	    	public void mineBack();
	    }

}
