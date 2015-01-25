package com.wole.story.utils;

import java.util.HashMap;
import java.util.Map;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.WindowManager;
import android.widget.Toast;

public class ActivtyUtil {
	static ProgressDialog progressDlg ;
	public static Map<String ,Activity> activitys=new HashMap<String, Activity>();
	public static void addActivity(Activity activity){
		activitys.put(activity.getClass().getSimpleName(), activity);
	}
	public static void ExitApp(){
		for(Map.Entry<String ,Activity> ac:activitys.entrySet()){
			if(ac.getValue()!=null){
				ac.getValue().finish();
			}
		}
		//System.exit(0);
	}
	public static void showProgressDialog(Context context,String msg,boolean cance){
		progressDlg= new ProgressDialog(context);
		progressDlg.setMessage(msg);
		progressDlg.setCancelable(cance);
		progressDlg.show();
	
	}
	
	public static void dismissProgressDialog(){
		if(progressDlg!=null){
			progressDlg.dismiss();
		}
	}
	
	/** 有确定，取消按钮的提示框*/
	public static void showInfoDialog(Context context,String title,String message,int iconType,OnClickListener sureListener,OnClickListener cancelListener){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		if(iconType!=0)
		builder.setIcon(iconType);
		builder.setPositiveButton("确定",sureListener);
		builder.setNegativeButton("取消", cancelListener);
		AlertDialog dialog = builder.create();
		//dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		dialog.show();
	}

	public static void showInfoDialog(Context context, String title, String message, int iconType) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		//builder.setIcon(iconType);
		AlertDialog dialog = builder.create();
		//dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		dialog.show();
	}


	  public static void showAlert(Context context,CharSequence title,CharSequence message,CharSequence btnTitle){
	    	new AlertDialog.Builder(context).setTitle(title)
	    	.setMessage(message).setPositiveButton(btnTitle, new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface dialog, int which) {
					
				}
	    		
	    	}).show();
	    }
	    public static void openToast(Context context,String str){
	    	Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	    }
	    
	    public static void finshActivity(){
	    	
	    }
}
