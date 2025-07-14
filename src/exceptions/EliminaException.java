package exceptions;

public class EliminaException extends Throwable 
{
    public String getMessage() {
    	return "La cancellazione dei dati non è andata a buon fine";
    }
}
