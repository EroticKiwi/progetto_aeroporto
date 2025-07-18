package listeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import controller.DataController;
import view.*;

public class SendUserData_Listener implements ActionListener{
	
	JFrame parent; // Può essere sia Login/Registrazione Cliente che Login Amministratore
	
	public SendUserData_Listener(JFrame parent) {
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(parent instanceof ClientLoginForm) { // Se questo listener viene dalla pagina login dell'utente
			SendClientLoginData();
		}
		
		if(parent instanceof ClientRegisterForm) {
			SendClientRegisterData();
		}
		
	}
	
	void SendClientLoginData() { // Mandiamo i dati relativi al login dell'utente
		
		ClientLoginForm clientLogin = (ClientLoginForm) parent;
		String email = clientLogin.getEmail();
		String password = clientLogin.getPassword();
		
		DataController.getInstance().trovaCliente(email, password);
	}
	
	void SendClientRegisterData() { // Mandiamo i dati relativi alla registrazione dell'utente
		
		ClientRegisterForm clientRegister = (ClientRegisterForm) parent;
		String nome = clientRegister.getNome();
		String cognome = clientRegister.getCognome();
		String email = clientRegister.getEmail();
		String password = clientRegister.getPassword();
		String metodoPagamento = clientRegister.getMetodoPagamento();
		
		DataController.getInstance().inserisciCliente(nome, cognome, email, password, metodoPagamento);
	}
}
