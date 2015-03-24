package com.crouniversity.userinfo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.crouniversity.fab.FloatingActionButton;
import com.crouniversity.main.BaseActivity;
import com.example.crouniversity.R;

public class UserInfoMainActivity extends BaseActivity {
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userinfomain);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("个人中心");

		FloatingActionButton floating = (FloatingActionButton) findViewById(R.id.fab);
		floating.initBackground();
		floating.setImageResource(R.drawable.ic_launcher);
	}

}
