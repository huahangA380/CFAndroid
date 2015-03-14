package com.crouniversity.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.crouniversity.crowdfunding.CroMainAdapter;
import com.example.crouniversity.R;

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
		View v = inflater.inflate(R.layout.fragment_cromain, container, false);

		CroMainAdapter adapter = new CroMainAdapter(getActivity());
		ListView lv = (ListView) v.findViewById(R.id.lv);

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
