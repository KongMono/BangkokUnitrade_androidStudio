package com.codefriday.bangkokunitrade.activity;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import pl.rafalmanka.bubble_edit_text.BubbleEditText;
import pl.rafalmanka.bubble_edit_text.Hyperlink;
import pl.rafalmanka.bubble_edit_text.OnBubbleClickListener;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.DataOrderDataset;
import com.codefriday.bangkokunitrade.dataset.MasterimplantEntry;
import com.codefriday.bangkokunitrade.dataset.OrderCase4Entry;
import com.codefriday.bangkokunitrade.json.MasterImplantPaser;
import com.codefriday.bangkokunitrade.adapters.MasterImplantsetAdapter;
import com.codefriday.bangkokunitrade.util.Api;
import com.codefriday.bangkokunitrade.util.Util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SetMasterImplantActivity extends Activity implements OnItemClickListener{
	private String strApiCall;
	private ListView list;
	private Button btnNext;
	MasterImplantsetAdapter adapter;
	MasterImplantPaser masterImplantPaser;
	HashMap<String,OrderCase4Entry> data = new HashMap<String,OrderCase4Entry>();
	ArrayList<MasterimplantEntry> masterimplantEntries = new ArrayList<MasterimplantEntry>();
	BubbleEditText editTotal;
	EditText inputSearch;
	Api api;
	AQuery aq;
	ProgressDialog dialog;
	ArrayList<OrderCase4Entry> arrayList = new ArrayList<OrderCase4Entry>();
	DataOrderDataset dataOrderDataset = DataOrderDataset.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setcase_master_implant);

		dialog = ProgressDialog.show(this, "","Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();
		
		init();
		
		strApiCall = api.getApiMasterImplant();
		
		ApiGetData();
		editTotal.setBubbleDrawableRight(android.R.drawable.presence_offline); 
		editTotal.setOnBubbleClickListener(new OnBubbleClickListener() {
			
			@Override
			public void onBubbleClick(View view, Hyperlink linkSpec) {
		        data.remove(String.valueOf(linkSpec.textSpan));
		    	editTotal.removeBubble(linkSpec);
			}
		});
		
		/**
		 * Enabling Search Filter
		 * */
		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,int arg3) {
				SetMasterImplantActivity.this.adapter.getFilter().filter(cs);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});
		
		btnNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				if (data.isEmpty()) {
					return;
				}

				for (String key : data.keySet()) {
					
					arrayList.add(new OrderCase4Entry(data.get(key).getImplant_id(),
							data.get(key).getImplant_name(),
							data.get(key).getDescription()));
				}
				
				dataOrderDataset.setOrderCase4Entries(arrayList);
				
				Intent intent = new Intent(SetMasterImplantActivity.this,SetcaseDetailActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			}
		});
	}
	
	private void ApiGetData() {
		
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<MasterimplantEntry> arrayList = new ArrayList<MasterimplantEntry>();
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = masterImplantPaser.getData(json);
						 masterimplantEntries = arrayList;
						 adapter = new MasterImplantsetAdapter(SetMasterImplantActivity.this, arrayList);
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
		list.setTextFilterEnabled(true);
		list.setOnItemClickListener(this);
		
		editTotal = (BubbleEditText) findViewById(R.id.editTotal);
		editTotal.setTypeface(Util.getBoldFont(this));
		editTotal.setTextSize(20f);
		
		inputSearch = (EditText) findViewById(R.id.editSearch);
		btnNext = (Button) findViewById(R.id.btnNext);
		btnNext.setTypeface(Util.getBoldFont(this));
		btnNext.setTextSize(50f);
		aq = new AQuery(this);
		api = new Api(this);
		masterImplantPaser = new MasterImplantPaser();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
		
		TextView name = (TextView) view.findViewById(R.id.name);
		TextView item = (TextView) view.findViewById(R.id.item);
		TextView desc = (TextView) view.findViewById(R.id.desc);
        String strName = name.getText().toString().trim();
        String strCheck = strName+ "(" + item.getText().toString() + ")";
        String strId = String.valueOf(id);
        String strDesc = desc.getText().toString();
        
        if (data.containsKey(strCheck)) {
			Toast.makeText(this, "Exist item", Toast.LENGTH_SHORT).show();
			return;
		}
        
        data.put(strCheck, new OrderCase4Entry(
        		strId,
				strName,
				strDesc));
        
		editTotal.addBubbleText(strCheck);
//		// set text to textView, including one that is wrapped in bubble
		editTotal.setText(editTotal.getText() + strCheck + " ");
	}
}
