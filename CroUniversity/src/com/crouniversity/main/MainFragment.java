package com.crouniversity.main;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.crouniversity.R;

public class MainFragment extends Fragment {
	private static final String ARG_SECTION_NUMBER = "section_number";
	private MainFragmentCro fragmentCro;
	private MainFragmentSns fragmentSns;
	private ViewPager viewPager;
	private ArrayList<Fragment> fragmentList;// ҳ���б�
	ArrayList<String> titleList = new ArrayList<String>();// �����б�
	private PagerTabStrip pagerTabStrip;// ���ñ�������

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
		viewPager = (ViewPager) view.findViewById(R.id.viewpager);
		pagerTabStrip = (PagerTabStrip) view.findViewById(R.id.pagertabstrip);
		pagerTabStrip.setTabIndicatorColor(getResources().getColor(
				android.R.color.holo_blue_dark));// �»�����ɫ
		pagerTabStrip.setBackgroundResource(android.R.color.holo_orange_dark);// ������ɫ
		pagerTabStrip.setTextColor(getResources().getColor(
				R.color.abc_search_url_text_normal));
		pagerTabStrip.setTextSize(2, 18);
		fragmentList = new ArrayList<Fragment>();
		fragmentCro = new MainFragmentCro();
		fragmentSns = new MainFragmentSns();
		fragmentList.add(fragmentCro);
		fragmentList.add(fragmentSns);
		titleList.add("�ڳ�");
		titleList.add("����");

		viewPager.setAdapter(new ViewPagerActivity(getFragmentManager()));
		viewPager.setOffscreenPageLimit(2);// Ԥ����2ҳ��
		return view;
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

	public class ViewPagerActivity extends FragmentPagerAdapter {

		public ViewPagerActivity(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return titleList.get(position);
		}

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}
