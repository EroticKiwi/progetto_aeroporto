package listeners.find;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.ActiveEntity_Enum;

public class FindEntity_Listener implements ActionListener {
	
	ActiveEntity_Enum activeEntity;
	
	public FindEntity_Listener(ActiveEntity_Enum activeEntity) {
		this.activeEntity = activeEntity;
	}

	public void actionPerformed(ActionEvent e) {
		// ViewController.getInstance().FindEntityView_Activate(activeEntity);
	}

}
