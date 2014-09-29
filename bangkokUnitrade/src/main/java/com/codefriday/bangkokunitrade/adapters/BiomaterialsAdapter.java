package com.codefriday.bangkokunitrade.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.SetEntry;

public class BiomaterialsAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<SetEntry> items = new ArrayList<SetEntry>();
	protected Context context;
	private String TAG = getClass().getName();
	ViewHolder holder;
	AQuery aQuery;

	public class ViewHolder {
		public TextView txtTitle;
		public TextView edittext;
		public int position;
	}

	public BiomaterialsAdapter(Context context, ArrayList<SetEntry> items) {
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
		final SetEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_tramua_biomaterials_list, parent, false);
			holder = new ViewHolder();
			holder.txtTitle = (TextView)view.findViewById(R.id.title);
			holder.edittext = (TextView)view.findViewById(R.id.num);
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.txtTitle.setText(item.getName());
		holder.txtTitle.setTag(item.getId());
		
		holder.edittext.setText("0");
		holder.edittext.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					View view = mInflater.inflate(R.layout.popupnumberpick, null);
					final NumberPicker picker = (NumberPicker) view.findViewById(R.id.myNumber);
					
					picker.setMinValue(1);
					picker.setMaxValue(9999);
					
					AlertDialog.Builder builder =  new AlertDialog.Builder(context);
					builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog, int which) {
					    	
					   	 	picker.clearFocus();
					   	 	holder.edittext.setText(String.valueOf(picker.getValue()));
					    	item.setQty(String.valueOf(picker.getValue()));
					    	
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
				return false;
			}
		});
		
		
//		holder.edittext.addTextChangedListener(new TextWatcher() {
//			
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void afterTextChanged(Editable s) {
//				item.setQty(s.toString());
//			}
//		});
		
		
		
		
		return view;
	}
}
