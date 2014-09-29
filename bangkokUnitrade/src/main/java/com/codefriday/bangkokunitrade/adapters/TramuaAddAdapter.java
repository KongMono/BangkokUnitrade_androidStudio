package com.codefriday.bangkokunitrade.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.OrderCase2Entry;
import com.codefriday.bangkokunitrade.dataset.MasterDetailimplantEntry;
import com.codefriday.bangkokunitrade.util.Util;

public class TramuaAddAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<MasterDetailimplantEntry> items = new ArrayList<MasterDetailimplantEntry>();
	protected Context context;
	private SparseArray<OrderCase2Entry> array = new SparseArray<OrderCase2Entry>();
	private String TAG = getClass().getName();
	ViewHolder holder;
	AQuery aQuery;

	public class ViewHolder {
		public TextView txtTitle;
		public TextView item;
		public EditText edittext;
		public int position;
	}

	public TramuaAddAdapter(Context context, ArrayList<MasterDetailimplantEntry> items) {
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
	
	public SparseArray<OrderCase2Entry> getOrder(){
		return array;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final MasterDetailimplantEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_tramua_add_list, parent, false);
			holder = new ViewHolder();
			holder.txtTitle = (TextView)view.findViewById(R.id.title);
			holder.txtTitle.setTypeface(Util.getBoldFont(context));
			holder.txtTitle.setTextSize(20f);
			holder.item = (TextView) view.findViewById(R.id.txtitem);
			holder.item.setTypeface(Util.getBoldFont(context));
			holder.item.setTextSize(20f);
			holder.edittext = (EditText)view.findViewById(R.id.edittext);
			holder.edittext.setInputType(InputType.TYPE_CLASS_NUMBER);
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.txtTitle.setText(item.getDescription());
		holder.txtTitle.setTag(item.getId());
		holder.edittext.setText(item.getQty());
		holder.edittext.setTag(item.getId());
		holder.item.setText(item.getItem());
		
		holder.edittext.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				item.setQty(s.toString());
				
			}
		});
		
		return view;
	}
	
	

}
