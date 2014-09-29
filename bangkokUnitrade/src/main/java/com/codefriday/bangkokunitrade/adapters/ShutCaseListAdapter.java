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
import com.codefriday.bangkokunitrade.dataset.ShutCaseListEntry;
import com.codefriday.bangkokunitrade.util.Util;

public class ShutCaseListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<ShutCaseListEntry> items = new ArrayList<ShutCaseListEntry>();
	protected Context context;
	private String TAG = getClass().getName();
	ViewHolder holder;
	AQuery aQuery;

	public class ViewHolder {
		public TextView textSurgeryDate;
		public TextView textSurgeryTime;
		public TextView textpatient;
		public TextView texthn;
		public TextView textdoctor;
		public int position;
	}

	public ShutCaseListAdapter(Context context, ArrayList<ShutCaseListEntry> items) {
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
		return items.get(position).getCase_id();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ShutCaseListEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_shutcase_list, parent, false);
			holder = new ViewHolder();
			holder.textSurgeryDate = (TextView)view.findViewById(R.id.textSurgeryDate);
			holder.textSurgeryDate.setTypeface(Util.getBoldFont(context));
			holder.textSurgeryDate.setTextSize(25f);

			holder.textSurgeryTime = (TextView)view.findViewById(R.id.textSurgeryTime);
			holder.textSurgeryTime.setTypeface(Util.getBoldFont(context));
			holder.textSurgeryTime.setTextSize(25f);
			
			holder.texthn = (TextView)view.findViewById(R.id.texthn);
			holder.texthn.setTypeface(Util.getBoldFont(context));
			holder.texthn.setTextSize(25f);
			
			holder.textpatient = (TextView)view.findViewById(R.id.textpatient);
			holder.textpatient.setTypeface(Util.getBoldFont(context));
			holder.textpatient.setTextSize(25f);
			
			holder.textdoctor = (TextView)view.findViewById(R.id.textdoctor);
			holder.textdoctor.setTypeface(Util.getBoldFont(context));
			holder.textdoctor.setTextSize(25f);
			
			
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		holder.textSurgeryDate.setText("วันที่ผ่าตัด: " + item.getSurgery_date());
		holder.textSurgeryTime.setText("เวลาผ่าตัด: " + item.getSurgery_time());
		holder.texthn.setText("HN: " + item.getHn());
		holder.textpatient.setText("ชื่อคนไข้: " + item.getPatient());
		holder.textdoctor.setText("แพทย์ผู้ผ่าตัด: " + item.getDoctor());
		
		return view;
	}
}
