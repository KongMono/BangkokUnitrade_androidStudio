package com.codefriday.bangkokunitrade.dataset;

public class StatuscaseDetailListEntry {
	private String biomaterial;
	private String implant;
	private String item;
	private String description;
	private String qty;
	private String delivery_qty;
	
	public StatuscaseDetailListEntry(){
		
	}
	
	public StatuscaseDetailListEntry(String biomaterial, String implant,
			String item, String description, String qty, String delivery_qty) {
		super();
		this.biomaterial = biomaterial;
		this.implant = implant;
		this.item = item;
		this.description = description;
		this.qty = qty;
		this.delivery_qty = delivery_qty;
	}

	public String getBiomaterial() {
		return biomaterial;
	}

	public void setBiomaterial(String biomaterial) {
		this.biomaterial = biomaterial;
	}

	public String getImplant() {
		return implant;
	}

	public void setImplant(String implant) {
		this.implant = implant;
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

	public String getDelivery_qty() {
		return delivery_qty;
	}

	public void setDelivery_qty(String delivery_qty) {
		this.delivery_qty = delivery_qty;
	}
	
	
	

}
