package com.codefriday.bangkokunitrade.activity;

import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.util.PreferencesApp;
import com.codefriday.bangkokunitrade.util.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class ListSaleActivity extends Activity implements OnTouchListener {
	private Button btnSetcase;
	private Button btnCheckcase;
	private Button btnQuotation;
	private Button btnReport;
	private Button btnCatalog;
	private Button btnCase;
	PreferencesApp app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_sale);
		
		app = new PreferencesApp(this);
		btnSetcase = (Button) findViewById(R.id.button1);
		btnCheckcase = (Button) findViewById(R.id.button2);
		btnQuotation = (Button) findViewById(R.id.button3);
		btnReport = (Button) findViewById(R.id.button4);
		btnCatalog = (Button) findViewById(R.id.button5);
		btnCase = (Button) findViewById(R.id.button6); 

		btnSetcase.setOnTouchListener(this);
		btnSetcase.setTypeface(Util.getBoldFont(this));
		btnSetcase.setTextSize(50f);
		btnSetcase.setTextColor(getResources().getColor(R.color.Blue_font));

		btnCheckcase.setOnTouchListener(this);
		btnCheckcase.setTypeface(Util.getBoldFont(this));
		btnCheckcase.setTextSize(50f);
		btnCheckcase.setTextColor(getResources().getColor(R.color.Blue_font));

		btnQuotation.setOnTouchListener(this);
		btnQuotation.setTypeface(Util.getBoldFont(this));
		btnQuotation.setTextSize(50f);
		btnQuotation.setTextColor(getResources().getColor(R.color.Blue_font));

		btnReport.setOnTouchListener(this);
		btnReport.setTypeface(Util.getBoldFont(this));
		btnReport.setTextSize(50f);
		btnReport.setTextColor(getResources().getColor(R.color.Blue_font));


		btnCatalog.setOnTouchListener(this);
		btnCatalog.setTypeface(Util.getBoldFont(this));
		btnCatalog.setTextSize(50f);
		btnCatalog.setTextColor(getResources().getColor(R.color.Blue_font));
		
		btnCase.setOnTouchListener(this);
		btnCase.setTypeface(Util.getBoldFont(this));
		btnCase.setTextSize(50f);
		btnCase.setTextColor(getResources().getColor(R.color.Blue_font));
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_logout:
	        	app.clearData();
	        	Intent intent = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent);
				finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.button1:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				intent = new Intent(ListSaleActivity.this,OrdersListChooseActivity.class);
				startActivity(intent);
				return true;
			}

			break;
		case R.id.button2:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				intent = new Intent(ListSaleActivity.this,CheckcaseImplantActivity.class);
				startActivity(intent);
				return true;
			}

			break;
		case R.id.button3:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				intent = new Intent(ListSaleActivity.this, QuotateActivity.class);
				startActivity(intent);
				return true;
			}
			
			break;
		case R.id.button4:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				intent = new Intent(ListSaleActivity.this,ReportActivity.class);
				startActivity(intent);
				return true;
			}
			
			break;
		case R.id.button5:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				intent = new Intent(ListSaleActivity.this,CatalogActivity.class);
				startActivity(intent);
				return true;
			}
			break;
			
		case R.id.button6:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				intent = new Intent(ListSaleActivity.this,ShutCaseActivity.class);
				startActivity(intent);
				return true;
			}
			break;

		default:
			break;
		}
		return false;
	}

}
