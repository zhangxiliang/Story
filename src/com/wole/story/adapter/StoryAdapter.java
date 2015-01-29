package com.wole.story.adapter;

import com.wole.story.entity.Story;
import com.wole.story.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
		return convertView;
	}

	@Override
	protected void onReachBottom() {
		
	}

}
