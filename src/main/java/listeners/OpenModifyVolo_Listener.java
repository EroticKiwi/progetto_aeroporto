package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.DataController;
import controller.ViewController;
import model.Volo;

public class OpenModifyVolo_Listener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		
		Volo volo = (Volo) DataController.getInstance().getEntityObj();
		ViewController.getInstance().EntityDetailsView_ActivateModifyPanel(volo);
		
	}

	
	
}
