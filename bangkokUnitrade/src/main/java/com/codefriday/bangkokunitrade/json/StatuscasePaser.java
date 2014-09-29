package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONObject;

import com.codefriday.bangkokunitrade.R;
import com.codefriday.bangkokunitrade.dataset.StatuscaseEntry;

import android.content.Context;
import android.util.Log;

public class StatuscasePaser {
	private ArrayList<StatuscaseEntry> arr;
	private JSONObject jObject;
	private String str = "";
	StatuscaseEntry item;

	public StatuscasePaser() {
		arr = new ArrayList<StatuscaseEntry>();
	}

	public ArrayList<StatuscaseEntry> getData(Context context) {
		arr.clear();

		try {
			item = new StatuscaseEntry();
			item.setId(1);
			item.setTitle(context.getResources().getString(R.string.listStatusCase));
			item.setDetail(".......");
			arr.add(item);
			item = new StatuscaseEntry();
			item.setId(2);
			item.setTitle(context.getResources().getString(R.string.listStatusCase));
			item.setDetail(".......");
			arr.add(item);
			item = new StatuscaseEntry();
			item.setId(3);
			item.setTitle(context.getResources().getString(R.string.listStatusCase));
			item.setDetail(".......");
			arr.add(item);

		} catch (Exception e) {
			Log.e("StatuscasePaser", "Error ", e);
		}
		return arr;
	}

	// public ArrayList<StatuscaseEntry> getData(JSONObject jsonObject) {
	// arr.clear();
	//
	// try {
	//
	// jObject = jsonObject;
	//
	// JSONObject responseObject = jObject.getJSONObject("response");
	// JSONObject dataObject = responseObject.getJSONObject("data");
	// JSONObject itemObject = dataObject.getJSONObject("items");
	// JSONArray itemsjsonArray= itemObject.getJSONArray("item");
	//
	// for (int i = 0; i < itemsjsonArray.length(); i++) {
	// item = new StatuscaseEntry();
	// jObject = itemsjsonArray.getJSONObject(i);
	// item.setId(jObject.getInt("id"));
	// arr.add(item);
	// }
	//
	//
	// } catch (JSONException e) {
	// Log.e("NewsPaser", "Error ", e);
	// }
	// return arr;
	// }

}
