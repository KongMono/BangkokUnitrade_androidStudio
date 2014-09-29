package com.codefriday.bangkokunitrade.activity;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.DataOrderDataset;
import com.codefriday.bangkokunitrade.dataset.OrderCase1Entry;
import com.codefriday.bangkokunitrade.dataset.OrdernIdicatorEntry;
import com.codefriday.bangkokunitrade.dataset.SetEntry;
import com.codefriday.bangkokunitrade.dataset.TramuaSubListEntry;
import com.codefriday.bangkokunitrade.json.TramuaListIndicatorPaser;
import com.codefriday.bangkokunitrade.adapters.TramuaConfirmAdapter;
import com.codefriday.bangkokunitrade.adapters.TramuaSubListAdapter;
import com.codefriday.bangkokunitrade.util.Api;
import com.codefriday.bangkokunitrade.util.ExpandableHeightListView;
import com.codefriday.bangkokunitrade.util.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class TramuaSubListDetailActivity extends Activity implements OnTouchListener{
	private String strApiCall;
	private ImageView imageView;
	private TextView TextHead;
	private LinearLayout Llayout;
	private Api api;
	private AQuery aq;
	private Button buttonNext;
	private ArrayList<TramuaSubListEntry> arr = new ArrayList<TramuaSubListEntry>();
	TramuaSubListAdapter tramuaSubListAdapter;
	TramuaListIndicatorPaser indicatorPaser;
	ArrayList<OrdernIdicatorEntry> OrdernIdicatorList = new ArrayList<OrdernIdicatorEntry>();
	ArrayList<SetEntry> arraySetList = new ArrayList<SetEntry>();
	HashMap<String,OrderCase1Entry> map = new HashMap<String,OrderCase1Entry>();
	private ArrayList<CheckBox> boxs = new ArrayList<CheckBox>();
	private ArrayList<TextView> Texts = new ArrayList<TextView>();
	private SparseArray<String> sparseArray = new SparseArray<String>();
	private HashMap<String,OrderCase1Entry> SparseData = new HashMap<String,OrderCase1Entry>();
	private SparseArray<String> Datakey = new SparseArray<String>();
	private ProgressDialog dialog;
	private JSONObject jObject;
	private Bundle bundle;
	AlertDialog helpDialog;
	OrderCase1Entry case1Entry = new OrderCase1Entry();
	DataOrderDataset dataOrderDataset = DataOrderDataset.getInstance();
	String trauma_id = null,trauma_name = null;
	boolean noExistData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tramua_sublist);

		dialog = ProgressDialog.show(this, "","Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();
		
		init();
		
		TextHead.setText(bundle.getString("name"));
		TextHead.setTypeface(Util.getBoldFont(this));
		TextHead.setTextSize(30f);
		
		aq.id(imageView).image(bundle.getString("image"), true, true, 0, 0, null,AQuery.FADE_IN_FILE,AQuery.RATIO_PRESERVE);
		trauma_id = String.valueOf(bundle.getInt("id"));
		trauma_name = bundle.getString("name");
		ApiGetData(trauma_id);
		
	}
	
	private void ApiGetData(String Trauma_id) {
		strApiCall = api.getApiTramuaSetList(Trauma_id);
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 JsonPaser(json);
				 }catch(Exception e){
					 e.printStackTrace();
				 }finally{
					 dialog.dismiss();
				 }
			}

			private void JsonPaser(JSONObject json) {
				if (json != null) {
					 arr.clear();
					try {
						jObject = json;
						JSONObject responseObject = jObject.getJSONObject("content");
						JSONArray itemsjsonArray = responseObject.getJSONArray("item");

						for (int i = 0; i < itemsjsonArray.length(); i++) {
							arraySetList.clear();
							TramuaSubListEntry item = new TramuaSubListEntry();
							jObject = itemsjsonArray.getJSONObject(i);
							
							SetEntry entry = new SetEntry();
							entry.setId(jObject.getInt("id"));
							entry.setName(jObject.getString("name"));
							entry.setDescription(jObject.getString("description"));
							entry.setSelected(false);
								
							LinearLayout layout = new LinearLayout(TramuaSubListDetailActivity.this);
							layout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
							layout.setOrientation(LinearLayout.HORIZONTAL);
							CheckBox box = new CheckBox(TramuaSubListDetailActivity.this);
							box.setId(entry.getId());
							box.setText(entry.getName());
							box.setOnTouchListener(TramuaSubListDetailActivity.this);
							boxs.add(box);
							
							TextView detail = new TextView(TramuaSubListDetailActivity.this);
							detail.setId(entry.getId());
							detail.setTypeface(Util.getBoldFont(TramuaSubListDetailActivity.this));
							detail.setTextColor(Color.RED);
							detail.setPadding(5, 5, 5, 5);
							detail.setTextSize(20f);
							detail.setText(null);
							Texts.add(detail);
							sparseArray.put(entry.getId(), entry.getDescription());
							layout.addView(box);
							layout.addView(detail);
							Llayout.addView(layout);
							arraySetList.add(entry);
						
						item.setSetEntries(arraySetList);
						arr.add(item);
						}
					}catch (JSONException e) {
						Log.e("JsonPaser", "Error ", e);
					}
				 }
			}
        });
	}

	private void init() {
	    imageView = (ImageView) findViewById(R.id.image);
	    TextHead = (TextView) findViewById(R.id.TextHead);
		aq = new AQuery(this);
		api = new Api(this);
		Llayout = (LinearLayout) findViewById(R.id.Llayout);
		buttonNext = (Button) findViewById(R.id.buttonNext);
		buttonNext.setTypeface(Util.getBoldFont(this));
		buttonNext.setTextSize(50f);
		buttonNext.setOnTouchListener(this);
		indicatorPaser = new TramuaListIndicatorPaser();
		bundle = getIntent().getExtras();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}

	@Override
	public boolean onTouch(final View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			switch (v.getId()) {
			case R.id.buttonNext:
				showPopUpComfirm();
				break;

			default:
				try {
					for (CheckBox box : boxs) {
						if (box.getId() == v.getId()) {
							if (box.isChecked()) {
								box.setOnCheckedChangeListener(new OnCheckedChangeListener() {
									
									@Override
									public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
										if (!isChecked){
									         buttonView.setChecked(false);
									         
									         for(int i = 0; i < sparseArray.size(); i++) {
									        	 if (sparseArray.keyAt(i) == v.getId()){
									        		 for (TextView text : Texts) {
											        	 if (text.getId() == v.getId()){
											        		 Log.d("checkbox id UnCheck",String.valueOf(v.getId()));
											        		 text.setText(null);     
											        		 String key = Datakey.get(text.getId());
											        		 Log.d("Key Remove", key);
											        		 SparseData.remove(key);
											        		 Datakey.remove(text.getId());
											        		 
											        		 if (dataOrderDataset.getOrderCase1Entries() != null) {
											        			 if (dataOrderDataset.getOrderCase1Entries().containsKey(key)) {
												        			 Log.d("Key dataOrderDataset  Remove", key);
												        			 dataOrderDataset.getOrderCase1Entries().remove(key);
												        		 }
															 }
														 } 
											         }
												 }
									         }
									         
									    }else{
									    	 buttonView.setChecked(true);
									    }
									}
								});
								
							}else{
								ApiGetCaseList(v.getId(),box.getText().toString());
								Log.d("checkbox id Check",String.valueOf(v.getId()));
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		
		return false;
	}
	
	private void ApiGetCaseList(final int id,final String masterimplant) {
		strApiCall = api.getApiTramuaSetListIndicator(String.valueOf(id));
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 OrdernIdicatorList = indicatorPaser.getData(json);
						 tramuaSubListAdapter = new TramuaSubListAdapter(TramuaSubListDetailActivity.this, OrdernIdicatorList);
						 showPopUp(tramuaSubListAdapter,id,masterimplant);
					 }
				 }catch(Exception e){
					 e.printStackTrace();
				 }finally{
					 dialog.dismiss(); 
				 }
			}
        });
	}
	
	private void showPopUp(TramuaSubListAdapter TramuaSubListAdapter,final int masterimplant_id,final String masterimplant) {
	
		final Builder helpBuilder = new Builder(this);
		final LayoutInflater inflater = getLayoutInflater();
		final View view = inflater.inflate(R.layout.layout_list_tramua, null);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		TextView textheader = (TextView) view.findViewById(R.id.textheader);
		textheader.setTypeface(Util.getBoldFont(this));
		textheader.setTextSize(30f);
		
		ListView list = (ListView) view.findViewById(R.id.list);
		list.setAdapter(tramuaSubListAdapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				OrdernIdicatorList.get(position).getId();
				for (TextView text : Texts) {
					if (text.getId() == masterimplant_id) {
						text.setText((" (").concat(OrdernIdicatorList.get(position).getName().concat(")")));
						
						String key = String.valueOf(masterimplant_id).concat(trauma_id).concat(String.valueOf(OrdernIdicatorList.get(position).getId()));
						
						Datakey.put(text.getId(), key);
						
						Log.d("Ket Add", key);
						
						SparseData.put(key
								,new OrderCase1Entry(
								trauma_id,trauma_name,
								String.valueOf(masterimplant_id),masterimplant,
								String.valueOf(OrdernIdicatorList.get(position).getId()),OrdernIdicatorList.get(position).getName()
								));
					}
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
		final View view = inflater.inflate(R.layout.layout_confirm_tramua, null);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		TextView textheader = (TextView) view.findViewById(R.id.textheader);
		textheader.setTypeface(Util.getBoldFont(this));
		textheader.setTextSize(30f);
		
		TextView HeadList = (TextView) view.findViewById(R.id.HeadList);
		HeadList.setText(bundle.getString("name"));
		
		ExpandableHeightListView list = (ExpandableHeightListView) view.findViewById(R.id.list);
		list.setExpanded(true);
		
		for ( String key : SparseData.keySet() ) {
			OrderCase1Entry entryAdd = SparseData.get(key);
			
		    if (dataOrderDataset.getOrderCase1Entries() != null) {
		    	
		    	if (!dataOrderDataset.getOrderCase1Entries().containsKey(key)) {
		    		noExistData = true;
				}
					
				if (noExistData) {
					dataOrderDataset.getOrderCase1Entries()
					.put(entryAdd.getMasterimplant_id().concat(trauma_id).concat(entryAdd.getIndication_id())
							,new OrderCase1Entry(trauma_id, trauma_name,
			    			entryAdd.getMasterimplant_id(),
			    			entryAdd.getMasterimplant_name(),
			    			entryAdd.getIndication_id(),
			    			entryAdd.getIndication_name()));
				}
				// RESET Boolean
				noExistData = false;
				
		    }else{
		    	map.put(entryAdd.getMasterimplant_id().concat(trauma_id).concat(entryAdd.getIndication_id())
		    			,new OrderCase1Entry(trauma_id, trauma_name,
		    			entryAdd.getMasterimplant_id(),
		    			entryAdd.getMasterimplant_name(), 
		    			entryAdd.getIndication_id(),
		    			entryAdd.getIndication_name()));
		    	
		    	dataOrderDataset.setOrderCase1Entries(map);
		    }
		}
		
		ArrayList<OrderCase1Entry> arrayList = new ArrayList<OrderCase1Entry>();
		
		if (dataOrderDataset.getOrderCase1Entries() != null) {
			for (String key : dataOrderDataset.getOrderCase1Entries().keySet()) {
				   OrderCase1Entry entry =  dataOrderDataset.getOrderCase1Entries().get(key);
				   LinearLayout layout = new LinearLayout(this);
				   layout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				   layout.setOrientation(LinearLayout.HORIZONTAL);
				   
				   TextView textMaster = new TextView(this);
				   textMaster.setText(entry.getMasterimplant_name());
				   textMaster.setTypeface(Util.getBoldFont(this));
				   textMaster.setTextSize(20f);
				   
				   layout.addView(textMaster);
				   
				   TextView textIndication = new TextView(this);
				   textIndication.setText("(" + entry.getIndication_name() + ")");
				   textIndication.setTypeface(Util.getBoldFont(this));
				   textIndication.setTextSize(20f);
				   textIndication.setTextColor(Color.RED);
				   textIndication.setPadding(5, 5, 5, 5);
				   
				   layout.addView(textIndication);
				   
				   
				   arrayList.add(new OrderCase1Entry(trauma_id, 
						   trauma_name, 
						   entry.getMasterimplant_id(), 
						   entry.getMasterimplant_name(), 
						   entry.getIndication_id(), 
						   entry.getIndication_name()));
				  
				}
			
			TramuaConfirmAdapter adapter = new TramuaConfirmAdapter(this, arrayList,dataOrderDataset.getOrderCase1Entries());
			list.setAdapter(adapter);
			   
			Button btnBack = (Button) view.findViewById(R.id.btnBack);
			btnBack.setTypeface(Util.getBoldFont(this));
			btnBack.setTextSize(30f);
			
			Button btnNext = (Button) view.findViewById(R.id.btnNext);
			btnNext.setTypeface(Util.getBoldFont(this));
			btnNext.setTextSize(30f);
			
			btnBack.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					helpDialog.dismiss();
					Intent intent = new Intent(TramuaSubListDetailActivity.this,OrdersSetUpperLowerActivity.class);
					startActivity(intent);
					finish();
					overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
				}

			});
			
			btnNext.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					if (dataOrderDataset.getOrderCase1Entries() != null && dataOrderDataset.getOrderCase1Entries().size() > 0) {
						helpDialog.dismiss();
						Intent intent = new Intent(TramuaSubListDetailActivity.this,SetcaseDetailActivity.class);
						startActivity(intent);
						finish();
						overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
					}else{
						Toast.makeText(TramuaSubListDetailActivity.this, "No Order", Toast.LENGTH_SHORT).show();
					}
				}
			});
			
			helpBuilder.setView(view);
			
			helpDialog = helpBuilder.create();
			helpDialog.show();
		}
		
	}

}



