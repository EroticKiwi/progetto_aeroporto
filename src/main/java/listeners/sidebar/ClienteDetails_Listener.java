package listeners.sidebar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.DataController;
import controller.ViewController;
import enums.ActiveEntity_Enum;
import model.Cliente;

public class ClienteDetails_Listener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Cliente cliente = (Cliente) DataController.getInstance().getUtenteSessione();
		ViewController.getInstance().EntityDetailsView_Activate(ActiveEntity_Enum.Cliente, cliente);
		
	}

}
