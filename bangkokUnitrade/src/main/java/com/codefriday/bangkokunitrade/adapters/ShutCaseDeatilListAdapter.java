package com.codefriday.bangkokunitrade.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.implantEntry;
import com.codefriday.bangkokunitrade.util.Util;

public class ShutCaseDeatilListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<implantEntry> items = new ArrayList<implantEntry>();
	protected Context context;
	ViewHolder holder;
	AQuery aQuery;

	public class ViewHolder {
		public TextView texttitle;
		public EditText edittextUse;
		public TextView textqty;
		public int position;
	}

	public ShutCaseDeatilListAdapter(Context context,ArrayList<implantEntry> items) {
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		final implantEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_shutcase_detail_list,parent, false);
			holder = new ViewHolder();

			holder.texttitle = (TextView) view.findViewById(R.id.texttitle);
			holder.texttitle.setTypeface(Util.getFont(context));
			holder.texttitle.setTextSize(20f);

			holder.textqty = (TextView) view.findViewById(R.id.textqty);
			holder.textqty.setTypeface(Util.getFont(context));
			holder.textqty.setTextSize(20f);


			holder.edittextUse = (EditText) view.findViewById(R.id.edittextUse);
			holder.edittextUse.setTypeface(Util.getFont(context));
			holder.edittextUse.setTextSize(20f);
			holder.edittextUse.setInputType(InputType.TYPE_CLASS_NUMBER);
			
		
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.texttitle.setText(item.getNum() + " " + item.getSet_implant_name() + " "+ item.getItem() + " "  + item.getDescription());
		holder.textqty.setText(item.getQty());
		holder.edittextUse.setText(item.getUse());
		
		holder.edittextUse.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				item.setUse(s.toString());
			}
		});

		return view;
	}
}
