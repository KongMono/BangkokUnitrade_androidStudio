package com.codefriday.bangkokunitrade.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.DataOrderDataset;
import com.codefriday.bangkokunitrade.dataset.OrderCase1Entry;
import com.codefriday.bangkokunitrade.util.Util;

public class TramuaConfirmAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<OrderCase1Entry> items = new ArrayList<OrderCase1Entry>();
	protected Context context;
	private String TAG = getClass().getName();
	ViewHolder holder;
	DataOrderDataset dataOrderDataset = DataOrderDataset.getInstance();
	SparseArray<OrderCase1Entry> array = new SparseArray<OrderCase1Entry>();
	AQuery aQuery;
	private HashMap<String, OrderCase1Entry> hashMap = new HashMap<String, OrderCase1Entry>();
	public class ViewHolder {
		public TextView txtTitle;
		public int position;
		public ImageView imgDelete;
	}

	public TramuaConfirmAdapter(Context context, ArrayList<OrderCase1Entry> items,HashMap<String, OrderCase1Entry> hashMap) {
		this.mInflater = LayoutInflater.from(context);
		this.items = items;
		this.context = context;
		this.hashMap = hashMap;
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
		OrderCase1Entry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_tramua_comfirm_list, parent, false);
			holder = new ViewHolder();
			holder.txtTitle = (TextView)view.findViewById(R.id.title);
			holder.txtTitle.setTypeface(Util.getBoldFont(context));
			holder.txtTitle.setTextSize(20f);
			
			holder.imgDelete = (ImageView)view.findViewById(R.id.img);
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.txtTitle.setText(item.getMasterimplant_name() + " (" + item.getIndication_name() + ")");
		
		holder.imgDelete.setTag(position);
		
		array.put(position, item);
		
		holder.imgDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Integer index = (Integer) v.getTag();
                items.remove(index.intValue());
				
				String key  = array.get(index).getMasterimplant_id()
						.concat(array.get(index).getTrauma_id())
						.concat(array.get(index).getIndication_id());
                
                dataOrderDataset.getOrderCase1Entries().remove(key);
                
                notifyDataSetChanged();
			}
		});
		
		return view;
	}
}
