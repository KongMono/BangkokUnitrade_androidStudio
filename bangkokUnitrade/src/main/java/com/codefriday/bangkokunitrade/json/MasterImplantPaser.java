package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.MasterimplantEntry;

import android.util.Log;

public class MasterImplantPaser {
	private ArrayList<MasterimplantEntry> arr;
	private JSONObject jObject;
	MasterimplantEntry item;

	public MasterImplantPaser() {
		arr = new ArrayList<MasterimplantEntry>();
	}

	public ArrayList<MasterimplantEntry> getData(JSONObject jsonObject) {
		arr.clear();

		try {

			jObject = jsonObject;

			JSONObject responseObject = jObject.getJSONObject("content");
			JSONArray itemsjsonArray = responseObject.getJSONArray("item");

			for (int i = 0; i < itemsjsonArray.length(); i++) {
				item = new MasterimplantEntry();
				jObject = itemsjsonArray.getJSONObject(i);
				item.setId(jObject.getInt("id"));
				item.setName(jObject.getString("name"));
				item.setDescription(jObject.getString("description"));
				item.setItem(jObject.getString("item"));
				item.setQty(jObject.getString("qty"));
				arr.add(item);
			}

		} catch (JSONException e) {
			Log.e("MasterImplantPaser", "Error ", e);
		}
		return arr;
	}

}
