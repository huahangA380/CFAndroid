package com.crouniversity.cro;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.crouniversity.main.BaseActivity;
import com.example.crouniversity.R;

public class CroDetailActivity extends BaseActivity implements
		OnPageChangeListener {
	/**
	 * ViewPager
	 */
	private ViewPager viewPager;

	/**
	 * 装点点的ImageView数组
	 */
	private ImageView[] tips;

	/**
	 * 装ImageView数组
	 */
	private ImageView[] mImageViews;

	/**
	 * 图片资源id
	 */
	private int[] imgIdArray;

	Timer mTimer;
	TimerTask mTask;
	int pageIndex = 1;
	boolean isTaskRun;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cro_detail);
		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		Button btn_support = (Button) findViewById(R.id.btn_support);
		btn_support.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						CroTrancheActivity.class));
			}
		});
		// 载入图片资源ID
		imgIdArray = new int[] { R.drawable.item01, R.drawable.item02,
				R.drawable.item03, R.drawable.item04, };

		// 将点点加入到ViewGroup中
		tips = new ImageView[imgIdArray.length];
		for (int i = 0; i < tips.length; i++) {
			ImageView imageView = new ImageView(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					15, 15);
			params.setMargins(3, 0, 3, 0);// 左，上，右，下
			imageView.setLayoutParams(params);
			imageView.setScaleType(ScaleType.FIT_XY);

			tips[i] = imageView;
			if (i == 0) {
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}

			group.addView(imageView);
		}

		// 将图片装载到数组中
		mImageViews = new ImageView[imgIdArray.length];
		for (int i = 0; i < mImageViews.length; i++) {
			ImageView imageView = new ImageView(this);
			mImageViews[i] = imageView;
			imageView.setBackgroundResource(imgIdArray[i]);
		}

		// 设置Adapter
		viewPager.setAdapter(new MyAdapter());
		// 设置监听，主要是设置点点的背景
		viewPager.setOnPageChangeListener(this);
		// 设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
		viewPager.setCurrentItem((mImageViews.length) * 100);

	}

	/**
	 * 开启定时任务
	 */
	private void startTask() {
		// TODO Auto-generated method stub
		isTaskRun = true;
		mTimer = new Timer();
		mTask = new TimerTask() {
			@Override
			public void run() {
				pageIndex++;
				mHandler.sendEmptyMessage(0);
			}
		};
		mTimer.schedule(mTask, 2 * 1000, 2 * 1000);// 这里设置自动切换的时间，单位是毫秒，2*1000表示2秒
	}

	// 处理EmptyMessage(0)
	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			setCurrentItem();
		}
	};

	/**
	 * 处理Page的切换逻辑
	 */
	private void setCurrentItem() {
		if (pageIndex == 0) {
			pageIndex = 1;
		} else if (pageIndex == imgIdArray.length) {
			pageIndex = 1;
		}
		viewPager.setCurrentItem(pageIndex, false);// 取消动画
	}

	/**
	 * 停止定时任务
	 */
	private void stopTask() {
		// TODO Auto-generated method stub
		isTaskRun = false;
		mTimer.cancel();
	}

	@Override
	public void onResume() {
		super.onResume();
		setCurrentItem();
		startTask();
	}

	@Override
	public void onPause() {
		super.onPause();
		stopTask();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

		// TODO Auto-generated method stub
		// System.out.println("state:" + state);
		if (arg0 == 0 && !isTaskRun) {
			setCurrentItem();
			startTask();
		} else if (arg0 == 1 && isTaskRun)
			stopTask();

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		setImageBackground(arg0 % mImageViews.length);
		pageIndex = arg0;
	}

	/**
	 * 设置选中的tip的背景
	 * 
	 * @param selectItems
	 */
	private void setImageBackground(int selectItems) {
		for (int i = 0; i < tips.length; i++) {
			if (i == selectItems) {
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
		}
	}

	public class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(mImageViews[position
					% mImageViews.length]);

		}

		/**
		 * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
		 */
		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(mImageViews[position
					% mImageViews.length], 0);
			return mImageViews[position % mImageViews.length];
		}

	}

}
