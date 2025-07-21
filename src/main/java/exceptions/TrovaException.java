package exceptions;

/* CASI D'ERRORE:
 * 1) Problemi di connessione al DB
 * 2)
 * */

public class TrovaException extends Throwable
{
    public String getMessage() {
    	return "I dati non sono presenti nel sistema";
    }
}
