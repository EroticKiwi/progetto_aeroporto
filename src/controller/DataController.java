package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.*;
import exceptions.*;

public class DataController {
	
	private static DataController instance;
	
	private Utente utenteSessione; // Quando un utente effettua l'accesso, i suoi dati vengono mantenuti in memoria.
	
	private ArrayList<Object> entitaObjs; // Quando si effettua un "trova" su entità diverse da Cliente e Amministratore, allora si utilizza questa lista per ottimizzare il consumo di memoria.

	private DataController() {
		entitaObjs = new ArrayList<Object>();
	}
	
	public static DataController getInstance() {
		if(instance == null) {
			instance = new DataController();
		}
		
		return instance;
	}

	// Metodi Cliente
	
	public void inserisciCliente(String nome, String cognome, String email, String password, String metodo_pagamento) { // Chiamato alla registrazione del cliente, oppure alla modifica dei suoi dati
		
		// Inserimento cliente nel DB
		// Ok, e ora l'id come lo prendiamo?!
		// Andiamo a prendere l'utente appena inserito!
		// 2 operazioni. Questo metodo viene effettuato una volta per registrazione di un cliente, qualcosa che dovrebbe accadere una singola volta per esecuzione del programma (o anche meno)
		
		String query = "INSERT INTO Cliente(nome, cognome, email, password, metodo_pagamento) VALUES (?, ?, ?, ?, ?)";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put(1, nome);
		params.put(2, cognome);
		params.put(3, email);
		params.put(4, password);
		params.put(5, metodo_pagamento);
		
		try {
			DataModel.getInstance().inserisciEntita(query, params);
			
			// Trova Cliente
			
			query = "SELECT id FROM Cliente WHERE email = ? AND password = ?";
			
			params.clear();
			params.put(1, email);
			params.put(2, password);
			
			ResultSet rs = DataModel.getInstance().trovaEntita(query, params);
			int id = -1;
			
			if(!rs.next()) { // bisogna sempre fare rs.next() dato che si parte da prima dei risultati, per qualche oscuro motivo :P
				// Dici alla view che c'è stato un errore generico del DB.
				rs.getStatement().close();
			}
						
			id = rs.getInt("id");
			
			rs.getStatement().close();
			
			utenteSessione = new Cliente(id, nome, cognome, email, password, metodo_pagamento);
			
			System.out.println(utenteSessione.toString());
			
			// Prosegui con le view
			
		} catch (InserisciException e) {
			// Comunica alla view che c'è stato un errore durante l'inserimento!
		} catch (SQLException | TrovaException e) { /* errore nell'rs.next() o nella chiusura dello statement*/
			// Comunica alla view che c'è stato un errore generico del database! 
		}
	}
	
	public void trovaCliente(String email, String password) { // Chiamato al Login del cliente
		
		String query = "SELECT * FROM Cliente WHERE email = ? AND password = ?";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put(1, email);
		params.put(2, password);
		
		try {
			
			ResultSet rs = DataModel.getInstance().trovaEntita(query, params);
			
			if(!rs.next()) {
				// Dici alla view che nessun cliente è stato trovato!
				System.out.println("La combinazione di email e password potrebbe essere sbagliata!");
				rs.getStatement().close();
				return;
			}		

			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			String cognome = rs.getString("cognome");
			String mail = rs.getString("email");
			String pass = rs.getString("password");
			String metodo_pagamento = rs.getString("metodo_pagamento");
			
			rs.getStatement().close();
			
			utenteSessione = new Cliente(id, nome, cognome, mail, pass, metodo_pagamento);
			
			System.out.println(utenteSessione.toString());
			
			// Prosegui con le view
			
		} catch(SQLException | TrovaException e) {
			// Comunica alla view che c'è stato un errore generico del DB
		}
	}
	
	public void eliminaCliente() { // Chiamato quando un cliente prova ad eliminare il suo account

		if(utenteSessione == null) { // Per qualche motivo stiamo cercando di eliminare un utente che non ha fatto l'accesso, ci fermiamo.
			return; 
		}
		
		String query = "DELETE FROM Cliente WHERE id = ?";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put(1, utenteSessione.getId());
		
		try {
			DataModel.getInstance().eliminaEntita(query, params);
			System.out.println("L'utente è stato cancellato!");
			// Prosegui con le view
		} catch(EliminaException e) {
			// Comunica alla view che c'è stato un errore generico del DB.
		}
		
	}
	
	// Metodi Amministratore
	
	public void trovaAmministratore(String email, String password, int chiave_accesso) { // Chiamato al Login dell'amministratore
		
		String query = "SELECT * FROM Amministratore WHERE email = ? AND password = ? AND chiave_accesso = ?";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put(1, email);
		params.put(2, password);
		params.put(3, chiave_accesso);
		
		try {
			ResultSet rs = DataModel.getInstance().trovaEntita(query, params);
			
			if(!rs.next()) {
				// Dici alla view che nessun amministratore è stato trovato!
				System.out.println("La combinazione di email, password e chiave d'accesso potrebbe essere sbagliata!");
				rs.getStatement().close();
				return;
			}
			
			int id = rs.getInt("id");
			
			rs.getStatement().close();
			
			utenteSessione = new Amministratore(id, email, password, chiave_accesso);
			
			System.out.println(utenteSessione.toString());
			
		} catch(SQLException | TrovaException e) {
			// Comunica alla view che c'è stato un errore generico del DB
		}
	}
	
	// Metodi Aeroporto
	
	public void inserisciAeroporto(String citta, String nazione, int numero_piste) { // Chiamato durante la creazione di un nuovo Aeroporto
		
		String query = "INSERT INTO Aeroporto(citta, nazione, numero_piste) VALUES (?, ?, ?)";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put(1, citta);
		params.put(2, nazione);
		params.put(3, numero_piste);
		
		try {
			DataModel.getInstance().inserisciEntita(query, params);
			
			// Prosegui con le view
			System.out.println("Inserimento andato a buon fine!");
		} catch(InserisciException e) {
			System.out.println(e.getMessage());
			// Comunica alla view che c'è stato un errore durante l'inserimento oppure esiste già un dato uguale!
		}
	}
	
	public void trovaAeroporto(int id) { // Chiamato durante la ricerca di un Aeroporto
		
		String query = "SELECT * FROM Aeroporto WHERE id = ?";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put(1, id);
		
		entitaObjs.clear();
		
		try {
			ResultSet rs = DataModel.getInstance().trovaEntita(query, params);
			
			if(!rs.next()) {
				// Dici alla view che nessun aeroporto è stato trovato!
				System.out.println("Nessun aeroporto presente nel sistema corrisponde ai dati inseriti!");
				rs.getStatement().close();
				return;
			}
			
			String citta = rs.getString("citta");
			String nazione = rs.getString("nazione");
			int numero_piste = rs.getInt("numero_piste");
			
			rs.getStatement().close();
			
			Aeroporto aeroporto = new Aeroporto(id, citta, nazione, numero_piste);
			entitaObjs.add(aeroporto);
			
			// Prosegui con le view
			System.out.println(aeroporto.toString());
			
		} catch(SQLException | TrovaException e) {
			// Comunica alla view che c'è stato un errore generico del DB
		}
		
	}
	
	public void trovaTuttiAeroporti() {
		
		String query = "SELECT * FROM Aeroporto";
		
		entitaObjs.clear();
		
		try {
			ResultSet rs = DataModel.getInstance().trovaEntita(query, null);
			
			if(!rs.next()) {
				// Dici alla view che nessun aeroporto è stato trovato!
				System.out.println("Nessun aeroporto presente nel sistema!");
				rs.getStatement().close();
				return;
			}
			
			// Dobbiamo tornare al primo elemento del result set.
			rs.beforeFirst();
			
			Aeroporto aeroporto;
			while(rs.next()) { // Non c'è bisogno di fermare se rs è vuoto, si fermerà da solo!
				aeroporto = new Aeroporto(rs.getInt("id"), rs.getString("citta"), rs.getString("nazione"), rs.getInt("numero_piste"));
				entitaObjs.add(aeroporto);
				System.out.println(aeroporto.toString());
			}
			
			rs.getStatement().close();
			
			// Prosegui con le view
						
		} catch(SQLException | TrovaException e) {
			// Comunica alla view che c'è stato un errore generico del DB
			System.out.println(e.getMessage());
		}
	}
	
	public void eliminaAeroporto(int id) {
		
		String query = "DELETE FROM Aeroporto WHERE id = ?";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put(1, id);
		
		try {
			DataModel.getInstance().eliminaEntita(query, params);
			System.out.println("L'aeroporto è stato cancellato!");
			// Prosegui con le view
		} catch(EliminaException e) {
			// Comunica alla view che c'è stato un errore generico del DB
		}
		
	}
}
