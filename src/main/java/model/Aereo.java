package model;

public class Aereo {
	int id;
	int id_aeroporto_residenza;
	int capienza;
	String modello;
	
	
	public Aereo(int id, int id_aeroporto_residenza, int capienza, String modello) {
		this.id = id;
		this.id_aeroporto_residenza = id_aeroporto_residenza;
		this.capienza = capienza;
		this.modello = modello;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getId_aeroporto_residenza() {
		return id_aeroporto_residenza;
	}


	public void setId_aeroporto_residenza(int id_aeroporto_residenza) {
		this.id_aeroporto_residenza = id_aeroporto_residenza;
	}


	public int getCapienza() {
		return capienza;
	}


	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}


	public String getModello() {
		return modello;
	}


	public void setModello(String modello) {
		this.modello = modello;
	}
	
	public String toString() {
		return id + " | " + modello + " | " + capienza;
	}

}
