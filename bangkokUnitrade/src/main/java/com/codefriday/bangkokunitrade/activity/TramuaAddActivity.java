package com.codefriday.bangkokunitrade.activity;


import java.util.ArrayList;

import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.DataOrderDataset;
import com.codefriday.bangkokunitrade.dataset.MasterDetailimplantEntry;
import com.codefriday.bangkokunitrade.dataset.OrderCase2Entry;
import com.codefriday.bangkokunitrade.json.DetailMasterImplantPaser;
import com.codefriday.bangkokunitrade.adapters.TramuaAddAdapter;
import com.codefriday.bangkokunitrade.util.Api;
import com.codefriday.bangkokunitrade.util.Util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class TramuaAddActivity extends Activity implements OnItemClickListener,OnTouchListener{
	private String strApiCall;
	private ListView list;
	private Button buttonNext;
	Api api;
	AQuery aq;
	private Bundle bundle;
	TramuaAddAdapter adapter;
	DetailMasterImplantPaser detailMasterImplantPaser;
	ArrayList<MasterDetailimplantEntry> tramuaListEntries = new ArrayList<MasterDetailimplantEntry>();
	ProgressDialog dialog;
	DataOrderDataset dataOrderDataset = DataOrderDataset.getInstance();
	String trauma_id = null,set_implant_id = null,trauma_name = null;
	TextView TextHead,textName;
	ArrayList<OrderCase2Entry> arrayList = new ArrayList<OrderCase2Entry>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tramua_add_list);

		dialog = ProgressDialog.show(this, "","Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();
		
		init();
		set_implant_id = String.valueOf(bundle.getInt("set_implant_id"));
		trauma_name = String.valueOf(bundle.getString("name"));
		trauma_id = String.valueOf(bundle.getInt("trauma_id"));
		
		ApiGetData(set_implant_id);
		
		TextHead.setText(getResources().getString(R.string.textHeadAdd));
		textName.setText(trauma_name);
	}
	
	private void ApiGetData(String set_implant_id) {
		strApiCall = api.getApiDetailMasterImplant(set_implant_id);
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<MasterDetailimplantEntry> arrayList = new ArrayList<MasterDetailimplantEntry>();
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = detailMasterImplantPaser.getData(json);
						 tramuaListEntries = arrayList;
						 adapter = new TramuaAddAdapter(TramuaAddActivity.this, arrayList);
						 list.setAdapter(adapter);
						 dialog.dismiss();
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
		list = (ListView) findViewById(R.id.list);
		list.setOnItemClickListener(this);
		aq = new AQuery(this);
		api = new Api(this);
		detailMasterImplantPaser = new DetailMasterImplantPaser();
		bundle = getIntent().getExtras();
		TextHead = (TextView) findViewById(R.id.textHead);
		textName = (TextView) findViewById(R.id.textName);
		textName.setTypeface(Util.getBoldFont(this));
		textName.setTextSize(30f);
		
		buttonNext = (Button) findViewById(R.id.buttonNext);
		buttonNext.setTypeface(Util.getBoldFont(this));
		buttonNext.setTextSize(50f);
		buttonNext.setOnTouchListener(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
		
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		if (event.getAction() == MotionEvent.ACTION_UP) {
			switch (v.getId()) {
			case R.id.buttonNext:
				
				for (int i = 0; i < list.getAdapter().getCount(); i++) {
					try {
						MasterDetailimplantEntry item = (MasterDetailimplantEntry)list.getAdapter().getItem(i);
						
					    if (!item.getQty().matches("") && !item.getQty().matches("0") ) {
					    	
					    	int checkInt = Integer.parseInt(item.getQty());
					    	item.setQty(String.valueOf(checkInt));
					    	
					    	arrayList.add(new OrderCase2Entry(
					    			set_implant_id,
					    			trauma_id,
					    			trauma_name, 
					    			String.valueOf(item.getId()),
					    			item.getDescription(), 
					    			item.getQty()));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				    
				}
				
				if (arrayList.size() == 0) {
					Toast.makeText(this, "กรุณาใส่จำนวนอย่างน้อย 1 รายการ", Toast.LENGTH_SHORT).show();
					return false;
				}else{
					dataOrderDataset.setOrderCase2Entries(arrayList);
					
					Intent intent = new Intent(TramuaAddActivity.this,SetcaseDetailActivity.class);
					startActivity(intent);
					finish();
					overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
				}
				break;

			default:
				break;
			};
		}
		return false;
	}
	
	
}
