package com.crouniversity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.crouniversity.main.BaseActivity;
import com.example.crouniversity.R;

public class AccountActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_account);
		ActionBar actionbar = getSupportActionBar();
		actionbar.setTitle("’À∫≈…Ë÷√");
		
		TextView tv_resetpwd = (TextView) findViewById(R.id.tv_resetpwd);
		tv_resetpwd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplication(),
						ResetPwdActivity.class));
			}
		});
	}

	
}
