package com.crouniversity.sns;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.crouniversity.main.MainActivity;
import com.crouniversity.roundimg.RoundImageView;
import com.crouniversity.roundimg.ShowHideOnScroll;
import com.crouniversity.userinfo.UserInfoMainActivity;
import com.crouniversity.utils.ReadTextFile;
import com.example.crouniversity.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class SnsStudyMainFragment extends Fragment {

	private ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
	private PullToRefreshListView mPullRefreshListView;
	private SnsLvAdapter adapter;
	private Mode currentMode;
	private final static String SELECTNUM = "selectnum";

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				SELECTNUM));
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		listItem = SnsMainGetData.getData(getActivity());// 获取LIST数据
		adapter = new SnsLvAdapter(getActivity(), listItem);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_sns_main, container, false);
		mPullRefreshListView = (PullToRefreshListView) v
				.findViewById(R.id.pull_refresh_list);
		setHasOptionsMenu(true);
		final RoundImageView round = (RoundImageView) v
				.findViewById(R.id.roundimg);
		round.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(),
						UserInfoMainActivity.class));

			}
		});
		// 设定下拉监听函数
		mPullRefreshListView
				.setOnRefreshListener(new OnRefreshListener<ListView>() {
					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {
						String label = DateUtils.formatDateTime(getActivity(),
								System.currentTimeMillis(),
								DateUtils.FORMAT_SHOW_TIME
										| DateUtils.FORMAT_SHOW_DATE
										| DateUtils.FORMAT_ABBREV_ALL);
						currentMode = refreshView.getCurrentMode();
						if (Mode.PULL_FROM_START == currentMode) {
							// Update the LastUpdatedLabel
							refreshView.getLoadingLayoutProxy()
									.setLastUpdatedLabel(label);

							// D work to refresh the list here.
							new GetDataTask().execute();
						}
						if (Mode.PULL_FROM_END == currentMode) {
							// Update the LastUpdatedLabel
							refreshView.getLoadingLayoutProxy()
									.setLastUpdatedLabel(label);

							// D work to refresh the list here.
							new GetDataTask2().execute();
						}

					}
				});

		// 设置适配器
		ListView actualListView = mPullRefreshListView.getRefreshableView();
		actualListView.setAdapter(adapter);
		actualListView.setOnTouchListener(new ShowHideOnScroll(round));
		mPullRefreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), SnsDetailActivity.class));
			}
		});
		return v;
	}

	private class GetDataTask extends
			AsyncTask<Void, Void, ArrayList<HashMap<String, Object>>> {

		// 后台处理部分
		@Override
		protected ArrayList<HashMap<String, Object>> doInBackground(
				Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> map = new HashMap<String, Object>();
			InputStream inputStream;
			try {
				inputStream = getActivity().getAssets().open("test.txt");
				String json = ReadTextFile.readTextFile(inputStream);
				JSONArray array = new JSONArray(json);
				for (int i = 0; i < array.length(); i++) {
					map = new HashMap<String, Object>();
					map.put("title", array.getJSONObject(i).getString("title"));
					map.put("content",
							array.getJSONObject(i).getString("content"));
					map.put("author", array.getJSONObject(i)
							.getString("author"));
					map.put("date", array.getJSONObject(i).getString("date"));
					map.put("num", array.getJSONObject(i).getString("num"));
					list.add(map);
				}
				return list;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}

		}

		// 这里是对刷新的响应，可以利用addFirst（）和addLast()函数将新加的内容加到LISTView中
		// 根据AsyncTask的原理，onPostExecute里的result的值就是doInBackground()的返回值
		@Override
		protected void onPostExecute(ArrayList<HashMap<String, Object>> result) {
			// 在头部增加新添内容

			try {
				listItem.addAll(0, result);

				// 通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
				adapter.notifyDataSetChanged();
				// Call onRefreshComplete when the list has been refreshed.
				mPullRefreshListView.onRefreshComplete();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			super.onPostExecute(result);
		}
	}

	private class GetDataTask2 extends
			AsyncTask<Void, Void, ArrayList<HashMap<String, Object>>> {

		// 后台处理部分
		@Override
		protected ArrayList<HashMap<String, Object>> doInBackground(
				Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> map = new HashMap<String, Object>();
			InputStream inputStream;
			try {
				inputStream = getActivity().getAssets().open("test.txt");
				String json = ReadTextFile.readTextFile(inputStream);
				JSONArray array = new JSONArray(json);
				for (int i = 0; i < array.length(); i++) {
					map = new HashMap<String, Object>();
					map.put("title", array.getJSONObject(i).getString("title"));
					map.put("content",
							array.getJSONObject(i).getString("content"));
					map.put("author", array.getJSONObject(i)
							.getString("author"));
					map.put("date", array.getJSONObject(i).getString("date"));
					map.put("num", array.getJSONObject(i).getString("num"));
					list.add(map);
				}
				return list;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}

		}

		// 这里是对刷新的响应，可以利用addFirst（）和addLast()函数将新加的内容加到LISTView中
		// 根据AsyncTask的原理，onPostExecute里的result的值就是doInBackground()的返回值
		@Override
		protected void onPostExecute(ArrayList<HashMap<String, Object>> result) {
			// 在头部增加新添内容

			try {
				listItem.addAll(result);

				// 通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
				adapter.notifyDataSetChanged();
				// Call onRefreshComplete when the list has been refreshed.
				mPullRefreshListView.onRefreshComplete();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			super.onPostExecute(result);
		}
	}

	public SnsStudyMainFragment newInstance(int selectnum) {
		// TODO Auto-generated method stub
		SnsStudyMainFragment fragment = new SnsStudyMainFragment();
		Bundle args = new Bundle();
		args.putInt(SELECTNUM, selectnum);
		fragment.setArguments(args);
		return fragment;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.issue) {
			startActivity(new Intent(getActivity(), SnsIssueActivity.class));
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {

		menu.clear();

		MenuInflater inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.issue, menu);
		super.onPrepareOptionsMenu(menu);
	}

}
