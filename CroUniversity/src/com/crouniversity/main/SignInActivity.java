package com.crouniversity.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.crouniversity.utils.DialogUtil;
import com.crouniversity.utils.ToastUtil;
import com.example.crouniversity.R;

public class SignInActivity extends Activity implements OnClickListener {
	private EditText edt_email;
	private EditText edt_pwd;
	private Button btn_sign_in;
	private Button btn_sign_up;

	// private Button btn_sigin_qq_up;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		initView();
	}

	private void initView() {
		edt_email = (EditText) this.findViewById(R.id.edt_email);
		edt_pwd = (EditText) this.findViewById(R.id.edt_pwd);
		btn_sign_in = (Button) this.findViewById(R.id.btn_sign_in);
		btn_sign_up = (Button) this.findViewById(R.id.btn_sign_up);
		btn_sign_in.setOnClickListener(this);
		btn_sign_up.setOnClickListener(this);

	}

	@SuppressLint("InflateParams")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_sign_in:

			if (edt_email.getText().toString().trim().equals("")
					|| edt_pwd.getText().toString().trim().equals("")) {
				ToastUtil.showToast(
						getApplicationContext(),
						"«Î ‰»Î√‹¬Î∫Õ” œ‰",
						getLayoutInflater().inflate(R.layout.toast_cutsomeview,
								null));
			} else {
				DialogUtil.showProgressDialog(this, "‘ÿ»Î÷–... «Î…‘µ»");
				startActivity(new Intent(getApplicationContext(),
						com.crouniversity.main.MainActivity.class));
				SignInActivity.this.finish();
			}

			break;

		case R.id.btn_sign_up:
			startActivity(new Intent(getApplicationContext(),
					com.crouniversity.main.SignUpActivity.class));
			SignInActivity.this.finish();
			break;
		}
	}
}
