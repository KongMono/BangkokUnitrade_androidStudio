package com.codefriday.bangkokunitrade.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.DataOrderDataset;
import com.codefriday.bangkokunitrade.dataset.DoctorToolsEntry;
import com.codefriday.bangkokunitrade.dataset.HospitalEntry;
import com.codefriday.bangkokunitrade.dataset.OrderCase1Entry;
import com.codefriday.bangkokunitrade.dataset.OrderCase2Entry;
import com.codefriday.bangkokunitrade.dataset.OrderCase3Entry;
import com.codefriday.bangkokunitrade.dataset.OrderCase4Entry;
import com.codefriday.bangkokunitrade.dataset.PopupSetEntry;
import com.codefriday.bangkokunitrade.dataset.ToolsSetimplantEntry;
import com.codefriday.bangkokunitrade.dataset.TransportsEntry;
import com.codefriday.bangkokunitrade.dataset.UserDataset;
import com.codefriday.bangkokunitrade.json.DoctorPaser;
import com.codefriday.bangkokunitrade.json.DoctorToolsParser;
import com.codefriday.bangkokunitrade.json.HospitalPaser;
import com.codefriday.bangkokunitrade.json.PopBiomaterialsPaser;
import com.codefriday.bangkokunitrade.json.ToolssetPaser;
import com.codefriday.bangkokunitrade.json.TransportsParser;
import com.codefriday.bangkokunitrade.adapters.DoctorToolsAdapter;
import com.codefriday.bangkokunitrade.adapters.HospitalAdapter;
import com.codefriday.bangkokunitrade.adapters.PopupBiomaterialAdapter;
import com.codefriday.bangkokunitrade.adapters.ToolsImplantsetAdapter;
import com.codefriday.bangkokunitrade.adapters.TransportsAdapter;
import com.codefriday.bangkokunitrade.util.Api;
import com.codefriday.bangkokunitrade.util.ExpandableHeightListView;
import com.codefriday.bangkokunitrade.util.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog.Builder;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SetcaseDetailActivity extends Activity implements OnTouchListener{
	private Spinner spinnerHospital,spinnerDoctor,spinnerTransport;
	private EditText editTextdoctor,editTextpatient, HN,requireDate,requireTime,surgeryDate,surgeryTime,EditTextDoctorPoint,EditCheck2;
	private Button buttonNext,btnSend,btnchecklist;
	private RadioGroup radioTools,radioCase,radioBorrow;
	private RadioButton radioButtonTools1, radioButtonTools2,radioButtonTools3,
			radioButtonCase1,radioButtonCase2,
			radioButtonBorrow1,radioButtonBorrow2,radioButtonBorrow3,radioButtonBorrow4;
	private CheckBox check1,check2;
	private TextView textheader,textheadCaseMore,textheadCase,textheadTool,textheadBorrow,textCheck1;
	private ExpandableHeightListView doctorToolsList,listToolSet;
	private LinearLayout layoutEdit,layoutcheck1,layoutListConfirm;
	//Master Detail
	//Tools
	private ToolsImplantsetAdapter toolsImplantsetAdapter;
	private ToolssetPaser toolssetPaser;
	//Hospital,Doctor
	private HospitalAdapter hospitalAdapter,doctorAdapter;
	private HospitalPaser hospitalPaser;
	private DoctorPaser doctorPaser;
	private ArrayList<HospitalEntry> hospitalEntries,doctorEntries;
	// Trasport
	private TransportsAdapter transportsAdapter;
	private ArrayList<TransportsEntry> transportsEntries;
	private TransportsParser transportsParser;
	// DoctorTools
	private DoctorToolsParser doctorToolsParser;
	private DoctorToolsAdapter doctorToolsAdapter;
	// Biomaterials
	PopupBiomaterialAdapter biomaterialsAdapter;
	private PopBiomaterialsPaser popBiomaterialsPaser;
	ArrayList<OrderCase3Entry> biomaterialsList  = new ArrayList<OrderCase3Entry>();
	private int pYear;
	private int pMonth;
	private int pDay;
	private int hour;
	private int minute;
	private String am_pm;
	static final int Datesurgery = 1;
	static final int DateRequire = 2;
	static final int Timesurgery  = 3;
	static final int TimeRequire = 4;
	AlertDialog helpDialog;
	TimePicker timePicker;
	private String strApiCall;
	String Hospital = null;
	String Doctor = null;
	String Transfer = null;
	String master_implants_id = null;
	String doctor_id = null;
	String Transfer_id = null;
	String hospital_id = null;
	String radioTools_id = null;
	String radioCase_id = null;
	String radioBorrow_id = null;
	String tool_id = null;
	String implantName = null;
	String order_data = null;
	String biomaterials_detail = null;
	Api api;
	AQuery aq;
	UserDataset dataset = UserDataset.getInstance();
	int FixOneTouch = 0;
	ProgressDialog dialog;
	DataOrderDataset dataOrderDataset = DataOrderDataset.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setcase_detail);

		dialog = ProgressDialog.show(this, "","Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();
		
		init();

		/** Get the current date */
		final Calendar cal = Calendar.getInstance();
		pYear = cal.get(Calendar.YEAR);
		pMonth = cal.get(Calendar.MONTH);
		pDay = cal.get(Calendar.DAY_OF_MONTH);
		
		spinnerHospital.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				hospital_id = String.valueOf(hospitalEntries.get(position).getId());
				Hospital = hospitalEntries.get(position).getName();
				ApiGetDataDoctor(hospital_id);
		    }
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		spinnerTransport.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				Transfer_id = String.valueOf(transportsEntries.get(position).getId());
				Transfer = transportsEntries.get(position).getTransport_type();
				ApiGetDataDoctor(hospital_id);
		    }
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		ApiGetDataHospital();
		ApiTransportsType();
		setCurrentTimeOnView();
		
		order_data = checkPrepareData();
	}

	private void init()  {
		
		aq = new AQuery(this);
		api = new Api(this);
		hospitalPaser = new HospitalPaser();
		doctorPaser = new DoctorPaser();
		transportsParser = new TransportsParser();
		doctorToolsParser = new DoctorToolsParser();
		popBiomaterialsPaser = new PopBiomaterialsPaser();
		toolssetPaser = new ToolssetPaser();
		dataset = UserDataset.getInstance();
		textheader = (TextView) findViewById(R.id.textheader);
		textheader.setTypeface(Util.getBoldFont(this));
		textheader.setTextSize(30f);
		
		layoutcheck1 = (LinearLayout) findViewById(R.id.layoutcheck1);
		
		doctorToolsList = (ExpandableHeightListView) findViewById(R.id.list);
		doctorToolsList.setExpanded(true);
		doctorToolsList.setSmoothScrollbarEnabled(true);
		
		listToolSet = (ExpandableHeightListView) findViewById(R.id.listToolSet);
		listToolSet.setExpanded(true);
		listToolSet.setSmoothScrollbarEnabled(true);
		
		textCheck1 = (TextView) findViewById(R.id.textCheck1);
		textCheck1.setTypeface(Util.getFont(this));
		textCheck1.setTextSize(25f);
		
		textheadCaseMore = (TextView) findViewById(R.id.textheadCaseMore);
		textheadCaseMore.setTypeface(Util.getBoldFont(this));
		textheadCaseMore.setTextSize(30f);
		
		textheadCase = (TextView) findViewById(R.id.textheadCase);
		textheadCase.setTypeface(Util.getBoldFont(this));
		textheadCase.setTextSize(30f);
		
		textheadTool = (TextView) findViewById(R.id.textheadTool);
		textheadTool.setTypeface(Util.getBoldFont(this));
		textheadTool.setTextSize(30f);
		
		textheadBorrow = (TextView) findViewById(R.id.textheadBorrow);
		textheadBorrow.setTypeface(Util.getBoldFont(this));
		textheadBorrow.setTextSize(30f);
		
		spinnerHospital = (Spinner) findViewById(R.id.spinnerHospital);
		spinnerTransport = (Spinner) findViewById(R.id.spinnerTransport);
		
		editTextdoctor = (EditText) findViewById(R.id.EditTextDoctor);
		editTextdoctor.setTypeface(Util.getFont(this));
		editTextdoctor.setTextSize(25f);
		editTextdoctor.setHint(getResources().getString(R.string.Doctor));
		editTextdoctor.setOnTouchListener(this);
		
		editTextpatient = (EditText) findViewById(R.id.EditTextName);
		editTextpatient.setTypeface(Util.getFont(this));
		editTextpatient.setTextSize(25f);
		editTextpatient.setHint(getResources().getString(R.string.patient));
		
		HN = (EditText) findViewById(R.id.EditTextHN);
		HN.setTypeface(Util.getFont(this));
		HN.setTextSize(25f);
		HN.setHint(getResources().getString(R.string.HN));
		
		
		EditCheck2 = (EditText) findViewById(R.id.EditCheck2);
		EditCheck2.setTypeface(Util.getFont(this));
		EditCheck2.setEnabled(false);
		EditCheck2.setTextSize(25f);
		
		requireDate = (EditText) findViewById(R.id.EditDateRequire);
		requireDate.setTypeface(Util.getFont(this));
		requireDate.setTextSize(25f);
		requireDate.setOnTouchListener(this);
		
		requireTime = (EditText) findViewById(R.id.EditTimeRequire);
		requireTime.setTypeface(Util.getFont(this));
		requireTime.setTextSize(25f);
		requireTime.setOnTouchListener(this);
		
		surgeryDate = (EditText) findViewById(R.id.EditDateSurgery);
		surgeryDate.setTypeface(Util.getFont(this));
		surgeryDate.setTextSize(25f);
		surgeryDate.setOnTouchListener(this);
		
		
		surgeryTime = (EditText) findViewById(R.id.EditTimeSurgery);
		surgeryTime.setTypeface(Util.getFont(this));
		surgeryTime.setTextSize(25f);
		surgeryTime.setOnTouchListener(this);
		
		buttonNext = (Button) findViewById(R.id.buttonNext);
		buttonNext.setTypeface(Util.getBoldFont(this));
		buttonNext.setTextSize(50f);
		buttonNext.setOnTouchListener(this);
		
		radioTools = (RadioGroup) findViewById(R.id.radioTools);
		radioCase = (RadioGroup) findViewById(R.id.radioCase);
		radioBorrow = (RadioGroup) findViewById(R.id.radioBorrow);
		
		radioButtonTools1 = (RadioButton) findViewById(R.id.radioButtonTools1);
		radioButtonTools1.setTypeface(Util.getFont(this));
		radioButtonTools1.setTextSize(25f);
		radioButtonTools1.setOnTouchListener(this);

		radioButtonTools2 = (RadioButton) findViewById(R.id.radioButtonTools2);
		radioButtonTools2.setTypeface(Util.getFont(this));
		radioButtonTools2.setTextSize(25f);
		radioButtonTools2.setOnTouchListener(this);
		
		radioButtonTools3 = (RadioButton) findViewById(R.id.radioButtonTools3);
		radioButtonTools3.setTypeface(Util.getFont(this));
		radioButtonTools3.setTextSize(25f);
		radioButtonTools3.setOnTouchListener(this);

		radioButtonCase1 = (RadioButton) findViewById(R.id.radioButtonCase1);
		radioButtonCase1.setTypeface(Util.getFont(this));
		radioButtonCase1.setTextSize(25f);

		radioButtonCase2 = (RadioButton) findViewById(R.id.radioButtonCase2);
		radioButtonCase2.setTypeface(Util.getFont(this));
		radioButtonCase2.setTextSize(25f);
		
		// Borrow
		radioButtonBorrow1 = (RadioButton) findViewById(R.id.radioButtonBorrow1);
		radioButtonBorrow1.setTypeface(Util.getFont(this));
		radioButtonBorrow1.setTextSize(25f);
		
		radioButtonBorrow2 = (RadioButton) findViewById(R.id.radioButtonBorrow2);
		radioButtonBorrow2.setTypeface(Util.getFont(this));
		radioButtonBorrow2.setTextSize(25f);

		radioButtonBorrow3 = (RadioButton) findViewById(R.id.radioButtonBorrow3);
		radioButtonBorrow3.setTypeface(Util.getFont(this));
		radioButtonBorrow3.setTextSize(25f);
		
		radioButtonBorrow4 = (RadioButton) findViewById(R.id.radioButtonBorrow4);
		radioButtonBorrow4.setTypeface(Util.getFont(this));
		radioButtonBorrow4.setTextSize(25f);
		
		check1 = (CheckBox) findViewById(R.id.check1);
		check1.setTypeface(Util.getFont(this));
		check1.setTextSize(25f);
		
		check2 = (CheckBox) findViewById(R.id.check2);
		check2.setTypeface(Util.getFont(this));
		check2.setTextSize(25f);
		
		
		check1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					ApiBiomaterialsSetList();
				}else{
					textCheck1.setVisibility(View.GONE);
					layoutcheck1.removeAllViews();
					layoutcheck1.setVisibility(View.GONE);
					biomaterialsList.clear();
				}
				
			}
		});
		
		check2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					EditCheck2.setEnabled(true);
				}else{
					EditCheck2.setText(null);
					EditCheck2.setEnabled(false);
				}
				
			}
		});
		
		radioTools.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				int pos = radioTools.indexOfChild(findViewById(checkedId));
				
				switch (pos) {
				case 0:
					tool_id = "0";
					listToolSet.setVisibility(View.GONE);
					break;
				case 1:
					tool_id = "1";
					ApiGetDataToolSet();
					break;
				case 2:
					tool_id = "2";
					listToolSet.setVisibility(View.GONE);
					break;

				default:
					break;
				}

			}
		});
	}
	
	private void showPopUpBiomaterials(PopupBiomaterialAdapter biomaterialsAdapter) {
		
		final LayoutInflater inflater = getLayoutInflater();
		final View view = inflater.inflate(R.layout.layout_confirm_biomaterial, null);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		TextView textheader = (TextView) view.findViewById(R.id.textheader);
		textheader.setTypeface(Util.getBoldFont(this));
		textheader.setTextSize(30f);
		textheader.setText("Biomaterials");
		
		final ListView list = (ListView) view.findViewById(R.id.list);
		list.setAdapter(biomaterialsAdapter);
		Util.setListViewHeightBasedOnChildren(list);
		
		Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
		btnCancel.setTypeface(Util.getBoldFont(this));
		btnCancel.setTextSize(30f);
		
		
		Button btnNext = (Button) view.findViewById(R.id.btnNext);
		btnNext.setTypeface(Util.getBoldFont(this));
		btnNext.setTextSize(30f);
		
		
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				biomaterialsList.clear();
				check1.setChecked(false);
				layoutcheck1.setVisibility(View.GONE);
				layoutcheck1.removeAllViews();
				Log.d("biomaterialsList Size", String.valueOf(biomaterialsList.size()));
			}
		});
		
		btnNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int checkInt = 0;
				StringBuilder br = new StringBuilder();
				br.append("(");
				for (int i = 0; i < list.getAdapter().getCount(); i++) {
					PopupSetEntry item =  (PopupSetEntry)list.getAdapter().getItem(i);
					
					Log.d("item: ",item.getName() + "," + item.getQty());
					
				    if (!item.getQty().matches("") && !item.getQty().matches("0") ) {
				    	checkInt = Integer.parseInt(item.getQty());
				    	item.setQty(String.valueOf(checkInt));
				    	
				    	 biomaterialsList.add(new OrderCase3Entry(
				    			 String.valueOf(item.getId()),
				    			 item.getName(),
				    			 item.getQty()));
				    	 
				    	 br.append(item.getName()).append("=").append(item.getQty()).append(",");
					}
				}
				
				if (biomaterialsList.isEmpty()) {
					Toast.makeText(SetcaseDetailActivity.this, "กรุณาใส่จำนวนอย่างน้อย 1 รายการ", Toast.LENGTH_SHORT).show();
					return;
				}
				String b =  br.toString().substring(0,br.toString().length() - 1); 
				b = b.concat(")");
				
				textCheck1.setText(b);
				textCheck1.setVisibility(View.VISIBLE);
				layoutcheck1.setVisibility(View.GONE);
				layoutcheck1.removeAllViews();
				Log.d("biomaterialsList Size", String.valueOf(biomaterialsList.size()));
			}
		});
		
		layoutcheck1.addView(view);
		layoutcheck1.setVisibility(View.VISIBLE);
		
	}
	
	

	private void ApiBiomaterialsSetList() {
		strApiCall = api.getApiBiomaterialsSetList();
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<PopupSetEntry> arrayList = new ArrayList<PopupSetEntry>();
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = popBiomaterialsPaser.getData(json);
						 biomaterialsAdapter = new PopupBiomaterialAdapter(SetcaseDetailActivity.this, arrayList);
						 showPopUpBiomaterials(biomaterialsAdapter);
					 }
					 
				 }catch(Exception e){
					 e.printStackTrace();
				 }finally{
					 dialog.dismiss();
				 }
			}
        });
	}
	
	private void ApiGetDataToolSet() {
		strApiCall = api.getApiToolSet();
		
		Map<String, Object> params = new HashMap<String, Object>();
		String strTools  = checkPrepareCallToolsSet();
		params.put("type",dataOrderDataset.getOrder_type());
		params.put("data",strTools);
		
		aq.ajax(strApiCall,params, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<ToolsSetimplantEntry> arrayList = new ArrayList<ToolsSetimplantEntry>();
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = toolssetPaser.getData(json);
						 toolsImplantsetAdapter = new ToolsImplantsetAdapter(SetcaseDetailActivity.this, arrayList);
						 listToolSet.setAdapter(toolsImplantsetAdapter);
						 listToolSet.setVisibility(View.VISIBLE);
					 }
				 }catch(Exception e){
					 e.printStackTrace();
				 }
			}
        });
	}
	
	
	private void ApiGetDataHospital() {
		strApiCall = api.getApiHospital(dataset.getUser_id());
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<HospitalEntry> arrayList = new ArrayList<HospitalEntry>();
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = hospitalPaser.getData(json);
						 hospitalEntries = arrayList;
						 hospitalAdapter = new HospitalAdapter(SetcaseDetailActivity.this, arrayList);
						 spinnerHospital.setAdapter(hospitalAdapter);
					 }
					 
				 }catch(Exception e){
					 e.printStackTrace();
				 }
			}
        });
	}
	
	private void ApiGetDataDoctor(String hospital_id) {
		strApiCall = api.getApiDoctorList(hospital_id);
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<HospitalEntry> arrayList = new ArrayList<HospitalEntry>();
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = doctorPaser.getData(json);
						 doctorEntries = arrayList;
						 doctorAdapter = new HospitalAdapter(SetcaseDetailActivity.this, arrayList);
					 }
				 }catch(Exception e){
					 e.printStackTrace();
				 }finally{
					 dialog.dismiss();
				 }
			}
        });
	}
	
	private void ApiDoctorsTools(String doctor_id) {
		
		if (doctor_id.equalsIgnoreCase("0")) {
			doctorToolsList.setAdapter(null);
		}else{
			strApiCall = api.getApiDoctorsTools(doctor_id);
			aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
				ArrayList<DoctorToolsEntry> arrayList = new ArrayList<DoctorToolsEntry>();
				@Override
				public void callback(String url, JSONObject json, AjaxStatus status) {
					 try {
						 if (json != null) {
							 arrayList = doctorToolsParser.getData(json);
							 doctorToolsAdapter = new DoctorToolsAdapter(SetcaseDetailActivity.this, arrayList);
							 doctorToolsList.setAdapter(doctorToolsAdapter);
						 }
					 }catch(Exception e){
						 e.printStackTrace();
					 }finally{
						 dialog.dismiss();
					 }
				}
	        });
		}
	}
	
	private void ApiTransportsType() {
		strApiCall = api.getApiTransportsType();
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			ArrayList<TransportsEntry> arrayList = new ArrayList<TransportsEntry>();
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = transportsParser.getData(json);
						 transportsEntries = arrayList;
						 transportsAdapter = new TransportsAdapter(SetcaseDetailActivity.this, arrayList);
						 spinnerTransport.setAdapter(transportsAdapter);
					 }
				 }catch(Exception e){
					 e.printStackTrace();
				 }finally{
					 dialog.dismiss();
				 }
			}
        });
	}
	
	// display current time
	public void setCurrentTimeOnView() {
 
		timePicker = new TimePicker(this);
 
		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		am_pm = (hour < 12) ? "AM" : "PM";
 
//		// set current time into textview
//		surgeryTime.setText(
//                    new StringBuilder().append(pad(hour))
//                                       .append(":").append(pad(minute))
//                                       .append(" " + am_pm));
 
		// set current time into timepicker
		timePicker.setCurrentHour(hour);
		timePicker.setCurrentMinute(minute);
 
	}
 
 
	private TimePickerDialog.OnTimeSetListener timeSurgeryPickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;
			am_pm = (hour < 12) ? "AM" : "PM";
//			// set current time into textview
			surgeryTime.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(minute))
					.append(" " + am_pm));
 
			// set current time into timepicker
			timePicker.setCurrentHour(hour);
			timePicker.setCurrentMinute(minute);
 
		}
	};
	

	private TimePickerDialog.OnTimeSetListener timeRequirePickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;
			am_pm = (hour < 12) ? "AM" : "PM";
//			// set current time into textview
			requireTime.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(minute))
					.append(" " + am_pm));
 
			// set current time into timepicker
			timePicker.setCurrentHour(hour);
			timePicker.setCurrentMinute(minute);
 
		}
	};
 
	private static String pad(int c) {
		if (c >= 10)
		   return String.valueOf(c);
		else
		   return "0" + String.valueOf(c);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
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
	
	private DatePickerDialog.OnDateSetListener pDateReQuireSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
			pYear = year;
			pMonth = monthOfYear;
			pDay = dayOfMonth;
			
			updateDisplay(1);
		}

	};

	private void updateDisplay(int num) {
		switch (num) {
		case 0:
			surgeryDate.setText(new StringBuilder().append(pDay).append("/")
					.append(pMonth + 1).append("/").append(pYear).append(" "));
			break;

		case 1:
			requireDate.setText(new StringBuilder().append(pDay).append("/")
					.append(pMonth + 1).append("/").append(pYear).append(" "));
			break;

		default:
			break;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case Datesurgery:
			return new DatePickerDialog(this, pDateSurgerySetListener, pYear, pMonth,pDay);
		case DateRequire:
			return new DatePickerDialog(this, pDateReQuireSetListener, pYear, pMonth,pDay);
		case Timesurgery:
			return new TimePickerDialog(this, timeSurgeryPickerListener, hour, minute,false);
		case TimeRequire:
			return new TimePickerDialog(this, timeRequirePickerListener, hour, minute,false);
		}
		return super.onCreateDialog(id); 
	}
	
	private void showPopUpdoctor() {
		
		final Builder helpBuilder = new Builder(this);
		final LayoutInflater inflater = getLayoutInflater();
		final View view = inflater.inflate(R.layout.layout_confirm_doctor, null);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		spinnerDoctor = (Spinner) view.findViewById(R.id.spinnerDoctor);
		EditTextDoctorPoint = (EditText) view.findViewById(R.id.EditTextDoctorPoint);
		layoutEdit = (LinearLayout) view.findViewById(R.id.layoutEdit);
		Button buttonNext = (Button) view.findViewById(R.id.buttonNext);
		buttonNext.setTypeface(Util.getBoldFont(this));
		buttonNext.setTextSize(30f);
		
		spinnerDoctor.setAdapter(doctorAdapter);
		
		spinnerDoctor.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				doctor_id = String.valueOf(doctorEntries.get(position).getId());
				if (doctor_id.equalsIgnoreCase("0")) {
					layoutEdit.setVisibility(View.VISIBLE);
				}else{
					Doctor = String.valueOf(doctorEntries.get(position).getName());
					layoutEdit.setVisibility(View.GONE);
				}
		    }
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		buttonNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (doctor_id != null) {
					if (doctor_id.equalsIgnoreCase("0")) {
						Doctor = EditTextDoctorPoint.getText().toString();
					}
					ApiDoctorsTools(doctor_id);
					editTextdoctor.setText(Doctor);
				}
				helpDialog.dismiss();
				
			}
		});
		
		helpBuilder.setView(view);
		
		helpDialog = helpBuilder.create();
		helpDialog.setCancelable(false);
		helpDialog.show();
	}

	private void showPopUpComfirm() {

		final Builder helpBuilder = new Builder(this);
		final LayoutInflater inflater = getLayoutInflater();
		final View view = inflater.inflate(R.layout.layout_confirm, null);
		
		layoutListConfirm = (LinearLayout) view.findViewById(R.id.layoutListConfirm);
		
		checkPrepareListConFirm(layoutListConfirm);
		
    	TextView textheader = (TextView) view.findViewById(R.id.textheader);
    	TextView textheaderInfo = (TextView) view.findViewById(R.id.textheaderInfo);
    	
		TextView texthospital = (TextView) view.findViewById(R.id.texthospital);
		TextView textdoctor = (TextView) view.findViewById(R.id.textdoctor);
		TextView textSurgeryDate = (TextView) view.findViewById(R.id.textSurgeryDate);
		TextView textSurgeryTime = (TextView) view.findViewById(R.id.textSurgeryTime);
		TextView textpatient = (TextView) view.findViewById(R.id.textpatient);
		TextView textHN = (TextView) view.findViewById(R.id.textHN);
		TextView textCase = (TextView) view.findViewById(R.id.textCase);
		TextView textTools = (TextView) view.findViewById(R.id.textTools);
		TextView textRequireDate = (TextView) view.findViewById(R.id.textRequireDate);
		TextView textBorrow = (TextView) view.findViewById(R.id.textBorrow);
		TextView texttypetranfer = (TextView) view.findViewById(R.id.texttypetranfer);
		TextView textspecial = (TextView) view.findViewById(R.id.textspecial);
		
		
		textheader.setTypeface(Util.getBoldFont(this));
		textheader.setTextSize(30f);
		
		textheaderInfo.setTypeface(Util.getBoldFont(this));
		textheaderInfo.setTextSize(30f);
		
		texthospital.setText(Hospital);
		textdoctor.setText(editTextdoctor.getText());
		texttypetranfer.setText(Transfer);
		
		StringBuilder  strCheck = new StringBuilder();
		if (check1.isChecked() && check2.isChecked()) {
			strCheck.append(check1.getText().toString()).append(",").append(EditCheck2.getText().toString());
		}else if(!check1.isChecked() && check2.isChecked()) {
			strCheck.append(EditCheck2.getText().toString());
		}else if(check1.isChecked() && !check2.isChecked()) {
			strCheck.append(check1.getText().toString());
		}else if(!check1.isChecked() && check2.isChecked()){
			strCheck.append("-");
		}
		textspecial.setText(strCheck.toString());
		textSurgeryDate.setText(surgeryDate.getText());
		textSurgeryTime.setText(surgeryTime.getText());
		textRequireDate.setText(requireDate.getText());
		textpatient.setText(editTextpatient.getText());
		textHN.setText(HN.getText());
		
		if(radioTools.getCheckedRadioButtonId()!= -1){
		    int id = radioTools.getCheckedRadioButtonId();
		    View radioButton = radioTools.findViewById(id);
		    int radioId = radioTools.indexOfChild(radioButton);
		    radioTools_id = String.valueOf(radioId);
		    RadioButton btn = (RadioButton) radioTools.getChildAt(radioId);
		    String selection = (String) btn.getText();
			textTools.setText(selection);
			Log.d("radioTools", radioTools_id);
		}
		
		if(radioCase.getCheckedRadioButtonId()!= -1){
		    int id = radioCase.getCheckedRadioButtonId();
		    View radioButton = radioCase.findViewById(id);
		    int radioId = radioCase.indexOfChild(radioButton);
		    radioCase_id = String.valueOf(radioId);
		    RadioButton btn = (RadioButton) radioCase.getChildAt(radioId);
		    String selection = (String) btn.getText();
		    textCase.setText(selection);
		    Log.d("radioCase_id", radioCase_id);
		}
		
		
		if(radioBorrow.getCheckedRadioButtonId()!= -1){
		    int id = radioBorrow.getCheckedRadioButtonId();
		    View radioButton = radioBorrow.findViewById(id);
		    int radioId = radioBorrow.indexOfChild(radioButton);
		    radioBorrow_id = String.valueOf(radioId);
		    RadioButton btn = (RadioButton) radioBorrow.getChildAt(radioId);
		    String selection = (String) btn.getText();
		    textBorrow.setText(selection);
		    Log.d("radioBorrow_id", radioBorrow_id);
		}
		
		btnchecklist = (Button) view.findViewById(R.id.btnchecklist);
		btnchecklist.setTypeface(Util.getBoldFont(this));
		btnchecklist.setTextSize(30f);
		btnchecklist.setOnTouchListener(this);
		
		btnSend = (Button) view.findViewById(R.id.btnSend);
		btnSend.setTypeface(Util.getBoldFont(this));
		btnSend.setTextSize(30f);
		btnSend.setOnTouchListener(this);
		
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		helpBuilder.setView(view);
		helpDialog = helpBuilder.create();
		helpDialog.show();
	}
	
	
	private void SendData() {
		
		strApiCall = api.getApiSendOrder();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type",dataOrderDataset.getOrder_type());
		params.put("order_data",order_data);
		params.put("sale_id",dataset.getUser_id());
		params.put("hospital_id",hospital_id);
		params.put("doctor_id",doctor_id);
		
		params.put("doctor_name",editTextdoctor.getText().toString());
		params.put("require_date",requireDate.getText().toString());
		params.put("require_time",requireTime.getText().toString());
		params.put("surgery_date",surgeryDate.getText().toString());
		params.put("surgery_time",surgeryTime.getText().toString());
		params.put("patient_name",editTextpatient.getText().toString());
		params.put("hn",HN.getText().toString());
		params.put("reason_borrow",radioBorrow_id);
		params.put("instrument",radioTools_id);
		params.put("case",radioCase_id);
		
		params.put("type_transport_id",Transfer_id);
		
		if (check1.isChecked()) {
			StringBuilder br = new StringBuilder();
			params.put("biomaterial","1");
			if (biomaterialsList != null && biomaterialsList.size() > 0) {
				for (int i = 0; i < biomaterialsList.size(); i++) {
					OrderCase3Entry case3Entry = biomaterialsList.get(i);
					br.append("|")
					  .append(case3Entry.getSpecial_tool_id())
					  .append(",")
					  .append(case3Entry.getQuantity());
				}
				biomaterials_detail = br.substring(1);
				Log.d("biomaterials_detail: ",biomaterials_detail);
			}
			params.put("biomaterials_detail",biomaterials_detail);
		}else{
			params.put("biomaterial","0");
			params.put("biomaterials_detail",null);
		}
	
		
		if (check2.isChecked()) {
			params.put("addons","1");
			params.put("addon_details",EditCheck2.getText().toString());
		}else{
			params.put("addons","0");
			params.put("addon_details",null);
		}
		
		aq.ajax(strApiCall, params, JSONObject.class, new AjaxCallback<JSONObject>() {
		    @Override
		    public void callback(String url, JSONObject json, AjaxStatus status) {
		    	
		    	if (json != null) {
		    		try {
		    			Log.d("SendData", json.toString());
						int code = json.getInt("code");
						
						String message = json.getString("message");
						switch (code) {
						case 200:
							Toast.makeText(getApplicationContext(),"Success: " + message, Toast.LENGTH_SHORT).show();
							finish();
							overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
							break;
						case 501:
							Toast.makeText(getApplicationContext(),"Error: " + message, Toast.LENGTH_SHORT).show();
							break;
						default:
							break;
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
		    	}else{
		    		Toast.makeText(getApplicationContext(),"Error: " + status.getMessage(), Toast.LENGTH_SHORT).show();
		    	}
		    	FixOneTouch--;
		    	dialog.dismiss();
		    	helpDialog.dismiss();
					 
		    }
	    });
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		switch (view.getId()) {
		
		case R.id.EditTextDoctor:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				showPopUpdoctor();
				return true;
			}
			break;
		
		case R.id.EditDateSurgery:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				showDialog(Datesurgery);
				return true;
			}
			
			break;
			
		case R.id.EditDateRequire:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				showDialog(DateRequire);
				return true;
			}
			
			break;
			
		case R.id.EditTimeRequire:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				showDialog(TimeRequire);
				return true;
			}
			
			break;
			
		case R.id.EditTimeSurgery:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				showDialog(Timesurgery);
				return true;
			}
			break;
			
		case R.id.buttonNext:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				if (editTextdoctor.getText().toString().matches("") ) {
					Toast.makeText(SetcaseDetailActivity.this, "กรุณาใส่ข้อมูลแพทย์ค่ะ", Toast.LENGTH_SHORT).show();
					return true;
				}
				
				if (requireDate.getText().toString().matches("") ) {
					Toast.makeText(SetcaseDetailActivity.this, "กรุณาใส่ข้อมูล วันที่ส่ง/วันที่ต้องการ ค่ะ", Toast.LENGTH_SHORT).show();
					return true;
				}
				
				if (requireTime.getText().toString().matches("") ) {
					Toast.makeText(SetcaseDetailActivity.this, "กรุณาใส่ข้อมูล เวลาส่ง/เวลาที่ต้องการ ค่ะ", Toast.LENGTH_SHORT).show();
					return true;
				}
				
				if (surgeryTime.getText().toString().matches("") ) {
					Toast.makeText(SetcaseDetailActivity.this, "กรุณาใส่ข้อมูล เวลาผ่าตัด ค่ะ", Toast.LENGTH_SHORT).show();
					return true;
				}
				
				if (surgeryDate.getText().toString().matches("") ) {
					Toast.makeText(SetcaseDetailActivity.this, "กรุณาใส่ข้อมูล วันที่ผ่าตัด ค่ะ", Toast.LENGTH_SHORT).show();
					return true;
				}
				
//				if (editTextpatient.getText().toString().matches("") ) {
//					Toast.makeText(SetcaseDetailActivity.this, "กรุณาใส่ชื่อคนไข้ค่ะ", Toast.LENGTH_SHORT).show();
//					return true;
//				}
				
//				if (HN.getText().toString().matches("") ) {
//					Toast.makeText(SetcaseDetailActivity.this, "กรุณาใส่ข้อมูล HN ค่ะ", Toast.LENGTH_SHORT).show();
//					return true;
//				}
				
				showPopUpComfirm();
				return true;
			}
			break;
		case R.id.btnchecklist:
			helpDialog.dismiss();
			layoutListConfirm.removeAllViews();
			break;
		case R.id.btnSend:
			dialog.show();
			if (FixOneTouch == 0) {
				FixOneTouch++;
				SendData();
			}
			break;
		default:
			break;
		}
		return false;
	}
	
	private void checkPrepareListConFirm(LinearLayout layout){
		int index = 0;
		TextView textView = null;
		switch (Integer.parseInt(dataOrderDataset.getOrder_type())) {
		case 1:
			if (dataOrderDataset.getOrderCase1Entries() != null && dataOrderDataset.getOrderCase1Entries().size() > 0) {
				for ( String key : dataOrderDataset.getOrderCase1Entries().keySet() ) {
					OrderCase1Entry case1Entry = dataOrderDataset.getOrderCase1Entries().get(key);
					
					if (index < 1) {
						TextView head = new TextView(this);
						head.setTypeface(Util.getBoldFont(this));
						head.setTextSize(20f);
						head.setText(case1Entry.getTrauma_name());
						layout.addView(head);
					}
					
					LinearLayout linearLayoutDetail = new LinearLayout(this);
					linearLayoutDetail.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					linearLayoutDetail.setOrientation(LinearLayout.HORIZONTAL);
					
					textView = new TextView(this);
					textView.setTypeface(Util.getBoldFont(this));
					textView.setTextSize(20f);
					textView.setText(case1Entry.getMasterimplant_name());
					
					linearLayoutDetail.addView(textView);
					
					textView = new TextView(this);
					textView.setTypeface(Util.getBoldFont(this));
					textView.setTextSize(20f);
					textView.setText(" (" + case1Entry.getIndication_name()+ ")");
					textView.setTextColor(Color.RED);
					linearLayoutDetail.addView(textView);
					
					layout.addView(linearLayoutDetail);
					
					index++;
				}
				Log.d("getOrderCase1Entries: ",textView.getText().toString());
			}
			break;
		case 2:
			if (dataOrderDataset.getOrderCase2Entries() != null && dataOrderDataset.getOrderCase2Entries().size() > 0) {
				for (int i = 0; i < dataOrderDataset.getOrderCase2Entries().size(); i++) {
					
					OrderCase2Entry case2Entry = dataOrderDataset.getOrderCase2Entries().get(i);
					
					if (index < 1) {
						TextView head = new TextView(this);
						head.setTypeface(Util.getBoldFont(this));
						head.setTextSize(20f);
						head.setText(case2Entry.getTrauma_name());
						layout.addView(head);
					}
					
					LinearLayout linearLayoutDetail = new LinearLayout(this);
					linearLayoutDetail.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					linearLayoutDetail.setOrientation(LinearLayout.HORIZONTAL);
					
					textView = new TextView(this);
					textView.setTypeface(Util.getBoldFont(this));
					textView.setTextSize(20f);
					textView.setText(case2Entry.getTraumaset_name());
					
					linearLayoutDetail.addView(textView);
					
					textView = new TextView(this);
					textView.setTypeface(Util.getBoldFont(this));
					textView.setTextSize(20f);
					textView.setText(" (" + case2Entry.getQuantity() + ")");
					textView.setTextColor(Color.RED);
					linearLayoutDetail.addView(textView);
					
					layout.addView(linearLayoutDetail);
					
					index++;
				}
				Log.d("getOrderCase2Entries: ",textView.getText().toString());
			}
			break;
		case 3:
			if (dataOrderDataset.getOrderCase3Entries() != null && dataOrderDataset.getOrderCase3Entries().size() > 0) {
				for (int i = 0; i < dataOrderDataset.getOrderCase3Entries().size(); i++) {
					
					OrderCase3Entry case3Entry = dataOrderDataset.getOrderCase3Entries().get(i);
					LinearLayout linearLayoutDetail = new LinearLayout(this);
					linearLayoutDetail.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					linearLayoutDetail.setOrientation(LinearLayout.HORIZONTAL);
					
					textView = new TextView(this);
					textView.setTypeface(Util.getBoldFont(this));
					textView.setTextSize(20f);
					textView.setText(case3Entry.getSpecial_tool_name());
					
					linearLayoutDetail.addView(textView);
					
					textView = new TextView(this);
					textView.setTypeface(Util.getBoldFont(this));
					textView.setTextSize(20f);
					textView.setText(" (" + case3Entry.getQuantity() + ")");
					textView.setTextColor(Color.RED);
					linearLayoutDetail.addView(textView);
					
					layout.addView(linearLayoutDetail);
					
				}
				Log.d("getOrderCase3Entries: ",textView.getText().toString());
			}
			break;
		case 4:
			if (dataOrderDataset.getOrderCase4Entries() != null && dataOrderDataset.getOrderCase4Entries().size() > 0) {
				for (int i = 0; i < dataOrderDataset.getOrderCase4Entries().size(); i++) {
					
					OrderCase4Entry case4Entry = dataOrderDataset.getOrderCase4Entries().get(i);
					LinearLayout linearLayoutDetail = new LinearLayout(this);
					linearLayoutDetail.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					linearLayoutDetail.setOrientation(LinearLayout.HORIZONTAL);
					
					textView = new TextView(this);
					textView.setTypeface(Util.getBoldFont(this));
					textView.setTextSize(20f);
					textView.setText(case4Entry.getImplant_name());
					
					linearLayoutDetail.addView(textView);
					
					textView = new TextView(this);
					textView.setTypeface(Util.getBoldFont(this));
					textView.setTextSize(20f);
					textView.setText(" (" + case4Entry.getDescription() + ")");
					textView.setTextColor(Color.RED);
					linearLayoutDetail.addView(textView);
					
					layout.addView(linearLayoutDetail);
					
				}
				Log.d("getOrderCase4Entries: ",textView.getText().toString());
			}
			break;

		default:
			break;
		}
	}
	
	private String checkPrepareCallToolsSet(){
		StringBuilder br = new StringBuilder();
		String value = null;
		switch (Integer.parseInt(dataOrderDataset.getOrder_type())) {
		case 1:
			if (dataOrderDataset.getOrderCase1Entries() != null && dataOrderDataset.getOrderCase1Entries().size() > 0) {
				for ( String key : dataOrderDataset.getOrderCase1Entries().keySet() ) {
					
					OrderCase1Entry case1Entry = dataOrderDataset.getOrderCase1Entries().get(key);
					br.append("|")
					  .append(case1Entry.getMasterimplant_id());
				}
				value = br.substring(1);
				Log.d("OrderCase1Entry: ",value);
			}
			break;
		case 2:
			
			
			if (dataOrderDataset.getOrderCase2Entries() != null && dataOrderDataset.getOrderCase2Entries().size() > 0) {
				
				for (int i = 0; i < dataOrderDataset.getOrderCase2Entries().size(); i++) {
					
					OrderCase2Entry case2Entry = dataOrderDataset.getOrderCase2Entries().get(i);
					br.append("|")
					  .append(case2Entry.getTramuaset_id());
				}
				value = br.substring(1);
				Log.d("OrderCase2Entry: ",value);
			}
			break;
		case 3:
			if (dataOrderDataset.getOrderCase3Entries() != null && dataOrderDataset.getOrderCase3Entries().size() > 0) {
				value = "0";
				Log.d("getOrderCase3Entries: ",value);
			}
			break;
		case 4:
			if (dataOrderDataset.getOrderCase4Entries() != null && dataOrderDataset.getOrderCase4Entries().size() > 0) {
				for (int i = 0; i < dataOrderDataset.getOrderCase4Entries().size(); i++) {
					OrderCase4Entry case4Entry = dataOrderDataset.getOrderCase4Entries().get(i);
					br.append("|")
					  .append(case4Entry.getImplant_id());
				}
				value = br.substring(1);
				Log.d("getOrderCase4Entries: ",value);
			}
			break;

		default:
			break;
		}
		return value;
	}

	
	private String checkPrepareData(){
		StringBuilder br = new StringBuilder();
		String value = null;
		switch (Integer.parseInt(dataOrderDataset.getOrder_type())) {
		case 1:
			if (dataOrderDataset.getOrderCase1Entries() != null && dataOrderDataset.getOrderCase1Entries().size() > 0) {
				for ( String key : dataOrderDataset.getOrderCase1Entries().keySet() ) {
					
					OrderCase1Entry case1Entry = dataOrderDataset.getOrderCase1Entries().get(key);
					br.append("|")
					  .append(case1Entry.getTrauma_id())
					  .append(",")
					  .append(case1Entry.getMasterimplant_id())
					  .append("-")
					  .append(case1Entry.getIndication_id());
				}
				value = br.substring(1);
				Log.d("getOrderCase1Entries: ",value);
			}
			break;
		case 2:
			if (dataOrderDataset.getOrderCase2Entries() != null && dataOrderDataset.getOrderCase2Entries().size() > 0) {
				for (int i = 0; i < dataOrderDataset.getOrderCase2Entries().size(); i++) {
					OrderCase2Entry case2Entry = dataOrderDataset.getOrderCase2Entries().get(i);
					br.append("|")
					  .append(case2Entry.getTramuaset_id())
					  .append(",")
					  .append(case2Entry.getQuantity());
				}
				value = br.substring(1);
				Log.d("getOrderCase2Entries: ",value);
			}
			break;
		case 3:
			if (dataOrderDataset.getOrderCase3Entries() != null && dataOrderDataset.getOrderCase3Entries().size() > 0) {
				for (int i = 0; i < dataOrderDataset.getOrderCase3Entries().size(); i++) {
					OrderCase3Entry case3Entry = dataOrderDataset.getOrderCase3Entries().get(i);
					br.append("|")
					  .append(case3Entry.getSpecial_tool_id())
					  .append(",")
					  .append(case3Entry.getQuantity());
				}
				value = br.substring(1);
				Log.d("getOrderCase3Entries: ",value);
			}
			break;
		case 4:
			if (dataOrderDataset.getOrderCase4Entries() != null && dataOrderDataset.getOrderCase4Entries().size() > 0) {
				for (int i = 0; i < dataOrderDataset.getOrderCase4Entries().size(); i++) {
					OrderCase4Entry case4Entry = dataOrderDataset.getOrderCase4Entries().get(i);
					br.append("|")
					  .append(case4Entry.getImplant_id());
				}
				value = br.substring(1);
				Log.d("getOrderCase4Entries: ",value);
			}
			break;

		default:
			break;
		}
		return value;
	}

	
}
