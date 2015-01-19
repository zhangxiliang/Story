package com.wole.story.framework;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

/**
 * TODO <一句话功能描述>
 * 
 * @FileName com.wole56.ishow.service.BasePostRequest.java
 * @author zhangbl
 * @date 2014年11月12日 下午7:38:02
 * @version V1.0 <描述当前版本功能>
 */
public class BasePostRequest extends StringRequest {

	private HashMap<String, String> mParams;

	public BasePostRequest(int method, String url, Listener<String> listener,
			ErrorListener errorListener, HashMap<String, String> mParams) {
		super(method, url, listener, errorListener);
		//setRetryPolicy(new DefaultRetryPolicy(12* 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		this.mParams = mParams;

	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return mParams;
	}
}
