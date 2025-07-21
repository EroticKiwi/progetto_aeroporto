package controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.*;

public class ViewController {

	private static ViewController instance;
	ArrayList<JFrame> views;
	int activeView;
	
	private ViewController() {
		views = new ArrayList<JFrame>();
		views.add(new ClientLoginForm()); // views.get(0) = View del login cliente
		views.add(new ClientRegisterForm()); // views.get(1) = View della registrazione cliente
		views.add(new AdminLoginForm()); // views.get(2) = View del login amministratore
	}
	
	public static ViewController getInstance() {
		if(instance == null) {
			instance = new ViewController();
		}
		
		return instance;
	}
	
	// Utility
	
	void DisableAllViews() { // Spegniamo tutte le view prima di attivarne una in particolare
		for(int i = 0; i < views.size(); i++) {
			views.get(i).setVisible(false);
		}
	}
	
	public void ShowDBError_Modal() {
		JOptionPane.showMessageDialog(views.get(activeView), "Errore di connessione al database. Riprova più tardi", "Si è verificato un errore!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	// Metodi view Cliente Login
	
	public void ClientLogin_Activate() {
		
		DisableAllViews();
		
		views.get(0).setVisible(true);
		activeView = 0;
	}
	
	public void ClientLogin_ShowLoginError() {
		ClientLoginForm clientLoginForm = (ClientLoginForm) views.get(0);
		clientLoginForm.activateError();
	}
	
	// Metodi view Cliente Register
	
	public void ClientRegister_Activate() {
		
		DisableAllViews();
		
		views.get(1).setVisible(true);
		activeView = 1;
	}
	
	public void ClientRegister_ShowError(String errorMessage) {
		ClientRegisterForm clientRegisterForm = (ClientRegisterForm) views.get(1);
		clientRegisterForm.activateError(errorMessage);
	}
	
	// Metodi view Admin Login
	
	public void AdminLogin_Activate() {
		
		DisableAllViews();
		
		views.get(2).setVisible(true);
		activeView = 2;
	}
	
	public void AdminLogin_ShowLoginError() {
		AdminLoginForm adminLoginForm = (AdminLoginForm) views.get(2);
		adminLoginForm.activateError();
	}
}
