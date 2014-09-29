package com.codefriday.bangkokunitrade.activity;

import java.util.ArrayList;

import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.ShutCaseListEntry;
import com.codefriday.bangkokunitrade.dataset.UserDataset;
import com.codefriday.bangkokunitrade.json.ShutcasePaser;
import com.codefriday.bangkokunitrade.adapters.ShutCaseListAdapter;
import com.codefriday.bangkokunitrade.util.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ShutCaseListActivity extends Activity implements OnItemClickListener {
	private ListView list;
	private String strApiCall;
	// Hospital
	private ShutCaseListAdapter adapter;
	private ShutcasePaser shutcasePaser;
	ArrayList<ShutCaseListEntry> hospitalEntries;
	AQuery aq;
	String Hospital = null;
	String hospital_id = null;
	UserDataset dataset = UserDataset.getInstance();
	ProgressDialog dialog;
	private Bundle bundle;
	Api api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shutcase_list);

		dialog = ProgressDialog.show(this, "", "Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();

		init();

		ApigetApiCasesList(bundle.getInt("hospital_id"));

	}

	private void init() {
		aq = new AQuery(this);
		api = new Api(this);
		shutcasePaser = new ShutcasePaser();
		list = (ListView) findViewById(R.id.list);
		list.setOnItemClickListener(this);
		bundle = getIntent().getExtras();
	}

	private void ApigetApiCasesList(int hospital_id) {
		strApiCall = api.getApiCasesList(String.valueOf(hospital_id));
		
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<ShutCaseListEntry> arrayList = new ArrayList<ShutCaseListEntry>();

			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				try {
					if (json != null) {
						arrayList = shutcasePaser.getData(json);
						hospitalEntries = arrayList;
						adapter = new ShutCaseListAdapter(ShutCaseListActivity.this,arrayList);
						list.setAdapter(adapter);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					dialog.dismiss();
				}
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		
		Intent intent = new Intent(ShutCaseListActivity.this,ShutCaseDetailActivity.class);
		intent.putExtra("id", hospitalEntries.get(position).getCase_id());
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		
		
	}

}
