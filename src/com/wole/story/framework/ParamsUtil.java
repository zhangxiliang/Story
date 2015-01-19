package com.wole.story.framework;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


/**
 * TODO <一句话功能描述>
 * 
 * @FileName com.wole56.ishow.framework.ParamsUtil.java
 * @author zhangbl
 * @date 2014年11月13日 下午5:58:24
 * @version V1.0 <描述当前版本功能>
 */
public class ParamsUtil {

	private static ArrayList<String> combinParms(
			HashMap<String, String> paramsMap) {

		if (paramsMap == null) {
			paramsMap = new HashMap<String, String>();
		}
	/*	paramsMap.put(NetConfig.REQUEST_USER_HEX, getUserHex());
		paramsMap.put(NetConfig.REQUEST_CLIENT_INFO, ClientInfoUtils
				.getClientInfo(WoleApplication.getInstance()
						.getApplicationContext()));*/
		if (TextUtils.isEmpty(paramsMap.get(NetConfig.REQUEST_ACTION))) {
			paramsMap.put(NetConfig.REQUEST_ACTION, "Phone/Android");
		}
		ArrayList<String> paramValues = new ArrayList<String>();

		if (paramsMap != null && !paramsMap.isEmpty()) {
			Iterator<Map.Entry<String, String>> ite = paramsMap.entrySet()
					.iterator();
			while (ite.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) ite
						.next();
				String value = entry.getValue();
				if (!TextUtils.isEmpty(value)) {
					paramValues.add(value);
				}
			}
		}
		return paramValues;

	}

	
}
