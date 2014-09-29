package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.HospitalEntry;

import android.util.Log;

public class DoctorPaser {
	private ArrayList<HospitalEntry> arr;
	private JSONObject jObject;
	HospitalEntry item;

	public DoctorPaser() {
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
				item.setId(jObject.getInt("id"));
				item.setName(jObject.getString("name"));
				arr.add(item);
			}
			item = new HospitalEntry();
			item.setId(0);
			item.setName("ระบุแพทย์เพิ่มเติม");
			
			arr.add(item);
		} catch (JSONException e) {
			Log.e("DoctorPaser", "Error ", e);
		}
		return arr;
	}

}
