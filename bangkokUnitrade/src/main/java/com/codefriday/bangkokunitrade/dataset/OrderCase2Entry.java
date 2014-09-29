package com.codefriday.bangkokunitrade.dataset;

public class OrderCase2Entry {
	private String set_implant_id;
	private String trauma_id;
	private String trauma_name;
	private String tramuaset_id;
	private String traumaset_name;
	private String quantity;

	public OrderCase2Entry() {

	}

	public OrderCase2Entry(String set_implant_id, String trauma_id,
			String trauma_name, String tramuaset_id, String traumaset_name,
			String quantity) {
		super();
		this.set_implant_id = set_implant_id;
		this.trauma_id = trauma_id;
		this.trauma_name = trauma_name;
		this.tramuaset_id = tramuaset_id;
		this.traumaset_name = traumaset_name;
		this.quantity = quantity;
	}

	public String getSet_implant_id() {
		return set_implant_id;
	}

	public void setSet_implant_id(String set_implant_id) {
		this.set_implant_id = set_implant_id;
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

	public String getTramuaset_id() {
		return tramuaset_id;
	}

	public void setTramuaset_id(String tramuaset_id) {
		this.tramuaset_id = tramuaset_id;
	}

	public String getTraumaset_name() {
		return traumaset_name;
	}

	public void setTraumaset_name(String traumaset_name) {
		this.traumaset_name = traumaset_name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	

}
