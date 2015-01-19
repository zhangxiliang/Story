package com.wole.story.app;


import android.app.Application;

public class StoryApplication extends Application{
	private static StoryApplication instance;

	public static StoryApplication getInstance() {
		return instance;
	}
}
