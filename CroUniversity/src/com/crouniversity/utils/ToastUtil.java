package com.crouniversity.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crouniversity.R;

public class ToastUtil {
	private static TextView tv_toast;

	/**
	 * 普通Toast
	 * 
	 * @param context
	 * @param message
	 * @param toast_view
	 */
	public static void showToast(Context context, String message,
			View toast_view) {
		Toast toast = new Toast(context);
		toast.setView(toast_view);
		tv_toast = (TextView) toast_view.findViewById(R.id.tv_toastInfo);
		tv_toast.setText(message);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * 退出应用Toast
	 * 
	 * @param context
	 * @param message
	 * @param toast_view
	 */
	public static void showOutToast(Context context, String message,
			View toast_view) {
		Toast toast = new Toast(context);
		toast.setView(toast_view);
		tv_toast = (TextView) toast_view.findViewById(R.id.tv_toastInfo);
		tv_toast.setText(message);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
