package exceptions;

public class TrovaException extends Throwable
{
    public String getMessage() {
    	return "I dati non sono presenti nel sistema";
    }
}
