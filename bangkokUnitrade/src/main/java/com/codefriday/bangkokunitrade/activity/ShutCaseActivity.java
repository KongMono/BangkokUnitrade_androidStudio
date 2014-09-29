package com.codefriday.bangkokunitrade.activity;

import java.util.ArrayList;

import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.HospitalEntry;
import com.codefriday.bangkokunitrade.dataset.UserDataset;
import com.codefriday.bangkokunitrade.json.HospitalPaser;
import com.codefriday.bangkokunitrade.adapters.ShutCaseAdapter;
import com.codefriday.bangkokunitrade.util.Api;
import com.codefriday.bangkokunitrade.util.Util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class ShutCaseActivity extends Activity implements OnItemClickListener {
	private EditText editSearch;
	private ListView list;
	private String strApiCall;
	// Hospital
	private ShutCaseAdapter adapter;
	private HospitalPaser hospitalPaser;
	ArrayList<HospitalEntry> hospitalEntries;
	AQuery aq;
	String Hospital = null;
	String hospital_id = null;
	UserDataset dataset = UserDataset.getInstance();
	ProgressDialog dialog;
	Api api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shutcase);

		dialog = ProgressDialog.show(this, "", "Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();

		init();

		editSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				ShutCaseActivity.this.adapter.getFilter().filter(cs);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});

		ApiGetDataHospital();

	}

	private void init() {
		aq = new AQuery(this);
		api = new Api(this);
		hospitalPaser = new HospitalPaser();
		editSearch = (EditText) findViewById(R.id.editSearch);
		editSearch.setTypeface(Util.getBoldFont(this));
		editSearch.setTextSize(30f);
		
		list = (ListView) findViewById(R.id.list);
		list.setOnItemClickListener(this);

	}

	private void ApiGetDataHospital() {
		strApiCall = api.getApiHospital(dataset.getUser_id());
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<HospitalEntry> arrayList = new ArrayList<HospitalEntry>();

			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				try {
					if (json != null) {
						arrayList = hospitalPaser.getData(json);
						hospitalEntries = arrayList;
						adapter = new ShutCaseAdapter(ShutCaseActivity.this,arrayList);
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
		
		Intent intent = new Intent(ShutCaseActivity.this,ShutCaseListActivity.class);
		intent.putExtra("hospital_id", hospitalEntries.get(position).getId());
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		

	}

}
