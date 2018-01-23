package cn.xxf.mvvm.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by ybzhang on 2016/7/1 0001.
 */
public class Util {

	private static final int RADIX = 10 + 26; // 10 digits + 26 letters

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int getStatebarHeight(Activity ac) {
		int x = 0, sbar = 0;

		Rect frame = new Rect();
		((Activity) ac).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		sbar = frame.top;
		if (sbar == 0) {
			Class<?> c = null;
			Object obj = null;
			Field field = null;
			try {
				c = Class.forName("com.android.internal.R$dimen");
				obj = c.newInstance();
				field = c.getField("status_bar_height");
				x = Integer.parseInt(field.get(obj).toString());
				sbar = ac.getResources().getDimensionPixelSize(x);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return sbar;
	}

	public static int getNavigationBarHeight(Context context) {
		int result = 0;
		if (hasNavBar(context)) {
			Resources res = context.getResources();
			int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
			if (resourceId > 0) {
				result = res.getDimensionPixelSize(resourceId);
			}
		}
		return result;
	}


	@SuppressLint("MissingPermission")
	public static String getDeviceId(Context context) {
		try {
			final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			return tm.getDeviceId();
		}catch (Exception e){
			return "";
		}
	}

	public static String generateMD5(String str) {
		String reStr = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(str.getBytes());
			StringBuffer stringBuffer = new StringBuffer();
			for (byte b : bytes){
				int bt = b&0xff;
				if (bt < 16){
					stringBuffer.append(0);
				}
				stringBuffer.append(Integer.toHexString(bt));
			}
			reStr = stringBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return reStr;
	}

	public static void showShortToast(Context context,String msg){
		Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
	}

	public static void showLongToast(Context context, String msg){
		Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
	}

	public static void showLongToast(Context context, int resId){
		Toast.makeText(context,resId,Toast.LENGTH_LONG).show();
	}

	public static void showShortToast(Context context, int resId){
		Toast.makeText(context,resId,Toast.LENGTH_SHORT).show();
	}

	public static boolean isEmpty(List list){
		return list == null || list.isEmpty();
	}

	public static void hideSoftKeyboard(Activity context) {

		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (context.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
			if (context.getCurrentFocus() != null)
				imm.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	public static void hideKeyboard(Context context, View view){
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	public static void showKeyboard(Context context, EditText view){
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);
	}

	public static void showOrHiddenKeyboardInDialog(Context context){
		InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public static Object deepClone(Serializable object) throws IOException, ClassNotFoundException {
		//将对象写到流里
		ByteArrayOutputStream bo=new ByteArrayOutputStream();
		ObjectOutputStream oo=new ObjectOutputStream(bo);
		oo.writeObject(object);
		//从流里读出来
		ByteArrayInputStream bi=new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream oi=new ObjectInputStream(bi);
		return (oi.readObject());
	}

	public static boolean isAppRunning(Context context,String packetName) {
		boolean result = false;
		ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
		for (ActivityManager.RunningTaskInfo info : list) {
			if (info.topActivity.getPackageName().equalsIgnoreCase(packetName) && info.baseActivity.getPackageName().equalsIgnoreCase(packetName)) {
				result = true;
				break;
			}
		}
		return  result;
	}

	public static boolean isAppRunningForeground(Context context) {
		ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);

		try {
			List list = am.getRunningTasks(1);
			if(list != null && list.size() >= 1) {
				boolean result = context.getPackageName().equalsIgnoreCase(((ActivityManager.RunningTaskInfo)list.get(0)).baseActivity.getPackageName());
				Log.d("utils", "app running in foregroud：" + result);
				return result;
			} else {
				return false;
			}
		} catch (SecurityException e) {
			Log.d("EasyUtils", "Apk doesn\'t hold GET_TASKS permission");
			e.printStackTrace();
			return false;
		}
	}

	public static final int NET_TYPE_NONE = 0;
	public static final int NET_TYPE_WIFI = 1;
	public static final int NET_TYPE_MOBILE = 2;

	/**
	 * 检查网络状况
	 *
	 * @return state:0-无网，1-wifi,2-mobile.
	 */
	@SuppressLint("MissingPermission")
	public static int getNetWorkState(Context context) {
		if (context == null) {
			return NET_TYPE_NONE;
		}
		int state = NET_TYPE_NONE;
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		if (wifiNetInfo != null) {
			Log.d("Util", "wifiNetInfo.............. state=" + wifiNetInfo.getState());
			if (wifiNetInfo.getState() == NetworkInfo.State.CONNECTED) {
				state = NET_TYPE_WIFI;
			} else {
				state = NET_TYPE_NONE;
			}
		}
		if (mobNetInfo != null && state == 0) {
			Log.d("Util", "mobNetInfo.............. state=" + mobNetInfo.getState());
			if (mobNetInfo.getState() == NetworkInfo.State.CONNECTED) {
				state = NET_TYPE_MOBILE;
			} else {
				state = NET_TYPE_NONE;
			}
		}
		return state;
	}

	public static final int PHONE_VERSION_OTHER = -1;
	public static final int PHONE_VERSION_XIAOMI = 1;
	public static final int PHONE_VERSION_MEIZU = 2;
	public static final int PHONE_VERSION_HUAWEI = 3;

	public static void goToPermission(Context context){
		int type = getPhoneVersion();
		String packageName = context.getPackageName();
		switch (type){
			case PHONE_VERSION_XIAOMI://小米
				try {
					Intent xiaomiIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
					xiaomiIntent.setClassName("com.miui.securitycenter","com.miui.permcenter.permissions.AppPermissionsEditorActivity");
					xiaomiIntent.putExtra("extra_pkgname", packageName);
					context.startActivity(xiaomiIntent);
				} catch (Exception e) {
					e.printStackTrace();
					goToSetting(context,packageName);
				}
				break;
			case PHONE_VERSION_MEIZU://魅族
				Intent meizuIntent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
				meizuIntent.addCategory(Intent.CATEGORY_DEFAULT);
				meizuIntent.putExtra("packageName", packageName);
				try {
					context.startActivity(meizuIntent);
				} catch (Exception e) {
					e.printStackTrace();
					goToSetting(context,packageName);
				}
				break;
			case PHONE_VERSION_HUAWEI:
				try {
					Intent huaweiIntent = new Intent();
					huaweiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
					huaweiIntent.setComponent(comp);
					context.startActivity(huaweiIntent);
				} catch (Exception e) {
					e.printStackTrace();
					goToSetting(context,packageName);
				}
				break;
			default:
				goToSetting(context,packageName);
				break;
		}
	}

	private static void goToSetting(Context context,String packageName){
		Intent defaultIntent = new Intent();
		defaultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if (Build.VERSION.SDK_INT >= 9) {
			defaultIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
			defaultIntent.setData(Uri.fromParts("package", packageName, null));
		} else if (Build.VERSION.SDK_INT <= 8) {
			defaultIntent.setAction(Intent.ACTION_VIEW);
			defaultIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
			defaultIntent.putExtra("com.android.settings.ApplicationPkgName", packageName);
		}
		context.startActivity(defaultIntent);
	}

	public static int getPhoneVersion(){
		int type = PreferenceUtil.getInt(PreferenceUtil.KEY_PHONE_VERSION);
		if(type != 0){
			return type;
		}

		type = getPhoneVersionGet();
		PreferenceUtil.setValue(PreferenceUtil.KEY_PHONE_VERSION,type);
		return type;
	}

	private static int getPhoneVersionGet(){
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
			if(properties.getProperty("ro.miui.ui.version.code", null) != null
					|| properties.getProperty("ro.miui.ui.version.code", null) != null
					|| properties.getProperty("ro.miui.internal.storage", null) != null){
				return PHONE_VERSION_XIAOMI;
			}

			if(properties.containsKey("ro.build.hw_emui_api_level")){
				return PHONE_VERSION_HUAWEI;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Method method = Build.class.getMethod("hasSmartBar");
			if(method != null){
				return PHONE_VERSION_MEIZU;
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return PHONE_VERSION_OTHER;
	}

	public static boolean ExistSDCard() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getDataPath(Context context){
		String pathName = File.separator+context.getPackageName()+File.separator;
		if(ExistSDCard()){
			return Environment.getExternalStorageDirectory().getAbsolutePath()+pathName;
		}else{
			return Environment.getDownloadCacheDirectory().getAbsolutePath()+pathName;
		}
	}

	public static void setEditText(String text, EditText mInput) {
		mInput.setText(text);
		mInput.setSelection(mInput.getText().length());
	}

	public static boolean hasNavBar(Context context) {
		boolean hasNavigationBar = false;
		Resources rs = context.getResources();
		int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
		if (id > 0) {
			hasNavigationBar = rs.getBoolean(id);
		}
		try {
			Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
			Method m = systemPropertiesClass.getMethod("get", String.class);
			String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
			if ("1".equals(navBarOverride)) {
				hasNavigationBar = false;
			} else if ("0".equals(navBarOverride)) {
				hasNavigationBar = true;
			}
		} catch (Exception e) {

		}
		return hasNavigationBar;
	}




	public static boolean openUrlWithBrower(Context context,String url) {
		if(!TextUtils.isEmpty(url) && (url.toLowerCase().startsWith("http://") || url.toLowerCase().startsWith("https://"))){
			Intent intent = new Intent();
			intent.setAction("android.intent.action.VIEW");
			Uri content_url = Uri.parse(url);
			intent.setData(content_url);
			context.startActivity(intent);
			return true;
		}else{
			return false;
		}
	}

	public static void clipboardText(Context context,String content){
		//获取剪贴板管理器：
		ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		// 创建普通字符型ClipData
		ClipData mClipData = ClipData.newPlainText("Label", content);
		// 将ClipData内容放到系统剪贴板里。
		cm.setPrimaryClip(mClipData);
	}

	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true;
	}

	/**
	 * 手机 DES 加密
	 *
	 * @param desKey 手机号Mの值
	 * @param data
	 * @return
	 */
	public static String encryptBasedDes(String desKey, String data) {
		String encryptedData = null;
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(desKey.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			// 加密对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			// 加密，并把字节数组编码成字符串
			//encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()))
			encryptedData = new String(Base64.encode(cipher.doFinal(data.getBytes()),0));
			encryptedData = URLEncoder.encode(encryptedData, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedData;
	}


	public static String getTopActivityName(Context context) {
		ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);

		try {
			List list = am.getRunningTasks(1);
			return list != null && list.size() >= 1?((ActivityManager.RunningTaskInfo)list.get(0)).topActivity.getClassName():"";
		} catch (SecurityException e) {
			Log.d("EasyUtils", "Apk doesn\'t hold GET_TASKS permission");
			e.printStackTrace();
			return "";
		}
	}


}
