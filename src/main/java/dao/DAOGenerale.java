package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import exceptions.*;

public interface DAOGenerale{
	
	public void inserisciEntita(String query, Map<Integer, Object> params) throws InserisciException, SQLException; // Inserisce e modifica dati nella base dati
	public void eliminaEntita(String query, Map<Integer, Object> params) throws EliminaException, SQLException; // Elimina dati nella base dati
	public ResultSet trovaEntita(String query, Map<Integer, Object> params) throws TrovaException, SQLException; // Trova una singola istanza di entità nella base dati
}