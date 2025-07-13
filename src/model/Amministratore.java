package model;

public class Amministratore extends Utente{

	private String chiave_accesso;
	
	public Amministratore(int id, String email, String password) {
		super(id, email, password);
		setChiave_accesso(chiave_accesso);
	}
	
	public String getChiave_accesso() {
		return chiave_accesso;
	}

	public void setChiave_accesso(String chiave_accesso) {
		this.chiave_accesso = chiave_accesso;
	}
}
