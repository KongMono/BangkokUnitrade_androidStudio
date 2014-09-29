package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.DoctorToolsEntry;

import android.util.Log;


public class DoctorToolsParser {
	private ArrayList<DoctorToolsEntry> arr;
	private JSONObject jObject;
	DoctorToolsEntry item;

	public DoctorToolsParser() {
		arr = new ArrayList<DoctorToolsEntry>();
	}
	
	public ArrayList<DoctorToolsEntry> getData(JSONObject jsonObject) {
		arr.clear();

		try {

			jObject = jsonObject;

			JSONObject responseObject = jObject.getJSONObject("content");
			JSONArray itemsjsonArray = responseObject.getJSONArray("item");

			for (int i = 0; i < itemsjsonArray.length(); i++) {
				item = new DoctorToolsEntry();
				jObject = itemsjsonArray.getJSONObject(i);
				item.setNo(jObject.getInt("no"));
				item.setTool(jObject.getString("tool"));
				arr.add(item);
			}

		} catch (JSONException e) {
			Log.e("DoctorToolsParser", "Error ", e);
		}
		return arr;
	}


}
