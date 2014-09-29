package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.TramuaListEntry;

import android.util.Log;

public class TramuaListPaser {
	private ArrayList<TramuaListEntry> arr;
	private JSONObject jObject;
	TramuaListEntry item;

	public TramuaListPaser() {
		arr = new ArrayList<TramuaListEntry>();
	}

	public ArrayList<TramuaListEntry> getData(JSONObject jsonObject) {
		arr.clear();

		try {

			jObject = jsonObject;

			JSONObject responseObject = jObject.getJSONObject("content");
			JSONArray itemsjsonArray = responseObject.getJSONArray("item");

			for (int i = 0; i < itemsjsonArray.length(); i++) {
				item = new TramuaListEntry();
				jObject = itemsjsonArray.getJSONObject(i);
				item.setId(jObject.getInt("id"));
				item.setName(jObject.getString("name"));
				item.setImage(jObject.getString("image"));
				arr.add(item);
			}

		} catch (JSONException e) {
			Log.e("TramuaListPaser", "Error ", e);
		}
		return arr;
	}

}
