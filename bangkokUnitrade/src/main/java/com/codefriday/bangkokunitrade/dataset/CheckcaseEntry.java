package com.codefriday.bangkokunitrade.dataset;

public class CheckcaseEntry {
	private int order_id;
	private String order_no;
	private String Hospital;
	private String status;
	
	public CheckcaseEntry(){
		
	}
	
	public CheckcaseEntry(int order_id, String order_no, String hospital,
			String status) {
		super();
		this.order_id = order_id;
		this.order_no = order_no;
		Hospital = hospital;
		this.status = status;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getHospital() {
		return Hospital;
	}

	public void setHospital(String hospital) {
		Hospital = hospital;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
