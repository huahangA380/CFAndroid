package com.crouniversity.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crouniversity.R;

public class SplashActivity extends Activity {
	private WebView web_content;
	private TextView tv_toast;
	private final String URL_LOCAL = "file:///android_asset/welcome.html";
	private final String URL_INTENT = "http://www.baidu.com";

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);// 淡入淡出效果
		web_content = (WebView) findViewById(R.id.web_content);
		web_content.setWebViewClient(new MyWebViewClient());
		web_content.loadUrl(URL_INTENT);
		WebSettings webSetting = web_content.getSettings();
		webSetting.setJavaScriptEnabled(true);
		webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
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

		@Override
		public void onReceivedSslError(WebView view, SslErrorHandler handler,
				SslError error) {
			// TODO Auto-generated method stub
			handler.proceed();
		}
	}

	private boolean isNetworkConnected() {

		// TODO 自动生成的方法存根
		ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnected()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void onStart() {
		// TODO 自动生成的方法存根
		super.onStart();
		if (isNetworkConnected()) {
			new Thread() {
				public void run() {
					try {
						Thread.sleep(3000);// 休眠两秒钟
						Intent intent = new Intent(
								com.crouniversity.main.SplashActivity.this,
								com.crouniversity.main.MainActivity.class);
						startActivity(intent);
						finish();
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				};
			}.start();
		} else {
			View toast_view = getLayoutInflater().inflate(
					R.layout.toast_cutsomeview,null);
			Toast toast = new Toast(getApplicationContext());
			toast.setView(toast_view);
			tv_toast = (TextView) toast_view.findViewById(R.id.tv_toastInfo);
			tv_toast.setText("无网络连接");
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.show();
			// Toast.makeText(getApplicationContext(), "无网络状态 影响交互体验",
			// Toast.LENGTH_LONG).show();
			new Thread() {
				public void run() {
					try {
						Thread.sleep(3000);// 休眠两秒钟
						Intent intent = new Intent(
								com.crouniversity.main.SplashActivity.this,
								com.crouniversity.main.MainActivity.class);
						startActivity(intent);
						finish();
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				};
			}.start();
		}
	}

}
