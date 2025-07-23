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
	
	ActiveEntity_Enum activeEntity; // Enum modificato attraverso specifici metodi "View_Activate" che ci serve per definire quali entità passare quando si accede a certe view.
	
	private ViewController() {
		views = new ArrayList<JFrame>();
		views.add(new ClientLogin_View()); // views.get(0) = View del login cliente
		views.add(new ClientRegister_View()); // views.get(1) = View della registrazione cliente
		views.add(new AdminLogin_View()); // views.get(2) = View del login amministratore
		// views.add(new FindEntity_View()); // views.get(3) = View della ricerca delle entità
		// views.add(new EntityDetails_View()); // views.get(4) = View dei dettagli dell'entità e per l'inserimento dell'entità (e per la cancellazione delle entità)
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
	
	public void ActivateView(int viewToActivate) {
		activeView = viewToActivate;
		views.get(viewToActivate).setVisible(true);
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
		
		ActivateView(0);
	}
	
	public void ClientLogin_ShowLoginError() {
		ClientLogin_View clientLoginForm = (ClientLogin_View) views.get(0);
		clientLoginForm.activateError();
	}
	
	// Metodi view Cliente Register
	
	public void ClientRegister_Activate() {
		
		DisableAllViews();
		
		ActivateView(1);
	}
	
	public void ClientRegister_ShowError(String errorMessage) {
		ClientRegister_View clientRegisterForm = (ClientRegister_View) views.get(1);
		clientRegisterForm.activateError(errorMessage);
	}
	
	// Metodi view Admin Login
	
	public void AdminLogin_Activate() {
		
		DisableAllViews();
		
		ActivateView(2);
	}
	
	public void AdminLogin_ShowLoginError() {
		AdminLogin_View adminLoginForm = (AdminLogin_View) views.get(2);
		adminLoginForm.activateError();
	}
	
	// Metodi view FindEntity_View
	
	public void FindEntityView_Activate(ActiveEntity_Enum activeEntity) {
		
		if(views.size() < 4) { // Abbiamo delineato un flusso ben preciso, sappiamo per certo che la finestra "FindEntity_View" sarà esattamente la quarta da creare.
			views.add(new FindEntity_View()); // Creiamo la finestra invece di instanziarla subito per non incorrere in errori con DataController, evitando dunque di fare accessi nulli.
		}
		
		DisableAllViews();
		
		SetActiveEntity_Enum(activeEntity);

		//FindEntity_View findEntity_View = (FindEntity_View) views.get(3);
		
		DataController.getInstance().clearEntities(); // Puliamo per sicurezza, anche se la lista viene pulita nel medesimo modo all'interno dei vari metodi
		
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
		}
		
		 // findEntity_View.SetEntityList(DataController.getInstance().getEntities());
		
		ActivateView(3);
	}
	
	// Metodi view EntityDetails_View
	
	public void EntityDetailsView_Activate(ActiveEntity_Enum activeEntity, int id) {
		
		if(views.size() < 5) { // Abbiamo delineato un flusso ben preciso, sappiamo per certo che la finestra "FindEntity_View" sarà esattamente la quinta da creare.
			views.add(new EntityDetails_View()); // Creiamo la finestra invece di instanziarla subito per non incorrere in errori con DataController, evitando dunque di fare accessi nulli.
		}
		
		DisableAllViews();
		
		SetActiveEntity_Enum(activeEntity);
		
		// EntityDetails_View entityDetails_view = (EntityDetails_View) views.get(4);
		
		DataController.getInstance().clearEntities(); // Puliamo per sicurezza, anche se la lista viene pulita nel medesimo modo all'interno dei vari.
		
		switch(activeEntity) {
		case ActiveEntity_Enum.Aereo:
			DataController.getInstance().trovaAereo(id);
			break;
		case ActiveEntity_Enum.Aeroporto:
			DataController.getInstance().trovaAeroporto(id);
			break;
		case ActiveEntity_Enum.Volo:
			DataController.getInstance().trovaVolo(id);
			break;
		case ActiveEntity_Enum.Biglietto:
			DataController.getInstance().trovaBiglietto(id);
			break;
	}
		
		// EntityDetails_View entityDetails_view.SetEntityData(DataController.getInstance().getEntities().get(0));
		
		ActivateView(4);
	}
}
