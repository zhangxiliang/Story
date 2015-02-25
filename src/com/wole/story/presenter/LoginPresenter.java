package com.wole.story.presenter;

import java.util.List;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;
import com.wole.story.utils.Logs;

public class LoginPresenter{

	public OnLoginListener onLoginListener;
	
	public LoginPresenter(OnLoginListener onLoginListener){
		this.onLoginListener=onLoginListener;
	}
	
	public void login(String userName,String password){
		AVUser.logInInBackground(userName, password, new LogInCallback<AVUser>() {
			
			@Override
			public void done(AVUser user, AVException arg1) {
				
				if (user != null) {
					onLoginListener.onLoginSuccess();
		        } else {
		        	onLoginListener.onLoginFail();
		        }
			}
		});

	}
		
	
	public interface OnLoginListener{
		public void onLoginSuccess();
		public void onLoginFail();
	}
  

}
