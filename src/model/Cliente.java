package model;

public class Cliente extends Utente{
	
	String nome;
	String cognome;
	String metodo_pagamento;
	
	public Cliente(int id, String nome, String cognome, String email, String password, String metodo_pagamento) {
		super(id, email, password);
		setNome(nome);
		setCognome(cognome);
		setMetodo_pagamento(metodo_pagamento);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getMetodo_pagamento() {
		return metodo_pagamento;
	}
	public void setMetodo_pagamento(String metodo_pagamento) {
		this.metodo_pagamento = metodo_pagamento;
	}
	
	public String toString() {
		return id + ", " + nome + ", " + cognome + ", " + email + ", " + password + ", " + metodo_pagamento;
	}
	
}
