package com.codefriday.bangkokunitrade.dataset;

public class MasterimplantEntry {
	private int id;
	private String name;
	private String item;
	private String description;
	private String qty;

	public MasterimplantEntry() {

	}
	
	public MasterimplantEntry(int id, String name, String item,
			String description, String qty) {
		super();
		this.id = id;
		this.name = name;
		this.item = item;
		this.description = description;
		this.qty = qty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}
	
	
	
}
