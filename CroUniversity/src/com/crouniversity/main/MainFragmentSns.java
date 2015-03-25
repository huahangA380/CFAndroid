package com.crouniversity.main;

import com.crouniversity.fab.FloatingActionButton;
import com.crouniversity.fab.ShowHideOnScroll;
import com.crouniversity.sns.SnsDetailActivity;
import com.crouniversity.sns.SnsStudyMainAdapter;
import com.example.crouniversity.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainFragmentSns extends Fragment {
	private SnsStudyMainAdapter adapter;

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
		View view = inflater.inflate(R.layout.fragment_main_sns, container,
				false);

		FloatingActionButton fab = (FloatingActionButton) view
				.findViewById(R.id.fab);
		ListView lv_main_sns = (ListView) view.findViewById(R.id.lv_main_sns);
		lv_main_sns.setAdapter(adapter);
		lv_main_sns.setOnTouchListener(new ShowHideOnScroll(fab));
		lv_main_sns.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), SnsDetailActivity.class));
			}
		});
		return view;

	}
}
