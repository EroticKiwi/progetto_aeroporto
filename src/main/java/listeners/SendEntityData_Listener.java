package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.DataController;
import controller.ViewController;
import enums.ActiveEntity_Enum;
import view.EntityDetails_View;

public class SendEntityData_Listener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		
		InsertVolo();
		
	}
	
	void InsertVolo() {
		 
		 EntityDetails_View entityDetails_View = (EntityDetails_View) ViewController.getInstance().GetView(4);
		 
		 int id_volo = entityDetails_View.getVoloId();
		 int id_aereo;
		 int id_aeroporto_partenza;
		 int id_aeroporto_arrivo;
		 int postiLiberi;
		 float prezzo;
		 
		 try {
			    id_aereo = Integer.parseInt(entityDetails_View.getIdAereo_field());
			    
			    id_aeroporto_partenza = Integer.parseInt(entityDetails_View.getIdAeroportoPartenza_field());
			    id_aeroporto_arrivo = Integer.parseInt(entityDetails_View.getIdAeroportoArrivo_field());
			    postiLiberi = Integer.parseInt(entityDetails_View.getPostiLiberi_field());
			    prezzo = Float.parseFloat(entityDetails_View.getPrezzo_field());

			} catch (NumberFormatException e) { // Uno dei dati di cui abbiamo fatto il parse NON era una int corretto
			    ViewController.getInstance().EntityDetailsView_ShowError("Uno o più campi numerici contengono caratteri errati.");
			    return;
			}
		 
		 if(prezzo <= 0){
			ViewController.getInstance().EntityDetailsView_ShowError("Il prezzo non può essere minore o uguale a 0!");
		 	return;
		 }
		 
		 /*String nome_volo = DataController.getInstance().getNomeVolo(id_aeroporto_partenza, id_aeroporto_arrivo);
		 if(nome_volo.equals("")){
		 	return; // ViewController avrà già gestito l'errore lanciato dal DataController.
		 }*/
		 
		 String orario_partenza = entityDetails_View.getOrarioPartenza_field();
		 if(orario_partenza.equals("")){
			ViewController.getInstance().EntityDetailsView_ShowError("Inserire un orario valido.");
		 	return;
		 }
		 
		 String orario_arrivo = entityDetails_View.getOrarioArrivo_field();
		 if(orario_arrivo.equals("")){
			 ViewController.getInstance().EntityDetailsView_ShowError("Inserire un orario valido.");
		 	return;
		 }
		 		 
		 if(id_volo == -1) { // Inseriamo un nuovo volo
			 // DataController.getInstance().inserisciVolo(prezzo, id_aeroporto_partenza, id_aeroporto_arrivo, orario_partenza, orario_arrivo, id_aereo, postiLiberi);
			 ViewController.getInstance().ShowDBError_Modal();
			 ViewController.getInstance().Logout();
		 } else { // Stiamo modificando un volo già esistente
			 DataController.getInstance().modificaVolo(id_volo, prezzo, id_aeroporto_partenza, id_aeroporto_arrivo, orario_partenza, orario_arrivo, id_aereo, postiLiberi);
		 }
		 
	
	}
	
}
