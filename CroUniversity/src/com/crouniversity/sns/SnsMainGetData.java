package com.crouniversity.sns;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;

import android.content.Context;

import com.crouniversity.utils.ReadTextFile;

public class SnsMainGetData {
	public static ArrayList<HashMap<String, Object>> getData(Context context) {
		// TODO Auto-generated method stub

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		InputStream inputStream;
		try {
			inputStream = context.getAssets().open("test.txt");
			String json = ReadTextFile.readTextFile(inputStream);
			JSONArray array = new JSONArray(json);
			for (int i = 0; i < array.length(); i++) {
				map = new HashMap<String, Object>();
				map.put("title", array.getJSONObject(i).getString("title"));
				map.put("content", array.getJSONObject(i).getString("content"));
				map.put("author", array.getJSONObject(i).getString("author"));
				map.put("date", array.getJSONObject(i).getString("date"));
				map.put("num", array.getJSONObject(i).getString("num"));
				list.add(map);
			}
			return list;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;

	}

}
