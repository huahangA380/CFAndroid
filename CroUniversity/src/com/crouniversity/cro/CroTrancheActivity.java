package com.crouniversity.cro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.crouniversity.main.BaseActivity;
import com.example.crouniversity.R;

public class CroTrancheActivity extends BaseActivity implements OnClickListener {
	private View layout01;
	private View layout02;
	private View layout03;
	private View layout04;
	private View layout05;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cro_tranche);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		layout01 = this.findViewById(R.id.tranche01);
		layout02 = this.findViewById(R.id.tranche02);
		layout03 = this.findViewById(R.id.tranche03);
		layout04 = this.findViewById(R.id.tranche04);
		layout05 = this.findViewById(R.id.tranche05);
		layout01.setOnClickListener(this);
		layout02.setOnClickListener(this);
		layout03.setOnClickListener(this);
		layout04.setOnClickListener(this);
		layout05.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tranche01:
			startActivity(new Intent(getApplicationContext(),
					CroPayActivity.class));
			break;
		case R.id.tranche02:
			startActivity(new Intent(getApplicationContext(),
					CroPayActivity.class));
			break;
		case R.id.tranche03:
			startActivity(new Intent(getApplicationContext(),
					CroPayActivity.class));
			break;
		case R.id.tranche04:
			startActivity(new Intent(getApplicationContext(),
					CroPayActivity.class));
		case R.id.tranche05:
			startActivity(new Intent(getApplicationContext(),
					CroPayActivity.class));
			break;

		default:
			break;
		}
	}
}
