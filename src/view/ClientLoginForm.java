package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import listeners.SendUserData_Listener;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Window.Type;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JButton;

public class ClientLoginForm extends JFrame {

	private JPanel contentPanel;
	
	public JTextField emailField;
	public JTextField passwordField;
	
	private JLabel loginErrorField;
	
	public ClientLoginForm() {
		setTitle("Login Cliente - Aeroporto");
		setResizable(false);
		setAlwaysOnTop(true);
		setBackground(new Color(192, 192, 192));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 419);
		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel loginText = new JLabel("Effettua l'accesso");
		loginText.setBounds(21, 5, 744, 44);
		contentPanel.add(loginText);
		loginText.setHorizontalAlignment(SwingConstants.CENTER);
		loginText.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		
		JPanel loginFields_Panel = new JPanel();
		loginFields_Panel.setBackground(new Color(192, 192, 192));
		loginFields_Panel.setBounds(15, 67, 762, 128);
		contentPanel.add(loginFields_Panel);
		GridBagLayout gbl_loginFields_Panel = new GridBagLayout();
		gbl_loginFields_Panel.columnWidths = new int[] {200, 544};
		gbl_loginFields_Panel.rowHeights = new int[] {42, 42};
		gbl_loginFields_Panel.columnWeights = new double[]{0.0, 0.0};
		gbl_loginFields_Panel.rowWeights = new double[]{0.0, 0.0};
		loginFields_Panel.setLayout(gbl_loginFields_Panel);
		
		JLabel emailLabel = new JLabel("Inserisci l'email");
		emailLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 20));
		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.SOUTH;
		gbc_emailLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 0;
		loginFields_Panel.add(emailLabel, gbc_emailLabel);
		
		emailField = new JTextField();
		emailLabel.setLabelFor(emailField);
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.fill = GridBagConstraints.BOTH;
		gbc_emailField.insets = new Insets(0, 0, 5, 0);
		gbc_emailField.gridx = 1;
		gbc_emailField.gridy = 0;
		loginFields_Panel.add(emailField, gbc_emailField);
		emailField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Inserisci la password");
		passwordLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 20));
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.SOUTH;
		gbc_passwordLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordLabel.insets = new Insets(0, 0, 0, 5);
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 1;
		loginFields_Panel.add(passwordLabel, gbc_passwordLabel);
		
		passwordField = new JPasswordField();
		passwordLabel.setLabelFor(passwordField);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setColumns(10);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		loginFields_Panel.add(passwordField, gbc_passwordField);
		
		loginErrorField = new JLabel("La combinazione di email e password potrebbe essere errata!");
		loginErrorField.setHorizontalAlignment(SwingConstants.CENTER);
		loginErrorField.setForeground(new Color(255, 0, 0));
		loginErrorField.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		loginErrorField.setBounds(128, 192, 530, 21);
		loginErrorField.setVisible(false);
		contentPanel.add(loginErrorField);
		
		JButton loginButton = new JButton("Accedi");
		loginButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		loginButton.setBounds(293, 236, 200, 50);
		SendUserData_Listener listener = new SendUserData_Listener(this);
		loginButton.addActionListener(listener);
		contentPanel.add(loginButton);
		
		JLabel registerField = new JLabel("Non hai ancora un account? Registrati!");
		registerField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		registerField.setHorizontalAlignment(SwingConstants.CENTER);
		registerField.setBounds(143, 296, 500, 20);
		Font font = registerField.getFont();
		contentPanel.add(registerField);
		
		JLabel amministratoreField = new JLabel("Sei un amministratore? Accedi!");
		amministratoreField.setHorizontalAlignment(SwingConstants.CENTER);
		amministratoreField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		amministratoreField.setBounds(143, 321, 500, 20);
		contentPanel.add(amministratoreField);
	}
	
	public void activateError() { // Attiva il testo di errore!
		loginErrorField.setVisible(true);
	}
	
	public String getEmail() {
		return emailField.getText();
	}
	
	public String getPassword() {
		return passwordField.getText();
	}
}
