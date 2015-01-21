package com.wole.story.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




import com.wole.story.ui.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
/***
 * 
 * 通用工具类   
 * Created By Zhangxiliang
 * Date：2014年9月22日 
 * Version： 2.0   
 * Copyright (c) 2014 56.com Software corporation All Rights Reserved.     
 *
 */
public class CommonUtils {
	

	
	public static void toggleKeyBoard(Context context){
		 InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
         imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
	public static void hideInputMethod(Context context, View v) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm != null) {
			 imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
		}
	}

	
	public static void showInputMethod(Context context, View v) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm != null) {
			 imm.showSoftInput(v, 0);
		}
	}
	
	public static SpannableStringBuilder getSpanerBuilder(Context context,String text,int color){
		SpannableStringBuilder builder = new SpannableStringBuilder(text);
		builder.setSpan(new ForegroundColorSpan(context.getResources().getColor(color)), 0, builder.length(),
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return  builder;
	}
	public static String delHTMLTag(String htmlStr){
	    String regEx_script="<script[^>]*?>[//s//S]*?<///script>"; //定义script的正则表达式
	    String regEx_style="<style[^>]*?>[//s//S]*?<///style>"; //定义style的正则表达式
	    String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式
	    Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
	    Matcher m_script=p_script.matcher(htmlStr);
	    htmlStr=m_script.replaceAll(""); //过滤script标签
	    Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
	    Matcher m_style=p_style.matcher(htmlStr);
	    htmlStr=m_style.replaceAll(""); //过滤style标签
	    Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
	    Matcher m_html=p_html.matcher(htmlStr);
	    htmlStr=m_html.replaceAll(""); //过滤html标签
	    return htmlStr.trim(); //返回文本字符串
	}
	
	public static  int  getPhoneWidth(Context context){
		
		int width = ((Activity) context).getWindowManager().getDefaultDisplay()
				.getWidth();
		return width;
	}
	public static  int  getPhoneHeight(Context context){
		
		int height = ((Activity) context).getWindowManager().getDefaultDisplay()
				.getHeight();
		return height;
	}
	
	/**根据包名卸载应用*/
	public static void uninstallAPK(Context mContext,String packageName) {
		// TODO Auto-generated method stub
		// 通过程序的报名创建URI
		Uri packageURI = Uri.parse("package:" + packageName);
		// 创建Intent意图
		Intent intent = new Intent(Intent.ACTION_DELETE);
		intent.setData(packageURI);
		// 执行卸载程序
		mContext.startActivity(intent);
		
	}
	
	/**判断是否装有某应用*/
	public static boolean  isInstallSoftware(Context context, String packageName) {  
	    PackageManager packageManager = context.getPackageManager();  
	    try {  
	        PackageInfo pInfo = packageManager.getPackageInfo(packageName,  
	                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT); 
	        //判断是否获取到了对应的包名信息 
	        if(pInfo!=null){  
	            return true;
	        }  
	    } catch (NameNotFoundException e) {  
	        e.printStackTrace();  
	    }  
	    return false;
	} 
	/** 
     * 删除程序的快捷方式 
     */ 
	public static void delShortcut(Context mContext){  
        Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");  
               
        //快捷方式的名称  
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, mContext.getString(R.string.app_name));  
        String appClass = mContext.getPackageName() + "." +((Activity)mContext).getLocalClassName();  
        ComponentName comp = new ComponentName(mContext.getPackageName(), appClass);  
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));  
               
        mContext.sendBroadcast(shortcut);  
               
    }  
	
	public static  boolean hasShortcut(Context mContext)
	{
	        boolean isInstallShortcut = false;
	        final ContentResolver cr = mContext.getContentResolver();
	        final String AUTHORITY ="com.android.launcher.settings";
	        final Uri CONTENT_URI = Uri.parse("content://" +AUTHORITY + "/favorites?notify=true");
	        Cursor c = cr.query(CONTENT_URI,new String[] {"title","iconResource" },"title=?",
	        new String[] {mContext.getString(R.string.app_name).trim()}, null);
	        if(c!=null && c.getCount()>0){
	            isInstallShortcut = true ;
	        }
	        return isInstallShortcut ;
	}
	
	/** 
     * 为程序创建桌面快捷方式 
     */ 
	public static void addShortcut(Context mContext) {
		Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

		// 快捷方式的名称
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, mContext.getString(R.string.app_name));
		shortcut.putExtra("duplicate", false); // 不允许重复创建

		Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
		shortcutIntent.setClassName(mContext, mContext.getClass().getName());
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		// 快捷方式的图标
		ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(mContext, R.drawable.ic_launcher);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
		shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
		mContext.sendBroadcast(shortcut);
	}
	
	public static void statisApp(Context context,String tagName,String tagValue){
		//StatService.onEvent(context, tagName, tagValue, 1);
		//if(WoleConfig.OPENUMENG)
		//MobclickAgent.onEvent(context, tagName, tagName);
	}
	

	
	public static int getRandCseq(){
		Random random = new Random();
		int s = random.nextInt(100000) + 1;
		return s;
    }

}
