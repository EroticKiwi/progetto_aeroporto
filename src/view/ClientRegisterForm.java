package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import listeners.SendUserData_Listener;
import listeners.goTo.GoToAdminLogin_Listener;
import listeners.goTo.GoToClientLogin_Listener;

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

public class ClientRegisterForm extends JFrame {

	private JPanel contentPanel;
	
	public JTextField nomeField;
	public JTextField cognomeField;
	public JTextField emailField;
	public JTextField passwordField;
	public JTextField metodoPagamentoField;
	
	private JLabel registerErrorField;
	
	public ClientRegisterForm() {
		setTitle("Registrazione Cliente - Aeroporto");
		setResizable(false);
		setAlwaysOnTop(true);
		setBackground(new Color(192, 192, 192));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 480);
		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel registerText = new JLabel("Effettua la registrazione");
		registerText.setBounds(21, 5, 744, 44);
		contentPanel.add(registerText);
		registerText.setHorizontalAlignment(SwingConstants.CENTER);
		registerText.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		
		JPanel registerFields_Panel = new JPanel();
		registerFields_Panel.setBackground(new Color(192, 192, 192));
		registerFields_Panel.setBounds(15, 67, 736, 222);
		contentPanel.add(registerFields_Panel);
		GridBagLayout gbl_registerFields_Panel = new GridBagLayout();
		gbl_registerFields_Panel.columnWidths = new int[] {200, 544};
		gbl_registerFields_Panel.rowHeights = new int[] {42, 42, 42, 42, 42};
		gbl_registerFields_Panel.columnWeights = new double[]{0.0, 1.0};
		gbl_registerFields_Panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		registerFields_Panel.setLayout(gbl_registerFields_Panel);
		
		////////////////////////////////////////////////////////
		
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nomeLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 20));
		GridBagConstraints gbc_nomeLabel = new GridBagConstraints();
		gbc_nomeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nomeLabel.gridx = 0;
		gbc_nomeLabel.gridy = 0;
		registerFields_Panel.add(nomeLabel, gbc_nomeLabel);
				
		nomeField = new JTextField();
		nomeField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nomeField.setColumns(10);
		GridBagConstraints gbc_nomeField = new GridBagConstraints();
		gbc_nomeField.insets = new Insets(0, 0, 5, 0);
		gbc_nomeField.fill = GridBagConstraints.BOTH;
		gbc_nomeField.gridx = 1;
		gbc_nomeField.gridy = 0;
		registerFields_Panel.add(nomeField, gbc_nomeField);
		
		JLabel cognomeLabel = new JLabel("Cognome");
		cognomeLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 20));
		cognomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_cognomeLabel = new GridBagConstraints();
		gbc_cognomeLabel.anchor = GridBagConstraints.SOUTH;
		gbc_cognomeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_cognomeLabel.gridx = 0;
		gbc_cognomeLabel.gridy = 1;
		registerFields_Panel.add(cognomeLabel, gbc_cognomeLabel);
		
		cognomeField = new JTextField();
		cognomeField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_cognomeField = new GridBagConstraints();
		gbc_cognomeField.insets = new Insets(0, 0, 5, 0);
		gbc_cognomeField.fill = GridBagConstraints.BOTH;
		gbc_cognomeField.gridx = 1;
		gbc_cognomeField.gridy = 1;
		registerFields_Panel.add(cognomeField, gbc_cognomeField);
		cognomeField.setColumns(10);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 20));
		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.SOUTH;
		gbc_emailLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 2;
		registerFields_Panel.add(emailLabel, gbc_emailLabel);
		
		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.fill = GridBagConstraints.BOTH;
		gbc_emailField.insets = new Insets(0, 0, 5, 0);
		gbc_emailField.gridx = 1;
		gbc_emailField.gridy = 2;
		registerFields_Panel.add(emailField, gbc_emailField);
		emailField.setColumns(10);
		emailLabel.setLabelFor(emailField);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 20));
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.SOUTH;
		gbc_passwordLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 3;
		registerFields_Panel.add(passwordLabel, gbc_passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setColumns(10);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 3;
		registerFields_Panel.add(passwordField, gbc_passwordField);
		passwordLabel.setLabelFor(passwordField);
		
		JLabel metodoPagamentoLabel = new JLabel("Metodo di pagamento");
		metodoPagamentoLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		GridBagConstraints gbc_metodoPagamentoLabel = new GridBagConstraints();
		gbc_metodoPagamentoLabel.anchor = GridBagConstraints.SOUTH;
		gbc_metodoPagamentoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_metodoPagamentoLabel.gridx = 0;
		gbc_metodoPagamentoLabel.gridy = 4;
		registerFields_Panel.add(metodoPagamentoLabel, gbc_metodoPagamentoLabel);
		
		metodoPagamentoField = new JTextField();
		metodoPagamentoField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_metodoPagamentoField = new GridBagConstraints();
		gbc_metodoPagamentoField.insets = new Insets(0, 0, 5, 0);
		gbc_metodoPagamentoField.fill = GridBagConstraints.BOTH;
		gbc_metodoPagamentoField.gridx = 1;
		gbc_metodoPagamentoField.gridy = 4;
		registerFields_Panel.add(metodoPagamentoField, gbc_metodoPagamentoField);
		metodoPagamentoField.setColumns(10);
		
		registerErrorField = new JLabel("");
		registerErrorField.setHorizontalAlignment(SwingConstants.CENTER);
		registerErrorField.setForeground(new Color(255, 0, 0));
		registerErrorField.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		registerErrorField.setBounds(128, 291, 530, 21);
		registerErrorField.setVisible(false);
		contentPanel.add(registerErrorField);
		
		JButton registerButton = new JButton("Registrati");
		registerButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		registerButton.setBounds(293, 322, 200, 50);
		SendUserData_Listener listener = new SendUserData_Listener(this);
		registerButton.addActionListener(listener);
		contentPanel.add(registerButton);
		
		JLabel accediClientLabel = new JLabel("Sei già Cliente? Accedi!");
		accediClientLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accediClientLabel.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 15));
		accediClientLabel.setBounds(43, 382, 700, 20);
		GoToClientLogin_Listener clientLoginListener = new GoToClientLogin_Listener();
		accediClientLabel.addMouseListener(clientLoginListener);
		contentPanel.add(accediClientLabel);
		
		JLabel accediAdminLabel = new JLabel("Sei un amministratore? Accedi!");
		accediAdminLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accediAdminLabel.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 15));
		accediAdminLabel.setBounds(43, 405, 700, 20);
		GoToAdminLogin_Listener adminLoginListener = new GoToAdminLogin_Listener();
		accediAdminLabel.addMouseListener(adminLoginListener);
		contentPanel.add(accediAdminLabel);
	}
	
	public void activateError(String errorText) { // Attiva il testo di errore!
		registerErrorField.setText(errorText);
		registerErrorField.setVisible(true);
	}
	
	public String getNome() {
		return nomeField.getText();
	}
	
	public String getCognome() {
		return cognomeField.getText();
	}
	
	public String getEmail() {
		return emailField.getText();
	}
	
	public String getPassword() {
		return passwordField.getText();
	}
	
	public String getMetodoPagamento() {
		return metodoPagamentoField.getText();
	}
}
