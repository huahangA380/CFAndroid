package com.crouniversity.sns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnsStudyMainGetData {
	public static List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < 100; i++) {
			map.put("tv_studysns_title", "�������");
			map.put("tv_studysns_conents", "�����ʵ������");
			map.put("tv_var_man", "������");
			map.put("tv_var_time", "2014.1.1");
			map.put("ck_good", "true");
			map.put("ck_comment", true);
			map.put("ck_comment_num", "1224");
			list.add(map);
		}
		return list;
	}
}
