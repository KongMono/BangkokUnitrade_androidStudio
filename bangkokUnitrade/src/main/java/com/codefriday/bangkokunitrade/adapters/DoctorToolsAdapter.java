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
import com.codefriday.bangkokunitrade.dataset.DoctorToolsEntry;
import com.codefriday.bangkokunitrade.util.Util;

public class DoctorToolsAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<DoctorToolsEntry> items = new ArrayList<DoctorToolsEntry>();
	protected Context context;
	private String TAG = getClass().getName();
	ViewHolder holder;
	AQuery aQuery;

	public class ViewHolder {
		public TextView title;
		public int position;
	}

	public DoctorToolsAdapter(Context context, ArrayList<DoctorToolsEntry> items) {
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
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DoctorToolsEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_transport_spinner, parent, false);
			holder = new ViewHolder();
			holder.title = (TextView)view.findViewById(R.id.title);
			holder.title.setTypeface(Util.getBoldFont(context));
			holder.title.setTextSize(25f);
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		holder.title.setText(item.getNo() + ". " + item.getTool());

		return view;
	}
}
