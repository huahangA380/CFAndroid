package com.crouniversity.sns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;

import android.content.Context;

public class SnsStudyMainGetData {
	public static List<HashMap<String, Object>> getData(Context context) {
		// TODO Auto-generated method stub

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		InputStream inputStream;
		try {
			inputStream = context.getAssets().open("test.txt");
			String json = readTextFile(inputStream);
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

	public static String readTextFile(InputStream inputStream) {
		String readedStr = "";
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream, "gbk"));
			String tmp;
			while ((tmp = br.readLine()) != null) {
				readedStr += tmp;
			}
			br.close();
			inputStream.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return readedStr;
	}
}
