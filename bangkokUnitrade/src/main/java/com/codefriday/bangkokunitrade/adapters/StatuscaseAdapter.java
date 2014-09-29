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
import com.codefriday.bangkokunitrade.dataset.StatuscaseEntry;
import com.codefriday.bangkokunitrade.util.Util;

public class StatuscaseAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<StatuscaseEntry> items = new ArrayList<StatuscaseEntry>();
	protected Context context;
	private String TAG = getClass().getName();
	ViewHolder holder;
	AQuery aQuery;

	public class ViewHolder {
		public TextView title;
		public TextView detail;
		public int position;
	}

	public StatuscaseAdapter(Context context, ArrayList<StatuscaseEntry> items) {
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
		StatuscaseEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_statuscase_list, parent, false);
			holder = new ViewHolder();
			holder.title = (TextView)view.findViewById(R.id.title);
			holder.title.setTypeface(Util.getBoldFont(context));
			holder.title.setTextSize(30f);
			
			holder.detail = (TextView)view.findViewById(R.id.detail);
			holder.detail.setTypeface(Util.getBoldFont(context));
			holder.detail.setTextSize(30f);
			
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		holder.title.setText(item.getTitle());
		holder.detail.setText(item.getDetail());
		
		return view;
	}
}
