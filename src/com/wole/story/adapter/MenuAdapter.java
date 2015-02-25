package com.wole.story.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wole.story.entity.SampleItem;
import com.wole.story.ui.R;

public class MenuAdapter extends ArrayAdapter<SampleItem> {

	private int selectIndex;
	private Context context;

	public MenuAdapter(Context context) {
		super(context, 0);
		this.context=context;
	}

	public void setSelectIndex(int position) {
		selectIndex = position;
		notifyDataSetChanged();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_slide_menu, null);
		}
		ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
		icon.setImageResource(getItem(position).iconRes);
		TextView title = (TextView) convertView.findViewById(R.id.row_title);
		title.setText(getItem(position).tag);
		if (position == selectIndex) {
			convertView.setBackgroundColor(context.getResources().getColor(R.color.common_sys));
			title.setTextColor(context.getResources().getColor(R.color.white));
			icon.setImageResource(getPressedIcon(position));

		} else {
			convertView.setBackgroundColor(context.getResources().getColor(R.color.white));
			title.setTextColor(context.getResources().getColor(R.color.black));
			icon.setImageResource(getNormalIcon(position));

		}

		return convertView;
	}

	
	public int getNormalIcon(int position){
		int iconId=0;
		switch (position) {
		case 0:
			iconId=R.drawable.ic_drawer_home_normal;
			break;
		case 1:
			iconId=R.drawable.ic_drawer_login_normal;
			break;
		case 2:
			iconId=R.drawable.ic_drawer_collect_normal;
			break;
		case 3:
			iconId=R.drawable.ic_drawer_setting_normal;
			break;
		default:
			break;
		}
		return iconId;
	}
	
	public int getPressedIcon(int position){
		int iconId=0;
		switch (position) {
		case 0:
			iconId=R.drawable.ic_drawer_home_pressed;

			break;
		case 1:
			iconId=R.drawable.ic_drawer_login_pressed;
			break;
		case 2:
			iconId=R.drawable.ic_drawer_collect_pressed;
			break;
			
		case 3:
			iconId=R.drawable.ic_drawer_setting_pressed;
			break;
		default:
			break;
		}
		return iconId;
	}
}