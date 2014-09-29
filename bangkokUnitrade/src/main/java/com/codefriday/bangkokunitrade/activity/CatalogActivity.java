package com.codefriday.bangkokunitrade.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.CatalogEntry;
import com.codefriday.bangkokunitrade.json.CatalogPaser;
import com.codefriday.bangkokunitrade.adapters.CatalogAdapter;
import com.codefriday.bangkokunitrade.util.Api;
import com.codefriday.bangkokunitrade.util.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CatalogActivity extends Activity implements OnClickListener{
	private TextView textheader;
	private ListView list;
	private String strApiCall;
	private EditText editto,editsubject,editConpose;
	private Api api;
	private AQuery aq;
	private Button buttonNext,btnSend,btnCancel;
	CatalogAdapter catalogAdapter;
	CatalogPaser catalogPaser;
	ArrayList<CatalogEntry> catalogEntries = new ArrayList<CatalogEntry>();
	AlertDialog helpDialog;
	String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalog);

		init();
		
		getApicatalog();
	}

	private void getApicatalog() {
		strApiCall = api.getApiCatalogs();
		
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<CatalogEntry> arrayList = new ArrayList<CatalogEntry>();
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = catalogPaser.getData(json);
						 catalogAdapter = new CatalogAdapter(CatalogActivity.this, arrayList);
						 list.setAdapter(catalogAdapter);
					 }
					 
				 }catch(Exception e){
					 e.printStackTrace();
				 }
			}
        });
	}

	private void init() {
		api = new Api(this);
		aq = new AQuery(this);
		list = (ListView) findViewById(R.id.list);
		catalogPaser = new CatalogPaser();
		textheader = (TextView) findViewById(R.id.textheader);
		textheader.setTypeface(Util.getBoldFont(this));
		textheader.setTextSize(30f);
		
		buttonNext = (Button) findViewById(R.id.buttonNext);
		buttonNext.setTypeface(Util.getBoldFont(this));
		buttonNext.setTextSize(30f);
		
		buttonNext.setOnClickListener(this);
	}
	
	private void showPopUp() {
		
		final Builder helpBuilder = new Builder(this);
		final LayoutInflater inflater = getLayoutInflater();
		final View view = inflater.inflate(R.layout.activity_catalog_email, null);

		TextView header = (TextView) view.findViewById(R.id.textheader);
		header.setTypeface(Util.getBoldFont(this));
		header.setTextSize(30f);
		
		editto = (EditText) view.findViewById(R.id.editto);
		editto.setTypeface(Util.getBoldFont(this));
		editto.setTextSize(30f); 
		
		editsubject = (EditText) view.findViewById(R.id.editsubject);
		editsubject.setTypeface(Util.getBoldFont(this));
		editsubject.setTextSize(30f); 
		
		editConpose = (EditText) view.findViewById(R.id.editConpose);
		editConpose.setTypeface(Util.getBoldFont(this));
		editConpose.setTextSize(30f); 
		
		btnSend = (Button) view.findViewById(R.id.buttonSend);
		btnSend.setTypeface(Util.getBoldFont(this));
		btnSend.setTextSize(30f);
		
		btnCancel = (Button) view.findViewById(R.id.buttonCancel);
		btnCancel.setTypeface(Util.getBoldFont(this));
		btnCancel.setTextSize(30f);
		

		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		helpBuilder.setView(view);
		
		
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				helpDialog.dismiss();
				catalogEntries.clear();
			}
		});
		
		btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (!editto.getText().toString().matches(emailPattern)){
					Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
					return;
				}
				
				getApiSendMail();
			}
		});

	    helpDialog = helpBuilder.create();
	    helpDialog.setCancelable(false);
		helpDialog.show();

	}
	
	private void getApiSendMail() {
		
		strApiCall = api.getApiSendMail();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("to", editto.getText().toString());
		params.put("subject", editsubject.getText().toString());
		params.put("message",editConpose.getText().toString());
		
		String value = prepareData();
		
		params.put("data",value);
		
		aq.ajax(strApiCall,params, JSONObject.class, new AjaxCallback<JSONObject>() {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 JSONObject responseObject = json.getJSONObject("content");
						 String message = responseObject.getString("message");
						 Toast.makeText(CatalogActivity.this, message, Toast.LENGTH_SHORT).show();
						 helpDialog.dismiss();
					 }
					 
				 }catch(Exception e){
					 e.printStackTrace();
				 }
			}
        });
	}

	private String prepareData() {
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < catalogEntries.size(); i++) {
			CatalogEntry entry = catalogEntries.get(i);
			br.append("|")
			  .append(entry.getId());
		}
		String value = br.substring(1);
		Log.d("catalogEntries: ",value);
		return value;
	}


	@Override
	public void onClick(View v) {
		
		if (v.getId() == R.id.buttonNext) {
			catalogEntries.clear();
			for (int i = 0; i < list.getAdapter().getCount(); i++) {
				CatalogEntry item = (CatalogEntry)list.getAdapter().getItem(i);
				if (item.isIschecked()) {
					catalogEntries.add(item);
				}
			}
			
			if (catalogEntries.isEmpty()) {
				return;
			}else{
				showPopUp();
			}
			
			
		}
	}

}
