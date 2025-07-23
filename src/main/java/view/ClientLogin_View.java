package view;

import javax.swing.*;
import java.awt.*;

import listeners.LoginRegister_Listener;
import listeners.goTo.GoToAdminLogin_Listener;
import listeners.goTo.GoToClientLogin_Listener;
import listeners.goTo.GoToClientRegister_Listener;


public class ClientLogin_View extends JFrame {

    private JPanel ovestContainer;    
    private JPanel centerContainer;    

    private JLabel loginTitleLabel;    
    private JLabel accountinfoLabel;    

    private JLabel emailLabel; 
    private JLabel passwordLabel; 

    private JTextField emailField;      
    private JPasswordField passwordField; 
    
    private JLabel loginErrorLabel; 

    private JLabel goToRegisterLabel;
    private JLabel goToAdminLabel; 

    private JButton loginButton;	

    public ClientLogin_View() {
        super("Login Cliente - Aeroporto"); 
        this.setSize(900, 500); 
        this.setLocationRelativeTo(null); 
        this.setResizable(false); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.getContentPane().setLayout(new BorderLayout());
      

        ovestContainer = new JPanel(); 
        ovestContainer.setLayout(new BoxLayout(ovestContainer, BoxLayout.Y_AXIS));
        ovestContainer.setBackground(new Color(230, 230, 230)); 
        ovestContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); 


        try {
            ImageIcon iconaOriginale = new ImageIcon(getClass().getResource("/icons/plane.png")); 

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


        loginTitleLabel = new JLabel("Accedi");
        loginTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        loginTitleLabel.setForeground(new Color(51, 103, 153));
        loginTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ovestContainer.add(loginTitleLabel);
        ovestContainer.add(Box.createVerticalStrut(20)); 

        

        accountinfoLabel = new JLabel("Utilizza il tuo account cliente");
        accountinfoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        accountinfoLabel.setForeground(new Color(95, 99, 104));
        accountinfoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ovestContainer.add(accountinfoLabel);    


        
        centerContainer = new JPanel(new GridBagLayout()); 
        centerContainer.setBackground(new Color(40, 44, 52)); 
        centerContainer.setBorder(BorderFactory.createEmptyBorder(60, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.insets = new Insets(12, 0, 12, 0); 

        
        
        emailLabel = new JLabel("Email:"); 
        emailLabel.setForeground(Color.WHITE); 
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); 

        passwordLabel = new JLabel("Password:"); 
        passwordLabel.setForeground(Color.WHITE); 
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); 


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.weightx = 0.0; 
        gbc.insets = new Insets(12, 0, 12, 10); 
        centerContainer.add(emailLabel, gbc);

        
        
        emailField = new JTextField();
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
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.weightx = 1.0; 
        gbc.insets = new Insets(12, 0, 12, 0); 
        centerContainer.add(emailField, gbc);



        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; 
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
        gbc.gridy = 1; 
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.weightx = 1.0; 
        gbc.insets = new Insets(12, 0, 12, 0); 
        centerContainer.add(passwordField, gbc);
        
        
        loginErrorLabel = new JLabel("La combinazione di email e password potrebbe essere errata");
        loginErrorLabel.setForeground(Color.RED);
        loginErrorLabel.setFont(new Font("Segoe UI",Font.PLAIN, 16)); 
        gbc.gridx = 0; 
        gbc.gridy = 2; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.weightx = 0.0; 
        loginErrorLabel.setVisible(false);
        centerContainer.add(loginErrorLabel, gbc);


        goToRegisterLabel = new JLabel("<html><u>Crea un account</u></html>"); 
        goToRegisterLabel.setForeground(Color.WHITE); 
        goToRegisterLabel.setFont(new Font("Segoe UI",Font.PLAIN, 16)); 
        gbc.gridx = 0; 
        gbc.gridy = 3; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.weightx = 0.0; 
        gbc.insets = new Insets(30, 0, 12, 0); 
        centerContainer.add(goToRegisterLabel, gbc);
        
        GoToClientRegister_Listener goToRegister = new GoToClientRegister_Listener();
        goToRegisterLabel.addMouseListener(goToRegister);
        

        
        
        goToAdminLabel = new JLabel("<html><u>Se sei un amministratore accedi qui</u></html>");
        goToAdminLabel.setForeground(Color.WHITE);
        goToAdminLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE; 
        gbc.weightx = 0.0;
        gbc.insets = new Insets(10, 0, 12, 0);
        
        GoToAdminLogin_Listener adminLoginListener = new GoToAdminLogin_Listener();
        goToAdminLabel.addMouseListener(adminLoginListener);
        
        centerContainer.add(goToAdminLabel, gbc);
        
        loginButton = new JButton("Accedi");
        loginButton.setBackground(new Color(175, 207, 255)); 
        loginButton.setForeground(new Color(51, 103, 153)); 
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16)); 
        loginButton.setPreferredSize(new Dimension(100, 45)); 
        loginButton.setFocusPainted(false); 
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(10, 0, 12, 0);
        
        LoginRegister_Listener sendData = new LoginRegister_Listener(this);
        loginButton.addActionListener(sendData);
        
        centerContainer.add(loginButton, gbc);
        
        this.getContentPane().add(ovestContainer, BorderLayout.WEST);
        this.getContentPane().add(centerContainer, BorderLayout.CENTER);
    }
    
	public void activateError() { 
		loginErrorLabel.setVisible(true);
	}
	
	public String getEmail() {
		return emailField.getText();
	}
	
	public String getPassword() {
		return passwordField.getText();
	}
}
