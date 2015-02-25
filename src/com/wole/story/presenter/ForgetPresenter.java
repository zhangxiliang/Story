package com.wole.story.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestPasswordResetCallback;

public class ForgetPresenter {

public OnPasswordReset onPasswordReset;
	
	public ForgetPresenter(OnPasswordReset onPasswordReset){
		this.onPasswordReset= onPasswordReset;
	}
	
	public void resetPassword(String email){
		AVUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
		    public void done(AVException e) {
		        if (e == null) {
		        	onPasswordReset.onResetOk();
		        } else {
		           
		        }
		    }
		});

	}
		
	
	public interface OnPasswordReset{
		public void onResetOk();
	}
  
}
