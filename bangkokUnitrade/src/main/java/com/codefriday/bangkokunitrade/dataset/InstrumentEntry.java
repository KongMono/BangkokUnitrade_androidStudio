package com.codefriday.bangkokunitrade.dataset;

public class InstrumentEntry {
	private String no;
	private String name;
	
	public InstrumentEntry(){
		
	}

	public InstrumentEntry(String no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
