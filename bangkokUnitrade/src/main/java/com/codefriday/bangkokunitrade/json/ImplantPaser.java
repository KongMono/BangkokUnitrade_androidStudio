package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.implantEntry;

import android.util.Log;

public class ImplantPaser {
	private ArrayList<implantEntry> arr;
	private JSONObject jObject;
	private String str = "";
	implantEntry item;

	public ImplantPaser() {
		arr = new ArrayList<implantEntry>();
	}

	public ArrayList<implantEntry> getData() {
		arr.clear();

		try {
			item = new implantEntry();
			item.setId(1);
			item.setPlantset("set1");
			arr.add(item);
			item = new implantEntry();
			item.setId(2);
			item.setPlantset("set2");
			arr.add(item);
			item = new implantEntry();
			item.setId(3);
			item.setPlantset("set3");
			arr.add(item);

		} catch (Exception e) {
			Log.e("NewsPaser", "Error ", e);
		}
		return arr;
	}

	// public ArrayList<implantEntry> getData(JSONObject jsonObject) {
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
	// item = new implantEntry();
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
