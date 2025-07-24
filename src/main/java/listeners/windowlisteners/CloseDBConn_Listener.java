package listeners.windowlisteners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import controller.DataController;

public class CloseDBConn_Listener extends WindowAdapter {

    public void windowClosing(WindowEvent e) {
        // Qui chiami il tuo metodo personalizzato
        DataController.getInstance().closeDBConnection();
        e.getWindow().dispose(); // Chiudiamo la finestra!
    }
	
}
