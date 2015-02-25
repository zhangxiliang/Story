package com.wole.story.ui;

import com.wole.story.presenter.ForgetPresenter;
import com.wole.story.presenter.ForgetPresenter.OnPasswordReset;
import com.wole.story.utils.CommonUtils;
import com.wole.story.utils.ToastOf56;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class ForgetPassWdActivity extends BaseActivity implements OnClickListener, OnPasswordReset {
	private EditText mEmailEt;
	private TextView mSendEmailTv;
	private ForgetPresenter mForgetPresenter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_forget);
		mEmailEt = (EditText) findViewById(R.id.email_et);
		mSendEmailTv = (TextView) findViewById(R.id.forget_btn);
		mSendEmailTv.setOnClickListener(this);
		mForgetPresenter = new ForgetPresenter(this);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);  
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.forget_btn) {
			String email = mEmailEt.getText().toString();
			if (!CommonUtils.isEmail(email)) {
				ToastOf56.showToast(mActivity, "邮箱格式不正确");
				return;
			}
			if (!email.equals("")) {
				mForgetPresenter.resetPassword(email);
			}
		}

	}

	@Override
	public void onResetOk() {
		ToastOf56.showToast(mActivity, "重置链接已发到该邮箱");

	}
}
