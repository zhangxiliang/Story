package com.wole.story.framework;

import org.json.JSONObject;


/**
 * TODO <一句话功能描述>
 * 
 * @FileName com.wole56.ishow.service.TaskCallBack.java
 * @author zhangbl
 * @date 2014年11月13日 下午2:45:28
 * @version V1.0 <描述当前版本功能>
 */
public interface TaskCallBack {

	void onPreExecute();
	void onPostExecute(JSONObject jsonObject);
	void onError( Exception result);
}
