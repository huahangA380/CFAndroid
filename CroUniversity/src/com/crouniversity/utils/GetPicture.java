package com.crouniversity.utils;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GetPicture {
	/**
	 * ����ͼƬ���ƻ�ȡ��ҳͼƬ
	 */
	public static Bitmap getPic(String photo, Context context) {
		String homeName = photo + ".jpg";
		InputStream is = null;

		try {
			is = context.getAssets().open("home/" + homeName);
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			is.close();
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}
