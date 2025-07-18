package controller;

import java.util.ArrayList;

import javax.swing.JFrame;

import view.*;

public class ViewController {

	private static ViewController instance;
	ArrayList<JFrame> views;
	
	private ViewController() {
		views = new ArrayList<JFrame>();
		views.add(new ClientLoginForm()); // views.get(0) = View del login cliente
		views.add(new ClientRegisterForm()); // views.get(1) = View della registrazione cliente
		// views.add(new AdminLoginForm()); // views.get(2) = View del login amministratore
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
	
	// Metodi view Cliente Login
	
	public void ClientLogin_Activate() {
		
		DisableAllViews();
		
		views.get(0).setVisible(true);
	}
	
	public void ClientLogin_ShowLoginError() {
		ClientLoginForm clientLoginForm = (ClientLoginForm) views.get(0);
		clientLoginForm.activateError();
	}
	
	// Metodi view Cliente Register
	
	public void ClientRegister_Activate() {
		
		DisableAllViews();
		
		views.get(1).setVisible(true);
	}
	
	public void ClientRegister_ShowError(String errorMessage) {
		ClientRegisterForm clientRegisterForm = (ClientRegisterForm) views.get(1);
		clientRegisterForm.activateError(errorMessage);
	}
}
