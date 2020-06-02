package main.models;

import java.sql.Date;

import javafx.scene.control.DatePicker;

public class BookHolders {
	private String EmriMbiemri;
	private String EmriLibritAutori;
	private DatePicker DataMarrjes;
	private DatePicker DataKthimit;
	private String EmriPuntorit;
	private Date DataMarrjesNew;
	private Date DataKthimitNew;

	public BookHolders() {

	}

	public BookHolders(String EmriMbiemri, String EmriLibritAutori,
			DatePicker DataMarrjes,
			DatePicker DataKthimit, String EmriPuntorit) {
		this.EmriMbiemri = EmriMbiemri;
		this.EmriLibritAutori = EmriLibritAutori;
		this.DataMarrjes = DataMarrjes;
		this.DataKthimit = DataKthimit;
		this.EmriPuntorit = EmriPuntorit;
	}

	public BookHolders(String EmriMbiemri, String EmriLibritAutori,
			Date DataMarrjesNew,
			Date DataKthimitNew,
			String EmriPuntorit) {
		this.EmriMbiemri = EmriMbiemri;
		this.EmriLibritAutori = EmriLibritAutori;
		this.DataMarrjesNew = DataMarrjesNew;
		this.DataKthimitNew = DataKthimitNew;
		this.EmriPuntorit = EmriPuntorit;
	}

	public String getEmriMbiemri() {
		return EmriMbiemri;
	}

	public void setEmriMbiemri(String emriMbiemri) {
		EmriMbiemri = emriMbiemri;
	}

	public String getEmriLibritAutori() {
		return EmriLibritAutori;
	}

	public void setEmriLibritAutori(String emriLibritAutori) {
		EmriLibritAutori = emriLibritAutori;
	}

	public DatePicker getDataMarrjes() {
		return DataMarrjes;
	}

	public void setDataMarrjes(DatePicker dataMarrjes) {
		DataMarrjes = dataMarrjes;
	}

	public DatePicker getDataKthimit() {
		return DataKthimit;
	}

	public void setDataKthimit(DatePicker dataKthimit) {
		DataKthimit = dataKthimit;
	}

	public Date getDataKthimitNew() {
		return DataKthimitNew;
	}

	public void setDataKthimitNew(Date DataKthimitNew) {
		this.DataKthimitNew = DataKthimitNew;
	}

	public Date getDataMarrjesNew() {
		return DataMarrjesNew;
	}

	public void setDataMarrjesNew(Date DataMarrjesNew) {
		this.DataMarrjesNew = DataMarrjesNew;
	}

	public String getEmriPuntorit() {
		return EmriPuntorit;
	}

	public void setEmriPuntorit(String emriPuntorit) {
		EmriPuntorit = emriPuntorit;
	}


}
