package view;

import model.Biglietto;
import javax.swing.*;

import controller.DataController;
import enums.ActiveEntity_Enum;

import java.awt.*;

import listeners.FindEntities_Listener;
import listeners.FindEntity_Listener;
import listeners.goTo.GoToAdminLogin_Listener;
import listeners.goTo.GoToClientLogin_Listener;
import listeners.goTo.GoToClientRegister_Listener;


public class FindEntity_View extends JFrame {

	
	private JList<Object> entities;
	
	
	
    private JPanel ovestContainer;
    private JPanel centerContainer;
    private JScrollPane scrollPaneEntities;

    private JLabel registerTitleLabel;
    private JLabel accountinfoLabel;

    private JButton sideButton1;
    private JButton sideButton2;
    private JButton sideButton3;
    private JButton logoutButton;
    
    
    
    public FindEntity_View() {
        super("Registrazione Cliente - Aeroporto");
        this.setSize(1100,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        
        

        CreateSidebar(); // Tutta la creazione della Sidebar la facciamo fare dentro ad un metodo, per avere un costruttore più ordinato ed un insieme di cose più modulare.
        
        CreateCenterPanel(); // Tutta la creazione del panel centra la facciamo fare dentro ad un metodo, per avere tutto più ordinato e modulare
         
        this.getContentPane().add(ovestContainer, BorderLayout.WEST);
        this.getContentPane().add(centerContainer, BorderLayout.CENTER);
           
        // this.setVisible(true); Debug
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
        
        FindEntity_Listener utenteDetailsListener = new FindEntity_Listener(ActiveEntity_Enum.Cliente, DataController.getInstance().getIdUtente());
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
        
        
        
        GoToClientLogin_Listener clientLogin = new GoToClientLogin_Listener();
        logoutButton.addMouseListener(clientLogin);
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
        
        
        GoToAdminLogin_Listener adminLogin = new GoToAdminLogin_Listener();
        logoutButton.addMouseListener(adminLogin);
    }
    
    void CreateCenterPanel() {
        centerContainer = new JPanel();
        centerContainer.setLayout(new BorderLayout());
        centerContainer.setBackground(Color.WHITE);
        centerContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        entities = new JList<Object>(DataController.getInstance().getEntities().toArray()); // Instanziamo la lista generica
        entities.setFont(new Font("Segoe UI", Font.BOLD, 25));
        entities.setFixedCellHeight(40); // Imposta manualmente l'altezza delle righe
        
        scrollPaneEntities = new JScrollPane();
        scrollPaneEntities.setViewportView(entities);
        scrollPaneEntities.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneEntities.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerContainer.add(scrollPaneEntities, BorderLayout.CENTER);
    }
    
    public void setJList_Entities() { // Premi sul listener -> ViewController.getInstance().FindEntityView_Activate -> FindEntity_View.SetJList_Entities();
        entities = new JList<Object>(DataController.getInstance().getEntities().toArray()); // Instanziamo la lista generica!
        entities = new JList<Object>(DataController.getInstance().getEntities().toArray()); // Instanziamo la lista generica
        entities.setFont(new Font("Segoe UI", Font.BOLD, 18));
        entities.setFixedCellHeight(20); // Imposta manualmente l'altezza delle righe
        scrollPaneEntities.setViewportView(entities);
    }
}
	

