package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.CatalogEntry;

import android.util.Log;

public class CatalogPaser {
	private ArrayList<CatalogEntry> arr;
	private JSONObject jObject;
	private String str = "";
	CatalogEntry item;

	public CatalogPaser() {
		arr = new ArrayList<CatalogEntry>();
	}


	 public ArrayList<CatalogEntry> getData(JSONObject jsonObject) {
		 arr.clear();
		
		 try {
		
			 jObject = jsonObject;
			
			 JSONObject responseObject = jObject.getJSONObject("content");
			 JSONArray itemsjsonArray= responseObject.getJSONArray("item");
			
			 for (int i = 0; i < itemsjsonArray.length(); i++) {
				 item = new CatalogEntry();
				 jObject = itemsjsonArray.getJSONObject(i);
				 item.setId(jObject.getInt("id"));
				 item.setName(jObject.getString("name"));
				 item.setUrl(jObject.getString("url"));
				 item.setIschecked(false);
				 arr.add(item);
			 }
			
		 } catch (JSONException e) {
			 Log.e("CatalogPaser", "Error ", e);
		 }
	 	return arr;
	 }

}
