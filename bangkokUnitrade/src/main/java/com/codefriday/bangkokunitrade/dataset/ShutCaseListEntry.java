package com.codefriday.bangkokunitrade.dataset;

public class ShutCaseListEntry {
	private int case_id;
	private String surgery_date;
	private String surgery_time;
	private String hn;
	private String patient;
	private String doctor;
	
	public ShutCaseListEntry(){
		
	}

	public ShutCaseListEntry(int case_id, String surgery_date,
			String surgery_time, String hn, String patient, String doctor) {
		super();
		this.case_id = case_id;
		this.surgery_date = surgery_date;
		this.surgery_time = surgery_time;
		this.hn = hn;
		this.patient = patient;
		this.doctor = doctor;
	}

	public int getCase_id() {
		return case_id;
	}

	public void setCase_id(int case_id) {
		this.case_id = case_id;
	}

	public String getSurgery_date() {
		return surgery_date;
	}

	public void setSurgery_date(String surgery_date) {
		this.surgery_date = surgery_date;
	}

	public String getSurgery_time() {
		return surgery_time;
	}

	public void setSurgery_time(String surgery_time) {
		this.surgery_time = surgery_time;
	}

	public String getHn() {
		return hn;
	}

	public void setHn(String hn) {
		this.hn = hn;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	
	
}
