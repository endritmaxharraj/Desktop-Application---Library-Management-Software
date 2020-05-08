package main;

public class Librat {
	private int RegjistrimiID;
	private String EmriLibrit;
	private String AutoriLibrit;
	private int VitiBotimit;
	private long ISBNKodi;
	private int Sasia;

	public Librat() {

	}

	public Librat(int RegjistrimiID, String EmriLibrit, String AutoriLibrit, int VitiBotimit,
			long ISBNKodi,
			int Sasia) {
		this.RegjistrimiID = RegjistrimiID;
		this.EmriLibrit = EmriLibrit;
		this.AutoriLibrit = AutoriLibrit;
		this.VitiBotimit = VitiBotimit;
		this.ISBNKodi = ISBNKodi;
		this.Sasia = Sasia;
	}

	public int getRegjistrimiID() {
		return RegjistrimiID;
	}

	public void setRegjistrimiID(int regjistrimiID) {
		RegjistrimiID = regjistrimiID;
	}

	public String getEmriLibrit() {
		return EmriLibrit;
	}

	public void setEmriLibrit(String emriLibrit) {
		EmriLibrit = emriLibrit;
	}

	public String getAutoriLibrit() {
		return AutoriLibrit;
	}

	public void setAutoriLibrit(String autoriLibrit) {
		AutoriLibrit = autoriLibrit;
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
