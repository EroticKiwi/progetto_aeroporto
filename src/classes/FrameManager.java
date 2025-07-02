package classes;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import frames.FormFrame;
import frames.MainFrame;
import utility_classes.ComponentCreator;

public class FrameManager {
	
	private static FrameManager Instance;
	
	ImageIcon programIcon;
	
	JDialog loginDialog;
	boolean hasLoggedIn = false;
	
	MainFrame mainFrame;
	FormFrame formFrame;
	
	JFrame activeFrame;
	int activeFrame_Index;
		
	private FrameManager() {
		ShowLoginDialog();
	}
	
	public static FrameManager getInstance() {
		if(Instance == null) {
			Instance = new FrameManager();
		}
		return Instance;
	}
	
	void ShowLoginDialog() {
		loginDialog = ComponentCreator.CreateLoginDialog();
	}
	
	void InitializeFrames() {
		programIcon = new ImageIcon("imgs\\hehehehehe.jpg");
				
		mainFrame = ComponentCreator.CreateMainFrame(1280, 720);
		formFrame = ComponentCreator.CreateFormFrame(1280, 720);
		
		mainFrame.setIconImage(programIcon.getImage());
		formFrame.setIconImage(programIcon.getImage());
		
		formFrame.setVisible(false);
		
		ActivateFrame(0);
		activeFrame_Index = 0;
	}
	
	public void ActivateFrame(int frameIndex) {
		
		if(frameIndex == activeFrame_Index) {
			return;
		}
		
		if(activeFrame != null) {
			activeFrame.setVisible(false);
		}
		
		switch(frameIndex) {
			case 0:
				activeFrame = mainFrame;
				break;
			case 1:
				activeFrame = formFrame;
				break;
			default:
				activeFrame = mainFrame;
				break;
		}
		
		activeFrame.setVisible(true);
		activeFrame_Index = frameIndex;
	}
}
