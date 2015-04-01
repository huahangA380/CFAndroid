package com.crouniversity.userinfo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.crouniversity.main.BaseActivity;
import com.crouniversity.roundimg.RoundImageView;
import com.example.crouniversity.R;

public class UserInfoMainActivity extends BaseActivity implements
		OnClickListener {
	private RoundImageView user_avatar;
	private TextView tv_camera;
	private TextView tv_photo;
	private Dialog d;
	private static final int CAMEAEA_CODE = 1;
	private static final int PHOTO_CODE = 2;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userinfomain);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("个人中心");
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.sigin_button));
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		user_avatar = (RoundImageView) this.findViewById(R.id.user_avatar);

		user_avatar.setOnClickListener(this);
		d = new Dialog(this);
		d.setTitle("更换头像");
		d.setContentView(R.layout.dialog_avatar);
		tv_camera = (TextView) d.findViewById(R.id.tv_camera);
		tv_photo = (TextView) d.findViewById(R.id.tv_photo);
		tv_camera.setOnClickListener(this);
		tv_photo.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.user_avatar:
			d.show();
			break;
		case R.id.tv_camera:

			startActivityForResult(new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE),
					CAMEAEA_CODE);

			break;
		case R.id.tv_photo:

			startActivityForResult(
					new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
					PHOTO_CODE);

			break;
		default:
			d.dismiss();
			break;

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case CAMEAEA_CODE:
			if (resultCode == RESULT_OK) {
				Bundle b = data.getExtras();
				Bitmap bitmap = (Bitmap) b.get("data");
				user_avatar.setImageBitmap(bitmap);
			}

			break;

		case PHOTO_CODE:
			if (resultCode == RESULT_OK) {
				Uri uri = data.getData();
				user_avatar.setImageURI(uri);
			}

			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		d.dismiss();
	}
}
