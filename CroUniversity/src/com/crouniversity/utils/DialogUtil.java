package com.crouniversity.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Window;
import android.view.WindowManager;

public class DialogUtil {
	private static ProgressDialog progressDialog;

	public static void showProgressDialog(Activity activity, String message) {
		progressDialog = new ProgressDialog(activity);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage(message);
		progressDialog.setCancelable(true);
		Window window = progressDialog.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.alpha = 0.6f;
		window.setAttributes(lp);
		progressDialog.show();
	}
}
