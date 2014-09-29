package com.codefriday.bangkokunitrade.dataset;

public class OrderCase1Entry {
	private String trauma_id;
	private String trauma_name;
	private String masterimplant_id;
	private String masterimplant_name;
	private String indication_id;
	private String indication_name;

	public OrderCase1Entry() {

	}
	
	public OrderCase1Entry(String trauma_id, String trauma_name,
			String masterimplant_id, String masterimplant_name,
			String indication_id, String indication_name) {
		super();
		this.trauma_id = trauma_id;
		this.trauma_name = trauma_name;
		this.masterimplant_id = masterimplant_id;
		this.masterimplant_name = masterimplant_name;
		this.indication_id = indication_id;
		this.indication_name = indication_name;
	}



	public String getTrauma_id() {
		return trauma_id;
	}

	public void setTrauma_id(String trauma_id) {
		this.trauma_id = trauma_id;
	}

	public String getTrauma_name() {
		return trauma_name;
	}

	public void setTrauma_name(String trauma_name) {
		this.trauma_name = trauma_name;
	}

	public String getMasterimplant_id() {
		return masterimplant_id;
	}

	public void setMasterimplant_id(String masterimplant_id) {
		this.masterimplant_id = masterimplant_id;
	}

	public String getMasterimplant_name() {
		return masterimplant_name;
	}

	public void setMasterimplant_name(String masterimplant_name) {
		this.masterimplant_name = masterimplant_name;
	}

	public String getIndication_id() {
		return indication_id;
	}

	public void setIndication_id(String indication_id) {
		this.indication_id = indication_id;
	}

	public String getIndication_name() {
		return indication_name;
	}

	public void setIndication_name(String indication_name) {
		this.indication_name = indication_name;
	}
	
	


}
