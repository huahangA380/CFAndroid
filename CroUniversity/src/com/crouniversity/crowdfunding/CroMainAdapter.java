package com.crouniversity.crowdfunding;

import java.util.List;
import java.util.Map;

import com.example.crouniversity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CroMainAdapter extends BaseAdapter {
	private List<Map<String, Object>> data;
	private LayoutInflater mLayoutInflater;

	public CroMainAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.mLayoutInflater = LayoutInflater.from(context);
		data = CroMainGetData.getData();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		CroMainViewHolder viewHoder = new CroMainViewHolder();
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.layout_crowdfunding,
					parent, false);
			viewHoder.img_cro_main = (ImageView) convertView
					.findViewById(R.id.img_cro_main);
			viewHoder.tv_complete = (TextView) convertView
					.findViewById(R.id.tv_main_completed);
			viewHoder.tv_fund = (TextView) convertView
					.findViewById(R.id.tv_main_fund);
			viewHoder.tv_main_remaindays = (TextView) convertView
					.findViewById(R.id.tv_main_remaindays);
			convertView.setTag(viewHoder);
		} else {
			viewHoder = (CroMainViewHolder) convertView.getTag();
		}

		viewHoder.img_cro_main.setBackgroundResource((Integer) data.get(
				position).get("img_cro_main"));
		// item.pro.setProgress((Integer) data.get(position).get(
		// "pro_cro_completed"));
		viewHoder.tv_complete.setText((CharSequence) data.get(position).get(
				"tv_main_completed"));
		viewHoder.tv_fund.setText((CharSequence) data.get(position).get(
				"tv_main_fund"));
		viewHoder.tv_main_remaindays.setText((CharSequence) data.get(position)
				.get("tv_main_remaindays"));
		return convertView;
	}

}
