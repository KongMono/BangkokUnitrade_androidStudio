package com.codefriday.bangkokunitrade.activity;

import com.androidquery.AQuery;
import com.codefriday.bangkokunitrade.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class OrdersSetUpperLowerActivity extends Activity implements OnTouchListener {
	EditText inputSearch;
	ImageButton imgupper,imglower;
	AQuery aq;
	ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_upper_lower);

		dialog = ProgressDialog.show(this, "","Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();
		init();
		
		setImage();
	}

	private void setImage() {
		imgupper.setBackground(getResources().getDrawable(R.drawable.upper));
		imglower.setBackground(getResources().getDrawable(R.drawable.lower));
		dialog.dismiss();
	}

	private void init() {
		aq = new AQuery(this);
		imgupper = (ImageButton) findViewById(R.id.imgupper);
		imgupper.setOnTouchListener(this);
		imglower = (ImageButton) findViewById(R.id.imglower);
		imglower.setOnTouchListener(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Intent intent = null;
		if (event.getAction() == MotionEvent.ACTION_UP) {
			switch (v.getId()) {
			case R.id.imgupper:
				intent = new Intent(this,TramuaListActivity.class);
				intent.putExtra("position", "upper");
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
				break;
			case R.id.imglower:
				intent = new Intent(this,TramuaListActivity.class);
				intent.putExtra("position", "lower");
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
				break;
			default:
				break;
			}
		}
		return false;
	}
}
