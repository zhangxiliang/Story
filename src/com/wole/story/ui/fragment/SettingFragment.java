package com.wole.story.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.wole.story.app.StoryApplication;
import com.wole.story.config.StorySetting;
import com.wole.story.ui.R;

public class SettingFragment extends BaseFragment implements OnCheckedChangeListener,OnSeekBarChangeListener{

	   private RadioButton mGirlRb;
	  private RadioButton mSysRb;
	  private SeekBar mSeekBar;
	  private RadioGroup radioGroup;
	  private TextView mFrontSize;


	public static SettingFragment newInstance(){
		SettingFragment setFragment=new SettingFragment();
		return setFragment;
	}
	
	
	
	@Override
	public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		mView=inflater.inflate(R.layout.fragment_setting, null);
		mGirlRb=(RadioButton)mView.findViewById(R.id.girl_font);
		mSysRb=(RadioButton)mView.findViewById(R.id.sys_font);
		mSeekBar=(SeekBar)mView.findViewById(R.id.seekBar);
		mFrontSize=(TextView)mView.findViewById(R.id.fontSize);
		radioGroup=(RadioGroup)mView.findViewById(R.id.font_group);
		radioGroup.setOnCheckedChangeListener(this);
		mSeekBar.setOnSeekBarChangeListener(this);
		mSeekBar.setProgress(StorySetting.getInstance().getFrontSize());
		mFrontSize.setText("字体大小:"+mSeekBar.getProgress());
		return mView;
	}



	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		
		
	}



	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}



	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		mFrontSize.setText("字体大小:"+seekBar.getProgress());
		StorySetting.getInstance().saveFrontSize(seekBar.getProgress());
	}



	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		   if(checkedId==R.id.girl_font){
			   StorySetting.getInstance().saveFront("girl");
		   }else{
			   StorySetting.getInstance().saveFront("sys");
		   }
	}
	

}
