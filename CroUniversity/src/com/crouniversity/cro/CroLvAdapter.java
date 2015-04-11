package com.crouniversity.cro;

import java.util.ArrayList;
import java.util.HashMap;

import com.crouniversity.utils.GetPicture;
import com.example.crouniversity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CroLvAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<HashMap<String, Object>> listItem;
	private Context context;

	public CroLvAdapter(Context context,
			ArrayList<HashMap<String, Object>> listItem) {
		this.listItem = listItem;
		this.mInflater = LayoutInflater.from(context);
		this.context = context;
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

		CroViewHolder holder = null;

		if (convertView == null) {
			holder = new CroViewHolder();
			convertView = mInflater.inflate(R.layout.layout_cro_main_item,
					parent, false);
			holder.img_cro_main = (ImageView) convertView
					.findViewById(R.id.img_cro_main);
			holder.pro = (ProgressBar) convertView
					.findViewById(R.id.pro_cro_completed);
			holder.tv_complete = (TextView) convertView
					.findViewById(R.id.tv_main_completed);
			holder.tv_fund = (TextView) convertView
					.findViewById(R.id.tv_main_fund);
			holder.tv_main_remaindays = (TextView) convertView
					.findViewById(R.id.tv_main_remaindays);
			convertView.setTag(holder);

		} else {

			holder = (CroViewHolder) convertView.getTag();
		}
		holder.img_cro_main.setImageBitmap(GetPicture.getPic(((String) listItem
				.get(position).get("cro_img_main")), context));
		String s = (String) listItem.get(position).get("cro_pro_main");
		int i = Integer.parseInt(s);
		holder.pro.setProgress(i);
		holder.tv_complete.setText((String) listItem.get(position).get(
				"cro_tv_completed"));
		holder.tv_fund.setText((String) listItem.get(position).get(
				"cro_tv_fund"));
		holder.tv_main_remaindays.setText((String) listItem.get(position).get(
				"cro_tv_remaindays"));

		return convertView;
	}
}
