package main;

public class Librat {
	private String EmriLibritAutori;
	private int VitiBotimit;
	private long ISBNKodi;
	private int Sasia;

	public Librat() {

	}

	public Librat(String EmriLibritAutori, int VitiBotimit, long ISBNKodi, int Sasia) {
		this.EmriLibritAutori = EmriLibritAutori;
		this.VitiBotimit = VitiBotimit;
		this.ISBNKodi = ISBNKodi;
		this.Sasia = Sasia;
	}

	public String getEmriLibritAutori() {
		return EmriLibritAutori;
	}

	public void setEmriLibritAutori(String emriLibritAutori) {
		EmriLibritAutori = emriLibritAutori;
	}

	public int getVitiBotimit() {
		return VitiBotimit;
	}

	public void setVitiBotimit(int vitiBotimit) {
		VitiBotimit = vitiBotimit;
	}

	public long getISBNKodi() {
		return ISBNKodi;
	}

	public void setISBNKodi(long iSBNKodi) {
		ISBNKodi = iSBNKodi;
	}


	public int getSasia() {
		return Sasia;
	}

	public void setSasia(int sasia) {
		Sasia = sasia;
	}

}
