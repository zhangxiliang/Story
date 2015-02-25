package com.wole.story.view;




import com.wole.story.ui.R;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.LinearLayout.LayoutParams;

public class LoadingWindow extends PopupWindow{
	private View mView;
	private Context mContext;

	public LoadingWindow(Context context ) {
		super(context);
		this.mContext=context;
		mView = LayoutInflater.from(context).inflate(R.layout.layout_load, null);
		this.setContentView(mView);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		this.setBackgroundDrawable(new BitmapDrawable());
		this.setOutsideTouchable(false);
		this.setTouchable(false);
	}
	
	
	
}
