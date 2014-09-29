package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.QuoateEntry;

import android.util.Log;

public class QuoatePaser {
	private ArrayList<QuoateEntry> arr;
	private JSONObject jObject;
	QuoateEntry item;

	public QuoatePaser() {
		arr = new ArrayList<QuoateEntry>();
	}

	 public ArrayList<QuoateEntry> getData(JSONObject jsonObject) {
		 arr.clear();
		
		 try {
			
			 jObject = jsonObject;
			
			 JSONObject responseObject = jObject.getJSONObject("content");
			 String sale = responseObject.getString("sale");
			 
			 JSONArray itemsjsonArray = responseObject.getJSONArray("item");

			 for (int i = 0; i < itemsjsonArray.length(); i++) {
				 item = new QuoateEntry();
				 jObject = itemsjsonArray.getJSONObject(i);
				 item.setSale(sale);
				 item.setId(jObject.getInt("id"));
				 item.setImplant(jObject.getString("implant"));
				 item.setQty("0");
				 item.setPrice(jObject.getString("price"));
				 item.setDelete(false);
				 arr.add(item);
			 }
			
		 } catch (JSONException e) {
			 Log.e("NewsPaser", "Error ", e);
		 }
		 return arr;
	 }

}
