package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.ShutCaseListEntry;

import android.util.Log;

public class ShutcasePaser {
	private ArrayList<ShutCaseListEntry> arr;
	private JSONObject jObject;
	ShutCaseListEntry item;

	public ShutcasePaser() {
		arr = new ArrayList<ShutCaseListEntry>();
	}

	public ArrayList<ShutCaseListEntry> getData(JSONObject jsonObject) {
		arr.clear();

		try {

			jObject = jsonObject;

			JSONObject responseObject = jObject.getJSONObject("content");
			JSONArray itemsjsonArray = responseObject.getJSONArray("item");

			for (int i = 0; i < itemsjsonArray.length(); i++) {
				item = new ShutCaseListEntry();
				jObject = itemsjsonArray.getJSONObject(i);
				item.setCase_id(jObject.getInt("id"));
				item.setSurgery_date(jObject.getString("surgery_date"));
				item.setSurgery_time(jObject.getString("surgery_time"));
				item.setDoctor(jObject.getString("doctor"));
				item.setHn(jObject.getString("hn"));
				item.setPatient(jObject.getString("patient"));
				arr.add(item);
			}

		} catch (JSONException e) {
			Log.e("ShutcasePaser", "Error ", e);
		}
		return arr;
	}

}
