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
import com.codefriday.bangkokunitrade.adapters.HospitalAdapter;
import com.codefriday.bangkokunitrade.util.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class QuotateActivity extends Activity implements OnItemClickListener {
	private ListView list;
	private Api api;
	private AQuery aq;
	private String strApiCall;
	private HospitalAdapter hospitalAdapter;
	private HospitalPaser hospitalPaser;
	private ArrayList<HospitalEntry> hospitalEntries;
	private ProgressDialog dialog;
	private UserDataset dataset = UserDataset.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quotate);

		dialog = ProgressDialog.show(this, "","Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();
		
		init();

		ApiGetData();
	}

	private void ApiGetData() {
		
		strApiCall = api.getApiHospital(dataset.getUser_id());
		
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<HospitalEntry> arrayList = new ArrayList<HospitalEntry>();
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = hospitalPaser.getData(json);
						 hospitalEntries = arrayList;
						 hospitalAdapter = new HospitalAdapter(QuotateActivity.this, arrayList);
						 list.setAdapter(hospitalAdapter);
					 }
				 }catch(Exception e){
					 e.printStackTrace();
				 }finally{
					 dialog.dismiss();
				 }
			}
        });
	}

	private void init() {

		hospitalPaser = new HospitalPaser();
		aq = new AQuery(this);
		api = new Api(this);

		list = (ListView) findViewById(R.id.list);
		list.setOnItemClickListener(this);
		
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		Intent intent = new Intent(this,ListQuotateActivity.class);
		intent.putExtra("hospital_id", hospitalEntries.get(position).getId());
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}

}
