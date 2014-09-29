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

public class ListProductActivity extends Activity implements OnTouchListener {
	private Button btnStatuscase,btnCheckcase,btnReport;
	PreferencesApp app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_list_product);
		
		app = new PreferencesApp(this);
		btnCheckcase = (Button) findViewById(R.id.button1);
		btnStatuscase = (Button) findViewById(R.id.button2);
		btnReport = (Button) findViewById(R.id.button3);

		btnStatuscase.setOnTouchListener(this);
		btnStatuscase.setTypeface(Util.getBoldFont(this));
		btnStatuscase.setTextSize(50f);
		btnStatuscase.setTextColor(getResources().getColor(R.color.Blue_font));

		btnCheckcase.setOnTouchListener(this);
		btnCheckcase.setTypeface(Util.getBoldFont(this));
		btnCheckcase.setTextSize(50f);
		btnCheckcase.setTextColor(getResources().getColor(R.color.Blue_font));


		btnReport.setOnTouchListener(this);
		btnReport.setTypeface(Util.getBoldFont(this));
		btnReport.setTextSize(50f);
		btnReport.setTextColor(getResources().getColor(R.color.Blue_font));
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
	        	Intent intent = new Intent(this,MainActivity.class);
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
				intent = new Intent(ListProductActivity.this,CheckcaseImplantActivity.class);
				startActivity(intent);
				return true;
			}

			break;
		case R.id.button2:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				intent = new Intent(ListProductActivity.this,ShutCaseActivity.class);
				startActivity(intent);
				return true;
			}

			break;
		case R.id.button3:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				intent = new Intent(ListProductActivity.this, ReportActivity.class);
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
