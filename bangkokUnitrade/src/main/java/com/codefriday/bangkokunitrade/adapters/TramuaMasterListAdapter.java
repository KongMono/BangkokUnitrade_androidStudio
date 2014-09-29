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
import com.codefriday.bangkokunitrade.dataset.SetEntry;

public class TramuaMasterListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<SetEntry> items = new ArrayList<SetEntry>();
	protected Context context;
	private String TAG = getClass().getName();
	ViewHolder holder;
	AQuery aQuery;

	public class ViewHolder {
		public TextView txtTitle;
		public int position;
	}

	public TramuaMasterListAdapter(Context context, ArrayList<SetEntry> items) {
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
		SetEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_tramua_list, parent, false);
			holder = new ViewHolder();
			holder.txtTitle = (TextView)view.findViewById(R.id.title);
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.txtTitle.setText(item.getName());
		
		
		return view;
	}
}
