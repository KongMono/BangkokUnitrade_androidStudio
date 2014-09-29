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
import com.codefriday.bangkokunitrade.dataset.ToolsSetimplantEntry;
import com.codefriday.bangkokunitrade.util.Util;

public class ToolsImplantsetAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<ToolsSetimplantEntry> MasterItem = new ArrayList<ToolsSetimplantEntry>();
	private List<ToolsSetimplantEntry> items = new ArrayList<ToolsSetimplantEntry>();
	protected Context context;
	ViewHolder holder;
	AQuery aQuery;

	public class ViewHolder {
		public TextView text;
		public int position;
	}

	public ToolsImplantsetAdapter(Context context,
			ArrayList<ToolsSetimplantEntry> items) {
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
		return items.get(position).getNo();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ToolsSetimplantEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_tool_set_implant_list, parent,
					false);
			holder = new ViewHolder();
			holder.text = (TextView) view.findViewById(R.id.text);
			holder.text.setTypeface(Util.getBoldFont(context));
			holder.text.setTextColor(context.getResources().getColor(R.color.red));
			holder.text.setTextSize(25f);
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.text.setText(item.getNo() + ". " + item.getName() + " " + item.getMessage());
		

		return view;
	}


}
