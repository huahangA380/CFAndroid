package com.crouniversity.sns;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.crouniversity.R;

public class SnsStudyMainAdapter extends BaseAdapter {
	private List<HashMap<String, Object>> data;
	private LayoutInflater layoutInflater;
	private SnsStudyMainViewHolder viewHolder;

	public SnsStudyMainAdapter(Context context) {
		// TODO Auto-generated constructor stub
		// this.mLayoutInflater = LayoutInflater.from(context);
		// data = CroMainGetData.getData();
		this.layoutInflater = LayoutInflater.from(context);
		data = SnsStudyMainGetData.getData(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		viewHolder = new SnsStudyMainViewHolder();
		if (convertView == null) {
			convertView = layoutInflater.inflate(
					R.layout.layout_studysns_main_item, parent, false);
			viewHolder.tv_studysns_title = (TextView) convertView
					.findViewById(R.id.tv_studysns_title);
			viewHolder.tv_studysns_conents = (TextView) convertView
					.findViewById(R.id.tv_studysns_conents);
			viewHolder.tv_var_man = (TextView) convertView
					.findViewById(R.id.tv_var_man);
			viewHolder.tv_var_time = (TextView) convertView
					.findViewById(R.id.tv_var_time);
			// viewHolder.ck_good = (CheckBox) convertView
			// .findViewById(R.id.ck_good);
			// viewHolder.ck_comment = (CheckBox) convertView
			// .findViewById(R.id.ck_comment);
			viewHolder.ck_comment_num = (TextView) convertView
					.findViewById(R.id.tv_comment_num);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (SnsStudyMainViewHolder) convertView.getTag();
		}
		viewHolder.tv_studysns_title.setText((CharSequence) data.get(position)
				.get("title"));
		viewHolder.tv_studysns_conents.setText((CharSequence) data
				.get(position).get("content"));
		viewHolder.tv_var_man.setText((CharSequence) data.get(position).get(
				"author"));
		viewHolder.tv_var_time.setText((CharSequence) data.get(position).get(
				"date"));
		viewHolder.ck_good
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							Log.i("**************", "до");
						} else {
							Log.i("******************", "ШЁЯћдо");
						}
					}

				});
		viewHolder.ck_comment_num.setText((CharSequence) data.get(position)
				.get("num"));
		return convertView;
	}
}
