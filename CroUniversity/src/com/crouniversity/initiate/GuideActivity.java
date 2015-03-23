package com.crouniversity.initiate;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;

import com.example.crouniversity.R;

public class GuideActivity extends FragmentActivity {
	private ViewPager viewpager;
	private GuideFragmentOne guideFrament1;
	private GuideFragmentTwo guideFrament2;
	private GuideFragmentThree guideFrament3;
	private ArrayList<Fragment> fragmentlist;// 页面列表

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);// 淡入淡出效果
		viewpager = (ViewPager) findViewById(R.id.guide_pager);

		fragmentlist = new ArrayList<Fragment>();
		guideFrament1 = new GuideFragmentOne();
		guideFrament2 = new GuideFragmentTwo();
		guideFrament3 = new GuideFragmentThree();
		fragmentlist.add(guideFrament1);
		fragmentlist.add(guideFrament2);
		fragmentlist.add(guideFrament3);
		viewpager
				.setAdapter(new ViewPagerActivity(getSupportFragmentManager()));
		viewpager.setOffscreenPageLimit(2);// 预加载2页面
	}

	public class ViewPagerActivity extends FragmentPagerAdapter {

		public ViewPagerActivity(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return fragmentlist.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fragmentlist.size();
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.i("----------", "onKeyDown");
		if (keyCode == KeyEvent.KEYCODE_BACK) {

		}
		return super.onKeyDown(keyCode, event);
	}
}
