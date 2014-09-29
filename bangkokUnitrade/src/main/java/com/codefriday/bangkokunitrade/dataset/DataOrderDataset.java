package com.codefriday.bangkokunitrade.dataset;

import java.util.ArrayList;
import java.util.HashMap;


public class DataOrderDataset {
	private String order_type;
	private HashMap<String,OrderCase1Entry> orderCase1Entries;
	private ArrayList<OrderCase2Entry> orderCase2Entries;
	private ArrayList<OrderCase3Entry> orderCase3Entries;
	private ArrayList<OrderCase4Entry> orderCase4Entries;
	
	private static DataOrderDataset instance;

	private DataOrderDataset() {
		// Private constructor so nobody can create an instance of your class.
	}

	public static DataOrderDataset getInstance() {
		if (instance == null) {
			instance = new DataOrderDataset();
		}
		return instance;
	}
	
	public DataOrderDataset(String order_type,
			HashMap<String, OrderCase1Entry> orderCase1Entries,
			ArrayList<OrderCase2Entry> orderCase2Entries,
			ArrayList<OrderCase3Entry> orderCase3Entries,
			ArrayList<OrderCase4Entry> orderCase4Entries) {
		super();
		this.order_type = order_type;
		this.orderCase1Entries = orderCase1Entries;
		this.orderCase2Entries = orderCase2Entries;
		this.orderCase3Entries = orderCase3Entries;
		this.orderCase4Entries = orderCase4Entries;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public HashMap<String, OrderCase1Entry> getOrderCase1Entries() {
		return orderCase1Entries;
	}

	public void setOrderCase1Entries(
			HashMap<String, OrderCase1Entry> orderCase1Entries) {
		this.orderCase1Entries = orderCase1Entries;
	}

	public ArrayList<OrderCase2Entry> getOrderCase2Entries() {
		return orderCase2Entries;
	}

	public void setOrderCase2Entries(ArrayList<OrderCase2Entry> orderCase2Entries) {
		this.orderCase2Entries = orderCase2Entries;
	}

	public ArrayList<OrderCase3Entry> getOrderCase3Entries() {
		return orderCase3Entries;
	}

	public void setOrderCase3Entries(ArrayList<OrderCase3Entry> orderCase3Entries) {
		this.orderCase3Entries = orderCase3Entries;
	}

	public ArrayList<OrderCase4Entry> getOrderCase4Entries() {
		return orderCase4Entries;
	}

	public void setOrderCase4Entries(ArrayList<OrderCase4Entry> orderCase4Entries) {
		this.orderCase4Entries = orderCase4Entries;
	}

	public static void setInstance(DataOrderDataset instance) {
		DataOrderDataset.instance = instance;
	}
	
	

}
