package com.codefriday.bangkokunitrade.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.StatuscaseDetailListEntry;
import com.codefriday.bangkokunitrade.util.Util;

public class CheckcaseListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<StatuscaseDetailListEntry> items = new ArrayList<StatuscaseDetailListEntry>();
	protected Context context;
	private String TAG = getClass().getName();
	ViewHolder holder;
	AQuery aQuery;
	String order_type;

	public class ViewHolder {
		public TextView textNumber;
		public TextView textImplant;
		public TextView textItem;
		public TextView textDesc;
		public TextView textQty;
		public TextView textDQty;
		public int position;
	}

	public CheckcaseListAdapter(Context context, ArrayList<StatuscaseDetailListEntry> items,String order_type) {
		this.mInflater = LayoutInflater.from(context);
		this.items = items;
		this.context = context;
		this.order_type = order_type;
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
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		StatuscaseDetailListEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_checkcase_list_detail, parent, false);
			holder = new ViewHolder();
			
			holder.textNumber = (TextView)view.findViewById(R.id.textNumber);
			holder.textNumber.setTypeface(Util.getBoldFont(context));
			holder.textNumber.setTextSize(17f);
			
			
			
			holder.textImplant = (TextView)view.findViewById(R.id.textImplant);
			holder.textImplant.setTypeface(Util.getBoldFont(context));
			holder.textImplant.setTextSize(17f);
			
			holder.textItem = (TextView)view.findViewById(R.id.textItem);
			holder.textItem.setTypeface(Util.getBoldFont(context));
			holder.textItem.setTextSize(17f);

			holder.textDesc = (TextView)view.findViewById(R.id.textDesc);
			holder.textDesc.setTypeface(Util.getBoldFont(context));
			holder.textDesc.setTextSize(17f);
			
			holder.textQty = (TextView)view.findViewById(R.id.textQty);
			holder.textQty.setTypeface(Util.getBoldFont(context));
			holder.textQty.setTextSize(17f);
			
			holder.textDQty = (TextView)view.findViewById(R.id.textDQty);
			holder.textDQty.setTypeface(Util.getBoldFont(context));
			holder.textDQty.setTextSize(17f);
			
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		holder.textNumber.setText(String.valueOf(position + 1));
		
		if (order_type.equalsIgnoreCase("3")) {
			holder.textImplant.setText(item.getBiomaterial());
			holder.textItem.setText("-");
			holder.textDesc.setText("-");
		}else{
			holder.textImplant.setText(item.getImplant());
			holder.textItem.setText(item.getItem());
			holder.textDesc.setText(item.getDescription());
		}
		holder.textQty.setText(item.getQty());
		holder.textDQty.setText(item.getDelivery_qty());
		
		
		return view;
	}
}
