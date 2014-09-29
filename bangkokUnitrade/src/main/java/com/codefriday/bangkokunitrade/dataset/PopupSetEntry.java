package com.codefriday.bangkokunitrade.dataset;

public class PopupSetEntry {
	private int id;
	private String name;
	private String description;
	private String qty;
	private boolean selected = false;

	public PopupSetEntry() {

	}
	
	public PopupSetEntry(int id, String name, String description, String qty,
			boolean selected) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.qty = qty;
		this.selected = selected;
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

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
	
}
