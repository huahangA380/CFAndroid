package com.crouniversity.sns;

import android.os.Bundle;
import android.view.View;

import com.crouniversity.main.BaseActivity;
import com.example.crouniversity.R;

public class SnsDetailActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_studysns_detail);
		View layout = findViewById(R.id.commentArea);
		layout.setVisibility(View.GONE);
	}

}
