package com.codefriday.bangkokunitrade.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

public class ListMainDialogAdapter extends ArrayAdapter<String> {

	protected Context context;
	private String[] itemsData;
	private LayoutInflater inflater;
	ViewHolder holder;
	AQuery aq;

	int textViewResourceId;

	public class ViewHolder {
		public TextView txtTitle;
		public ImageView logo;
		public int position;
	}

	public ListMainDialogAdapter(Context context, int textViewResourceId,String[] str) {
		super(context, textViewResourceId, str);
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.itemsData = str;
		this.textViewResourceId = textViewResourceId;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (convertView == null) {
			view = inflater.inflate(textViewResourceId, parent, false);
			holder = new ViewHolder();
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		return view;
	}
}
