package com.crouniversity.sign;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.crouniversity.main.BaseActivity;
import com.crouniversity.utils.ToastUtil;
import com.example.crouniversity.R;

public class SignUpActivity extends BaseActivity {
	private EditText edt_user_name;
	private EditText edt_email;
	private EditText edt_f_pwd;
	private EditText edt_s_pwd;
	private CheckBox cbox;
	private Button btn_signin;

	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		edt_user_name = (EditText) findViewById(R.id.edt_name);
		edt_email = (EditText) findViewById(R.id.edt_email);
		edt_f_pwd = (EditText) findViewById(R.id.edt_pwd);
		edt_s_pwd = (EditText) findViewById(R.id.edt_second_pwd);
		cbox = (CheckBox) findViewById(R.id.cbox_pro);
		btn_signin = (Button) this.findViewById(R.id.btn_sign_in);
		cbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					final boolean isChecked) {
				// TODO Auto-generated method stub
				btn_signin.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						boolean i;
						i = isNull();
						if (i) {
							if (edt_f_pwd
									.getText()
									.toString()
									.trim()
									.equals(edt_s_pwd.getText().toString()
											.trim())) {
								startActivity(new Intent(
										getApplicationContext(),
										com.crouniversity.main.MainActivity.class));

							} else {
								ToastUtil.showToast(
										getApplicationContext(),
										"密码不一致，请重新输入",
										getLayoutInflater().inflate(
												R.layout.toast_cutsomeview,
												null));

							}

						} else {
							ToastUtil.showToast(
									getApplicationContext(),
									"请将内容填写完整",
									getLayoutInflater().inflate(
											R.layout.toast_cutsomeview, null));
						}
					}
				});
			}
		});
	}

	/**
	 * 判断输入框是否为空
	 * 
	 * @return
	 */
	private boolean isNull() {
		// TODO Auto-generated method stub
		if (edt_user_name.getText().toString().trim().equals("")
				|| edt_email.getText().toString().trim().equals("")
				|| edt_f_pwd.getText().toString().trim().equals("")
				|| edt_s_pwd.getText().toString().trim().equals("")) {

			return false;
		} else
			return true;

	}
}
