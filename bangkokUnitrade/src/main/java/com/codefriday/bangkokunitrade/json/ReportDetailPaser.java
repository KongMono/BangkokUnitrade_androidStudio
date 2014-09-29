package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.ReportDetailEntry;

import android.util.Log;

public class ReportDetailPaser {
	private ArrayList<ReportDetailEntry> arr;
	private JSONObject jObject;
	ReportDetailEntry item;

	public ReportDetailPaser() {
		arr = new ArrayList<ReportDetailEntry>();
	}

	public ArrayList<ReportDetailEntry> getData(JSONObject jsonObject) {
		arr.clear();

		try {

			jObject = jsonObject;

			JSONObject responseObject = jObject.getJSONObject("content");
			JSONArray itemsjsonArray = responseObject.getJSONArray("item");

			for (int i = 0; i < itemsjsonArray.length(); i++) {
				item = new ReportDetailEntry();
				jObject = itemsjsonArray.getJSONObject(i);
				item.setId(jObject.getInt("id"));
				item.setTopic(jObject.getString("topic"));
				item.setSubject(jObject.getString("subject"));
				item.setDetail(jObject.getString("detail"));
				item.setImage(jObject.getString("image"));
				item.setDate(jObject.getString("date"));
				arr.add(item);
			}
			
			arr.add(item);
		} catch (JSONException e) {
			Log.e("ReportDetailPaser", "Error ", e);
		}
		return arr;
	}

}
