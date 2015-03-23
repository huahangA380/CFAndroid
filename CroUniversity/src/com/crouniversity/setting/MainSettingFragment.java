package com.crouniversity.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crouniversity.main.MainActivity;
import com.example.crouniversity.R;

public class MainSettingFragment extends Fragment {
	private static final String ARG_SECTION_NUMBER = "section_number";

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-g enerated method stub
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_setting_main, container,
				false);
		TextView tv_accountset = (TextView) v.findViewById(R.id.tv_accountset);
		tv_accountset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), AccountActivity.class));
			}
		});
		return v;
	}

	public MainSettingFragment newInstance(int section) {
		MainSettingFragment fragment = new MainSettingFragment();

		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, section);
		fragment.setArguments(args);
		return fragment;
	}
}
