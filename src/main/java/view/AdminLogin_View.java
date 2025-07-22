package view;


import javax.swing.*;
import java.awt.*;

import listeners.LoginRegister_Listener;
import listeners.goTo.GoToAdminLogin_Listener;
import listeners.goTo.GoToClientLogin_Listener;
import listeners.goTo.GoToClientRegister_Listener;


public class AdminLogin_View extends JFrame {

	// --- Dichiarazione dei Componenti (variabili membro) ---
    // Questi JPanel saranno i contenitori principali del nostro layout
    private JPanel ovestContainer;    // Pannello per la sezione sinistra
    private JPanel centerContainer;    // Pannello centrale 

    private JLabel loginTitleLabel;    // Label per il testo in grande "Accedi"
    private JLabel accountinfoLabel;    // Label per "Utilizza il tuo account"

    private JLabel emailLabel; // Etichetta per il campo emailLabel
    private JLabel passwordLabel; // Etichetta per il campo passwordLabel
    private JLabel chiaveAccessoLabel; // Etichetta per il campo chiaveAccessoLabel

    // Campi di testo per l'input utente
    private JTextField emailField;      // Campo per l'email
    private JPasswordField passwordField; // Campo per la password
    private JTextField chiaveAccessoField; // Campo per la chiave d'accesso
    
    private JLabel loginErrorLabel; // Etichetta per l'errore di login

    // Label interattive ai click del mouse
    private JLabel goToRegisterLabel;
    private JLabel goToAdminLabel; 

    private JButton loginButton;	// Il bottone "Avanti"

    // --- Costruttore della Finestra Principale ---
    public AdminLogin_View() {
        // --- MODIFICHE PER LA FINESTRA PRINCIPALE (Configurazione della finestra principale) ---
        super("Login Amministratore - Aeroporto"); // Imposta il titolo della finestra
        this.setSize(1000, 500); // Imposta le dimensioni della finestra (larghezza, altezza)
        this.setLocationRelativeTo(null); // Centra la finestra sullo schermo
        this.setResizable(false); // Impedisce all'utente di ridimensionare la finestra
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // (cliccando X), termina il programma
        //this.getContentPane().setBackground(Color.BLACK); // Imposta il colore di sfondo del Content Pane a nero
        this.getContentPane().setLayout(new BorderLayout()); // 5 regioni (Nord, Sud, Est, Ovest, Centro)
      

        // --- JPanel OVEST (La barra laterale sinistra con "Accedi") ---
        ovestContainer = new JPanel(); // Inizializza il pannello OVEST
        // Imposta BoxLayout verticale (componenti uno sotto l'altro)
        ovestContainer.setLayout(new BoxLayout(ovestContainer, BoxLayout.Y_AXIS));
        ovestContainer.setBackground(new Color(230, 230, 230)); // Imposta un colore di sfondo grigio chiaro
        ovestContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Aggiunge un margine di 30px


        // --- Caricamento e ridimensionamento dell'immagine dell'elefante ---
        try {
            // Carica l'immagine dal percorso relativo.
            ImageIcon iconaOriginale = new ImageIcon(getClass().getResource("/icons/amministratore.png")); //

            // Ottieni l'oggetto Image dall'ImageIcon
            Image immagineOriginale = iconaOriginale.getImage(); //

            // Definisci le nuove dimensioni desiderate per l'immagine
            int nuovaLarghezza = 100;
            int nuovaAltezza = 100;

            // Ridimensiona l'immagine usando RenderingHints per una migliore qualità (SCALE_SMOOTH)
            Image immagineRidimensionata = immagineOriginale.getScaledInstance(nuovaLarghezza, nuovaAltezza, Image.SCALE_SMOOTH); //

            // Crea un nuovo ImageIcon con l'immagine ridimensionata
            ImageIcon iconaRidimensionata = new ImageIcon(immagineRidimensionata); //

            // Crea la JLabel per l'immagine ridimensionata
            JLabel imageLabel = new JLabel(iconaRidimensionata); //
            imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Allinea a sinistra

            // Aggiungi un po' di spazio sopra l'immagine
            ovestContainer.add(Box.createVerticalStrut(3)); //
            // Aggiungi la label dell'immagine al pannello OVEST
            ovestContainer.add(imageLabel); //

        } catch (Exception e) {
            // In caso di errore nel caricamento dell'immagine, stampa lo stack trace.
            e.printStackTrace(); //
            System.err.println("Errore nel caricamento dell'immagine: " + e.getMessage()); //
        }
        // --- FINE AGGIUNTA IMMAGINE ---



        // --- Testo "Accedi" ---
        loginTitleLabel = new JLabel("Accedi");
        loginTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        loginTitleLabel.setForeground(new Color(51, 103, 153));
        loginTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ovestContainer.add(loginTitleLabel);
        ovestContainer.add(Box.createVerticalStrut(20)); // Aggiunge uno spazio verticale vuoto di 20 pixel sotto la label

        

        // --- Testo "Utilizza il tuo account amministratore" ---
        accountinfoLabel = new JLabel("Utilizza il tuo account amministratore");
        accountinfoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        accountinfoLabel.setForeground(new Color(95, 99, 104));
        accountinfoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ovestContainer.add(accountinfoLabel);    // Aggiungiamo questo componente al ovestContainer



        // --- JPanel centerContainer (Il box scuro contenente i campi emailLabel,passwordLabel ecc...) ---
        centerContainer = new JPanel(new GridBagLayout()); // Inizializza il pannello e imposta GridBagLayout
        centerContainer.setBackground(new Color(40, 44, 52)); // Imposta il colore di sfondo del box, un grigio scuro
        // Aggiunge un bordo vuoto attorno ai campi (Top, Left, Bottom, Right)
        centerContainer.setBorder(BorderFactory.createEmptyBorder(60, 30, 20, 30));

        // --- Configurazione per GridBagLayout (GridBagConstraints) ---
        GridBagConstraints gbc = new GridBagConstraints(); // Crea un oggetto per definire le regole di posizionamento
        // I margini (insets) predefiniti per i campi e le label
        gbc.insets = new Insets(12, 0, 12, 0); // Spazio interno: top, left, bottom, right.

        
        
        // --- Inizializzazione delle nuove JLabel per "emailLabel:" e "passwordLabel:" ---
        emailLabel = new JLabel("Email:"); // Dichiarata come variabile membro
        emailLabel.setForeground(Color.WHITE); // Colore del testo bianco per leggibilità
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Font a tua scelta

        passwordLabel = new JLabel("Password:"); // Dichiarata come variabile membro
        passwordLabel.setForeground(Color.WHITE); // Colore del testo bianco
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Impostiamo il font
        
        chiaveAccessoLabel = new JLabel("Chiave d'accesso: ");
        chiaveAccessoLabel.setForeground(Color.WHITE); // Colore del testo bianco
        chiaveAccessoLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Impostiamo il font


        // --- Campo emailLabel e relativa JLabel ---
        // Posizionamento della JLabel "emailLabel:" (Colonna 0, Riga 0)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Allinea a sinistra nella cella
        gbc.fill = GridBagConstraints.NONE; // La label non si espande per riempire la cella
        gbc.weightx = 0.0; // Non assegnare peso a questa colonna, non si espanderà
        gbc.insets = new Insets(12, 0, 12, 10); // Spazio interno: top, left, bottom, right.
        centerContainer.add(emailLabel, gbc);

        // Posizionamento del JTextField per l'emailLabel (Colonna 1, Riga 0)
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

        gbc.gridx = 1; // Colonna 1 (accanto alla label)
        gbc.gridy = 0; // Riga 0 (stessa riga della label)
        gbc.anchor = GridBagConstraints.WEST; // Allinea a sinistra nella cella
        gbc.fill = GridBagConstraints.HORIZONTAL; // Il campo di testo si espande orizzontalmente
        gbc.weightx = 1.0; // Assegna peso a questa colonna, si espanderà
        gbc.insets = new Insets(12, 0, 12, 0); // Spazio interno: top, left, bottom, right.
        centerContainer.add(emailField, gbc);

        // --- Campo passwordLabel e relativa JLabel ---
        // Posizionamento della JLabel "passwordLabel:" (Colonna 0, Riga 1)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER; // Allinea a sinistra nella cella
        gbc.fill = GridBagConstraints.NONE; // La label non si espande
        gbc.weightx = 0.0; // Nessun peso
        gbc.insets = new Insets(12, 0, 12, 10); // Spazio interno: top, left, bottom, right.
        centerContainer.add(passwordLabel, gbc);

        // Posizionamento del JPasswordField (Colonna 1, Riga 1)
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

        gbc.gridx = 1; // Colonna 1
        gbc.gridy = 1; // Riga 1 (stessa riga della label passwordLabel)
        gbc.anchor = GridBagConstraints.WEST; // Allinea a sinistra nella cella
        gbc.fill = GridBagConstraints.HORIZONTAL; // Il campo si espande orizzontalmente
        gbc.weightx = 1.0; // Assegna peso a questa colonna
        gbc.insets = new Insets(12, 0, 12, 0); // Spazio interno: top, left, bottom, right.
        centerContainer.add(passwordField, gbc);
        
        // Posizionamento del ChiaveAccessoLabel (Colonna 0, Riga 2)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST; // Allinea a sinistra nella cella
        gbc.fill = GridBagConstraints.NONE; // La label non si espande per riempire la cella
        gbc.weightx = 0.0; // Non assegnare peso a questa colonna, non si espanderà
        gbc.insets = new Insets(12, 0, 12, 10); // Spazio interno: top, left, bottom, right.
        centerContainer.add(chiaveAccessoLabel, gbc);
        
        // Posizionamento del ChiaveAccessoField (Colonna 1, Riga 2)
        chiaveAccessoField = new JTextField(3);
        chiaveAccessoField.setText("");
        chiaveAccessoField.setPreferredSize(new Dimension(350, 45));
        chiaveAccessoField.setBackground(new Color(60, 63, 66));
        chiaveAccessoField.setForeground(Color.WHITE);
        chiaveAccessoField.setCaretColor(Color.WHITE);
        chiaveAccessoField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        chiaveAccessoField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(90, 93, 96), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        gbc.gridx = 1; // Colonna 1
        gbc.gridy = 2; // Riga 1 (stessa riga della label passwordLabel)
        gbc.anchor = GridBagConstraints.WEST; // Allinea a sinistra nella cella
        gbc.fill = GridBagConstraints.HORIZONTAL; // Il campo si espande orizzontalmente
        gbc.weightx = 1.0; // Assegna peso a questa colonna
        gbc.insets = new Insets(12, 0, 12, 0); // Spazio interno: top, left, bottom, right.
        centerContainer.add(chiaveAccessoField, gbc);
        
        // --- JLabel "Errore di Login" ---
        loginErrorLabel = new JLabel("La combinazione di email, password e chiave d'accesso potrebbe essere errata");
        loginErrorLabel.setForeground(Color.RED);
        loginErrorLabel.setFont(new Font("Segoe UI",Font.PLAIN, 16)); // Font a scelta
        gbc.gridx = 0; // Colonna 0 (sotto la label "emailLabel:" o "passwordLabel:")
        gbc.gridy = 3; // Riga 2 (sotto il campo password che è in riga 1)
        gbc.gridwidth = 2; //  Questo fa in modo che la label occupi 2 colonne 
        gbc.anchor = GridBagConstraints.CENTER; // Allinea a sinistra
        gbc.fill = GridBagConstraints.NONE; // Non far espandere la label
        gbc.weightx = 0.0; // Non dare peso orizzontale
        loginErrorLabel.setVisible(false);
        centerContainer.add(loginErrorLabel, gbc);          
        
        // --- JLabel "Se sei un amministratore accedi qui" ---
        goToAdminLabel = new JLabel("<html><u>Se sei un cliente accedi qui</u></html>");
        goToAdminLabel.setForeground(Color.WHITE);
        goToAdminLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Manteniamo questo valore o prova 3 se hai più colonne o se c'è una colonna invisibile.
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE; // Manteniamo NONE, poiché non vogliamo che si estenda
        gbc.weightx = 0.0;
        gbc.insets = new Insets(10, 0, 12, 0);
        
        GoToClientLogin_Listener adminLoginListener = new GoToClientLogin_Listener();
        goToAdminLabel.addMouseListener(adminLoginListener);
        
        centerContainer.add(goToAdminLabel, gbc);
        
        // --- JButton "Accedi" ---
        loginButton = new JButton("Accedi");
        loginButton.setBackground(new Color(175, 207, 255)); // Sfondo simile ai campi di input
        loginButton.setForeground(new Color(51, 103, 153)); // Testo 
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Font audace e dimensione 16
        loginButton.setPreferredSize(new Dimension(100, 45)); // Dimensione fissa (larghezza, altezza)
        // Rimuove il bordo di focus (il rettangolo che appare quando il pulsante è selezionato)
        loginButton.setFocusPainted(false); 
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(10, 0, 12, 0);
        
        LoginRegister_Listener sendData = new LoginRegister_Listener(this);
        loginButton.addActionListener(sendData);
        
        centerContainer.add(loginButton, gbc);
        
     // --- Aggiunta dei Contenitori al Content Pane della Finestra Principale ---
        this.getContentPane().add(ovestContainer, BorderLayout.WEST);
        this.getContentPane().add(centerContainer, BorderLayout.CENTER);

    }
	
	public void activateError() { // Attiva il testo di errore!
		loginErrorLabel.setVisible(true);
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
