package com.codefriday.bangkokunitrade.activity;


import java.util.ArrayList;

import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.DataOrderDataset;
import com.codefriday.bangkokunitrade.dataset.TramuaListEntry;
import com.codefriday.bangkokunitrade.json.TramuaListPaser;
import com.codefriday.bangkokunitrade.adapters.TramuaListAdapter;
import com.codefriday.bangkokunitrade.util.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TramuaListActivity extends Activity implements OnItemClickListener{
	private String strApiCall;
	private ListView list;
	Api api;
	AQuery aq;
	Bundle bundle;
	TramuaListAdapter adapter;
	TramuaListPaser tramuaListPaser;
	ArrayList<TramuaListEntry> tramuaListEntries = new ArrayList<TramuaListEntry>();
	ProgressDialog dialog;
	DataOrderDataset dataOrderDataset = DataOrderDataset.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tramua_list);

		dialog = ProgressDialog.show(this, "",
				"Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();
		
		init();
		ApiGetData(bundle.getString("position"));
		
	}
	
	private void ApiGetData(String position) {
		strApiCall = api.getApiTramuaList(position);
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<TramuaListEntry> arrayList = new ArrayList<TramuaListEntry>();
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = tramuaListPaser.getData(json);
						 tramuaListEntries = arrayList;
						 adapter = new TramuaListAdapter(TramuaListActivity.this, arrayList);
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
		tramuaListPaser = new TramuaListPaser();
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
		
		if (dataOrderDataset.getOrder_type().equalsIgnoreCase("1")) {
			Intent intent = new Intent(this,TramuaSubListDetailActivity.class);
			intent.putExtra("id", tramuaListEntries.get(pos).getId());
			intent.putExtra("name", tramuaListEntries.get(pos).getName());
			intent.putExtra("image", tramuaListEntries.get(pos).getImage());
			startActivity(intent);
			
		}else if(dataOrderDataset.getOrder_type().equalsIgnoreCase("2")){
			Intent intent = new Intent(this,TramuaMasterListActivity.class);
			intent.putExtra("id", tramuaListEntries.get(pos).getId());
			intent.putExtra("name", tramuaListEntries.get(pos).getName());
			intent.putExtra("image", tramuaListEntries.get(pos).getImage());
			startActivity(intent);
		}
		
		finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		
	}
}
