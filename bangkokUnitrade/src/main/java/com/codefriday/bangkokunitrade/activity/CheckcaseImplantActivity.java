package com.codefriday.bangkokunitrade.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.CheckcaseEntry;
import com.codefriday.bangkokunitrade.dataset.UserDataset;
import com.codefriday.bangkokunitrade.json.CheckcasePaser;
import com.codefriday.bangkokunitrade.adapters.CheckcaseAdapter;
import com.codefriday.bangkokunitrade.util.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CheckcaseImplantActivity extends Activity implements OnItemClickListener {
	private String strApiCall;
	private ListView list;
	//Hospital
	private CheckcaseAdapter adapter;
	private CheckcasePaser checkcasePaser;
	ArrayList<CheckcaseEntry> checkcaseEntries;
	AQuery aq;
	String Hospital = null;
	String hospital_id = null;
	UserDataset dataset = UserDataset.getInstance();
	ProgressDialog dialog;
	Api api;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkcase_implant);

		dialog = ProgressDialog.show(this, "","Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();
		
		init();
		
		ApiGetData();
	}

	private void init() {
		aq = new AQuery(this);
		api = new Api(this);
		checkcasePaser = new CheckcasePaser();

		list = (ListView) findViewById(R.id.list);
		list.setOnItemClickListener(this);
	}

	private void ApiGetData() {
		strApiCall = api.getApiOrderStatus();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", dataset.getUser_id());
		params.put("user_type",dataset.getUser_type());
		
		aq.ajax(strApiCall,params, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<CheckcaseEntry> arrayList = new ArrayList<CheckcaseEntry>();
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = checkcasePaser.getData(json);
						 checkcaseEntries = arrayList;
						 adapter = new CheckcaseAdapter(CheckcaseImplantActivity.this, arrayList);
						 list.setAdapter(adapter);
					 }
				 }catch(Exception e){
					 e.printStackTrace();
				 }finally{
					 dialog.dismiss();
				 }
			}
        });
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
		if (checkcaseEntries != null) {
			Intent intent = new Intent(this,ListcheckCaseActivity.class);
			intent.putExtra("order_id", checkcaseEntries.get(pos).getOrder_id());
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		}
		
	}

}
