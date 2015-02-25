package com.wole.story.ui;

import com.wole.story.presenter.LoginPresenter;
import com.wole.story.presenter.RegisterPresenter;
import com.wole.story.presenter.RegisterPresenter.RegisterListener;
import com.wole.story.utils.CommonUtils;
import com.wole.story.utils.ToastOf56;
import com.wole.story.view.WoxiuProgressDialog;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, RegisterListener {
	private TextView mLoginTv;
	private TextView mRegTv;
	private EditText mUserNameEt;
	private EditText mPassWdEt;
	private EditText mEmailEt;
	private RegisterPresenter mRegisterPresenter;
	private WoxiuProgressDialog mDialog;
	private String userName;
	private String passWord;
	private String email;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_register);
		mLoginTv = (TextView) findViewById(R.id.login_btn);
		mRegTv = (TextView) findViewById(R.id.reg_btn);
		mUserNameEt = (EditText) findViewById(R.id.userName_et);
		mPassWdEt = (EditText) findViewById(R.id.passwd_et);
		mEmailEt = (EditText) findViewById(R.id.email_et);
		mRegTv.setOnClickListener(this);
		mRegisterPresenter = new RegisterPresenter(this);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);  
	}

	@Override
	public void onClick(View v) {
		userName = mUserNameEt.getText().toString();
		passWord = mPassWdEt.getText().toString();
		email = mEmailEt.getText().toString();
		if (v.getId() == R.id.reg_btn) {
			if(!CommonUtils.isEmail(email)){
				ToastOf56.showToast(mActivity, "邮箱格式不正确");
				return;
			}
			if (!userName.equals("") && !passWord.equals("") && !email.equals("")) {
				showDialog("注册中...");
				mRegisterPresenter.register(userName, passWord, email);
			}
		}
	}

	public void showDialog(String tips) {
		if (mDialog == null) {
			mDialog = new WoxiuProgressDialog(mActivity);
		}
		mDialog.setTips(tips);
		mDialog.show();
	}

	@Override
	public void onRegisterOk() {
		mDialog.dismiss();
		ToastOf56.showToast(mActivity, "注册成功,并登录成功!");
		if (!userName.equals("")) {
			mStorySetting.saveUserName(userName);
		}
		setResult(RESULT_OK);
		this.finish();
	}



	@Override
	public void onRegisterFail(String errorMsg) {
		mDialog.dismiss();
		ToastOf56.showToast(mActivity, errorMsg);
		
	}
}
