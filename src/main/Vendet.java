package main;

public class Vendet{

	private int vendiId;
	private String emriUlses;
	private String emriSalles;
	
	
	public Vendet(int vendiId,String emriUlses,String emriSalles){
	this.vendiId = vendiId;
	this.emriUlses = emriUlses;
	this.emriSalles = emriSalles;
	}
	
	
	public int getVendiId(){
		return vendiId;
	}
	
	public void setVendiId(int vendiId){
		this.vendiId = vendiId;
	}
	public String getEmriUlses(){
		return emriUlses;
	}
	public void setEmriUlses(String emriUlses){
		this.emriUlses = emriUlses;
	}
	
	public String getEmriSalles(){
		return emriSalles;
	}
	public void setEmriSalles(String emriSalles){
		this.emriSalles = emriSalles;
	}
	
}


