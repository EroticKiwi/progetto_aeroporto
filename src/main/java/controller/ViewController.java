package controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import enums.ActiveEntity_Enum;
import view.*;

public class ViewController {

	private static ViewController instance;
	ArrayList<JFrame> views; // Lista di tutte le view accessibili nel programma
	int activeView; // View attiva al momento
	
	ActiveEntity_Enum activeEntity;
	
	private ViewController() {
		views = new ArrayList<JFrame>();
		views.add(new ClientLogin_View()); // views.get(0) = View del login cliente
		views.add(new ClientRegister_View()); // views.get(1) = View della registrazione cliente
		views.add(new AdminLogin_View()); // views.get(2) = View del login amministratore
		// views.add(new FindEntity_View()); // views.get(3) = View della ricerca delle entità
		// views.add(new InsertEntity_View()); // views.get(4) = View dell'inserimento delle entità
		// views.add(new EntityDetails_View()); // views.get(5) = View dei dettagli dell'entità (e del cliente per l'eventuale cancellamento!)
	}
	
	public static ViewController getInstance() {
		if(instance == null) {
			instance = new ViewController();
		}
		
		return instance;
	}
	
	// Utility
	
	public JFrame GetView(int view) {
		if(view < 0 || view >= views.size()) {
			return views.get(0); // Per sicurezza mandiamo la pagina di login.
		}
		
		return views.get(view);
	}
	
	void DisableAllViews() { // Spegniamo tutte le view prima di attivarne una in particolare
		for(int i = 0; i < views.size(); i++) {
			views.get(i).setVisible(false);
		}
	}
	
	public void ShowDBError_Modal() {
		JOptionPane.showMessageDialog(views.get(activeView), "Errore di connessione al database. Riprova più tardi", "Si è verificato un errore!", JOptionPane.INFORMATION_MESSAGE);
		// Magari dopo questo, mandiamo l'utente alla pagina login!
	}
	
	public void SetActiveEntity_Enum(ActiveEntity_Enum activeEntity) { // Quando andiamo su una pagina "Trova" o "Inserisci" chiamiamo questo metodo ed impostiamo l'entità per cui stiamo facendo queste operazioni.
		this.activeEntity = activeEntity;
	}
	
	public ActiveEntity_Enum GetActiveEntity_Enum() {
		return activeEntity;
	}
	
	// Metodi view Cliente Login
	
	public void ClientLogin_Activate() {
		
		DisableAllViews();
		
		views.get(0).setVisible(true);
		activeView = 0;
	}
	
	public void ClientLogin_ShowLoginError() {
		ClientLogin_View clientLoginForm = (ClientLogin_View) views.get(0);
		clientLoginForm.activateError();
	}
	
	// Metodi view Cliente Register
	
	public void ClientRegister_Activate() {
		
		DisableAllViews();
		
		views.get(1).setVisible(true);
		activeView = 1;
	}
	
	public void ClientRegister_ShowError(String errorMessage) {
		ClientRegister_View clientRegisterForm = (ClientRegister_View) views.get(1);
		clientRegisterForm.activateError(errorMessage);
	}
	
	// Metodi view Admin Login
	
	public void AdminLogin_Activate() {
		
		DisableAllViews();
		
		views.get(2).setVisible(true);
		activeView = 2;
	}
	
	public void AdminLogin_ShowLoginError() {
		AdminLogin_View adminLoginForm = (AdminLogin_View) views.get(2);
		adminLoginForm.activateError();
	}
	
	// Metodi view FindEntity_View
	
	public void FindEntityView_Activate(ActiveEntity_Enum activeEntity) {
		
		DisableAllViews();
		
		SetActiveEntity_Enum(activeEntity);
		activeView = 3;
		/*
		 FindEntity_View findEntity_View = (FindEntity_View) views.get(3);
		
		switch(activeEntity) {
			case ActiveEntity_Enum.Aereo:
				DataController.getInstance().trovaTuttiAerei();
				break;
			case ActiveEntity_Enum.Aeroporto:
				DataController.getInstance().trovaTuttiAeroporti();
				break;
			case ActiveEntity_Enum.Volo:
				DataController.getInstance().trovaTuttiVoli();
				break;
			case ActiveEntity_Enum.Biglietto:
				DataController.getInstance().trovaTuttiBiglietti();
				break;
			case Default:
				DataController.getInstance().clearEntities;
				break;
		}
		
		 findEntity_View.SetEntityList(DataController.getInstance().getEntities());
		 */
	}
}
