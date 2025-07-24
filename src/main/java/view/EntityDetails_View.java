package view;

import javax.swing.*;

import controller.DataController;
import enums.ActiveEntity_Enum;
import listeners.BuyBiglietto_Listener;
import listeners.DeleteEntity_Listener;
import listeners.Logout_Listener;
import listeners.OpenModifyVolo_Listener;
import listeners.SendEntityData_Listener;
import listeners.sidebar.ClienteDetails_Listener;
import listeners.sidebar.FindEntities_Listener;
import listeners.windowlisteners.CloseDBConn_Listener;
import model.*;

import java.awt.*;

public class EntityDetails_View extends JFrame {

    private JPanel ovestContainer;
    
    // Sidebar
    
    private JLabel registerTitleLabel;
    private JLabel accountinfoLabel;

    private JButton sideButton1;
    private JButton sideButton2;
    private JButton sideButton3;
    private JButton logoutButton;
    
	// Attributi Panel modifica Volo
    
    JPanel createVoloPanel;
    
    JTextField idAereo_field;
    JTextField idAeroportoPartenza_field;
    JTextField idAeroportoArrivo_field;
    JTextField prezzo_field;
    JTextField postiLiberi_field;
    JTextField orarioPartenza_field;
    JTextField orarioArrivo_field;
    
    JLabel insertError;

    // Attributi Panel Volo
    
    JPanel voloPanel;
    
    JLabel entitaVolo;
    JLabel idVolo;
    JLabel idAereo_Volo;
    JLabel idAeroportoPartenza;
    JLabel idAeroportoArrivo;
    JLabel prezzo;
    JLabel valido_volo;
    JLabel postiLiberi;
    JLabel orarioPartenza;
    JLabel orarioArrivo;
    
    // Attributi Panel Biglietto
    
    JPanel bigliettoPanel;
    
    JLabel entitaBiglietto;
    JLabel idBiglietto;
    JLabel idCliente_biglietto;
    JLabel idVolo_biglietto;
    JLabel valido_biglietto;
    
    // Attributi Panel Aeroporto
    
    JPanel aeroportoPanel;
    
    JLabel entitaAeroporto;
    JLabel idAeroporto;
    JLabel citta;
    JLabel nazione;
    JLabel numeroPiste;
    
    // Attributi Panel Aereo
    
    JPanel aereoPanel;
    
    JLabel entitaAereo;
    JLabel idAereo;
    JLabel idAeroportoResidenza;
    JLabel modello;
    JLabel capienza;
    
    // Attributi Panel Cliente
    
    JPanel clientePanel;
    
    JLabel entitaCliente;
    JLabel idCliente;
    JLabel email;
    JLabel password;
    JLabel nome;
    JLabel cognome;
    JLabel metodoPagamento;
    
    // Bottoni
    
    // Il riutilizzo dei bottoni è reso difficile dal BoxLayout che stiamo usando per i panel, ecco perchè tutti questi bottoni.
    JButton inserisciVoloButton;
	JButton modificaVoloButton;
	JButton deleteButton;
	JButton cancelVoloButton;
	JButton cancelBigliettoButton;
	JButton buyButton;
	    
    public EntityDetails_View() {
        super("Mostra dettagli delle entità");
        this.setSize(1100, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout()); // BORDER LAYOUT PER IL CONTENT PANE
        
        // Aggiungiamo alla finestra il listener per chiudere il DB all'uscita!
        CloseDBConn_Listener closeDBConn_Listener = new CloseDBConn_Listener();
        this.addWindowListener(closeDBConn_Listener);

        // --- Pannello di sinistra ---
        ovestContainer = new JPanel();
        ovestContainer.setLayout(new BoxLayout(ovestContainer, BoxLayout.Y_AXIS));
        ovestContainer.setBackground(new Color(230, 230, 230));
        ovestContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        registerTitleLabel = new JLabel("Dettagli");
        registerTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        registerTitleLabel.setForeground(new Color(51, 103, 153));
        registerTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ovestContainer.add(registerTitleLabel);
        ovestContainer.add(Box.createVerticalStrut(20));
        
        
        // METODO CHE CHIAMA IL PANNELLO DI DESTRA PER OGNI ENTITA SPECIFICA
        createPanel_InsertVolo();
        createPanel_DetailsVolo();
        createPanel_DetailsBiglietto();
        createPanel_DetailsAeroporto();
        createPanel_DetailsAereo();
        createPanel_DetailsCliente();
        
        createButtons(); // Creazione di tutti i bottoni
  
        CreateSidebar(); // Tutta la creazione della Sidebar la facciamo fare dentro ad un metodo, per avere un costruttore più ordinato ed un insieme di cose più modulare.
        this.getContentPane().add(ovestContainer, BorderLayout.WEST);
        
        createVoloPanel.setVisible(false);
        voloPanel.setVisible(false);
        bigliettoPanel.setVisible(false);
        aeroportoPanel.setVisible(false);
        aereoPanel.setVisible(false);
        clientePanel.setVisible(false);

        
        this.setVisible(true);
    }
    
    private void createButtons() {
    	
    	if(inserisciVoloButton == null) {
    		inserisciVoloButton = new JButton("Inserisci");
    	}
    	inserisciVoloButton.setForeground(new Color(51, 103, 153));
    	inserisciVoloButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
    	inserisciVoloButton.setFocusPainted(false);
    	
    	SendEntityData_Listener sendVolo_Listener = new SendEntityData_Listener();
    	inserisciVoloButton.addActionListener(sendVolo_Listener);
    	
    	if(modificaVoloButton == null) {
        	modificaVoloButton = new JButton("Modifica Volo");
    	}
    	modificaVoloButton.setForeground(new Color(51, 103, 153));
    	modificaVoloButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
    	modificaVoloButton.setFocusPainted(false);
    	
    	OpenModifyVolo_Listener modifyVolo_Listener = new OpenModifyVolo_Listener();
    	modificaVoloButton.addActionListener(modifyVolo_Listener);
    	
    	if(deleteButton == null) {
        	deleteButton = new JButton("Elimina");
    	}
    	
    	deleteButton.setBackground(Color.RED);
    	deleteButton.setForeground(Color.WHITE);
    	deleteButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
    	deleteButton.setFocusPainted(false);
    	
    	DeleteEntity_Listener deleteEntity_Listener = new DeleteEntity_Listener(ActiveEntity_Enum.Cliente);
    	deleteButton.addActionListener(deleteEntity_Listener);
    	
    	if(cancelVoloButton == null) {
    		cancelVoloButton = new JButton("Annulla volo");
    	}
    	
    	cancelVoloButton.setBackground(Color.RED);
    	cancelVoloButton.setForeground(Color.WHITE);
    	cancelVoloButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
    	cancelVoloButton.setFocusPainted(false);
    	
    	DeleteEntity_Listener cancelVolo_listener = new DeleteEntity_Listener(ActiveEntity_Enum.Volo); // Riutilizziamo DeleteEntity_Listener e all'interno chiamiamo un metodo differente
    	cancelVoloButton.addActionListener(cancelVolo_listener);
    	
    	if(cancelBigliettoButton == null) {
    		cancelBigliettoButton = new JButton("Annulla biglietto");
    	}
    	
    	cancelBigliettoButton.setBackground(Color.RED);
    	cancelBigliettoButton.setForeground(Color.WHITE);
    	cancelBigliettoButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
    	cancelBigliettoButton.setFocusPainted(false);
    	
    	DeleteEntity_Listener cancelBiglietto_Listener = new DeleteEntity_Listener(ActiveEntity_Enum.Biglietto);
    	cancelBigliettoButton.addActionListener(cancelBiglietto_Listener);
    	
    	if(buyButton == null) {
        	buyButton = new JButton("Acquista un biglietto");
    	}
    	
    	buyButton.setForeground(new Color(51, 103, 153));
    	buyButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
    	buyButton.setFocusPainted(false);
    	
    	BuyBiglietto_Listener buyBiglietto_Listener = new BuyBiglietto_Listener();
    	buyButton.addActionListener(buyBiglietto_Listener);
    	
    	// Aggiunta bottoni ai panel
    	
    	createVoloPanel.add(Box.createVerticalStrut(70));
    	createVoloPanel.add(inserisciVoloButton);
    	
    	voloPanel.add(Box.createVerticalStrut(70));
    	voloPanel.add(cancelVoloButton);
    	voloPanel.add(Box.createVerticalStrut(20));
    	voloPanel.add(modificaVoloButton);
    	voloPanel.add(Box.createVerticalStrut(20));
    	voloPanel.add(buyButton);
    	
    	clientePanel.add(Box.createVerticalStrut(70));
    	clientePanel.add(deleteButton);
    	
    	bigliettoPanel.add(Box.createVerticalStrut(70));
    	bigliettoPanel.add(cancelBigliettoButton);
    }
    
    private void createPanel_InsertVolo() {
    	
        createVoloPanel = new JPanel();
        createVoloPanel.setLayout(new BoxLayout(createVoloPanel, BoxLayout.Y_AXIS));
        createVoloPanel.setBackground(new Color(40, 44, 52));
        createVoloPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));

        idAereo_field = new JTextField("Id Aereo:");
        idAereo_field.setForeground(Color.BLACK);
        idAereo_field.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idAereo_field.setAlignmentX(Component.LEFT_ALIGNMENT);

        idAeroportoPartenza_field = new JTextField("Id Aeroporto Partenza:");
        idAeroportoPartenza_field.setForeground(Color.BLACK);
        idAeroportoPartenza_field.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idAeroportoPartenza_field.setAlignmentX(Component.LEFT_ALIGNMENT);

        idAeroportoArrivo_field = new JTextField("Id Aeroporto Arrivo: ");
        idAeroportoArrivo_field.setForeground(Color.BLACK);
        idAeroportoArrivo_field.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idAeroportoArrivo_field.setAlignmentX(Component.LEFT_ALIGNMENT);

        prezzo_field = new JTextField("Prezzo: ");
        prezzo_field.setForeground(Color.BLACK);
        prezzo_field.setFont(new Font("Segoe UI", Font.BOLD, 16));
        prezzo_field.setAlignmentX(Component.LEFT_ALIGNMENT);

        postiLiberi_field = new JTextField("Posti Liberi: ");
        postiLiberi_field.setForeground(Color.BLACK);
        postiLiberi_field.setFont(new Font("Segoe UI", Font.BOLD, 16));
        postiLiberi_field.setAlignmentX(Component.LEFT_ALIGNMENT);

        orarioPartenza_field = new JTextField("Orario Partenza: ");
        orarioPartenza_field.setForeground(Color.BLACK);
        orarioPartenza_field.setFont(new Font("Segoe UI", Font.BOLD, 16));
        orarioPartenza_field.setAlignmentX(Component.LEFT_ALIGNMENT);

        orarioArrivo_field = new JTextField("Orario Arrivo: ");
        orarioArrivo_field.setForeground(Color.BLACK);
        orarioArrivo_field.setFont(new Font("Segoe UI", Font.BOLD, 16));
        orarioArrivo_field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        insertError = new JLabel();
        insertError.setForeground(Color.RED);
        insertError.setFont(new Font("Segoe UI", Font.BOLD, 16));
        insertError.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        insertError.setVisible(false);
        
        createVoloPanel.add(Box.createVerticalStrut(10));
        createVoloPanel.add(idAereo_field);
        createVoloPanel.add(Box.createVerticalStrut(10));
        createVoloPanel.add(idAeroportoPartenza_field);
        createVoloPanel.add(Box.createVerticalStrut(10));
        createVoloPanel.add(idAeroportoArrivo_field);
        createVoloPanel.add(Box.createVerticalStrut(10));
        createVoloPanel.add(prezzo_field);
        createVoloPanel.add(Box.createVerticalStrut(10));
        createVoloPanel.add(postiLiberi_field);
        createVoloPanel.add(Box.createVerticalStrut(10));
        createVoloPanel.add(orarioPartenza_field);
        createVoloPanel.add(Box.createVerticalStrut(10));
        createVoloPanel.add(orarioArrivo_field);
        createVoloPanel.add(Box.createVerticalStrut(30));
        createVoloPanel.add(insertError);
    	
    }

    private void createPanel_DetailsVolo() {
    	
        voloPanel = new JPanel();
        voloPanel.setLayout(new BoxLayout(voloPanel, BoxLayout.Y_AXIS));
        voloPanel.setBackground(new Color(40, 44, 52));
        voloPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
        
        entitaVolo = new JLabel("Volo");
        entitaVolo.setForeground(new Color(51, 103, 153));
        entitaVolo.setFont(new Font("Segoe UI", Font.BOLD, 40));
        entitaVolo.setAlignmentX(Component.LEFT_ALIGNMENT);

        idVolo = new JLabel("Id:");
        idVolo.setForeground(Color.WHITE);
        idVolo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idVolo.setAlignmentX(Component.LEFT_ALIGNMENT);

        idAereo_Volo = new JLabel("Id Aereo:");
        idAereo_Volo.setForeground(Color.WHITE);
        idAereo_Volo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idAereo_Volo.setAlignmentX(Component.LEFT_ALIGNMENT);

        idAeroportoPartenza = new JLabel("Id Aeroporto Partenza:");
        idAeroportoPartenza.setForeground(Color.WHITE);
        idAeroportoPartenza.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idAeroportoPartenza.setAlignmentX(Component.LEFT_ALIGNMENT);

        idAeroportoArrivo = new JLabel("Id Aeroporto Arrivo: ");
        idAeroportoArrivo.setForeground(Color.WHITE);
        idAeroportoArrivo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idAeroportoArrivo.setAlignmentX(Component.LEFT_ALIGNMENT);

        prezzo = new JLabel("Prezzo: ");
        prezzo.setForeground(Color.WHITE);
        prezzo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        prezzo.setAlignmentX(Component.LEFT_ALIGNMENT);

        valido_volo = new JLabel("Valido: ");
        valido_volo.setForeground(Color.WHITE);
        valido_volo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        valido_volo.setAlignmentX(Component.LEFT_ALIGNMENT);

        postiLiberi = new JLabel("Posti Liberi: ");
        postiLiberi.setForeground(Color.WHITE);
        postiLiberi.setFont(new Font("Segoe UI", Font.BOLD, 16));
        postiLiberi.setAlignmentX(Component.LEFT_ALIGNMENT);

        orarioPartenza = new JLabel("Orario Partenza: ");
        orarioPartenza.setForeground(Color.WHITE);
        orarioPartenza.setFont(new Font("Segoe UI", Font.BOLD, 16));
        orarioPartenza.setAlignmentX(Component.LEFT_ALIGNMENT);

        orarioArrivo = new JLabel("Orario Arrivo: ");
        orarioArrivo.setForeground(Color.WHITE);
        orarioArrivo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        orarioArrivo.setAlignmentX(Component.LEFT_ALIGNMENT);


        // Aggiungiamo le etichette al pannello centerContainer
        voloPanel.add(Box.createVerticalStrut(30));
        voloPanel.add(idVolo);
        voloPanel.add(Box.createVerticalStrut(10));
        voloPanel.add(idAereo_Volo);
        voloPanel.add(Box.createVerticalStrut(10));
        voloPanel.add(Box.createVerticalStrut(10));
        voloPanel.add(idAeroportoPartenza);
        voloPanel.add(Box.createVerticalStrut(10));
        voloPanel.add(idAeroportoArrivo);
        voloPanel.add(Box.createVerticalStrut(10));
        voloPanel.add(prezzo);
        voloPanel.add(Box.createVerticalStrut(10));
        voloPanel.add(valido_volo);
        voloPanel.add(Box.createVerticalStrut(10));
        voloPanel.add(postiLiberi);
        voloPanel.add(Box.createVerticalStrut(10));
        voloPanel.add(orarioPartenza);
        voloPanel.add(Box.createVerticalStrut(10));
        voloPanel.add(orarioArrivo);
        
        

    }

    private void createPanel_DetailsBiglietto() {
    	
    	bigliettoPanel = new JPanel();
    	bigliettoPanel.setLayout(new BoxLayout(bigliettoPanel, BoxLayout.Y_AXIS));
    	bigliettoPanel.setBackground(new Color(40, 44, 52));
    	bigliettoPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
        
        entitaBiglietto = new JLabel("Biglietto");
        entitaBiglietto.setForeground(new Color(51, 103, 153));
        entitaBiglietto.setFont(new Font("Segoe UI", Font.BOLD, 40));
        entitaBiglietto.setAlignmentX(Component.LEFT_ALIGNMENT);

        idBiglietto = new JLabel("Id: ");
        idBiglietto.setForeground(Color.WHITE);
        idBiglietto.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idBiglietto.setAlignmentX(Component.LEFT_ALIGNMENT);

        idCliente_biglietto = new JLabel("Id Cliente: ");
        idCliente_biglietto.setForeground(Color.WHITE);
        idCliente_biglietto.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idCliente_biglietto.setAlignmentX(Component.LEFT_ALIGNMENT);

        idVolo_biglietto = new JLabel("Id Volo: ");
        idVolo_biglietto.setForeground(Color.WHITE);
        idVolo_biglietto.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idVolo_biglietto.setAlignmentX(Component.LEFT_ALIGNMENT);

        valido_biglietto = new JLabel("Valido: ");
        valido_biglietto.setForeground(Color.WHITE);
        valido_biglietto.setFont(new Font("Segoe UI", Font.BOLD, 16));
        valido_biglietto.setAlignmentX(Component.LEFT_ALIGNMENT);


        bigliettoPanel.add(entitaBiglietto);
        bigliettoPanel.add(Box.createVerticalStrut(30)); // Spazio dopo il titolo
        bigliettoPanel.add(idBiglietto);
        bigliettoPanel.add(Box.createVerticalStrut(10)); // Spazio tra le etichette
        bigliettoPanel.add(idCliente_biglietto);
        bigliettoPanel.add(Box.createVerticalStrut(10));
        bigliettoPanel.add(idVolo_biglietto);
        bigliettoPanel.add(Box.createVerticalStrut(10));
        bigliettoPanel.add(valido_biglietto);
        
        
    }

    private void createPanel_DetailsAeroporto() {
    	
    	aeroportoPanel = new JPanel();
    	aeroportoPanel.setLayout(new BoxLayout(aeroportoPanel, BoxLayout.Y_AXIS));
    	aeroportoPanel.setBackground(new Color(40, 44, 52));
    	aeroportoPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
    	
        entitaAeroporto = new JLabel("Aeroporto");
        entitaAeroporto.setForeground(new Color(51, 103, 153));
        entitaAeroporto.setFont(new Font("Segoe UI", Font.BOLD, 40));
        entitaAeroporto.setAlignmentX(Component.LEFT_ALIGNMENT);

        idAeroporto = new JLabel("Id Aeroporto: ");
        idAeroporto.setForeground(Color.WHITE);
        idAeroporto.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idAeroporto.setAlignmentX(Component.LEFT_ALIGNMENT);

        citta = new JLabel("Città: ");
        citta.setForeground(Color.WHITE);
        citta.setFont(new Font("Segoe UI", Font.BOLD, 16));
        citta.setAlignmentX(Component.LEFT_ALIGNMENT);

        nazione = new JLabel("Nazione: ");
        nazione.setForeground(Color.WHITE);
        nazione.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nazione.setAlignmentX(Component.LEFT_ALIGNMENT);

        numeroPiste = new JLabel("Numero Piste: ");
        numeroPiste.setForeground(Color.WHITE);
        numeroPiste.setFont(new Font("Segoe UI", Font.BOLD, 16));
        numeroPiste.setAlignmentX(Component.LEFT_ALIGNMENT);

        aeroportoPanel.add(entitaAeroporto);
        aeroportoPanel.add(Box.createVerticalStrut(30));
        aeroportoPanel.add(idAeroporto);
        aeroportoPanel.add(Box.createVerticalStrut(10));
        aeroportoPanel.add(citta);
        aeroportoPanel.add(Box.createVerticalStrut(10));
        aeroportoPanel.add(nazione);
        aeroportoPanel.add(Box.createVerticalStrut(10));
        aeroportoPanel.add(numeroPiste);
        
        
    }

    private void createPanel_DetailsAereo() {
    	
    	aereoPanel = new JPanel();
    	aereoPanel.setLayout(new BoxLayout(aereoPanel, BoxLayout.Y_AXIS));
    	aereoPanel.setBackground(new Color(40, 44, 52));
    	aereoPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
    	
        entitaAereo = new JLabel("Aereo");
        entitaAereo.setForeground(new Color(51, 103, 153));
        entitaAereo.setFont(new Font("Segoe UI", Font.BOLD, 40));
        entitaAereo.setAlignmentX(Component.LEFT_ALIGNMENT);

        idAereo = new JLabel("Id Aereo: ");
        idAereo.setForeground(Color.WHITE);
        idAereo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idAereo.setAlignmentX(Component.LEFT_ALIGNMENT);

        idAeroportoResidenza = new JLabel("Id Aeroporto Residenza:");
        idAeroportoResidenza.setForeground(Color.WHITE);
        idAeroportoResidenza.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idAeroportoResidenza.setAlignmentX(Component.LEFT_ALIGNMENT);

        modello = new JLabel("Modello: ");
        modello.setForeground(Color.WHITE);
        modello.setFont(new Font("Segoe UI", Font.BOLD, 16));
        modello.setAlignmentX(Component.LEFT_ALIGNMENT);

        capienza = new JLabel("Capienza: ");
        capienza.setForeground(Color.WHITE);
        capienza.setFont(new Font("Segoe UI", Font.BOLD, 16));
        capienza.setAlignmentX(Component.LEFT_ALIGNMENT);

        aereoPanel.add(entitaAereo);
        aereoPanel.add(Box.createVerticalStrut(30));
        aereoPanel.add(idAereo);
        aereoPanel.add(Box.createVerticalStrut(10));
        aereoPanel.add(idAeroportoResidenza);
        aereoPanel.add(Box.createVerticalStrut(10));
        aereoPanel.add(modello);
        aereoPanel.add(Box.createVerticalStrut(10));
        aereoPanel.add(capienza);
        
    }

    private void createPanel_DetailsCliente() {

    	clientePanel = new JPanel();
    	clientePanel.setLayout(new BoxLayout(clientePanel, BoxLayout.Y_AXIS));
    	clientePanel.setBackground(new Color(40, 44, 52));
    	clientePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
    	
        entitaCliente = new JLabel("Cliente");
        entitaCliente.setForeground(new Color(51, 103, 153));
        entitaCliente.setFont(new Font("Segoe UI", Font.BOLD, 40));
        entitaCliente.setAlignmentX(Component.LEFT_ALIGNMENT);

        idCliente = new JLabel("Id Cliente: ");
        idCliente.setForeground(Color.WHITE);
        idCliente.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idCliente.setAlignmentX(Component.LEFT_ALIGNMENT);

        email = new JLabel("Email: ");
        email.setForeground(Color.WHITE);
        email.setFont(new Font("Segoe UI", Font.BOLD, 16));
        email.setAlignmentX(Component.LEFT_ALIGNMENT);

        password = new JLabel("Password: ");
        password.setForeground(Color.WHITE);
        password.setFont(new Font("Segoe UI", Font.BOLD, 16));
        password.setAlignmentX(Component.LEFT_ALIGNMENT);

        nome = new JLabel("Nome: ");
        nome.setForeground(Color.WHITE);
        nome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nome.setAlignmentX(Component.LEFT_ALIGNMENT);

        cognome = new JLabel("Cognome: ");
        cognome.setForeground(Color.WHITE);
        cognome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        cognome.setAlignmentX(Component.LEFT_ALIGNMENT);

        metodoPagamento = new JLabel("Metodo di Pagamento: ");
        metodoPagamento.setForeground(Color.WHITE);
        metodoPagamento.setFont(new Font("Segoe UI", Font.BOLD, 16));
        metodoPagamento.setAlignmentX(Component.LEFT_ALIGNMENT);

        clientePanel.add(entitaCliente);
        clientePanel.add(Box.createVerticalStrut(30));
        clientePanel.add(idCliente);
        clientePanel.add(Box.createVerticalStrut(10));
        clientePanel.add(email);
        clientePanel.add(Box.createVerticalStrut(10));
        clientePanel.add(password);
        clientePanel.add(Box.createVerticalStrut(10));
        clientePanel.add(nome);
        clientePanel.add(Box.createVerticalStrut(10));
        clientePanel.add(cognome);
        clientePanel.add(Box.createVerticalStrut(10));
        clientePanel.add(metodoPagamento);
        
    }
    
    public void disableActivePanel() {
    	insertError.setVisible(false);
        this.getContentPane().removeAll();
        this.getContentPane().add(ovestContainer, BorderLayout.WEST);
    }
    
    public void activateCreateVoloPanel(Volo volo) { // usiamo il .toString() per passare i dati in stringa
        idAereo_field.setText(Integer.toString(volo.getId_aereo()));
        idAeroportoPartenza_field.setText(Integer.toString(volo.getId_aeroporto_partenza()));
        idAeroportoArrivo_field.setText(Integer.toString(volo.getId_aeroporto_arrivo()));
        prezzo_field.setText(Float.toString(volo.getPrezzo()));
        postiLiberi_field.setText(Integer.toString(volo.getPosti_liberi()));
        orarioPartenza_field.setText(volo.getOrario_partenza());
        orarioArrivo_field.setText(volo.getOrario_arrivo());
        
        createVoloPanel.setVisible(true);
        
    	disableActivePanel();
    	this.getContentPane().add(createVoloPanel, BorderLayout.CENTER);
    }
    
    public void activateVoloPanel(Volo volo) {
        idVolo.setText("Id: " + volo.getId());
        idAereo_Volo.setText("Id Aereo: "+ volo.getId_aereo());
        idAeroportoPartenza.setText("Id aeroporto di partenza: " + volo.getId_aeroporto_partenza());
        idAeroportoArrivo.setText("Id aeroporto di arrivo: " + volo.getId_aeroporto_arrivo());
        prezzo.setText("Prezzo biglietto: " + volo.getPrezzo());
        valido_volo.setText("Validità: " + volo.isValido());
        postiLiberi.setText("Posti Liberi: " + volo.getPosti_liberi());
        orarioPartenza.setText("Orario di partenza: " + volo.getOrario_partenza());
        orarioArrivo.setText("Orario di arrivo: " + volo.getOrario_arrivo());
        
        if(DataController.getInstance().isClient()) { // L'utente loggato è un cliente
        	buyButton.setVisible(true);
        	cancelVoloButton.setVisible(false);
        	modificaVoloButton.setVisible(false);
        } else { // L'utente loggato è un amministratore
        	buyButton.setVisible(false);
        	modificaVoloButton.setVisible(true);
        	if(getVoloValido()) { // Attiviamo il bottone per annullare il volo solo se il volo in questione è ancora attivo!
        		cancelVoloButton.setVisible(true);
        	}
        }
        
    	voloPanel.setVisible(true);
    	
    	disableActivePanel();
    	this.getContentPane().add(voloPanel, BorderLayout.CENTER);
    }
    
    public void activateBigliettoPanel(Biglietto biglietto) {
        idBiglietto.setText("Id: " + biglietto.getId());
        idCliente_biglietto.setText("Id cliente: " + biglietto.getId_cliente());
        idVolo_biglietto.setText("Id volo: " + biglietto.getId_volo());
        valido_biglietto.setText("Valido: " + biglietto.isValido());
        bigliettoPanel.setVisible(true);
        disableActivePanel();
        
        if(getBigliettoValido()) {
        	cancelBigliettoButton.setVisible(true);
        } else {
        	cancelBigliettoButton.setVisible(false);
        }
        
        this.getContentPane().add(bigliettoPanel, BorderLayout.CENTER);
    }
    
    public void activateAeroportoPanel(Aeroporto aeroporto) {
        idAeroporto.setText("Id: " + aeroporto.getId());
        citta.setText("Città: " + aeroporto.getCitta());
        nazione.setText("Nazione: " + aeroporto.getNazione());
        numeroPiste.setText("Numero piste: " + aeroporto.getNumero_piste());
        aeroportoPanel.setVisible(true);
        System.out.println(aeroportoPanel.isVisible());
        disableActivePanel();
        this.getContentPane().add(aeroportoPanel, BorderLayout.CENTER);
    }
    
    public void activateAereoPanel(Aereo aereo) {
        idAereo.setText("Id: " + aereo.getId());
        idAeroportoResidenza.setText("Id Aeroporto residenza: " + aereo.getId_aeroporto_residenza());
        modello.setText("Modello: " + aereo.getModello());
        capienza.setText("Capienza: " + aereo.getCapienza());
        aereoPanel.setVisible(true);
        disableActivePanel();
        this.getContentPane().add(aereoPanel, BorderLayout.CENTER);
    }
    
    public void activateClientePanel(Cliente cliente) {
        idCliente.setText("Id: " + cliente.getId());
        email.setText("Email: " + cliente.getEmail());
        password.setText("Password: " + cliente.getPassword());
        nome.setText("Nome: " + cliente.getNome());
        cognome.setText("Cognome: " + cliente.getCognome());
        metodoPagamento.setText("Metodo di pagamento: " + cliente.getMetodo_pagamento());
        clientePanel.setVisible(true);
        disableActivePanel();
        this.getContentPane().add(clientePanel, BorderLayout.CENTER);
    }
    
    void CreateSidebar() {
    	
    	// Grafica da mostrare per entrambi i ruoli
    	
        ovestContainer = new JPanel();
        ovestContainer.setLayout(new BoxLayout(ovestContainer, BoxLayout.Y_AXIS));
        ovestContainer.setBackground(new Color(230, 230, 230));
        ovestContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));


        if(DataController.getInstance().isClient()) {
            registerTitleLabel = new JLabel("Ciao, " + DataController.getInstance().getNomeUtente() + "!");
        } else {
            registerTitleLabel = new JLabel("Ciao, amministratore");
        }
        registerTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        registerTitleLabel.setForeground(new Color(51, 103, 153));
        registerTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ovestContainer.add(registerTitleLabel);
        ovestContainer.add(Box.createVerticalStrut(20));

        
        if(DataController.getInstance().isClient()) { // Controlliamo che ruolo ha fatto l'accesso, ed in base a quello modifichiamo i bottoni mostrati sotto.
        	CreateSidebar_Client();
        } else {
        	CreateSidebar_Admin();
        }
        
    }
    
    void CreateSidebar_Client() {
    	accountinfoLabel = new JLabel("Acquista o vedi biglietti");
        accountinfoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        accountinfoLabel.setForeground(new Color(95, 99, 104));
        accountinfoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ovestContainer.add(accountinfoLabel);
        ovestContainer.add(Box.createVerticalStrut(115));

        
        
        sideButton1 = new JButton ("Voli");
        sideButton1.setBackground(new Color(175, 207, 255));
        sideButton1.setForeground(new Color(51, 103, 153));
        sideButton1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sideButton1.setPreferredSize(new Dimension(125, 45));
        // Imposta la dimensione massima per corrispondere a quella preferita 
        sideButton1.setMaximumSize(new Dimension(250, 50)); // Lo usiamo quando si usa il BoxLayout
       
        sideButton1.setFocusPainted(false);
        
        FindEntities_Listener findVoliListener = new FindEntities_Listener(ActiveEntity_Enum.Volo);
        sideButton1.addActionListener(findVoliListener);
        
        ovestContainer.add(sideButton1);
        ovestContainer.add(Box.createVerticalStrut(20));
        
        
        
        sideButton2 = new JButton("I miei biglietti");
        sideButton2.setBackground(new Color(175, 207, 255));
        sideButton2.setForeground(new Color(51, 103, 153));
        sideButton2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sideButton2.setPreferredSize(new Dimension(125, 45));
        // Imposta la dimensione massima per corrispondere a quella preferita ()
        sideButton2.setMaximumSize(new Dimension(250, 50)); // Lo usiamo quando si usa il BoxLayout
        
        sideButton2.setFocusPainted(false);
        
        FindEntities_Listener findBigliettiListener = new FindEntities_Listener(ActiveEntity_Enum.Biglietto);
        sideButton2.addActionListener(findBigliettiListener);
        
        ovestContainer.add(sideButton2);
        ovestContainer.add(Box.createVerticalStrut(20));
        
        
        
        sideButton3 = new JButton("Il mio account");
        sideButton3.setBackground(new Color(175, 207, 255));
        sideButton3.setForeground(new Color(51, 103, 153));
        sideButton3.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sideButton3.setPreferredSize(new Dimension(125, 45));
        // Imposta la dimensione massima per corrispondere a quella preferita ()
        sideButton3.setMaximumSize(new Dimension(250, 50)); // Lo usiamo quando si usa il BoxLayout
        
        sideButton3.setFocusPainted(false);
        
        ClienteDetails_Listener utenteDetailsListener = new ClienteDetails_Listener();
        sideButton3.addActionListener(utenteDetailsListener);
        
        ovestContainer.add(sideButton3);
        ovestContainer.add(Box.createVerticalStrut(20));
        
        
        
        logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        logoutButton.setPreferredSize(new Dimension(200, 60));
        // Imposta la dimensione massima per corrispondere a quella preferita ()
        logoutButton.setMaximumSize(new Dimension(250, 50)); // Lo usiamo quando si usa il BoxLayout
       
        logoutButton.setFocusPainted(false);
        ovestContainer.add(logoutButton);
        ovestContainer.add(Box.createVerticalStrut(20));
        
        
        
        Logout_Listener logoutListener = new Logout_Listener();
        logoutButton.addActionListener(logoutListener);
    }
    
    void CreateSidebar_Admin() {
    	accountinfoLabel = new JLabel("Aggiungi, modifica o visualizza le entità presenti nel database.");
        accountinfoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        accountinfoLabel.setForeground(new Color(95, 99, 104));
        accountinfoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ovestContainer.add(accountinfoLabel);
        ovestContainer.add(Box.createVerticalStrut(80));

        
        
        sideButton1 = new JButton ("Voli");
        sideButton1.setBackground(new Color(175, 207, 255));
        sideButton1.setForeground(new Color(51, 103, 153));
        sideButton1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sideButton1.setPreferredSize(new Dimension(125, 45));
        sideButton1.setAlignmentX(Component.LEFT_ALIGNMENT);
        sideButton1.setFocusPainted(false);
        
        FindEntities_Listener findVoloListener = new FindEntities_Listener(ActiveEntity_Enum.Volo);
        sideButton1.addActionListener(findVoloListener);
        
        ovestContainer.add(sideButton1);
        ovestContainer.add(Box.createVerticalStrut(20));
        
        
        sideButton2 = new JButton("Aerei");
        sideButton2.setBackground(new Color(175, 207, 255));
        sideButton2.setForeground(new Color(51, 103, 153));
        sideButton2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sideButton2.setPreferredSize(new Dimension(125, 45));
        sideButton2.setFocusPainted(false);
        
        FindEntities_Listener findAereoListener = new FindEntities_Listener(ActiveEntity_Enum.Aereo);
        sideButton2.addActionListener(findAereoListener);
        
        ovestContainer.add(sideButton2);
        ovestContainer.add(Box.createVerticalStrut(20));
        
        
        
        sideButton3 = new JButton("Aeroporti");
        sideButton3.setBackground(new Color(175, 207, 255));
        sideButton3.setForeground(new Color(51, 103, 153));
        sideButton3.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sideButton3.setPreferredSize(new Dimension(125, 45));
        sideButton3.setFocusPainted(false);
        
        FindEntities_Listener findAeroportoListener = new FindEntities_Listener(ActiveEntity_Enum.Aeroporto);
        sideButton3.addActionListener(findAeroportoListener);
        
        ovestContainer.add(sideButton3);
        ovestContainer.add(Box.createVerticalStrut(20));
        
        
        
        logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        logoutButton.setPreferredSize(new Dimension(125, 45));
        logoutButton.setFocusPainted(false);
        ovestContainer.add(logoutButton);
        ovestContainer.add(Box.createVerticalStrut(20));
        
        
        Logout_Listener logoutListener = new Logout_Listener();
        logoutButton.addActionListener(logoutListener);
    }
    
    public int getVoloId() {
    	// idVolo = "Id: 2", con getText().split(":") otteniamo due stringhe "Id:" e " 2"
    	String[] strings = idVolo.getText().split(":"); // Dividiamo la stringa in fondamentalmente label a sinistra e valore a destra messi in un array, perchè altrimenti ci darà una stringa del tipo "Id: 2" che non è un intero!
    	int id;
    	if(strings.length == 1) {
    		id = -1;
    	} else {
    		id = Integer.parseInt(strings[1].trim()); // Rimuove gli spazi vuoti all'inizio e alla fine della stringa. Molto importante per evitare dati sbagliati.
    	}
    	return id;
    }
    
    public boolean getVoloValido() {
    	String[] strings = valido_volo.getText().split(":");
    	boolean valido = Boolean.parseBoolean(strings[1].trim());
    	return valido;
    }
    
    public int getBigliettoId() {
    	String[] strings = idBiglietto.getText().split(":");
    	int id = Integer.parseInt(strings[1].trim());
    	return id;
    }
    
    public boolean getBigliettoValido() {
    	String[] strings = valido_biglietto.getText().split(":");
    	boolean valido = Boolean.parseBoolean(strings[1].trim());
    	return valido;
    }
    
    public String getIdAereo_field() {
    	return idAereo_field.getText();
    }
    
    public String getIdAeroportoPartenza_field() {
    	return idAeroportoPartenza_field.getText();
    }
    
    public String getIdAeroportoArrivo_field() {
    	return idAeroportoArrivo_field.getText();
    }
    
    public String getPrezzo_field() {
    	return prezzo_field.getText();
    }
    
    public String getPostiLiberi_field() {
    	return postiLiberi_field.getText();
    }
    
    public String getOrarioPartenza_field() {
    	return orarioPartenza_field.getText();
    }
    
    public String getOrarioArrivo_field() {
    	return orarioArrivo_field.getText();
    }
    
    public void showError(String text) {
    	insertError.setText(text);
    	insertError.setVisible(true);
    }
}