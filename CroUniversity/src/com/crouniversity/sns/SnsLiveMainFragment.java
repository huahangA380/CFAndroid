package com.crouniversity.sns;

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
import android.widget.ListView;
import android.widget.TextView;

import com.crouniversity.fab.FloatingActionButton;
import com.crouniversity.fab.ShowHideOnScroll;
import com.crouniversity.main.MainActivity;
import com.crouniversity.userinfo.UserInfoMainActivity;
import com.crouniversity.utils.ReadTextFile;
import com.example.crouniversity.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class SnsLiveMainFragment extends Fragment {

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
		View v = inflater
				.inflate(R.layout.fragment_sns_main, container, false);
		mPullRefreshListView = (PullToRefreshListView) v
				.findViewById(R.id.pull_refresh_list);

		FloatingActionButton fab = (FloatingActionButton) v
				.findViewById(R.id.fab);
		fab.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(),
						UserInfoMainActivity.class));
			}
		});
		// �趨������������
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

		listItem = SnsMainGetData.getData(getActivity());// ��ȡLIST����
		// ����������
		ListView actualListView = mPullRefreshListView.getRefreshableView();
		actualListView.setAdapter(adapter);
		actualListView.setOnTouchListener(new ShowHideOnScroll(fab));
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

		// ��̨��������
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

		// �����Ƕ�ˢ�µ���Ӧ����������addFirst������addLast()�������¼ӵ����ݼӵ�LISTView��
		// ����AsyncTask��ԭ����onPostExecute���result��ֵ����doInBackground()�ķ���ֵ
		@Override
		protected void onPostExecute(ArrayList<HashMap<String, Object>> result) {
			// ��ͷ��������������

			try {
				listItem.addAll(0, result);

				// ֪ͨ�������ݼ��Ѿ��ı䣬�������֪ͨ����ô������ˢ��mListItems�ļ���
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

		// ��̨��������
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

		// �����Ƕ�ˢ�µ���Ӧ����������addFirst������addLast()�������¼ӵ����ݼӵ�LISTView��
		// ����AsyncTask��ԭ����onPostExecute���result��ֵ����doInBackground()�ķ���ֵ
		@Override
		protected void onPostExecute(ArrayList<HashMap<String, Object>> result) {
			// ��ͷ��������������

			try {
				listItem.addAll(result);

				// ֪ͨ�������ݼ��Ѿ��ı䣬�������֪ͨ����ô������ˢ��mListItems�ļ���
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

			SnsViewHolder holder = null;
			if (convertView == null) {

				holder = new SnsViewHolder();

				convertView = mInflater.inflate(
						R.layout.layout_sns_main_item, parent, false);
				holder.title = (TextView) convertView
						.findViewById(R.id.tv_studysns_title);
				holder.content = (TextView) convertView
						.findViewById(R.id.tv_studysns_conents);
				holder.author = (TextView) convertView
						.findViewById(R.id.tv_var_man);
				holder.date = (TextView) convertView
						.findViewById(R.id.tv_var_time);
				holder.num = (TextView) convertView
						.findViewById(R.id.tv_comment_num);
				convertView.setTag(holder);

			} else {

				holder = (SnsViewHolder) convertView.getTag();
			}

			holder.title.setText((String) listItem.get(position).get("title"));
			holder.content.setText((String) listItem.get(position).get(
					"content"));
			holder.author
					.setText((String) listItem.get(position).get("author"));
			holder.date.setText((String) listItem.get(position).get("date"));
			holder.num.setText((String) listItem.get(position).get("num"));
			return convertView;
		}

	}

	public SnsLiveMainFragment newInstance(int selectnum) {
		// TODO Auto-generated method stub
		SnsLiveMainFragment fragment = new SnsLiveMainFragment();
		Bundle args = new Bundle();
		args.putInt(SELECTNUM, selectnum);
		fragment.setArguments(args);
		return fragment;

	}

}