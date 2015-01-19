package com.wole.story.view;


import com.wole.story.ui.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class WoxiuProgressDialog extends Dialog {

	private AlertDialog ad;
	private Context context;
	private TextView mTipTv;
	
	public WoxiuProgressDialog(Context context) {
		super(context,R.style.progress_dialog);
		this.context = context;
		setCanceledOnTouchOutside(false);
		View dialogView=LayoutInflater.from(context).inflate(R.layout.layout_progress, null);

		setContentView(dialogView);
		getWindow().getAttributes().gravity=Gravity.CENTER;
		
		mTipTv=(TextView)getWindow().findViewById(R.id.tip_tv);

	}
	
	
	public void setTips(String tips){
		mTipTv.setText(tips);
	}
	
	public void setTipsGone(){
		mTipTv.setVisibility(View.GONE);
	}
	
}
