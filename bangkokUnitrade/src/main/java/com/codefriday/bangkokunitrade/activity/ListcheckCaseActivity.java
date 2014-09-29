package com.codefriday.bangkokunitrade.activity;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.BiomaterialEntry;
import com.codefriday.bangkokunitrade.dataset.StatuscaseDetailEntry;
import com.codefriday.bangkokunitrade.dataset.StatuscaseDetailListEntry;
import com.codefriday.bangkokunitrade.dataset.UserDataset;
import com.codefriday.bangkokunitrade.dataset.InstrumentEntry;
import com.codefriday.bangkokunitrade.json.CheckcasedetailPaser;
import com.codefriday.bangkokunitrade.adapters.CheckcaseListAdapter;
import com.codefriday.bangkokunitrade.util.Api;
import com.codefriday.bangkokunitrade.util.ExpandableHeightListView;
import com.codefriday.bangkokunitrade.util.Util;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ListcheckCaseActivity extends Activity implements OnClickListener,OnTouchListener{
	private TextView textheader,textheaderInfo,texthospital,textdoctor,texttranspotstatus
			,textpatient,textHN,texttranspot,textsale,textDoctorTools,textAddTools;
	private LinearLayout layoutlistTools,layoutlistBiomaterial;
	private String strApiCall;
	private Api api;
	private EditText editTextSurgeryDate,editTextSurgeryTime;
	private Button buttonUpdate,buttonCancel;
	private AQuery aq;
	private CheckcasedetailPaser checkcasedetailPaser;
	private CheckcaseListAdapter adapter;
	private Bundle b;
	private ExpandableHeightListView listOrder;
	private ArrayList<StatuscaseDetailListEntry> orderListEntries = new ArrayList<StatuscaseDetailListEntry>();
	UserDataset dataset = UserDataset.getInstance();
	private ProgressDialog dialog;
	private int position = 0;
	private String order_id = null;
	static final int Datesurgery = 1;
	static final int Timesurgery  = 2;
	private int pYear;
	private int pMonth;
	private int pDay;
	private int hour;
	private int minute;
	private String am_pm;
	TimePicker timePicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checklist_tools);
		
		dialog = ProgressDialog.show(this, "","Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();
		
		/** Get the current date */
		final Calendar cal = Calendar.getInstance();
		pYear = cal.get(Calendar.YEAR);
		pMonth = cal.get(Calendar.MONTH);
		pDay = cal.get(Calendar.DAY_OF_MONTH);
		
		init();
		b = getIntent().getExtras();
		order_id = String.valueOf(b.getInt("order_id"));
		ApiOrderDetail(order_id);
		
	}

	private void init() {
		aq = new AQuery(this);
		api = new Api(this);
		checkcasedetailPaser = new CheckcasedetailPaser();
		
		listOrder = (ExpandableHeightListView) findViewById(R.id.listOrder);
		listOrder.setExpanded(true);
		
		layoutlistTools = (LinearLayout) findViewById(R.id.layoutlistTools);
		layoutlistBiomaterial = (LinearLayout) findViewById(R.id.layoutlistBiomaterial);
		
		editTextSurgeryDate = (EditText) findViewById(R.id.EditTextSurgeryDate);
		editTextSurgeryDate.setTypeface(Util.getBoldFont(this));
		editTextSurgeryDate.setTextSize(20f);
		
		editTextSurgeryDate.setOnTouchListener(this);
		
		editTextSurgeryTime = (EditText) findViewById(R.id.EditTextSurgeryTime);
		editTextSurgeryTime.setTypeface(Util.getBoldFont(this));
		editTextSurgeryTime.setTextSize(20f);
		
		editTextSurgeryTime.setOnTouchListener(this);
		
		textheader = (TextView) findViewById(R.id.textheader);
		textheader.setTypeface(Util.getBoldFont(this));
		textheader.setTextSize(30f);
		
		textheaderInfo = (TextView) findViewById(R.id.textheaderinfo);
		textheaderInfo.setTypeface(Util.getBoldFont(this));
		textheaderInfo.setTextSize(30f);
		
		texttranspotstatus = (TextView) findViewById(R.id.texttranspotstatus);
		texttranspotstatus.setTypeface(Util.getBoldFont(this));
		texttranspotstatus.setTextSize(20f);
		
		texthospital = (TextView) findViewById(R.id.texthospital);
		texthospital.setTypeface(Util.getBoldFont(this));
		texthospital.setTextSize(20f);
		
		textdoctor = (TextView) findViewById(R.id.textdoctor);
		textdoctor.setTypeface(Util.getBoldFont(this));
		textdoctor.setTextSize(20f);
		
		textpatient = (TextView) findViewById(R.id.textpatient);
		textpatient.setTypeface(Util.getBoldFont(this));
		textpatient.setTextSize(20f);
		
		textHN = (TextView) findViewById(R.id.textHN);
		textHN.setTypeface(Util.getBoldFont(this));
		textHN.setTextSize(20f);
		
		texttranspot = (TextView) findViewById(R.id.texttranspot);
		texttranspot.setTypeface(Util.getBoldFont(this));
		texttranspot.setTextSize(20f);
		
		textsale = (TextView) findViewById(R.id.textsale);
		textsale.setTypeface(Util.getBoldFont(this));
		textsale.setTextSize(20f);
		
		textDoctorTools = (TextView) findViewById(R.id.textDoctorTools);
		textDoctorTools.setTypeface(Util.getBoldFont(this));
		textDoctorTools.setTextSize(20f);
		
		textAddTools = (TextView) findViewById(R.id.textAddTools);
		textAddTools.setTypeface(Util.getBoldFont(this));
		textAddTools.setTextSize(20f);
		
		buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
		buttonUpdate.setTypeface(Util.getBoldFont(this));
		buttonUpdate.setTextSize(20f);
		
		buttonUpdate.setOnClickListener(this);
		
		buttonCancel = (Button) findViewById(R.id.buttonCancel);
		buttonCancel.setTypeface(Util.getBoldFont(this));
		buttonCancel.setTextSize(20f);
		
		buttonCancel.setOnClickListener(this);
		
		setCurrentTimeOnView();
	}
	
	// display current time
	public void setCurrentTimeOnView() {
 
		timePicker = new TimePicker(this);
 
		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		am_pm = (hour < 12) ? "AM" : "PM";
 
		// set current time into timepicker
		timePicker.setCurrentHour(hour);
		timePicker.setCurrentMinute(minute);
 
	}
	
	private void ApiOrderDetail(String order_id) {
		
		strApiCall = api.getApiorderDetail(order_id);
		
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<StatuscaseDetailEntry> arrayList = new ArrayList<StatuscaseDetailEntry>();
		    @Override
		    public void callback(String url, JSONObject json, AjaxStatus status) {
		    	
		    	if (json != null) {
		    		arrayList = checkcasedetailPaser.getData(json);
	    			texthospital.setText(arrayList.get(position).getHospital());
		    		textdoctor.setText(arrayList.get(position).getDoctor());
		    		editTextSurgeryDate.setText(arrayList.get(position).getSergery_date());
		    		editTextSurgeryTime.setText(arrayList.get(position).getSergery_time());
		    		textpatient.setText(arrayList.get(position).getPatient_name());
		    		textHN.setText(arrayList.get(position).getHn());
		    		texttranspot.setText(arrayList.get(position).getTransport());
		    		textsale.setText(arrayList.get(position).getSale());
		    		textDoctorTools.setText(arrayList.get(position).getInstrument_doctor());
		    		textAddTools.setText(arrayList.get(position).getAddons());
		    		texttranspotstatus.setText(arrayList.get(position).getStatus());
		    		
		    		orderListEntries = arrayList.get(position).getOrder();
		    		
		    		adapter = new CheckcaseListAdapter(ListcheckCaseActivity.this,orderListEntries,arrayList.get(position).getOrder_type());
		    		listOrder.setAdapter(adapter);
		    		
		    		for (int i = 0; i < arrayList.get(position).getInstrument().size(); i++) {
		    			InstrumentEntry entry = arrayList.get(position).getInstrument().get(i);
		    			TextView detail = new TextView(ListcheckCaseActivity.this);
						detail.setTypeface(Util.getBoldFont(ListcheckCaseActivity.this));
						detail.setPadding(5, 5, 5, 5);
						detail.setTextSize(20f);
						detail.setText(" no."+ entry.getNo() + " " + entry.getName());
		    			
		    			layoutlistTools.addView(detail);
					}
		    		
		    		for (int i = 0; i < arrayList.get(position).getBiomaterial().size(); i++) {
		    			BiomaterialEntry entry = arrayList.get(position).getBiomaterial().get(i);
		    			TextView detail = new TextView(ListcheckCaseActivity.this);
						detail.setTypeface(Util.getBoldFont(ListcheckCaseActivity.this));
						detail.setPadding(5, 5, 5, 5);
						detail.setTextSize(20f);
						detail.setText(" no."+ entry.getNo() + " " + entry.getBiomaterial() + " จำนวน " + entry.getAmount()  + " รายการ");
		    			
		    			layoutlistBiomaterial.addView(detail);
					}
		    	}else{
		    		Toast.makeText(getApplicationContext(),"Error: " + status.getMessage(), Toast.LENGTH_SHORT).show();
		    	}
		    	dialog.dismiss(); 
		    }
	    });
		
	}
	
	private void ApiOrderCancel() {
		strApiCall = api.getApiOrderCancel();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", order_id);
		
		aq.ajax(strApiCall,params, JSONObject.class, new AjaxCallback<JSONObject>() {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 Toast.makeText(ListcheckCaseActivity.this, status.getMessage(), Toast.LENGTH_SHORT).show();
						 finish();
					 }else{
						 Toast.makeText(ListcheckCaseActivity.this, status.getMessage(), Toast.LENGTH_SHORT).show();
						 
					 }
				 }catch(Exception e){
					 e.printStackTrace();
				 }finally{
					 dialog.dismiss();
				 }
			}
        });
	}
	
	private void ApiOrderUpdate() {
		strApiCall = api.getApiOrderUpdate();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", order_id);
		params.put("surgery_date",editTextSurgeryDate.getText().toString());
		params.put("surgery_time",editTextSurgeryTime.getText().toString());
		
		aq.ajax(strApiCall,params, JSONObject.class, new AjaxCallback<JSONObject>() {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 Toast.makeText(ListcheckCaseActivity.this, status.getMessage(), Toast.LENGTH_SHORT).show();
						 finish();
					 }else{
						 Toast.makeText(ListcheckCaseActivity.this, status.getMessage(), Toast.LENGTH_SHORT).show();
					 }
				 }catch(Exception e){
					 e.printStackTrace();
				 }finally{
					 dialog.dismiss();
				 }
			}
        });
	}
	
	private DatePickerDialog.OnDateSetListener pDateSurgerySetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
			pYear = year;
			pMonth = monthOfYear;
			pDay = dayOfMonth;
			
			updateDisplay(0);
		}

	};
	
	private void updateDisplay(int num) {
		switch (num) {
		case 0:
			editTextSurgeryDate.setText(new StringBuilder().append(pDay).append("/")
					.append(pMonth + 1).append("/").append(pYear).append(" "));
			break;

		default:
			break;
		}
	}
	
	private TimePickerDialog.OnTimeSetListener timeSurgeryPickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;
			am_pm = (hour < 12) ? "AM" : "PM";
//			// set current time into textview
			editTextSurgeryTime.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(minute))
					.append(" " + am_pm));
 
			// set current time into timepicker
			timePicker.setCurrentHour(hour);
			timePicker.setCurrentMinute(minute);
 
		}
	};
	
	private String pad(int c) {
		if (c >= 10)
		   return String.valueOf(c);
		else
		   return "0" + String.valueOf(c);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case Datesurgery:
			return new DatePickerDialog(this, pDateSurgerySetListener, pYear, pMonth,pDay);
		case Timesurgery:
			return new TimePickerDialog(this, timeSurgeryPickerListener, hour, minute,false);
		}
		return super.onCreateDialog(id); 
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonUpdate:
			 ApiOrderUpdate();
			break;
			
		case R.id.buttonCancel:
			 ApiOrderCancel();
			break;

		default:
			break;
		}
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (v.getId()) {
		case R.id.EditTextSurgeryDate:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				showDialog(Datesurgery);
				return true;
			}
			break;
			
		case R.id.EditTextSurgeryTime:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				showDialog(Timesurgery);
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}

	
}
