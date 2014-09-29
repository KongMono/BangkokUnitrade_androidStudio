package com.codefriday.bangkokunitrade.adapters;

import java.math.BigDecimal;
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
import com.codefriday.bangkokunitrade.dataset.QuoateEntry;
import com.codefriday.bangkokunitrade.util.Util;

public class QuoateAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<QuoateEntry> items = new ArrayList<QuoateEntry>();
	protected Context context;
	private String TAG = getClass().getName();
	ViewHolder holder;
	AQuery aQuery;

	public class ViewHolder {
		public TextView Textcheckbox;
		public TextView textQty;
		public TextView textfixPrice;
		public TextView textTotal;
		public int position;
	}

	public QuoateAdapter(Context context, ArrayList<QuoateEntry> items) {
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
		QuoateEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_quoate_list_comfirm, parent, false);
			holder = new ViewHolder();
			holder.Textcheckbox = (TextView)view.findViewById(R.id.textcheckbox);
			holder.Textcheckbox.setTypeface(Util.getBoldFont(context));
			holder.Textcheckbox.setTextSize(20f);
			
			holder.textQty = (TextView)view.findViewById(R.id.textQty);
			holder.textQty.setTypeface(Util.getBoldFont(context));
			holder.textQty.setTextSize(20f);
			
			holder.textfixPrice = (TextView)view.findViewById(R.id.textfixPrice);
			holder.textfixPrice.setTypeface(Util.getBoldFont(context));
			holder.textfixPrice.setTextSize(20f);
			
			holder.textTotal = (TextView)view.findViewById(R.id.textTotal);
			holder.textTotal.setTypeface(Util.getBoldFont(context));
			holder.textTotal.setTextSize(20f);
			
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		holder.Textcheckbox.setText(item.getImplant());
		holder.textQty.setText(item.getQty());
		holder.textfixPrice.setText(item.getPrice());
		
		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(item.getPrice()));
		BigDecimal qty = BigDecimal.valueOf(Double.parseDouble(item.getQty()));
		BigDecimal count = qty.multiply(price);
		
		holder.textTotal.setText(String.valueOf(count));
		
		return view;
	}
}
