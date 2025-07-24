package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import enums.ActiveEntity_Enum;
import model.*;
import exceptions.*;

public class DataController {
	
	private static DataController instance;
	
	// Viene utilizzata solo in InserisciCliente, TrovaCliente, TrovaAmministratore
	private Utente utenteSessione; // Quando un utente effettua l'accesso, i suoi dati vengono mantenuti in memoria.
	
	// è praticamente una cache che viene riempita e svuotata ogni  "SELECT"
	private ArrayList<Object> entitaObjs; // Quando si effettua un "trova" su entità diverse da Cliente e Amministratore, allora si utilizza questa lista per ottimizzare il consumo di memoria.
	private Object entitaObj; // Utilizzata per la possibile modifica di un entità. Non si utilizza erntitaObjs.get(0) poichè ha una funzionalità diversa.
	
	private DataController() {
		entitaObjs = new ArrayList<Object>();
	}
	
	public static DataController getInstance() {
		if(instance == null) {
			instance = new DataController();
		}
		
		return instance;
	}
	
	// Metodi Utility
	
	public void closeDBConnection() {
		DataModel.getInstance().closeDBConnection();
	}
	
	public Object getEntityObj() {
		return entitaObj;
	}
	
	public void setEntityObj(Object obj) {
		entitaObj = obj;
	}
	
	public ArrayList<Object> getEntities(){ // Questo metodo serve solo per il ViewController
		return entitaObjs;
	}
	
	public void clearEntities(){
		entitaObjs.clear();
	}
	
	public Utente getUtenteSessione() {
		return utenteSessione;
	}
	
	public void clearUtenteSessione() {
		utenteSessione = null;
	}
	
	public int getIdUtente() {
		return utenteSessione.getId();
	}
	
	public String getNomeUtente() {
		Cliente cliente = (Cliente) utenteSessione;
		return cliente.getNome();
	}
	
	public boolean isClient() { // Ci dice se l'utente loggato è un cliente. Se non è così è per forza un amministratore.
		if(utenteSessione instanceof Cliente) { // Viene chiamato in tutte le view che devono mostrare
												// Informazioni diverse in base al ruolo dell'utente
			return true;
		}
		
		return false;
	}

	// Metodi Cliente
	
	// Serve per la registrazione (ClientRegister_View)
	public void inserisciCliente(String nome, String cognome, String email, String password, String metodo_pagamento) { // Chiamato alla registrazione del cliente, oppure alla modifica dei suoi dati
		// La view ClientRegister_View una volta cliccato il bottone "Registra"
		// chiama il listener e il listener chiama questo metodo
		
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
		params.put(5, metodo_pagamento); // Carichiamo i dati dentro una Map con l'indice di posizione nella query
		
		
		try {
			// Chiamiamo il data Model e gli diamo la query da fare, il DataModel la assembla e la esegue
			DataModel.getInstance().inserisciEntita(query, params); 
			
			// Voglio salvare i dati dell'utente nel nostro attributo
			// Ma noi non sappiamo l'iD del cliente (in quanto viene auto generato)
			// Quindi lo andiamo a trovare tramite una SELECT
			
			// --- Trova Cliente --- Con questa query cerchiamo l'ID cliente che ha questa certa email e passw
			query = "SELECT id FROM Cliente WHERE email = ? AND password = ?";
			
			clearEntities();
			params.clear();
			
			params.put(1, email);
			params.put(2, password);
			
			// Chiamiamo il Data model per farci restituire un ResultSet con L'ID
			ResultSet rs = DataModel.getInstance().trovaEntita(query, params); 
			int id = -1; // Misura di sicurezza per non avere un ID vuoto
			
			if(!rs.next()) { // bisogna sempre fare rs.next() dato che si parte da prima dei risultati, per qualche oscuro motivo :P
				// Dici alla view che c'è stato un errore generico del DB.
				rs.getStatement().close();
				return;
			}
						
			id = rs.getInt("id"); // Da questo metodo ResultSet otteniamo L'ID caricato
			
			rs.getStatement().close();
			
			utenteSessione = new Cliente(id, nome, cognome, email, password, metodo_pagamento);
			
			System.out.println(utenteSessione.toString()); // Per debug
			
			// Prosegui con le view
			// Attiva la view specifica FindEntity
			ViewController.getInstance().FindEntityView_Activate(ActiveEntity_Enum.Biglietto);
			
		} catch (InserisciException e) {
			ViewController.getInstance().ClientRegister_ShowError(e.getMessage());
		} catch (SQLException e) { /* errore nell'rs.next() o nella chiusura dello statement*/
			// Comunica alla view che c'è stato un errore generico del database
			ViewController.getInstance().ShowDBError_Modal();
		}
	}
	
	
	 // Questo metodo viene chiamato quando facciamo il login (per vedere se l'utente esiste)
	public void trovaCliente(String email, String password) {
		String query = "SELECT * FROM Cliente WHERE email = ? AND password = ?";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put(1, email);
		params.put(2, password);
		
		try {
			
			ResultSet rs = DataModel.getInstance().trovaEntita(query, params);
			
			if(!rs.next()) {
				// Dici alla view che nessun cliente è stato trovato!
				ViewController.getInstance().ClientLogin_ShowLoginError();
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
			ViewController.getInstance().FindEntityView_Activate(ActiveEntity_Enum.Biglietto);

		} catch(SQLException e) {
			// Comunica alla view che c'è stato un errore generico del DB
			ViewController.getInstance().ShowDBError_Modal();
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
			
			ViewController.getInstance().ShowDeletion_Modal("L'account è stato cancellato con successo."); // Pop-up che indica all'utente la corretta cancellazione del suo account
			// L'esecuzione si ferma qui fino a che non chiudiamo il modal
			ViewController.getInstance().Logout();
		} catch(SQLException e) {
			// Comunica alla view che c'è stato un errore generico del DB.
			ViewController.getInstance().ShowDBError_Modal();
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
				ViewController.getInstance().AdminLogin_ShowLoginError();
				System.out.println("La combinazione di email, password e chiave d'accesso potrebbe essere sbagliata!");
				rs.getStatement().close();
				return;
			}
			
			int id = rs.getInt("id");
			
			rs.getStatement().close();
			
			utenteSessione = new Amministratore(id, email, password, chiave_accesso);
			
			System.out.println(utenteSessione.toString());
			
			// Prosegui con le view
			ViewController.getInstance().FindEntityView_Activate(ActiveEntity_Enum.Volo);

			
		} catch(SQLException e) {
			// Comunica alla view che c'è stato un errore generico del DB
			ViewController.getInstance().ShowDBError_Modal();
		}
	}
	
	// Metodi Aeroporto
	
	public void trovaTuttiAeroporti() {
		
		String query = "SELECT * FROM Aeroporto ORDER BY id";
		
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
						
		} catch(SQLException e) {
			// Comunica alla view che c'è stato un errore generico del DB
			ViewController.getInstance().ShowDBError_Modal();
		}
	}
	
	// Metodi Aereo
	
	public void trovaTuttiAerei() {
		
		String query = "SELECT * FROM Aereo ORDER BY id";
		
		entitaObjs.clear();
		
		try {
			ResultSet rs = DataModel.getInstance().trovaEntita(query, null);
			
			if(!rs.next()) {
				// Dici alla view che nessun aeroporto è stato trovato!
				System.out.println("Nessun aereo presente nel sistema!");
				rs.getStatement().close();
				return;
			}
			
			// Dobbiamo tornare al primo elemento del result set.
			rs.beforeFirst();
			
			Aereo aereo;
			while(rs.next()) { // Non c'è bisogno di fermare se rs è vuoto, si fermerà da solo!
				aereo = new Aereo(rs.getInt("id"), rs.getInt("id_aeroporto_residenza"), rs.getInt("capienza"), rs.getString("modello"));
				entitaObjs.add(aereo);
				System.out.println(aereo.toString());
			}
			
			rs.getStatement().close();
			
			// Prosegui con le view
						
		} catch(SQLException e) {
			// Comunica alla view che c'è stato un errore generico del DB
			ViewController.getInstance().ShowDBError_Modal();
		}
		
	}
	
	public int getCapienzaAereo(int id) {
		
		String query = "GET capienza FROM Aereo WHERE id = ?";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put(1, id);
		
		try {
			ResultSet rs = DataModel.getInstance().trovaEntita(query, params);
			
			if(!rs.next()) {
				rs.getStatement().close();
				return 0; // è impossibile che non trovi l'aereo, quindi questo caso non dovrebbe MAI verificarsi. Questo perchè i dati dell'aereo vengono inseriti dalla View dopo aver trovato tutti gli aerei presenti nel database.
			}
			
			int capienza = rs.getInt("capienza");
			rs.getStatement().close();
			
			return capienza;
		} catch(SQLException e) {
			ViewController.getInstance().ShowDBError_Modal();
		}
		
		return 0;
	}
	
	// Metodi Volo
	
	// Modifica di un volo già esistente
	
	public void modificaVolo(int idVolo, float prezzo, int id_aeroporto_partenza, int id_aeroporto_arrivo, String orario_partenza, String orario_arrivo, int id_aereo, int postiLiberi) {
		
		String query = "UPDATE VOLO SET nome = ?, prezzo = ?, id_aeroporto_partenza = ?, id_aeroporto_arrivo = ?, orario_partenza = ?, orario_arrivo = ?, id_aereo = ?, posti_liberi = ? WHERE id = ?";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
				
		try {
			String nome_volo = getNomeVolo(id_aeroporto_partenza, id_aeroporto_arrivo);
		
			params.put(1, nome_volo);
			params.put(2, prezzo);
			params.put(3, id_aeroporto_partenza);
			params.put(4, id_aeroporto_arrivo);
			params.put(5, orario_partenza);
			params.put(6, orario_arrivo);
			params.put(7, id_aereo);
			params.put(8, postiLiberi);
			params.put(9, idVolo);
			
			try {
				DataModel.getInstance().inserisciEntita(query, params); 
				
				ViewController.getInstance().ShowInsert_Modal("Il volo è stato modificato!");
				ViewController.getInstance().FindEntityView_Activate(ActiveEntity_Enum.Volo);
			} catch(SQLException e) {
				ViewController.getInstance().ShowDBError_Modal();
			} catch(InserisciException e) {
				ViewController.getInstance().EntityDetailsView_ShowError(e.getMessage());
			}
		}catch(InserisciException e) {
			ViewController.getInstance().EntityDetailsView_ShowError(e.getMessage());
		}
	
	}
	
	public void trovaTuttiVoli() {
		
		String query = "SELECT * FROM Volo WHERE valido = true AND posti_liberi > 0 ORDER BY id"; // Mostriamo solo i voli validi
		
		entitaObjs.clear();
		
		try {
			ResultSet rs = DataModel.getInstance().trovaEntita(query, null);
			
			if(!rs.next()) {
				// Dici alla view che nessun aeroporto è stato trovato!
				System.out.println("Nessun volo presente nel sistema!");
				rs.getStatement().close();
				return;
			}
			
			// Dobbiamo tornare al primo elemento del result set.
			rs.beforeFirst();
			
			Volo volo;
			while(rs.next()) { // Non c'è bisogno di fermare se rs è vuoto, si fermerà da solo!
				
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				float prezzo = rs.getFloat("prezzo");
				int id_aeroporto_partenza = rs.getInt("id_aeroporto_partenza");
				int id_aeroporto_arrivo = rs.getInt("id_aeroporto_arrivo");
				String orario_partenza = rs.getString("orario_partenza");
				String orario_arrivo = rs.getString("orario_arrivo");
				int id_aereo = rs.getInt("id_aereo");
				int posti_liberi = rs.getInt("posti_liberi");
				boolean valido = rs.getBoolean("valido");
				
				volo = new Volo(id, id_aereo, id_aeroporto_partenza, id_aeroporto_arrivo, posti_liberi, prezzo, nome, orario_partenza, orario_arrivo, valido);
				
				entitaObjs.add(volo);
				System.out.println(volo.toString());
			}
			
			rs.getStatement().close();
			
			// Prosegui con le view
						
		} catch(SQLException e) {
			// Comunica alla view che c'è stato un errore generico del DB
			ViewController.getInstance().ShowDBError_Modal();
		}
		
	}
	
	public void eliminaVolo(int id) { // Settiamo la validità del volo a 'False', non eliminiamo davvero il volo specifico.
		
		String query = "UPDATE VOLO SET valido = false WHERE ID = ?";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put(1, id);
		
		try {
			DataModel.getInstance().eliminaEntita(query, params);
			System.out.println("Il volo è stato cancellato!");
			// Prosegui con le view
			
			ViewController.getInstance().ShowDeletion_Modal("Il volo è stato eliminato con successo.");
			ViewController.getInstance().FindEntityView_Activate(ActiveEntity_Enum.Volo); // Torniamo alla pagina voli
		} catch(SQLException e) {
			// Comunica alla view che c'è stato un errore generico del DB
			ViewController.getInstance().ShowDBError_Modal();
		}
		
	}
	
	public String getNomeVolo(int id_partenza, int id_arrivo) throws InserisciException {
		
		String query = "SELECT Citta FROM AEROPORTO WHERE id = ? OR id = ?";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put(1, id_partenza);
		params.put(2, id_arrivo);
		
		try {
			
			ResultSet rs = DataModel.getInstance().trovaEntita(query, params);
			
			if(!rs.next()) {
				return ""; // Non dovremmo mai avere questo errore, dato che gli aeroporti ESISTERANNO PER FORZA. Nella view li sceglieremo a seguito di una chiamata al DB di tipo "trovaEntita()"
			}
			
			String nome = rs.getString("citta");
			rs.next();
			nome += " - " + rs.getString("citta");
			
			return nome;
			
		} catch(SQLException e) {
			throw new InserisciException("aeroporto"); // Sappiamo che se dà errore qui allora sono stati inseriti id aereo che non esistono!
		}
	}
	
	// Metodi Biglietto
	
	public void inserisciBiglietto(int id_volo, boolean valido) {
		
		String query = "INSERT INTO Biglietto(id_cliente, id_volo, valido) VALUES (?,?,?)";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put(1, utenteSessione.getId());
		params.put(2, id_volo);
		params.put(3, valido);
		
		try {
			DataModel.getInstance().inserisciEntita(query, params);
			System.out.println("Il biglietto è stato inserito!");

			// Prosegui con le view
			
			ViewController.getInstance().ShowBigliettoComprato_Modal();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			// Comunica alla view che c'è stato un errore generico del DB
			ViewController.getInstance().ShowDBError_Modal();
		} catch(InserisciException e) {
			
		}
		
	}
	
	public void trovaTuttiBiglietti() {
		
		String query = "SELECT * FROM Biglietto WHERE id_cliente = ? AND valido = true ORDER BY id";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put(1, utenteSessione.getId());
		
		entitaObjs.clear();
		
		try {
			ResultSet rs = DataModel.getInstance().trovaEntita(query, params);
			
			if(!rs.next()) {
				// Dici alla view che nessun biglietto è stato trovato!
				System.out.println("Nessun biglietto presente nel sistema!");
				rs.getStatement().close();
				return;
			}
			
			// Dobbiamo tornare al primo elemento del result set.
			rs.beforeFirst();
			
			Biglietto biglietto;
			while(rs.next()) { // Non c'è bisogno di fermare se rs è vuoto, si fermerà da solo!
				biglietto = new Biglietto(rs.getInt("id"), rs.getInt("id_cliente"), rs.getInt("id_volo"), rs.getBoolean("valido"));
				entitaObjs.add(biglietto);
				System.out.println(biglietto.toString());
			}
			
			rs.getStatement().close();
			
			// Prosegui con le view
						
		} catch(SQLException e) {
			// Comunica alla view che c'è stato un errore generico del DB
			ViewController.getInstance().ShowDBError_Modal();
		}
		
	}
	
	public void eliminaBiglietto(int id) { // Il biglietto viene annullato, non eliminato del tutto dalla base dati.
		
		String query = "UPDATE BIGLIETTO SET valido = false WHERE ID = ?";
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put(1, id);
		
		try {
			DataModel.getInstance().eliminaEntita(query, params);
			System.out.println("Il biglietto è stato annullato!");
			// Prosegui con le view
			
			ViewController.getInstance().ShowDeletion_Modal("Il biglietto è stato annullato!");
			ViewController.getInstance().FindEntityView_Activate(ActiveEntity_Enum.Biglietto); // Torniamo alla pagina biglietti
		} catch(SQLException e) {
			// Comunica alla view che c'è stato un errore generico del DB
			ViewController.getInstance().ShowDBError_Modal();
		}
		
	}
}
