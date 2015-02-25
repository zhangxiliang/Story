package com.wole.story.ui;

import com.wole.story.presenter.LoginPresenter;
import com.wole.story.presenter.LoginPresenter.OnLoginListener;
import com.wole.story.presenter.RegisterPresenter;
import com.wole.story.presenter.RegisterPresenter.RegisterListener;
import com.wole.story.utils.ToastOf56;
import com.wole.story.view.WoxiuProgressDialog;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends BaseActivity implements OnClickListener, OnLoginListener {
	private TextView mLoginTv;
	private TextView mRegTv;
	private EditText mUserNameEt;
	private EditText mPassWdEt;
	private LoginPresenter mLoginPresenter;
	private WoxiuProgressDialog mDialog;
	private String userName;
	private String passWord;
	private TextView mForgetPwTv;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_login);
		mLoginTv = (TextView) findViewById(R.id.login_btn);
		mRegTv = (TextView) findViewById(R.id.reg_btn);
		mUserNameEt = (EditText) findViewById(R.id.userName_et);
		mPassWdEt = (EditText) findViewById(R.id.passwd_et);
		mForgetPwTv=(TextView)findViewById(R.id.forget_tv);
		mLoginTv.setOnClickListener(this);
		mRegTv.setOnClickListener(this);
		mForgetPwTv.setOnClickListener(this);
		mLoginPresenter = new LoginPresenter(this);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);  
	}

	@Override
	public void onClick(View v) {
		userName = mUserNameEt.getText().toString();
		 passWord = mPassWdEt.getText().toString();
		if (v.getId() == R.id.login_btn) {
			if (!userName.equals("") && !passWord.equals("")){
				 showDialog("正在登录...");
				 mLoginPresenter.login(userName, passWord);
			}
           
		}else if(v.getId()==R.id.reg_btn){
			Intent intent=new Intent(this,RegisterActivity.class);
			startActivityForResult(intent, 1);
		} else if(v.getId()==R.id.forget_tv){
			Intent intent=new Intent(this,ForgetPassWdActivity.class);
			startActivity(intent);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent arg2) {
		if(resultCode==RESULT_OK){
			this.finish();
		}
		super.onActivityResult(requestCode, resultCode, arg2);
	}
	
	public void showDialog(String tips) {
		if (mDialog == null) {
			mDialog = new WoxiuProgressDialog(mActivity);
		}
		mDialog.setTips(tips);
		mDialog.show();
	}


	@Override
	public void onLoginSuccess() {
		mDialog.dismiss();
		ToastOf56.showToast(mActivity, "登陆成功!");
		
		this.finish();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		  switch (item.getItemId()) {  
		    case android.R.id.home:  
		        finish();  
		        return true;  
		    }  
		  return false;
	}

	@Override
	public void onLoginFail() {
		mDialog.dismiss();
		ToastOf56.showToast(mActivity, "账号或密码错误");
		
	}

}
