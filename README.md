# progetto_aeroporto
Progetto Basi Dati - Programmazione a Oggetti UNINA Corso di Informatica 2024/2025

*Descrizione breve:*
Questo è un programma per un ipotetica compagnia aerea che permette la gestione dei propri voli, e l'acquisto di biglietti da parte di utenti registrati.
Realizzato per gli esami congiunti di *Programmazione ad oggetti* e *Basi Dati* da Paragliola Francesco Pio e Gabriele Rosario Russo.

*Funzionalità*:
- Registrazione, accesso e cancellazione dell'account per utenti.
- Acquisto di biglietti e cancellazione di biglietti per utenti.
- Modifica e rimozione di voli da parte di amministratori.

*Descrizione tecnica:*
Programma che impiega il paradigma di programmazione a eventi sviluppato in JAVA che si collega ad un database Postgre. Le build del programma sono effettuate tramite Maven, sfruttando il file pom.xml che contiene i metadati relativi al programma.

*Design Pattern implementati:*
- Model View Controller (MVC) per la suddivisione dei compiti e della responsabilità tra *Interfaccia Utente*, *Gestione della logica applicativa* e *Gestione dei dati*. 
- Data Access Objects (DAO) per le operazioni sui dati.
- Singleton per la centralizzazione di componenti chiave del programma quali *Controller* e *Model*.
- Observer e Observable (Listener, in JAVA) utilizzati per componenti grafiche quali bottoni e scroll.
