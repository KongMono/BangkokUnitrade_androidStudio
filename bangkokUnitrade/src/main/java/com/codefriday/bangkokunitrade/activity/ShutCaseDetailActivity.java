package com.codefriday.bangkokunitrade.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.ShutCaseDetailEntry;
import com.codefriday.bangkokunitrade.dataset.implantEntry;
import com.codefriday.bangkokunitrade.json.ShutcaseDetailPaser;
import com.codefriday.bangkokunitrade.adapters.ShutCaseDeatilListAdapter;
import com.codefriday.bangkokunitrade.util.Api;
import com.codefriday.bangkokunitrade.util.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShutCaseDetailActivity extends Activity implements OnTouchListener {
	private ListView list;
	private Button buttonNext;
	public TextView textSurgeryDate,textSurgeryTime,textpatient,texthn,textdoctor,textheader;
	AlertDialog helpDialog;
	ShutCaseDeatilListAdapter adapter;
	ShutcaseDetailPaser shutcaseDetailPaser;
	ArrayList<implantEntry> arrayListTotal = new ArrayList<implantEntry>();
	ArrayList<ShutCaseDetailEntry> caseDetailEntries = new ArrayList<ShutCaseDetailEntry>();
	private String strApiCall;
	Api api;
	AQuery aq;
	private Bundle b;
	ProgressDialog dialog;
	String case_id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shutcase_detail);

		dialog = ProgressDialog.show(this, "",
				"Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();
		
		init();
		case_id = String.valueOf(b.getInt("id"));
		ApiGetCaseList(case_id);
		
	}

	private void init() {
		aq = new AQuery(this);
		api = new Api(this);
		b = getIntent().getExtras();
		shutcaseDetailPaser = new ShutcaseDetailPaser();
		list = (ListView) findViewById(R.id.list);
		
		textSurgeryDate = (TextView)findViewById(R.id.textSurgeryDate);
		textSurgeryDate.setTypeface(Util.getBoldFont(this));
		textSurgeryDate.setTextSize(25f);

		textSurgeryTime = (TextView)findViewById(R.id.textSurgeryTime);
		textSurgeryTime.setTypeface(Util.getBoldFont(this));
		textSurgeryTime.setTextSize(25f);
		
		texthn = (TextView)findViewById(R.id.texthn);
		texthn.setTypeface(Util.getBoldFont(this));
		texthn.setTextSize(25f);
		
		textpatient = (TextView)findViewById(R.id.textpatient);
		textpatient.setTypeface(Util.getBoldFont(this));
		textpatient.setTextSize(25f);
		
		textdoctor = (TextView)findViewById(R.id.textdoctor);
		textdoctor.setTypeface(Util.getBoldFont(this));
		textdoctor.setTextSize(25f);
		
		buttonNext = (Button) findViewById(R.id.buttonNext);
		buttonNext.setTypeface(Util.getBoldFont(this));
		buttonNext.setTextSize(50f);
		buttonNext.setOnTouchListener(this);
	}
	
	private void ApiGetCaseList(String case_id) {
		strApiCall = api.getApiCasedetail(case_id);
		
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<ShutCaseDetailEntry> arrayList = new ArrayList<ShutCaseDetailEntry>();
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = shutcaseDetailPaser.getData(json);
						 caseDetailEntries = arrayList;
						 textSurgeryDate.setText("วันที่ผ่าตัด: " + arrayList.get(0).getSurgery_date());
						 textSurgeryTime.setText("เวลาผ่าตัด: " + arrayList.get(0).getSurgery_time());
						 texthn.setText("HN: " + arrayList.get(0).getHn());
						 textpatient.setText("ชื่อคนไข้: " + arrayList.get(0).getPatient());
						 textdoctor.setText("แพทย์ผู้ผ่าตัด: " + arrayList.get(0).getDoctor());
						 
						 adapter = new ShutCaseDeatilListAdapter(ShutCaseDetailActivity.this, arrayList.get(0).getImplantList());
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
	
	private void ApiSendData() {
		strApiCall = api.getApiCaseInform();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("case_id", case_id);
		
		String data = prepareData();
		
		params.put("data",data);
		
		aq.ajax(strApiCall,params, JSONObject.class, new AjaxCallback<JSONObject>() {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 String message = json.getString("message");
						 String code = json.getString("code");
						 if (code.equalsIgnoreCase("200")) {
							 Toast.makeText(ShutCaseDetailActivity.this, message, Toast.LENGTH_SHORT).show();
							 Log.d("ApiSendData", json.toString());
							 finish();
						 }else{
							 arrayListTotal.clear();
							 Toast.makeText(ShutCaseDetailActivity.this, message, Toast.LENGTH_SHORT).show();
						 }
					 }
					 
				 }catch(Exception e){
					 e.printStackTrace();
				 }
			}
        });
	}
	
	private String prepareData() {
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < arrayListTotal.size(); i++) {
			implantEntry entry = arrayListTotal.get(i);
			br.append("|")
			  .append(entry.getMaster_implants_id())
			  .append(",")
			  .append(entry.getUse());
		}
		String value = br.substring(1);
		Log.d("catalogEntries: ",value);
		return value;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			switch (v.getId()) {
			case R.id.buttonNext:
				for (int i = 0; i < list.getAdapter().getCount(); i++) {
					
					implantEntry item = (implantEntry)list.getAdapter().getItem(i);
					
				    if (!item.getUse().matches("") && !item.getUse().matches("0") ) {
				    	
				    	int checkInt = Integer.parseInt(item.getUse());
				    	item.setUse((String.valueOf(checkInt)));
				    	arrayListTotal.add(item);
					}
				}
				if (arrayListTotal.isEmpty()) {
					Toast.makeText(this, "กรุณาใส่จำนวนอย่างน้อย 1 รายการ", Toast.LENGTH_SHORT).show();
					return false;
				}else{
					ApiSendData();
				}
				break;

			default:
				break;
			};
		}
		return false;
	}

	
}
