package com.codefriday.bangkokunitrade.dataset;

public class StatuscaseEntry {
	private int id;
	private String title;
	private String detail;
	
	public StatuscaseEntry(){
		
	}
	
	public StatuscaseEntry(int id, String title, String detail) {
		super();
		this.id = id;
		this.title = title;
		this.detail = detail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}


}
