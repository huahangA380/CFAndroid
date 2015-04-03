package com.crouniversity.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crouniversity.cro.CroCommunityFragment;
import com.crouniversity.sns.SnsLiveMainFragment;
import com.example.crouniversity.R;

public class MainFragment extends Fragment {
	private static final String ARG_SECTION_NUMBER = "section_number";

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	// private DisplayMetrics dm;
	private String[] titles = { "ÖÚ³ï", "ÉçÇø" };
	private SnsLiveMainFragment sns;
	private CroCommunityFragment cro;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		// dm = getResources().getDisplayMetrics();
		pager = (ViewPager) view.findViewById(R.id.viewPager);
		tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
		tabs.setTextSize(40);
		pager.setAdapter(new MyAdapter(getFragmentManager(), titles));
		tabs.setViewPager(pager);
		return view;
	}

	public class MyAdapter extends FragmentStatePagerAdapter {
		String[] _titles;

		public MyAdapter(FragmentManager fm, String[] titles) {
			super(fm);
			_titles = titles;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return _titles[position];
		}

		@Override
		public int getCount() {
			return _titles.length;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				if (cro == null) {
					cro = new CroCommunityFragment().newInstance(position);
				}
				return cro;
			case 1:
				if (sns == null) {
					sns = new SnsLiveMainFragment().newInstance(position);
				}
				return sns;

			default:
				return null;
			}
		}
	}

	public MainFragment newInstance(int sectionnum) {
		// TODO Auto-generated method stub
		MainFragment fragment = new MainFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionnum);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-g enerated method stub
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}
