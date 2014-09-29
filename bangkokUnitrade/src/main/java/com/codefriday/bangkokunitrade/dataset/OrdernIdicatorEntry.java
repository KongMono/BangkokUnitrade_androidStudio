package com.codefriday.bangkokunitrade.dataset;

public class OrdernIdicatorEntry {
	private int id;
	private String name;
	
	public OrdernIdicatorEntry(){
		
	}

	public OrdernIdicatorEntry(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	

}
