package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.PopupSetEntry;

import android.util.Log;

public class PopBiomaterialsPaser {
	private ArrayList<PopupSetEntry> arr;
	private JSONObject jObject;
	PopupSetEntry item;
	public PopBiomaterialsPaser() {
		arr = new ArrayList<PopupSetEntry>();
	}

	public ArrayList<PopupSetEntry> getData(JSONObject jsonObject) {
		arr.clear();

		try {

			jObject = jsonObject;

			JSONObject responseObject = jObject.getJSONObject("content");
			JSONArray itemsjsonArray = responseObject.getJSONArray("item");

			for (int i = 0; i < itemsjsonArray.length(); i++) {
				item = new PopupSetEntry();
				jObject = itemsjsonArray.getJSONObject(i);
				
				item.setId(jObject.getInt("id"));
				item.setName(jObject.getString("name"));
				item.setQty("0");
				
				arr.add(item);
			}

		} catch (JSONException e) {
			Log.e("BiomaterialsPaser", "Error ", e);
		}
		return arr;
	}

}
