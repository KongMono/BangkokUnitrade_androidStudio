package com.codefriday.bangkokunitrade.dataset;

public class BiomaterialEntry {
	private String no;
	private String biomaterial;
	private String amount;
	
	public BiomaterialEntry(){
		
	}
	
	public BiomaterialEntry(String no, String biomaterial, String amount) {
		super();
		this.no = no;
		this.biomaterial = biomaterial;
		this.amount = amount;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getBiomaterial() {
		return biomaterial;
	}

	public void setBiomaterial(String biomaterial) {
		this.biomaterial = biomaterial;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}


}
