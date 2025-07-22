package view;

import javax.swing.*;
import java.awt.*;

import listeners.LoginRegister_Listener;
import listeners.goTo.GoToAdminLogin_Listener;
import listeners.goTo.GoToClientLogin_Listener;
import listeners.goTo.GoToClientRegister_Listener;


public class AdminLogin_View extends JFrame {

    // Questi JPanel saranno i contenitori principali del nostro layout
    private JPanel ovestContainer;    // Pannello per la sezione sinistra
    private JPanel centerContainer;    // Pannello centrale 

    private JLabel loginTitleLabel; // Label per il testo in grande "Accedi"
    private JLabel accountinfoLabel; // Label per "Utilizza il tuo account"

    private JLabel emailLabel; // Etichetta per il campo emailLabel
    private JLabel passwordLabel; // Etichetta per il campo passwordLabel
    private JLabel chiaveAccessoLabel; // Etichetta per il campo chiaveAccessoLabel

    // Campi di testo per l'input utente
    private JTextField emailField;      // Campo per l'email
    private JPasswordField passwordField; // Campo per la password
    private JTextField chiaveAccessoField; // Campo per la chiave d'accesso
    
    private JLabel loginErrorLabel; // Etichetta per l'errore di login

    // Label interattive ai click del mouse
    private JLabel goToAdminLabel; // Label "Accedi come cliente" (etichettata come goToAdminLabel ma usata per il login cliente)
    private JButton loginButton;	// Il bottone "Avanti"

    // --- Costruttore della Finestra Principale ---
    public AdminLogin_View() {
        // --- Configurazione della finestra principale ---
        super("Login Amministratore - Aeroporto"); // Impostiamo il titolo della finestra
        this.setSize(1000, 500); // Impostiamo le dimensioni della finestra (larghezza, altezza)
        this.setLocationRelativeTo(null); // Centriamo la finestra sullo schermo
        this.setResizable(false); // Impediamo all'utente di ridimensionare la finestra
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // (cliccando X), terminiamo il programma
        this.getContentPane().setLayout(new BorderLayout()); // Impostiamo il layout manager del content pane a BorderLayout (5 regioni: Nord, Sud, Est, Ovest, Centro)

      

        // --- JPanel OVEST (La barra laterale sinistra) ---
        ovestContainer = new JPanel(); // Inizializziamo il pannello OVEST
        // Impostiamo BoxLayout verticale (componenti uno sotto l'altro)
        ovestContainer.setLayout(new BoxLayout(ovestContainer, BoxLayout.Y_AXIS));
        ovestContainer.setBackground(new Color(230, 230, 230)); // Impostiamo un colore di sfondo grigio chiaro al pannello
        ovestContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Aggiungiamo un margine di 30px per ogni direzione


        // --- Caricamento e ridimensionamento dell'immagine dell'amministratore ---
        try {
            // Carichiamo l'immagine dal percorso relativo.
            ImageIcon iconaOriginale = new ImageIcon(getClass().getResource("/icons/amministratore.png")); 

            // Otteniamo l'oggetto Image dall'ImageIcon
            Image immagineOriginale = iconaOriginale.getImage(); 

            // Definiamo le nuove dimensioni desiderate per l'immagine
            int nuovaLarghezza = 100;
            int nuovaAltezza = 100;

            // Ridimensioniamo l'immagine usando RenderingHints per una migliore qualità (SCALE_SMOOTH)
            Image immagineRidimensionata = immagineOriginale.getScaledInstance(nuovaLarghezza, nuovaAltezza, Image.SCALE_SMOOTH); 

            // Creiamo un nuovo ImageIcon con l'immagine ridimensionata
            ImageIcon iconaRidimensionata = new ImageIcon(immagineRidimensionata); 

            // Creiamo la JLabel per l'immagine ridimensionata
            JLabel imageLabel = new JLabel(iconaRidimensionata); 
            imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Allineiamo l'immagine a sinistra

            // Aggiungiamo un po' di spazio sopra l'immagine
            ovestContainer.add(Box.createVerticalStrut(3)); // Aggiungiamo uno spazio verticale vuoto di 3 pixel
            // Aggiungiamo la label dell'immagine al pannello OVEST
            ovestContainer.add(imageLabel); //

        } catch (Exception e) {
            // In caso di errore nel caricamento dell'immagine, stampiamo l'errore
            e.printStackTrace(); // Stampiamo lo stack trace dell'eccezione
            System.err.println("Errore nel caricamento dell'immagine: " + e.getMessage()); // Stampiamo un messaggio di errore più descrittivo
        }
        
        // Aggiungiamo un altro spazio verticale dopo l'immagine
        ovestContainer.add(Box.createVerticalStrut(20));


        
        // --- Testo "Accedi" ---
        loginTitleLabel = new JLabel("Accedi"); // Testo che il label mostrerà
        loginTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 40)); // Modifichiamo il font con grandezza 40 e stile grassetto
        loginTitleLabel.setForeground(new Color(51, 103, 153));	// Modifichiamo il colore del testo
        loginTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Allineiamo a sinistra il testo nel pannello
        ovestContainer.add(loginTitleLabel); // Aggiungiamo il label al contenitore OVEST
        ovestContainer.add(Box.createVerticalStrut(20)); // Aggiungiamo uno spazio verticale vuoto di 20 pixel sotto a questo label

        

        // --- Testo "Utilizza il tuo account amministratore" ---
        accountinfoLabel = new JLabel("Utilizza il tuo account amministratore"); // Testo che il label mostrerà
        accountinfoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Modifichiamo il font con grandezza 16 e stile normale
        accountinfoLabel.setForeground(new Color(95, 99, 104)); // Modifichiamo il colore del testo
        accountinfoLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Allineiamo a sinistra il testo nel pannello
        ovestContainer.add(accountinfoLabel); // Aggiungiamo questo componente al ovestContainer



        // --- JPanel centerContainer (Il contenitore scuro contenente i campi emailLabel, passwordLabel ecc...) ---
        centerContainer = new JPanel(new GridBagLayout()); // Inizializziamo il pannello e impostiamo il GridBagLayout per un layout flessibile a griglia
        centerContainer.setBackground(new Color(40, 44, 52)); // Impostiamo il colore di sfondo del pannello centrale, un grigio scuro
        // Aggiungiamo un bordo vuoto attorno ai campi (Top, Left, Bottom, Right) per creare spaziatura interna
        centerContainer.setBorder(BorderFactory.createEmptyBorder(60, 30, 20, 30));

        // --- Configurazione per GridBagLayout (GridBagConstraints) ---
        GridBagConstraints gbc = new GridBagConstraints(); // Creiamo un oggetto per definire le regole di posizionamento dei componenti nel GridBagLayout
        // I margini (insets) predefiniti per i campi e le label
        gbc.insets = new Insets(12, 0, 12, 0); // Spazio interno: top, left, bottom, right per ogni cella

        
        
        // --- Inizializzazione delle JLabel per "Email:", "Password:" e "Chiave d'accesso:" ---
        emailLabel = new JLabel("Email:"); // Testo che il label mostrerà
        emailLabel.setForeground(Color.WHITE); // Impostiamo il colore del testo in bianco
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Modifichiamo il font con grandezza 16 e stile grassetto
        
        
        
        passwordLabel = new JLabel("Password:"); //  Testo che il label mostrerà
        passwordLabel.setForeground(Color.WHITE); // Impostiamo il colore del testo in bianco
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Modifichiamo il font con grandezza 16 e stile grassetto
        
        
        
        chiaveAccessoLabel = new JLabel("Chiave d'accesso: "); //  Testo che il label mostrerà
        chiaveAccessoLabel.setForeground(Color.WHITE); // Impostiamo il colore del testo in bianco
        chiaveAccessoLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Modifichiamo il font con grandezza 16 e stile grassetto

        

        // --- Campo email e relativa JLabel ---
        // Posizionamento della JLabel "Email:" 
        gbc.gridx = 0; // Colonna 0 nella griglia
        gbc.gridy = 0; // Riga 0 nella griglia
        gbc.anchor = GridBagConstraints.WEST; // Allineiamo il componente a sinistra nella sua cella
        gbc.fill = GridBagConstraints.NONE; // La label non si espande per riempire la cella
        gbc.weightx = 0.0; // Non assegniamo peso orizzontale a questa colonna, quindi non si espanderà
        gbc.insets = new Insets(12, 0, 12, 10); // Spazio interno specifico per questa label
        centerContainer.add(emailLabel, gbc);

        
        
        // Posizionamento del JTextField per l'email 
        emailField = new JTextField(); // Inizializziamo il JTextField
        emailField.setText(""); // Il campo di testo non ha testo predefinito
        emailField.setPreferredSize(new Dimension(350, 45)); // Modifichiamo le dimensioni preferite del componente
        emailField.setBackground(new Color(60, 63, 66)); // Modifichiamo il colore di sfondo del campo
        emailField.setForeground(Color.WHITE); // Impostiamo il colore del testo digitato in bianco
        emailField.setCaretColor(Color.WHITE); // Impostiamo il colore del cursore lampeggiante a bianco
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Modifichiamo il font del testo digitato
        emailField.setBorder(BorderFactory.createCompoundBorder( // Modifichiamo il bordo del campo combinando un bordo linea con uno vuoto
            BorderFactory.createLineBorder(new Color(90, 93, 96), 1), // Bordo linea sottile
            BorderFactory.createEmptyBorder(5, 10, 5, 10) // Bordo interno per spaziatura del testo
        ));

        
        
        gbc.gridx = 1; // Colonna 1 nella griglia
        gbc.gridy = 0; // Riga 0 nella griglia
        gbc.anchor = GridBagConstraints.WEST; // Allineiamo il campo a sinistra nella cella
        gbc.fill = GridBagConstraints.HORIZONTAL; // Il campo di testo si espande orizzontalmente per riempire lo spazio disponibile
        gbc.weightx = 1.0; // Assegniamo peso a questa colonna, quindi si espanderà per occupare lo spazio extra
        gbc.insets = new Insets(12, 0, 12, 0); // Spazio interno per questo campo
        centerContainer.add(emailField, gbc);

        
        
        // --- Campo password e relativa JLabel ---
        // Posizionamento della JLabel "Password:" 
        gbc.gridx = 0; // Colonna 0 nella griglia
        gbc.gridy = 1; // Riga 1 nella griglia
        gbc.anchor = GridBagConstraints.WEST; // Allineiamo il componente a sinistra nella sua cella
        gbc.fill = GridBagConstraints.NONE; // La label non si espande per riempire la cella
        gbc.weightx = 0.0; // Non assegniamo peso orizzontale a questa colonna
        gbc.insets = new Insets(12, 0, 12, 10); // Spazio interno specifico per questa label
        centerContainer.add(passwordLabel, gbc);

        
        
        // Posizionamento del JPasswordField
        passwordField = new JPasswordField(20); // Inizializziamo il JPasswordField con una larghezza massima di 20 caratteri
        passwordField.setText(""); // Campo di testo vuoto
        passwordField.setPreferredSize(new Dimension(350, 45)); // Modifichiamo le dimensioni preferite del componente
        passwordField.setBackground(new Color(60, 63, 66)); // Colore di sfondo del campo
        passwordField.setForeground(Color.WHITE); // Impostiamo il colore del carattere in bianco
        passwordField.setCaretColor(Color.WHITE); // Impostiamo il colore del cursore lampeggiante a bianco
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Modifichiamo il font del testo
        passwordField.setBorder(BorderFactory.createCompoundBorder( // Modifichiamo il bordo del campo
            BorderFactory.createLineBorder(new Color(90, 93, 96), 1), // Bordo linea sottile
            BorderFactory.createEmptyBorder(5, 10, 5, 10) // Bordo interno per spaziatura del testo
        ));

        
        
        gbc.gridx = 1; // Colonna 1 nella griglia
        gbc.gridy = 1; // Riga 1 nella griglia
        gbc.anchor = GridBagConstraints.WEST; // Allineiamo a sinistra nella cella
        gbc.fill = GridBagConstraints.HORIZONTAL; // Il campo si espande orizzontalmente
        gbc.weightx = 1.0; // Assegniamo peso a questa colonna
        gbc.insets = new Insets(12, 0, 12, 0); // Spazio interno specifico per questo campo
        centerContainer.add(passwordField, gbc);
        
        
        
        // --- Campo Chiave d'Accesso e relativa JLabel ---
        // Posizionamento della ChiaveAccessoLabel 
        gbc.gridx = 0; // Colonna 0 nella griglia
        gbc.gridy = 2; // Riga 2 nella griglia
        gbc.anchor = GridBagConstraints.WEST; // Allineiamo a sinistra nella cella
        gbc.fill = GridBagConstraints.NONE; // La label non si espande per riempire la cella
        gbc.weightx = 0.0; // Non assegniamo peso orizzontale a questa colonna
        gbc.insets = new Insets(12, 0, 12, 10); // Spazio interno specifico per questa label
        centerContainer.add(chiaveAccessoLabel, gbc);
        
        
        
        // Posizionamento del ChiaveAccessoField 
        chiaveAccessoField = new JTextField(3); // Inizializziamo il JTextField con una larghezza preferita di 3 caratteri
        chiaveAccessoField.setText(""); // Campo di testo vuoto
        chiaveAccessoField.setPreferredSize(new Dimension(350, 45)); // Modifichiamo le dimensioni preferite del componente
        chiaveAccessoField.setBackground(new Color(60, 63, 66)); // Colore di sfondo del campo
        chiaveAccessoField.setForeground(Color.WHITE); // Colore del testo digitato in bianco
        chiaveAccessoField.setCaretColor(Color.WHITE); // Impostiamo il colore del cursore lampeggiante a bianco
        chiaveAccessoField.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Modifichiamo il font del testo
        chiaveAccessoField.setBorder(BorderFactory.createCompoundBorder( // Modifichiamo il bordo del campo
            BorderFactory.createLineBorder(new Color(90, 93, 96), 1), // Bordo linea sottile
            BorderFactory.createEmptyBorder(5, 10, 5, 10) // Bordo interno per spaziatura del testo
        ));
        
        
        
        gbc.gridx = 1; // Colonna 1 nella griglia
        gbc.gridy = 2; // Riga 2 nella griglia
        gbc.anchor = GridBagConstraints.WEST; // Allineiamo a sinistra nella cella
        gbc.fill = GridBagConstraints.HORIZONTAL; // Il campo si espande orizzontalmente
        gbc.weightx = 1.0; // Assegniamo peso a questa colonna
        gbc.insets = new Insets(12, 0, 12, 0); // Spazio interno per questo campo
        centerContainer.add(chiaveAccessoField, gbc);
        
        
        
        // --- JLabel "Errore di Login" ---
        loginErrorLabel = new JLabel("La combinazione di email, password e chiave d'accesso potrebbe essere errata");
        loginErrorLabel.setForeground(Color.RED); // Colore del testo rosso per l'errore
        loginErrorLabel.setFont(new Font("Segoe UI",Font.PLAIN, 16)); // Modifichiamo il font per il messaggio di errore
        gbc.gridx = 0; // Colonna 0 nella griglia
        gbc.gridy = 3; // Riga 3 nella griglia 
        gbc.gridwidth = 2; // La label occupa 2 colonne
        gbc.anchor = GridBagConstraints.CENTER; // Allineiamo al centro nella cella
        gbc.fill = GridBagConstraints.NONE; // Non facciamo espandere la label
        gbc.weightx = 0.0; // Non diamo peso orizzontale
        loginErrorLabel.setVisible(false); // Inizialmente la label di errore è nascosta
        centerContainer.add(loginErrorLabel, gbc);          
        
        
        
        // --- JLabel "Se sei un cliente accedi qui" (Link per passare al login cliente) ---
        goToAdminLabel = new JLabel("<html><u>Se sei un cliente accedi qui</u></html>"); // Testo con formattazione HTML per sottolineatura
        goToAdminLabel.setForeground(Color.WHITE); // Colore del testo bianco
        goToAdminLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Modifichiamo il font del testo
        gbc.gridx = 0; // Colonna 0 nella griglia
        gbc.gridy = 4; // Riga 4 nella griglia
        gbc.gridwidth = 2; // La label occupa 2 colonne
        gbc.anchor = GridBagConstraints.WEST; // Allineiamo a sinistra nella cella
        gbc.fill = GridBagConstraints.NONE; // Manteniamo NONE, poiché non vogliamo che si estenda
        gbc.weightx = 0.0; // Non diamo peso orizzontale
        gbc.insets = new Insets(10, 0, 12, 0); // Spazio interno per questa label

        
        
        // Associamo un listener per il click del mouse per navigare alla schermata di login del cliente
        GoToClientLogin_Listener adminLoginListener = new GoToClientLogin_Listener();
        goToAdminLabel.addMouseListener(adminLoginListener);
        centerContainer.add(goToAdminLabel, gbc);
        
        
        LoginRegister_Listener sendData = new LoginRegister_Listener(this);
        
        // --- JButton "Accedi" (Bottone per inviare i dati di login) ---
        loginButton = new JButton("Accedi");
        loginButton.setBackground(new Color(175, 207, 255)); // Sfondo del bottone (un blu chiaro)
        loginButton.setForeground(new Color(51, 103, 153)); // Testo del bottone (un blu più scuro)
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Modifichiamo il font del testo
        loginButton.setPreferredSize(new Dimension(100, 45)); // Modifichiamo la dimensione (larghezza, altezza)
        // Rimuoviamo il bordo di focus (il rettangolo che appare quando il pulsante è selezionato)
        loginButton.setFocusPainted(false); 
        gbc.gridx = 0; // Colonna 0 nella griglia
        gbc.gridy = 5; // Riga 5 nella griglia
        gbc.anchor = GridBagConstraints.EAST; // Allineiamo il bottone a destra nella cella
        gbc.weightx = 0.0; // Non diamo peso orizzontale
        gbc.insets = new Insets(10, 0, 12, 0); // Spazio interno specifico per il bottone

        // Associamo un listener per l'azione del bottone per inviare i dati dell'utente
        loginButton.addActionListener(sendData);
        centerContainer.add(loginButton, gbc);
        
        
        
        // --- Aggiunta dei Contenitori al Content Pane della Finestra Principale ---
        this.getContentPane().add(ovestContainer, BorderLayout.WEST); // Aggiungiamo il pannello OVEST nella regione OVEST
        this.getContentPane().add(centerContainer, BorderLayout.CENTER); // Aggiungiamo il pannello CENTRALE nella regione CENTRO
        
        // Rendiamo la finestra visibile solo dopo che tutti i componenti sono stati aggiunti e configurati
        this.setVisible(true); // Impostiamo la finestra come visibile
    }
	
    
    
    
    
   
    // Attiva la visualizzazione del messaggio di errore di login.
    // Rendiamo visibile la JLabel che contiene il testo di errore.
	public void activateError() { 
		loginErrorLabel.setVisible(true); // Impostiamo la visibilità della label di errore a true
	}
	
	
    
    // Restituisce il testo inserito nel campo email.
	public String getEmail() {
		return emailField.getText(); // Restituiamo il testo del campo email
	}
	
    
     
    // Restituisce il testo inserito nel campo password.
	public String getPassword() {
		return new String(passwordField.getPassword()); // Restituiamo il testo del campo password convertito in String
	}
	
    
    
    // Restituisce il testo inserito nel campo chiave d'accesso
	public String getChiaveAccesso() {
		return chiaveAccessoField.getText(); // Restituiamo il testo del campo chiave d'accesso
	}
}