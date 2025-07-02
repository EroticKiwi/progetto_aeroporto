package dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import custom_components.JRoundedButton;
import utility_classes.ComponentCreator;

public class LoginDialog extends JDialog{

	JLabel usernameLabel;
	JLabel passwordLabel;
	
	JTextArea usernameTextArea;
	JPasswordField passwordTextArea;
	
	JRoundedButton submitButton;
	
	String username;
	String password;
	
	public LoginDialog() {
		Initialize();
	}
	
	void Initialize() {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		CreateLabelPanel(gbc);
		CreateUsernamePanel(gbc);
		CreatePasswordPanel(gbc);
		CreateSubmitPanel(gbc);

		// Filler panel label
		CreateFillerPanel(gbc, 0, 0);
		CreateFillerPanel(gbc, 2, 0);
		// Filler panel username
		CreateFillerPanel(gbc, 0, 1);
		CreateFillerPanel(gbc, 2, 1);
		// Filler panel password
		CreateFillerPanel(gbc, 0, 2);
		CreateFillerPanel(gbc, 2, 2);
		// Filler panel button
		CreateFillerPanel(gbc, 0, 3);
		CreateFillerPanel(gbc, 2, 3);
		
		this.setLocationRelativeTo(null);
		this.pack();
	}
	
	void CreateLabelPanel(GridBagConstraints gbc) {
		JPanel labelPanel = ComponentCreator.CreatePanel();
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		this.add(labelPanel, gbc);
		
		JLabel dialogLabel = new JLabel("Inserire username e password per effettuare l'accesso");
		labelPanel.add(dialogLabel);
	}
	
	void CreateUsernamePanel(GridBagConstraints gbc) {
		JPanel usernamePanel = ComponentCreator.CreatePanel();
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		this.add(usernamePanel, gbc);
		
		usernameLabel = new JLabel("Username: ");
		usernamePanel.add(usernameLabel);
		
		usernameTextArea = new JTextArea(1, 15);
		usernamePanel.add(usernameTextArea);

	}
	
	void CreatePasswordPanel(GridBagConstraints gbc) {
		JPanel passwordPanel = ComponentCreator.CreatePanel();

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		this.add(passwordPanel, gbc);
		
		passwordLabel = new JLabel("Password: ");
		passwordPanel.add(passwordLabel);
		
		passwordTextArea = new JPasswordField();
		passwordTextArea.setColumns(usernameTextArea.getColumns());
		passwordPanel.add(passwordTextArea);

	}
	
	void CreateSubmitPanel(GridBagConstraints gbc) {
		JPanel submitPanel = ComponentCreator.CreatePanel();
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		
		this.add(submitPanel, gbc);
		
		submitButton = new JRoundedButton("Accedi");
		submitPanel.add(submitButton);
	}
	
	void CreateFillerPanel(GridBagConstraints gbc, int gridx, int gridy) {
		JPanel filler = ComponentCreator.CreatePanel();
		
		gbc.gridx = gridx;
		gbc.gridy = gridy;
				
		this.add(filler, gbc);
	}
	
}
