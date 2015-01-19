package com.wole.story.adapter;

import java.util.LinkedList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 
* 适配器基类
* @FileName com.wole56.ishow.adapter.AdapterBase.java  
* @author zhangbl   
* @date 2014年8月20日 下午2:20:25  
 */
public abstract class AdapterBase<T> extends BaseAdapter {
	
	protected List<T> mList = new LinkedList<T>();
	
	public List<T> getList(){
		return mList;
	}
	
	public void appendToList(List<T> list) {
		if (list == null) {
			return;
		}
		mList.addAll(list);
		notifyDataSetChanged();
	}

	public void appendEntity(T entity){
		if (entity == null) {
			return;
		}
		mList.add(entity);
		notifyDataSetChanged();
	}
	
	public void appendToTopList(List<T> list) {
		if (list == null) {
			return;
		}
		mList.addAll(0, list);
		notifyDataSetChanged();
	}
	
	public void refreshList(List<T> list) {
		mList.clear();
		appendToList(list);
	}

	public void clear() {
		mList.clear();
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		if(position > mList.size()-1){
			return null;
		}
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (position == getCount() - 1) {
			onReachBottom();
		}
		return getExView(position, convertView, parent);
	}
	

	protected abstract View getExView(int position, View convertView, ViewGroup parent);
	protected abstract void onReachBottom();


}
