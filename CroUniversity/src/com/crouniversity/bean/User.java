package com.crouniversity.bean;

import android.widget.ImageView;

public class User {
	private String email;
	private String name;
	private ImageView avatar;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageView getAvatar() {
		return avatar;
	}

	public void setAvatar(ImageView avatar) {
		this.avatar = avatar;
	}
}
