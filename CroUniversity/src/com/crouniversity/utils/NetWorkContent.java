package com.crouniversity.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkContent {

	/**
	 * 判断网络
	 * 
	 * @return boolean 是否已联网
	 */
	public static boolean isNetworkConnected(Context context) {

		// TODO 自动生成的方法存根
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnected()) {
			return true;
		} else {
			return false;
		}
	}
}