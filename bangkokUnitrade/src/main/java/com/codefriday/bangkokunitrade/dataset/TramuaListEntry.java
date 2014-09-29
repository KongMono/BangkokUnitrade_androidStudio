package com.codefriday.bangkokunitrade.dataset;

public class TramuaListEntry {
	private int id;
	private String image;
	private String name;

	public TramuaListEntry() {

	}
	
	public TramuaListEntry(int id, String image, String name) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
