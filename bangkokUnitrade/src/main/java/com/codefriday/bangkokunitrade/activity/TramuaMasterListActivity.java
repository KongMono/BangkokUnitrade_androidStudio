package com.codefriday.bangkokunitrade.activity;


import java.util.ArrayList;

import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.DataOrderDataset;
import com.codefriday.bangkokunitrade.dataset.SetEntry;
import com.codefriday.bangkokunitrade.json.TramuaListMasterPaser;
import com.codefriday.bangkokunitrade.adapters.TramuaMasterListAdapter;
import com.codefriday.bangkokunitrade.util.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TramuaMasterListActivity extends Activity implements OnItemClickListener{
	private String strApiCall;
	private ListView list;
	Api api;
	AQuery aq;
	Bundle bundle;
	TramuaMasterListAdapter adapter;
	TramuaListMasterPaser tramuaListMasterPaser;
	ArrayList<SetEntry> tramuaListEntries = new ArrayList<SetEntry>();
	ProgressDialog dialog;
	DataOrderDataset dataOrderDataset = DataOrderDataset.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tramua_list);

		dialog = ProgressDialog.show(this, "","Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();
		
		init();
		ApiGetData(bundle.getInt("id"));
		
	}
	
	private void ApiGetData(Integer tramua_id) {
		strApiCall = api.getApiImplantsListbyTraumasID(String.valueOf(tramua_id));
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<SetEntry> arrayList = new ArrayList<SetEntry>();
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = tramuaListMasterPaser.getData(json);
						 tramuaListEntries = arrayList;
						 adapter = new TramuaMasterListAdapter(TramuaMasterListActivity.this, arrayList);
						 list.setAdapter(adapter);
						 dialog.dismiss();
					 }
					 
				 }catch(Exception e){
					 e.printStackTrace();
				 }
			}
        });
	}

	private void init() {
		list = (ListView) findViewById(R.id.list);
		list.setOnItemClickListener(this);
		aq = new AQuery(this);
		api = new Api(this);
		tramuaListMasterPaser = new TramuaListMasterPaser();
		bundle = getIntent().getExtras();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
		
		Intent intent = new Intent(this,TramuaAddActivity.class);
		intent.putExtra("trauma_id", bundle.getInt("id"));
		intent.putExtra("set_implant_id", tramuaListEntries.get(pos).getId());
		intent.putExtra("name", tramuaListEntries.get(pos).getName());
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		
	}
}
