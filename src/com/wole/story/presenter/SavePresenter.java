package com.wole.story.presenter;

import android.webkit.WebView.FindListener;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SaveCallback;


public abstract class SavePresenter extends SaveCallback{

	@Override
	public abstract void done(AVException arg0); 

}
