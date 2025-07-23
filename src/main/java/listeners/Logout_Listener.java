package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.DataController;
import controller.ViewController;

public class Logout_Listener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		
		DataController.getInstance().clearUtenteSessione();
		ViewController.getInstance().Logout();
		
	}

}
