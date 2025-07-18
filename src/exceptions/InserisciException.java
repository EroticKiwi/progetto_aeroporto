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
	
	public InserisciException(String message) {
		this.message = message.toLowerCase();
	}
	
    public String getMessage() {
    	
    	if(message.contains("nome")) {
    		return "Il campo 'Nome' è già presente nella base dati.";
    	}
    	
    	if(message.contains("cognome")) {
    		return "Il campo 'Cognome' è già presente nella base dati.";
    	}
    	
    	if(message.contains("email")) {
    		return "Il campo 'Email' è già presente nella base dati.";
    	}
    	
    	return message;
    }
}