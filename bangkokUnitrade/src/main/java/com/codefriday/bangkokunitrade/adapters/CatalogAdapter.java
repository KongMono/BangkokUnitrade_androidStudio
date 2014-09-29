package com.codefriday.bangkokunitrade.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.CatalogEntry;

public class CatalogAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<CatalogEntry> items = new ArrayList<CatalogEntry>();
	protected Context context;
	private String TAG = getClass().getName();
	ViewHolder holder;
	AQuery aQuery;

	public class ViewHolder {
		public CheckBox checkbox;
		public ImageView pic;
		public int position;
	}

	public CatalogAdapter(Context context, ArrayList<CatalogEntry> items) {
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
		final CatalogEntry item = items.get(position);
		View view = convertView;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_catalog_list, parent, false);
			holder = new ViewHolder();
			holder.checkbox = (CheckBox) view.findViewById(R.id.checkbox);
			holder.pic = (ImageView) view.findViewById(R.id.pic);
			holder.position = position;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}

		holder.checkbox.setText(item.getName());
		holder.pic.setBackgroundResource(R.drawable.pdf_download_icon);
		holder.pic.setTag(item.getUrl());
		
		
		holder.pic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Uri uri =  Uri.parse(v.getTag().toString());
				 try{
					  Intent intentUrl = new Intent(Intent.ACTION_VIEW);
					  intentUrl.setDataAndType(uri, "application/pdf");
					  intentUrl.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					  
					  Intent intent = Intent.createChooser(intentUrl, "Open File");
					  try {
						  context.startActivity(intent);
					  } catch (ActivityNotFoundException e) {
					      // Instruct the user to install a PDF reader here, or something
					  } 
					
				 }catch (ActivityNotFoundException e) {
					 Toast.makeText(context, "No PDF Viewer Installed", Toast.LENGTH_LONG).show();
				 }
				
			}
		});
		
		holder.checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					item.setIschecked(true);
				}else{
					item.setIschecked(false);
				}
			}
		});

		return view;
	}
}
