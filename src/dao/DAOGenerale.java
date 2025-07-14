package dao;
import java.sql.ResultSet;
import java.util.ArrayList;

import exceptions.*;

public interface DAOGenerale{
	
	public void inserisciEntita(String query) throws InserisciException; // Inserisce e modifica dati nella base dati
	// public void modificaEntita(String query) throws InserisciException;
	public void eliminaEntita(String query) throws EliminaException; // Elimina dati nella base dati
	public ResultSet trovaEntita(String query) throws TrovaException; // Trova una singola istanza di entità nella base dati
}