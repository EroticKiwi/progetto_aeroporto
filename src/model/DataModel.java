package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import dao.DAOGenerale;
import db.DBConnection;

import exceptions.*;

/*
 * NOTE:
 * 
 * NOTA SUI PREPARED STATEMENT:
 * Un PreparedStatement è un oggetto di cui necessitiamo per poter fare una query sul database dopo esserci
 * connessi. Questo tipo di statement in particolare evita il rischio di SQL Injection.
 * Un PS si prepara dall'oggetto Connection del DB, e poi si chiama il suo metodo "execute()" o "executeQuery()".
 * Lancia una SQLException se la query non è andata a buon fine.
 * 
 * NOTA SULLA CHIUSURA DEGLI STATEMENT E DEI RESULT SET:
 * La chiusura degli statement (e di conseguenza dei result set legati a quegli statement) avverrà nel metodo
 * del controller che chiama GestioneDati, nel caso di TrovaEntita(). Questo perchè chiudere lo statement E POI
 * ritornare un ResultSet derivante da quello statement potrerebbe il ResultSet ad essere chiuso a sua volta, restituendo
 * al controller un RS inutilizzabile.
 * Come regola generale è bene notare che se un metodo deve ricevere un ResultSet da un altro metodo, allora
 * è necessario che a chiudere il ResultSet sia il metodo che deve utilizzare il ResultSet, con una riga
 * di codice simile a:
 * resultSet.getStatement().close();
 * Dunque andiamo a chiudere direttamente lo statement che di conseguenza chiuderà anche il resultSet associato.
 * */

public class DataModel implements DAOGenerale{
	
	private static DataModel instance;
	
	private DataModel() { }
	
	public static DataModel getInstance() {
		if(instance == null) {
			instance = new DataModel();
		}
		
		return instance;
	}

	public void inserisciEntita(String query, Map<Integer, Object> params) throws InserisciException{
		
		PreparedStatement statement = null;
		try {
			 statement = DBConnection.getInstance().connection.prepareStatement(query);
			 AssemblaStatement(statement, params);
			 statement.execute();
			 statement.close();
		} catch (SQLException e) {
			throw new InserisciException(e.getSQLState());
		}
		
	}

	public void eliminaEntita(String query, Map<Integer, Object> params) throws EliminaException{
		
		PreparedStatement statement = null;
		try {
			 statement = DBConnection.getInstance().connection.prepareStatement(query);
			 AssemblaStatement(statement, params);
			 statement.execute();
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			throw new EliminaException();
		}
	}

	public ResultSet trovaEntita(String query, Map<Integer, Object> params) throws TrovaException{
		
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			statement = DBConnection.getInstance().connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Ci serve per poter scorrere avanti ed indietro il result set.
			if(params != null) {
				AssemblaStatement(statement, params);
			}
			rs = statement.executeQuery();
		} catch(SQLException e) {
			throw new TrovaException();
		}
		
		return rs;
	}
	
	void AssemblaStatement(PreparedStatement statement, Map<Integer, Object> params) throws SQLException { // Metodo che ci permette di "assemblare" il PreparedStatement che useremo, direttamente nel model.
		
		int index;
		Object value;
		
		for(Map.Entry<Integer, Object> element : params.entrySet()) {
			
			index = element.getKey(); // ? interessato
			value = element.getValue(); // Valore da inserire nel ? interessato di index
			
			statement.setObject(index, value);
			//System.out.println("DEBUG!");
			
			// Potremmo fare un controllo per vedere se value è NULL, ma per ora non dà problemi 
			// lasciarlo così!
		}
	}
	

}
