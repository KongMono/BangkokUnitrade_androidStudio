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
import com.codefriday.bangkokunitrade.dataset.MasterDetailimplantEntry;
import com.codefriday.bangkokunitrade.util.Util;

public class MasterDetailImplantAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<MasterDetailimplantEntry> items = new ArrayList<MasterDetailimplantEntry>();
	protected Context context;
	ViewHolder holder;
	AQuery aQuery;

	public class ViewHolder {
		public TextView name;
		public TextView item;
		public TextView description;
		public TextView qty;
		public int position;
	}

	public MasterDetailImplantAdapter(Context context,ArrayList<MasterDetailimplantEntry> items) {
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
		MasterDetailimplantEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_master_detail_implant_list, parent,
					false);
			holder = new ViewHolder();
			holder.name = (TextView) view.findViewById(R.id.name);
			holder.name.setTypeface(Util.getFont(context));
			holder.name.setTextSize(25f);
			
			holder.item = (TextView) view.findViewById(R.id.item);
			holder.item.setTypeface(Util.getFont(context));
			holder.item.setTextSize(25f);
			
			holder.description = (TextView) view.findViewById(R.id.description);
			holder.description.setTypeface(Util.getFont(context));
			holder.description.setTextSize(25f);
			
			holder.qty = (TextView) view.findViewById(R.id.qty);
			holder.qty.setTypeface(Util.getFont(context));
			holder.qty.setTextSize(25f);
			
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.name.setText("Name: " + item.getName());
		holder.item.setText("Item " + item.getItem());
		holder.description.setText("Description: "+ item.getDescription());
		holder.qty.setText("Quantity: " + item.getQty());

		return view;
	}


}
