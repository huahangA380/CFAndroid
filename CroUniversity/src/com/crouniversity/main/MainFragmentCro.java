package com.crouniversity.main;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crouniversity.cro.CroDetailActivity;
import com.crouniversity.cro.CroMainGetData;
import com.crouniversity.cro.CroViewHolder;
import com.crouniversity.roundimg.RoundImageView;
import com.crouniversity.roundimg.ShowHideOnScroll;
import com.crouniversity.userinfo.UserInfoMainActivity;
import com.crouniversity.utils.GetPicture;
import com.crouniversity.utils.ReadTextFile;
import com.example.crouniversity.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MainFragmentCro extends Fragment {

	private ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
	private PullToRefreshListView mPullRefreshListView;
	MyAdapter adapter = null;
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
		adapter = new MyAdapter(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_cro_main, container, false);
		mPullRefreshListView = (PullToRefreshListView) v
				.findViewById(R.id.pull_refresh_list);

		RoundImageView round = (RoundImageView) v.findViewById(R.id.roundimg);
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

		listItem = CroMainGetData.getData(getActivity());// 获取LIST数据
		// 设置适配器
		ListView actualListView = mPullRefreshListView.getRefreshableView();
		actualListView.setAdapter(adapter);
		actualListView.setOnTouchListener(new ShowHideOnScroll(round));
		mPullRefreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), CroDetailActivity.class));
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
				inputStream = getActivity().getAssets().open("cro.txt");
				String json = ReadTextFile.readTextFile(inputStream);
				JSONArray array = new JSONArray(json);
				int n = array.length();
				for (int i = 0; i < n; i++) {
					map = new HashMap<String, Object>();
					map.put("cro_img_main",
							array.getJSONObject(i).getString("cro_img_main"));
					map.put("cro_pro_main",
							array.getJSONObject(i).getString("cro_pro_main"));
					map.put("cro_tv_completed", array.getJSONObject(i)
							.getString("cro_tv_completed"));
					map.put("cro_tv_fund",
							array.getJSONObject(i).getString("cro_tv_fund"));
					map.put("cro_tv_remaindays", array.getJSONObject(i)
							.getString("cro_tv_remaindays"));
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
				inputStream = getActivity().getAssets().open("cro.txt");
				String json = ReadTextFile.readTextFile(inputStream);
				JSONArray array = new JSONArray(json);
				int n = array.length();
				for (int i = 0; i < n; i++) {
					map = new HashMap<String, Object>();
					map.put("cro_img_main",
							array.getJSONObject(i).getString("cro_img_main"));
					map.put("cro_pro_main",
							array.getJSONObject(i).getString("cro_pro_main"));
					map.put("cro_tv_completed", array.getJSONObject(i)
							.getString("cro_tv_completed"));
					map.put("cro_tv_fund",
							array.getJSONObject(i).getString("cro_tv_fund"));
					map.put("cro_tv_remaindays", array.getJSONObject(i)
							.getString("cro_tv_remaindays"));
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

	public class MyAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listItem.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return listItem.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			CroViewHolder holder = null;

			if (convertView == null) {
				holder = new CroViewHolder();
				convertView = mInflater.inflate(R.layout.layout_cro_main_item,
						parent, false);
				holder.img_cro_main = (ImageView) convertView
						.findViewById(R.id.img_cro_main);
				holder.pro = (ProgressBar) convertView
						.findViewById(R.id.pro_cro_completed);
				holder.tv_complete = (TextView) convertView
						.findViewById(R.id.tv_main_completed);
				holder.tv_fund = (TextView) convertView
						.findViewById(R.id.tv_main_fund);
				holder.tv_main_remaindays = (TextView) convertView
						.findViewById(R.id.tv_main_remaindays);
				convertView.setTag(holder);

			} else {

				holder = (CroViewHolder) convertView.getTag();
			}
			holder.img_cro_main.setImageBitmap(GetPicture.getPic(
					((String) listItem.get(position).get("cro_img_main")),
					getActivity()));
			String s = (String) listItem.get(position).get("cro_pro_main");
			int i = Integer.parseInt(s);
			holder.pro.setProgress(i);
			holder.tv_complete.setText((String) listItem.get(position).get(
					"cro_tv_completed"));
			holder.tv_fund.setText((String) listItem.get(position).get(
					"cro_tv_fund"));
			holder.tv_main_remaindays.setText((String) listItem.get(position)
					.get("cro_tv_remaindays"));

			return convertView;
		}

	}

	public MainFragmentCro newInstance(int selectnum) {
		// TODO Auto-generated method stub
		MainFragmentCro fragment = new MainFragmentCro();
		Bundle args = new Bundle();
		args.putInt(SELECTNUM, selectnum);
		fragment.setArguments(args);
		return fragment;

	}

}
