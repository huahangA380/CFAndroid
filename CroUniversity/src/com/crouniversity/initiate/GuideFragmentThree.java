package com.crouniversity.initiate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.crouniversity.main.MainActivity;
import com.example.crouniversity.R;

public class GuideFragmentThree extends Fragment {

	private View mView;
	private WebView webView;
	private Button btn_startact;
	private final String URL1 = "file:///android_asset/Guide.html";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		mView = inflater
				.inflate(R.layout.fragment_guidestartcontent,
						(ViewGroup) getActivity()
								.findViewById(R.id.guide_pager), false);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup viewgproup = (ViewGroup) mView.getParent();
		if (viewgproup != null) {
			viewgproup.removeAllViewsInLayout();
		}
		btn_startact = (Button) mView.findViewById(R.id.btn_start_mainact);
		btn_startact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), MainActivity.class));
				getActivity().finish();
			}
		});
		webView = (WebView) mView.findViewById(R.id.web_guide_content);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
		});
		webView.loadUrl(URL1);
		WebSettings webSetting = webView.getSettings();
		webSetting.setJavaScriptEnabled(true);

		return mView;
	}

}
