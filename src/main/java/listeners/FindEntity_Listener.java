package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ViewController;
import enums.ActiveEntity_Enum;

// POTREMMO CREARE UNA CLASSE ASTRATTA PER QUESTI LISTENER!
public class FindEntity_Listener implements ActionListener { // Viene chiamata quando si preme su un entità specifica e si vuole aprire EntityDetails_View

	ActiveEntity_Enum activeEntity; // Enum per il tipo di entità
	int entityId; // id dell'entità specifica da passare poi per l'apertura della pagina dettagli entità
	
	public FindEntity_Listener(ActiveEntity_Enum activeEntity, int entityId) {
		this.activeEntity = activeEntity;
	}
	
	public void actionPerformed(ActionEvent e) {
		ViewController.getInstance().EntityDetailsView_Activate(activeEntity, entityId);
	}

}
