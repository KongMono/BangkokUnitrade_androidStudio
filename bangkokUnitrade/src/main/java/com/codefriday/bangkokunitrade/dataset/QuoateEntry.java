package com.codefriday.bangkokunitrade.dataset;

public class QuoateEntry {
	private int id;
	private String sale;
	private String implant;
	private String qty;
	private String price;
	private Boolean delete;
	
	public QuoateEntry(){
		
	}
	
	
	public QuoateEntry(int id, String sale, String implant, String qty, String price) {
		super();
		this.id = id;
		this.sale = sale;
		this.implant = implant;
		this.qty = qty;
		this.price = price;
	}
	
	public QuoateEntry(int id, String sale, String implant, String qty,
			String price, Boolean delete) {
		super();
		this.id = id;
		this.sale = sale;
		this.implant = implant;
		this.qty = qty;
		this.price = price;
		this.delete = delete;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public String getImplant() {
		return implant;
	}

	public void setImplant(String implant) {
		this.implant = implant;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	
	

}
