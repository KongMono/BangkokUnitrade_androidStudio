package com.codefriday.bangkokunitrade.dataset;

public class DoctorToolsEntry {
	private int no;
	private	String tool;
	
	public DoctorToolsEntry(){
		
	}
	
	public DoctorToolsEntry(int no, String tool) {
		super();
		this.no = no;
		this.tool = tool;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}
	
	
	
	
}
