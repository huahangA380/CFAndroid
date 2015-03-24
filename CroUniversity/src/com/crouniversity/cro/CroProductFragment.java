package com.crouniversity.cro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.crouniversity.fab.FloatingActionButton;
import com.crouniversity.fab.ShowHideOnScroll;
import com.crouniversity.main.MainActivity;
import com.example.crouniversity.R;

public class CroProductFragment extends Fragment {
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

		View v = inflater.inflate(R.layout.fragment_cromain, container, false);
		final FloatingActionButton fab = (FloatingActionButton) v
				.findViewById(R.id.fab);
		fab.setColor(getResources().getColor(android.R.color.holo_blue_light));
		// 设置FloatButton背景色

		// NOTE invoke this method after setting new values!
		fab.initBackground();
		// NOTE standard method of ImageView
		fab.setImageResource(R.drawable.ic_launcher);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),
						com.crouniversity.userinfo.UserInfoMainActivity.class));
				// fab.setImageResource(R.drawable.ic_launcher);//
				// 设置FloatButtonImage

			}
		});
		CroMainAdapter adapter = new CroMainAdapter(getActivity());
		ListView lv = (ListView) v.findViewById(R.id.lv);

		lv.setAdapter(adapter);
		lv.setOnTouchListener(new ShowHideOnScroll(fab,
				R.anim.floating_action_button_show,
				R.anim.floating_action_button_hide));// 滑动lvFloatingButton消失
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), CroDetailActivity.class));
			}
		});

		return v;
	}

	public CroProductFragment newInstance(int sectionNumber) {
		CroProductFragment fragment = new CroProductFragment();
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
