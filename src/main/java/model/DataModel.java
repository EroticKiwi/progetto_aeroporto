package model;

//Il PreparedStatenebt lo utilizziamo per eseguire query SQL precompilate e parametrizzate, prevenendo SQL Injection.
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Utilizziamo le Map per passare parametri alle query in modo flessibile (mappa di indice a valore)
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

	public void inserisciEntita(String query, Map<Integer, Object> params) throws InserisciException, SQLException{
		
		PreparedStatement statement = null;
		try {
			 statement = DBConnection.getInstance().connection.prepareStatement(query);
			 AssemblaStatement(statement, params);
			 statement.execute();
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			if(CheckDBException(e.getSQLState())) {
				throw e;
			}
			throw new InserisciException(e.getMessage());
		} finally {
			try {
				if(statement != null){
					 statement.close();
				}
			} catch(SQLException e) {
				throw new InserisciException(e.getMessage());
			}
		}
		
	}

	public void eliminaEntita(String query, Map<Integer, Object> params) throws SQLException{
		
		PreparedStatement statement = null;
		try {
			 statement = DBConnection.getInstance().connection.prepareStatement(query);
			 AssemblaStatement(statement, params);
			 statement.execute();
			statement.close();
		} catch (SQLException e) {
			if(CheckDBException(e.getSQLState())) {
				throw e;
			}
		}	finally {
			try {
				 statement.close();
			} catch(SQLException e) {
				throw e;
			}
		}
	}

	public ResultSet trovaEntita(String query, Map<Integer, Object> params) throws SQLException{
		
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			statement = DBConnection.getInstance().connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Ci serve per poter scorrere avanti ed indietro il result set.
			if(params != null) {
				AssemblaStatement(statement, params);
			}
			rs = statement.executeQuery();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			throw e;
		}
		
		return rs;
	}
	
	private void AssemblaStatement(PreparedStatement statement, Map<Integer, Object> params) throws SQLException { // Metodo che ci permette di "assemblare" il PreparedStatement che useremo, direttamente nel model.
		
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
	
	boolean CheckDBException(String sqlCode) { // true = errore del database, false = il db funziona
		
		if(sqlCode.equals("08001")) {
			return true;
		}
		
		if(sqlCode.equals("08006")) {
			return true;
		}
		
		if(sqlCode.equals("08003")) {
			return true;
		}
		
		if(sqlCode.equals("08004")) {
			return true;
		}
		
		if(sqlCode.equals("53300")) {
			return true;
		}
		
		if(sqlCode.equals("57P01")) {
			return true;
		}
		
		if(sqlCode.equals("3D000")) {
			return true;
		}
		
		return false;
	}
	

}
