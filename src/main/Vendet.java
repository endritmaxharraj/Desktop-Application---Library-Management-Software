package main;

import java.sql.Timestamp;

public class Vendet{

	private int nrVendi;
	private String EmriMbiemri;
	private String Sektori;
	private Timestamp DataKoha;
	
	public Vendet(int nrVendi, String EmriMbiemri, String Sektori, Timestamp DataKoha) {
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
	
	public Timestamp getDataKoha() {
		return DataKoha;
	}

	public void setDataKoha(Timestamp dataKoha) {
		DataKoha = dataKoha;
	}

}


