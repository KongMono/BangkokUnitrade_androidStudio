package com.codefriday.bangkokunitrade.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.activity.ListQuotateActivity;
import com.codefriday.bangkokunitrade.dataset.QuoateEntry;
import com.codefriday.bangkokunitrade.util.Util;

public class ListQuoateTotalAdapter extends BaseAdapter implements TextWatcher{
	ListQuotateActivity d = new ListQuotateActivity();
	private LayoutInflater mInflater;
	private List<QuoateEntry> items = new ArrayList<QuoateEntry>();
	protected Context context;
	private String TAG = getClass().getName();
	ViewHolder holder;
	AQuery aQuery;

	public class ViewHolder {
		public TextView texttitle;
		public TextView textQty;
		public TextView textPrice;
		public ImageView img;
		public int position;
	}

	public ListQuoateTotalAdapter(Context context, ArrayList<QuoateEntry> items) {
		this.mInflater = LayoutInflater.from(context);
		this.items = items;
		this.context = context;
		aQuery = new AQuery(context);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int pos) {
		return items.get(pos);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final QuoateEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_quoate_total_list, parent, false);
			holder = new ViewHolder();
			
			 holder.texttitle  = (TextView) view.findViewById(R.id.texttitle);
			 holder.texttitle.setTypeface(Util.getBoldFont(context));
			 holder.texttitle.setTextSize(20f);
			 holder.texttitle.setId(item.getId());
			 holder.texttitle.setTag(item.getId());
			 
			 holder.img = (ImageView) view.findViewById(R.id.img);
			 holder.img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					item.setDelete(true);
					
//					for (int i = 0; i < ListQuotateActivity.listTotal.getAdapter().getCount(); i++) {
//						
//						QuoateEntry item = (QuoateEntry)ListQuotateActivity.listTotal.getAdapter().getItem(i);
//				    	ListQuotateActivity.arrayListTotal.remove(item);
//				    	notifyDataSetChanged();
//					}
					
				}
			});
//			 holder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//				
//				@Override
//				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//					if (!isChecked){
//				         buttonView.setChecked(false);
//				         
//				   		 for (EditText editText : ListQuotateActivity.editexts) {
//					        	 if (editText.getTag() == buttonView.getTag()){
//					        		 editText.setText(null);
//					        		 editText.setEnabled(false);
//					        		 
//					        		 if (ListQuotateActivity.sparseArray.size() > 0) {
//					        			 int position = Integer.parseInt(editText.getTag().toString());
//					        			 ListQuotateActivity.sparseArray.remove(position);
//					        		 }
//								 }
//				   		 }
//				    }else{
//				    	
//				    	 buttonView.setChecked(true);
//				    	 
//				    	 for (EditText editText : ListQuotateActivity.editexts) {
//				    		 if (editText.getTag() == buttonView.getTag()){
//				        		 editText.setEnabled(true);
//				        		 editText.setFocusable(true);
//							 }
//				         }
//				    }
//					
//				}
//			});
			 
//			 ListQuotateActivity.boxs.add(holder.checkBox);
			 
			 holder.textQty = (TextView)view.findViewById(R.id.textQty);
			 holder.textQty.setTypeface(Util.getBoldFont(context));
			 holder.textQty.setTextSize(20f);
			 holder.textQty.setInputType(InputType.TYPE_CLASS_NUMBER);
			 holder.textQty.setTag(item.getId());
			 
			 holder.textQty.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					popup(item);
				}
			});
				
			 holder.textPrice = (TextView)view.findViewById(R.id.textPrice);
			 holder.textPrice.setTypeface(Util.getBoldFont(context));
			 holder.textPrice.setTextSize(20f);
			 holder.textPrice.setTag(item.getId());

//			 ListQuotateActivity.textPrice.add(holder.textPrice);
			
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		holder.texttitle.setText(item.getImplant());
		 holder.textQty.setText(item.getQty());
		holder.textPrice.setText(item.getPrice());

		
		return view;
	}
	
	public void popup(final QuoateEntry item){
		
		View view = mInflater.inflate(R.layout.popupnumberpick, null);
		final NumberPicker picker = (NumberPicker) view.findViewById(R.id.myNumber);
		
		picker.setMinValue(0);
		picker.setMaxValue(999);
		
		enableNumberPickerManualEditing(picker, true);
		
		AlertDialog.Builder builder =  new AlertDialog.Builder(context);
		builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) {
		    	picker.clearFocus();
		    	item.setQty(String.valueOf(picker.getValue()));
		    	notifyDataSetChanged();
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
	
	public void enableNumberPickerManualEditing(NumberPicker numPicker,
	        boolean enable) {
	    int childCount = numPicker.getChildCount();

	    for (int i = 0; i < childCount; i++) {
	        View childView = numPicker.getChildAt(i);

	        if (childView instanceof EditText) {
	            EditText et = (EditText) childView;
	            et.setFocusable(enable);
	            return;
	        }
	    }
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterTextChanged(Editable s) {
//		BigDecimal total = BigDecimal.ZERO;
//		for (EditText editText : ListQuotateActivity.editexts) {
//			String num = editText.getText().toString();
//			if (!num.isEmpty()) {
//				for(TextView textView : ListQuotateActivity.textPrice){
//					if (editText.getTag() == textView.getTag()){
//						String priceText = textView.getText().toString();
//						BigDecimal price = BigDecimal.valueOf(Double.valueOf(priceText.toString()));
//						BigDecimal qty = BigDecimal.valueOf(Double.valueOf(num));
//						BigDecimal count = qty.multiply(price);
//						total = total.add(count);
//					}
//				}
//			}
//        }
//		ListQuotateActivity.textpriceNum.setText(String.valueOf(total));
		
	}
	
}
