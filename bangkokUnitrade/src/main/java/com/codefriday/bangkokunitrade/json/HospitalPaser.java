package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.HospitalEntry;

import android.util.Log;

public class HospitalPaser {
	private ArrayList<HospitalEntry> arr;
	private JSONObject jObject;
	HospitalEntry item;

	public HospitalPaser() {
		arr = new ArrayList<HospitalEntry>();
	}

	public ArrayList<HospitalEntry> getData(JSONObject jsonObject) {
		arr.clear();

		try {

			jObject = jsonObject;

			JSONObject responseObject = jObject.getJSONObject("content");
			JSONArray itemsjsonArray = responseObject.getJSONArray("item");

			for (int i = 0; i < itemsjsonArray.length(); i++) {
				item = new HospitalEntry();
				jObject = itemsjsonArray.getJSONObject(i);
				item.setId(jObject.getInt("hospital_id"));
				item.setName(jObject.getString("name"));
				arr.add(item);
			}

		} catch (JSONException e) {
			Log.e("HospitalPaser", "Error ", e);
		}
		return arr;
	}

}
