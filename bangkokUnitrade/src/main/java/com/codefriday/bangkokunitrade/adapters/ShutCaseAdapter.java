package com.codefriday.bangkokunitrade.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.HospitalEntry;
import com.codefriday.bangkokunitrade.util.Util;

public class ShutCaseAdapter extends BaseAdapter implements Filterable{

	private LayoutInflater mInflater;
	private List<HospitalEntry> MasterItem = new ArrayList<HospitalEntry>();
	private List<HospitalEntry> items = new ArrayList<HospitalEntry>();
	protected Context context;
	private String TAG = getClass().getName();
	ViewHolder holder;
	AQuery aQuery;
	private ValueFilter valueFilter;
	
	public class ViewHolder {
		public TextView name;
		public int position;
	}

	public ShutCaseAdapter(Context context, ArrayList<HospitalEntry> items) {
		this.mInflater = LayoutInflater.from(context);
		this.items = items;
		this.MasterItem = items;
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
		HospitalEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_hospital_spinner, parent,
					false);
			holder = new ViewHolder();
			holder.name = (TextView) view.findViewById(R.id.title);
			holder.name.setTypeface(Util.getBoldFont(context));
			holder.name.setTextSize(25f);
			
			
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		holder.name.setText(item.getName());
		return view;
	}

	@Override
	public Filter getFilter() {
		if (valueFilter == null) {
			valueFilter = new ValueFilter();
		}

		return valueFilter;
	}
	
	private class ValueFilter extends Filter {

		// Invoked in a worker thread to filter the data according to the
		// constraint.
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			if (constraint != null && constraint.length() > 0) {
				ArrayList<HospitalEntry> filterList = new ArrayList<HospitalEntry>();
				for (int i = 0; i < MasterItem.size(); i++) {
					if ((MasterItem.get(i).getName().toUpperCase()).contains(constraint.toString().toUpperCase())) {
						HospitalEntry entry = new HospitalEntry();
						entry.setName(MasterItem.get(i).getName());
						entry.setId(MasterItem.get(i).getId());
						filterList.add(entry);
					}
				}
				results.count = filterList.size();
				results.values = filterList;
			} else {
				results.count = MasterItem.size();
				results.values = MasterItem;
			}
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,FilterResults results) {
			items = (ArrayList<HospitalEntry>) results.values;
			notifyDataSetChanged();
		}
	}
}
