package exceptions;

public class InserisciException extends Throwable 
{
    public String getMessage() {
    	return "i dati inseriti non sono corretti o sono già presenti nel sistema";
    }
}