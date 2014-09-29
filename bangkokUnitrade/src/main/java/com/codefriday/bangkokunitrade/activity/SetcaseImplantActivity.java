package com.codefriday.bangkokunitrade.activity;


import com.codefriday.bangkokunitrade.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SetcaseImplantActivity extends Activity implements OnTouchListener {
	
//	private RadioGroup radioGroupTools, radioGroupCase;
//	private RadioButton radioButtonTools1, radioButtonTools2, radioButtonCase1,
//			radioButtonCase2;
//	private ListView list;
//	private EditText Search;
//	private Button buttonNext;
//	private TextView headerList, headerRadioTool, headerRadioCase;
//	private int selectedId;
//	private TabHost tabhost;
//	ImplantsetAdapter implantsetAdapter;
//	ImplantPaser implantPaser = new ImplantPaser();
//	ArrayList<implantEntry> arrayList = new ArrayList<implantEntry>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setcase_detail);
		init();
//		arrayList = implantPaser.getData();
//		implantsetAdapter = new ImplantsetAdapter(SetcaseImplantActivity.this, arrayList);
//		list.setAdapter(implantsetAdapter);
	}

	private void init() {
		
//		list = (ListView) findViewById(R.id.list);
//		
//		Search = (EditText) findViewById(R.id.editSearch);
//		Search.setTypeface(Util.getBoldFont(this));
//		Search.setTextSize(30f);
//
//		headerList = (TextView) findViewById(R.id.textheader);
//		headerList.setTypeface(Util.getBoldFont(this));
//		headerList.setTextSize(30f);
//
//		headerRadioTool = (TextView) findViewById(R.id.textheadTool);
//		headerRadioTool.setTypeface(Util.getBoldFont(this));
//		headerRadioTool.setTextSize(30f);
//
//		headerRadioCase = (TextView) findViewById(R.id.textheadCase);
//		headerRadioCase.setTypeface(Util.getBoldFont(this));
//		headerRadioCase.setTextSize(30f);
//
//		radioButtonTools1 = (RadioButton) findViewById(R.id.radioButtonTools1);
//		radioButtonTools1.setTypeface(Util.getFont(this));
//		radioButtonTools1.setTextSize(25f);
//
//		radioButtonTools2 = (RadioButton) findViewById(R.id.radioButtonTools2);
//		radioButtonTools2.setTypeface(Util.getFont(this));
//		radioButtonTools2.setTextSize(25f);
//
//		radioButtonCase1 = (RadioButton) findViewById(R.id.radioButtonCase1);
//		radioButtonCase1.setTypeface(Util.getFont(this));
//		radioButtonCase1.setTextSize(25f);
//
//		radioButtonCase2 = (RadioButton) findViewById(R.id.radioButtonCase2);
//		radioButtonCase2.setTypeface(Util.getFont(this));
//		radioButtonCase2.setTextSize(25f);
//
//		buttonNext = (Button) findViewById(R.id.buttonNext);
//		buttonNext.setTypeface(Util.getBoldFont(this));
//		buttonNext.setTextSize(50f);
//
//		radioGroupTools = (RadioGroup) findViewById(R.id.radioTools);
//		radioGroupCase = (RadioGroup) findViewById(R.id.radioCase);
//
//		buttonNext.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		switch (view.getId()) {
		case R.id.buttonNext:
			if (event.getAction() == MotionEvent.ACTION_DOWN) {

				return true;
			}

			if (event.getAction() == MotionEvent.ACTION_MOVE) {

				return true;

			}
			if (event.getAction() == MotionEvent.ACTION_UP) {
				Intent intent = new Intent(SetcaseImplantActivity.this,SetcaseDetailActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
				return true;
			}
			break;

		default:
			break;
		}
		return false;
	}
}
