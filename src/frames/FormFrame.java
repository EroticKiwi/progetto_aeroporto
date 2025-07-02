package frames;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import actions.ActivateFormFrame_ActionListener;
import actions.ActivateMainFrame_ActionListener;
import custom_components.JRoundedButton;
import utility_classes.ColorUtility;
import utility_classes.ComponentCreator;

public class FormFrame extends JFrame{

	JPanel topBar;
	JRoundedButton[] topButtons;
	
	JPanel contentPanel;
	
	public FormFrame() {
		Initialize();
	}
	
	public void Initialize() {
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		CreateTopBar(gbc);
		
		CreateContentPanel(gbc);
	}
	
	void CreateTopBar(GridBagConstraints gbc) {
		topBar = ComponentCreator.CreatePanel();
		topBar.setBackground(Color.DARK_GRAY);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 20;
		gbc.anchor = GridBagConstraints.NORTH;
				
		this.add(topBar, gbc);
		
		topButtons = new JRoundedButton[4];
		int length = (topButtons.length * 2) + 1;
		
		int buttonIndex = 0;
		
		for(int i = 0; i < length; i++) {
			if(i % 2 == 0) { // Creiamo un pannello filler
				JPanel filler = ComponentCreator.CreatePanel();
				filler.setBackground(topBar.getBackground());
				topBar.add(filler);
				continue;
			}
			
			int buttonIndexVisual = buttonIndex + 1;
			topButtons[buttonIndex] = new JRoundedButton("Finestra " + buttonIndexVisual);
			topBar.add(topButtons[buttonIndex]);
			buttonIndex++;
		}
		
		topButtons[0].addActionListener(new ActivateMainFrame_ActionListener());
		topButtons[1].addActionListener(new ActivateFormFrame_ActionListener());
	}
	
	void CreateContentPanel(GridBagConstraints gbc) {
		contentPanel = ComponentCreator.CreatePanel();
		contentPanel.setBackground(ColorUtility.DarkRed);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 100;
		gbc.weighty = 80;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH; // Riempie il panel sia in width che height
		
		this.add(contentPanel, gbc);
	}
	
}
