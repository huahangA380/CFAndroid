package com.crouniversity.crowdfunding;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.crouniversity.main.MainActivity;
import com.example.crouniversity.R;

public class CroMainFragment extends Fragment {
	// private List<Map<String, Object>> data;
	private static final String ARG_SECTION_NUMBER = "section_number";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-g enerated method stub
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// data = CroMainGetData.getData();
		View v = inflater.inflate(R.layout.fragment_cromain, container, false);

		CroMainAdapter adapter = new CroMainAdapter(getActivity());
		ListView lv = (ListView) v.findViewById(android.R.id.list);

		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), position + " ",
						Toast.LENGTH_SHORT).show();
			}
		});

		return v;
	}

	public  CroMainFragment newInstance(int sectionNumber) {
		CroMainFragment fragment = new CroMainFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	// class CroMainAdapter extends BaseAdapter {
	// private LayoutInflater mLayoutInflater;
	//
	// public CroMainAdapter(Context context) {
	// // TODO Auto-generated constructor stub
	// this.mLayoutInflater = LayoutInflater.from(context);
	// }
	//
	// @Override
	// public int getCount() {
	// // TODO Auto-generated method stub
	// return data.size();
	// }
	//
	// @Override
	// public Object getItem(int position) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public long getItemId(int position) {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public View getView(int position, View convertView, ViewGroup parent) {
	// // TODO Auto-generated method stub
	// CroMainItem item = new CroMainItem();
	// if (convertView == null) {
	// convertView = mLayoutInflater.inflate(
	// R.layout.layout_crowdfunding, parent, false);
	// item.img_cro_main = (ImageView) convertView
	// .findViewById(R.id.img_cro_main);
	// item.tv_complete = (TextView) convertView
	// .findViewById(R.id.tv_main_completed);
	// item.tv_fund = (TextView) convertView
	// .findViewById(R.id.tv_main_fund);
	// item.tv_main_remaindays = (TextView) convertView
	// .findViewById(R.id.tv_main_remaindays);
	// convertView.setTag(item);
	// } else {
	// item = (CroMainItem) convertView.getTag();
	// }
	// item.img_cro_main.setBackgroundResource((Integer) data
	// .get(position).get("img_cro_main"));
	// // item.pro.setProgress((Integer) data.get(position).get(
	// // "pro_cro_completed"));
	// item.tv_complete.setText((CharSequence) data.get(position).get(
	// "tv_main_completed"));
	// item.tv_fund.setText((CharSequence) data.get(position).get(
	// "tv_main_fund"));
	// item.tv_main_remaindays.setText((CharSequence) data.get(position)
	// .get("tv_main_remaindays"));
	// return convertView;
	// }
	// }

}
