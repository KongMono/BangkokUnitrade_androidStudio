package com.codefriday.bangkokunitrade.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codefriday.bangkokunitrade.dataset.BiomaterialEntry;
import com.codefriday.bangkokunitrade.dataset.StatuscaseDetailEntry;
import com.codefriday.bangkokunitrade.dataset.StatuscaseDetailListEntry;
import com.codefriday.bangkokunitrade.dataset.InstrumentEntry;

import android.util.Log;

public class CheckcasedetailPaser {
	private ArrayList<StatuscaseDetailEntry> arr;
	private ArrayList<StatuscaseDetailListEntry> detailListEntries = new ArrayList<StatuscaseDetailListEntry>();
	private ArrayList<InstrumentEntry> instrumentEntries = new ArrayList<InstrumentEntry>();
	private ArrayList<BiomaterialEntry> biomaterialEntries = new ArrayList<BiomaterialEntry>();
	private JSONObject jObject;
	StatuscaseDetailEntry item;
	String order_type;

	public CheckcasedetailPaser() {
		arr = new ArrayList<StatuscaseDetailEntry>();
	}

	public ArrayList<StatuscaseDetailEntry> getData(JSONObject jsonObject) {
		arr.clear();

		try {

			jObject = jsonObject;

			JSONObject responseObject = jObject.getJSONObject("content");
			JSONObject itemsjsonObject = responseObject.getJSONObject("item");
			
			item = new StatuscaseDetailEntry();
			jObject = itemsjsonObject;
			item.setOrder_type(jObject.getString("order_type"));
			item.setStatus(jObject.getString("status"));
			order_type = jObject.getString("order_type");
			item.setHospital(jObject.getString("hospital"));
			item.setDoctor(jObject.getString("doctor"));
			item.setSergery_date(jObject.getString("sergery_date"));
			item.setSergery_time(jObject.getString("sergery_time"));
			item.setPatient_name(jObject.getString("patient_name"));
			item.setHn(jObject.getString("hn"));
			item.setAddons(jObject.getString("addons"));
			item.setInstrument_doctor(jObject.getString("instrument_doctor"));
			item.setTransport(jObject.getString("transport"));
			item.setSale(jObject.getString("sale"));
			
			JSONArray orderJsonArray = itemsjsonObject.getJSONArray("order");
			
			for (int i = 0; i < orderJsonArray.length(); i++) {
				jObject = orderJsonArray.getJSONObject(i);
				StatuscaseDetailListEntry detailListEntry = new StatuscaseDetailListEntry();
				
				if (order_type.equalsIgnoreCase("3")) {
					detailListEntry.setBiomaterial(jObject.getString("biomaterial"));
					detailListEntry.setQty(jObject.getString("qty"));
					detailListEntry.setDelivery_qty(jObject.getString("delivery_qty"));
				}else{
					detailListEntry.setImplant(jObject.getString("implant"));
					detailListEntry.setItem(jObject.getString("item"));
					detailListEntry.setDescription(jObject.getString("description"));
					detailListEntry.setQty(jObject.getString("qty"));
					detailListEntry.setDelivery_qty(jObject.getString("delivery_qty"));
				}
				
				
				
				detailListEntries.add(detailListEntry);
			}
			
			item.setOrder(detailListEntries);
			
			JSONArray instrumentJsonArray = itemsjsonObject.getJSONArray("instrument");
			
			for (int i = 0; i < instrumentJsonArray.length(); i++) {
				jObject = instrumentJsonArray.getJSONObject(i);
				InstrumentEntry instrumentEntry = new InstrumentEntry();
				instrumentEntry.setNo(jObject.getString("no"));
				instrumentEntry.setName(jObject.getString("instrument"));
				
				instrumentEntries.add(instrumentEntry);
			}
			item.setInstrument(instrumentEntries);
			
			
			JSONArray biomaterialJsonArray = itemsjsonObject.getJSONArray("biomaterial");
			
			for (int i = 0; i < biomaterialJsonArray.length(); i++) {
				jObject = biomaterialJsonArray.getJSONObject(i);
				BiomaterialEntry biomaterialEntry = new BiomaterialEntry();
				biomaterialEntry.setNo(jObject.getString("no"));
				biomaterialEntry.setBiomaterial(jObject.getString("biomaterial"));
				biomaterialEntry.setAmount(jObject.getString("amount"));
				
				biomaterialEntries.add(biomaterialEntry);
			}
			item.setBiomaterial(biomaterialEntries);
			
			arr.add(item);

		} catch (JSONException e) {
			Log.e("CheckcasedetailPaser", "Error ", e);
		}
		return arr;
	}

}
