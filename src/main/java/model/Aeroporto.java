package model;

public class Aeroporto {
	int id;
	int numero_piste;
	String citta;
	String nazione;
	
	
	public Aeroporto(int id, String citta, String nazione, int numero_piste) {
		this.id = id;
		this.numero_piste = numero_piste;
		this.citta = citta;
		this.nazione = nazione;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getNumero_piste() {
		return numero_piste;
	}


	public void setNumero_piste(int numero_piste) {
		this.numero_piste = numero_piste;
	}


	public String getCitta() {
		return citta;
	}


	public void setCitta(String citta) {
		this.citta = citta;
	}


	public String getNazione() {
		return nazione;
	}


	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
	public String toString() {
		return "ID: " + id + " | Nazione: " + nazione + " | Città: " + citta;
	}
	
}
