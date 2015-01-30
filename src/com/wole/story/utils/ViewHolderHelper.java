package com.wole.story.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * 
 * 获取view
 * 
 * @FileName com.wole56.ishow.utils.ViewHolderHelper.java
 * @author zhangbl
 * @date 2014年11月3日 上午11:34:28
 * @version V1.0 <描述当前版本功能>
 */
public class ViewHolderHelper {

	@SuppressWarnings("unchecked")
	public static <T extends View> T get(View view, int id) {
		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
		if (viewHolder == null) {
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}
		View childView = viewHolder.get(id);
		if (childView == null) {
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
		}
		return (T) childView;
	}
}
