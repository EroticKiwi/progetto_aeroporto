package view;

import javax.swing.*;
import java.awt.*;

import listeners.LoginRegister_Listener;
import listeners.goTo.GoToAdminLogin_Listener;
import listeners.goTo.GoToClientLogin_Listener;


public class ClientRegister_View extends JFrame {

    private JPanel ovestContainer;
    private JPanel centerContainer;

    private JLabel registerTitleLabel;
    private JLabel accountinfoLabel;

    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel metodoPagamentoLabel;

    private JTextField nomeField;
    private JTextField cognomeField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField metodoPagamentoField;
    
    private JLabel registerErrorField;

    private JLabel goToRegisterLabel;
    private JLabel goToAdminLabel;

    private JButton loginButton;

    public ClientRegister_View() {
        super("Registrazione Cliente - Aeroporto");
        this.setSize(1100,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
      

        ovestContainer = new JPanel();
        ovestContainer.setLayout(new BoxLayout(ovestContainer, BoxLayout.Y_AXIS));
        ovestContainer.setBackground(new Color(230, 230, 230));
        ovestContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));


        try {
            ImageIcon iconaOriginale = new ImageIcon(getClass().getResource("/icons/add-user.png"));

            Image immagineOriginale = iconaOriginale.getImage();

            int nuovaLarghezza = 100;
            int nuovaAltezza = 100;

            Image immagineRidimensionata = immagineOriginale.getScaledInstance(nuovaLarghezza, nuovaAltezza, Image.SCALE_SMOOTH);

            ImageIcon iconaRidimensionata = new ImageIcon(immagineRidimensionata);

            JLabel imageLabel = new JLabel(iconaRidimensionata);
            imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            ovestContainer.add(Box.createVerticalStrut(3));
            ovestContainer.add(imageLabel);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Errore nel caricamento dell'immagine: " + e.getMessage());
        }


        registerTitleLabel = new JLabel("Registrati");
        registerTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        registerTitleLabel.setForeground(new Color(51, 103, 153));
        registerTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ovestContainer.add(registerTitleLabel);
        ovestContainer.add(Box.createVerticalStrut(20));

        

        accountinfoLabel = new JLabel("Registra un nuovo account cliente");
        accountinfoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        accountinfoLabel.setForeground(new Color(95, 99, 104));
        accountinfoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ovestContainer.add(accountinfoLabel);


        centerContainer = new JPanel(new GridBagLayout());
        centerContainer.setBackground(new Color(40, 44, 52));
        centerContainer.setBorder(BorderFactory.createEmptyBorder(60, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 0, 12, 0);

        
        
        nomeLabel = new JLabel("Nome:");
        nomeLabel.setForeground(Color.WHITE);
        nomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        cognomeLabel = new JLabel("Cognome:");
        cognomeLabel.setForeground(Color.WHITE);
        cognomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        metodoPagamentoLabel = new JLabel("Metodo di Pagamento: ");
        metodoPagamentoLabel.setForeground(Color.WHITE);
        metodoPagamentoLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(12, 0, 12, 10);
        centerContainer.add(nomeLabel, gbc);

        nomeField = new JTextField();
        nomeField.setText("");
        nomeField.setPreferredSize(new Dimension(350, 45));
        nomeField.setBackground(new Color(60, 63, 66));
        nomeField.setForeground(Color.WHITE);
        nomeField.setCaretColor(Color.WHITE);
        nomeField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nomeField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(90, 93, 96), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(12, 0, 12, 0);
        centerContainer.add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(12, 0, 12, 10);
        centerContainer.add(cognomeLabel, gbc);

        cognomeField = new JTextField(20);
        cognomeField.setText("");
        cognomeField.setPreferredSize(new Dimension(350, 45));
        cognomeField.setBackground(new Color(60, 63, 66));
        cognomeField.setForeground(Color.WHITE);
        cognomeField.setCaretColor(Color.WHITE);
        cognomeField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cognomeField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(90, 93, 96), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(12, 0, 12, 0);
        centerContainer.add(cognomeField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(12, 0, 12, 10);
        centerContainer.add(emailLabel, gbc);
        
        emailField = new JTextField(3);
        emailField.setText("");
        emailField.setPreferredSize(new Dimension(350, 45));
        emailField.setBackground(new Color(60, 63, 66));
        emailField.setForeground(Color.WHITE);
        emailField.setCaretColor(Color.WHITE);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(90, 93, 96), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(12, 0, 12, 0);
        centerContainer.add(emailField, gbc);
        
        ///////////
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(12, 0, 12, 10);
        centerContainer.add(passwordLabel, gbc);
        
        passwordField = new JPasswordField(20);
        passwordField.setText("");
        passwordField.setPreferredSize(new Dimension(350, 45));
        passwordField.setBackground(new Color(60, 63, 66));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(90, 93, 96), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(12, 0, 12, 0);
        centerContainer.add(passwordField, gbc);
        
        ///////
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(12, 0, 12, 10);
        centerContainer.add(metodoPagamentoLabel, gbc);
        
        metodoPagamentoField = new JPasswordField(20);
        metodoPagamentoField.setText("");
        metodoPagamentoField.setPreferredSize(new Dimension(350, 45));
        metodoPagamentoField.setBackground(new Color(60, 63, 66));
        metodoPagamentoField.setForeground(Color.WHITE);
        metodoPagamentoField.setCaretColor(Color.WHITE);
        metodoPagamentoField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        metodoPagamentoField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(90, 93, 96), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(12, 0, 12, 0);
        centerContainer.add(metodoPagamentoField, gbc);
        
        registerErrorField = new JLabel("La combinazione di email, password e chiave d'accesso potrebbe essere errata");
        registerErrorField.setForeground(Color.RED);
        registerErrorField.setFont(new Font("Segoe UI",Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        registerErrorField.setVisible(false);
        centerContainer.add(registerErrorField, gbc);          
        
        goToAdminLabel = new JLabel("<html><u>Se sei un cliente accedi qui</u></html>");
        goToAdminLabel.setForeground(Color.WHITE);
        goToAdminLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(10, 0, 12, 0);
        
        GoToClientLogin_Listener adminLoginListener = new GoToClientLogin_Listener();
        goToAdminLabel.addMouseListener(adminLoginListener);
        
        centerContainer.add(goToAdminLabel, gbc);
        
        loginButton = new JButton("Registrati");
        loginButton.setBackground(new Color(175, 207, 255));
        loginButton.setForeground(new Color(51, 103, 153));
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(125, 45));
        loginButton.setFocusPainted(false); 
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(10, 0, 12, 0);
        
        LoginRegister_Listener sendData = new LoginRegister_Listener(this);
        loginButton.addActionListener(sendData);
        
        centerContainer.add(loginButton, gbc);
        
        this.getContentPane().add(ovestContainer, BorderLayout.WEST);
        this.getContentPane().add(centerContainer, BorderLayout.CENTER);
    }
	
	public void activateError(String errorText) {
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
