package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import controller.DataController;
import controller.ViewController;
import enums.ActiveEntity_Enum;
import model.*;

// POTREMMO CREARE UNA CLASSE ASTRATTA PER QUESTI LISTENER!
public class FindEntity_Listener implements MouseListener { // Viene chiamata quando si preme su un entità specifica e si vuole aprire EntityDetails_View

	ActiveEntity_Enum activeEntity; // Enum per il tipo di entità
	JList entities; // Lista di entità dallo scrollpanel
	
	public FindEntity_Listener(ActiveEntity_Enum activeEntity, JList entities) {
		this.activeEntity = activeEntity;
		this.entities = entities;
	}
	
	public FindEntity_Listener() { // Chiamato per il tasto "il mio account". Non gli passiamo niente, sappiamo già che se abbiamo premuto su quel tasto dobbiamo visualizzare i dati del cliente
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(entities == null) {
			Cliente cliente = (Cliente) DataController.getInstance().getUtenteSessione();
			ViewController.getInstance().EntityDetailsView_Activate(ActiveEntity_Enum.Cliente, cliente);
			return;
		}
		
		int index = entities.locationToIndex(e.getPoint()); // prendiamo l'entità presente all'indice dove abbiamo cliccato
		
		Object entity = entities.getModel().getElementAt(index); // Istanziamo un entità generica e dopo controlliamo precisamente di che entità si tratta
		
		ViewController.getInstance().EntityDetailsView_Activate(activeEntity, entity);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
