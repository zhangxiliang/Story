package com.wole.story.adapter;

import com.wole.story.entity.Story;
import com.wole.story.ui.R;
import com.wole.story.utils.ViewHolderHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StoryAdapter extends AdapterBase<Story> {

	private LayoutInflater inflater;
	public StoryAdapter(Context context){
		inflater=LayoutInflater.from(context);
	}
	@Override
	protected View getExView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView=inflater.inflate(R.layout.item_story, null);
		}
		
		Story story=mList.get(position);
		TextView titleTv=ViewHolderHelper.get(convertView, R.id.article_title_tv);
		TextView numTv=ViewHolderHelper.get(convertView, R.id.article_num_tv);
		TextView priviewTv=ViewHolderHelper.get(convertView, R.id.article_priview_tv);
		
		titleTv.setText(story.getTitle());
		numTv.setText(String.valueOf(story.getViewCount()));
		//String priview=story.getContent().substring(0, 100);
		//priviewTv.setText(priview);
		
		return convertView;
	}

	@Override
	protected void onReachBottom() {
		
	}

}
