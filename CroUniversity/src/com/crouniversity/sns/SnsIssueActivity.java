package com.crouniversity.sns;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.crouniversity.main.BaseActivity;
import com.example.crouniversity.R;

public class SnsIssueActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sns_issue);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.commit, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();

		if (id == R.id.commit) {
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
