package com.codefriday.bangkokunitrade.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.StatuscaseEntry;
import com.codefriday.bangkokunitrade.json.StatuscasePaser;
import com.codefriday.bangkokunitrade.adapters.StatuscaseAdapter;
import com.codefriday.bangkokunitrade.util.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

public class StatusCaseActivity extends Activity implements OnTouchListener,OnItemClickListener {
	private TextView textheader, textTime, textTo,textNote;
	public ImageView imageView;
	public File outputFileName;
	private EditText startTime, endTime;
	public String filePath;
	private Button btnCreate;
	private ListView list;
	private int hour;
	private int minute;
	static final int STARTTIME_DIALOG_ID = 1;
	static final int ENDTIME_DIALOG_ID = 2;
	AlertDialog helpDialog;
	StatuscaseAdapter adapter;
	StatuscasePaser checkcasePaser = new StatuscasePaser();
	ArrayList<StatuscaseEntry> arrayList = new ArrayList<StatuscaseEntry>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statuscase);

		init();

		/********* display current time on screen Start ********/

		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);

		/********* display current time on screen End ********/
		arrayList = checkcasePaser.getData(this);
		adapter = new StatuscaseAdapter(this, arrayList);
		list.setAdapter(adapter);
	}

	private void init() {

		textheader = (TextView) findViewById(R.id.textheader);
		textheader.setTypeface(Util.getBoldFont(this));
		textheader.setTextSize(30f);

		btnCreate = (Button) findViewById(R.id.buttonReport);
		btnCreate.setTypeface(Util.getBoldFont(this));
		btnCreate.setTextSize(30f);
		
		list = (ListView) findViewById(R.id.list);
		list.setOnItemClickListener(this);
		btnCreate.setOnTouchListener(this);

	}

	private TimePickerDialog.OnTimeSetListener StarttimePickerListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
			hour = hourOfDay;
			minute = minutes;

			String timeSet = "";
			if (hour > 12) {
				hour -= 12;
				timeSet = "PM";
			} else if (hour == 0) {
				hour += 12;
				timeSet = "AM";
			} else if (hour == 12)
				timeSet = "PM";
			else
				timeSet = "AM";

			String min = "";
			if (minute < 10)
				min = "0" + minute;
			else
				min = String.valueOf(minute);

			String aTime = new StringBuilder().append(hour).append(':')
					.append(min).append(" ").append(timeSet).toString();

			startTime.setText(aTime);

		}

	};
	
	private TimePickerDialog.OnTimeSetListener EndtimePickerListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
			// TODO Auto-generated method stub
			hour = hourOfDay;
			minute = minutes;

			String timeSet = "";
			if (hour > 12) {
				hour -= 12;
				timeSet = "PM";
			} else if (hour == 0) {
				hour += 12;
				timeSet = "AM";
			} else if (hour == 12)
				timeSet = "PM";
			else
				timeSet = "AM";

			String min = "";
			if (minute < 10)
				min = "0" + minute;
			else
				min = String.valueOf(minute);

			String aTime = new StringBuilder().append(hour).append(':')
					.append(min).append(" ").append(timeSet).toString();

			endTime.setText(aTime);

		}

	};

	// Used to convert 24hr format to 12hr format with AM/PM values
    private void updateTime(int hours, int mins) {
         
        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";
         
        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);
 
        // Append in a StringBuilder
         String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();
 
          endTime.setText(aTime);
          startTime.setText(aTime);
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case STARTTIME_DIALOG_ID:
			return new TimePickerDialog(this, StarttimePickerListener, hour, minute,false);

		case ENDTIME_DIALOG_ID:
			return new TimePickerDialog(this, EndtimePickerListener, hour, minute,false);

		}
		return null;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (v.getId()) {
		case R.id.buttonReport:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				showPopUp();
				return true;
			}
			break;

		case R.id.btnsend:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				helpDialog.dismiss();
				return true;
			}
			break;
		case R.id.btnendCase:
			helpDialog.dismiss();
			break;
		case R.id.startTime:
			showDialog(STARTTIME_DIALOG_ID);
			break;
		case R.id.endTime:
			showDialog(ENDTIME_DIALOG_ID);
			break;

		default:
			break;
		}
		return false;
	}

	private void showPopUp() {

		final Builder helpBuilder = new Builder(this);
		final LayoutInflater inflater = getLayoutInflater();
		final View view = inflater.inflate(R.layout.layout_create_statuscase,null);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		TextView header = (TextView) view.findViewById(R.id.textheader);
		header.setTypeface(Util.getBoldFont(this));
		header.setTextSize(30f);
		
		textTime = (TextView) view.findViewById(R.id.texttime);
		textTime.setTypeface(Util.getBoldFont(this));
		textTime.setTextSize(30f);

		textTo = (TextView) view.findViewById(R.id.textto);
		textTo.setTypeface(Util.getBoldFont(this));
		textTo.setTextSize(30f);
		
		textNote = (TextView) view.findViewById(R.id.textNote);
		textNote.setTypeface(Util.getBoldFont(this));
		textNote.setTextSize(30f);
		
		startTime = (EditText) view.findViewById(R.id.startTime);
		startTime.setTypeface(Util.getBoldFont(this));
		startTime.setTextSize(30f);
		startTime.setOnTouchListener(this);
		
		endTime = (EditText) view.findViewById(R.id.endTime);
		endTime.setTypeface(Util.getBoldFont(this));
		endTime.setTextSize(30f);
		endTime.setOnTouchListener(this);
		
		btnCreate = (Button) view.findViewById(R.id.btnsend);
		btnCreate.setOnTouchListener(this);
		
		helpBuilder.setView(view);
		updateTime(hour, minute);
		
		helpDialog = helpBuilder.create();
		helpDialog.show();
	}
	
	private void showPopUpList() {

		final Builder helpBuilder = new Builder(this);
		final LayoutInflater inflater = getLayoutInflater();
		final View view = inflater.inflate(R.layout.layout_list_create_statuscase,null);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		TextView header = (TextView) view.findViewById(R.id.textheader);
		header.setTypeface(Util.getBoldFont(this));
		header.setTextSize(30f);
		
		Button btnEndcase = (Button) view.findViewById(R.id.btnendCase);
		btnEndcase.setTypeface(Util.getBoldFont(this));
		btnEndcase.setTextSize(30f);
		btnEndcase.setOnTouchListener(this);
		
		
		ListView list =(ListView) view.findViewById(R.id.listDetail);
		list.setOnItemClickListener(this);
		
		
		helpBuilder.setView(view);
		
		helpDialog = helpBuilder.create();
		helpDialog.show();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		showPopUpList();
	}

}
