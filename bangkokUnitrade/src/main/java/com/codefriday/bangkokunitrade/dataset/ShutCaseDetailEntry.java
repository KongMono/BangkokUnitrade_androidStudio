package com.codefriday.bangkokunitrade.dataset;

import java.util.ArrayList;

public class ShutCaseDetailEntry {
	private String surgery_date;
	private String surgery_time;
	private String hn;
	private String patient;
	private String doctor;
	private ArrayList<implantEntry> implantList;
	
	public ShutCaseDetailEntry(){
		
	}

	public ShutCaseDetailEntry(String surgery_date,
			String surgery_time, String hn, String patient, String doctor,
			ArrayList<implantEntry> implantList) {
		super();
		this.surgery_date = surgery_date;
		this.surgery_time = surgery_time;
		this.hn = hn;
		this.patient = patient;
		this.doctor = doctor;
		this.implantList = implantList;
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

	public ArrayList<implantEntry> getImplantList() {
		return implantList;
	}

	public void setImplantList(ArrayList<implantEntry> implantList) {
		this.implantList = implantList;
	}
	
	
	
}
