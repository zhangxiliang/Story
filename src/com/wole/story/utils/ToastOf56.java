package com.wole.story.utils;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

/**
 * 用户统一Toast调用~
 * Created with IntelliJ IDEA.
 * User: Jace
 * Date: 12-12-27
 * Time: 下午6:10
 * To change this template use File | Settings | File Templates.
 */
public class ToastOf56 {
    private static Toast mToast;


    public static void showToast(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
    
    public static void showToastLong(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }

    public static void showToast(Context context, int resId) {
        showToast(context, context.getResources().getString(resId));
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
    
    public static void showAlert(Context context, String text) {
		new AlertDialog.Builder(context).setTitle("提示").setMessage(text).setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).show();
	}
}
