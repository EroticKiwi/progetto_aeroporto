package model;

public class Amministratore extends Utente{

	private int chiave_accesso;
	
	public Amministratore(int id, String email, String password, int chiave_accesso) {
		super(id, email, password);
		setChiave_accesso(chiave_accesso);
	}
	
	public int getChiave_accesso() {
		return chiave_accesso;
	}

	public void setChiave_accesso(int chiave_accesso) {
		this.chiave_accesso = chiave_accesso;
	}
	
	public String toString() {
		return id + ", " + chiave_accesso + ", " + email + ", " + password;
	}
}
