package com.wole.story.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wole.story.entity.Story;
import com.wole.story.presenter.StoryPresenter;
import com.wole.story.presenter.StoryPresenter.IStoryListener;
import com.wole.story.utils.CommonUtils;

public class StoryActivity extends BaseActivity implements IStoryListener {

	private TextView mContentTv;
	private TextView mTimeTv;
	private TextView mAuthorTv;
	private TextView mTitleTv;
	  private Typeface fontFace;

	private StoryPresenter mStoryPresenter;
	private static final String STORY = "story";

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mStoryPresenter = new StoryPresenter(this);
		setContentView(R.layout.activity_story);
		showLoadingView();
		Intent intent = getIntent();
		Story story = (Story) intent.getSerializableExtra(STORY);
		mContentTv = (TextView) this.findViewById(R.id.story_content);
		mTimeTv = (TextView) this.findViewById(R.id.story_time);
		mAuthorTv = (TextView) this.findViewById(R.id.story_writer);
		mTitleTv = (TextView) this.findViewById(R.id.story_title);
		mStoryPresenter.reqStory(story.getUrl());
		
		String front=mStorySetting.getFront();
		if(front!=null && front.equals("girl")){
			this.fontFace = Typeface.createFromAsset(getAssets(), "fonts/DFPShaoNvW5-GB.ttf");
			mContentTv.setTypeface(fontFace);
			mTimeTv.setTypeface(fontFace);
			mAuthorTv.setTypeface(fontFace);
			mTitleTv.setTypeface(fontFace);
		}
		int size=CommonUtils.sp2px(this, mStorySetting.getFrontSize());
		mContentTv.setTextSize(size);
	}

	@Override
	public void onStory(Story story) {
		hiddenLoadingView();
		mContentTv.setText(Html.fromHtml(story.getContent()));
		mTimeTv.setText("日期:" + story.getDate());
		mAuthorTv.setText("作者:" + story.getAuthor().substring(0, story.getAuthor().indexOf("发")));
		mTitleTv.setText(story.getTitle());
		
	}
}
