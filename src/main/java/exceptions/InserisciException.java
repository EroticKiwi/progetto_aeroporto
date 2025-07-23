package exceptions;

import java.sql.SQLException;

/* CASI D'ERRORE:
 * 1) Problemi di connessione al DB
 * 2) I dati inseriti sono già presenti nel DB
 * 
 * */

public class InserisciException extends Throwable
{	
	
	String message;
	
	public InserisciException(String message) { // in questo parametro arriva la stringa dell' SQLException
		this.message = message.toLowerCase();  
		System.out.println(message);
	}
	
    public String getMessage() { // Controlla le specifiche dell'errore
    	// Se il messaggio di errore dell' SQLException contiene una di queste stringhe
    	// Allora noi "Specifichiamo l'errore"
    	
    	if(message.contains("non esiste")) {
    		return "";
    	}
    	
    	if(message.contains("nome")) {
    		return "Il campo 'Nome' è già presente nella base dati.";
    	}
    	
    	if(message.contains("cognome")) {
    		return "Il campo 'Cognome' è già presente nella base dati.";
    	}
    	
    	if(message.contains("email")) {
    		return "L'email inserita appartiene già ad un altro account.";
    	}
    	
    	return message;
    }
}