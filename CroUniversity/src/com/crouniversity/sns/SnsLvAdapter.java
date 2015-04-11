package com.crouniversity.sns;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.crouniversity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SnsLvAdapter extends BaseAdapter {

	private ArrayList<HashMap<String, Object>> listItem;
	private LayoutInflater mInflater;

	public SnsLvAdapter(Context context,
			ArrayList<HashMap<String, Object>> listItem) {
		this.listItem = listItem;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItem.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		SnsViewHolder holder = null;
		if (convertView == null) {

			holder = new SnsViewHolder();

			convertView = mInflater.inflate(R.layout.layout_sns_main_item,
					parent, false);
			holder.title = (TextView) convertView
					.findViewById(R.id.tv_studysns_title);
			holder.content = (TextView) convertView
					.findViewById(R.id.tv_studysns_conents);
			holder.author = (TextView) convertView
					.findViewById(R.id.tv_var_man);
			holder.date = (TextView) convertView.findViewById(R.id.tv_var_time);
			holder.num = (TextView) convertView
					.findViewById(R.id.tv_comment_num);
			convertView.setTag(holder);

		} else {

			holder = (SnsViewHolder) convertView.getTag();
		}

		holder.title.setText((String) listItem.get(position).get("title"));
		holder.content.setText((String) listItem.get(position).get("content"));
		holder.author.setText((String) listItem.get(position).get("author"));
		holder.date.setText((String) listItem.get(position).get("date"));
		holder.num.setText((String) listItem.get(position).get("num"));
		return convertView;
	}

}
