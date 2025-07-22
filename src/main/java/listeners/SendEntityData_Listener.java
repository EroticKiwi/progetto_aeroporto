package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.DataController;
import controller.ViewController;
import enums.ActiveEntity_Enum;

public class SendEntityData_Listener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		
		if(ViewController.getInstance().GetActiveEntity_Enum() == ActiveEntity_Enum.Aereo) {
			InsertAereo();
		}
		
		if(ViewController.getInstance().GetActiveEntity_Enum() == ActiveEntity_Enum.Aeroporto) {
			InsertAeroporto();
		}
		
		if(ViewController.getInstance().GetActiveEntity_Enum() == ActiveEntity_Enum.Volo) {
			InsertVolo();
		}
		
		if(ViewController.getInstance().GetActiveEntity_Enum() == ActiveEntity_Enum.Biglietto) {
			//InsertBiglietto();
		}
		
	}

	void InsertAereo() {
		/*
		
		InsertEntity_View insertEntity_View = (InsertEntity_View) ViewController.getInstance().GetView(3);
		
		int id_aeroporto_residenza = insertEntity_View.GetId_Aeroporto_Residenza();
		
		int capienza = insertEntity_View.GetCapienza();
		
		if(capienza < 5) {
			ViewController.getInstance().InsertEntity_ShowError("L'aereo deve avere una capienza minima pari a 5!");
			return;
		}
		
		String modello = insertEntity_View.GetModello();
		
		if(modello.equals("")) {
			ViewController.getInstance().InsertEntity_ShowError("Il campo 'Modello' non può essere vuoto!");
			return;
		}
		
		DataController.getInstance().inserisciAereo(id_aeroporto_residenza, capienza, modello);
	
		*/
	}
	
	void InsertAeroporto() {
		/*
		 
		 InsertEntity_View insertEntity_View = (InsertEntity_View) ViewController.getInstance().GetView(3);
		 
		 int numeroPiste = insertEntity_View.GetNumeroPiste();
		 if(numeroPiste <= 0){
		 	ViewController.getInstance().InsertEntity_ShowError("Il numero di piste non può essere pari o inferiore a 0!");
		 	return;
		 }
		 
		 String citta = insertEntity_View.GetCitta();
		 if(citta.equals("")){
		 	ViewController.getInstance().InsertEntity_ShowError("Il campo 'Città' non può essere vuoto!");
		 	return;
		 }
		 
		 String nazione = insertEntity_View.GetNazione();
		 if(nazione.equals("")){
		 	ViewController.getInstance().InsertEntity_ShowError("Il campo 'Nazione' non può essere vuoto!")
			return;
		 }
		 
		 DataController.getInstance().inserisciAeroporto(numeroPiste, citta, nazione);
		 
		 */
	}
	
	void InsertVolo() {
		
		/*
		 
		 InsertEntity_View insertEntity_View = (InsertEntity_View) ViewController.getInstance().GetView(3);
		 
		 int id_aereo = insertEntity_View.GetId_Aereo();
		 
		 int id_aeroporto_partenza = insertEntity_View.GetId_Aeroporto_Partenza();
		 
		 int id_aeroporto_arrivo = insertEntity_View.GetId_Aeroporto_Arrivo();
		 
		 int postiLiberi = DataController.getInstance().getCapienzaAereo(id_aereo);
		 
		 float prezzo = insertEntity_View.GetPrezzo();
		 
		 if(prezzo <= 0){
		 	ViewController.getInstance().InsertEntity_ShowError("Il prezzo non deve essere pari o inferiore a 0!");
		 	return;
		 }
		 
		 String nome_volo = DataController.getInstance().getNomeVolo(id_aeroporto_partenza, id_aeroporto_arrivo);
		 if(nome_volo.equals("")){
		 	return; // ViewController avrà già gestito l'errore lanciato dal DataController.
		 }
		 
		 String orario_partenza = insertEntity_View.GetOrario_Partenza();
		 if(orario_partenza.equals("")){
		 	ViewController.getInstance().InsertEntity_ShowError("Il campo 'Orario di partenza" non può essere vuoto!");
		 	return;
		 }
		 
		 String orario_arrivo = insertEntity_View.GetOrario_Arrivo();
		 if(orario_arrivo.equals("")){
		 	ViewController.getInstance().InsertEntity_ShowError("Il campo 'Orario di arrivo" non può essere vuoto!");
		 	return;
		 }
		 
		 boolean valido = true;
		 
		 DataController.getInstance().inserisciVolo(id_aereo, id_aeroporto_partenza, id_aeroporto_arrivo, postiLiberi, prezzo, nome_volo, orario_partenza, orario_arrivo, valido);
		 */
		
	}
	
}
