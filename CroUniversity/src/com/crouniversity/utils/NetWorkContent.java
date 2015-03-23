package com.crouniversity.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkContent extends Activity {

	/**
	 * �ж�����
	 * 
	 * @return boolean �Ƿ�������
	 */
	public static boolean isNetworkConnected(Context context) {

		// TODO �Զ����ɵķ������
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnected()) {
			return true;
		} else {
			return false;
		}
	}
}
