package com.codefriday.bangkokunitrade.dataset;

import java.util.ArrayList;

public class TramuaSubListEntry {
	private String group_type_name;
	private ArrayList<SetEntry> setEntries;
	
	public TramuaSubListEntry(){
		
	}

	public TramuaSubListEntry(String group_type_name,
			ArrayList<SetEntry> setEntries) {
		super();
		this.group_type_name = group_type_name;
		this.setEntries = setEntries;
	}

	public String getGroup_type_name() {
		return group_type_name;
	}

	public void setGroup_type_name(String group_type_name) {
		this.group_type_name = group_type_name;
	}

	public ArrayList<SetEntry> getSetEntries() {
		return setEntries;
	}

	public void setSetEntries(ArrayList<SetEntry> setEntries) {
		this.setEntries = setEntries;
	}
	
}


