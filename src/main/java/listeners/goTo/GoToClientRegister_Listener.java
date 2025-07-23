package listeners.goTo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.ViewController;

public class GoToClientRegister_Listener implements MouseListener {
	
	// Chiamiamo metodi del ViewController per rendere visibile la view che vogliamo
	@Override
	public void mouseClicked(MouseEvent e) {
		ViewController.getInstance().ClientRegister_Activate();
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
