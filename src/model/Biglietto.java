package model;

public class Biglietto {
	int id;
	int id_cliente;
	int id_volo;
	boolean valido;
	
	
	public Biglietto(int id, int id_cliente, int id_volo, boolean valido) {
		this.id = id;
		this.id_cliente = id_cliente;
		this.id_volo = id_volo;
		this.valido = valido;
	}
	
	
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getId_cliente() {
		return id_cliente;
	}
	
	
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	
	public int getId_volo() {
		return id_volo;
	}
	
	
	public void setId_volo(int id_volo) {
		this.id_volo = id_volo;
	}
	
	
	public boolean isValido() {
		return valido;
	}
	
	
	public void setValido(boolean valido) {
		this.valido = valido;
	}
	
	

}
