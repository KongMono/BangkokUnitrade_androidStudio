package com.codefriday.bangkokunitrade.activity;

import com.androidquery.AQuery;
import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.DataOrderDataset;
import com.codefriday.bangkokunitrade.util.Util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;

public class OrdersListChooseActivity extends Activity implements OnTouchListener {
	EditText inputSearch;
	Button img1,img2,img3,img4;
	AQuery aq;
	ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_choose_list);

		dialog = ProgressDialog.show(this, "","Loading. Please wait...", true);
		dialog.setCancelable(true);
		dialog.show();
		init();
		
		dialog.dismiss();
	}


	private void init() {
		aq = new AQuery(this);
		img1 = (Button) findViewById(R.id.img1);
		img1.setTypeface(Util.getBoldFont(this));
		img1.setTextSize(50f);
		img1.setTextColor(getResources().getColor(R.color.Blue_font));
		img1.setOnTouchListener(this);
		
		img2 = (Button) findViewById(R.id.img2);
		img2.setTypeface(Util.getBoldFont(this));
		img2.setTextSize(50f);
		img2.setTextColor(getResources().getColor(R.color.Blue_font));
		img2.setOnTouchListener(this);
		
		img3 = (Button) findViewById(R.id.img3);
		img3.setTypeface(Util.getBoldFont(this));
		img3.setTextSize(50f);
		img3.setTextColor(getResources().getColor(R.color.Blue_font));
		img3.setOnTouchListener(this);
		
		img4 = (Button) findViewById(R.id.img4);
		img4.setTypeface(Util.getBoldFont(this));
		img4.setTextSize(50f);
		img4.setTextColor(getResources().getColor(R.color.Blue_font));
		img4.setOnTouchListener(this);
		
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
			case R.id.img1:
				DataOrderDataset.getInstance().setOrder_type("1");
				DataOrderDataset.getInstance().setOrderCase1Entries(null);
				intent = new Intent(this,OrdersSetUpperLowerActivity.class);
				startActivity(intent);
				break;
			case R.id.img2:
				DataOrderDataset.getInstance().setOrder_type("2");
				DataOrderDataset.getInstance().setOrderCase2Entries(null);
				intent = new Intent(this,OrdersSetUpperLowerActivity.class);
				startActivity(intent);
				break;
			case R.id.img3:
				DataOrderDataset.getInstance().setOrder_type("3");
				DataOrderDataset.getInstance().setOrderCase3Entries(null);
				intent = new Intent(this,BiomaterialsActivity.class);
				startActivity(intent);
				break;
			case R.id.img4:
				DataOrderDataset.getInstance().setOrder_type("4");
				DataOrderDataset.getInstance().setOrderCase4Entries(null);
				intent = new Intent(this,SetMasterImplantActivity.class);
				startActivity(intent);
				break;
			default:
				break;
			}
			
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		}
		return false;
	}
}
