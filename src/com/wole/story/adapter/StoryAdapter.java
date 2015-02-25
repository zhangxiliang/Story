package com.wole.story.adapter;

import com.wole.story.entity.Story;
import com.wole.story.ui.R;
import com.wole.story.utils.Logs;
import com.wole.story.utils.ViewHolderHelper;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StoryAdapter extends AdapterBase<Story> {

	private LayoutInflater inflater;
	private Context mContext;
	public StoryAdapter(Context context){
		inflater=LayoutInflater.from(context);
		this.mContext=context;
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
		//Logs.error("story.getContent()="+story.getContent());
		priviewTv.setText(story.getAuthor());
		
		if(story.isReaded()){
			numTv.setBackgroundColor(mContext.getResources().getColor(R.color.gray));
			numTv.setTextColor(mContext.getResources().getColor(R.color.white));
			titleTv.setTextColor(mContext.getResources().getColor(R.color.gray));
			priviewTv.setTextColor(mContext.getResources().getColor(R.color.gray));
		}else{
			numTv.setBackgroundColor(mContext.getResources().getColor(R.color.color_pager_bg1));
			numTv.setTextColor(mContext.getResources().getColor(R.color.white));
			titleTv.setTextColor(mContext.getResources().getColor(R.color.black));
			priviewTv.setTextColor(mContext.getResources().getColor(R.color.common_666));
		}
		if(story.getContent()!=null && story.getContent().length()>50){
			//String priview=story.getContent().substring(0, 50);
			//priviewTv.setText(Html.fromHtml(priview));
		}
		
		
		return convertView;
	}

	@Override
	protected void onReachBottom() {
		
	}

}
