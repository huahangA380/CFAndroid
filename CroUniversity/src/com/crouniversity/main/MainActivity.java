package com.crouniversity.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.crouniversity.cro.CroProductFragment;
import com.crouniversity.sns.SnsStudyMainFragment;
import com.crouniversity.utils.ToastUtil;
import com.example.crouniversity.R;

@SuppressLint("InflateParams")
public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {
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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view = getLayoutInflater().inflate(R.layout.toast_cutsomeview, null);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		// final FloatingActionButton fab = (FloatingActionButton)
		// findViewById(R.id.fab);
		// fab.setColor(getResources().getColor(android.R.color.holo_blue_light));
		// //����FloatButton����ɫ
		//
		// fab.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// Toast.makeText(v.getContext(), R.string.action_clicked,
		// Toast.LENGTH_SHORT).show();
		// fab.setImageResource(R.drawable.ic_launcher);// ����FloatButtonImage
		// }
		// });

	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		CroProductFragment croProductFragment = new CroProductFragment();
		PlaceholderFragment p = new PlaceholderFragment();
		MainFragment mainFragment = new MainFragment();
		SnsStudyMainFragment snsStudyMainFragment = new SnsStudyMainFragment();
		switch (position) {
		case 0:
			fragmentManager.beginTransaction()
					.replace(R.id.container, mainFragment.newInstance(0))
					.commit();
			break;

		case 1:
			fragmentManager.beginTransaction()
					.replace(R.id.container, croProductFragment.newInstance(1))
					.commit();
			break;
		case 2:
			fragmentManager.beginTransaction()
					.replace(R.id.container, p.newInstance(2)).commit();
			break;
		case 3:
			fragmentManager.beginTransaction()
					.replace(R.id.container, p.newInstance(3)).commit();
			break;
		case 4:
			fragmentManager.beginTransaction()
					.replace(R.id.container, p.newInstance(4)).commit();
			break;
		case 5:
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							snsStudyMainFragment.newInstance(5)).commit();
			break;
		case 6:
			fragmentManager.beginTransaction()
					.replace(R.id.container, p.newInstance(6)).commit();
			break;
		case 7:
			fragmentManager.beginTransaction()
					.replace(R.id.container, p.newInstance(7)).commit();
			break;
		case 8:
			fragmentManager.beginTransaction()
					.replace(R.id.container, p.newInstance(8)).commit();
			break;
		}
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

		actionBar.setIcon(getResources().getDrawable(
				android.R.color.holo_orange_light));
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.action_example) {
			item.setIcon(R.drawable.ic_drawer);
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_studysns_detail,
					container, false);
			// TextView tv = (TextView)
			// rootView.findViewById(R.id.section_label);
			// String b = getArguments().toString();
			// tv.setText(b);
			// final EditText input = (EditText) rootView
			// .findViewById(R.id.edt_input);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {// ��дback��
		// TODO �Զ����ɵķ������
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			long secondtime = System.currentTimeMillis();
			if (secondtime - firstime > 3000) { // �������3�� ����toast��ʾ
				firstime = System.currentTimeMillis();
				ToastUtil.showOutToast(getApplicationContext(), "�ٴε�����ؼ��˳�",
						view);
				return true;
			} else { // ���С��3���˳�����
				MainActivity.this.finish();
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			View v = getCurrentFocus();
			if (isShouldHideInput(v, ev)) {

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				if (imm != null) {
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				}
			}
			return super.dispatchTouchEvent(ev);
		}
		// �ز����٣��������е������������TouchEvent��
		if (getWindow().superDispatchTouchEvent(ev)) {
			return true;
		}
		return onTouchEvent(ev);

	}

	public boolean isShouldHideInput(View v, MotionEvent event) {
		if (v != null && (v instanceof EditText)) {
			int[] leftTop = { 0, 0 };
			// ��ȡ�����ǰ��locationλ��
			v.getLocationInWindow(leftTop);
			int left = leftTop[0];
			int top = leftTop[1];
			int bottom = top + v.getHeight();
			int right = left + v.getWidth();
			if (event.getX() > left && event.getX() < right
					&& event.getY() > top && event.getY() < bottom) {
				// ���������������򣬱������EditText���¼�
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
}
