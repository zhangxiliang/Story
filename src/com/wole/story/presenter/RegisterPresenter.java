package com.wole.story.presenter;

import java.util.List;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SignUpCallback;
import com.wole.story.utils.Logs;

public class RegisterPresenter  {
	public RegisterListener registerListener;

	public RegisterPresenter(RegisterListener registerListener) {
		this.registerListener = registerListener;
	}

	public void register(String userName, String passWd,String email) {
		
		AVUser user = new AVUser();
		user.setUsername(userName);
		user.setPassword(passWd);
		user.setEmail(email);

	

		user.signUpInBackground(new SignUpCallback() {
			
			@Override
			public void done(AVException e) {
				 if (e == null) {
					 registerListener.onRegisterOk();
			        } else {
			         registerListener.onRegisterFail(e.getMessage());
			        }
				
			}
		});
		

	}
	


	public interface RegisterListener {
		public void onRegisterOk();
		public void onRegisterFail(String errorMsg);
	}

	
}
