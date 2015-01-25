package com.wole.story.utils;

import android.util.Log;

public class Logs {

	public static void debug(String msg) {
		Log.d("Story", msg);
	}

	public static void info(String msg) {
		Log.i("Story", msg);
	}

	public static void error(String msg) {
		Log.e("Story", msg);
	}
}
