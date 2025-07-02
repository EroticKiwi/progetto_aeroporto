package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import classes.FrameManager;

public class ActivateMainFrame_ActionListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		FrameManager.getInstance().ActivateFrame(0);
	}
	
}
