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

	private int currentversioncode;// ��ǰ�汾��
	private SharedPreferences preferences;
	private int count;// ��һ�汾��

	@SuppressLint({ "SetJavaScriptEnabled", "InflateParams" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);// ���뵭��Ч��
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
	 * ��ȡ�汾��(�ڲ�ʶ���)
	 * 
	 * @param context
	 * @return �汾��
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
						Thread.sleep(3000);// ����3����
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
					"����������ʧ�ܣ�����������Ժ�����",
					getLayoutInflater().inflate(R.layout.toast_cutsomeview,
							null));
			new Thread() {
				public void run() {
					try {
						Thread.sleep(3000);// ����3����
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
