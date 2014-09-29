package com.codefriday.bangkokunitrade.activity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.AlertDialog.Builder;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.QuoateEntry;
import com.codefriday.bangkokunitrade.dataset.UserDataset;
import com.codefriday.bangkokunitrade.json.QuoatePaser;
import com.codefriday.bangkokunitrade.adapters.ListQuoateAdapter;
import com.codefriday.bangkokunitrade.adapters.QuoateAdapter;
import com.codefriday.bangkokunitrade.util.Api;
import com.codefriday.bangkokunitrade.util.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.ActionBar.LayoutParams;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class ListQuotateActivity extends Activity implements OnClickListener,OnItemClickListener{
	private Api api;
	private LinearLayout layoutTotal;
	// Progress Dialog
	private ProgressDialog pDialog;
	public static final int progress_bar_type = 0;
	private AQuery aq;
	private String strApiCall;
	private QuoateAdapter adapter;
//	public  ListQuoateTotalAdapter listQuoateTotalAdapter;
	private ListQuoateAdapter listQuoateAdapter;
	private TextView textheader,textpricetag;
	public  TextView textpriceNum;
	public ArrayList<CheckBox> boxs = new ArrayList<CheckBox>();
	public ArrayList<EditText> editexts = new ArrayList<EditText>();
	public ArrayList<TextView> textPrice = new ArrayList<TextView>();
	public ArrayList<View> viewTotal = new ArrayList<View>();
	public SparseArray<QuoateEntry> sparseArray = new SparseArray<QuoateEntry>();
	private ArrayList<QuoateEntry> arrayList = new ArrayList<QuoateEntry>();
	private ArrayList<QuoateEntry> arrayChoose = new ArrayList<QuoateEntry>();
	public  ArrayList<QuoateEntry> arrayListTotal = new ArrayList<QuoateEntry>();
	private ArrayList<QuoateEntry> listconfirm = new ArrayList<QuoateEntry>();
	private Button btnQuotate;
	private Bundle bundle;
	private ListView list;
	private QuoatePaser quoatePaser;
	private ProgressDialog dialog;
	private UserDataset dataset = UserDataset.getInstance();
	private String saleName = null;
	AlertDialog helpDialog;
	String hospitalID = null;
	EditText inputSearch;
	Boolean isSame = false;
	BigDecimal total = BigDecimal.ZERO;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quotatelist);

		dialog = ProgressDialog.show(this, "","Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();
		
		init();
		
		ApiGetImplantQuotation(bundle.getInt("hospital_id"));
		
		/**
		 * Enabling Search Filter
		 * */
		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,int arg3) {
				ListQuotateActivity.this.listQuoateAdapter.getFilter().filter(cs);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});


	}

	private void ApiGetImplantQuotation(int hospital_id) {
		hospitalID = String.valueOf(hospital_id);
		strApiCall = api.getApiImplantQuotation(String.valueOf(hospital_id),dataset.getUser_id());
		
		aq.ajax(strApiCall, JSONObject.class, new AjaxCallback<JSONObject>() {
			
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 arrayList = quoatePaser.getData(json);
						 arrayChoose = arrayList;
						 listQuoateAdapter = new ListQuoateAdapter(ListQuotateActivity.this, arrayList);
						 list.setAdapter(listQuoateAdapter);
						 
//						 for (int i = 0; i < arrayList.size(); i++) {
//							 
//							 saleName = arrayList.get(i).getSale();
//							 
//							 final LayoutInflater inflater = getLayoutInflater();
//							 final View view = inflater.inflate(R.layout.item_quoate_list, null);
//							 view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
//							 
//							 CheckBox box =(CheckBox) view.findViewById(R.id.checkbox);
//							 box.setId(arrayList.get(i).getId());
//							 box.setText(arrayList.get(i).getImplant());
//							 box.setTag(arrayList.get(i).getId());
//							 box.setOnCheckedChangeListener(ListQuotateActivity.this);
//							 box.setTypeface(Util.getBoldFont(ListQuotateActivity.this));
//							 box.setTextSize(20f);
//							 boxs.add(box);
//							 
//							 EditText edittextQty = (EditText)view.findViewById(R.id.edittextQty);
//							 edittextQty.setTypeface(Util.getBoldFont(ListQuotateActivity.this));
//							 edittextQty.setEnabled(false);
//							 edittextQty.setTextSize(20f);
//							 edittextQty.setInputType(InputType.TYPE_CLASS_NUMBER);
//							 edittextQty.setTag(arrayList.get(i).getId());
//							 edittextQty.addTextChangedListener(ListQuotateActivity.this);
//							 editexts.add(edittextQty);
//								
//							 TextView textfixPrice = (TextView)view.findViewById(R.id.textPrice);
//							 textfixPrice.setTypeface(Util.getBoldFont(ListQuotateActivity.this));
//							 textfixPrice.setTextSize(20f);
//							 textfixPrice.setText(arrayList.get(i).getPrice());
//							 textfixPrice.setTag(arrayList.get(i).getId());
//							 textPrice.add(textfixPrice);
//							 
//							 layout.addView(view);
//						 }
						 
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
		api = new Api(this);
		aq = new AQuery(this);
		quoatePaser = new QuoatePaser();
		bundle = getIntent().getExtras();
		
		list = (ListView) findViewById(R.id.list);
		list.setOnItemClickListener(this);
		
		textheader = (TextView) findViewById(R.id.textheader);
		textheader.setTypeface(Util.getBoldFont(this));
		textheader.setTextSize(30f);
		
		textpriceNum = (TextView) findViewById(R.id.textpriceNum);
		textpriceNum.setTypeface(Util.getBoldFont(this));
		textpriceNum.setText("0");
		textpriceNum.setTextSize(30f);
		
		textpricetag = (TextView) findViewById(R.id.textpricetag);
		textpricetag.setTypeface(Util.getBoldFont(this));
		textpriceNum.setTextSize(30f);

		btnQuotate = (Button) findViewById(R.id.btnQuotate);
		btnQuotate.setTypeface(Util.getBoldFont(this));
		btnQuotate.setTextSize(30f);
		btnQuotate.setOnClickListener(this);
		
		inputSearch = (EditText) findViewById(R.id.editSearch);
		
		layoutTotal = (LinearLayout) findViewById(R.id.layoutTotal);
	}


	@Override
	public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
		
		    
		for (int i = 0; i < arrayChoose.size(); i++) {
			if (String.valueOf(id).equalsIgnoreCase(String.valueOf(arrayChoose.get(i).getId()))) {
				
				if (viewTotal.isEmpty()) {
					
					 saleName = arrayList.get(i).getSale();
					
					 final LayoutInflater inflater = getLayoutInflater();
					 View view = inflater.inflate(R.layout.item_quoate_total_list, null);
					 view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
					 view.setTag(arrayList.get(i).getId());
					 
					 TextView texttitle  = (TextView) view.findViewById(R.id.texttitle);
					 texttitle.setText(arrayList.get(i).getImplant());
					 texttitle.setTag(arrayList.get(i).getId());
					 texttitle.setTypeface(Util.getBoldFont(ListQuotateActivity.this));
					 texttitle.setTextSize(20f);
					 
					 TextView textQty = (TextView)view.findViewById(R.id.textQty);
					 textQty.setTypeface(Util.getBoldFont(ListQuotateActivity.this));
					 textQty.setTextSize(20f);
					 textQty.setText(arrayList.get(i).getQty());
					 textQty.setTag(arrayList.get(i).getId());
					 textQty.setOnClickListener(this);
						
					 TextView textPrice = (TextView)view.findViewById(R.id.textPrice);
					 textPrice.setTypeface(Util.getBoldFont(ListQuotateActivity.this));
					 textPrice.setTextSize(20f);
					 textPrice.setText(arrayList.get(i).getPrice());
					 textPrice.setTag(arrayList.get(i).getId());
					 
					 
					 ImageView img = (ImageView)view.findViewById(R.id.imgDelete);
					 img.setTag(arrayList.get(i).getId());
					 img.setOnClickListener(this);
					 
					 viewTotal.add(view);
					 
					 layoutTotal.addView(view);
					 
				}else{
					
					for (int j = 0; j < viewTotal.size(); j++) {
						if (viewTotal.get(j).getTag().equals(arrayChoose.get(i).getId())) {
							isSame = true; 
						}
					}
					if (isSame) {
						Toast.makeText(this, "เพิ่มรายการนี้ไปแล้วค่ะ", Toast.LENGTH_SHORT).show();
						isSame = false;
					}else{
						final LayoutInflater inflater = getLayoutInflater();
						 View view = inflater.inflate(R.layout.item_quoate_total_list, null);
						 view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
						 view.setTag(arrayList.get(i).getId());
						 TextView texttitle  = (TextView) view.findViewById(R.id.texttitle);
						 texttitle.setText(arrayList.get(i).getImplant());
						 texttitle.setTag(arrayList.get(i).getId());
						 texttitle.setTypeface(Util.getBoldFont(ListQuotateActivity.this));
						 texttitle.setTextSize(20f);
						 
						 TextView textQty = (TextView)view.findViewById(R.id.textQty);
						 textQty.setTypeface(Util.getBoldFont(ListQuotateActivity.this));
						 textQty.setTextSize(20f);
						 textQty.setText(arrayList.get(i).getQty());
						 textQty.setTag(arrayList.get(i).getId());
						 textQty.setOnClickListener(this);
							
						 TextView textPrice = (TextView)view.findViewById(R.id.textPrice);
						 textPrice.setTypeface(Util.getBoldFont(ListQuotateActivity.this));
						 textPrice.setTextSize(20f);
						 textPrice.setText(arrayList.get(i).getPrice());
						 textPrice.setTag(arrayList.get(i).getId());
						 
						 
						 ImageView img = (ImageView)view.findViewById(R.id.imgDelete);
						 img.setTag(arrayList.get(i).getId());
						 img.setOnClickListener(this);
						 
						 viewTotal.add(view);
						 
						 layoutTotal.addView(view);
						 
						 isSame = false;
					}
				}
			}
		}
	}
	
	
	private void showPopUp() {
		
		final Builder helpBuilder = new Builder(this);
		final LayoutInflater inflater = getLayoutInflater();
		final View view = inflater.inflate(R.layout.layout_comfirm_quotate, null);

		TextView header = (TextView) view.findViewById(R.id.textheader);
		header.setTypeface(Util.getBoldFont(this));
		header.setTextSize(30f);
		
		TextView textSalename = (TextView) view.findViewById(R.id.textSalename);
		textSalename.setTypeface(Util.getBoldFont(this));
		textSalename.setTextSize(20f);
		textSalename.setText("ชื่อผู้แทน: " + saleName);
		
		TextView texttotal = (TextView) view.findViewById(R.id.texttotal_confirm);
		texttotal.setTypeface(Util.getBoldFont(this));
		texttotal.setPadding(5, 5, 5, 5);
		texttotal.setTextSize(30f);
		texttotal.setText("รวมเงิน: " + textpriceNum.getText().toString() + " บาท");
		
		ListView list = (ListView) view.findViewById(R.id.list);
		
		int key = 0;
		
		for(int i = 0; i < sparseArray.size(); i++) {
		   key = sparseArray.keyAt(i);
		   QuoateEntry quoateEntry = sparseArray.get(key);
		   listconfirm.add(quoateEntry);
		}
		adapter = new QuoateAdapter(this, listconfirm);
		list.setAdapter(adapter);
		
		Button btncancel = (Button) view.findViewById(R.id.btncancel);
		btncancel.setTypeface(Util.getBoldFont(this));
		btncancel.setTextSize(30f);

		Button btnSend = (Button) view.findViewById(R.id.btnsend);
		btnSend.setTypeface(Util.getBoldFont(this));
		btnSend.setTextSize(30f);

		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		helpBuilder.setView(view);
		
		
		btncancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				helpDialog.dismiss();
				listconfirm.clear();
				sparseArray.clear();
			}
		});
		
		btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sendData();
			}
		});

	    helpDialog = helpBuilder.create();
	    helpDialog.setCancelable(false);
		helpDialog.show();

	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		arrayListTotal.clear();
	}
	
	private void sendData() {
		
		strApiCall = api.getApiQuotationRequest();
		Map<String, Object> params = new HashMap<String, Object>();
		String value = prepareData();
		
		params.put("hospital_id", hospitalID);
		params.put("data",value);
		
		aq.ajax(strApiCall,params, JSONObject.class, new AjaxCallback<JSONObject>() {
			
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				 try {
					 if (json != null) {
						 JSONObject responseObject = json.getJSONObject("content");
						 String urlDownload = responseObject.getString("url");
						 Uri uri =  Uri.parse(urlDownload);
						 try{
							  Intent intentUrl = new Intent(Intent.ACTION_VIEW);
							  intentUrl.setDataAndType(uri, "application/pdf");
							  intentUrl.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							  startActivity(intentUrl);
						 }catch (ActivityNotFoundException e) {
							 Toast.makeText(ListQuotateActivity.this, "No PDF Viewer Installed", Toast.LENGTH_LONG).show();
						 }
					 }
				 }catch(Exception e){
					 e.printStackTrace();
				 }finally{
					 helpDialog.dismiss();
				 }
			}
        });
		
	}
	private String prepareData() {
		StringBuilder br = new StringBuilder();
		
		for (int i = 0; i < listconfirm.size(); i++) {
			QuoateEntry quoateEntry = listconfirm.get(i);
			br.append("|")
			  .append(quoateEntry.getId())
			  .append(",")
			  .append(quoateEntry.getQty());
		}
		String value = br.substring(1);
		Log.d("listconfirm: ",value);
		return value;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgDelete:
			
			for (int i = 0; i < viewTotal.size(); i++) {
				if (v.getTag().equals(viewTotal.get(i).getTag())) {
					layoutTotal.removeView(viewTotal.get(i));
					viewTotal.remove(i);
				}
			}
			
			total = BigDecimal.ZERO;
	    	
	    	for (int i = 0; i < viewTotal.size(); i++) {
	    		 TextView textQty = (TextView)viewTotal.get(i).findViewById(R.id.textQty);
	    		 TextView textPrice = (TextView)viewTotal.get(i).findViewById(R.id.textPrice);
	    		 String priceStr = textPrice.getText().toString();
	    		 String qtyStr = textQty.getText().toString();
	    		 BigDecimal price = BigDecimal.valueOf(Double.valueOf(priceStr));
	    		 BigDecimal qty = BigDecimal.valueOf(Double.valueOf(qtyStr));
	    		 BigDecimal count = qty.multiply(price);
	    		 total = total.add(count);
	    	}
	    	
	    	textpriceNum.setText(String.valueOf(total));
			
			isSame = false;
			
			
			break;
		case R.id.textQty:
			popup(String.valueOf(v.getTag()));
			break;
			
		case R.id.btnQuotate:
			
			boolean checkSomeOne = false;
			
			for (int i = 0; i < viewTotal.size(); i++) {
				 TextView texttitle  = (TextView)viewTotal.get(i).findViewById(R.id.texttitle);
	    		 TextView textQty = (TextView)viewTotal.get(i).findViewById(R.id.textQty);
	    		 TextView textPrice = (TextView)viewTotal.get(i).findViewById(R.id.textPrice);
	    		 String titleStr = texttitle.getText().toString();
	    		 String priceStr = textPrice.getText().toString();
	    		 String qtyStr = textQty.getText().toString();
	    		 BigDecimal price = BigDecimal.valueOf(Double.valueOf(priceStr));
	    		 BigDecimal qty = BigDecimal.valueOf(Double.valueOf(qtyStr));
	    		 BigDecimal count = qty.multiply(price);
	    		 total = total.add(count);
	    		 
	    		 
	    		 sparseArray.put(Integer.parseInt(viewTotal.get(i).getTag().toString()), 
							new QuoateEntry(Integer.parseInt(viewTotal.get(i).getTag().toString()),
									saleName,
									titleStr,
									qtyStr,
									priceStr));
	    		 
	    		 checkSomeOne = true;
	    	}
			
			if (checkSomeOne) {
				showPopUp();
			}
			
			break;

		default:
			break;
		}
		
	}
	
	public void popup(final String tagId){
		final LayoutInflater inflater = getLayoutInflater();
		View view = inflater.inflate(R.layout.popupnumberpick, null);
		final NumberPicker picker = (NumberPicker) view.findViewById(R.id.myNumber);
		
		picker.setMinValue(1);
		picker.setMaxValue(9999);
		
		AlertDialog.Builder builder =  new AlertDialog.Builder(this);
		builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) {
		    	
		    	for (int i = 0; i < viewTotal.size(); i++) {
					if (viewTotal.get(i).getTag().toString().equals(tagId)) {
						 picker.clearFocus();
						 TextView textQty = (TextView)viewTotal.get(i).findViewById(R.id.textQty);
						 textQty.setText(String.valueOf(picker.getValue()));
					}
				}
		    	
		    	total = BigDecimal.ZERO;
		    	
		    	for (int i = 0; i < viewTotal.size(); i++) {
		    		 TextView textQty = (TextView)viewTotal.get(i).findViewById(R.id.textQty);
		    		 TextView textPrice = (TextView)viewTotal.get(i).findViewById(R.id.textPrice);
		    		 String priceStr = textPrice.getText().toString();
		    		 String qtyStr = textQty.getText().toString();
		    		 BigDecimal price = BigDecimal.valueOf(Double.valueOf(priceStr));
		    		 BigDecimal qty = BigDecimal.valueOf(Double.valueOf(qtyStr));
		    		 BigDecimal count = qty.multiply(price);
		    		 total = total.add(count);
		    	}
		    	
		    	textpriceNum.setText(String.valueOf(total));
		    	
		    } }); 
		builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) {
		        return;
		    } }); 
		
		builder.setView(view);

		final AlertDialog dialog = builder.create();
		dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		dialog.show();      

	}


//	@Override
//	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//		if (!isChecked){
//			
//	         buttonView.setChecked(false);
//	         
//	   		 for (EditText editText : editexts) {
//		        	 if (editText.getTag() == buttonView.getTag()){
//		        		 editText.setText(null);
//		        		 editText.setEnabled(false);
//		        		 
//		        		 if (sparseArray.size() > 0) {
//		        			 int position = Integer.parseInt(editText.getTag().toString());
//		        			 sparseArray.remove(position);
//		        		 }
//					 }
//	   		 }
//	    }else{
//	    	
//	    	 buttonView.setChecked(true);
//	    	 
//	    	 for (EditText editText : editexts) {
//	    		 if (editText.getTag() == buttonView.getTag()){
//	        		 editText.setEnabled(true);
//	        		 editText.setFocusable(true);
//				 }
//	         }
//	    }
//	}

//	@Override
//	public void beforeTextChanged(CharSequence s, int start, int count,int after) {
//		
//	}
//
//	@Override
//	public void onTextChanged(CharSequence s, int start, int before, int count) {
//		
//	}
//
//	@Override
//	public void afterTextChanged(Editable s) {
//		
//		BigDecimal total = BigDecimal.ZERO;
//		for (EditText editText : editexts) {
//			if (editText.isEnabled()) {
//				String num = editText.getText().toString();
//				if (!num.isEmpty()) {
//					for(TextView textView : textPrice){
//						if (editText.getTag() == textView.getTag()){
//							String priceText = textView.getText().toString();
//							BigDecimal price = BigDecimal.valueOf(Double.valueOf(priceText.toString()));
//							BigDecimal qty = BigDecimal.valueOf(Double.valueOf(num));
//							BigDecimal count = qty.multiply(price);
//							total = total.add(count);
//						}
//					}
//				}
//			}
//        }
//		textpriceNum.setText(String.valueOf(total));
//	}

//	@Override
//	public void onClick(View v) {
//		if (v.getId() == R.id.btnQuotate) {
//			boolean checkSomeOne = false;
//			for (CheckBox box : boxs) {
//				if (box.isChecked()){
//					for (EditText editText : editexts) {
//						if (editText.isEnabled()) {
//							
//							if (editText.getText().toString().matches("") || editText.getText().toString().matches("0")) {
//								Toast.makeText(this, "กรุณาใส่จำนวนด้วยค่ะ", Toast.LENGTH_SHORT).show();
//								sparseArray.clear();
//								return;
//							}else{
//								for(TextView textView : textPrice){
//									if ((editText.getTag() == textView.getTag()) && (editText.getTag() == box.getTag()) && (textView.getTag() == box.getTag())){
//										String priceText = textView.getText().toString();
//										BigDecimal price = BigDecimal.valueOf(Double.valueOf(priceText.toString()));
//										sparseArray.put(Integer.parseInt(editText.getTag().toString()), 
//												new QuoateEntry(Integer.parseInt(editText.getTag().toString()),
//														saleName,
//														box.getText().toString(),
//														editText.getText().toString(),
//														String.valueOf(price)));
//										
//										checkSomeOne = true;
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//			if (checkSomeOne) {
//				showPopUp();
//			}
//			
//		}
//		
//	}
	
	
	
}
