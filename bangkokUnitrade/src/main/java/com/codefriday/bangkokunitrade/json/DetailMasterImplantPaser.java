package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.MasterDetailimplantEntry;

import android.util.Log;

public class DetailMasterImplantPaser {
	private ArrayList<MasterDetailimplantEntry> arr;
	private JSONObject jObject;
	MasterDetailimplantEntry item;

	public DetailMasterImplantPaser() {
		arr = new ArrayList<MasterDetailimplantEntry>();
	}

	public ArrayList<MasterDetailimplantEntry> getData(JSONObject jsonObject) {
		arr.clear();

		try {

			jObject = jsonObject;

			JSONObject responseObject = jObject.getJSONObject("content");
			JSONArray itemsjsonArray = responseObject.getJSONArray("item");

			for (int i = 0; i < itemsjsonArray.length(); i++) {
				item = new MasterDetailimplantEntry();
				jObject = itemsjsonArray.getJSONObject(i);
				item.setId(jObject.getInt("id"));
				item.setName(jObject.getString("name"));
				item.setItem(jObject.getString("item"));
				item.setDescription(jObject.getString("description"));
				item.setQty("0");
				arr.add(item);
			}

		} catch (JSONException e) {
			Log.e("DetailMasterImplantPaser", "Error ", e);
		}
		return arr;
	}

}
