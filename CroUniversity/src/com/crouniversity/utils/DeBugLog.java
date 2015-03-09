package com.crouniversity.utils;

import android.util.Log;
import android.util.LogPrinter;

public class DeBugLog extends LogPrinter {
	private static boolean DEBUG = true;

	public DeBugLog(int priority, String tag) {
		super(priority, tag);
		// TODO Auto-generated constructor stub
	}

	public static void logI(String tag, String msg) {
		if (DEBUG) {
			Log.i(tag, msg);
		}
	}

	public static void logW(String tag, String msg) {
		if (DEBUG) {
			Log.w(tag, msg);
		}
	}

	public static void logE(String tag, String msg) {
		if (DEBUG) {
			Log.e(tag, msg);
		}
	}
}
