package com.codefriday.bangkokunitrade.adapters;

import java.text.DecimalFormat;
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
import com.codefriday.bangkokunitrade.dataset.QuoateEntry;
import com.codefriday.bangkokunitrade.util.Util;

public class ListQuoateAdapter extends BaseAdapter implements Filterable{
	
	private LayoutInflater mInflater;
	private List<QuoateEntry> items = new ArrayList<QuoateEntry>();
	private List<QuoateEntry> MasterItem = new ArrayList<QuoateEntry>();
	protected Context context;
	private String TAG = getClass().getName();
	ViewHolder holder;
	AQuery aQuery;
	private ValueFilter valueFilter;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");
	
	public class ViewHolder {
		public TextView texttitle;
		public int position;
	}

	public ListQuoateAdapter(Context context, ArrayList<QuoateEntry> items) {
		this.mInflater = LayoutInflater.from(context);
		this.items = items;
		MasterItem = items;
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
				ArrayList<QuoateEntry> filterList = new ArrayList<QuoateEntry>();
				for (int i = 0; i < MasterItem.size(); i++) {
					if ((MasterItem.get(i).getImplant().toUpperCase()).contains(constraint.toString().toUpperCase())) {
						QuoateEntry entry = new QuoateEntry();
						entry.setImplant(MasterItem.get(i).getImplant());
						entry.setPrice(MasterItem.get(i).getPrice());
						entry.setId(MasterItem.get(i).getId());
						entry.setQty(MasterItem.get(i).getQty());
						entry.setSale(MasterItem.get(i).getSale());
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
			items = (ArrayList<QuoateEntry>) results.values;
			notifyDataSetChanged();
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		QuoateEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_quoate_list, parent, false);
			holder = new ViewHolder();
			
			
			 holder.texttitle  = (TextView) view.findViewById(R.id.texttitle);
			 holder.texttitle.setTypeface(Util.getBoldFont(context));
			 holder.texttitle.setTextSize(20f);
			 holder.texttitle.setId(item.getId());
			 holder.texttitle.setTag(item.getId());
			 
			
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		holder.texttitle.setText(item.getImplant());
		
		return view;
	}


	
}
