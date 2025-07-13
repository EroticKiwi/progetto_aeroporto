package model;

public class Volo {
	int id;
	int id_aereo;
	int id_aeroporto_partenza;
	int id_aeroporto_arrivo;
	int posti_liberi;
	float prezzo;
	String nome_volo;
	String orario_partenza;
	String orario_arrivo;
	boolean valido;
	
	
	public Volo(int id, int id_aereo, int id_aeroporto_partenza, int id_aeroporto_arrivo, int posti_liberi,
			float prezzo, String nome_volo, String orario_partenza, String orario_arrivo, boolean valido) {
		this.id = id;
		this.id_aereo = id_aereo;
		this.id_aeroporto_partenza = id_aeroporto_partenza;
		this.id_aeroporto_arrivo = id_aeroporto_arrivo;
		this.posti_liberi = posti_liberi;
		this.prezzo = prezzo;
		this.nome_volo = nome_volo;
		this.orario_partenza = orario_partenza;
		this.orario_arrivo = orario_arrivo;
		this.valido = valido;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getId_aereo() {
		return id_aereo;
	}


	public void setId_aereo(int id_aereo) {
		this.id_aereo = id_aereo;
	}


	public int getId_aeroporto_partenza() {
		return id_aeroporto_partenza;
	}


	public void setId_aeroporto_partenza(int id_aeroporto_partenza) {
		this.id_aeroporto_partenza = id_aeroporto_partenza;
	}


	public int getId_aeroporto_arrivo() {
		return id_aeroporto_arrivo;
	}


	public void setId_aeroporto_arrivo(int id_aeroporto_arrivo) {
		this.id_aeroporto_arrivo = id_aeroporto_arrivo;
	}


	public int getPosti_liberi() {
		return posti_liberi;
	}


	public void setPosti_liberi(int posti_liberi) {
		this.posti_liberi = posti_liberi;
	}


	public float getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}


	public String getNome_volo() {
		return nome_volo;
	}


	public void setNome_volo(String nome_volo) {
		this.nome_volo = nome_volo;
	}


	public String getOrario_partenza() {
		return orario_partenza;
	}


	public void setOrario_partenza(String orario_partenza) {
		this.orario_partenza = orario_partenza;
	}


	public String getOrario_arrivo() {
		return orario_arrivo;
	}


	public void setOrario_arrivo(String orario_arrivo) {
		this.orario_arrivo = orario_arrivo;
	}


	public boolean isValido() {
		return valido;
	}


	public void setValido(boolean valido) {
		this.valido = valido;
	}
	
	
	
	
	
	

}
