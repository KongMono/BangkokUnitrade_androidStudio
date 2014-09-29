package com.codefriday.bangkokunitrade.dataset;

public class OrderCase4Entry {
	private String implant_id;
	private String implant_name;
	private String description;
	

	public OrderCase4Entry() {

	}
	
	public OrderCase4Entry(String implant_id, String implant_name,
			String description) {
		super();
		this.implant_id = implant_id;
		this.implant_name = implant_name;
		this.description = description;
	}

	public String getImplant_id() {
		return implant_id;
	}


	public void setImplant_id(String implant_id) {
		this.implant_id = implant_id;
	}


	public String getImplant_name() {
		return implant_name;
	}


	public void setImplant_name(String implant_name) {
		this.implant_name = implant_name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
