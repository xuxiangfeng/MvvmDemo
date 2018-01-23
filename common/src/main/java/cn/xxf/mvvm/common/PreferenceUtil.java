package cn.xxf.mvvm.common;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 *
 * @author ybzhang
 *
 * 2015-8-14
 */
public class PreferenceUtil {

	private static final String PREFENCE_FILE_NAME = "xxf";
	public static final String KEY_PHONE_VERSION = "key.phone.version";


	private static Application APPLICATION  = null;
	
	public static void init(Application app){
		APPLICATION = app;
	}
	
	public static void setValue(String key,long lValue){
		if(key == null || APPLICATION == null)
			return;
		 SharedPreferences preferences = APPLICATION.getSharedPreferences(PREFENCE_FILE_NAME, Context.MODE_PRIVATE);
		 Editor editor = preferences.edit();
		 editor.putLong(key, lValue);
		 editor.commit();
	}

	public static void setValue(String key,int iValue){
		if(key == null || APPLICATION == null)
			return;
		SharedPreferences preferences = APPLICATION.getSharedPreferences(PREFENCE_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt(key, iValue);
		editor.commit();
	}

	public static void setValue(String key,String sValue){
		if(key == null || APPLICATION == null)
			return;
		SharedPreferences preferences = APPLICATION.getSharedPreferences(PREFENCE_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(key, sValue);
		editor.commit();
	}

	public static long getLong(String key){
		if(key == null || APPLICATION == null)
			return 0;
		 SharedPreferences preferences = APPLICATION.getSharedPreferences(PREFENCE_FILE_NAME, Context.MODE_PRIVATE);
		return  preferences.getLong(key, 0);
	}

	public static int getInt(String key){
		if(key == null || APPLICATION == null)
			return 0;
		SharedPreferences preferences = APPLICATION.getSharedPreferences(PREFENCE_FILE_NAME, Context.MODE_PRIVATE);
		return  preferences.getInt(key, 0);
	}

	public static String getString(String key){
		if(key == null || APPLICATION == null)
			return null;
		SharedPreferences preferences = APPLICATION.getSharedPreferences(PREFENCE_FILE_NAME, Context.MODE_PRIVATE);
		return  preferences.getString(key,null);
	}

	public static String getString(String key,String defaultValue){
		if(key == null || APPLICATION == null)
			return null;
		SharedPreferences preferences = APPLICATION.getSharedPreferences(PREFENCE_FILE_NAME, Context.MODE_PRIVATE);
		return  preferences.getString(key,defaultValue);
	}


	public static boolean getBoolean(String key){
		return getBoolean(key,true);
	}

	public static boolean getBoolean(String key,boolean defaultValue){
		if(key == null || APPLICATION == null)
			return false;
		SharedPreferences preferences = APPLICATION.getSharedPreferences(PREFENCE_FILE_NAME, Context.MODE_PRIVATE);
		return  preferences.getBoolean(key, defaultValue);
	}
	
	public static void setBoolean(String key,boolean bValue){
		if(key == null || APPLICATION == null)
			return;
		 SharedPreferences preferences = APPLICATION.getSharedPreferences(PREFENCE_FILE_NAME, Context.MODE_PRIVATE);
		 Editor editor = preferences.edit();
		 editor.putBoolean(key, bValue);
		 editor.commit();
	}
}
