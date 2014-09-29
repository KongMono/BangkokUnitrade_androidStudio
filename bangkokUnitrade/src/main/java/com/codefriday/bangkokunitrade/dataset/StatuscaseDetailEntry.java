package com.codefriday.bangkokunitrade.dataset;

import java.util.ArrayList;

public class StatuscaseDetailEntry {
	private String order_type;
	private String status;
	private String hospital;
	private String doctor;
	private String sergery_date;
	private String sergery_time;
	private String patient_name;
	private String hn;
	private ArrayList<StatuscaseDetailListEntry> order;
	private ArrayList<InstrumentEntry> instrument;
	private ArrayList<BiomaterialEntry> Biomaterial;
	private String addons;
	private String instrument_doctor;
	private String transport;
	private String sale;
	
	public StatuscaseDetailEntry(){
		
	}
	
	public StatuscaseDetailEntry(String order_type, String status,
			String hospital, String doctor, String sergery_date,
			String sergery_time, String patient_name, String hn,
			ArrayList<StatuscaseDetailListEntry> order,
			ArrayList<InstrumentEntry> instrument,
			ArrayList<BiomaterialEntry> biomaterial, String addons,
			String instrument_doctor, String transport, String sale) {
		super();
		this.order_type = order_type;
		this.status = status;
		this.hospital = hospital;
		this.doctor = doctor;
		this.sergery_date = sergery_date;
		this.sergery_time = sergery_time;
		this.patient_name = patient_name;
		this.hn = hn;
		this.order = order;
		this.instrument = instrument;
		Biomaterial = biomaterial;
		this.addons = addons;
		this.instrument_doctor = instrument_doctor;
		this.transport = transport;
		this.sale = sale;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getSergery_date() {
		return sergery_date;
	}

	public void setSergery_date(String sergery_date) {
		this.sergery_date = sergery_date;
	}

	public String getSergery_time() {
		return sergery_time;
	}

	public void setSergery_time(String sergery_time) {
		this.sergery_time = sergery_time;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getHn() {
		return hn;
	}

	public void setHn(String hn) {
		this.hn = hn;
	}

	public ArrayList<StatuscaseDetailListEntry> getOrder() {
		return order;
	}

	public void setOrder(ArrayList<StatuscaseDetailListEntry> order) {
		this.order = order;
	}

	public ArrayList<InstrumentEntry> getInstrument() {
		return instrument;
	}

	public void setInstrument(ArrayList<InstrumentEntry> instrument) {
		this.instrument = instrument;
	}

	public ArrayList<BiomaterialEntry> getBiomaterial() {
		return Biomaterial;
	}

	public void setBiomaterial(ArrayList<BiomaterialEntry> biomaterial) {
		Biomaterial = biomaterial;
	}

	public String getAddons() {
		return addons;
	}

	public void setAddons(String addons) {
		this.addons = addons;
	}

	public String getInstrument_doctor() {
		return instrument_doctor;
	}

	public void setInstrument_doctor(String instrument_doctor) {
		this.instrument_doctor = instrument_doctor;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	
	
}
