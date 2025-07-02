package utility_classes;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class JPanelUtility {

	public static void SetVerticalAlignment(JPanel panel) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	}
	
	public static void SetBackgroundImage(JPanel panel) {
		panel.setForeground(Color.red);
	}
	
}
