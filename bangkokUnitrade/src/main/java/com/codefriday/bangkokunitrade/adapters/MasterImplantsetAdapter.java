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
import com.codefriday.bangkokunitrade.dataset.MasterimplantEntry;

public class MasterImplantsetAdapter extends BaseAdapter implements Filterable {

	private LayoutInflater mInflater;
	private List<MasterimplantEntry> MasterItem = new ArrayList<MasterimplantEntry>();
	private List<MasterimplantEntry> items = new ArrayList<MasterimplantEntry>();
	protected Context context;
	ViewHolder holder;
	AQuery aQuery;
	private ValueFilter valueFilter;

	public class ViewHolder {
		public TextView name;
		public TextView desc;
		public TextView item;
		public int position;
	}

	public MasterImplantsetAdapter(Context context,
			ArrayList<MasterimplantEntry> items) {
		this.mInflater = LayoutInflater.from(context);
		this.items = items;
		MasterItem = items;
		this.context = context;
		aQuery = new AQuery(context);
		getFilter();
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
		MasterimplantEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_master_implant_list, parent,
					false);
			holder = new ViewHolder();
			holder.name = (TextView) view.findViewById(R.id.name);
			holder.item = (TextView) view.findViewById(R.id.item);
			holder.desc = (TextView) view.findViewById(R.id.desc);
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.name.setText(item.getName());
		holder.desc.setText("Description: " + item.getDescription());
		holder.item.setText("item: "+ item.getItem());
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
				ArrayList<MasterimplantEntry> filterList = new ArrayList<MasterimplantEntry>();
				for (int i = 0; i < MasterItem.size(); i++) {
					if ((MasterItem.get(i).getName().toUpperCase()).contains(constraint.toString().toUpperCase())) {
						MasterimplantEntry entry = new MasterimplantEntry();
						entry.setName(MasterItem.get(i).getName());
						entry.setDescription(MasterItem.get(i).getDescription());
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
			items = (ArrayList<MasterimplantEntry>) results.values;
			notifyDataSetChanged();
		}
	}

}
