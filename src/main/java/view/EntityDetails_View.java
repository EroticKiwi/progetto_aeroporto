package view;

import javax.swing.*;
import java.awt.*;

public class EntityDetails_View extends JFrame {

    private JPanel ovestContainer;
    private JPanel centerContainer;
    private JPanel southContainer;
    
    private JLabel registerTitleLabel;

    public EntityDetails_View() {
        super("Mostra dettagli delle entità");
        this.setSize(1100, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout()); // BORDER LAYOUT PER IL CONTENT PANE

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

        // --- Pannello di destra ---
        centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setBackground(new Color(40, 44, 52));
        centerContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
        
        
        // METODO CHE CHIAMA IL PANNELLO DI DESTRA PER OGNI ENTITA SPECIFICA
        this.pannelloDettagliVolo();
        // this.pannelloDettagliBiglietto();
        // this.pannelloDettagliAeroporto();
        // this.pannelloDettagliAereo();
        // this.pannelloDettagliCliente();

        
        // METODO CHE AGGIUNGE I BOTTONI (DOBBIAMO FARLO NEL CASO TU SIA UN AMMINISTRATORE)
        this.aggiungiBottoni();
        
        
        
        
        
        
        
        this.getContentPane().add(ovestContainer, BorderLayout.WEST);
        this.getContentPane().add(centerContainer, BorderLayout.CENTER);
        
        this.setVisible(true);
    }

    private void pannelloDettagliVolo() {
        JLabel entitaVolo;
        JLabel idVolo;
        JLabel idAereo;
        JLabel nomeVolo;
        JLabel idAeroportoPartenza;
        JLabel idAeroportoArrivo;
        JLabel prezzo;
        JLabel valido;
        JLabel postiLiberi;
        JLabel orarioPartenza;
        JLabel orarioArrivo;

        entitaVolo = new JLabel("Volo");
        entitaVolo.setForeground(new Color(51, 103, 153));
        entitaVolo.setFont(new Font("Segoe UI", Font.BOLD, 40));
        entitaVolo.setAlignmentX(Component.LEFT_ALIGNMENT);

        idVolo = new JLabel("Id:");
        idVolo.setForeground(Color.WHITE);
        idVolo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idVolo.setAlignmentX(Component.LEFT_ALIGNMENT);

        idAereo = new JLabel("Id Aereo:");
        idAereo.setForeground(Color.WHITE);
        idAereo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idAereo.setAlignmentX(Component.LEFT_ALIGNMENT);

        nomeVolo = new JLabel("Nome Volo:");
        nomeVolo.setForeground(Color.WHITE);
        nomeVolo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nomeVolo.setAlignmentX(Component.LEFT_ALIGNMENT);

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

        valido = new JLabel("Valido: ");
        valido.setForeground(Color.WHITE);
        valido.setFont(new Font("Segoe UI", Font.BOLD, 16));
        valido.setAlignmentX(Component.LEFT_ALIGNMENT);

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


        // Aggiungi le etichette al pannello centerContainer
        centerContainer.add(entitaVolo);
        centerContainer.add(Box.createVerticalStrut(30));
        centerContainer.add(idVolo);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(idAereo);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(nomeVolo);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(idAeroportoPartenza);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(idAeroportoArrivo);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(prezzo);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(valido);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(postiLiberi);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(orarioPartenza);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(orarioArrivo);
        
        

    }

    private void pannelloDettagliBiglietto() {
        JLabel entitaBiglietto;
        JLabel idBiglietto;
        JLabel idCliente;
        JLabel idVolo;
        JLabel valido;

        entitaBiglietto = new JLabel("Biglietto");
        entitaBiglietto.setForeground(new Color(51, 103, 153));
        entitaBiglietto.setFont(new Font("Segoe UI", Font.BOLD, 40));
        entitaBiglietto.setAlignmentX(Component.LEFT_ALIGNMENT);

        idBiglietto = new JLabel("Id:");
        idBiglietto.setForeground(Color.WHITE);
        idBiglietto.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idBiglietto.setAlignmentX(Component.LEFT_ALIGNMENT);

        idCliente = new JLabel("Id Cliente:");
        idCliente.setForeground(Color.WHITE);
        idCliente.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idCliente.setAlignmentX(Component.LEFT_ALIGNMENT);

        idVolo = new JLabel("Id Volo:");
        idVolo.setForeground(Color.WHITE);
        idVolo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idVolo.setAlignmentX(Component.LEFT_ALIGNMENT);

        valido = new JLabel("Valido:");
        valido.setForeground(Color.WHITE);
        valido.setFont(new Font("Segoe UI", Font.BOLD, 16));
        valido.setAlignmentX(Component.LEFT_ALIGNMENT);


        centerContainer.add(entitaBiglietto);
        centerContainer.add(Box.createVerticalStrut(30)); // Spazio dopo il titolo
        centerContainer.add(idBiglietto);
        centerContainer.add(Box.createVerticalStrut(10)); // Spazio tra le etichette
        centerContainer.add(idCliente);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(idVolo);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(valido);
        
        
    }

    private void pannelloDettagliAeroporto() {
        JLabel entitaAeroporto;
        JLabel idAeroporto;
        JLabel citta;
        JLabel nazione;
        JLabel numeroPiste;

        entitaAeroporto = new JLabel("Aeroporto");
        entitaAeroporto.setForeground(new Color(51, 103, 153));
        entitaAeroporto.setFont(new Font("Segoe UI", Font.BOLD, 40));
        entitaAeroporto.setAlignmentX(Component.LEFT_ALIGNMENT);

        idAeroporto = new JLabel("Id Aeroporto:");
        idAeroporto.setForeground(Color.WHITE);
        idAeroporto.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idAeroporto.setAlignmentX(Component.LEFT_ALIGNMENT);

        citta = new JLabel("Città:");
        citta.setForeground(Color.WHITE);
        citta.setFont(new Font("Segoe UI", Font.BOLD, 16));
        citta.setAlignmentX(Component.LEFT_ALIGNMENT);

        nazione = new JLabel("Nazione:");
        nazione.setForeground(Color.WHITE);
        nazione.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nazione.setAlignmentX(Component.LEFT_ALIGNMENT);

        numeroPiste = new JLabel("Numero Piste:");
        numeroPiste.setForeground(Color.WHITE);
        numeroPiste.setFont(new Font("Segoe UI", Font.BOLD, 16));
        numeroPiste.setAlignmentX(Component.LEFT_ALIGNMENT);

        centerContainer.add(entitaAeroporto);
        centerContainer.add(Box.createVerticalStrut(30));
        centerContainer.add(idAeroporto);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(citta);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(nazione);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(numeroPiste);
        
        
    }

    private void pannelloDettagliAereo() {
        JLabel entitaAereo;
        JLabel idAereo;
        JLabel idAeroportoResidenza;
        JLabel modello;
        JLabel capienza;

        entitaAereo = new JLabel("Aereo");
        entitaAereo.setForeground(new Color(51, 103, 153));
        entitaAereo.setFont(new Font("Segoe UI", Font.BOLD, 40));
        entitaAereo.setAlignmentX(Component.LEFT_ALIGNMENT);

        idAereo = new JLabel("Id Aereo:");
        idAereo.setForeground(Color.WHITE);
        idAereo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idAereo.setAlignmentX(Component.LEFT_ALIGNMENT);

        idAeroportoResidenza = new JLabel("Id Aeroporto Residenza:");
        idAeroportoResidenza.setForeground(Color.WHITE);
        idAeroportoResidenza.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idAeroportoResidenza.setAlignmentX(Component.LEFT_ALIGNMENT);

        modello = new JLabel("Modello:");
        modello.setForeground(Color.WHITE);
        modello.setFont(new Font("Segoe UI", Font.BOLD, 16));
        modello.setAlignmentX(Component.LEFT_ALIGNMENT);

        capienza = new JLabel("Capienza:");
        capienza.setForeground(Color.WHITE);
        capienza.setFont(new Font("Segoe UI", Font.BOLD, 16));
        capienza.setAlignmentX(Component.LEFT_ALIGNMENT);

        centerContainer.add(entitaAereo);
        centerContainer.add(Box.createVerticalStrut(30));
        centerContainer.add(idAereo);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(idAeroportoResidenza);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(modello);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(capienza);
        
    }

    private void pannelloDettagliCliente() {
        JLabel entitaCliente;
        JLabel idCliente;
        JLabel email;
        JLabel password;
        JLabel nome;
        JLabel cognome;
        JLabel metodoPagamento;

        entitaCliente = new JLabel("Cliente");
        entitaCliente.setForeground(new Color(51, 103, 153));
        entitaCliente.setFont(new Font("Segoe UI", Font.BOLD, 40));
        entitaCliente.setAlignmentX(Component.LEFT_ALIGNMENT);

        idCliente = new JLabel("Id Cliente:");
        idCliente.setForeground(Color.WHITE);
        idCliente.setFont(new Font("Segoe UI", Font.BOLD, 16));
        idCliente.setAlignmentX(Component.LEFT_ALIGNMENT);

        email = new JLabel("Email:");
        email.setForeground(Color.WHITE);
        email.setFont(new Font("Segoe UI", Font.BOLD, 16));
        email.setAlignmentX(Component.LEFT_ALIGNMENT);

        password = new JLabel("Password:");
        password.setForeground(Color.WHITE);
        password.setFont(new Font("Segoe UI", Font.BOLD, 16));
        password.setAlignmentX(Component.LEFT_ALIGNMENT);

        nome = new JLabel("Nome:");
        nome.setForeground(Color.WHITE);
        nome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nome.setAlignmentX(Component.LEFT_ALIGNMENT);

        cognome = new JLabel("Cognome:");
        cognome.setForeground(Color.WHITE);
        cognome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        cognome.setAlignmentX(Component.LEFT_ALIGNMENT);

        metodoPagamento = new JLabel("Metodo di Pagamento:");
        metodoPagamento.setForeground(Color.WHITE);
        metodoPagamento.setFont(new Font("Segoe UI", Font.BOLD, 16));
        metodoPagamento.setAlignmentX(Component.LEFT_ALIGNMENT);

        centerContainer.add(entitaCliente);
        centerContainer.add(Box.createVerticalStrut(30));
        centerContainer.add(idCliente);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(email);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(password);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(nome);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(cognome);
        centerContainer.add(Box.createVerticalStrut(10));
        centerContainer.add(metodoPagamento);
        
        
    }
    
    private void aggiungiBottoni ()
    {
    	JButton bottoneModifica;
    	JButton bottoneElimina;
    	
    	bottoneModifica = new JButton("Modifica");
    	bottoneModifica.setForeground(new Color(51, 103, 153));
    	bottoneModifica.setFont(new Font("Segoe UI", Font.BOLD, 16));
    	bottoneModifica.setFocusPainted(false);
    	
    	bottoneElimina = new JButton("Elimina");
    	bottoneElimina.setForeground(new Color(51, 103, 153));
    	bottoneElimina.setFont(new Font("Segoe UI", Font.BOLD, 16));
    	bottoneElimina.setFocusPainted(false);
    	centerContainer.add(Box.createVerticalStrut(70));
    	centerContainer.add(bottoneModifica);
    	centerContainer.add(Box.createVerticalStrut(20));
    	centerContainer.add(bottoneElimina);
    	
    	
    }
}