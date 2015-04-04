package com.crouniversity.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.crouniversity.main.MainActivity;
import com.example.crouniversity.R;

public class MainSettingFragment extends Fragment implements OnClickListener {
	private static final String ARG_SECTION_NUMBER = "section_number";
	private TextView tv_accountset;
	private TextView tv_message;
	private TextView tv_feedback;
	private TextView tv_about;
	private Button btn_exit;
	private View v;

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
		v = inflater.inflate(R.layout.fragment_setting_main, container, false);
		initView();
		return v;
	}

	public MainSettingFragment newInstance(int section) {
		MainSettingFragment fragment = new MainSettingFragment();

		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, section);
		fragment.setArguments(args);
		return fragment;
	}

	public void initView() {
		tv_accountset = (TextView) v.findViewById(R.id.tv_accountset);
		tv_about = (TextView) v.findViewById(R.id.tv_about);
		tv_feedback = (TextView) v.findViewById(R.id.tv_feedback);
		tv_message = (TextView) v.findViewById(R.id.tv_message);
		btn_exit = (Button) v.findViewById(R.id.btn_exit);
		tv_about.setOnClickListener(this);
		tv_accountset.setOnClickListener(this);
		tv_feedback.setOnClickListener(this);
		tv_message.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_accountset:
			startActivity(new Intent(getActivity(), AccountActivity.class));
			break;
		case R.id.tv_message:

			break;
		case R.id.tv_feedback:
			startActivity(new Intent(getActivity(), FeedBackActivity.class));
			break;
		case R.id.tv_about:
			startActivity(new Intent(getActivity(), AboutActivity.class));
			break;
		case R.id.btn_exit:
			getActivity().finish();
			break;
		}
	}

}
