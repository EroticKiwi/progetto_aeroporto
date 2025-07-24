package listeners;

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

	@Override
	public void mouseClicked(MouseEvent e) {
		
		int index = entities.locationToIndex(e.getPoint()); // prendiamo l'entità presente all'indice dove abbiamo cliccato
		
		if(index < 0) { // Se per qualche motivo la lista ritorna un indice non esistente ci fermiamo
			return;
		}
		
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
