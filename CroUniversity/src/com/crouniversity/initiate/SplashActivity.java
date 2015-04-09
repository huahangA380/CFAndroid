package com.crouniversity.initiate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.crouniversity.utils.NetWorkContent;
import com.crouniversity.utils.ToastUtil;
import com.example.crouniversity.R;

public class SplashActivity extends Activity {
	private ImageView img_content;

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
		img_content = (ImageView) findViewById(R.id.img_splash_content);
		img_content.setBackgroundResource(R.drawable.splash_list);
		final AnimationDrawable animationDrawable = (AnimationDrawable) img_content
				.getBackground();
		img_content.post(new Runnable() {
			@Override
			public void run() {
				animationDrawable.start();
			}
		});
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

}
