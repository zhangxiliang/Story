package com.wole.story.framework;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.wole.story.app.StoryApplication;

/**
 * TODO <一句话功能描述>
 * 
 * @FileName com.wole56.ishow.service.VolleyUtil.java
 * @author zhangbl
 * @date 2014年11月12日 下午6:32:21
 * @version V1.0 <描述当前版本功能>
 */
public class VolleyUtil {

	private static RequestQueue mQueue = null;

	public synchronized static void syncRequest(BasePostRequest request) {
		if (mQueue == null) {
			mQueue = Volley.newRequestQueue(StoryApplication.getInstance()
					.getApplicationContext());
			
		}
		if (request != null) {
			mQueue.add(request);
			
		}
	}

}
