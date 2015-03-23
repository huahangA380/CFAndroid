package com.crouniversity.cro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.crouniversity.R;

public class CroMainGetData {
	public static List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		//
		for (int i = 0; i < 100; i++) {
			map.put("img_cro_main", R.drawable.banner);
			// map.put("pro_cro_completed", 20);
			map.put("tv_main_completed", "20%");
			map.put("tv_main_fund", "2000ิช");
			map.put("tv_main_remaindays", "20ฬ์");
			list.add(map);
		}
		return list;
	}
}
