package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.OrdernIdicatorEntry;

import android.util.Log;

public class TramuaListIndicatorPaser {
	private ArrayList<OrdernIdicatorEntry> arr;
	private JSONObject jObject;
	OrdernIdicatorEntry item;

	public TramuaListIndicatorPaser() {
		arr = new ArrayList<OrdernIdicatorEntry>();
	}

	public ArrayList<OrdernIdicatorEntry> getData(JSONObject jsonObject) {
		arr.clear();

		try {

			jObject = jsonObject;

			JSONObject responseObject = jObject.getJSONObject("content");
			JSONArray itemsjsonArray = responseObject.getJSONArray("item");

			for (int i = 0; i < itemsjsonArray.length(); i++) {
				item = new OrdernIdicatorEntry();
				jObject = itemsjsonArray.getJSONObject(i);
				item.setId(jObject.getInt("id"));
				item.setName(jObject.getString("name"));
				arr.add(item);
			}

		} catch (JSONException e) {
			Log.e("TramuaListPaser", "Error ", e);
		}
		return arr;
	}

}
