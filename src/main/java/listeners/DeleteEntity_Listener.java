package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.DataController;
import controller.ViewController;
import enums.ActiveEntity_Enum;

public class DeleteEntity_Listener implements ActionListener {
	
	ActiveEntity_Enum activeEntity;
	
	public DeleteEntity_Listener(ActiveEntity_Enum activeEntity) { // Passiamo l'entità che si dovrà cancellare o annullare
		this.activeEntity = activeEntity;
	}

	public void actionPerformed(ActionEvent e) {
		
		if(activeEntity == ActiveEntity_Enum.Volo) { // Se l'entita è Volo allora annulliamo il volo
			int idToCancel = ViewController.getInstance().EntityDetailsView_GetVoloId();
			DataController.getInstance().eliminaVolo(idToCancel); // Annulliamo il volo, non lo cancelliamo davvero!
		}
		
		if(activeEntity == ActiveEntity_Enum.Cliente) { // Se l'entità è Cliente allora eliminiamo Cliente
			DataController.getInstance().eliminaCliente(); // Non ci serve prendere l'id del cliente, è salvato all'interno di utenteSessione in DataController
		}
		
		if(activeEntity == ActiveEntity_Enum.Biglietto) { // Se l'entità è Biglietto allor annulliamo il biglietto
			int idToCancel = ViewController.getInstance().EntityDetailsView_GetBigliettoId();
			DataController.getInstance().eliminaBiglietto(idToCancel); // Annulliamo il volo, non lo cancelliamo davvero!
		}
		
	}

}
