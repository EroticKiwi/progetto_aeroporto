package listeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import controller.DataController;
import controller.ViewController;
import view.*;

public class LoginRegister_Listener implements ActionListener{
	
	JFrame parent; // Può essere sia Login/Registrazione Cliente che Login Amministratore
	
	public LoginRegister_Listener(JFrame parent) {
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(parent instanceof ClientLogin_View) { // Se questo listener viene dalla pagina login dell'utente
			SendClientLoginData();
		}
		
		if(parent instanceof ClientRegister_View) {
			SendClientRegisterData();
		}
		
		if(parent instanceof AdminLogin_View) {
			SendAdminLoginData();
		}
		
	}
	
	void SendClientLoginData() { // Mandiamo i dati relativi al login dell'utente
		
		ClientLogin_View clientLogin = (ClientLogin_View) parent;
		String email = clientLogin.getEmail();
		String password = clientLogin.getPassword();
		
		DataController.getInstance().trovaCliente(email, password);
	}
	
	void SendClientRegisterData() { // Mandiamo i dati relativi alla registrazione dell'utente
		
		ClientRegister_View clientRegister = (ClientRegister_View) parent;
		String nome = clientRegister.getNome();
		if(nome.equals("")) {
			ViewController.getInstance().ClientRegister_ShowError("Il campo 'Nome' non può essere vuoto!");
			return;
		}
		String cognome = clientRegister.getCognome();
		if(cognome.equals("")) {
			ViewController.getInstance().ClientRegister_ShowError("Il campo 'Cognome' non può essere vuoto!");
			return;
		}
		String email = clientRegister.getEmail();
		if(email.equals("")) {
			ViewController.getInstance().ClientRegister_ShowError("Il campo 'Email' non può essere vuoto!");
			return;
		}
		String password = clientRegister.getPassword();
		if(password.equals("")) {
			ViewController.getInstance().ClientRegister_ShowError("Il campo 'Password' non può essere vuoto!");
			return;
		}
		String metodoPagamento = clientRegister.getMetodoPagamento();
		if(metodoPagamento.equals("")) {
			ViewController.getInstance().ClientRegister_ShowError("Il campo 'Metodo di Pagamento' non può essere vuoto!");
			return;
		}
		
		DataController.getInstance().inserisciCliente(nome, cognome, email, password, metodoPagamento);
	}

	void SendAdminLoginData() { // Mandiamo i dati relativi al login dell'amministratore
		
		AdminLogin_View adminLoginForm = (AdminLogin_View) parent;
		String email = adminLoginForm.getEmail();
		String password = adminLoginForm.getPassword();
		int chiaveAccesso = 0;
		
		try { // si mette nel try catch siccome è possibile che l'utente inserisca dei caratteri non numerici!
			   chiaveAccesso = Integer.parseInt(adminLoginForm.getChiaveAccesso());
			} catch (NumberFormatException e) {
			   chiaveAccesso = 0;
		}
		
		DataController.getInstance().trovaAmministratore(email, password, chiaveAccesso);;
	}
}
