package com.codefriday.bangkokunitrade.activity;


import java.util.ArrayList;

import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.DataOrderDataset;
import com.codefriday.bangkokunitrade.dataset.OrderCase3Entry;
import com.codefriday.bangkokunitrade.dataset.SetEntry;
import com.codefriday.bangkokunitrade.json.BiomaterialsPaser;
import com.codefriday.bangkokunitrade.adapters.BiomaterialsAdapter;
import com.codefriday.bangkokunitrade.util.Api;
import com.codefriday.bangkokunitrade.util.Util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;

public class BiomaterialsActivity extends Activity implements OnTouchListener{
	private String strApiCall;
	private ListView list;
	private Button buttonNext;
	Api api;
	AQuery aq;
	BiomaterialsAdapter adapter;
	BiomaterialsPaser biomaterialsPaser;
	ArrayList<SetEntry> tramuaListEntries = new ArrayList<SetEntry>();
	ProgressDialog dialog;
	DataOrderDataset dataOrderDataset = DataOrderDataset.getInstance();
	String trauma_id = null,name = null;
	TextView TextHead;
	ArrayList<OrderCase3Entry> arrayList = new ArrayList<OrderCase3Entry>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_biomaterials_list);

		dialog = ProgressDialog.show(this, "","Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();
		
		init();
		ApiGetData();
		
		TextHead.setText(getResources().getString(R.string.list));
	}
	
	private void ApiGetData() {
		strApiCall = api.getApiBiomaterialsSetList();
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<SetEntry> arrayList = new ArrayList<SetEntry>();
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = biomaterialsPaser.getData(json);
						 tramuaListEntries = arrayList;
						 adapter = new BiomaterialsAdapter(BiomaterialsActivity.this, arrayList);
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
		list.setOnItemClickListener(null);
		aq = new AQuery(this);
		api = new Api(this);
		biomaterialsPaser = new BiomaterialsPaser();
		
		TextHead = (TextView) findViewById(R.id.textHead);
		TextHead.setTypeface(Util.getBoldFont(this));
		TextHead.setTextSize(30f);
		
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
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			switch (v.getId()) {
			case R.id.buttonNext:
				for (int i = 0; i < list.getAdapter().getCount(); i++) {
					
					SetEntry item = (SetEntry)list.getAdapter().getItem(i);
					
				    if (!item.getQty().matches("") && !item.getQty().matches("0") ) {
				    	
				    	int checkInt = Integer.parseInt(item.getQty());
				    	item.setQty(String.valueOf(checkInt));
				    	
				    	arrayList.add(new OrderCase3Entry(
				    			String.valueOf(item.getId()),
				    			item.getName(),
				    			item.getQty()));
					}
				}
				if (arrayList.isEmpty()) {
					Toast.makeText(this, "กรุณาใส่จำนวนอย่างน้อย 1 รายการ", Toast.LENGTH_SHORT).show();
					return false;
				}else{
					dataOrderDataset.setOrderCase3Entries(arrayList);
					
					Intent intent = new Intent(BiomaterialsActivity.this,SetcaseDetailActivity.class);
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
