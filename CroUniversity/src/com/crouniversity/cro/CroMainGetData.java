package com.crouniversity.cro;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;

import android.content.Context;

import com.crouniversity.utils.ReadTextFile;

public class CroMainGetData {
	public static ArrayList<HashMap<String, Object>> getData(Context context) {
		// TODO Auto-generated method stub

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		InputStream inputStream;
		try {
			inputStream = context.getAssets().open("cro.txt");
			String json = ReadTextFile.readTextFile(inputStream);
			JSONArray array = new JSONArray(json);
			int n = array.length();
			for (int i = 0; i < n; i++) {
				map = new HashMap<String, Object>();
				map.put("cro_img_main",
						array.getJSONObject(i).getString("cro_img_main"));
				map.put("cro_pro_main",
						array.getJSONObject(i).getString("cro_pro_main"));
				map.put("cro_tv_completed",
						array.getJSONObject(i).getString("cro_tv_completed"));
				map.put("cro_tv_fund",
						array.getJSONObject(i).getString("cro_tv_fund"));
				map.put("cro_tv_remaindays",
						array.getJSONObject(i).getString("cro_tv_remaindays"));
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
