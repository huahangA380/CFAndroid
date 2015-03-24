package com.crouniversity.sns;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.crouniversity.fab.FloatingActionButton;
import com.crouniversity.fab.ShowHideOnScroll;
import com.crouniversity.main.MainActivity;
import com.example.crouniversity.R;

public class SnsStudyMainFragment extends Fragment {

	private SnsStudyMainAdapter adapter;
	private final static String SELECTNUM = "selectnum";

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				SELECTNUM));
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		adapter = new SnsStudyMainAdapter(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_studysns_main,
				container, false);
		FloatingActionButton fab = (FloatingActionButton) view
				.findViewById(R.id.fab);
		ListView lv_studysns_main = (ListView) view
				.findViewById(R.id.lv_studysns_main);
		lv_studysns_main.setAdapter(adapter);
		lv_studysns_main.setOnTouchListener(new ShowHideOnScroll(fab));
		lv_studysns_main.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), SnsDetailActivity.class));
			}
		});
		return view;
	}

	public SnsStudyMainFragment newInstance(int selectnum) {
		// TODO Auto-generated method stub
		SnsStudyMainFragment fragment = new SnsStudyMainFragment();
		Bundle args = new Bundle();
		args.putInt(SELECTNUM, selectnum);
		fragment.setArguments(args);
		return fragment;

	}
}
