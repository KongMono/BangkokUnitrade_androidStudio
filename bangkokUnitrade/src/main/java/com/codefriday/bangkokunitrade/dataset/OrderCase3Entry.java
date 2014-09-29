package com.codefriday.bangkokunitrade.dataset;

public class OrderCase3Entry {
	
	private String special_tool_id;
	private String special_tool_name;
	private String quantity;

	public OrderCase3Entry() {

	}
	

	public OrderCase3Entry(String special_tool_id, String special_tool_name,
			String quantity) {
		super();
		this.special_tool_id = special_tool_id;
		this.special_tool_name = special_tool_name;
		this.quantity = quantity;
	}


	public String getSpecial_tool_id() {
		return special_tool_id;
	}

	public void setSpecial_tool_id(String special_tool_id) {
		this.special_tool_id = special_tool_id;
	}

	public String getSpecial_tool_name() {
		return special_tool_name;
	}

	public void setSpecial_tool_name(String special_tool_name) {
		this.special_tool_name = special_tool_name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	

	
}
