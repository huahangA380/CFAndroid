package com.crouniversity.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import com.crouniversity.cro.CroCateFragment;
import com.crouniversity.cro.CroCommunityFragment;
import com.crouniversity.cro.CroProductFragment;
import com.crouniversity.cro.CroTravelFragment;
import com.crouniversity.setting.MainSettingFragment;
import com.crouniversity.sns.SnsLiveMainFragment;
import com.crouniversity.sns.SnsOriginalityFragment;
import com.crouniversity.sns.SnsStudyMainFragment;
import com.crouniversity.utils.DeBugLog;
import com.crouniversity.utils.ToastUtil;
import com.example.crouniversity.R;

@SuppressLint("InflateParams")
public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {
<<<<<<< HEAD
	private FragmentManager fragmentManager = getSupportFragmentManager();
	private CroProductFragment croProductFragment = new CroProductFragment();
	private MainFragment mainFragment = new MainFragment();
	private SnsStudyMainFragment snsStudyMainFragment = new SnsStudyMainFragment();
	private MainSettingFragment mainSettingFragment = new MainSettingFragment();
	private SnsLiveMainFragment snsLivewFragment = new SnsLiveMainFragment();
	private CroCommunityFragment croCommunityFragment = new CroCommunityFragment();
	private CroTravelFragment croTravelFragment = new CroTravelFragment();
	private CroCateFragment croCateFragment = new CroCateFragment();
	private SnsOriginalityFragment snsOriginalityFragment = new SnsOriginalityFragment();
=======
	FragmentManager fragmentManager = getSupportFragmentManager();
	CroProductFragment croProductFragment;// = new CroProductFragment();
	MainFragment mainFragment;// = new MainFragment();
	SnsStudyMainFragment snsStudyMainFragment;// = new SnsStudyMainFragment();
	MainSettingFragment mainSettingFragment;// = new MainSettingFragment();
	SnsLiveMainFragment snsLivewFragment;// = new SnsLiveMainFragment();
	CroCommunityFragment croCommunityFragment;// = new CroCommunityFragment();
	CroTravelFragment croTravelFragment;// = new CroTravelFragment();
	CroCateFragment croCateFragment;// = new CroCateFragment();
	SnsOriginalityFragment snsOriginalityFragment;// = new
													// SnsOriginalityFragment();
>>>>>>> parent of 45097ff... 更改Fragment状态
	private long firstime = 0;
	private View view;

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);// 淡入淡出效果
		view = getLayoutInflater().inflate(R.layout.toast_cutsomeview, null);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		DeBugLog.logI("***************", position + " ");

		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragment(transaction);
		switch (position) {
		case 0:
<<<<<<< HEAD

			fragmentManager.beginTransaction()
					.replace(R.id.container, mainFragment.newInstance(0))
					.commit();

=======
			if (mainFragment == null) {
				mainFragment = new MainFragment();
				transaction
						.replace(R.id.container, mainFragment.newInstance(0));
			} else {
				transaction.show(mainFragment.newInstance(0));
			}
			// fragmentManager.beginTransaction()
			// .replace(R.id.container, mainFragment.newInstance(0))
			// .commit();
>>>>>>> parent of 45097ff... 更改Fragment状态
			break;

		case 1:
			if (croProductFragment == null) {

<<<<<<< HEAD
			fragmentManager.beginTransaction()
					.replace(R.id.container, croProductFragment.newInstance(1))
					.commit();

=======
				croProductFragment = new CroProductFragment();
				transaction.replace(R.id.container,
						croProductFragment.newInstance(1)).setCustomAnimations(
						android.R.anim.fade_in, android.R.anim.fade_out);
			} else {

				transaction.show(croProductFragment);
			}
			// fragmentManager.beginTransaction()
			// .replace(R.id.container, croProductFragment.newInstance(1))
			// .commit();
>>>>>>> parent of 45097ff... 更改Fragment状态
			break;
		case 2:
			if (croCommunityFragment == null) {

<<<<<<< HEAD
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							croCommunityFragment.newInstance(2)).commit();

=======
				croCommunityFragment = new CroCommunityFragment();
				transaction.replace(R.id.container,
						croCommunityFragment.newInstance(2))
						.setCustomAnimations(android.R.anim.fade_in,
								android.R.anim.fade_out);
			} else {

				transaction.show(croCommunityFragment);
			}
			// fragmentManager
			// .beginTransaction()
			// .replace(R.id.container,
			// croCommunityFragment.newInstance(2)).commit();
>>>>>>> parent of 45097ff... 更改Fragment状态
			break;
		case 3:
			if (croTravelFragment == null) {

				croTravelFragment = new CroTravelFragment();
				transaction.replace(R.id.container,
						croTravelFragment.newInstance(3)).setCustomAnimations(
						android.R.anim.fade_in, android.R.anim.fade_out);
			} else {

				transaction.show(croCommunityFragment);
			}
			// fragmentManager.beginTransaction()
			// .replace(R.id.container, croTravelFragment.newInstance(3))
			// .commit();
			break;
		case 4:
			if (croCateFragment == null) {

				croCateFragment = new CroCateFragment();
				transaction.replace(R.id.container,
						croCateFragment.newInstance(4));
			} else {

				transaction.show(croCommunityFragment);
			}
			// fragmentManager.beginTransaction()
			// .replace(R.id.container, croCateFragment.newInstance(4))
			// .commit();
			break;
		case 5:
			if (snsStudyMainFragment == null) {

				snsStudyMainFragment = new SnsStudyMainFragment();
				transaction.replace(R.id.container,
						snsStudyMainFragment.newInstance(5));
			} else {

				transaction.show(snsStudyMainFragment);
			}
			// fragmentManager
			// .beginTransaction()
			// .replace(R.id.container,
			// snsStudyMainFragment.newInstance(5)).commit();
			break;
		case 6:
			if (snsLivewFragment == null) {

				snsLivewFragment = new SnsLiveMainFragment();
				transaction.replace(R.id.container,
						snsLivewFragment.newInstance(6));
			} else {

				transaction.show(snsLivewFragment);
			}
			// fragmentManager.beginTransaction()
			// .replace(R.id.container, snsLivewFragment.newInstance(6))
			// .commit();
			break;
		case 7:
			if (snsOriginalityFragment == null) {

				snsOriginalityFragment = new SnsOriginalityFragment();
				transaction.replace(R.id.container,
						snsOriginalityFragment.newInstance(7));
			} else {

				transaction.show(snsOriginalityFragment);
			}
			// fragmentManager
			// .beginTransaction()
			// .replace(R.id.container,
			// snsOriginalityFragment.newInstance(7)).commit();
			break;
		case 8:
			if (mainSettingFragment == null) {

				mainSettingFragment = new MainSettingFragment();
				transaction.replace(R.id.container,
						mainSettingFragment.newInstance(8));
			} else {

				transaction.show(mainSettingFragment);
			}
			// fragmentManager
			// .beginTransaction()
			// .replace(R.id.container, mainSettingFragment.newInstance(8))
			// .commit();
			break;
		default:

			break;
		}
<<<<<<< HEAD

=======
		transaction.commit();
>>>>>>> parent of 45097ff... 更改Fragment状态
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 0:
			mTitle = getString(R.string.homepage);
			break;
		case 1:
			mTitle = getString(R.string.productcro);
			break;
		case 2:
			mTitle = getString(R.string.charitypro);
			break;
		case 3:
			mTitle = getString(R.string.travelpro);
			break;
		case 4:
			mTitle = getString(R.string.catepro);
			break;
		case 5:
			mTitle = getString(R.string.studycommunity);
			break;
		case 6:
			mTitle = getString(R.string.livecommunity);
			break;
		case 7:
			mTitle = getString(R.string.originalitycommunity);
			break;
		case 8:
			mTitle = getString(R.string.action_settings);
			break;

		}
	}

	@SuppressWarnings("deprecation")
	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.sigin_button));
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// if (!mNavigationDrawerFragment.isDrawerOpen()) {
	// // Only show items in the action bar relevant to this screen
	// // if the drawer is not showing. Otherwise, let the drawer
	// // decide what to show in the action bar.
	// getMenuInflater().inflate(R.menu.main, menu);
	// restoreActionBar();
	// return true;
	// }
	// return super.onCreateOptionsMenu(menu);
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	// if (id == R.id.action_settings) {
	// return true;
	// }
	// if (id == R.id.action_example) {
	// item.setIcon(R.drawable.ic_drawer);
	// }
	// return super.onOptionsItemSelected(item);
	// }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {// 重写back键
		// TODO 自动生成的方法存根
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			long secondtime = System.currentTimeMillis();
			if (secondtime - firstime > 3000) { // 如果大于3秒 弹出toast提示
				firstime = System.currentTimeMillis();
				ToastUtil.showOutToast(getApplicationContext(), "再次点击返回键退出",
						view);
				return true;
			} else { // 如果小于3秒退出程序
				MainActivity.this.finish();
			}
		}
		return super.onKeyDown(keyCode, event);
	}

<<<<<<< HEAD
	// /**
	// * 切换页面的重载，优化了fragment的切换
	// *
	// * @param f
	// * @param descString
	// */
	// public void switchFragment(Fragment from, Fragment to) {
	// if (from == null || to == null)
	// return;
	// fragmentTransaction = getSupportFragmentManager().beginTransaction()
	// .setCustomAnimations(android.R.anim.fade_in,
	// android.R.anim.fade_out);
	// if (!to.isAdded()) {
	// // 隐藏当前的fragment，add下一个到Activity中
	// fragmentTransaction.hide(from).add(R.id.container, to).commit();
	// } else {
	// // 隐藏当前的fragment，显示下一个
	// fragmentTransaction.hide(from).show(to).commit();
	// }
	// }

	@Override
	protected void onSaveInstanceState(Bundle bundle) {
		// Empty
=======
	public void hideFragment(FragmentTransaction transaction) {
		// TODO Auto-generated method stub
		if (mainFragment != null) {
			transaction.hide(mainFragment);
		}
		if (croProductFragment != null) {
			transaction.hide(croProductFragment);
		}
		if (croCommunityFragment != null) {
			transaction.hide(croCommunityFragment);
		}
		if (croCateFragment != null) {
			transaction.hide(croCateFragment);
		}
		if (croTravelFragment != null) {
			transaction.hide(croTravelFragment);
		}
		if (snsLivewFragment != null) {
			transaction.hide(snsLivewFragment);
		}
		if (snsOriginalityFragment != null) {
			transaction.hide(snsOriginalityFragment);
		}
		if (snsStudyMainFragment != null) {
			transaction.hide(snsStudyMainFragment);
		}
		if (mainSettingFragment != null) {
			transaction.hide(mainSettingFragment);
		}
>>>>>>> parent of 45097ff... 更改Fragment状态
	}
}