package com.crouniversity.main;

import com.example.crouniversity.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragmentCro extends Fragment {
	private final static String SELECTUNM = "selectum";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	public MainFragmentCro newInstance(int selectnum) {
		MainFragmentCro fragment = new MainFragmentCro();
		Bundle bundle = new Bundle();
		bundle.putInt(SELECTUNM, selectnum);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_main_cro, container,
				false);
		TextView tv = (TextView) view.findViewById(R.id.tvsss);
		tv.setText("test");
		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				SELECTUNM));
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
