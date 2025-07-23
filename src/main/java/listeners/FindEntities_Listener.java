package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ViewController;
import enums.ActiveEntity_Enum;

public class FindEntities_Listener implements ActionListener { // Viene chiamata dalla sidebar
	
	ActiveEntity_Enum activeEntity;
	
	public FindEntities_Listener(ActiveEntity_Enum activeEntity) {
		this.activeEntity = activeEntity;
	}

	public void actionPerformed(ActionEvent e) {
		ViewController.getInstance().FindEntityView_Activate(activeEntity);
	}

}
