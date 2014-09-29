package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.ToolsSetimplantEntry;

import android.util.Log;

public class ToolssetPaser {
	private ArrayList<ToolsSetimplantEntry> arr;
	private JSONObject jObject;
	ToolsSetimplantEntry item;

	public ToolssetPaser() {
		arr = new ArrayList<ToolsSetimplantEntry>();
	}

	public ArrayList<ToolsSetimplantEntry> getData(JSONObject jsonObject) {
		arr.clear();

		try {

			jObject = jsonObject;

			JSONObject responseObject = jObject.getJSONObject("content");
			JSONArray itemsjsonArray = responseObject.getJSONArray("item");

			for (int i = 0; i < itemsjsonArray.length(); i++) {
				item = new ToolsSetimplantEntry();
				jObject = itemsjsonArray.getJSONObject(i);
				item.setNo(jObject.getInt("no"));
				item.setMessage(jObject.getString("message"));
				item.setName(jObject.getString("name"));
				arr.add(item);
			}

		} catch (JSONException e) {
			Log.e("ToolssetPaser", "Error ", e);
		}
		return arr;
	}

}
