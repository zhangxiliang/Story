package com.wole.story.framework;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * TODO <一句话功能描述>
 * 
 * @FileName com.wole56.ishow.service.BaseService.java
 * @author zhangbl
 * @date 2014年11月12日 下午6:08:53
 * @version V1.0 <描述当前版本功能>
 */
public class BaseService implements Response.Listener<String>,
		Response.ErrorListener {

	private BasePostRequest mBasePostRequest;
	private TaskCallBack taskCallBack;

	public BaseService(TaskCallBack callBack) {
		this.taskCallBack = callBack;
	}

	public void setTaskCallBack(TaskCallBack taskCallBack) {
		this.taskCallBack = taskCallBack;
	}

	/*
	 * 调用异步请求
	 */
	public void sync(String url, HashMap<String, String> params) {
		int method = (params == null) ? Method.GET : Method.POST;
		mBasePostRequest = new BasePostRequest(method, url, this, this,
				params);
		if (mBasePostRequest != null) {
			if (taskCallBack != null) {
				taskCallBack.onPreExecute();
			}
			VolleyUtil.syncRequest(mBasePostRequest);
		}
	}

	/*
	 * 调用异步请求，默认url
	 */
	public void sync( HashMap<String, String> params) {
		sync(NetConfig.BASE_URL, params);
	}
	
	/**
	 * Get请求
	 * @param url
	 * @param requestCode
	 */
	public void syncGet(String url){
		sync(url, null);
	}

	@Override
	public void onResponse(String arg0) {
		if (TextUtils.isEmpty(arg0)) {
			if (taskCallBack != null) {
				taskCallBack.onError(new WoxiuException(
						"network error"));
			}
		} else {
			try {
				if (taskCallBack != null) {
					taskCallBack.onPostExecute(
							new JSONObject(arg0));
				}
			} catch (JSONException e) {
				if (taskCallBack != null) {
					taskCallBack.onError( e);
				}
			}
		}

	}

	@Override
	public void onErrorResponse(VolleyError arg0) {
		String errorMsg = arg0.getMessage();
		if (taskCallBack != null) {
			taskCallBack.onError( new WoxiuException(errorMsg));
		}
		mBasePostRequest.cancel();
	}

	/*
	 * 取消网络请求
	 */
	protected void cancelRequest() {
		if (mBasePostRequest != null && !mBasePostRequest.isCanceled()) {
			mBasePostRequest.cancel();
		}
	}

}
