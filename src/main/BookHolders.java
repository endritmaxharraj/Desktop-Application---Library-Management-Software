package main;

import javafx.scene.control.DatePicker;

public class BookHolders {
	private String EmriMbiemri;
	private String EmriLibritAutori;
	private DatePicker DataMarrjes;
	private DatePicker DataKthimit;
	private String EmriPuntorit;

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

	public String getEmriPuntorit() {
		return EmriPuntorit;
	}

	public void setEmriPuntorit(String emriPuntorit) {
		EmriPuntorit = emriPuntorit;
	}

}
