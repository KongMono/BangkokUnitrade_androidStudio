package com.codefriday.bangkokunitrade.activity;

import java.util.ArrayList;

import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.CheckcaseEntry;
import com.codefriday.bangkokunitrade.json.CheckcasePaser;
import com.codefriday.bangkokunitrade.adapters.CheckcaseAdapter;
import com.codefriday.bangkokunitrade.util.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ListReportActivity extends Activity implements OnItemClickListener {
	private ListView listTools;
	private TextView textheader;
	CheckcaseAdapter adapter;
	CheckcasePaser checkcasePaser = new CheckcasePaser();
	ArrayList<CheckcaseEntry> arrayList = new ArrayList<CheckcaseEntry>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checklist_tools);
		
		init();
		
//		arrayList = checkcasePaser.getData();
//		adapter = new CheckcaseAdapter(this, arrayList);
//		adapter = new CheckcaseAdapter(ListReportActivity.this, arrayList);
//		listTools.setAdapter(adapter);
		
	}

	private void init() {
		listTools = (ListView) findViewById(R.id.list);
		listTools.setOnItemClickListener(this);
		
		textheader = (TextView) findViewById(R.id.textheader);
		textheader.setTypeface(Util.getBoldFont(this));
		textheader.setTextSize(30f);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int id, long arg3) {
		showPopUp();
	}
	
	private void showPopUp() {

		final Builder helpBuilder = new Builder(this);
		final LayoutInflater inflater = getLayoutInflater();
		final View view = inflater.inflate(R.layout.layout_confirm, null);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		helpBuilder.setView(view);

		final AlertDialog helpDialog = helpBuilder.create();
		helpDialog.show();
	}

}
