package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import listeners.SendUserData_Listener;
import listeners.goTo.GoToClientLogin_Listener;
import listeners.goTo.GoToClientRegister_Listener;

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

public class AdminLoginForm extends JFrame {

	private JPanel contentPanel;
	
	public JTextField emailField;
	public JTextField passwordField;
	public JTextField chiaveAccessoField;

	private JLabel loginErrorField;
	
	public AdminLoginForm() {
		setTitle("Login Amministratore - Aeroporto");
		setResizable(false);
		setAlwaysOnTop(true);	// La pagina della finestra sta sempre "davanti" alle altre
		setBackground(new Color(192, 192, 192));	// colore grigio dello sfondo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// chiude il programma quando schiacciamo la X
		setSize(800, 400);	// Selezioniamo la dimensione della finestra
		setLocationRelativeTo(null); // Centra la finestra sullo schermo
		
		contentPanel = new JPanel();	// primo contenitore
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel loginText = new JLabel("Effettua l'accesso"); // Scrive sulla finestra
		loginText.setBounds(21, 5, 744, 44);
		contentPanel.add(loginText);
		loginText.setHorizontalAlignment(SwingConstants.CENTER);
		loginText.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		
		JPanel loginFields_Panel = new JPanel();	// secondo contenitore
		loginFields_Panel.setBackground(new Color(192, 192, 192));
		loginFields_Panel.setBounds(15, 67, 744, 128);
		contentPanel.add(loginFields_Panel);
		GridBagLayout gbl_loginFields_Panel = new GridBagLayout();
		gbl_loginFields_Panel.columnWidths = new int[] {200, 544};
		gbl_loginFields_Panel.rowHeights = new int[] {42, 42, 42};
		gbl_loginFields_Panel.columnWeights = new double[]{0.0, 1.0};
		gbl_loginFields_Panel.rowWeights = new double[]{0.0, 0.0, 0.0};
		loginFields_Panel.setLayout(gbl_loginFields_Panel);
		
		JLabel emailLabel = new JLabel("Email:");
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
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 20));
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.SOUTH;
		gbc_passwordLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 1;
		loginFields_Panel.add(passwordLabel, gbc_passwordLabel);
		
		passwordField = new JPasswordField();
		passwordLabel.setLabelFor(passwordField);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setColumns(10);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		loginFields_Panel.add(passwordField, gbc_passwordField);
		
		JLabel chiaveAccessoLabel = new JLabel("Chiave d'accesso");
		chiaveAccessoLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 20));
		GridBagConstraints gbc_chiaveAccessoLabel = new GridBagConstraints();
		gbc_chiaveAccessoLabel.anchor = GridBagConstraints.SOUTH;
		gbc_chiaveAccessoLabel.insets = new Insets(0, 0, 0, 5);
		gbc_chiaveAccessoLabel.gridx = 0;
		gbc_chiaveAccessoLabel.gridy = 2;
		loginFields_Panel.add(chiaveAccessoLabel, gbc_chiaveAccessoLabel);
		
		chiaveAccessoField = new JTextField();
		chiaveAccessoField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_chiaveAcessoField = new GridBagConstraints();
		gbc_chiaveAcessoField.fill = GridBagConstraints.BOTH;
		gbc_chiaveAcessoField.gridx = 1;
		gbc_chiaveAcessoField.gridy = 2;
		loginFields_Panel.add(chiaveAccessoField, gbc_chiaveAcessoField);
		chiaveAccessoField.setColumns(10);
		
		loginErrorField = new JLabel("La combinazione di email, password e chiave d'accesso potrebbe essere errata!");
		loginErrorField.setHorizontalAlignment(SwingConstants.CENTER);
		loginErrorField.setForeground(new Color(255, 0, 0));
		loginErrorField.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		loginErrorField.setBounds(43, 205, 700, 21);
		loginErrorField.setVisible(false);
		contentPanel.add(loginErrorField);
		
		JButton loginButton = new JButton("Accedi");
		loginButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		loginButton.setBounds(293, 247, 200, 50);
		SendUserData_Listener listener = new SendUserData_Listener(this);
		loginButton.addActionListener(listener);
		contentPanel.add(loginButton);
		
		JLabel clientLabel = new JLabel("<html><u>Sei un cliente? Accedi!</u></html>");
		clientLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		clientLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clientLabel.setBounds(143, 313, 500, 20);
		GoToClientLogin_Listener clientLoginListener = new GoToClientLogin_Listener();
		clientLabel.addMouseListener(clientLoginListener);
		contentPanel.add(clientLabel);
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
	
	public String getChiaveAccesso() {
		return chiaveAccessoField.getText();
	}
}
