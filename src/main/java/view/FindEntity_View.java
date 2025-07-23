package view;

import model.Biglietto;
import javax.swing.*;

import controller.DataController;

import java.awt.*;

import listeners.goTo.GoToAdminLogin_Listener;
import listeners.goTo.GoToClientLogin_Listener;
import listeners.goTo.GoToClientRegister_Listener;


public class FindEntity_View extends JFrame {

	
	private JList<Biglietto> listaBiglietti;
	
	
	
    private JPanel ovestContainer;
    private JPanel centerContainer;
    private JScrollPane scrollPaneBiglietti;

    private JLabel registerTitleLabel;
    private JLabel accountinfoLabel;

    private JButton findVoloButton;
    private JButton findBigliettiButton;
    private JButton accountDetailsButton;
    private JButton logoutButton;
    
    
    
    public FindEntity_View() {
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




        registerTitleLabel = new JLabel("Ciao, " + DataController.getInstance().getNomeUtente());

        // egisterTitleLabel = new JLabel("Home");

        registerTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        registerTitleLabel.setForeground(new Color(51, 103, 153));
        registerTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ovestContainer.add(registerTitleLabel);
        ovestContainer.add(Box.createVerticalStrut(20));

        

        accountinfoLabel = new JLabel("Acquista o vedi biglietti");
        accountinfoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        accountinfoLabel.setForeground(new Color(95, 99, 104));
        accountinfoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ovestContainer.add(accountinfoLabel);
        ovestContainer.add(Box.createVerticalStrut(115));

        
        
        findVoloButton = new JButton ("Voli");
        findVoloButton.setBackground(new Color(175, 207, 255));
        findVoloButton.setForeground(new Color(51, 103, 153));
        findVoloButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        findVoloButton.setPreferredSize(new Dimension(125, 45));
        // Imposta la dimensione massima per corrispondere a quella preferita 
        findVoloButton.setMaximumSize(new Dimension(250, 50)); // Lo usiamo quando si usa il BoxLayout
       
        findVoloButton.setFocusPainted(false);
        ovestContainer.add(findVoloButton);
        ovestContainer.add(Box.createVerticalStrut(20));
        
        
        
        findBigliettiButton = new JButton("I miei biglietti");
        findBigliettiButton.setBackground(new Color(175, 207, 255));
        findBigliettiButton.setForeground(new Color(51, 103, 153));
        findBigliettiButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        findBigliettiButton.setPreferredSize(new Dimension(125, 45));
        // Imposta la dimensione massima per corrispondere a quella preferita ()
        findBigliettiButton.setMaximumSize(new Dimension(250, 50)); // Lo usiamo quando si usa il BoxLayout
        
        findBigliettiButton.setFocusPainted(false);
        ovestContainer.add(findBigliettiButton);
        ovestContainer.add(Box.createVerticalStrut(20));
        
        
        
        accountDetailsButton = new JButton("Il mio account");
        accountDetailsButton.setBackground(new Color(175, 207, 255));
        accountDetailsButton.setForeground(new Color(51, 103, 153));
        accountDetailsButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        accountDetailsButton.setPreferredSize(new Dimension(125, 45));
        // Imposta la dimensione massima per corrispondere a quella preferita ()
        accountDetailsButton.setMaximumSize(new Dimension(250, 50)); // Lo usiamo quando si usa il BoxLayout
        
        accountDetailsButton.setFocusPainted(false);
        ovestContainer.add(accountDetailsButton);
        ovestContainer.add(Box.createVerticalStrut(20));
        
        
        
        logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(175, 207, 255));
        logoutButton.setForeground(new Color(51, 103, 153));
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        logoutButton.setPreferredSize(new Dimension(200, 60));
        // Imposta la dimensione massima per corrispondere a quella preferita ()
        logoutButton.setMaximumSize(new Dimension(250, 50)); // Lo usiamo quando si usa il BoxLayout
       
        logoutButton.setFocusPainted(false);
        ovestContainer.add(logoutButton);
        ovestContainer.add(Box.createVerticalStrut(20));
        
        
        
        GoToClientLogin_Listener clientLogin = new GoToClientLogin_Listener();
        logoutButton.addMouseListener(clientLogin);
        

        
        centerContainer = new JPanel();
        centerContainer.setLayout(new BorderLayout());
        centerContainer.setBackground(Color.WHITE);
        centerContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        
        
        String [] datiAerei = {"Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000" ,"Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000","Aereo 23", "Aereo 543", "Aereo 1000"};
        listaBiglietti = new JList(datiAerei);
        
        
        
        scrollPaneBiglietti = new JScrollPane(listaBiglietti);
        scrollPaneBiglietti.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneBiglietti.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerContainer.add(scrollPaneBiglietti, BorderLayout.CENTER);
        
        
        
        this.getContentPane().add(ovestContainer, BorderLayout.WEST);
        this.getContentPane().add(centerContainer, BorderLayout.CENTER);
           
        // this.setVisible(true); Debug
    }
}
	

