package com.crouniversity.userinfo;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Window;

import com.crouniversity.main.BaseActivity;
import com.example.crouniversity.R;

public class UserInfoMainActivity extends BaseActivity {
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userinfomain);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("个人中心");
		actionBar.setBackgroundDrawable(new ColorDrawable(
				android.R.color.transparent));
		// 设置半透明的底色
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.sigin_button));
	}

}
