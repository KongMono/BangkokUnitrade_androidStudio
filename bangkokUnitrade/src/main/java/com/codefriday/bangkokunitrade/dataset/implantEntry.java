package com.codefriday.bangkokunitrade.dataset;

public class implantEntry {
	private int id;
	private String num;
	private String master_implants_id;
	private String set_implant_name;
	private String item;
	private String description;
	private String qty;
	private String use;
	private String created_at;
	private String updated_at;
	private String plantset;
	
	public implantEntry(){
		
	}
	public implantEntry(int id,String num, String master_implants_id,
			String set_implant_name, String item, String description,
			String qty, String use, String created_at, String updated_at,
			String plantset) {
		super();
		this.id = id;
		this.num = num;
		this.master_implants_id = master_implants_id;
		this.set_implant_name = set_implant_name;
		this.item = item;
		this.description = description;
		this.qty = qty;
		this.use = use;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.plantset = plantset;
	}
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaster_implants_id() {
		return master_implants_id;
	}

	public void setMaster_implants_id(String master_implants_id) {
		this.master_implants_id = master_implants_id;
	}

	public String getSet_implant_name() {
		return set_implant_name;
	}

	public void setSet_implant_name(String set_implant_name) {
		this.set_implant_name = set_implant_name;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getPlantset() {
		return plantset;
	}

	public void setPlantset(String plantset) {
		this.plantset = plantset;
	}
	

}
