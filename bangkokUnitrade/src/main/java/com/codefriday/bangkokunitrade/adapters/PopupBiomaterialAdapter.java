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
import com.codefriday.bangkokunitrade.dataset.PopupSetEntry;

public class PopupBiomaterialAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<PopupSetEntry> items = new ArrayList<PopupSetEntry>();
	protected Context context;
	private String TAG = getClass().getName();
	ViewHolder holder;
	AQuery aQuery;

	public class ViewHolder {
		public TextView txtTitle;
		public EditText edittext;
		public int position;
	}

	public PopupBiomaterialAdapter(Context context, ArrayList<PopupSetEntry> items) {
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
		final PopupSetEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_tramua_biomaterials_list, parent, false);
			holder = new ViewHolder();
			holder.txtTitle = (TextView)view.findViewById(R.id.title);
			holder.edittext = (EditText)view.findViewById(R.id.edittext);
			holder.edittext.setInputType(InputType.TYPE_CLASS_NUMBER);
			
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.txtTitle.setText(item.getName());
		holder.txtTitle.setTag(item.getId());
		
		holder.edittext.setText(item.getQty());
		holder.edittext.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				item.setQty(s.toString());
			}
		});
		
		return view;
	}
}
