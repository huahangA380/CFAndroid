package com.crouniversity.userinfo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.crouniversity.main.BaseActivity;
import com.example.crouniversity.R;

public class UserInfoMainActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userinfomain);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("��������");
	}

}
