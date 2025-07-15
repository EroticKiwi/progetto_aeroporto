package exceptions;

import java.sql.SQLException;

/* CASI D'ERRORE:
 * 1) Problemi di connessione al DB
 * 2) I dati inseriti sono già presenti nel DB
 * 
 * */

public class InserisciException extends Throwable
{
	private String errorCode;
	
	public InserisciException(String errorCode) {
		this.errorCode = errorCode;
	}
	
    public String getMessage() {
    	switch(this.errorCode) {
    		case "23505":
    			System.out.println("Almeno uno dei dati inseriti è già presente nella base dati.");
    	}
    	
    	return errorCode;
    }
}