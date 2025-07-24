package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.DataController;
import controller.ViewController;

public class BuyBiglietto_Listener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		
		int idVolo = ViewController.getInstance().EntityDetailsView_GetVoloId();
		boolean validita = ViewController.getInstance().EntityDetailsView_GetVoloValidita();
		
		DataController.getInstance().inserisciBiglietto(idVolo, validita);
		
	}

}
