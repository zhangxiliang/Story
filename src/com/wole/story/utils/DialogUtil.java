package com.wole.story.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
/***
 * 
 * Diaolog   
 * Created By Zhangxiliang
 * Date：2014年9月22日 
 * Version： 2.0   
 * Copyright (c) 2014 56.com Software corporation All Rights Reserved.     
 *
 */
public class DialogUtil {
	static ProgressDialog progressDlg;
	
	
	
	public static  void showDialog(Context mContext,String msg){
		showDialog(mContext, msg, null);
	}
	
	
	
	public static  void showDialog(Context mContext,String msg,OnClickListener confirmListenner){
		showDialog(mContext, msg, confirmListenner, null);
	}
	
	public static  void showDialog(Context mContext,String msg,OnClickListener confirmListenner,OnClickListener cancelListener){
		showDialog(mContext, msg, "确定", "取消", confirmListenner, cancelListener);
	}
	
	public static  void showDialog(Context mContext,String msg,String okmsg,String cancelmsg,OnClickListener confirmListenner,OnClickListener cancelListener){
		if(mContext!=null && !((Activity)mContext).isFinishing()){
		new AlertDialog.Builder(mContext).setCancelable(false).setTitle("提示").setMessage(msg).setNegativeButton(cancelmsg, cancelListener).setPositiveButton(okmsg, confirmListenner).show();
	    }
	}
	
	public static  void showDialog(Context mContext,String title,String msg,String okmsg,String cancelmsg,OnClickListener confirmListenner,OnClickListener cancelListener){
		if(mContext!=null && !((Activity)mContext).isFinishing()){
		new AlertDialog.Builder(mContext).setCancelable(false).setTitle(title).setMessage(msg).setNegativeButton(cancelmsg, cancelListener).setPositiveButton(okmsg, confirmListenner).show();
	    }
	}
	
	public static  void showDialogNoTitle(Context mContext,String msg,OnClickListener confirmListenner,OnClickListener cancelListener){
		
		new AlertDialog.Builder(mContext).setMessage(msg).setCancelable(false).setNegativeButton("取消", cancelListener).setPositiveButton("确定", confirmListenner).show();
	}
	
	public static  void showDialogNoTitle(Context mContext,String msg,OnClickListener confirmListenner){
		if(mContext!=null && !((Activity)mContext).isFinishing()){
		new AlertDialog.Builder(mContext).setMessage(msg).setCancelable(false).setPositiveButton("确定", confirmListenner).show();
		}
	}
	
    public static void showProgressWait(final Context mContext){
    	showProgress(mContext, "请稍候", true);
    }

    public static void showProgress(final Context mContext,final String tips,final boolean isCancel){
    	
    	
    	if(mContext!=null && !((Activity)mContext).isFinishing())
    	((Activity)mContext).runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				progressDlg = new ProgressDialog(mContext);
				progressDlg.setMessage(tips);
				progressDlg.setCancelable(isCancel);
				progressDlg.show();
			}
		});
    }
	
    public static void cancelProgress(){
    	if(progressDlg!=null){
    		progressDlg.cancel();
    	}
    }
    
    public static boolean isShowingProgress(){
    	if(progressDlg != null && progressDlg.isShowing()){
    		return true;
    	}else{
    		return false;
    	}
    }
}
