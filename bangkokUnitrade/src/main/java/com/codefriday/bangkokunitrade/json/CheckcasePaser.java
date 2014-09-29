package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.CheckcaseEntry;

import android.util.Log;

public class CheckcasePaser {
	private ArrayList<CheckcaseEntry> arr;
	private JSONObject jObject;
	private String str = "";
	CheckcaseEntry item;

	public CheckcasePaser() {
		arr = new ArrayList<CheckcaseEntry>();
	}

	public ArrayList<CheckcaseEntry> getData(JSONObject jsonObject) {
		arr.clear();

		try {

			jObject = jsonObject;

			JSONObject responseObject = jObject.getJSONObject("content");
			JSONArray itemsjsonArray = responseObject.getJSONArray("item");

			for (int i = 0; i < itemsjsonArray.length(); i++) {
				item = new CheckcaseEntry();
				jObject = itemsjsonArray.getJSONObject(i);
				item.setOrder_id(jObject.getInt("order_id"));
				item.setOrder_no(jObject.getString("order_no"));
				item.setHospital(jObject.getString("hospital"));
				item.setStatus(jObject.getString("status"));
				arr.add(item);
			}

		} catch (JSONException e) {
			Log.e("CheckcasePaser", "Error ", e);
		}
		return arr;
	}

}
