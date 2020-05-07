package main;

import javafx.scene.control.DatePicker;

public class Lexuesit {
	private int Id;
	private String Emri;
	private String Mbiemri;
	private String Profesioni;
	private String Adresa;
	private String Sektori;
	private int Cmimi;
	private DatePicker Regjistrimi;
	private DatePicker Skadimi;

	public Lexuesit(int Id, String Emri, String Mbiemri, String Profesioni, String Adresa, String Sektori, int Cmimi,
			DatePicker Regjistrimi, DatePicker Skadimi) {
		this.Id = Id;
		this.Emri = Emri;
		this.Mbiemri = Mbiemri;
		this.Profesioni = Profesioni;
		this.Adresa = Adresa;
		this.Sektori = Sektori;
		this.Cmimi = Cmimi;
		this.Regjistrimi = Regjistrimi;
		this.Skadimi = Skadimi;
	}

	public Lexuesit() {

	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getEmri() {
		return Emri;
	}

	public void setEmri(String emri) {
		Emri = emri;
	}

	public String getMbiemri() {
		return Mbiemri;
	}

	public void setMbiemri(String mbiemri) {
		Mbiemri = mbiemri;
	}

	public String getProfesioni() {
		return Profesioni;
	}

	public void setProfesioni(String profesioni) {
		Profesioni = profesioni;
	}

	public String getAdresa() {
		return Adresa;
	}

	public void setAdresa(String adresa) {
		Adresa = adresa;
	}

	public String getSektori() {
		return Sektori;
	}

	public void setSektori(String sektori) {
		Sektori = sektori;
	}

	public int getCmimi() {
		return Cmimi;
	}

	public void setCmimi(int cmimi) {
		Cmimi = cmimi;
	}

	public DatePicker getRegjistrimi() {
		return Regjistrimi;
	}

	public void setRegjistrimi(DatePicker regjistrimi) {
		Regjistrimi = regjistrimi;
	}

	public DatePicker getSkadimi() {
		return Skadimi;
	}

	public void setSkadimi(DatePicker skadimi) {
		Skadimi = skadimi;
	}

}
