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

import com.example.crouniversity.R;

public class HomeFragment extends Fragment {
	private View view;
	private static final String SELECTNUM = "selectnum";
	private PagerSlidingTabStrip tabs;
	private MainFragmentCro mainCro;
	private MainFragmentSns mainSns;
	private ViewPager pager;
	private String titles[] = { "ÖÚ³ï", "ÉçÇø" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_main, container, false);
		pager = (ViewPager) view.findViewById(R.id.viewPager);
		tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
		tabs.setTextSize(40);
		pager.setAdapter(new ViewPagerAdapater(getFragmentManager(), titles));
		tabs.setViewPager(pager);
		return view;
	}

	public class ViewPagerAdapater extends FragmentStatePagerAdapter {
		String[] titles;

		public ViewPagerAdapater(FragmentManager fm, String[] titles) {
			super(fm);
			// TODO Auto-generated constructor stub
			this.titles = titles;
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			switch (arg0) {
			case 0:
				mainCro = new MainFragmentCro();
				return mainCro;
			case 1:
				mainSns = new MainFragmentSns().newInstance(1);
				return mainSns;
			default:
				break;
			}
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return titles.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return titles[position];
		}
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				SELECTNUM));
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	public HomeFragment newInstance(int selectnum) {
		HomeFragment fragment = new HomeFragment();
		Bundle args = new Bundle();
		args.putInt(SELECTNUM, selectnum);
		fragment.setArguments(args);
		return fragment;
	}
}
