package com.wole.story.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class ViewUtils {
public static View getView(Context context,int layoutId){
	LayoutInflater inflater=LayoutInflater.from(context);
	return inflater.inflate(layoutId, null);
}
}
