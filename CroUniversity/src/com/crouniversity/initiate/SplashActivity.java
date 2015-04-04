package com.crouniversity.initiate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.crouniversity.utils.NetWorkContent;
import com.crouniversity.utils.ToastUtil;
import com.example.crouniversity.R;

public class SplashActivity extends Activity {
	private WebView web_content;
	private final String URL_LOCAL = "file:///android_asset/index.html";
	private final String URL_INTENT = "http://weibo.com";
	private int currentversioncode;// 当前版本号
	private SharedPreferences preferences;
	private int count;// 上一版本号

	@SuppressLint({ "SetJavaScriptEnabled", "InflateParams" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);// 淡入淡出效果
		web_content = (WebView) findViewById(R.id.web_splash_content);
		web_content.setWebViewClient(new MyWebViewClient());
		web_content.loadUrl(URL_INTENT);
		WebSettings webSetting = web_content.getSettings();
		webSetting.setJavaScriptEnabled(true);
		webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		currentversioncode = getVersionCode(getApplicationContext());
		startMainActivity();
		readShare();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		writeShare();
	}

	/**
	 * 获取版本号(内部识别号)
	 * 
	 * @param context
	 * @return 版本号
	 */
	public static int getVersionCode(Context context) {
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			return pi.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public void writeShare() {
		preferences = getSharedPreferences("versionCode", MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt("versionCode", currentversioncode);
		editor.commit();
	}

	public void readShare() {
		preferences = getSharedPreferences("versionCode", MODE_PRIVATE);
		count = preferences.getInt("versionCode", 2);
	}

	@SuppressLint("InflateParams")
	public void startMainActivity() {
		if (NetWorkContent.isNetworkConnected(getApplicationContext())) {
			new Thread() {
				public void run() {
					try {
						Thread.sleep(3000);// 休眠3秒钟
						if (count == currentversioncode) {
							Intent intent = new Intent(
									com.crouniversity.initiate.SplashActivity.this,
									com.crouniversity.main.MainActivity.class);
							startActivity(intent);
						} else {

							Intent intent = new Intent(
									com.crouniversity.initiate.SplashActivity.this,
									com.crouniversity.initiate.GuideActivity.class);
							startActivity(intent);
						}
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}

					SplashActivity.this.finish();
				};
			}.start();
		} else {
			ToastUtil.showToast(
					getApplicationContext(),
					"服务器连接失败，请检查网络或稍后重试",
					getLayoutInflater().inflate(R.layout.toast_cutsomeview,
							null));
			new Thread() {
				public void run() {
					try {
						Thread.sleep(3000);// 休眠3秒钟
						if (count == currentversioncode) {
							Intent intent = new Intent(
									com.crouniversity.initiate.SplashActivity.this,
									com.crouniversity.main.MainActivity.class);
							startActivity(intent);

						} else {
							Intent intent = new Intent(
									com.crouniversity.initiate.SplashActivity.this,
									com.crouniversity.initiate.GuideActivity.class);
							startActivity(intent);
						}

					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					SplashActivity.this.finish();
				};
			}.start();
		}
	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			// TODO Auto-generated method stub
			view.loadUrl(URL_LOCAL);
		}
	}

}
