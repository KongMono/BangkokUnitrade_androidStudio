package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.ShutCaseDetailEntry;
import com.codefriday.bangkokunitrade.dataset.implantEntry;

import android.util.Log;

public class ShutcaseDetailPaser {
	private ArrayList<ShutCaseDetailEntry> arr = new ArrayList<ShutCaseDetailEntry>();
	private ArrayList<implantEntry> implantEntries = new ArrayList<implantEntry>();
	private JSONObject jObject;
	ShutCaseDetailEntry item;
	implantEntry implantEntry;
	public ShutcaseDetailPaser() {
		
	}

	public ArrayList<ShutCaseDetailEntry> getData(JSONObject jsonObject) {
		arr.clear();

		try {

			jObject = jsonObject;
			
			JSONObject responseObject = jObject.getJSONObject("content");
			JSONArray itemsjsonArray = responseObject.getJSONArray("item");

			for (int i = 0; i < itemsjsonArray.length(); i++) {
				item = new ShutCaseDetailEntry();
				jObject = itemsjsonArray.getJSONObject(i);
				item.setSurgery_date(jObject.getString("surgery_date"));
				item.setSurgery_time(jObject.getString("surgery_time"));
				item.setHn(jObject.getString("hn"));
				item.setPatient(jObject.getString("patient"));
				item.setDoctor(jObject.getString("doctor"));
				
				JSONArray implantArray = jObject.getJSONArray("implant");
				implantEntries.clear();
				for (int x = 0; x < implantArray.length(); x++) {
					jObject = implantArray.getJSONObject(x);
					implantEntry = new implantEntry();
					implantEntry.setNum(String.valueOf(x+1).concat("."));
					implantEntry.setMaster_implants_id(jObject.getString("id"));
					implantEntry.setSet_implant_name(jObject.getString("set_implant_name"));
					implantEntry.setDescription(jObject.getString("description"));
					implantEntry.setItem(jObject.getString("item"));
					implantEntry.setQty(jObject.getString("qty"));
					implantEntry.setUse("0");
					implantEntries.add(implantEntry);
				}
				item.setImplantList(implantEntries);
				arr.add(item);
			}

		} catch (JSONException e) {
			Log.e("ShutcaseDetailPaser", "Error ", e);
		}
		return arr;
	}

}
