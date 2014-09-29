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
import com.codefriday.bangkokunitrade.dataset.CheckcaseEntry;
import com.codefriday.bangkokunitrade.util.Util;

public class CheckcaseAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<CheckcaseEntry> items = new ArrayList<CheckcaseEntry>();
	protected Context context;
	private String TAG = getClass().getName();
	ViewHolder holder;
	AQuery aQuery;

	public class ViewHolder {
		public TextView order_no;
		public TextView hospital;
		public TextView status;
		public int position;
	}

	public CheckcaseAdapter(Context context, ArrayList<CheckcaseEntry> items) {
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
		return items.get(position).getOrder_id();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CheckcaseEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_checkcase_list, parent, false);
			holder = new ViewHolder();
			holder.order_no = (TextView)view.findViewById(R.id.order_no);
			holder.order_no.setTypeface(Util.getBoldFont(context));
			holder.order_no.setTextSize(25f);
			
			holder.status = (TextView)view.findViewById(R.id.status);
			holder.status.setTypeface(Util.getBoldFont(context));
			holder.status.setTextSize(25f);
			
			holder.hospital = (TextView)view.findViewById(R.id.hospital);
			holder.hospital.setTypeface(Util.getBoldFont(context));
			holder.hospital.setTextSize(25f);
			
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		holder.order_no.setText("เลขที่: " + item.getOrder_no());
		holder.hospital.setText(item.getHospital());
		holder.status.setText("สถานะการสั่งซื้อ: " + item.getStatus());
		
		
		return view;
	}
}
