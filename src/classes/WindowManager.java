package classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import custom_components.*;
import utility_classes.ColorUtility;
import utility_classes.ComponentCreator;

public class WindowManager {
	
	JFrame firstWindow;
	JPanel firstPanel;
	JDialog dialog;
	JLabel label;
	JButton[] buttons;
	
	public WindowManager() {
		CreateSplitWindow();
	}
	
	void CreateFirstWindow() {
		
		firstWindow = ComponentCreator.CreateFrame();
		
		firstPanel = ComponentCreator.CreatePanel();
		label = new JLabel("Sto usando swing!");
		firstPanel.add(label);
		
		firstWindow.add(firstPanel);
		firstWindow.pack(); // .pack() ridimensiona il frame (finestra) per adattarsi ai componenti che contiene.
		
	}
	
	void CreateWindow_BorderLayout() {
		firstWindow = ComponentCreator.CreateFrame(200, 200);
		JLabel[] labels = new JLabel[5];
		
		labels[0] = ComponentCreator.CreateJLabel_Centered("Nord");
		labels[0].setForeground(Color.RED);
		labels[1] = ComponentCreator.CreateJLabel_Centered("Sud");
		labels[1].setForeground(Color.CYAN);
		labels[2] = ComponentCreator.CreateJLabel_Centered("Est");
		labels[2].setForeground(Color.GREEN);
		labels[3] = ComponentCreator.CreateJLabel_Centered("Ovest");
		labels[3].setForeground(Color.YELLOW);
		labels[4] = ComponentCreator.CreateJLabel_Centered("Centro");
		labels[4].setForeground(Color.ORANGE);
		
		firstWindow.add(labels[0], BorderLayout.NORTH);
		firstWindow.add(labels[1], BorderLayout.SOUTH);
		firstWindow.add(labels[2], BorderLayout.EAST);
		firstWindow.add(labels[3], BorderLayout.WEST);
		firstWindow.add(labels[4], BorderLayout.CENTER);
	}
	
	void CreateMainWindow() {
		
		firstWindow = ComponentCreator.CreateFrame(400, 400);
		
		firstPanel = ComponentCreator.CreatePanel_VerticalAlignment();
		firstPanel.setBackground(Color.red);
		
		buttons = new JRoundedButton[4];
		
		for(int i = 0; i < buttons.length; i++) {
			int index = i+1;
			buttons[i] = ComponentCreator.CreateRoundedButton("Finestra " + index);
			buttons[i].setBackground(ColorUtility.Brown);
			buttons[i].setForeground(ColorUtility.Azure);
			firstPanel.add(buttons[i]);
		}
		
		firstWindow.add(firstPanel, BorderLayout.WEST);
	}

	JPanel leftPanel;
	JPanel rightPanel;
	JRoundedButton[] buttons_leftPanel;
	
	void CreateSplitWindow() {
		
		firstWindow = ComponentCreator.CreateFrame_GridBagLayout(1280, 720);
		
		leftPanel = ComponentCreator.CreatePanel();
		rightPanel = ComponentCreator.CreatePanel();
		
		leftPanel.setName("Left Panel");
		rightPanel.setName("Right Panel");
		
		leftPanel.setBackground(Color.orange);
		rightPanel.setBackground(Color.black);
		
		// Creazione dei constraints e assegnazione dei valori default che saranno condivisi tra i JPanel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weighty = 0.7; // Assicura che entrambi i panel occupino verticalmente il 70% della finestra
		gbc.fill = GridBagConstraints.BOTH; // Assicura che i constraints vengano rispettati
		gbc.insets = new Insets(0, 0, 0, 0);
		
		// Posizionamento del panel a sinistra
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 5; // Assicura che il panel di sinistra occupi il 30% della finestra in orizzontale
		gbc.anchor = GridBagConstraints.LINE_START;
		firstWindow.add(leftPanel, gbc);
		
		// Posizionamento del panel a sinistra
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 95; // Assicura che il panel di destra occupi il 70% della finestra in orizzontale
		gbc.anchor = GridBagConstraints.LINE_END;
		firstWindow.add(rightPanel, gbc);
		
		// gbc è come una matrice. Tramite gridx e gridy scorriamo a che RIGA e COLONNA andrà posizionato l'oggetto.
		PopulateLeftPanel(gbc);
	}
	
	void PopulateLeftPanel(GridBagConstraints gbc) {
		
		leftPanel.setLayout(new GridBagLayout());
		
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.insets = new Insets(30, 0, 25, 0);
		
		buttons_leftPanel = new JRoundedButton[4];
		
		for(int i = 0; i < buttons_leftPanel.length; i++) {
			int index = i+1;
			gbc.gridy = i;
			
			JPanel filler = new JPanel();
			filler.setBackground(leftPanel.getBackground());
			gbc.weightx = 25;
			gbc.gridx = 0;
			
			leftPanel.add(filler, gbc);
			
			buttons_leftPanel[i] = ComponentCreator.CreateRoundedButton("Finestra " + index);
			buttons_leftPanel[i].setBackground(ColorUtility.Brown);
			
			gbc.weightx = 50;
			gbc.gridx = 1;
			gbc.gridy = i;
						
			leftPanel.add(buttons_leftPanel[i], gbc);
		
			filler = new JPanel();
			filler.setBackground(leftPanel.getBackground());
			gbc.weightx = 25;
			gbc.gridx = 2;
			
			leftPanel.add(filler, gbc);
		}
	}
	
	void SetLeftPanel_Buttons() {
		buttons_leftPanel[0].setAction(null);
	}
	
}
