package com.crouniversity.main;

import java.util.ArrayList;

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
	private MainFragmentCro fragmentCro;
	private MainFragmentSns fragmentSns;
	private ViewPager viewPager;
	private ArrayList<Fragment> fragmentList;// 页面列表
	ArrayList<String> titleList = new ArrayList<String>();// 标题列表
	private PagerTabStrip pagerTabStrip;// 设置标题属性

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
				android.R.color.holo_blue_dark));// 下划线颜色
		pagerTabStrip.setBackgroundResource(android.R.color.holo_orange_dark);// 背景颜色
		fragmentList = new ArrayList<Fragment>();
		fragmentCro = new MainFragmentCro();
		fragmentSns = new MainFragmentSns();
		fragmentList.add(fragmentCro.newInstance(1));
		fragmentList.add(fragmentSns);
		titleList.add("众筹");
		titleList.add("社区");

		viewPager.setAdapter(new ViewPagerActivity(getFragmentManager()));
		viewPager.setOffscreenPageLimit(2);// 预加载2页面
		return view;
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
