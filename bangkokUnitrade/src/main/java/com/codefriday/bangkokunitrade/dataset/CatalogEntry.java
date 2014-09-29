package com.codefriday.bangkokunitrade.dataset;

public class CatalogEntry {
	private int id;
	private String name;
	private String url;
	boolean ischecked;
	
	public CatalogEntry() {

	}
	
	public CatalogEntry(int id, String name, String url, boolean ischecked) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.ischecked = ischecked;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isIschecked() {
		return ischecked;
	}

	public void setIschecked(boolean ischecked) {
		this.ischecked = ischecked;
	}
	
	
	

}
