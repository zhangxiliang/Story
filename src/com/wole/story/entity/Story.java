package com.wole.story.entity;

import java.io.Serializable;

import com.avos.avoscloud.AVObject;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Story")
public class Story extends AVObject implements Serializable {
	private static final long serialVersionUID = 1L;
	@DatabaseField
	private String Author;
	@DatabaseField
	private String content;
	@DatabaseField
	private String date;
	@DatabaseField
	private String title;
	@DatabaseField(id=true)
	private String url;
	@DatabaseField
	private boolean isReaded;
	@DatabaseField
	private int viewCount;

	public String getAuthor() {
		return this.Author;
	}

	public String getContent() {
		return this.content;
	}

	public String getDate() {
		return this.date;
	}

	public String getTitle() {
		return this.title;
	}

	public String getUrl() {
		return this.url;
	}

	public int getViewCount() {
		return this.viewCount;
	}

	public void setAuthor(String paramString) {
		this.Author = paramString;
	}

	public void setContent(String paramString) {
		this.content = paramString;
	}

	public void setDate(String paramString) {
		this.date = paramString;
	}

	public void setTitle(String paramString) {
		this.title = paramString;
	}

	public void setUrl(String paramString) {
		this.url = paramString;
	}

	public void setViewCount(int paramInt) {
		this.viewCount = paramInt;
	}

	public boolean isReaded() {
		return isReaded;
	}

	public void setReaded(boolean isReaded) {
		this.isReaded = isReaded;
	}

	public String toString() {
		return "Story [title=" + this.title + ", Author=" + this.Author + ", date=" + this.date + ", url=" + this.url + "]";
	}
}