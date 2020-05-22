package main;

import java.time.format.DateTimeFormatter;

public class Vendet{

	private int nrVendi;
	private String EmriMbiemri;
	private String Sektori;
	private DateTimeFormatter DataKoha;
	
	
	public Vendet(int nrVendi, String EmriMbiemri, String Sektori, DateTimeFormatter DataKoha) {
		this.nrVendi = nrVendi;
		this.EmriMbiemri = EmriMbiemri;
		this.Sektori = Sektori;
		this.DataKoha = DataKoha;
	}
	
	public Vendet() {

	}

	public int getNrVendi() {
		return nrVendi;
	}
	
	public void setNrVendi(int nrVendi) {
		this.nrVendi = nrVendi;
	}

	public String getEmriMbiemri() {
		return EmriMbiemri;
	}
	
	public void setEmriMbiemri(String emriMbiemri) {
		EmriMbiemri = emriMbiemri;
	}

	public String getSektori() {
		return Sektori;
	}

	public void setSektori(String sektori) {
		Sektori = sektori;
	}
	
	public DateTimeFormatter getDataKoha() {
		return DataKoha;
	}

	public void setDataKoha(DateTimeFormatter dataKoha) {
		DataKoha = dataKoha;
	}


}


